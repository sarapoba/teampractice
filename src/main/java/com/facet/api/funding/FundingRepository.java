package com.facet.api.funding;

import com.facet.api.funding.model.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<Funding,Long> {
}
