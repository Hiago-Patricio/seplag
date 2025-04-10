curl -X 'POST' \
  'http://localhost:8080/api/servidores-temporarios' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvX2Rhc19uZXZlcyIsImlhdCI6MTc0NDI0MjMzNywiZXhwIjoxNzQ0MjQyNjM3LCJ0eXBlIjoiYWNjZXNzIn0.e9fdaSOttUcWgLjsPsPTd-9MVrNIvRTphJdUGiVAsxs' \
  -H 'Content-Type: multipart/form-data' \
  -F 'requestDTO={"dataAdmissao":"2023-04-01","dataDemissao":"2023-09-01","pessoa":{"nome":"João Silva","dataNascimento":"1990-01-01","sexo":"Masculino","mae":"Maria Silva","pai":"José Silva","enderecos":[{"tipoLogradouro":"Rua","logradouro":"Rua das Palmeiras","numero":123,"bairro":"Centro","cidade":{"nome":"Cuiabá","uf":"MT"}}]},"foto":"foto-perfil-123.jpg"}' \
  -F 'foto=@/home/hiago/Pictures/mao1.jpg;type=image/jpeg'
