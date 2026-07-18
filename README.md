
<div align="center"><h1 >Демопроект по автоматизации тестирования сайта <br> <a href="https://www.bspb.ru/ "> ПАО «Банк «Санкт-Петербург»</a></h1></div>div>

## ☑️ Содержание:

- Технологии и инструменты
- Список проверок, реализованных в тестах
- Запуск тестов (сборка в Jenkins) и из терминала
- Allure-отчет
- Интеграция с Allure TestOps
- Интеграция с Atlassian Jira
- Уведомление в Telegram о результатах прогона тестов


<a id="tools"></a>
## :ballot_box_with_check:Технологии и инструменты:

| Java                                                                                                      | IntelliJ  <br>  Idea                                                                                               | GitHub                                                                                                     | JUnit 5                                                                                                           | Gradle                                                                                                     | Selenide                                                                                                         | Selenoid                                                                                                                  | Allure <br> Report                                                                                                         |  Jenkins                                                                                                        |   Jira                                                                                                              | Telegram                                                                                                            |Allure <br> TestOps                                                                                                          
|:----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------:|
| <a href="https://www.java.com/"><img src="logo/Java.svg" width="50" height="50"  alt="Java"/></a>  | <a href="https://www.jetbrains.com/idea/"><img src="logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="logo/GitHub.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://aerokube.com/selenoid/"><img src="logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a> | <a href="https://github.com/allure-framework"><img src="logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> |<a href="https://www.jenkins.io/"><img src="logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://www.atlassian.com/software/jira/"><img src="logo/Jira.svg" width="50" height="50" alt="Java" title="Java"/></a> | <a href="https://web.telegram.org/"><img src="logo\Telegram.svg" width="50" height="50" alt="Telegram"/></a> |<a href="https://qameta.io/"><img src="logo\Allure_TO.svg" width="50" height="50" alt="Allure_TO"/></a> |

<a id="cases"></a>
## :ballot_box_with_check: Реализованные проверки:

- Параметризованный тест смены категорий клиентов в хэдере
- Переход по кнопке "Войти" на влкадку авторизации "Интернет-банк"
- Заполнение формы обратной связи и переход на шаг2
- Проверка валидации полей на форме обратной связи
- Скачивание pdf-файла из архива документов банка и проверка на соответствие содержимого документа
- Переход на главную страницу сайта по кнопке Logo


## <img alt="Jenkins" height="25" src="logo/Jenkins.svg" width="25"/> Сборка в [Jenkins]
**(https://jenkins.autotests.cloud/job/41-MashaSelyanko_proect1/)**

<p align="center">  
<img src="screen/Allure report - Status.png" alt="Jenkins" width="950"/></a>  
</p>



## :ballot_box_with_check: Параметры сборки в Jenkins:

- browser (браузер, по умолчанию chrome)
- browserVersion (версия браузера, по умолчанию 128.0)
- browserSize (размер окна браузера, по умолчанию 1920x1080)


## Команда для запуска из терминала

Удаленный запуск с использованием Jenkins+Selinoid(требуется логин и пароль):
```bash  
java "-DconfigFile=notifications/config.json" -jar ./notifications/allure-notifications-4.6.1.jar
```


## <img alt="Allure" height="25" src="logo/Allure.svg" width="25"/></a>  <a name="Allure"></a>Allure Report	</a>


## Основная страница отчёта

<p align="center">  
<img src="screen/Allure report.png" src="screen/Allure report.png" width="850">  
</p>  

## Сьюты

<p align="center">  
<img title="Allure Tests" src="screen/Allure report - Suites2.png" width="850">  
</p>

## Graphs

<p align="center">  
<img title="Allure Tests" src="screen/Allure report - Graphs.png" width="850">  
</p>


## <img alt="Allure_TO" height="25" src="logo/Allure_TO.svg" width="25"/> </a>Интеграция с Allure TestOps</a>


## Allure TestOps Запуски

<p align="center">  
<img title="Allure TestOps Dashboard" src="screen/AllureOps - тест - Запуски.png" width="850">  
</p>  

## Авто и Ручные тест-кейсы

<p align="center">  
<img title="Allure Graphics" src="screen/AllureOps - тест - кейсы.png" width="850">   
</p>

## <img alt="Allure" height="25" src="logo/Jira.svg" width="25"/></a> Интеграция с <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-689">Jira</a>

<p align="center">  
<img title="Jira" src="screen/Jira.png" width="">  
</p>

____
## <img alt="Allure" height="25" src="logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="screen/телеграм.png" width="550">  
</p>
