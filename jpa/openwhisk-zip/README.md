This is to demonstrate using base docker image and supplying a custom zip


The executable need not be a Bash script, of course. There are only two requirements for what you run as an executable:

That the zip file must contain a top-level executable file called exec.

That any binary executable is compatible with Alpine Linux 3.4.

openjdk:8-jdk-alpine

The zip can only be a max of 48MB as of this time.


=============



Assuming we have a SpringBoot app (thin app), we make a docker image with just standard 3rd party libs.
We use this image and add our app in it.


docker build -t alvinuy1110/java-spring-boot .
docker push alvinuy1110/java-spring-boot

# Preparing the ZIP

## Step 1: create 'exec' file

```
echo '#!/bin/bash
cp jpa-1.0.0-SNAPSHOT.jar ./m2/repository/com/myproject/jpa/1.0.0-SNAPSHOT/
java -Djava.security.egd=file:/dev/./urandom -jar runner.jar \
 --thin.root=/m2 --thin.name=function \
 --function.name=getStudent \
 --spring.main.sources=com.myproject.cloud.CloudFunctionJpaApp
' > exec
```


## Step 2: Change permission
```
chmod +x ./exec
./exec
```

## Step 3: Create archive
```
zip -r exec.zip exec function.properties jpa-1.0.0-SNAPSHOT.jar
```

*** payload too large http 413 :(

*** not workgin!!!


wsk -i action delete jpa-zip
wsk -i action create jpa-zip --native exec.zip --docker alvinuy1110/java-spring-boot --web true
wsk -i action invoke jpa-zip --result --param payload 1


Native
======

# Preparing the ZIP

## Step 1: create 'exec' file
```
echo '#!/bin/bash
echo "{ \"hello\": \"ran without a docker pull!\" }"' > exec
```

## Step 2: Change permission
```
chmod +x ./exec
./exec
```

## Step 3: Create archive
```
zip exec.zip exec
```

# WSK commands
wsk -i action delete noPull
wsk -i action create noPull --native exec.zip
wsk -i action invoke noPull --blocking --result

