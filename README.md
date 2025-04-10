# Teste Prático – SEPLAG

## 👤 Dados de Inscrição

**Processo Seletivo:** PSS 02/2025/SEPLAG (Analista de TI - Perfil Junior, Pleno e Sênior) - PLENO   
**Inscrição:** 9946   
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
- ✅ CRUD para Servidor Efetivo, Temporário, Unidade e Lotação
- ✅ Paginação em todas as consultas
- ✅ Upload de fotografias no MinIO com link temporário de 5 minutos
- ✅ Endpoint para buscar servidores efetivos por unidade
- ✅ Endpoint para buscar endereço funcional por nome
- ✅ CORS bloqueado para domínios externos
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

3. Crie um usuário (exemplo no swagger) em POST /api/auth/register.

4. Faça login em POST /api/auth/login com o conteúdo do swagger.

5. Testes os endpoints desejados.

## Cenários
### 1 - Criação de unidade POST /api/unidades
O conteúdo está no swagger

### 2 - Criação de servidor temporário POST /api/servidores-temporarios
O conteúdo está no swagger

### 3 - Criação de servidor efetivo POST /api/servidores-efetivos
O conteúdo está no swagger

### 4 - Criação de lotação
Pre-requisitos: 
 - Cenários 1 e 3 e obter os ids

### 5 - Envio de foto /POST /api/foto/upload/{id}
Pre-requisitos:
 - Cenário 3 e obter o id

Obs.: O parâmetro id do endpoint é o obtido no cenário 3

### 6 - Buscar Endereço de lotações através do nome do servidor efetivo POST /api/lotacoes/buscar-por-nome-servidor
Pre-requisitos:
 - Cenário 4

Obs.: Bastar digitar o nome cadastrado no servidor efetivo (cenário 3).

### 7 - Buscar os dados do servidor efetivo através do código da unidade POST /api/servidores-efetivos/unidade/{id}
Pre-requisitos:
- Cenário 4
- Id da unidade do servidor efetivo que pode ser obtido no cenário 1.
