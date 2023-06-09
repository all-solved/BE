package com.allsolved.allsolved.user.entity;

import com.allsolved.allsolved.baseTime.BaseTimeEntity;
import com.allsolved.allsolved.user.dto.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "also_user")
public class AlsoUser extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "also_email", unique = true)
    private String alsoEmail;

    @Column(name = "also_password")
    private String alsoPassword;

    @Column(name = "also_name")
    private String alsoName;

    @Column(name = "role")
    private String role;

    @OneToMany(
            mappedBy = "alsoUser",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Photo> photos = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : role.split(","))
            authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getUsername() {
        return alsoEmail;
    }

    @Override
    public String getPassword() { return alsoPassword; }

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

    @Builder
    public AlsoUser(String alsoEmail, String alsoPassword, String alsoName, String role) {
        this.alsoEmail = alsoEmail;
        this.alsoPassword = alsoPassword;
        this.alsoName = alsoName;
        this.role = role;
    }

    public AlsoUser(String alsoEmail, String alsoPassword, String alsoName) {
        this.alsoEmail = alsoEmail;
        this.alsoPassword = alsoPassword;
        this.alsoName = alsoName;
        this.role = Role.ADMIN.getValue();
    }

    public void update(String alsoName) {
        this.alsoName = alsoName;
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.setAlsoUser(this);
    }
}
