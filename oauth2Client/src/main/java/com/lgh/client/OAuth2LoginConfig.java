package com.lgh.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationCodeGrantFilter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class OAuth2LoginConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults());
        // Form login handles the redirect to the login page from the
        // authorization server filter chain
        return http.build();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if (StringUtils.pathEquals(username, "lgh")) {
                    return User.builder()
                            .username("lgh")
                            .password("$2a$10$xxoiTjzAmbk.rOxhkbtonOZNFpMEi0K6i2TzV4zS.lUKXImkT3N4K")
                            .roles("USER")
                            .build();
                }
                if (StringUtils.pathEquals(username, "lin")) {
                    return User.builder()
                            .username("lin")
                            .password("$2a$10$xxoiTjzAmbk.rOxhkbtonOZNFpMEi0K6i2TzV4zS.lUKXImkT3N4K")
                            .roles("USER")
                            .build();
                }
                return null;
            }
        };
    }*/

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(meClientRegistration(), giteeClientRegistration());
    }

    private ClientRegistration giteeClientRegistration() {
        return ClientRegistration.withRegistrationId("gitee")
                .clientId("c57ccc3d93609b510f7f1d83572f2b08d1107987fb24560ca95c269f228e6728")
                .clientSecret("89a9e18c467b99396886a1ff8eb289dae563ea5009dfa620ed3b90071fbf9d1b")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationUri("https://gitee.com/oauth/authorize")
                .tokenUri("https://gitee.com/oauth/token")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .userInfoUri("https://gitee.com/api/v5/user")
                .scope("user_info", "projects", "pull_requests", "issues", "notes", "enterprises")
                .clientName("gitee")
                .userNameAttributeName("login")
                .build();
    }

    private ClientRegistration meClientRegistration() {
        return ClientRegistration.withRegistrationId("oidc-client")
                .clientId("oidc-client")
                .clientSecret("secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationUri("http://localhost:9001/oauth2/authorize")
                .tokenUri("http://localhost:9001/oauth2/token")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .userInfoUri("http://localhost:9001/a")
                .scope("openid")
                .clientName("oidc-client")
                .userNameAttributeName("oidc-client")
                .build();
    }
}