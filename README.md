# 🏨 Hotel IFSC

Sistema de gestão hoteleira em Java, desenvolvido durante o curso de Análise e Desenvolvimento de Sistemas do IFSC (3º semestre).  
O projeto utiliza um gerador automático de telas CRUD para Swing, baseado em anotações e reflection, mantendo todos os dados em memória.

---

## 📋 Visão Geral

O **Hotel IFSC** provê uma interface desktop para cadastro e listagem de entidades como hóspedes e quartos, gerando automaticamente os formulários e tabelas a partir de classes anotadas. O foco atual está na implementação das telas de cadastro, utilizando `CrudGenerator` em conjunto com repositórios em memória.

---

## ⚙️ Funcionalidades

- Geração automática de formulários e tabelas CRUD
- Configuração de campos via anotações (`@CrudEntity`, `@CrudField`)
- Suporte a diversos tipos de campo: TEXT, NUMBER, DATE, BOOLEAN, PASSWORD, CHAR
- Validações de campos (obrigatórios, tamanho máximo)
- Herança de modelos (ex.: `Hospede` herda de `Pessoa`)
- Layout responsivo: formulário e tabela lado a lado
- Persistência em memória sem banco de dados

---

## 💻 Tecnologias

- **Linguagem:** Java 8+
- **GUI:** Swing
- **IDE:** Netbeans (recomendado) ou outra compatível
- **Arquitetura:** MVC com Repositórios e `CrudScreenGenerator`

---

## 🏁 Instalação e Execução

1. **Clone o repositório**  
   ```bash
   git clone https://github.com/cauamv/projeto-hotel-ifsc.git
   cd projeto-hotel-ifsc
   ```
2. **Importe no Netbeans**  
   - Selecione a pasta do projeto como um projeto Java.
3. **Execute a classe principal**  
   - Navegue até `src/main/java/main/TelaMenuPrincipal.java`.
   - Execute o método para abrir a tela principal.

---

## 📌 Observações

- Dados são mantidos apenas em memória enquanto a aplicação estiver em execução.
- O projeto está em desenvolvimento contínuo; novas entidades e telas serão adicionadas.

---

## ✍️ Créditos
Projeto sendo desenvolvido por [**Cauã de Moraes Vieira**](https://www.linkedin.com/in/cauamv/) e [**Arthur Souza Mendes**](https://www.linkedin.com/in/arthur-souza-mendes-078653252/).
