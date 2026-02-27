package com.facet.api.ask;

import com.facet.api.ask.model.Ask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AskRepository extends JpaRepository<Ask,Long> {

}
