package uo.asw.configuration;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.repositories.OperatorsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private OperatorsRepository operatorsRepository;

	@Override
	public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException{
	    Operator user = operatorsRepository.findByIdentifier(identifier);
	    
	    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
	  
	    return  new org.springframework.security.core.userdetails.User(
	            user.getIdentifier(), user.getPassword(), grantedAuthorities);
	}
}
