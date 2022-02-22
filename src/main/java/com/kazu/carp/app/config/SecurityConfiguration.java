package com.kazu.carp.app.config;

import com.kazu.carp.app.security.CustomUserDetailsService;
import com.kazu.carp.app.security.JWTAuthenticationFilter;
import com.kazu.carp.app.security.JWTAuthorizationFilter;
import com.kazu.carp.app.security.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final PasswordEncoder passwordEncoder;
    final Environment environment;

    @Resource(name = "authService")
    private CustomUserDetailsService cuds;

    public SecurityConfiguration(PasswordEncoder passwordEncoder, Environment environment) {
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/fonts/").permitAll()
                .antMatchers("/css/").permitAll()
                .antMatchers("/js/").permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.AUTH_LOGIN_URL).permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/authentication/check").authenticated()
                .antMatchers("/common/address/**").authenticated()

                .antMatchers("/license/**").hasAnyAuthority("ADMIN")

                //Stock Card
                .antMatchers("/scm/production/definition/stock-card/**").hasAnyAuthority("STOCK_CARD", "ADMIN")

                //Security
                .antMatchers("/user/account/get/**").authenticated()
                .antMatchers("/user/account/*").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/user/authorization/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/user/role/get/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/user/role/*").hasAnyAuthority("USER", "KAZU")

                //Common
                .antMatchers("/common/firm/**").hasAnyAuthority("FIRM", "ADMIN")
                .antMatchers("/common/uom/**").hasAnyAuthority("UOM", "ADMIN")
                .antMatchers("/common/brand/**").hasAnyAuthority("BRAND", "ADMIN")

                //Yarn
                .antMatchers("/yarn/**").hasAnyAuthority("YARN", "ADMIN")

                //Specimen
                .antMatchers("/specimen/sample-yarn/**").hasAnyAuthority("SAMPLE_YARN", "ADMIN")
                .antMatchers("/specimen/creel/**").hasAnyAuthority("CREEL", "ADMIN")

                //Archive
                .antMatchers("/archive/shelve/convert-to-unusable/*").hasAnyAuthority("ARCHIVE_UNUSABLE", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/shelve/convert-to-stock/*").hasAnyAuthority("ARCHIVE_STOCK", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/shelve/convert-to-retired/*").hasAnyAuthority("ARCHIVE_RETIRED", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/sample/convert-to-sample").hasAnyAuthority("ARCHIVE_SAMPLE", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/rd/**").hasAnyAuthority("ARCHIVE_RD", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/convert-to-archive/*").hasAnyAuthority("ARCHIVE", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/add-design").hasAnyAuthority("ARCHIVE_ADD", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/add-version").hasAnyAuthority("ARCHIVE_ADD_VERSION", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/archive/**").hasAnyAuthority("ARCHIVE_READ", "ADMIN")

                //Design Plan
                .antMatchers("/design/plan/actives").hasAnyAuthority("DESIGN_PLAN", "ARCHIVE_SAMPLE", "ARCHIVE_UNUSABLE", "ARCHIVE_STOCK", "ARCHIVE_RETIRED", "ARCHIVE_RD", "ARCHIVE_READ", "ARCHIVE_WRITE", "ADMIN")
                .antMatchers("/design/plan/**").hasAnyAuthority("DESIGN_PLAN", "ADMIN")

                //Image
                .antMatchers("/image/**").hasAnyAuthority("IMAGE", "STOCK_CARD", "ADMIN")

                //File
                .antMatchers("/file/bmp/**").hasAnyAuthority("DOWNLOAD_DESIGN", "ADMIN")
                .antMatchers("/file/**").hasAnyAuthority("DOWNLOAD_JPG", "ADMIN")

                .and()
                .cors().and().csrf().disable()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //@formatter:on
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cuds).passwordEncoder(passwordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", environment.getProperty("derlem.server.ip-address")));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("async-");
        return executor;
    }

    @Bean
    public DelegatingSecurityContextAsyncTaskExecutor taskExecutor(ThreadPoolTaskExecutor delegate) {
        return new DelegatingSecurityContextAsyncTaskExecutor(delegate);
    }
}
