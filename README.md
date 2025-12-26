# Plataforma de Apostas e Pools

Este repositório contém o código fonte de uma plataforma de apostas. O sistema é projetado para alta disponibilidade, baixa latência e integração com plataformas de streaming.

## Visão Geral

A arquitetura utiliza comunicação assíncrona e é dividida em serviços independentes para garantir performance e escalabilidade.

## Tecnologias Principais

*   **Backend**: Java (Core), Node.js (API/Integrações), Rust (Opcional para Matching)
*   **Banco de Dados**: PostgreSQL, Redis, TimescaleDB/ClickHouse, MongoDB
*   **Messaging**: Apache Kafka, RabbitMQ
*   **Frontend**: React/Vue.js, React Native/Flutter

## Estrutura do Projeto

*   `/backend`: Código fonte dos microserviços em Java.
*   `/docs`: Documentação da arquitetura e especificações.
*   `/frontend`: Código do frontend (em desenvolvimento).
*   `/infra`: Configurações de infraestrutura.

Para mais detalhes sobre a arquitetura, consulte a pasta [docs](./docs).
