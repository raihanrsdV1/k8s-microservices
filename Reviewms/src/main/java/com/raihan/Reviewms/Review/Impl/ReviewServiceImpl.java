package com.raihan.Reviewms.Review.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.raihan.Reviewms.Review.Review;
import com.raihan.Reviewms.Review.ReviewRepository;
import com.raihan.Reviewms.Review.ReviewService;
import com.raihan.Reviewms.Review.dto.ReviewWithCompany;
import com.raihan.Reviewms.Review.external.Company;



@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        
    }



    @Override
    public List<Review> getReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews;
    }

    public ReviewWithCompany getReviewWithCompany(Review review){
        // RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://COMPANYMS:8081/companies/"+review.getCompanyId(), Company.class);
        ReviewWithCompany reviewWithCompany = new ReviewWithCompany();
        reviewWithCompany.setReview(review);
        reviewWithCompany.setCompany(company);
        return reviewWithCompany;
    }

    @Override
    public void addReview(Long companyId, Review review) {

        if(companyId != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
        }
        else{
            throw new RuntimeException("Company not found");
        }
    }

    @Override
    public Review getReviewById(Long id) {
        Optional<Review> reviews = reviewRepository.findById(id);
        if(reviews.isPresent()){
            return reviews.get();
        }
        return null;
    }

    @Override
    public boolean deleteReview(Review review) {
        try {
            reviewRepository.delete(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateReview(Long id, Review review) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isPresent()){
            Review reviewToUpdate = reviewOptional.get();
            reviewToUpdate.setRating(review.getRating());
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewRepository.save(reviewToUpdate);
            return true;
        }
        return false;
    }
}
