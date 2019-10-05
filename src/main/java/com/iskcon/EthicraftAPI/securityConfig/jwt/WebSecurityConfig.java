package com.iskcon.EthicraftAPI.securityConfig.jwt;

import javax.annotation.PostConstruct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;
import com.iskcon.EthicraftAPI.domain.UserRoleCollegeMapping;
import com.iskcon.EthicraftAPI.repository.RoleRepository;
import com.iskcon.EthicraftAPI.repository.UserRepository;
import com.iskcon.EthicraftAPI.repository.UserRoleCollegeRepo;
import com.iskcon.EthicraftAPI.securityservices.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    CustomUserDetailsService appUserDetailsService;

    @Autowired
    UserRepository userInfoRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    UserRoleCollegeRepo userRoleCollegeRepo;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @PostConstruct
    public void populateRole(){
        List<String> roles = RoleConstant.ROLES;
        roles.forEach(role -> {
            Role dbRole = roleRepository.findByRole(role);
            if(dbRole == null) {
                Role role1 = new Role();
                role1.setRole(role);
                roleRepository.saveAndFlush(role1);
            }

        });
        createAdmin();
    }

    public void createAdmin(){
        User userInfo1 = userInfoRepository.findByEmail("sahil.verma@tothenew.com");
        if(userInfo1==null){
            User userInfo = new User();
            userInfo.setEmail("sahil.verma@tothenew.com");
            userInfo.setPassword(new BCryptPasswordEncoder().encode("igdefault"));
            userInfo.setUsername("sahil verma");
            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findByRole(RoleConstant.ROLE_ADMIN);
            roles.add(role);
            userInfo.setRoles(roles);
            userInfoRepository.saveAndFlush(userInfo);
            UserRoleCollegeMapping userRoleCollegeMapping = new UserRoleCollegeMapping();
            userRoleCollegeMapping.setRole(role);
            userRoleCollegeMapping.setUser(userInfo);
            userRoleCollegeRepo.saveAndFlush(userRoleCollegeMapping);
            System.out.println(" ****  Admin Created  ****");
        }


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/util/populateCollegeData","/util/populateMemberData","/authenticate","/college/collegeDropDown", "/register","/member/quick" +
                "-register").permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
