package com.alc.bookstore.backend.spring;

/*

//TODO @Configuration
public class SecurityConfig {

    //@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/fake/**").permitAll()  // OPEN
                        .anyRequest().authenticated()                      // REST IS CLOSED
                )
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
*/
