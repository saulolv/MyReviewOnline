package org.myreviewonline.Domain.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Episode {

    @Id
    private UUID id;
    private String title;
    private String synopsis;
    private Double duration;
    private Integer season;
    private Integer episode;
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;
}
