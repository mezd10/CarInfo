# CarInfo - Автомобильный справочник

Тестовое консольное приложение на Java для поступления на курсы swift разработки.
## Функциональность
Приложение имеет следующую функциональность:
* поиск по Марке и модели авто
* показ всех автомобилей
* добавление автомобиля в справочник
* изменение цвета автомобиля
* изменение стоимости автомобиля
* удаление автомобиля по заданному ID

## Реализация БД
Для корректного хранения, изменения и удаления данных была спроектирована база данных, использую Postgresql.
Скрипт создания и заполнения лежит в папке src/main/resources(create.sql || insert.sql)
Схема реализованной БД: 
![schema](https://github.com/mezd10/CarInfo/blob/master/schema.png)
Таблица characteristic:
(Содержит полную информацию об автомобиле)
* id
* id_marc - foreign key от таблицы marc
* id_model - foreign key от таблицы model_car
* id_body - foreign key от таблицы body
* year_issue - год выпуска автомобиля
* color - цвет автомобиля
* price - цена автомобиля в рублях

Таблица marc:
(Содержит марки машин)
* id
* marc - марка автомобиля

Таблица marc_car:
(содержит модели машин)
* id
* model - модель автомобиля

Таблица body:
(содержит тип кузова)
* id
* name_body - название кузова

## Реализация Java
Бало реализовано 9 классов:
* Main - главный класс
* DatabaseProvider - класс, отвечающий за подключение к БД
* FileProvider - класс, отвечающий за разбор конфигурационного файла
* Menu - главное меню приложения
* Info - класс, хранящий подменю приложения
* SelectData - класс, отвечающий за поиск в данном приложении 
* InsertData - класс, отвечающий за добавление новых записей в данном приложении
* UpdateData - класс, отвечающий за изменение в данном приложении
* DeleteData - класс, отвечающий за удаление в данном приложении

## Конфигурационный файл
### Пример конфигурационного файла
* host: localhost
* database: car
* port: 5432
* login: postgres
* password: 12345

Файл должен содержать все вышеперечисленные поля в формате указанном выше.
Порядок следования элементов не имеет значения.
