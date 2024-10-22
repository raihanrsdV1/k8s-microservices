package com.raihan.Reviewms.Review;

import java.util.List;

import com.raihan.Reviewms.Review.dto.ReviewWithCompany;

public interface ReviewService {
    public List<Review> getReviews(Long companyId);
    public void addReview(Long companyId, Review review);
    public Review getReviewById(Long id);
    public boolean deleteReview(Review review);
    public boolean updateReview(Long id, Review review);
}
