package com.epam.rd.tasks.zoo;

import java.time.LocalDateTime;

public class VisitorReview{
    private String visitorName;
    private String review;
    private LocalDateTime visitTime;
    private Integer zooId;

    public VisitorReview(String visitorName, String review, LocalDateTime visitTime) {
        this.visitorName = visitorName;
        this.review = review;
        this.visitTime = visitTime;
    }
}
