package com.haenandong.springboot.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/test")
public class HttpTestController {

    @PostMapping("/group")
    public GroupDto post(@RequestBody GroupDto groupDto) {
        return groupDto;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    private static class GroupDto {
        private String groupName;
        private List<String> members;
        private DateObj date;

        @Setter
        @Getter
        @NoArgsConstructor
        private class DateObj {
            private int year;
            private int month;
            private int day;
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/dev/hello")
    public String devHello(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");

        if(!"DEV".equals(auth)){
            throw new AccessDeniedException();
        }
        return "devHello";
    }

    @GetMapping("/real/hello")
    public String realHello(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");

        if(!"PRODUCTION".equals(auth)){
            throw new AccessDeniedException();
        }
        return "productionHello";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    static class AccessDeniedException extends RuntimeException {

    }

    @GetMapping("/cookie")
    public String getCookie(@CookieValue("user") String user) {
        return user+"님 안녕하세요!";
    }

}
