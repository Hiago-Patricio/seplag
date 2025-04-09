#!/bin/bash

TOKEN="ACCESS_TOKEN"

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "Cuiabá", "uf": "MT"}'

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "São Paulo", "uf": "SP"}'

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "Rio de Janeiro", "uf": "RJ"}'

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "Belo Horizonte", "uf": "MG"}'

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "Salvador", "uf": "BA"}'

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "Fortaleza", "uf": "CE"}'

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "Porto Alegre", "uf": "RS"}'

curl -X POST http://localhost:8080/api/cities \
  -H "accept: */*" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "Curitiba", "uf": "PR"}'

