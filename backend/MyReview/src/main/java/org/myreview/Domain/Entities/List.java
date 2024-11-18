package org.myreview.Domain.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity(name = "tb_list")
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
