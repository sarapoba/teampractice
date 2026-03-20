package com.facet.api.auction.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AucDto {
    @Getter
    @Builder
    public static class PageRes{
        private List<ListRes> auctionList;
        private int totalPage;
        private Long totalCount;
        private int currentPage;
        private int currentSize;

        public static PageRes from(Page<AucProduct> result){
            return PageRes.builder()
                    .auctionList(result.get().map(ListRes::from).toList())
                    .totalPage(result.getTotalPages())
                    .totalCount(result.getTotalElements())
                    .currentPage(result.getPageable().getPageNumber())
                    .currentSize(result.getPageable().getPageSize())
                    .build();
        }

    }

    @Getter
    @Builder
    public static class ListRes{
        private Long idx;
        private String name;
        private String category;
        private String brandName;
        private String image;
        private int startPrice;
        private String startAt;
        private String endAt;
        private String status;
        private int bidCount;

        public static ListRes from(AucProduct entity){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime now = LocalDateTime.now().withNano(0);
            String currentStatus;

            if(now.isBefore(entity.getStartAt())) {
                currentStatus = "BEFORE";
            } else if (now.isAfter(entity.getEndAt())) {
                currentStatus = "END";
            } else {
                currentStatus = "LIVE";
            }

            return ListRes.builder()
                    .idx(entity.getIdx())
                    .name(entity.getName())
                    .category(entity.getCategory())
                    .brandName(entity.getBrandName())
                    .image(entity.getImage())
                    .startPrice(entity.getStartPrice())
                    .startAt(entity.getStartAt().format(formatter))
                    .endAt(entity.getEndAt().format(formatter))
                    .status(currentStatus)
                    .bidCount(entity.getBidCount())
                    .build();
        }
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

    @Getter
    @Builder
    public static class DetailRes{
        private Long idx;
        private String category;
        private String name;
        private String description;
        private String brandName;
        private String image;
        private int startPrice;
        private String startAt;
        private String endAt;
        private int bidIncrement;
        private Long currentPrice;
        private int bidCount;
        private String origin;
        private String material;
        private String size;
        private String shippingMethod;
        private int shippingPrice;
        private String shippingDuration;

        public static AucDto.DetailRes from(AucProduct entity) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime now = LocalDateTime.now();
            String currentStatus;

            if(now.isBefore(entity.getStartAt())) {
                currentStatus = "BEFORE";
            } else if (now.isAfter(entity.getEndAt())) {
                currentStatus = "END";
            } else {
                currentStatus = "LIVE";
            }
            return AucDto.DetailRes.builder()
                    .idx(entity.getIdx())
                    .category(entity.getCategory())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .brandName(entity.getBrandName())
                    .image(entity.getImage())
                    .startPrice(entity.getStartPrice())
                    .startAt(entity.getStartAt().format(formatter))
                    .endAt(entity.getEndAt().format(formatter))
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

    @Builder
    @Setter
    @Getter
    public static class BidReq{
        @Schema(description = "경매 상품은 1~10번 상품이 있습니다.", required = true, example = "1")
        private Long aucProductIdx;
        private Long userIdx;
        @Schema(description = "입찰가는 현재 입찰가보다 높은 금액을 넣어야 합니다.", required = true, example = "50000")
        private Long bidPrice;

        public Bid toEntity() {
            return Bid.builder()
                    .aucProductIdx(this.aucProductIdx)
                    .userIdx(this.userIdx)
                    .bidPrice(this.bidPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class BidRes {
        private Long idx;
        private Long aucProductIdx;
        private Long userIdx;
        private Long bidPrice;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createAt;

        public static BidRes from(Bid entity) {
            return BidRes.builder()
                    .idx(entity.getIdx())
                    .aucProductIdx(entity.getAucProductIdx())
                    .userIdx(entity.getUserIdx())
                    .bidPrice(entity.getBidPrice())
                    .createAt(entity.getCreateAt())
                    .build();
        }
    }

    @Getter
    @Setter
    public static class ImageReq{
        private List<MultipartFile> fileList;
        private Long productIdx;

        // 리스트에서 하나씩 꺼내서 이미지 경로 저장할 예정
        public static AucProdImage toEntity(String imagePath, Long productIdx){
            return AucProdImage.builder()
                    .imagePath(imagePath)
                    .aucProductIdx(productIdx)
                    .build();
        }
    }

    // DB 저장 형식 dto
    @Builder
    @Getter
    @Setter
    public static class ImagePathDto{
        private Long productIdx;
        private String imagePath;

        public static AucProdImage toEntity(ImagePathDto dto){
            return AucProdImage.builder()
                    .aucProductIdx(dto.getProductIdx())
                    .imagePath(dto.getImagePath())
                    .build();
        }
    }
}
