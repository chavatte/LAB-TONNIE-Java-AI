# LAB-05: Criando Board de Tarefas com Java

## Chavatte Board - Sistema de Gerenciamento de Quadros Kanban

### Descrição

Este projeto implementa um sistema de gerenciamento de quadros Kanban em Java, similar ao Trello. O sistema oferece as seguintes funcionalidades:

* Criação de boards com nome customizado.
* Criação de colunas com diferentes tipos (Inicial, Pendente, Final, Cancelamento).
* Criação de cards com título, descrição e status de bloqueio.
* Movimentação de cards entre colunas, respeitando a ordem do board.
* Bloqueio e desbloqueio de cards com registro de motivos.
* Cancelamento de cards.
* Geração de relatórios de tempo de cards em cada coluna.
* Geração de relatórios de bloqueios de cards.

### Tecnologias Utilizadas

* Java
* SQLite (banco de dados)
* Flyway (migrações de banco de dados)
* Maven (gerenciamento de dependências)

## Aprendizado

O desenvolvimento deste projeto proporcionou o aprendizado em diversas áreas, tais como:

* **Lógica de programação:** Implementação da lógica de gerenciamento de boards, colunas e cards, incluindo movimentação, bloqueio, cancelamento e geração de relatórios.
* **Programação orientada a objetos:** Utilização de conceitos como classes, objetos, herança, polimorfismo e encapsulamento para organizar e estruturar o código.
* **Persistência de dados com SQLite:**  Utilização de um banco de dados relacional para armazenar as informações do sistema.
* **Migrações de banco de dados com Flyway:**  Gerenciamento das alterações no esquema do banco de dados.
* **Manipulação de eventos:** Gerenciamento de eventos do usuário, como entradas de teclado, para interagir com o sistema.
* **Gerenciamento de dependências com Maven:** Utilização do Maven para gerenciar as dependências do projeto.
* **Boas práticas de desenvolvimento:** Aplicação de boas práticas de desenvolvimento, como organização do código e nomenclatura clara.

## Instalação

**Bash**

```
# Clonando o Bootcamp:
git clone https://github.com/chavatte/LAB-TONNIE-Java-AI.git

```
**Bash**

```
# Compilando o jogo:
cd LAB-TONNIE-Java-AI/projects/LAB-05/chavatteboard
mvn compile

```

**Bash**

```
# Execute chavatteBoard:
mvn exec:java -Dexec.mainClass="dev.chavatte.Main"

# ou somente:
mvn exec:java

```

## Como Usar

* **Menu Principal:** Utilize o menu principal para criar, selecionar ou excluir boards.
* **Menu do Board:**  Após selecionar um board, utilize o menu do board para criar colunas, mover cards, bloquear/desbloquear cards, cancelar cards e gerar relatórios.
* **Relatórios:**  Acesse os relatórios de tempo e bloqueios para analisar o andamento das tarefas.

**Exemplos de Uso**

* **Criar um novo board:**  Selecione a opção "Criar novo board" no menu principal e informe o nome do board.
* **Adicionar colunas:** No menu do board, selecione a opção "Criar coluna" e informe o nome, ordem e tipo da coluna.
* **Criar cards:**  Selecione a opção "Criar card" no menu do board e informe o título e a descrição do card.
* **Mover cards:**  Selecione a opção "Mover card" no menu do board e escolha o card e a coluna de destino.
* **Bloquear/desbloquear cards:** Selecione a opção "Bloquear/desbloquear card" no menu do board e informe o motivo do bloqueio/desbloqueio.
* **Cancelar cards:** Selecione a opção "Cancelar card" no menu do board.
* **Gerar relatórios:** Selecione as opções "Gerar relatório de tempo" ou "Gerar relatório de bloqueios" no menu do board.

## Conclusão

Este projeto demonstra a implementação de um sistema de gerenciamento de quadros Kanban em Java. O código é organizado e modular, facilitando a compreensão, manutenção e futuras extensões. O sistema oferece uma experiência completa ao usuário, com criação de boards, colunas e cards, movimentação de cards, bloqueio/desbloqueio, cancelamento e geração de relatórios.
