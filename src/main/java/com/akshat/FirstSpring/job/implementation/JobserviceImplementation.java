package com.akshat.FirstSpring.job.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.akshat.FirstSpring.job.Job;
import com.akshat.FirstSpring.job.JobService;

@Service
public class JobserviceImplementation implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    // public static void main(String[] args) {
    // List<Job> jobs = new ArrayList<>();
    // int size = jobs.size();
    // System.out.println(size);
    // System.out.println(++size);
    // }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
        System.out.println("Job posted " + nextId);

    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (Objects.equals(job.getId(), id)) {
                return job;
            }
        }
        return null;
    }

}
