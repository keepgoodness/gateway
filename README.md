### Reference
GATEWAY (приложение фасада)

### Микросървизна архитектура
* fixer-provider: 
  * Да се ползва като заместител на fixer.io в случай, че няма api-key. В моя случай ми свършиха безплатните заявки.
* ### gateway-service:
  * Взима данни от fixe.io( или fixer-provider) праща към Rabbitmq.
  * #### Rest API endpoint
    * http://localhost:8081/api/v1/json/current POST медот, връща полседните валути влезнали в релационната база
      * request body 
      * {"requestId": "b99776fe-8c37-4962-8af3-76b88a245557","timestamp": 1586335186721,"client": "1234","currency":"EUR"}
  * #### Комуникация между сървизите
    * Взима данни директно от mysql-service с Feign клиент.
    * Изпраща взетите от fixer.io данни през RabbitMQ.

* ### mysql-service:
  * Взима данни от релационна база данни
  * Слуша RabbitMq и записва данните от fixer.iо. 
  * Отговаря на заявка от gаtеway-service 

* ### redis-service
  * Започнат, но все още не се ползва. 

### Docker
* Изготвен е docker-compose файл.
