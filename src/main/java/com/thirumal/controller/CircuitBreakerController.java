/**
 * 
 */
package com.thirumal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

/**
 * @author Thirumal
 */
@RestController
@RequestMapping("circuit-breaker")
public class CircuitBreakerController {

	private CircuitBreakerRegistry circuitBreakerRegistry;
	
	public CircuitBreakerController(CircuitBreakerRegistry circuitBreakerRegistry) {
		super();
		this.circuitBreakerRegistry = circuitBreakerRegistry;
	}

	@GetMapping("")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "example", fallbackMethod = "fallback")
    public ResponseEntity<String> simulateDelay(@RequestParam int delay) throws InterruptedException {
		Thread.sleep(delay * 1000L);
        
        return ResponseEntity.ok("Response after " + delay + " seconds");
    }

    public ResponseEntity<String> fallback(int delay, Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallback response");
    }
    
    @GetMapping("/debug-circuit-breaker")
    public String debugCircuitBreaker() {
    	CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("example");
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();

        return "State: " + circuitBreaker.getState() +
               ", Failure Rate: " + metrics.getFailureRate() +
               ", Buffered Calls: " + metrics.getNumberOfBufferedCalls() +
               ", Failed Calls: " + metrics.getNumberOfFailedCalls();
    }
    
}
