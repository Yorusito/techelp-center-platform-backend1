package com.logicverse.techelp.platform.community.application.internal.queryservices;

import com.logicverse.techelp.platform.repairing.infrastructure.persistence.jpa.repositories.TechnicalRepository;
import com.logicverse.techelp.platform.community.domain.model.entities.Review;
import com.logicverse.techelp.platform.community.domain.model.queries.GetReviewByIdQuery;
import com.logicverse.techelp.platform.community.domain.model.queries.GetReviewByTechnicalIdQuery;
import com.logicverse.techelp.platform.community.domain.services.ReviewQueryService;
import com.logicverse.techelp.platform.community.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private ReviewRepository reviewRepository;
    private TechnicalRepository technicalRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository, TechnicalRepository technicalRepository) {

        this.reviewRepository = reviewRepository;
        this.technicalRepository = technicalRepository;
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query){
        return reviewRepository.findById(query.reviewId());
    }

    @Override
    public List<Review> handle(){return reviewRepository.findAll();
    }

    @Override
    public List<Review> handle(GetReviewByTechnicalIdQuery query) {
        var technical = technicalRepository.findById(query.technicianId());
        if (technical.isEmpty()) throw new IllegalArgumentException("Technical with" + query.technicianId() + "does not exist");
        var tasksByTechnicalId = reviewRepository.findBytechnical(technical.get());
        return tasksByTechnicalId;
    }


}
