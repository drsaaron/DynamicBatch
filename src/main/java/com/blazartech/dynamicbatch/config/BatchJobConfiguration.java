/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.dynamicbatch.config;

import com.blazartech.batch.IJobParametersBuilder;
import com.blazartech.dynamicbatch.DynamicBatch;
import com.blazartech.dynamicbatch.data.DemoItem;
import com.blazartech.dynamicbatch.data.DemoItemOutput;
import com.blazartech.dynamicbatch.processor.DemoItemProcessor;
import jakarta.inject.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author scott
 */
@Configuration
@ImportResource("classpath:BatchConfig.xml")
public class BatchJobConfiguration {
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private JobParametersIncrementer incrementer;
    
    @Autowired
    private Tasklet clearWorktableTasklet;
    
    @Autowired
    private ItemReader<DemoItem> demoItemReader;
    
    @Autowired
    private Provider<ItemProcessor<DemoItem, DemoItemOutput>> demoItemProcessor;
    
    @Autowired
    private ItemWriter<DemoItemOutput> demoItemOutputWriter;
    
    @Bean
    public Map<String, Job> batchJobMap() {
        return Map.of(DynamicBatch.JOB_NAME, dynamicBatchJob());
    }
    
    @Bean
    public Map<String, IJobParametersBuilder> batchJobParameterBuilderMap() {
        return new HashMap<>();
    }
    
    @Bean
    public Step clearWorktableStep() {
        return new StepBuilder("clearWorktableStep", jobRepository)
                .tasklet(clearWorktableTasklet, transactionManager)
                .build();
    }
    
    private ItemProcessor<DemoItem, DemoItemOutput> createDynamicProcessor(int multiplier) {
        ItemProcessor<DemoItem, DemoItemOutput> processor = demoItemProcessor.get();
        ((DemoItemProcessor) processor).setMultiplier(multiplier);
        return processor;
    }
    
    public List<ItemProcessor<DemoItem, DemoItemOutput>> dynamicProcessors() {
        return List.of(
                createDynamicProcessor(10),
                createDynamicProcessor(50),
                createDynamicProcessor(1000)
        );
    }
    
    public Step buildDynamicStep(ItemProcessor<DemoItem, DemoItemOutput> processor) {
        int multiplier = ((DemoItemProcessor) processor).getMultiplier();
        return new StepBuilder("processItemsDynamic_" + multiplier, jobRepository)
                .<DemoItem, DemoItemOutput> chunk(15, transactionManager)
                .reader(demoItemReader)
                .processor(processor)
                .writer(demoItemOutputWriter)
                .build();
    }
    
    @Bean
    public Job dynamicBatchJob() {
        SimpleJobBuilder jobBuilder = new JobBuilder(DynamicBatch.JOB_NAME, jobRepository)
                .incrementer(incrementer)
                .start(clearWorktableStep());
                
        dynamicProcessors().forEach(p -> jobBuilder.next(buildDynamicStep(p)));
        
        return jobBuilder.build();
    }
}
