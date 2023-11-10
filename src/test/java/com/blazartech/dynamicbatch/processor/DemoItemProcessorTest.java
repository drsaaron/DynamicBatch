/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.dynamicbatch.processor;

import com.blazartech.dynamicbatch.data.DemoItem;
import com.blazartech.dynamicbatch.data.DemoItemOutput;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@Slf4j
public class DemoItemProcessorTest {
    
    @TestConfiguration
    public static class DemoItemProcessorTestConfiguration {
        
        @Bean
        public DemoItemProcessor instance() {
            return new DemoItemProcessor();
        }
    }
    
    @Autowired
    private DemoItemProcessor instance;
    
    public DemoItemProcessorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of process method, of class DemoItemProcessor.
     */
    @Test
    public void testProcess() throws Exception {
        log.info("process");
        
        DemoItem item = new DemoItem(500, "blah");
        
        int multiplier = 200;
        instance.setMultiplier(multiplier);
        DemoItemOutput result = instance.process(item);
        
        assertEquals(item.getId(), result.getId());
        assertEquals(item, result.getItem());
        assertEquals(multiplier * item.getId(), result.getAge());
    }
    
}
