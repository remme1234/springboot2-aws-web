package com.remme.springboot.config.auth;

import com.remme.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
// spring security 설정들을 활성화 시켜준다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위해 해당옵션들을 disable 합니다.
                .csrf().disable()
                .headers().frameOptions().disable()
            .and()
                // url별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션, url, http 메소드별로 관리가 가능, / 등 지정된 url들은 permitAll 옵션을 통해 전체 열람 권한을 주었음
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정된 값들 이외 나머지 url들을 나타냄, 여기서 authenticated를 추가하여 나머지 url들은 모두 인증된 사용자들에게만 허용한다.
                .anyRequest().authenticated()
            .and()
                // 로그아웃 기능에대한 설정의 진입점, 로그아웃 성공시 해당 / 주소로 이동한다.
                .logout()
                    .logoutSuccessUrl("/")
            .and()
                // oauth2 로그인 기능에대한 설정이 진입점
                .oauth2Login()
                    // oauth2 로그인 성공 이후 사용자 정보를 가져올때 설정 담당
                    .userInfoEndpoint()
                        // 소셜 로그인 성공시 후속조치를 진행할 userService 인터페이스의 구현체를 등록
                        // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다
                        .userService(customOAuth2UserService);
    }
}
