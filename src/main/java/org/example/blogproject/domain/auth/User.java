package org.example.blogproject.domain.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.blogproject.domain.blog.Blog;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String username;
    private String password;
    private String name;
    private String email;

    @Column(name = "registration_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate registrationDate = LocalDate.now();

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "email_notify")
    private boolean emailNotify;

    //user - role 설정
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    //user - blog 설정
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Blog blog;

    public void setBlog(Blog blog) {
        this.blog = blog;
        if (blog != null && blog.getUser() != this) {
            blog.setUser(this);
        }
    }
}
