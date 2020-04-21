
Preparing the Application
=========================

# Install the Artifact

```
mvn clean install
```

## The maven plugin

Make sure to use the thin layout. Using repackage will make it a fat jar and executable.  This is the traditional spring boot.

```
         <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.2.2.RELEASE</version>
                <dependencies>
                    <!-- The following enables the "thin jar" deployment option. -->
                    <dependency>
                        <groupId>org.springframework.boot.experimental</groupId>
                        <artifactId>spring-boot-thin-layout</artifactId>
                        <version>1.0.21.RELEASE</version>
                    </dependency>
                </dependencies>
<!--                <executions>-->
<!--                    <execution>-->
<!--                        <goals>-->
<!--                            <goal>repackage</goal>-->
<!--                        </goals>-->
<!--                    </execution>-->
<!--                </executions>-->
            </plugin>
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
docker build -t alvinuy1110/cloud-function-jpa-app .
```

# Push Image

This is to push to remote docker repo (i.e. docker.io).  
This is optional and needs customization depending on where the repo is.

```
docker push [username/appname]
```

example:
```
docker push alvinuy1110/cloud-function-jpa-app
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
wsk -i action create jpa --docker alvinuy1110/cloud-function-jpa-app --web true
```


## Testing
wsk -i action invoke jpa --result --param payload 1

wsk -i action get jpa --url
wsk -i action delete jpa

## activation list

wsk -i activation list
## trouble shoot using activationId
wsk -i activation get <activationId>

wsk -i activation result afaf6ce5eded4b00af6ce5ededcb0025
## test run docker
docker run --publish 8080:8080 --name jpa alvinuy1110/cloud-function-jpa-app

## Curl Commands

The web URL syntax is for web action is 

```
https://{APIHOST}/api/v1/web/{QUALIFIED ACTION NAME}.{EXT}
```
* where qualified action name - has namespace/ package name
* ext - extension (i.e. json, xml), assuming the service supports it

### POST
curl -kv https://172.17.0.4:31001/api/v1/web/guest/default/jpa.json  -H "Content-type: application/json" -d '{"payload":"1"}'

### GET
curl -kv https://172.17.0.4:31001/api/v1/web/guest/default/jpa.json?payload=1
