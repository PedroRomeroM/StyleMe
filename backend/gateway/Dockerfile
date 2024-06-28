# Usa a imagem oficial do Node como base
FROM node:alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o package.json e o package-lock.json (se existir)
COPY package*.json ./

# Instala as dependências
RUN npm install

# Copia o código fonte do gateway para o diretório de trabalho no container
COPY . .

# Expõe a porta 3001
EXPOSE 3001

# Comando para iniciar a aplicação
CMD ["node", "gateway.js"]
