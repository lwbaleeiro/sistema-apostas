## Código de Cores:

- 🔵 Azul: Frontend (Web, Mobile, Admin)
- 🟣 Roxo: API Gateway
- 🟢 Verde: Serviços Java (User, Pool, Settlement, Payment)
- 🔴 Vermelho: Rust (Bet Engine)
- 🟠 Laranja: Node.js (Integration, Real-time)
- 🔷 Ciano: Python (Analytics)
- 🔷 Azul Escuro: Databases
- ⚫ Cinza: Kafka (Message Queue)
- 🟣 Roxo Escuro: Serviços Externos

## Tipos de Comunicação:

- Síncrona (gRPC): Bet Engine ↔ User Service, Settlement ↔ Pool Service
- Assíncrona (Kafka): Todos serviços publicam/consomem eventos
- Cache (Redis): Sessions, odds, leaderboards, rate limiting
- Transacional (PostgreSQL): Dados críticos
- Analytics (ClickHouse): Time-series e métricas
- Flexível (MongoDB): Configurações dinâmicas

## Principais Fluxos:

- Frontend → Gateway → Microserviços (HTTPS/gRPC)
- Microserviços → Kafka → Outros Microserviços (Event-driven)
- Bet Engine valida saldo com User Service (gRPC síncrono)
- Integration Service coleta eventos externos → Kafka
- Real-time Service distribui via WebSocket

----

### Código do Diagrama:

```mermaid
graph TB
    subgraph "Frontend Layer"
        WEB[Web App<br/>Next.js<br/>HTTPS/WSS]
        MOBILE[Mobile Apps<br/>React Native<br/>HTTPS/WSS]
        ADMIN[Admin Dashboard<br/>React<br/>HTTPS]
    end

    subgraph "API Gateway Layer"
        GATEWAY[API Gateway<br/>Kong/Traefik<br/>Rate Limiting, Auth, Routing]
    end

    subgraph "Microservices - Kubernetes Cluster"
        subgraph "Core Services"
            USER[User Service<br/>Java/Spring Boot<br/>REST + gRPC<br/>Auth, Profile, KYC, Wallet]
            BET[Bet Engine<br/>Rust<br/>gRPC<br/>Matching, Validation]
            POOL[Pool Service<br/>Java/Spring Boot<br/>REST + gRPC<br/>Pools, Leaderboard]
            SETTLE[Settlement Service<br/>Java/Spring Boot<br/>gRPC<br/>Liquidation, Payouts]
        end
        
        subgraph "Integration & Real-time"
            INTEGRATION[Integration Service<br/>Node.js/TypeScript<br/>REST + WebHooks<br/>Twitch, Kick, YouTube]
            REALTIME[Real-time Service<br/>Node.js/Socket.io<br/>WebSocket<br/>Notifications, Chat]
            PAYMENT[Payment Gateway<br/>Java/Spring Boot<br/>REST<br/>Deposits, Withdrawals]
            ANALYTICS[Analytics Service<br/>Python<br/>REST<br/>Metrics, ML, Fraud]
        end
    end

    subgraph "Data Layer"
        POSTGRES[(PostgreSQL<br/>Primary + Replicas<br/>Transactional Data)]
        REDIS[(Redis Cluster<br/>Cache + Pub/Sub<br/>Sessions, Rate Limit)]
        KAFKA[Apache Kafka<br/>Event Streaming<br/>Event Sourcing]
        MONGO[(MongoDB<br/>Document Store<br/>Dynamic Configs)]
        CLICKHOUSE[(ClickHouse<br/>Analytics DB<br/>Time-series Data)]
    end

    subgraph "External Services"
        TWITCH[Twitch API]
        KICK[Kick API]
        PAYMENTGW[Payment Gateways<br/>Stripe, MercadoPago]
        KYC[KYC Services<br/>Onfido, Jumio]
    end

    %% Frontend to Gateway
    WEB -->|HTTPS/WSS| GATEWAY
    MOBILE -->|HTTPS/WSS| GATEWAY
    ADMIN -->|HTTPS| GATEWAY

    %% Gateway to Services
    GATEWAY -->|REST/gRPC| USER
    GATEWAY -->|REST/gRPC| POOL
    GATEWAY -->|REST| PAYMENT
    GATEWAY -->|WebSocket| REALTIME

    %% Service to Service Communication (gRPC)
    BET -->|gRPC<br/>Validate Balance| USER
    SETTLE -->|gRPC<br/>Calculate Payouts| POOL
    POOL -->|gRPC<br/>Check User| USER
    PAYMENT -->|gRPC<br/>Update Wallet| USER

    %% Services to Kafka (Async Events)
    USER -->|Publish Events| KAFKA
    BET -->|Publish Events| KAFKA
    POOL -->|Publish Events| KAFKA
    SETTLE -->|Consume & Publish| KAFKA
    INTEGRATION -->|Publish Events| KAFKA
    PAYMENT -->|Publish Events| KAFKA
    
    KAFKA -->|Consume Events| SETTLE
    KAFKA -->|Consume Events| REALTIME
    KAFKA -->|Consume Events| ANALYTICS

    %% Services to Redis
    USER -->|Cache & Sessions| REDIS
    BET -->|Cache Odds| REDIS
    POOL -->|Cache Leaderboard| REDIS
    REALTIME -->|Pub/Sub| REDIS
    GATEWAY -->|Rate Limiting| REDIS

    %% Services to PostgreSQL
    USER -->|SQL| POSTGRES
    BET -->|SQL| POSTGRES
    POOL -->|SQL| POSTGRES
    SETTLE -->|SQL| POSTGRES
    PAYMENT -->|SQL| POSTGRES

    %% Services to MongoDB
    POOL -->|NoSQL| MONGO
    INTEGRATION -->|NoSQL| MONGO

    %% Services to ClickHouse
    ANALYTICS -->|SQL| CLICKHOUSE
    KAFKA -->|Stream Data| CLICKHOUSE

    %% External Integrations
    INTEGRATION -->|HTTPS<br/>WebHooks| TWITCH
    INTEGRATION -->|HTTPS<br/>Polling| KICK
    PAYMENT -->|HTTPS API| PAYMENTGW
    USER -->|HTTPS API| KYC

    %% Styling
    classDef frontend fill:#3b82f6,stroke:#1e40af,color:#fff
    classDef gateway fill:#8b5cf6,stroke:#6d28d9,color:#fff
    classDef javaService fill:#10b981,stroke:#059669,color:#fff
    classDef rustService fill:#ef4444,stroke:#dc2626,color:#fff
    classDef nodeService fill:#f97316,stroke:#ea580c,color:#fff
    classDef pythonService fill:#06b6d4,stroke:#0891b2,color:#fff
    classDef database fill:#1e40af,stroke:#1e3a8a,color:#fff
    classDef messageQueue fill:#374151,stroke:#1f2937,color:#fff
    classDef external fill:#7c3aed,stroke:#6d28d9,color:#fff

    class WEB,MOBILE,ADMIN frontend
    class GATEWAY gateway
    class USER,POOL,SETTLE,PAYMENT javaService
    class BET rustService
    class INTEGRATION,REALTIME nodeService
    class ANALYTICS pythonService
    class POSTGRES,REDIS,MONGO,CLICKHOUSE database
    class KAFKA messageQueue
    class TWITCH,KICK,PAYMENTGW,KYC external
    ```