/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.blazartech.dynamicbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author scott
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.blazartech"})
public class DynamicBatch {

    public static final String JOB_NAME = "dynamicBatchDemo";
    
    public static void main(String[] args) {
        SpringApplication.run(DynamicBatch.class, args);
    }
}
