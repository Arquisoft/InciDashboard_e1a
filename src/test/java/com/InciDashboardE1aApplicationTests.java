package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.uniovi.IncidenceTest;
import com.uniovi.OperatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        OperatorTest.class,
        IncidenceTest.class
})

public class InciDashboardE1aApplicationTests {
    @Test
    public void contextLoads() {
    }
}
