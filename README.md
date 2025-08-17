#  krivstova_ai


Очистка теста и запуск Allure server
```
mvn clean test
mvn allure:serve
```

git add -f .\target\allure-results\.
git commit -m "add allure report"
git push