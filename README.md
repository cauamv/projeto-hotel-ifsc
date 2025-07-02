# 🏨 Sistema Gerador de CRUD para Swing

Um sistema automático para geração de telas CRUD (Create, Read, Update, Delete) em Java Swing, utilizando anotações e reflection para criar interfaces dinâmicas baseadas em modelos de dados.

## 🚀 Funcionalidades

- ✅ **Geração Automática de Telas**: Crie formulários e tabelas automaticamente baseados nas suas classes
- ✅ **Anotações Configuráveis**: Configure campos, tipos e validações usando anotações simples
- ✅ **Suporte a Herança**: Funciona com classes que herdam de outras classes
- ✅ **Tipos de Campo Diversos**: TEXT, NUMBER, DATE, BOOLEAN, PASSWORD, CHAR
- ✅ **Validações Integradas**: Campos obrigatórios, tamanho máximo, editabilidade
- ✅ **Interface Responsiva**: Layout automático com formulário e tabela lado a lado
- ✅ **Zero Configuração Manual**: Apenas anote seus modelos e use!

## 📋 Pré-requisitos

- Java 8 ou superior
- Swing Framework (incluído no JDK)
- IDE Java (Eclipse, IntelliJ, NetBeans, etc.)

## 🗂️ Estrutura do Projeto

```
ProjetoHotel/
├── src/
│   ├── generator/              # Sistema gerador de CRUD
│   │   ├── CrudField.java      # Anotação para campos
│   │   ├── CrudEntity.java     # Anotação para entidades
│   │   ├── CrudRepository.java # Interface do repositório
│   │   ├── CrudController.java # Controlador genérico
│   │   └── CrudScreenGenerator.java # Gerador de telas
│   ├── model/                  # Modelos de dados
│   │   ├── Pessoa.java         # Classe base
│   │   └── Hospede.java        # Classe filha
│   ├── controller/             # Repositórios
│   │   └── HospedeRepository.java
│   └── main/                   # Classes principais
│       └── TesteCrudHospede.java
```

## 🛠️ Instalação e Configuração

### 1. Clone ou copie os arquivos para seu projeto

### 2. Crie a estrutura de pacotes:
- `generator` - Sistema principal
- `model` - Suas classes de modelo
- `controller` - Repositórios de dados
- `main` - Classes executáveis

### 3. Adicione as anotações aos seus modelos:

```java
package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "usuarios", displayName = "Usuários")
public class Usuario {
    
    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER")
    private int id;
    
    @CrudField(label = "Nome", required = true, order = 2, maxLength = 100)
    private String nome;
    
    @CrudField(label = "Email", order = 3, maxLength = 150)
    private String email;
    
    @CrudField(label = "Ativo", order = 4, type = "BOOLEAN")
    private boolean ativo;
    
    // Construtores, getters e setters...
}
```

## 🎯 Como Usar

### 1. **Anote sua classe modelo**

Use as anotações `@CrudEntity` e `@CrudField` para configurar como sua classe será exibida:

```java
@CrudEntity(tableName = "produtos", displayName = "Produtos")
public class Produto {
    @CrudField(label = "Código", editable = false, order = 1)
    private Long id;
    
    @CrudField(label = "Nome do Produto", required = true, order = 2)
    private String nome;
    
    @CrudField(label = "Preço", type = "NUMBER", order = 3)
    private Double preco;
    
    // getters e setters...
}
```

### 2. **Implemente o repositório**

Crie uma classe que implementa `CrudRepository<T>`:

```java
package controller;

import generator.CrudRepository;
import model.Produto;
import java.util.*;

public class ProdutoRepository implements CrudRepository<Produto> {
    private Map<Long, Produto> database = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);
    
    @Override
    public void save(Produto entity) {
        if (entity.getId() == null) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }
    
    @Override
    public void update(Produto entity) {
        database.put(entity.getId(), entity);
    }
    
    @Override
    public void delete(Object id) {
        database.remove((Long) id);
    }
    
    @Override
    public Produto findById(Object id) {
        return database.get((Long) id);
    }
    
    @Override
    public List<Produto> findAll() {
        return new ArrayList<>(database.values());
    }
}
```

### 3. **Gere a tela CRUD**

Crie uma classe principal para executar:

```java
package main;

import javax.swing.*;
import controller.ProdutoRepository;
import generator.CrudController;
import generator.CrudScreenGenerator;
import model.Produto;

public class TesteProduto {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configurar Look and Feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Criar repositório e controlador
            ProdutoRepository repository = new ProdutoRepository();
            CrudController<Produto> controller = new CrudController<>(repository, Produto.class);
            
            // Gerar e exibir a tela CRUD
            JFrame tela = CrudScreenGenerator.generateCrudScreen(controller);
            tela.setVisible(true);
        });
    }
}
```

## 📚 Anotações Disponíveis

### @CrudEntity
Configura a entidade como um todo:
- `tableName`: Nome da tabela (obrigatório)
- `displayName`: Nome de exibição na interface

### @CrudField
Configura campos individuais:
- `label`: Rótulo do campo na interface
- `visible`: Se o campo aparece na tabela (padrão: true)
- `editable`: Se o campo pode ser editado (padrão: true)
- `required`: Se o campo é obrigatório (padrão: false)
- `order`: Ordem de exibição (padrão: 0)
- `type`: Tipo do campo (padrão: "TEXT")
- `maxLength`: Tamanho máximo do texto (padrão: 0 = ilimitado)

## 🎨 Tipos de Campo Suportados

| Tipo | Componente | Descrição |
|------|------------|-----------|
| `TEXT` | JTextField | Campo de texto padrão |
| `NUMBER` | JSpinner | Campo numérico com controles |
| `PASSWORD` | JPasswordField | Campo de senha mascarado |
| `BOOLEAN` | JCheckBox | Checkbox verdadeiro/falso |
| `CHAR` | JTextField | Campo de um caractere |
| `DATE` | JTextField | Campo de data (formato dd/MM/yyyy) |

## 🔧 Exemplo Completo

Veja o exemplo completo com a classe `Hospede` que herda de `Pessoa`:

```java
// Classe base
@CrudEntity(tableName = "pessoas", displayName = "Pessoas")
public class Pessoa {
    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER")
    private int id;
    
    @CrudField(label = "Nome", required = true, order = 2)
    private String nome;
    
    // ... outros campos
}

// Classe filha
@CrudEntity(tableName = "hospedes", displayName = "Hóspedes")
public class Hospede extends Pessoa {
    @CrudField(label = "CNPJ", order = 15)
    private String cnpj;
    
    @CrudField(label = "Razão Social", order = 16)
    private String razaoSocial;
    
    // ... outros campos
}
```

## 🚦 Funcionalidades da Interface

### Tabela (lado esquerdo):
- **Listar**: Exibe todos os registros
- **Atualizar**: Recarrega os dados
- **Selecionar**: Clique em uma linha para selecionar
- **Excluir**: Remove o registro selecionado

### Formulário (lado direito):
- **Campos automáticos**: Baseados nas anotações
- **Validação**: Campos obrigatórios em vermelho
- **Salvar**: Adiciona novo registro
- **Limpar**: Limpa todos os campos

## 🎯 Benefícios

- **Produtividade**: Crie telas CRUD em minutos, não horas
- **Consistência**: Interface padronizada em todo o sistema
- **Manutenibilidade**: Mudanças no modelo refletem automaticamente na interface
- **Flexibilidade**: Configuração através de anotações simples
- **Reutilização**: Um sistema para todos os seus modelos

**Desenvolvido com ❤️ para simplificar o desenvolvimento de interfaces Swing em Java**
