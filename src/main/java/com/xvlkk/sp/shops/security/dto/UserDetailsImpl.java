package com.xvlkk.sp.shops.security.dto;

import com.xvlkk.sp.shops.security.mapper.UserMapper;
import com.xvlkk.sp.shops.security.model.Role;
import com.xvlkk.sp.shops.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Getter
@Builder
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long cdUser;
    private String nmUser;
    private String nmEmail;
    private String nmLogin;
    private String nmPassword;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Map<String, List<String>> profileRolesMap = new HashMap<>();

        user.getProfiles().forEach(profile -> {
            List<String> roleNames = profile.getRoles().stream()
                    .map(Role::getRole)
                    .toList();

            profileRolesMap.put(profile.getProfile(), roleNames);

            authorities.add(new SimpleGrantedAuthority("PROFILE_" + profile.getProfile()));

            roleNames.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        });

        return UserMapper.INSTANCE.mapDetails(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.nmPassword;
    }

    @Override
    public String getUsername() {
        return this.nmEmail;
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
        return true;
    }
}
