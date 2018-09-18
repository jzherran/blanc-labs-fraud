package com.blanc_labs.fraud;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class GeneratorControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCCGeneratedOnDayBaseCase() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/cc/generator?day=1", String.class);
        assertFalse(entity.getBody().isEmpty());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void testGetCCGeneratedOnDayError() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/cc/generator?day=0", String.class);
        assertFalse(entity.getBody().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }
}
