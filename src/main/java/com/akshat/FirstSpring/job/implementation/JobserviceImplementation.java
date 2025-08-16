package com.akshat.FirstSpring.job.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.akshat.FirstSpring.job.Job;
import com.akshat.FirstSpring.job.JobRepository;
import com.akshat.FirstSpring.job.JobService;

@Service
public class JobserviceImplementation implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    // private Long nextId = 1L;

    JobRepository jobRepository;

    public JobserviceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;

    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public Boolean updateJob(Long id, Job updatedjob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(updatedjob.getTitle());
            job.setDescription(updatedjob.getDescription());
            job.setMaxSalary(updatedjob.getMaxSalary());
            job.setMinSalary(updatedjob.getMinSalary());
            job.setLocation(updatedjob.getLocation());
            jobRepository.save(job);
            return true;
        }

        return false;
    }

}
