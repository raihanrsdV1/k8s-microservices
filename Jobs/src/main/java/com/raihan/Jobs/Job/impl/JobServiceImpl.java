package com.raihan.Jobs.Job.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.raihan.Jobs.Job.Job;
import com.raihan.Jobs.Job.JobRepository;
import com.raihan.Jobs.Job.JobService;
import com.raihan.Jobs.Job.client.CompanyClient;
import com.raihan.Jobs.Job.client.ReviewClient;
import com.raihan.Jobs.Job.dto.JobDTO;
import com.raihan.Jobs.Job.external.Company;
import com.raihan.Jobs.Job.external.Review;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    // public List<Job> jobs = new ArrayList<Job>();
    private final JobRepository jobRepository;
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;
    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient CompanyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = CompanyClient;
        this.reviewClient = reviewClient;
    }


    public Long nextId = 1L;
    @Override
    // @CircuitBreaker(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    // @Retry(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> getJobs() {
        
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::getJobDTO).collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Company Service is down");
        return list;
    }

    public JobDTO getJobDTO(Job job){
        // RestTemplate restTemplate = new RestTemplate();
        Company company = companyClient.getCompany(job.getCompanyId());
        // System.out.println("Company: " + company.getName());
       
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setJob(job);
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(reviews);
        return jobWithCompanyDTO;
    }

    @Override
    public void addJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        return getJobDTO(jobRepository.findById(id).orElse(null));
    }

    @Override
    public boolean deleteJob(Job job) {
        try{
            jobRepository.delete(job);
            return true;
        }catch(Exception e){
            return false;
        }
        
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job jobToUpdate = jobOptional.get();
            jobToUpdate.setName(job.getName());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setLocation(job.getLocation());
            jobRepository.save(jobToUpdate);
            return true;
        }
        return false;
    }


}   
