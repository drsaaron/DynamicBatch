/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.dynamicbatch.processor;

import com.blazartech.dynamicbatch.data.DemoItem;
import com.blazartech.dynamicbatch.data.DemoItemOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@Scope("prototype")
@Slf4j
public class DemoItemProcessor implements ItemProcessor<DemoItem, DemoItemOutput> {

    private int multiplier;

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
    
    @Override
    public DemoItemOutput process(DemoItem item) throws Exception {
        log.info("processing item {} with multiplier {}", item, multiplier);
        
        DemoItemOutput output = new DemoItemOutput();
        output.setId(item.getId());
        output.setItem(item);
        output.setAge(multiplier * item.getId());
        
        return output;
    }
    
}
