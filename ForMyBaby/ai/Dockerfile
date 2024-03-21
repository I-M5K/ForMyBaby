FROM node:lts-alpine as builder

WORKDIR /ai

# ENV PATH /ai/node_modules/.bin:$PATH

# COPY package.json /ai-edu/package.json
COPY . .

RUN npm install
# RUN npm install typescript @types/node @types/react @types/react-dom @types/jest react-router-dom redux react-redux
CMD ["npm", "start"]

EXPOSE 3002
