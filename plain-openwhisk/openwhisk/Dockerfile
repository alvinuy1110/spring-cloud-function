FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY m2 /m2
ADD runner.jar .
ADD function.properties .
# ADD app.properties .
ENV JAVA_OPTS=""
## original

## function.name - name of function
## function.type - type, values are function (default), supplier, consumer
## added for spring boot app --spring.main.sources=com.myproject.PlanOpenwhiskApp

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "runner.jar", "--thin.root=/m2", "--thin.name=function", \
 "--function.name=uppercase", \
 "--spring.main.sources=com.myproject.PlanOpenwhiskApp"]
## as spring boot app
## supply external properties, spring config
# ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "runner.jar", "--thin.root=/m2","--thin.name=function", "--function.name=getStudent", \
# "--spring.config.name=app", "--spring.config.location=./", \
# "--spring.main.sources=com.myproject.cloud.CloudFunctionJpaApp"]
EXPOSE 8080

