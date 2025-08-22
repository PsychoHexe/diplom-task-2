#  krivstova_ai

Используемый язык и библиотеки:

|Java| 11+|
|--------|--------|
|Maven | 17|
|junit | 4.13.1|
|Selenium| 4.13.0|
|allure | 2.15.0 |
|rest-assured | 5.3.0 |
|gson | 2.8.9|
|datafaker| 1.4.0 |


## Очистка теста и запуск Allure server

```sh
mvn clean test
mvn allure:serve
```

### Сохранение отчета в коммит

```sh
git add -f .\target\allure-results.
git commit -m "add allure report"
git push
```