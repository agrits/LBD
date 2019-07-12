# Build
mvn clean package && docker build -t pl.fis.artur.kasza/lbd .

# RUN

docker rm -f lbd || true && docker run -d -p 8080:8080 -p 4848:4848 --name lbd pl.fis.artur.kasza/lbd 

Servlet

1. [x]
2. [x]
3. [x]
4. [x]
5. [x]
6. [x]

Filter

1. [x]
2. [x]

Lifecycle events

1. [x]
2. [x]

CDI

1. 
2. [x]
3. [x]

EJB

1. [x]
2. [x]
3. [x]
4. [x]
5. [x]
6. 
