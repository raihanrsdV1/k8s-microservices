package com.raihan.Jobs.Job;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
// @Table(name = "job_table")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;

    public Job() {
    }

    public Job(Long id, String name, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    // Getters

    
    public Long getCompanyId() {
        return companyId;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public String getLocation() {
        return location;
    }

    // Setters

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

