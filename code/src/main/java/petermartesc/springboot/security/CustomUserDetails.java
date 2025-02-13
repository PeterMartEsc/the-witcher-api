package petermartesc.springboot.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author mackstm
 */

public class CustomUserDetails implements UserDetails {

    /**
     * Properties
     */
    private final String username;
    private final String role;

    /**
     * Full constructor
     * @param username of the user
     * @param role of the user
     */
    public CustomUserDetails(String username, String role) {
        this.username = username;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() { return null; 	}

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}