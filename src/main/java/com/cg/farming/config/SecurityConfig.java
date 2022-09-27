package com.cg.farming.config;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cg.farming.service.AdminServiceImpl;
import com.cg.farming.service.FarmerServiceImpl;
import com.cg.farming.service.SupplierServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FarmerServiceImpl farmerDetailsService;
    
//    @Autowired
//    private SupplierServiceImpl supplierDetailsService;
//    
//    @Autowired
//    private AdminServiceImpl adminDetailsService;

    private static Logger logger = LogManager.getLogger();
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(farmerDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        logger.info(auth);
        return auth;
    }
	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
//	@Bean
//    public DaoAuthenticationProvider authenticationProvider1() {
//        DaoAuthenticationProvider auth1 = new DaoAuthenticationProvider();
//        auth1.setUserDetailsService(supplierDetailsService);
//        auth1.setPasswordEncoder(passwordEncoder());
//        return auth1;
//    }
//	
//	protected void configure1(AuthenticationManagerBuilder auth1) throws Exception {
//        auth1.authenticationProvider(authenticationProvider1());
//    }
	
//	@Bean
//    public DaoAuthenticationProvider authenticationProvider2() {
//        DaoAuthenticationProvider auth2 = new DaoAuthenticationProvider();
//        auth2.setUserDetailsService(adminDetailsService);
//        auth2.setPasswordEncoder(passwordEncoder());
//        return auth2;
//    }
//	
//	protected void configureAdmin(AuthenticationManagerBuilder auth2) throws Exception {
//		auth2.authenticationProvider(authenticationProvider1());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf().disable()
	        .authorizeRequests()
//	        .antMatchers("/*").hasRole("FARMER")
//	        .antMatchers("/**").permitAll()
//	        .antMatchers("/auth/signup/*").hasRole("ADMIN")
	        .anyRequest()
	        .authenticated()
	        .and()
	        .httpBasic();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
