FROM node:lts-alpine as builder

WORKDIR /ai

# ENV PATH /ai/node_modules/.bin:$PATH

# COPY package.json /ai-edu/package.json
COPY . .

RUN npm install
RUN npm run build
FROM nginx:stable-alpine
COPY --from=builder /app/build /usr/share/nginx/html
# RUN npm install typescript @types/node @types/react @types/react-dom @types/jest react-router-dom redux react-redux
CMD ["nginx", "-g", "daemon off;"]

EXPOSE 3002



