package com.logicverse.techelp.platform.community.interfaces.acl;

import com.logicverse.techelp.platform.community.domain.model.queries.GetReviewByIdQuery;
import com.logicverse.techelp.platform.community.domain.services.ReviewCommandService;
import com.logicverse.techelp.platform.community.domain.services.ReviewQueryService;
import org.springframework.stereotype.Service;

@Service
public class ReviewContextFacade {
    private final ReviewCommandService reviewCommandService;

    private final ReviewQueryService reviewQueryService;

    public ReviewContextFacade(ReviewCommandService reviewCommandService,
                               ReviewQueryService reviewQueryService){

        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }
    public Long getReviewById(Long reviewId){
        var command = new GetReviewByIdQuery(reviewId);
        var review = this.reviewQueryService.handle(command);
        if (review.isEmpty()) return 0L;
        return review.get().getId();
    }


}
