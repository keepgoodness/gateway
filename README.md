### GATEWAY (приложение фасада)

### Микросървизна архитектура
* fixer-provider: 
  * Да се ползва като заместител на fixer.io в случай, че няма api-key. В моя случай ми свършиха безплатните заявки.
* ### gateway-service:
  * Взима данни от fixe.io( или fixer-provider) праща към Rabbitmq.
  * #### Rest API endpoint
    * (POST) http://localhost:8081/api/v1/json/current  връща полседните валути, влезнали в релационната база, в Json формат
      * body
        * `{"requestId": "b99776fe-8c37-4962-8af3-76b88a245557","timestamp": 1586335186721,"client": "1234","currency":"EUR"}`
  
  * #### Rest API endpoint
    * (POST) http://localhost:8081/api/v1/json/history  връща обменния курс за дадена валута за определен период, в Json формат
      * body
        * `{"requestId": "b69777fe-2c87-4262-8af3-77u85a245557","timestamp": 1586335186721,"client": "1234","currency":"GBP","period":500}`
  
  * #### Rest API endpoint
    * (POST) http://localhost:8081/api/v1/xml/command  връща полседните валути влезнали в релационната база, в Xml формат
      * body
        * `<command id="133329"><get consumer="13617162"><currency>GBP</currency></get></command>`
        * `<command id="1234-7829"><history consumer="13617162" currency="BGN" period="60" /></command>`
  * #### Комуникация между сървизите
    * Взима данни директно от mysql-service с Feign клиент.
    * Изпраща взетите от fixer.io данни през RabbitMQ.

* ### mysql-service:
  * Слуша RabbitMq и записва данните от fixer.iо. също така и статистическите данни за заявките.
  * Rest API endpoints oтговарящи на заявка от gаtеway-service
  * cache Redis за бързодействие

### Docker
* Изготвен е docker-compose файл.
