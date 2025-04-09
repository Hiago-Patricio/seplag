# Teste Prático – SEPLAG

## 👤 Dados de Inscrição

**Nome:** Hiago de Sousa Patrício  
**E-mail:** hiagodesousa13@gmail.com 
**Cargo Pretendido:** Desenvolvedor Java

---

## 📘 Descrição do Projeto

Esta é uma API RESTful desenvolvida em Java 21 com Spring Boot 3.4.4 que atende aos requisitos do teste prático da SEPLAG. A aplicação oferece autenticação e autorização com JWT, integração com MinIO para armazenamento de arquivos, persistência de dados em PostgreSQL, e é orquestrada via Docker Compose.

---

## 📦 Tecnologias Utilizadas

- Java 21 + Spring Boot 3.4.4
- Spring Security + JWT
- PostgreSQL (em container)
- MinIO (em container)
- Gradle
- Docker e Docker Compose
- SpringDoc OpenAPI (Swagger)
- MapStruct
- Lombok

---

## 🧪 Requisitos Atendidos

- ✅ Autenticação e autorização com JWT e expiração a cada 5 minutos
- ✅ Renovação de token via refresh token
- ❌ CRUD para Servidor Efetivo, Temporário, Unidade e Lotação
- ✅ Paginação em todas as consultas
- ❌ Upload de fotografias no MinIO com link temporário de 5 minutos
- ❌ Endpoint para buscar servidores efetivos por unidade
- ❌ Endpoint para buscar endereço funcional por nome
- ❌ CORS bloqueado para domínios externos
- ✅ Docker Compose com containers de app, banco e MinIO

---

## 🚀 Como Executar a Aplicação

### Pré-requisitos

- Java 21
- Docker + Docker Compose
- Gradle

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/hiago-patricio/seplag.git

cd seplag

# dev (enable debug)
docker-compose up --build

# prod
docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d
```

2. Acesse http://localhost:8080/swagger-ui.html

3. Crie um usuário em POST /api/auth/register.

4. Faça login em POST /api/auth/login.

5. Testes os endpoints desejados.
