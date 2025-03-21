# Модуль оркестратора заказов аэропорта
Модуль оркестратора аэропорта предназначен для управления процессами обслуживания самолетов после их приземления. 
Он координирует выполнение заказов на различные услуги (например, разгрузка багажа, обслуживание пассажиров, заправка топливом). 
Когда все услуги выполнены, модуль уведомляет самолет о завершении обслуживания.

## Основные функции  
1) Приём уведомлений о приземлении самолёта
2) Отправка заказов на выполнение
3) Отслеживание отчётов о выполнении заказов
4) Уведомление о завершении обслуживания на табло о завершении разгрузки и заправки для начала регистрации
5) Уведомление на самолёт о полном завершении обслуживания

## Зависимости 
- **Java 17**
- **`spring-boot-starter-data-jpa` (версия 3.2.0)**
- **`spring-boot-starter-validation` (версия 3.2.0)**
- **`spring-boot-starter-web` (версия 3.2.0)**
- **`spring-cloud-starter-openfeign` (версия 2023.0.0)**
- **`springdoc-openapi-starter-webmvc-ui` (версия 2.0.2)**
- **`lombok` (версия 1.18.30)**
- **`postgresql` (версия 42.7.3)**
- **`spring-boot-starter-test` (версия 3.2.0)**
- **`spring-boot-starter-thymeleaf` (версия 3.2.0)**

## Установка и запуск
1) Убедитесь, что у вас установлены Java 17 и Maven
2) Клонируйте репозиторий
   ```bash
    git clone https://github.com/your-repo/Airport.git
   ```
3) Перейдите в директорию проекта
   ```
   cd Airport
   ```
4) Соберите проект с помощью Maven
   ```
   mvn clean install
   ```
5) Запустите приложение
   ```
   mvn spring-boot:run
   ```

## API Reference
### Контроллер: OrderController    
Контроллер OrderController отвечает за обработку запросов, связанных с заказами на обслуживание самолетов. Он получает отчеты о выполнении заказов от различных служб (например, follow-me, baggage-discharge, passengers-discharge и т.д.) и обновляет статус заказов.   

Базовый URL:
/uno/api/v1/order   
Методы  

**1) Отчет о выполнении заказа от службы follow-me**  
*URL: /successReport/{orderId}/follow-me*

Метод: POST

Описание: Получает отчет о выполнении заказа от службы follow-me и передает заказ на выполнение в службу питания (catering) и службу багажа (PBC).

Параметры:

orderId (Long): Идентификатор заказа.


**2) Отчет о выполнении заказа от службы baggage-discharge**   
*URL: /successReport/{orderId}/baggage-discharge*    

Метод: POST

Описание: Получает отчет о выполнении заказа от службы baggage-discharge и обновляет этап выполнения заказа.

Параметры:

orderId (Long): Идентификатор заказа.


**3) Отчет о выполнении заказа от службы passengers-discharge**   
*URL: /successReport/{orderId}/passengers-discharge*   

Метод: POST

Описание: Получает отчет о выполнении заказа от службы passengers-discharge, обновляет этап выполнения заказа и передает заказ на топливозаправщик (tanker-truck).

Параметры:

orderId (Long): Идентификатор заказа.


**4) Отчет о выполнении заказа от службы tanker-truck**   
*URL: /successReport/{orderId}/tanker-truck*  

Метод: POST

Описание: Получает отчет о выполнении заказа от службы tanker-truck и обновляет этап выполнения заказа.

Параметры:

orderId (Long): Идентификатор заказа.


**5) Отчет о выполнении заказа от службы passengers-loading**   
*URL: /successReport/{orderId}/passengers-loading*   

Метод: POST

Описание: Получает отчет о выполнении заказа от службы passengers-loading и обновляет этап выполнения заказа.

Параметры:

orderId (Long): Идентификатор заказа.


**6) Отчет о выполнении заказа от службы baggage-loading**   
*URL: /successReport/{orderId}/baggage-loading*  

Метод: POST

Описание: Получает отчет о выполнении заказа от службы baggage-loading и обновляет этап выполнения заказа.

Параметры:

orderId (Long): Идентификатор заказа.


**7) Отчет о выполнении заказа от службы catering-unload**   
*URL: /successReport/{orderId}/catering-unload* 

Метод: POST

Описание: Получает отчет о выполнении заказа от службы catering-unload и обновляет этап выполнения заказа.

Параметры:

orderId (Long): Идентификатор заказа.


**8) Отчет о выполнении заказа от службы catering-load**   
*URL: /successReport/{orderId}/catering-load*  

Метод: POST

Описание: Получает отчет о выполнении заказа от службы catering-load и обновляет этап выполнения заказа.

Параметры:

orderId (Long): Идентификатор заказа.


**9) Получение заказа от регистрации**   
*URL: /order-from-registration*

Метод: POST

Описание: Получает заказ от регистрации и передает его на выполнение в службу питания (catering) и службу багажа (PBC).

Параметры:

RequestFromRegistration (тело запроса): Объект с данными о заказе.

Пример JSON:

```
{
  "planeId": 123,
  "baggage": 10,
  "food": 5,
  "passengers": [
    {
      "passengerId": 1
    },
    {
      "passengerId": 2
    }
  ]
}
```
Логирование: Логирует получение заказа.

**10) Получение всех заказов**   
*URL: /getAllOrders*

Метод: GET

Описание: Возвращает список всех заказов для отображения на dashboard.


### Контроллер: PlaneController     
Контроллер PlaneController отвечает за создание нового самолета и инициализацию заказа на его обслуживание. Он сохраняет информацию о самолете и передает заказ на выполнение службе follow-me.

Базовый URL:  
/uno/api/v1/plane   

Методы   
***1) Создание самолета и инициализация заказа***    
*URL: /{planeId}/{fuel}/create-plane*      

Метод: POST    

Описание: Создает новый самолет с указанным идентификатором и количеством топлива, сохраняет заказ на его обслуживание и передает заказ службе follow-me.

Параметры:

planeId (Integer): Идентификатор самолета.

fuel (Integer): Количество топлива, которое необходимо заправить.


