package org.myreviewonline.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.myreviewonline.Domain.Enums.Genre;
import org.myreviewonline.Domain.Enums.TypeContent;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter @Getter
@Data
@NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TypeContent typeContent;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String synopsis;
    private String studio;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date releaseDate;
    private String coverImage;

    @OneToMany(mappedBy = "content")
    private ArrayList<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "content_list",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "list_id")
    )
    private ArrayList<List> lists;
}
