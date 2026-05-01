# Mert

## Project Overview

Mert is an event-driven microservices backend system built using Spring Boot, Java 21, Kafka, gRPC, and PostgreSQL.

It simulates a real-world distributed system architectured as <b>microservices</b> where:
- Merchant creation triggers billing account creation via gRPC (synchronous communication)
- Merchant creation also publishes events to Kafka
- Analytics service consumes Kafka events asynchronously for processing and insights

<img width="80%" height="80%" alt="image" src="https://github.com/user-attachments/assets/b462c482-c933-4dab-82d9-7258ee322a3f" />

#

## Tech Stack

| Category | Technology | Details |
| :--- | :--- | :--- |
| **Backend** | Java 21 | Programming Language |
| | Spring Boot | Web - REST API, Data JPA |
| **Communication** | gRPC | Inter-service communication |
| | Apache Kafka | Event streaming |
| **Database** | PostgreSQL | Primary Data Store |
| **Infrastructure** | Docker | Containerization |
| **Monitoring** | Kafka | Event tracking & observability |

#

## Microservices

### Flow

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/1e66de6f-944a-48b9-87db-2d424bf8214b" />


### Merchant Service
- Exposes REST APIs for merchant creation
- On merchant creation:
    - Calls Billing Service via gRPC to create a billing account
    - Produces an event to Kafka topic `merchant`
- Acts as the entry point of the system

#

### Billing Service
- Exposes gRPC service
- Receives merchant creation requests from Merchant Service
- To create and manage billing accounts for the Merchant

#

### Analytics Service
- Consumes events from Kafka topic `merchant`
- Processes merchant creation events asynchronously
- To be used for analytics, reporting, and downstream processing

#


## Summary

Mert demonstrates a production-style distributed system combining synchronous (gRPC) and asynchronous (Kafka) communication patterns.
It models a real-world micro-services architecture using docker where core business workflows and analytics pipelines are decoupled for scalability and resilience.