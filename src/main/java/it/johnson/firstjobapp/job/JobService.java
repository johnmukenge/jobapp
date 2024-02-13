package it.johnson.firstjobapp.job;

import java.util.List;


public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJobBy(Long id, Job updatedJob);
}
