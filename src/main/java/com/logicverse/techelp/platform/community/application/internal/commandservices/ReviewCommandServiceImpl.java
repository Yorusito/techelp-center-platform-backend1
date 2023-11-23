package com.logicverse.techelp.platform.community.application.internal.commandservices;

import com.logicverse.techelp.platform.repairing.infrastructure.persistence.jpa.repositories.TechnicalRepository;
import com.logicverse.techelp.platform.community.domain.model.commands.CreateReviewCommand;
import com.logicverse.techelp.platform.community.domain.model.entities.Review;
import com.logicverse.techelp.platform.community.domain.services.ReviewCommandService;
import com.logicverse.techelp.platform.community.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private ReviewRepository reviewRepository;
    private TechnicalRepository technicalRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, TechnicalRepository technicalRepository){
        this.reviewRepository = reviewRepository;
        this.technicalRepository = technicalRepository;
    }

    @Override
    public  Long handle(CreateReviewCommand command){
        var technical = technicalRepository.findById(command.technicalId());
        if (technical.isEmpty()) throw new IllegalArgumentException("Provided technical does not exist");
        var newReview = new Review(command.comment(),command.assessment(), technical.get());
        this.reviewRepository.save(newReview);
        return newReview.getId();
    }
}
