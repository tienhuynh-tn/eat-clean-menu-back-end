package com.happy3friends.eatcleanmenubackend.sercurity;

import com.happy3friends.eatcleanmenubackend.entity.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Mặc định Spring Security sử dụng một đối tượng UserDetails để chứa toàn bộ thông tin về người dùng
 * CustomUserDetails chuyển thông tin từ AccountEntity thành UserDetails
 */
public class CustomUserDetails implements UserDetails {
    private int id;
    private String gmail;
    private String password;
    private String fullName;
    private String avatar;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomUserDetails(int id, String gmail, String password, String fullName, String avatar, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.gmail = gmail;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(UsersEntity usersEntity) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomUserDetails(
                usersEntity.getId(),
                usersEntity.getGmail(),
                usersEntity.getPassword(),
                usersEntity.getFullname(),
                usersEntity.getAvatar(),
                authorities
        );
    }

    public static CustomUserDetails create(UsersEntity usersEntity, Map<String, Object> attributes) {
        CustomUserDetails customUserDetails = CustomUserDetails.create(usersEntity);
        customUserDetails.setAttributes(attributes);
        return customUserDetails;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return gmail;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGmail() {
        return gmail;
    }

    public String getAvatar() {
        return avatar;
    }
}
