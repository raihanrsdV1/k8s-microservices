package com.raihan.Jobs.Job;
import java.util.List;

import org.springframework.stereotype.Service;

import com.raihan.Jobs.Job.dto.JobDTO;

@Service
public interface JobService {
    List<JobDTO> getJobs();
    void addJob(Job job);
    JobDTO getJobById(Long id);
    boolean deleteJob(Job job);
    boolean updateJob(Long id, Job job);
}


