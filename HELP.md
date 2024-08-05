### Reference
GATEWAY (приложение фасада)

### Микросървизна архитектура
* fixer-provider: 
  * Да се ползва като заместител на fixer.io в случай, че няма api-key. В моя случай ми свършиха безплатните заявки.
* gateway-service:
  * Взима данни от fixe.io( или fixer-provider) праща към Rabbitmq.
  * #### Rest API endpoint
    * [Post request body Json(този от предоставената задача)](http://localhost:8081/api/v1/json/current)
      * Взима данни директно от mysql-service с Feign клиент.

* mysql-service:
  * Взима данни от релационна база данни
  * Слуша RabbitMq и записва данните от fixer.iо. 
  * Отговаря на заявка от getway-service

* redis-service
  * Започнат, но все още не се ползва. 

### Docker
* Изготвен е docker-compose файл.