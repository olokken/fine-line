FROM quay.io/keycloak/keycloak:21.1.1

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

EXPOSE 8080

ENTRYPOINT ["start-dev"]
