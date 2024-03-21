COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM python:3.9
WORKDIR /ai
COPY --from=builder /app/build /ai/frontend
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .
CMD ["python", "app.py"] 

EXPOSE 3002
