package com.haenandong.springboot.study.jpabook.ch04;

import com.haenandong.springboot.study.jpabook.ch04.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            //TODO 비즈니스 로직
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {
//        Member member = new Member();
//        member.setId(2L);
//        member.setName("2번");
//        member.setCity("서울");
//        member.setStreet("서울길");
//        member.setZipcode("12345");
//        em.persist(member);
//
//        Item item = new Item();
//        item.setId(2L);
//        item.setName("마우스");
//        item.setPrice(3000);
//        item.setStockQuantity(100);
//        em.persist(item);
//
//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(2L);
//        orderItem.setCount(2);
//        orderItem.setOrderPrice(6000);
//        orderItem.setItem(item);
//        em.persist(orderItem);
//
//        Order order = new Order();
//        order.setId(2L);
//        order.setOrderDate(new Date());
//        order.setMember(member);
//        order.setStatus(OrderStatus.ORDER);
//        em.persist(order);

        //조회
        Long orderID = 1L;
        Order order2 = em.find(Order.class, orderID);
        Member member2 = order2.getMember();  //주문한 회원, 참조사용
        System.out.println(member2);

        OrderItem orderItem2 = order2.getOrderItems().get(0);
        Item item2 = orderItem2.getItem();

        System.out.println(item2);

    }

}
