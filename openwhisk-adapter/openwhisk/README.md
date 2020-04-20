This is to create a custom openwhisk adapter, extending from Spring but addressing the current limitation.

This one uses the standard spring wrapper.  It then invokes the custom OpenwiskAdapterApp.  The final piece is the actual place of the function app.

The key part is in the function.properties to import said dependencies.


Preparing the Application
=========================

# Install the Artifact

```
mvn clean install
```

Preparing the Docker Image
==========================

# Set up the Function.properties

```$xslt
dependencies.function: com.example:pof:0.0.1-SNAPSHOT
```

Example:

populate the maven groupId, artifactId, version
```
dependencies.function: com.myproject:jpa:1.0.0-SNAPSHOT
```

# Copy the Openwhisk adapter

```
cp spring-cloud-function-adapters/spring-cloud-function-adapter-openwhisk/target/spring-cloud-function-adapter-openwhisk-2.0.0.BUILD-SNAPSHOT.jar runner.jar
```

or get from Maven Repo

```
https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-function-adapter-openwhisk

wget https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-function-adapter-openwhisk/3.0.4.RELEASE/spring-cloud-function-adapter-openwhisk-3.0.4.RELEASE.jar
```


Generate M2 repo
================

This is to package the maven dependencies to the docker image.  The dependencies are those used by Openwhisk adapter.
The maven coordinates in the function.properties will also be pulled.

```
java -jar -Dthin.root=m2 runner.jar --thin.name=function --thin.dryrun
```

Note:
not sure if this is helpful, NOT PART OF OFFICIAL GUIDE
Not working
this actually copies the jar into m2, makes image fatter
```
java -jar runner.jar --thin.classpath=path --thin.root=m2
```

Source Code of Adapter
======================

* https://github.com/spring-cloud/spring-cloud-function/tree/master/spring-cloud-function-adapters/spring-cloud-function-adapter-openwhisk


Docker
======

# Build Image

```
docker build -t [username/appname] .
```

example:
```
docker build -t alvinuy1110/uppercase .
```

# Push Image

This is to push to remote docker repo (i.e. docker.io).  
This is optional and needs customization depending on where the repo is.

```
docker push [username/appname]
```

example:
```
docker push alvinuy1110/uppercase
```

login:
```
docker login -u "myusername" -p "mypassword" docker.io
```

OpenWhisk
=========

# Create Action

Example syntax:
```
wsk action create example --docker [username/appname]
```

Example:
```
wsk -i action create plain --docker alvinuy1110/uppercase --web true
```


## Testing
wsk -i action invoke plain --result --param payload hello

wsk -i action get plain --url
wsk -i action delete plain

## activation list

wsk -i activation list
## trouble shoot using activationId
wsk -i activation get <activationId>

wsk -i activation result afaf6ce5eded4b00af6ce5ededcb0025
## test run docker
docker run --publish 8000:8080 --name jpa alvinuy1110/uppercase



## TODO

* it seems docker image needs pushing to docker.io
  openwhisk pulls the image from there
  ** need to figure how to point to private repo
  
* still getting errors
* No class def found

```
Caused by: java.lang.ClassNotFoundException: org.springframework.boot.SpringApplication
```
adding --thin.classpath=path solved???

Not working
added in the function.properties (thin.properties):
```
dependencies.org.springframework.boot: org.springframework.boot:spring-boot
```

java -jar runner.jar --thin.root=m2 --thin.name=function --thin.dryrun --thin.trace=true --thin.repo=http://nexus:8081/nexus/content/groups/public
java -jar runner.jar --thin.name=function --function.name=uppercase --thin.root=m2 --thin.repo=http://nexus:8081/nexus/content/groups/public


java -jar runner.jar --thin.root=m2 --thin.name=function --thin.dryrun --thin.classpath=path
adding --thin.classpath=path solved

#######################
# convert thin to thick jar
Note: make the runner.jar was built using spring-boot:repackage

java -jar runner.jar --thin.root=m2 --thin.name=function --thin.dryrun 
java -jar runner.jar --thin.library=org.springframework.boot.experimental:spring-boot-thin-tools-converter:1.0.21.RELEASE .

java -jar runner.jar --thin.library=org.springframework.boot.experimental:spring-boot-thin-tools-converter:1.0.21.RELEASE build-runner
Note: the jars are in build-runner


java -jar runner-exec.jar --thin.name=function --function.name=uppercase --thin.root=m2
#######################

===========================
/home/user/git/spring-cloud-function/plain/openwhisk/m2/repository/org/springframework/boot/spring-boot/2.2.5.RELEASE/spring-boot-2.2.5.RELEASE.jar
java -cp ./m2/repository/org/springframework/boot/spring-boot/2.2.5.RELEASE/spring-boot-2.2.5.RELEASE.jar -jar runner.jar --thin.name=function --function.name=uppercase --thin.root=m2

java -cp /home/user/git/spring-cloud-function/plain/openwhisk/m2/repository/org/springframework/boot/spring-boot/2.2.5.RELEASE/spring-boot-2.2.5.RELEASE.jar -jar runner.jar --thin.name=function --function.name=uppercase --thin.root=m2

java -cp /home/user/.m2/repository/org/springframework/boot/spring-boot/2.2.5.RELEASE/spring-boot-2.2.5.RELEASE.jar -jar runner.jar --thin.name=function --function.name=uppercase --thin.root=m2

/home/user/.m2/repository/org/springframework/boot/spring-boot/2.2.5.RELEASE/spring-boot-2.2.5.RELEASE.jar

java -jar runner.jar --thin.name=function --function.name=uppercase --thin.root=m2

java -jar runner.jar  -Dthin.root=m2 --thin.name=function --thin.dryrun --thin.trace=true


java -jar -Dthin.root=m2 runner.jar --thin.name=function --thin.dryrun --thin.classpath=path

CLASSPATH=`java -jar runner.jar --thin.classpath=path`


java -jar runner.jar --thin.name=function --function.name=uppercase --thin.root=m2


java -jar runner.jar --thin.name=function --function.name=uppercase --thin.root=m2 --spring.main.sources=com.myproject.OpenwhiskAdapterApp

java -jar -Dthin.root=m2 runner.jar --thin.name=function --thin.dryrun

## WORKS BUT...

--1) had to include-- 

2) had to create a bean

```

    // NOTE had to register this as getting an error
//	No qualifying bean of type 'org.springframework.cloud.function.context.catalog.FunctionInspector'
    // THIS IS A HACK FOR NOW
    @Bean
    @Primary
    FunctionInspector functionInspector(ConversionService conversionService, @Nullable CompositeMessageConverter messageConverter) {
        return new BeanFactoryAwareFunctionRegistry(conversionService, messageConverter);
//		return new InMemoryFunctionCatalog();
    }
```