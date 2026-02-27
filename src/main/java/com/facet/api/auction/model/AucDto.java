package com.facet.api.auction.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

public class AucDto {
    @Getter
    @Builder
    public static class ListRes{
        private Long idx;
        private String name;
        private String category;
        private String brandName;
        private int status;
        private String image;
        private int startPrice;
        private Date startAt;
    }

    @Getter
    @Builder
    public static class RankRes{
        private Long idx;
        private String name;
        private String image;
        private int status;
    }

    @Getter
    @Builder
    public static class SlideRes{
        private Long idx;
        private String name;
        private String image;
        private int status;
    }
}
