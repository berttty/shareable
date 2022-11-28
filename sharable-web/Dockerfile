# This file need to be modified as required
FROM node:lts-alpine as build-stage

RUN mkdir -p /frontend
WORKDIR /frontend

# copy both 'package.json' and 'package-lock.json' (if available)
COPY /src/package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY /src ./

# build app for production with minification
WORKDIR /frontend
ENV NODE_OPTIONS=--max-old-space-size=8192
RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /frontend/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]