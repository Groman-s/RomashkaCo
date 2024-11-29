# РомашкаКо

### Описание end-поинтов
#### GET запросы:

* `/api/products` - список всех товаров в базе. можно указать параметр _**keyWord**_ для фильтрации результатов. 
Вернёт пустой список, если результатов не найдено.
* `/api/products/{id}` - получение товара по id. Вернёт статус 404 и сообщение об ошибке, если товара с таким id в 
базе нет.

#### POST запросы:

* `/api/products` - добавить новый товар. Требуется указать поля **_name_**, **_description_**, **_price_**, **_inStock_**.
inStock по умолчанию false, description по умолчанию null, price по умолчанию 0. Вернёт статус 400 и сообщение об ошибке,
если название пустое или содержит больше 255 символов, описание длиннее 4096 символов или стоимость отрицательна.

#### PATCH запросы:

* `/api/products/{id}` - обновить товар по id (частично или полностью). Обновляет существующий в базе товар по его id. 
Вернёт статус 400 и сообщение об ошибке, если товара с таким id нет.

#### DELETE запросы:

* `/api/products/{id}` - удаляет товар из базы по id. Возвращает статус 400 и сообщение об ошибке, если товара с таким
ID нет.

---

### Docker

#### Создание образа

выполнить команду `docker build --tag <ИМЯ_ОБРАЗА> .`, находясь в корне проекта (содержит файл Dockerfile). Или можно
просто запустить скрипт `create-docker-microservice-image.sh` (по умолчанию создаёт образ с названием romashka)

#### Запуск микросервиса

выполнить команду `docker-compose up -d`

