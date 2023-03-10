package com.example.kotlinspring.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    
    @Throws(Exception::class)
    override fun configure(security: HttpSecurity) {
        security.httpBasic().disable()
        security.csrf().disable()
        security.cors() // exclude pre-flight response
    }
}
