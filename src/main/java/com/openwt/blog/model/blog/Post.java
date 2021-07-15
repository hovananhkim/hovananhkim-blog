package com.openwt.blog.model.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openwt.blog.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank
    private String content;

    @Column(nullable = false)
    private Date createDate = new Date();

    @Column(nullable = false)
    private Date updateDate = new Date();

    @NotNull
    private Boolean isActive = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"posts","createDate","updateDate"})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"posts","firstname","lastname","password","roles"})
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnoreProperties("posts")
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
}
