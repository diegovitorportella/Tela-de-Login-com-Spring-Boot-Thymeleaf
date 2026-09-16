# 🔐 Secure Login PUC - Atividade 02

Aplicação web desenvolvida com **Spring Boot** e **Thymeleaf**, implementando um sistema completo de autenticação e cadastro de usuários, incluindo envio de e-mails para recuperação de senha.

Projeto desenvolvido para a disciplina de Desenvolvimento e Integração de Aplicações Web da PUC Minas.

## 👥 Equipe (Pair Programming)
* Diego Vitor Pinto Mariano Portella
* Manoel Rodrigues Bezerra Neto

## 🚀 Tecnologias Utilizadas
* Java + Spring Boot
* Spring Security (Autenticação em Memória e BCrypt)
* Thymeleaf
* Java Mail Sender (SMTP)
* HTML5, CSS3 

## 🌐 Endpoints Disponíveis

| Método | Endpoint           | Descrição                                      | Público |
| ------ | ------------------ | ---------------------------------------------- | ------- |
| `GET`  | `/login`           | Exibe a tela de login                          | Sim     |
| `GET`  | `/register`        | Exibe a tela de cadastro                       | Sim     |
| `POST` | `/register`        | Processa os dados do cadastro                  | Sim     |
| `GET`  | `/recoverpassword` | Exibe a tela de recuperação de senha           | Sim     |
| `POST` | `/recoverpassword` | Processa a solicitação e envia e-mail          | Sim     |
| `GET`  | `/home`            | Dashboard do usuário logado                    | Não     |
| `GET`  | `/admin`           | Painel administrativo exclusivo (Role: ADMIN)  | Não     |
| `POST` | `/logout`          | Encerra a sessão atual                         | Não     |

### Pré-requisitos
* Java JDK 17 ou superior
* Maven instalado (ou utilize o `mvnw` incluso no projeto)

