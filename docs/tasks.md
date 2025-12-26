# 📘 Épicos — Plataforma de Apostas e Pools

Documento interativo de arquitetura e backlog técnico.  
Use os épicos colapsáveis e marque critérios conforme a evolução.

---

## 📊 Resumo Geral

| Métrica | Valor |
|------|------|
| Épicos | 12 |
| Stories | 48 |
| Linguagens | Java · Node.js · Rust |

---

<details>
<summary><strong>🌐 EPIC-1: Infraestrutura Base e DevOps</strong><br/>
<em>Cloud, CI/CD, observabilidade e service mesh</em></summary>

### Story 1.1 — Kubernetes Cluster
- [ ] Multi-AZ (3+)
- [ ] HPA
- [ ] VPA (opcional)
- [ ] Network Policies
- [ ] Ingress Controller
- [ ] Cert-manager

**Arquitetura**
> Kubernetes com namespaces isolados + Istio

---

### Story 1.2 — CI/CD
- [ ] Java
- [ ] Node.js
- [ ] Rust
- [ ] Testes em PR
- [ ] Docker otimizado
- [ ] Deploy staging
- [ ] Deploy prod com aprovação
- [ ] Rollback automático

---

### Story 1.3 — Observabilidade
- [ ] Prometheus
- [ ] Grafana
- [ ] Alertas
- [ ] Tracing distribuído
- [ ] Logs centralizados
- [ ] SLIs/SLOs
- [ ] Métricas de negócio

---

### Story 1.4 — Service Mesh
- [ ] Istio / Linkerd
- [ ] mTLS
- [ ] Circuit breakers
- [ ] Rate limiting
- [ ] Retry/Timeout
- [ ] Canary deploy

</details>

---

<details>
<summary><strong>🗄️ EPIC-2: Camada de Dados</strong><br/>
<em>Persistência, cache e eventos</em></summary>

### Story 2.1 — PostgreSQL
- [ ] Replicação
- [ ] Failover
- [ ] PgBouncer
- [ ] Backup + PITR
- [ ] Particionamento
- [ ] Índices

---

### Story 2.2 — Redis
- [ ] Cluster
- [ ] Replicação
- [ ] Persistência
- [ ] Cache sessões
- [ ] Cache odds
- [ ] Rate limiting
- [ ] Pub/Sub

---

### Story 2.3 — Kafka
- [ ] 3+ brokers
- [ ] KRaft/Zookeeper
- [ ] Topics principais
- [ ] Replication factor 3
- [ ] Retention
- [ ] Schema Registry
- [ ] Kafka Connect

---

### Story 2.4 — Analytics DB
- [ ] Timescale / ClickHouse
- [ ] Aggregates
- [ ] Retenção
- [ ] BI integration

---

### Story 2.5 — MongoDB
- [ ] Replica set
- [ ] Schema validation
- [ ] Indexes
- [ ] Change streams

</details>

---

<details>
<summary><strong>👤 EPIC-3: User Service (Java)</strong></summary>

### Story 3.1 — Auth
- [ ] JWT
- [ ] OAuth2
- [ ] 2FA
- [ ] Rate limit
- [ ] RBAC

---

### Story 3.2 — Perfil
- [ ] CRUD perfil
- [ ] Avatar upload
- [ ] Preferências
- [ ] Estatísticas
- [ ] Privacidade

---

### Story 3.3 — KYC
- [ ] Upload docs
- [ ] Integração externa
- [ ] Status workflow
- [ ] Limites por status

---

### Story 3.4 — Wallet
- [ ] Ledger
- [ ] Atomicidade
- [ ] Multi-moeda
- [ ] Freeze saldo
- [ ] Auditoria

</details>

---

<details>
<summary><strong>⚡ EPIC-4: Bet Engine (Rust)</strong></summary>

### Story 4.1 — Matching Engine
- [ ] p99 < 5ms
- [ ] Order books
- [ ] Price-time priority
- [ ] Partial fills
- [ ] Lock-free
- [ ] Snapshots
- [ ] Recovery

---

### Story 4.2 — Validações
- [ ] Saldo
- [ ] Mercado aberto
- [ ] Odds válidas
- [ ] Idempotência
- [ ] Anti-fraude

---

### Story 4.3 — gRPC
- [ ] PlaceBet
- [ ] CancelBet
- [ ] Streaming odds
- [ ] Load balancing

---

### Story 4.4 — Persistência
- [ ] WAL
- [ ] Snapshots
- [ ] Replay
- [ ] DR

</details>

---

<details>
<summary><strong>👥 EPIC-5: Pool Service</strong></summary>

### Story 5.1 — Criação
- [ ] Tipos de pool
- [ ] Regras customizadas
- [ ] Privado/público
- [ ] Fee criador

