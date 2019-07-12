FROM airhacks/glassfish
COPY ./target/lbd.war ${DEPLOYMENT_DIR}
