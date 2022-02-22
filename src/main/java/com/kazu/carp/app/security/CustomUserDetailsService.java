package com.kazu.carp.app.security;

import com.kazu.carp.security.account.business.IUserAccountService;
import com.kazu.carp.security.account.dto.UserAccountDto;
import com.kazu.carp.security.authorization.business.IAuthorizationService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author SaRPaRDa
 *
 */

@Service("authService")
public class CustomUserDetailsService implements UserDetailsService {

	final RestTemplate restTemplate;
	final IUserAccountService userAccountService;
	final IAuthorizationService authorizationService;

	public CustomUserDetailsService(RestTemplate restTemplate, IUserAccountService userAccountService,
									IAuthorizationService authorizationService) {
		this.restTemplate = restTemplate;
		this.userAccountService = userAccountService;
		this.authorizationService = authorizationService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserAccountDto auth = userAccountService.loadUserByEmailForUserDetailService(username);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		User user = new User(auth.getEmail(), auth.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, getAuthorities(username));

		return user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities(String email) {
		List<String> roleNames = authorizationService.getRoleNameListForUserEmail(email);

		if(roleNames.stream().anyMatch(m-> "KAZU".equals(m))) {
			roleNames.add("ADMIN");
		}

		List<GrantedAuthority> authList = getGrantedAuthorities(
				roleNames);
		return authList;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
