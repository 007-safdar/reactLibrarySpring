package react.library.springboot.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        //Disable cross site forgery
        http.csrf().disable();

        //Protect apis with api/<type>/secure
        http.authorizeHttpRequests(
                configurer -> configurer.antMatchers("/api/books/secure/**",
                        "/api/reviews/secure/**").authenticated()
        ).oauth2ResourceServer().jwt();

        //add cors filter
        http.cors();

        //Content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,new HeaderContentNegotiationStrategy());

        //Force a non-empty response body for 401s to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }
}
