package com.remme.springboot.domain.user;

import com.remme.springboot.domain.posts.BaseTimeEntitiy;
import lombok.Builder;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    // jpa로 디비에 저장할때 enum값을 어떤 형태로 저장할지를 결정
    // 기본적으로 int로 된 숫자가 저장된다.
    // 숫자로 저장되면 디비로 확인할때 그 값이 무슨 코드인지 알수 없어서 문자열로 저장한다.
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Builder
    public User (String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;

    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();

    }

}
