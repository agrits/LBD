# Build
mvn clean package && docker build -t pl.fis.artur.kasza/lbd .

# RUN

docker rm -f lbd || true && docker run -d -p 8080:8080 -p 4848:4848 --name lbd pl.fis.artur.kasza/lbd 