package com.xvlkk.sp.shops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_category")
    private Long cdCategory;

    @Column(name = "nm_category")
    private String nmCategory;

    @Column(name = "nm_slug")
    private String nmSlug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_category_parent")
    private Category categoryParent;

    @OneToMany(mappedBy = "categoryParent")
    private List<Category> categoryChilds = new ArrayList<>();
}

