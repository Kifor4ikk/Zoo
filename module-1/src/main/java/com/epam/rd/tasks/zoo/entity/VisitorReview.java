package com.epam.rd.tasks.zoo.entity;

import java.time.LocalDateTime;

public class VisitorReview {

    private LocalDateTime time;
    private String visitorName;
    private String review;

    public VisitorReview(LocalDateTime time, String visitorName, String review) {
        this.time = time;
        this.visitorName = visitorName;
        this.review = review;
    }
}
