/**
 * 
 */
package com.example.demo.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.IAccountRepository;
import com.example.demo.service.IUserDetailsServiceImpl;

/**
 * @author hoang.nguyen_viet
 *
 */
@Service
public class UserDetailsServiceImpl implements IUserDetailsServiceImpl {

    @Autowired
    private IAccountRepository accountRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).get();

        if (account == null) {
            throw new UsernameNotFoundException("User not found with username or email: " + account.getUsername());
        } ;

        Set<GrantedAuthority> authorities = account.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toSet());

        return new MyUserDetails(account.getId(), account.getUsername(), account.getPassword(),
                authorities);
    }

}
