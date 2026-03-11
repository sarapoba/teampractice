package com.facet.api.funding.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private Long percent;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long supporters;

    @Column(nullable = false)
    private Long days;

    @Column(nullable = false)
    private String status;
}
