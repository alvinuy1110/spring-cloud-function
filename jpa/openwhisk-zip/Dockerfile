FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY m2 /m2
ADD runner.jar .
# ENV JAVA_OPTS=""
# ## original
# ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "runner.jar", "--thin.root=/m2", "--thin.name=function", \
#  "--function.name=getStudent", \
#  "--spring.main.sources=com.myproject.cloud.CloudFunctionJpaApp"]
# ## as spring boot app
# ## supply external properties, spring config
# # ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "runner.jar", "--thin.root=/m2","--thin.name=function", "--function.name=getStudent", \
# # "--spring.config.name=app", "--spring.config.location=./", \
# # "--spring.main.sources=com.myproject.cloud.CloudFunctionJpaApp"]
EXPOSE 8080

