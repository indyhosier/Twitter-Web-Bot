FROM node

WORKDIR /home/rest-server

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8888

CMD ["node", "index.js"]
