/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.dynamicbatch.reader;

import com.blazartech.dynamicbatch.data.DemoItem;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@StepScope
public class DemoItemReader implements ItemReader<DemoItem> {

    private static final DemoItem[] DATA = {
        new DemoItem(1, "Scott"),
        new DemoItem(2, "Eddie"),
        new DemoItem(3, "George"),
        new DemoItem(4, "Eric"),
        new DemoItem(5, "Jimmy")
    };
    
    private int index = 0;
    
    @Override
    public DemoItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (index == DATA.length) {
            return null;
        } else {
            return DATA[index++];
        }
    }
    
}
