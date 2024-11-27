/**
 * 
 */
package com.thirumal.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Thirumal
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CircuitBreakerIntegrationTest {
	
	
	 
	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	void testCircuitBreaker() {
		// Send requests that exceed the failure threshold to trigger the circuit
		// breaker
		for (int i = 1; i <= 6; i++) {
			ResponseEntity<String> response = restTemplate.getForEntity("/circuit-breaker?delay=10", String.class);
			System.out.println(
					"Resilience4J Iteration " + i + ": " + response.getStatusCode() + " - " + response.getBody());
			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		}

		// After the circuit breaker is open, send requests with normal delays
		for (int i = 7; i <= 10; i++) {
			int delay = i % 2 == 0 ? 1 : 2;
			ResponseEntity<String> response = restTemplate.getForEntity("/circuit-breaker?delay=" + delay, String.class);
			System.out.println(
					"Resilience4J Iteration " + i + ": " + response.getStatusCode() + " - " + response.getBody());
			assertEquals("Response after " + delay + " seconds", response.getBody());
		}
	}
}
