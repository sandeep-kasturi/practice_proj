package com.dailycodebuffer.client.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dailycodebuffer.client.entity.User;

public class MyUserDetails implements UserDetails{

    // //This implementation is for each user has only one role
    // @Autowired
    // private User user;

    // public MyUserDetails(User user) {
    //     this.user = user;
    // }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
	// 	SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
	// 	return Arrays.asList(authority);
    // }

    // @Override
    // public String getPassword() {
    //     return user.getPassword();
    // }

    // @Override
    // public String getUsername() {
    //     return user.getFirstName();
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     return user.isEnabled();
    // }
    


    //This implementation is for each user can have multiple roles
    private String userName;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authorities;

    public MyUserDetails() {}
    
	public MyUserDetails(User user) {
		this.userName = user.getFirstName();
		this.password = user.getPassword();
		this.isActive = user.isEnabled();
		this.authorities = Arrays.stream(user.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}