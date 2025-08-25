package com.example.reminder.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DreamComment")
public class DreamComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dream_comment_id")
    private int dreamCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dream")
    private Dream dream;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment")
    private DreamComment parentComment;

    @Lob
    @Column(name = "content")
    private String content;
}