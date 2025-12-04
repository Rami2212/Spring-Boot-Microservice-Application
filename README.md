# Phone Shop - Microservices Architecture

A comprehensive e-commerce platform for phone retail built with Spring Boot microservices, Angular frontend, and cloud-native technologies. This project demonstrates modern enterprise software architecture with event-driven processing, comprehensive observability, and security best practices.

## 🎯 Overview

Phone Shop is a distributed e-commerce system demonstrating production-ready microservices patterns including service discovery, inter-service communication, event streaming, distributed tracing, and centralized logging with complete security implementation.

The platform consists of independent microservices that communicate asynchronously through Apache Kafka for event-driven workflows and synchronously through REST APIs with resilience patterns. All services are secured with OAuth 2.0 through Keycloak, monitored with Grafana, Prometheus, Loki, and Tempo for complete observability, and deployed on Kubernetes using industry best practices.

## 🛠 Technology Stack

### Backend Services
- **Framework**: Spring Boot (Java 21)
- **Testing**: JUnit, Mockito
- **Database**: MongoDB (NoSQL), MySQL with Flyway (Schema migrations)

### API & Documentation
- **API Documentation**: OpenAPI 3.0
- **API Documentation UI**: Swagger UI
- **REST API**: Spring MVC with proper HTTP standards

### Inter-Service Communication
- **Synchronous**: OpenFeign, RestTemplate
- **Resilience**: Resilience4j (Circuit Breaker)
- **Health Checks**: Spring Boot Actuator

### Event-Driven Architecture
- **Message Broker**: Apache Kafka
- **Coordination**: Apache Zookeeper
- **Schema Management**: Confluent Schema Registry
- **UI Monitoring**: Kafka UI

### Frontend
- **Framework**: Angular (TypeScript)
- **Authentication**: OIDC (OpenID Connect)
- **HTTP Client**: Angular HttpClient with Interceptors

### Security
- **Identity Provider**: Keycloak
- **Authentication Protocol**: OAuth 2.0, OpenID Connect
- **API Security**: Spring Security

### Observability Stack
- **Logs**: Grafana Loki
- **Metrics**: Prometheus
- **Distributed Tracing**: Grafana Tempo
- **Dashboards & Alerting**: Grafana
- **Service Monitoring**: Kafka UI for broker insights

### Deployment & Containerization
- **Container Build**: Cloud Native Buildpacks (CNB), Docker
- **Container Runtime**: Docker, Docker Compose
- **Orchestration**: Kubernetes
- **Local K8s**: Kind (Kubernetes in Docker)
- **CLI Tools**: kubectl