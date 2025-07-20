# LAB-06: Criando um Deploy de uma Aplicação

## Implantação de Aplicação Web com Kubernetes

### **Descrição**

Este projeto implementa uma aplicação de feedback completa. A arquitetura é composta por um frontend em HTML/CSS/JavaScript, um backend em PHP e um banco de dados MySQL. O principal objetivo é demonstrar o processo de conteinerização de cada componente com Docker e a orquestração dos contêineres em um cluster Kubernetes.

A aplicação oferece as seguintes funcionalidades:

* Interface web para envio de nome, e-mail e comentário.
* Backend para processar e armazenar os dados recebidos.
* Banco de dados para persistência dos feedbacks.

### **Tecnologias Utilizadas**

* **Frontend** : HTML, CSS, JavaScript (com jQuery)
* **Backend** : PHP
* **Banco de Dados** : MySQL
* **Conteinerização** : Docker
* **Orquestração** : Kubernetes

### **Aprendizado**

O desenvolvimento deste projeto proporcionou o aprendizado em diversas áreas, tais como:

* **Arquitetura de Microsserviços** : Compreensão de como desacoplar uma aplicação monolítica em serviços independentes (frontend, backend, banco de dados).
* **Conteinerização com Docker** : Criação de `Dockerfiles` para empacotar as aplicações de backend e banco de dados, garantindo um ambiente de execução consistente.
* **Orquestração com Kubernetes** :
* Criação de `Deployments` para gerenciar os pods da aplicação.
* Configuração de `Services` para expor a aplicação externamente (`LoadBalancer`) e permitir a comunicação entre os contêineres (`ClusterIP`).
* Gerenciamento de dados persistentes com `PersistentVolume` e `PersistentVolumeClaim` para o banco de dados MySQL.
* **Redes em Kubernetes** : Entendimento de como os serviços se comunicam dentro do cluster através de nomes de serviço (DNS do Kubernetes), como visto no arquivo de conexão do PHP que aponta para `mysql-connection`.
* **Automação de Deploy** : Utilização de scripts Shell para automatizar o processo de build das imagens Docker, push para um registry e aplicação dos manifestos no cluster Kubernetes.
* **Boas práticas de desenvolvimento** : Aplicação de boas práticas na organização dos arquivos do projeto e na criação dos manifestos YAML para garantir a manutenibilidade da infraestrutura como código.

### **Instalação e Execução**

**Pré-requisitos:**

* Docker instalado e em execução.
* `kubectl` instalado e configurado para acessar seu cluster Kubernetes.
* Um repositório de imagem (como o Docker Hub) para enviar as imagens.

**Bash**

```
# 1. Clone o repositório do projeto:
git clone https://github.com/chavatte/LAB-TONNIE-Java-AI

# 2. Navegue até o diretório do projeto:
cd LAB-TONNIE-Java-AI/projects/LAB-06

# 3. Execute o script de automação:
# (Lembre-se de logar no seu registry Docker antes: docker login)
sh script.sh
```

O script irá automaticamente construir as imagens, enviá-las para o repositório e implantar todos os recursos no Kubernetes.

### **Como Usar**

1. **Verifique a implantação** : Aguarde alguns instantes para que os pods sejam criados e estejam no estado `Running`. Você pode verificar com `kubectl get pods`.
2. **Obtenha o IP de acesso** : O backend foi exposto através de um serviço `LoadBalancer`. Para obter o endereço IP externo, execute o comando:
   **Bash**

```
   kubectl get svc php-lb
```

1. **Acesse a aplicação** : Copie o `EXTERNAL-IP` fornecido pelo comando acima e cole no seu navegador.
2. **Envie um feedback** : Preencha os campos "Nome", "Email" e "Comentário" no formulário e clique em "Enviar".
3. **Confirmação** : Um alerta confirmará que seu feedback foi enviado com sucesso.

### **Estrutura da Aplicação**

* **Formulário de Feedback** :
* Campo de texto para  **Nome** .
* Campo de texto para  **Email** .
* Área de texto para  **Comentário** .
* Botão **"Enviar"** para submeter os dados.

### **Exemplo de Uso**

Após executar o `script.sh`, use `kubectl get svc php-lb` para encontrar o IP externo. Acesse o IP no seu navegador, preencha o formulário com seus dados e um comentário de teste. Ao clicar em "Enviar", a aplicação PHP irá salvar essas informações no banco de dados MySQL que está rodando em um pod separado, com os dados persistidos em um volume.

### **Conclusão**

Este projeto demonstra de forma prática e eficiente como modernizar uma aplicação web tradicional, utilizando Docker para conteinerizar seus componentes e Kubernetes para orquestrar a implantação de forma robusta e escalável. A estrutura do projeto é modular e segue as boas práticas de infraestrutura como código, facilitando a manutenção, a compreensão e futuras extensões do ambiente.
