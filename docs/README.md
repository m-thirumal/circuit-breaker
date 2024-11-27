# Circuit Breaker


### Purpose of the Circuit Breaker Pattern

The primary objective of the Circuit Breaker pattern is to fortify distributed systems by effectively managing and mitigating failures. It functions as an intelligent mechanism that dynamically adapts to varying conditions, proactively safeguarding the system against potential disruptions. By actively monitoring interactions with external services, APIs, or databases, the Circuit Breaker intervenes when signs of trouble arise, preventing cascading failures and preserving system integrity. Furthermore, it promotes a proactive approach to fault tolerance, mitigating risks before they escalate into critical issues.

### Key Components of the Circuit Breaker Pattern

![](../img/Architecture.webp)


 **Closed State:** In the closed state, the Circuit Breaker allows normal system operation, akin to a green light signaling smooth sailing. It diligently monitors the health of the operation it safeguards, including metrics such as failure rates, response times, and concurrency levels. This state reflects the system’s optimal functioning, where requests are processed without impedance, and the Circuit Breaker remains poised to intercept any deviations from expected behavior.

 **Open State:** When the system encounters turbulence, the Circuit Breaker springs into action, transitioning to the open state. Here, it acts as a barrier, intercepting incoming requests to prevent further damage and offering predefined fallback mechanisms to ensure continuity in the face of adversity. By isolating the faulty component or service, the Circuit Breaker minimizes the impact of failures on the overall system, thereby enhancing its resilience and fault tolerance.

 **Half-Open State:** After a predefined cooldown period, the Circuit Breaker cautiously assesses the system’s health by transitioning to the half-open state. It allows a limited number of test requests to pass through, akin to poking a sleeping dragon to gauge its response. This state serves as a testing ground for evaluating the stability of the underlying components, enabling the Circuit Breaker to make informed decisions regarding further operation. Depending on the outcome, it decides whether to fully reopen or remain on guard, thus ensuring a balanced approach to recovery.
 
 ### Dependencies
 
 ```xml
 <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>
 
 ```