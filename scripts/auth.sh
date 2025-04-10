curl -X 'POST' \
  'http://localhost:8080/api/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "joao_das_neves",
  "password": "12345678"
}' | jq

echo "\n"

curl -X 'POST' \
  'http://localhost:8080/api/auth/login' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "joao_das_neves",
  "password": "12345678"
}' | jq -r '.accessToken'
