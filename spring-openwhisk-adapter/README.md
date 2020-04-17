This is a copy of the Spring Openwhisk Adapter.

Trying to understand as a standalone spring boot in order to understand how the app behaves.

In this demo, the application.properties is only need if the app is a Spring Boot app. 
As used by a jar, the function.* properties are passed in the environment


## POST init

curl -v http://localhost:8080/init -H "Content-Type:application/json" -X POST -d '{"name":"uppercase","binary":false,"main":null}'

## POST run
curl -v http://localhost:8080/run -H "Content-Type:application/json" -X POST -d '{"action_name":"uppercase", "value": {"payload":"hello"}}'

