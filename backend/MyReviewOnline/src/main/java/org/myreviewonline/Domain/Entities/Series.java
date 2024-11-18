package org.myreviewonline.Domain.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;

@Entity @Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Series extends Content {

    private Integer seasons;
    private Integer currentSeason;
    private Integer episodes;

    @OneToMany(mappedBy = "series")
    private ArrayList<Episode> episodeList = new ArrayList<>();
}
