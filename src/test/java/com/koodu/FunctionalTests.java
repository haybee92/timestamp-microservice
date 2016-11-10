package com.koodu;

import com.koodu.exception.TimeException;
import com.koodu.service.TimeService;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author Abiola.Adebanjo
 */
public class FunctionalTests {

    static TimeService mockTimeService;
    static TimeService timeService;

    static HashMap<String, String> expectedTimestamp = new HashMap<>(2);
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUp() throws TimeException {
        mockTimeService = Mockito.mock(TimeService.class);
        timeService = new TimeService();
        expectedTimestamp.put("unix", TestConstants.UNIX_1);
        expectedTimestamp.put("natural", TestConstants.NATURAL_1);

        when(mockTimeService.getTimeStamp(Matchers.isNull(String.class))).thenReturn(expectedTimestamp);
    }

    @Test
    public void testGetTimestampWhenParamIsNull() throws TimeException {
        HashMap<String, String> response = mockTimeService.getTimeStamp(null);

        assertEquals("unix mixmatch", TestConstants.UNIX_1, response.get("unix"));
        assertEquals("natural mismatch", TestConstants.NATURAL_1, response.get("natural"));
    }

    @Test
    public void testGetTimestampWhenParamIsUnixTimestamp() throws TimeException {
        HashMap<String, String> response = timeService.getTimeStamp(TestConstants.UNIX_2);

        assertEquals("unix mixmatch", TestConstants.UNIX_2, response.get("unix"));
        assertEquals("natural mismatch", TestConstants.NATURAL_2, response.get("natural"));
    }

    @Test
    public void testGetTimestampWhenParamIsNaturalDate() throws TimeException {
        HashMap<String, String> response = timeService.getTimeStamp(TestConstants.NATURAL_3);

        assertEquals("unix mixmatch", TestConstants.UNIX_3, response.get("unix"));
        assertEquals("natural mismatch", TestConstants.NATURAL_3, response.get("natural"));
    }

    @Test
    public void testGetTimestampWhenParamIsInvalid() throws TimeException {
        thrown.expect(TimeException.class);
        thrown.expectMessage("Parameter not in correct format");
        timeService.getTimeStamp("invalid_string");
    }
}
