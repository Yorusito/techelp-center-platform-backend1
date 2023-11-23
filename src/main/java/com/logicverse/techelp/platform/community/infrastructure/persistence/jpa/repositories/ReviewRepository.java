package com.logicverse.techelp.platform.community.infrastructure.persistence.jpa.repositories;
import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.community.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{


    List<Review> findBytechnical(Technical technical);
}
