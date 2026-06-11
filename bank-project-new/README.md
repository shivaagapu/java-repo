
# Banking Microservices Project

Architecture:
- API Gateway
- Eureka Service Registry
- Config Server
- Customer Service
- Account Service
- Transaction Service
- Notification Service
- Kafka
- Resilience4j Circuit Breaker
- MySQL

Flow:
Customer -> Gateway -> Account Service -> Transaction Service
Transaction Service -> Kafka -> Notification Service
