package com.logicverse.techelp.platform.community.domain.model.entities;
import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.shared.domain.model.entities.AuditableModel;
import com.logicverse.techelp.platform.community.domain.model.valueobjects.Comment;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class Review extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Getter
    private Comment comment;


    @Getter
    private String assessment;

    @Getter
    @ManyToOne
    @JoinColumn(name = "technical_id")
    private Technical technical;

    public Review(String comment, String assessment, Technical technical){
        this.comment = new Comment(comment);
        this.assessment = assessment;
        this.technical = technical;
    }

    public Review(){}

    public String getComment(){
        return this.comment.getComment();
    }


}
