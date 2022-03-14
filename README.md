# TwilioWorkSpace
My Application based on twilio API, In progress

This is my twilio base project with spring boot microservices

Application details <br>
1. All apps are sorted by A to Z on increasing order , they must be run from A to Z in order<br>

1.Anaming-server  :http://localhost:8761/<br>
    For Naming server , must start first then below apps<br>
2.Bapi-gateway  : http://localhost:8765/actuator<br>
   For registering other spring boot apps with this naming server<br>
3. CTwilioUI    :http://localhost:8100<br>
      http://localhost:8100/currency-conversion-feign/from/USD/INR/quantity/200<br>
      http://localhost:8100/currency-conversion/from/USD/INR/quantity/200<br>

4. DTwiliobackend : http://localhost:8000<br>
    http://localhost:8000/currency-exchange/from/USD/INR<br>
