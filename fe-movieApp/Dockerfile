FROM node:16-alpine AS build
WORKDIR /app
COPY ./ /app/
RUN npm install
RUN npm run build
EXPOSE 4200
CMD ["npm", "start"]