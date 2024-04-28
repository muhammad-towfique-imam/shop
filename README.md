# Cybershop Application for WSD

### Starting the application
Use the following command to start the application:
```shell
gradlew bootRun 
```
Once the application is started the following can be accessed:
1. Swagger UI can be accessed from here: 
   
   http://localhost:8080/swagger-ui/index.html
2. Grafana/Loki can be accessed from here:

   http://localhost:3000/
   
   To get the log level for the application, the following actuator endpoint can be used:
   ```shell
   curl http://localhost:8080/actuator/loggers/com.cyanice.shop
   ```
   To change the log level for the application, the following command can be used:
   ```shell
   curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel": "TRACE"}'
   http://localhost:8080/actuator/loggers/com.cyanice.shop
   ```
### Postman
Postman collection export for this project can be found here:
```shell
extra/Shop Assignment.postman_collection.json
```
### Running the test cases
Use the following command to run all the test cases:
```shell
gradlew test 
```
The html test report is generated in the following folder:
```shell
build/reports/tests/test/index.html 
```


### API endpoints
1. Return the wish list of a customer:

   http://localhost:8080/api/customer/:id/wishlist?pageNo=0&pageSize=10
2. Return the total sale amount of the current day:

   http://localhost:8080/api/order/sale-on-date
3. Return the max sale day of a certain time range:

   http://localhost:8080/api/order/max-sale-date?from=2024-04-20&to=2024-04-21
4. Return top 5 selling items of all time (based on total sale amount):

   http://localhost:8080/api/order/popular-products?duration=All&category=Amount
5. Return top 5 selling items of the last month (based on number of sales):

   http://localhost:8080/api/order/popular-products?duration=LastMonth&category=Count

** All the date (yyyy-MM-dd) value for input are considered to be in UTC
