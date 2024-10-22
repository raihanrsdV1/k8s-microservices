package com.raihan.Reviewms.Review.dto;

import com.raihan.Reviewms.Review.Review;
import com.raihan.Reviewms.Review.external.Company;

public class ReviewWithCompany {
    Review review;
    Company company;

    public Review getReview() {
        return review;
    }

    public Company getCompany() {
        return company;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
