package com.facet.api.auction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class AucProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String brandName;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int startPrice;

    @Column(nullable = false)
    private Date startAt;

    @Column(nullable = false)
    private Date endAt;

    @Column(nullable = false)
    private int status;

    @ColumnDefault("10000")
    @Column(nullable = false)
    private int bidIncrement;

    @Column(nullable = false)
    private Long currentPrice;

    @Column(nullable = false)
    private int bidCount;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String material;

    private String size;

    private String shippingMethod;

    private int shippingPrice;

    private String shippingDuration;

    public static AucDto.DetailRes from(AucProduct entity) {
        return AucDto.DetailRes.builder()
                .idx(entity.getIdx())
                .category(entity.getCategory())
                .name(entity.getName())
                .description(entity.getDescription())
                .brandName(entity.getBrandName())
                .image(entity.getImage())
                .startPrice(entity.getStartPrice())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .status(entity.getStatus())
                .bidIncrement(entity.getBidIncrement())
                .currentPrice (entity.getCurrentPrice())
                .bidCount(entity.getBidCount())
                .origin(entity.getOrigin())
                .material(entity.getMaterial())
                .size(entity.getSize())
                .shippingMethod(entity.getShippingMethod())
                .shippingPrice(entity.getShippingPrice())
                .shippingDuration(entity.getShippingDuration())
                .build();
    }
}
