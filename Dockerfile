FROM java:8
VOLUME /tmp
EXPOSE 8083
ADD /build/libs/client-ui-1.0.0.jar client-ui-1.0.0.jar
ENTRYPOINT ["java","-jar","client-ui-1.0.0.jar"]