package com.logicverse.techelp.platform.community.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable

public record Comment(String comment) {
    public Comment(){this(null);}

    public Comment {
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Comment cannot be null or blank");
        }
    }

    public String getComment() {
        return String.format("%s", comment);
    }
}
