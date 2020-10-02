package com.haenandong.springboot.study.jpabook.ch04;

import com.haenandong.springboot.study.jpabook.ch04.entity.Item;
import com.haenandong.springboot.study.jpabook.ch04.entity.Member;
import com.haenandong.springboot.study.jpabook.ch04.entity.Order;
import com.haenandong.springboot.study.jpabook.ch04.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

        Long orderID = 1L;
        Order order = em.find(Order.class, orderID);
        Member member = order.getMember();  //주문한 회원, 참조사용
        System.out.println(member);

        OrderItem orderItem = order.getOrderItems().get(0);
        Item item = orderItem.getItem();

        System.out.println(item);

    }

}
