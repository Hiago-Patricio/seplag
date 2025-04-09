#!/bin/bash

TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvX2Rhc19uZXZlcyIsImlhdCI6MTc0NDE4NzU0MywiZXhwIjoxNzQ0MTg3ODQzLCJ0eXBlIjoiYWNjZXNzIn0.iTmug1lHbWusvCdj7HiCqwpomD-niJ1OsIF6IQQjqks"

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Cuiabá", "uf": "MT"}'

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "São Paulo", "uf": "SP"}'

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Rio de Janeiro", "uf": "RJ"}'

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Belo Horizonte", "uf": "MG"}'

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Salvador", "uf": "BA"}'

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Fortaleza", "uf": "CE"}'

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Porto Alegre", "uf": "RS"}'

curl -X POST http://localhost:8080/api/cidades \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"nome": "Curitiba", "uf": "PR"}'

