package org.myreview.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity(name = "tb_list")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean isPublic = false;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "lists")
    private ArrayList<Content> contents = new ArrayList<>();
}
