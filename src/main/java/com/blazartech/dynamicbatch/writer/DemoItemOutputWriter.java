/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.dynamicbatch.writer;

import com.blazartech.dynamicbatch.data.DemoItemOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@StepScope
@Slf4j
public class DemoItemOutputWriter implements ItemWriter<DemoItemOutput> {

    @Override
    public void write(Chunk<? extends DemoItemOutput> chunk) throws Exception {
        chunk.getItems().forEach(i -> log.info("output item {}", i));
    }
    
}
