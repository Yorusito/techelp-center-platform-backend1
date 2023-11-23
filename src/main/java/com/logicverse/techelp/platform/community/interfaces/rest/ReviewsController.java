package com.logicverse.techelp.platform.community.interfaces.rest;

import com.logicverse.techelp.platform.community.domain.model.queries.GetReviewByIdQuery;
import com.logicverse.techelp.platform.community.domain.model.queries.GetReviewByTechnicalIdQuery;
import com.logicverse.techelp.platform.community.domain.services.ReviewCommandService;
import com.logicverse.techelp.platform.community.domain.services.ReviewQueryService;
import com.logicverse.techelp.platform.community.interfaces.rest.resources.CreateReviewResource;
import com.logicverse.techelp.platform.community.interfaces.rest.resources.ReviewResource;
import com.logicverse.techelp.platform.community.interfaces.rest.transform.CreateReviewFromResourceAssembler;
import com.logicverse.techelp.platform.community.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(value="api/v1/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewsController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    public ReviewsController(ReviewQueryService reviewQueryService, ReviewCommandService reviewCommandService) {
        this.reviewQueryService = reviewQueryService;
        this.reviewCommandService = reviewCommandService;
    }


    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource) {
        var command = CreateReviewFromResourceAssembler.toCommandFrom(resource);
        var reviewId = reviewCommandService.handle(command);

        var query = new GetReviewByIdQuery(reviewId);
        var reviewByQuery = reviewQueryService.handle(query);

        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(reviewByQuery.get());
        return new ResponseEntity<>(reviewResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Stream<ReviewResource>> getReviews() {
        var reviews = reviewQueryService.handle();
        if (reviews.isEmpty()) return ResponseEntity.badRequest().build();
        var reviewsResource = reviews.stream().map(review -> {
            return ReviewResourceFromEntityAssembler.toResourceFromEntity(review);
        });
        return ResponseEntity.ok(reviewsResource);
    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<Stream<ReviewResource>> getReviewsByTechnicalId(@PathVariable Long technicalId) {
        var reviews = reviewQueryService.handle(new GetReviewByTechnicalIdQuery(technicalId));
        if (reviews.isEmpty()) return ResponseEntity.badRequest().build();
        var reviewsResource = reviews.stream().map(review -> {
            return ReviewResourceFromEntityAssembler.toResourceFromEntity(review);
        });
        return ResponseEntity.ok(reviewsResource);
    }
}
