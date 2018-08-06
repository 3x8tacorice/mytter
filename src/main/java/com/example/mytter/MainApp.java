package com.example.mytter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@SpringBootApplication
@EnableWebSecurity
@EnableOAuth2Sso
@Profile(value={"local"})
public class MainApp extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       // ログアウト処理のパス
                .logoutSuccessUrl("/")
                // ログアウト時に削除するクッキー名
                .deleteCookies("JSESSIONID")
                // ログアウト時のセッション破棄を有効化
                .invalidateHttpSession(true)
                .permitAll();

    }
    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
                                                 OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }
}
