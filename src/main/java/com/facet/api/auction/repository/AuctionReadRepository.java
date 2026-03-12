package com.facet.api.auction.repository;

import com.facet.api.auction.model.AucProduct;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuctionReadRepository extends JpaRepository<AucProduct, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from AucProduct p where p.idx = :idx")
    Optional<AucProduct> findByIdWithLock(@Param("idx") Long idx);

    // AuctionRepository.java
    Page<AucProduct> findAllByName(String search, Pageable pageRequest);

}
