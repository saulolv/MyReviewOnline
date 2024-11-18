package org.myreviewonline.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Review {

    @Id
    private Long id;
    @Column(nullable = false, length = 2)
    private Integer rating;
    private String comment;

    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;
}
