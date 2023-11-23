package com.logicverse.techelp.platform.community.domain.services;


import com.logicverse.techelp.platform.community.domain.model.entities.Review;
import com.logicverse.techelp.platform.community.domain.model.queries.GetReviewByIdQuery;
import com.logicverse.techelp.platform.community.domain.model.queries.GetReviewByTechnicalIdQuery;


import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {

    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle();
    List<Review> handle(GetReviewByTechnicalIdQuery query);
}
