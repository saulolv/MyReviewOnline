package org.myreview.Domain.Entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter @Setter
public class Movie extends Content {

    private Integer duration;
}
