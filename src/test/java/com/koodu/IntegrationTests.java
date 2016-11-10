package com.koodu;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/api/v1/timestamp";

    @Test
    public void testGetTimestampWithoutParam() {

        ResponseEntity<HashMap> responseEntity = restTemplate.getForEntity(BASE_URL, HashMap.class);
        HashMap<String, String> response = responseEntity.getBody();
        MediaType mediaType = responseEntity.getHeaders().getContentType();

        assertEquals("Status code mismatch", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Content-Type mismatch", MediaType.APPLICATION_JSON_UTF8, mediaType);
        assertNotNull("unix cannot be null", response.get("unix"));
        assertNotNull("natural cannot be null", response.get("natural"));
    }

    @Test
    public void testGetTimestampGivenUnixTimestamp() {
        String unixTimestamp = "1453805026";
        String expectedNatural = "January 26, 2016";

        ResponseEntity<HashMap> responseEntity = restTemplate.getForEntity(BASE_URL + "/" + unixTimestamp, HashMap.class);
        HashMap<String, String> response = responseEntity.getBody();
        MediaType mediaType = responseEntity.getHeaders().getContentType();

        assertEquals("Status code mismatch", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Content-Type mismatch", MediaType.APPLICATION_JSON_UTF8, mediaType);
        assertEquals("unix mixmatch", response.get("unix"), unixTimestamp);
        assertEquals("natural cannot be null", response.get("natural"), expectedNatural);
    }

    @Test
    public void testGetTimestampGivenNaturalDate() {
        String expectedUnixTimestamp = "1453805026";
        String natural = "January 26, 2016";

        ResponseEntity<HashMap> responseEntity = restTemplate.getForEntity(BASE_URL + "/" + natural, HashMap.class);
        HashMap<String, String> response = responseEntity.getBody();
        MediaType mediaType = responseEntity.getHeaders().getContentType();

        assertEquals("Status code mismatch", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Content-Type mismatch", MediaType.APPLICATION_JSON_UTF8, mediaType);
        assertEquals("unix mixmatch", response.get("unix"), expectedUnixTimestamp);
        assertEquals("natural cannot be null", response.get("natural"), natural);
    }

    @Test
    public void contextLoads() {
    }

}
