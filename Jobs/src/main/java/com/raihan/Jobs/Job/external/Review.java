package com.raihan.Jobs.Job.external;




public class Review {
   
    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;

    public Review() {
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getRating() {
        return rating;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
} 
