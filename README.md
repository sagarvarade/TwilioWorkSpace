# TwilioWorkSpace
My Application based on twilio API, In progress

This is my twilio base project with spring boot microservices

Application details 
1. All apps are sorted by A to Z on increasing order , they must be run from A to Z in order

1.Anaming-server  :http://localhost:8761/
    For Naming server , must start first then below apps
2.Bapi-gateway  : http://localhost:8765/actuator
   For registering other spring boot apps with this naming server
3. CTwilioUI    :http://localhost:8100
      http://localhost:8100/currency-conversion-feign/from/USD/INR/quantity/200
      http://localhost:8100/currency-conversion/from/USD/INR/quantity/200

4. DTwiliobackend : http://localhost:8000
    http://localhost:8000/currency-exchange/from/USD/INR
