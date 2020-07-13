package com.haenandong.springboot.config.auth;

import com.haenandong.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity  // spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당옵션들을 disable
                .and()
                    .authorizeRequests()    // URL별 권한 관리를 설정하는 옵션의 시작점, antMatchers: 권한관리대상 지정하는 옵션
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // 지정 URL은 전체 열람 권한 부여
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // 지정 URL은 USER 권한을 가진 사람만 권한 부여
                    .anyRequest().authenticated()   // 설정된 값들 이외 나머지 URL은 모두 인증된(로그인한) 사용자들에게만 허용
                .and()
                    .logout()   // 로그아웃 기능 설정
                    .logoutSuccessUrl("/")  // 로그아웃 성공시 "/" 주소로 이동
                .and()
                    .oauth2Login()  // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올때의 설정 담당
                            .userService(customOAuth2UserService);  //소셜로그인 성공 시 후속조치를 진행할 UserService 인터페이스의 구현제 등록, 리소스서버(소셜서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }
}
