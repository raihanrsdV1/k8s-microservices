package com.raihan.Jobs.Job.dto;

import java.util.List;

import com.raihan.Jobs.Job.Job;
import com.raihan.Jobs.Job.external.Company;
import com.raihan.Jobs.Job.external.Review;

public class JobDTO {
    private Job job;
    private Company company;
    private List<Review> reviews;

    // Getters
    public Job getJob() {
        return job;
    }

    public Company getCompany() {
        return company;
    }

    // Setters

    public void setJob(Job job) {
        this.job = job;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
