


My Application based on twilio API, In progress

This is my twilio base project with spring boot microservices

Application details

All apps are sorted by A to Z on increasing order , they must be run from A to Z in order
1.Anaming-server :http://localhost:8761/
For Naming server , must start first then below apps
2.Bapi-gateway : http://localhost:8765/actuator
For registering other spring boot apps with this naming server
3. CTwilioUI :http://localhost:8100
http://localhost:8100/currency-conversion-feign/from/USD/INR/quantity/200
http://localhost:8100/currency-conversion/from/USD/INR/quantity/200

DTwiliobackend : http://localhost:8000
http://localhost:8000/currency-exchange/from/USD/INR

Eauthentication : http://localhost:8200 Will have two end points which can be used in all applications to check is user eligible for login or not All authentication logic will be performent here, We will implement logic in which all request are checked before giving output, can use filter for this POST http://localhost:8200/tokenverify : { "token": "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbm5hIiwiYXV0aG9yaXRpZXMiOlt7ImF1-jmbhT6HJl_bVX6SGE2TySWsQw79btCbutCWKOv582EPM9U_Pw7H7zS5m4Z" } 


POST http://localhost:8200/issuetoken : will issue token for this user: {"username":"anna", "password":"pass" }



http://localhost:8765/ctwilioui/currency-conversion-feign/from/USD/INR/quantity/200


http://localhost:8765/dtwiliobackend/currency-exchange/from/USD/INR


eauthenticationApplication

http://localhost:8765/eauthenticationApplication/actuator


Postman issue token : POST
http://localhost:8765/eauthenticationapplication/issuetoken   :   {"username":"anna","password":"pass"}

Token verify : POST 
http://localhost:8765/eauthenticationapplication/tokenverify   {     "token": "BeareryJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbm5hIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfU1RVREVOVCJ9XSwiaWF0IjoxNjQ3NTk3MDAyLCJleHAiOjE2NDg3NTE0MDB9.6FcIN-jmbhT6HJl_bVX6SGE2TySWsQw79btCbutCWKOv582EPM9U_Pw7H7zS5m4Z"} 



ctwilioui

To build Docker Application
Build as maven project Goals :  spring-boot:build-image -DSkipTests

docker run -p 8765:8761 sv/twilioprj-anaming-server:0.0.1-SNAPSHOT

docker run -p 8765:8765 sv/twilioprj-bapi-gateway:0.0.1-SNAPSHOT

docker run -p 8100:8100 sv/twilioprj-ctwilioui:0.0.1-SNAPSHOT

docker.io/sv/twilioprj-eauthentication:0.0.1-SNAPSHOT



let url ="http://192.168.1.100:8200/issuetoken";
Containers created :

[INFO] Successfully built image 'docker.io/sv/twilioprj-anaming-server:0.0.1-SNAPSHOT'

[INFO] Successfully built image 'docker.io/sv/twilioprj-bapi-gateway:0.0.1-SNAPSHOT'

[INFO] Successfully built image 'docker.io/sv/twilioprj-ctwilioui:0.0.1-SNAPSHOT'

[INFO] Successfully built image 'docker.io/sv/twilioprj-dtwiliobackend:0.0.1-SNAPSHOT'

[INFO] Successfully built image 'docker.io/sv/twilioprj-eauthentication:0.0.1-SNAPSHOT'



 docker container stop 954ddcb2ed64
 
 docker compose rm
 docker image remove e580d008610b
 
docker run -p 9411:9411 openzipkin/zipkin:2.23
 

docker run -p 8761:8761 sv/twilioprj-anaming-server:0.0.1-SNAPSHOT
 
docker run -p 8765:8765 sv/twilioprj-bapi-gateway:0.0.1-SNAPSHOT

docker run -p 8100:8100 sv/twilioprj-ctwilioui:0.0.1-SNAPSHOT

docker run -p 8000:8000 sv/twilioprj-dtwiliobackend:0.0.1-SNAPSHOT

docker run -p 8200:8200 sv/twilioprj-anaming-server:0.0.1-SNAPSHOT
 
 

#spring.security.user.name=abc
#spring.security.user.password=123
#spring.datasource.url= jdbc:postgresql://192.168.1.102:5432/ctwilioui
#spring.datasource.username= postgres
#spring.datasource.password= admin
#spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
#spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
#spring.jpa.hibernate.ddl-auto= update 


#spring.jpa.hibernate.ddl-auto=update
#spring.datasource.url=jdbc:mysql://192.168.1.102:3306/ctwilioui
#spring.datasource.username=root
#spring.datasource.password=admin
#spring.datasource.driver-class-name =com.mysql.jdbc.Driver


1. Run docker run -p 9411:9411 openzipkin/zipkin:2.23
2. Run 


npm run serve -- --port 4000
