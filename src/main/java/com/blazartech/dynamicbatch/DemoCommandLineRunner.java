/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.dynamicbatch;

import com.blazartech.batch.IJobManager;
import com.blazartech.batch.JobStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@Slf4j
public class DemoCommandLineRunner implements CommandLineRunner {

    @Autowired
    private IJobManager jobManager;

    @Override        
    public void run(String... args) throws Exception {
        log.info("starting job");

        JobStatus status = jobManager.runJob(DynamicBatch.JOB_NAME);
        
        log.info("job completed {}", status);
    }

}
