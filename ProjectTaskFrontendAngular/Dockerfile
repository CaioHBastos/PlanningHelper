FROM node:12.18.3 as angular
WORKDIR /app
COPY package.json /app
RUN apt-get update && apt-get install -y curl
RUN curl -sL https://deb.nodesource.com/setup_8.x | bash -
RUN apt-get update && apt-get install -y nodejs
RUN npm install --silent
COPY . .
RUN npm run build

FROM nginx:alpine
VOLUME /var/cache/nginx
COPY --from=angular app/dist/task-app /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf
