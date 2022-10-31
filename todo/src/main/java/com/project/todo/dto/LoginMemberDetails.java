package com.project.todo.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class LoginMemberDetails implements UserDetails {
    private Long mno;
    private String email;
    private String password;
    private String name;
    private Boolean isAdmin;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.getIsAdmin()){
            return Collections.singletonList(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }
        return Collections.singletonList(new SimpleGrantedAuthority(UserRole.USER.getValue()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }


    // 계정 만료 여부 -> 만료X:만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부 -> 안잠김:잠김
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 -> 만료X:만료
    @Override
   public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자 활성화 여부 -> 활성화:비활성화
    @Override
    public boolean isEnabled() {
        return true;
    }
}
