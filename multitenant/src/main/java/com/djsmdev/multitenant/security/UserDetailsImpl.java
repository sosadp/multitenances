package com.djsmdev.multitenant.security;

import com.djsmdev.multitenant.entity.Tenant;
import com.djsmdev.multitenant.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class UserDetailsImpl implements UserDetails {

    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Tenant tenant;


    public UserDetailsImpl(long id, String email, String password, Tenant tenant, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.tenant = tenant;
    }

    public static UserDetailsImpl build (User user){
        //List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
        //        .map(role -> new SimpleGrantedAuthority(role.getName()))
        //        .collect(Collectors.toUnmodifiableList());

        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Tenant tenant = Tenant.builder()
                .id(1)
                .name("sample")
                .active(true)
                .build();
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), tenant, grantedAuthorities);

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
        return email;
    }
}
