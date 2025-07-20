#!/bin/bash

echo "Criando as imagens...."

docker build -t chavatte/feedback_app-backend:1.0 backend/.
docker build -t chavatte/feedback_app-database:1.0 database/.

echo "Realizando o push das imagens...."

docker push chavatte/feedback_app-backend:1.0
docker push chavatte/feedback_app-database:1.0

echo "Criando servicos no cluster kubernets...."

kubectl apply -f ./services.yml

echo "Criando os deployments...."

kubectl apply -f ./deployment.yml

