FROM node:22 AS build

WORKDIR /app

COPY package.json yarn.lock ./

RUN yarn install

COPY . .

RUN yarn build

FROM nginx:alpine
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