---

### Story 5.2 — Participação
- [ ] Join/Leave
- [ ] Leaderboard
- [ ] Picks
- [ ] Notificações

---

### Story 5.3 — Prêmios
- [ ] Winner-takes-all
- [ ] Proportional
- [ ] Bracket
- [ ] Fees corretas

---

### Story 5.4 — Streaming
- [ ] Twitch
- [ ] Kick
- [ ] Eventos em tempo real

</details>

---

<details>
<summary><strong>📈 EPIC-6: Settlement</strong></summary>

### Story 6.1 — Resultados
- [ ] Múltiplas fontes
- [ ] Override manual
- [ ] Versionamento

---

### Story 6.2 — Settlement
- [ ] Win/Loss/Void
- [ ] Atomicidade
- [ ] Idempotência
- [ ] Batch

---

### Story 6.3 — Pool Settlement
- [ ] Ranking final
- [ ] Distribuição
- [ ] Saga

---

### Story 6.4 — Disputas
- [ ] Workflow
- [ ] Evidências
- [ ] Re-settlement

</details>

---

<details>
<summary><strong>🔌 EPIC-7: Integration Service</strong></summary>

### Story 7.1 — Twitch
- [ ] OAuth
- [ ] EventSub
- [ ] Chat events

---

### Story 7.2 — Kick
- [ ] Streams
- [ ] Polling

---

### Story 7.3 — YouTube
- [ ] Live streams
- [ ] Quotas

---

### Story 7.4 — Normalização
- [ ] Schema padrão
- [ ] Deduplicação
- [ ] Kafka publish

</details>

---

<details>
<summary><strong>🔔 EPIC-8: Real-time & Notifications</strong></summary>

### Story 8.1 — WebSockets
- [ ] 100k conexões
- [ ] JWT auth
- [ ] Rooms
- [ ] Redis Pub/Sub

---

### Story 8.2 — Odds Real-time
- [ ] Push < 100ms
- [ ] Throttling
- [ ] Batching

---

### Story 8.3 — Notifications
- [ ] Push
- [ ] Email
- [ ] SMS
- [ ] Preferências

---

### Story 8.4 — Chat
- [ ] Rooms
- [ ] Moderação
- [ ] Histórico

</details>

---

<details>
<summary><strong>💳 EPIC-9: Payment Gateway</strong></summary>

### Story 9.1 — Deposit
- [ ] Cartão
- [ ] PIX
- [ ] Crypto
- [ ] Webhooks

---

### Story 9.2 — Withdrawal
- [ ] KYC
- [ ] Approval manual
- [ ] Queue

---

### Story 9.3 — Reconciliação
- [ ] Job diário
- [ ] Dashboard

---

### Story 9.4 — Fraude
- [ ] Rules
- [ ] ML scoring
- [ ] Blacklists

</details>

---

<details>
<summary><strong>📊 EPIC-10: Analytics</strong></summary>

### Story 10.1 — Pipeline
- [ ] ETL
- [ ] Kafka Connect
- [ ] Aggregations

---

### Story 10.2 — Dashboards
- [ ] KPIs
- [ ] Revenue
- [ ] Export

---

### Story 10.3 — Behavior
- [ ] Funnels
- [ ] Cohorts
- [ ] A/B testing

---

### Story 10.4 — Fraud Analytics
- [ ] ML models
- [ ] Graph analysis
- [ ] Real-time scoring

</details>

---

<details>
<summary><strong>🛠️ EPIC-11: Admin Dashboard</strong></summary>

### Story 11.1 — Users
- [ ] Ban/unban
- [ ] Ajuste saldo
- [ ] Audit log

---

### Story 11.2 — Eventos
- [ ] CRUD eventos
- [ ] Odds
- [ ] Cancelamento

---

### Story 11.3 — Financeiro
- [ ] Withdrawals
- [ ] Refunds
- [ ] Relatórios

---

### Story 11.4 — Moderação
- [ ] Reports
- [ ] Chat moderation
- [ ] Escalação

</details>

---

<details>
<summary><strong>📱 EPIC-12: Frontend Web & Mobile</strong></summary>

### Story 12.1 — Web
- [ ] Next.js
- [ ] SSR
- [ ] WebSockets
- [ ] PWA

---

### Story 12.2 — Mobile
- [ ] React Native
- [ ] Biometrics
- [ ] Push

---

### Story 12.3 — Design System
- [ ] Componentes
- [ ] Tokens
- [ ] Dark mode

---

### Story 12.4 — Performance
- [ ] Code splitting
- [ ] Bundle < 200KB
- [ ] Lighthouse > 90

</details>

---