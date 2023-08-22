package com.brown.springwebproject.community.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column
    private Long parentId;

    @Column
    private Long ArticleId;
}
