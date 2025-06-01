# 🏊‍♂️ Swim Tracker
API REST desenvolvida para ajudar tecnicos a anotar e salvar parciais de natação e atletas conseguirem ter um historico de seus tempos via internet.

## 📌 Funcionalidades

* Cadastro e autenticação de usuários (atletas e técnicos)

* Registro de competições (prova, distância, tempo, data, etc.)

* Controle de performance por prova (parciais, tempos, observações)

* Acompanhamento da evolução dos atletas

## 🚀 Tecnologias
* **Java 22**

* **Spring Boot**

* **Spring Data JPA**

* **Spring Security (JWT)**

* **Banco de dados:** MySQL

* **Docker**

* **JPA/Hibernate**

* **FlyWay**

## 📦 Estrutura do Projeto
```arduino
src/
├── controller
├── entities
├── exceptions
├── infra
├── interceptors
├── repositoy
└── services
```

## 🔐 Autenticação 
A autenticação é feita via JWT. Após o login, o token deve ser enviado no header Authorization em cada requisição protegida.

```h
Authorization: Bearer <token>
```

## 📌 Estrutura de Roles

* **ADMIN:** cria contas, gerencia acessos.

* **TÉCNICO:** registra atletas, cria competições e lança parciais.

* **ATLETA:** (ainda em desenvolvimento) acesso a seus dados de desempenho.

## 📄 Endpoints
### 🔐 Login
Somente **ADMIN** e **TECNICO** conseguem efetuar login.

```http
POST /auth/login
Authorization: Bearer <token>
{
	"login": "",
	"password": "",
}
```
### 🔑 Troca de senha
Usuarios vem com uma senha padrão sendo obrigado a efetuar a troca de senha antes de fazer qualquer outra requisição.
```http
POST /auth/trocar-senha
Authorization: Bearer <token>
{
	"newPassword": "",
	"confirmPassword": "",
	"defaultPassword": ""
}
```
### ➕🏊 Adicionar atleta (TECNICO)
```http
POST /tecnico/adiconar-atleta
Authorization: Bearer <token>
{
	"name": "",
	"category": ""
}
```
### 🏁➕ Adicionar competição (TECNICO)
```http
POST /tecnico/criar-competicao
Authorization: Bearer <token>
{
    "name": "Torneio a",
    "date": "2025/04/10",
    "poolType": "50",
    "proofs": [
      {
        "distance":200,
        "styleType": "Borboleta",
        "proofOrder": 1,
        "series":[
          {"serieNumber": 1, "athletes": [1,2]},
          {"serieNumber": 3, "athletes": [1]}
         ]
      }
  ]
}
```
### 🧑‍🏫🏊‍♂️ Meus atletas (TECNICO)
```http 
GET /tecnico/meus-atletas
Authorization: Bearer <token>
```
### ⏱️🔄 Atualizar parciais (TECNICO)
```http
PATCH /tecnico/atualizar-parciais
Authorization: Bearer <token>
[
	{
		"partialId": 5,
		"time": 20.11,
		"frequency": null
	},
	{
		"partialId": 6,
		"time": 20.11,
		"frequency": null
	},
	{
		"partialId": 7,
		"time": 20.11,
		"frequency": null
	},
	{
		"partialId": 8,
		"time": 20.11,
		"frequency": null
	}
]
```
### ⏱️📥 Consultar parciais
```http 
GET /parciais?atleta={nome}
```
### 🏆 Competição por nome (TECNICO)
```http 
GET /competicao/{nome}
Authorization: Bearer <token>
```
### 📝➕ Registro (ADMIN)
```http
POST /auth/registro
Authorization: Bearer <token>
{
	"login": "",
	"password": "",
	"role": "" (adm, tecnico, atleta)
}
```
### 🧑‍🏫📝➕ Registro de técnico (ADMIN)
```http
POST /auth/registrar-tecnico
Authorization: Bearer <token>
{
	"login":"",
	"password":"",
	"role": "",
	"team": "",
	"name": ""
}
```
## 🛠️ Como rodar o projeto
1. Clone o repositório:
```bash
git clone https://github.com/seunome/natacao-api.git
```
2. Configure o application.properties ou application.yml com os dados do seu banco.
3. Rode a aplicação:
```bash
./mvnw spring-boot:run
```
## ✍️ Autor

**Mikael de Oliveira Regetz**

[LinkedIn](https://www.linkedin.com/in/mikael-regetz-81223a302/) • [GitHub](https://github.com/MikaelRegetz10)
