package com.raihan.Jobs.Job.external;






public class Company {
    
    private Long id;
    private String name;
    private String description;

    // private List<Review> reviews;

    // write all the getter and setter methods
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

