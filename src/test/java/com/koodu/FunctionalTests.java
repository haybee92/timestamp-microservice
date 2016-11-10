/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koodu;

import com.koodu.service.TimeService;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Abiola.Adebanjo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionalTests {

    @MockBean
    private TimeService mockTimeService;
    @Autowired
    TimeService timeService;

    HashMap<String, String> expectedTimestamp = new HashMap<>(2);

    @Before
    public void setUp() {
        expectedTimestamp.put("unix", TestConstants.UNIX);
        expectedTimestamp.put("natural", TestConstants.NATURAL);

        given(this.mockTimeService.getTimeStamp(Matchers.isNull(String.class))).willReturn(expectedTimestamp);
    }

    @Test
    public void testGetTimestampWhenParamIsNull() {
        HashMap<String, String> response = timeService.getTimeStamp(null);

        assertEquals("unix mixmatch", response.get("unix"), TestConstants.UNIX);
        assertEquals("natural cannot be null", response.get("natural"), TestConstants.NATURAL);
    }
}
