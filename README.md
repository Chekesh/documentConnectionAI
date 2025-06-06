# RAG Система
## 1. Цель
Целью данного технического задания является разработка сервера для приложения "RAG Система" (Retrieval-Augmented Generation System), а также интеграция этого сервера с приложением и перенос базы данных на более подходящую СУБД для работы с большими объемами данных и обеспечения высокой производительности и релевантности ответов.
## 2. Основания для разработки
Современные приложения, работающие с большими объемами данных и генерацией текстов, требуют эффективных решений для обработки запросов пользователей и предоставления точных ответов. Традиционные подходы, основанные только на локальных данных или прямом доступе к базам, ограничивают масштабируемость, безопасность и возможность интеграции с внешними источниками информации. Разработка "RAG Системы" (Retrieval-Augmented Generation System) с централизованным сервером позволит объединить механизмы извлечения релевантной информации и генерации ответов на основе языковых моделей, обеспечивая высокую производительность, безопасность и гибкость для дальнейшего расширения функциональности.
## 3. Требования к программе или программному изделию
### Функциональные требования:
1. Обработка запросов;
2. Извлечение текста из документа;
3.  Получение текста;
4.   Фрагментация текста;
5.    Отправка фрагментов в векторную БД;
6. Информирование о результатах;
7.  Определение вопроса;
8.   Поиск данных схожих по теме в векторной БД;
9.    Группировка данных в запросе;
10. Отправка запроса в ИИ;
11.  Получение ответа от ИИ;
12.   Проверка ответа;
13.    Отправка ответа пользователю;


### Нефункциональные требования:
1.	Стабильная работа приложения во время использования
2.	Высокая скорость обработки запросов (ответ не более чем за 5 секунд при стандартной нагрузке).
3.	Программа не должна занимать больше 512 Мб на носителе
4.	Программа должна потреблять не более 512 Мб оперативной памяти
5.	Безопасность
6.	Надежность


## Использованные источники:
- IntelliJ IDEA
- Pinecone
- Java
- Spring Framework
- Postman

## Разработчик:
Разработчиком приложения является Чекушин Никита, студент группы 21ИТ17 (ИТ - колледжа)

## Установка приложения:
Импортируете данный проект к свою среду разработки.
Вставте сввой ключ от API в ap;ication.properti
Открываем проект и заходим класс documentConnectionAIApplication , запускаем.
![Картинка](https://github.com/Chekesh/ZasechkaServer/blob/master/photo/%D0%97%D0%B0%D0%BF%D1%83%D1%81%D0%BA%20%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F%20%D0%94%D0%98%D0%9F%D0%9B%D0%9E%D0%9C.png)

Наш сервер готов! 

# Документация:

+ [ТЗ](https://github.com/Chekesh/documentConnectionAI/wiki/1.-%D0%A2%D0%97)
+ [Спецификация](https://github.com/Chekesh/documentConnectionAI/wiki/2.-%D0%A1%D0%BF%D0%B5%D1%86%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%86%D0%B8%D1%8F)
+ [Функциональные требования](https://github.com/Chekesh/documentConnectionAI/wiki/3.-%D0%A4%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D0%BE%D0%BD%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5-%D1%82%D1%80%D0%B5%D0%B1%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F.)
+ [Нефункцианальные требования](https://github.com/Chekesh/documentConnectionAI/wiki/4.-%D0%9D%D0%B5%D1%84%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D0%BE%D0%BD%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5-%D1%82%D1%80%D0%B5%D0%B1%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F.)
+ [Уровни интеграции](https://github.com/Chekesh/documentConnectionAI/wiki/5.-%D0%A3%D1%80%D0%BE%D0%B2%D0%BD%D0%B8-%D0%B8%D0%BD%D1%82%D0%B5%D0%B3%D1%80%D0%B0%D1%86%D0%B8%D0%B8)
+ [Логирование](https://github.com/Chekesh/documentConnectionAI/wiki/6-%D0%9B%D0%BE%D0%B3%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5)
+ [Тестирование](https://github.com/Chekesh/documentConnectionAI/wiki/7-%D0%A2%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5)
+ [Руководство пользователя](https://github.com/Chekesh/documentConnectionAI/wiki/8.-%D0%A0%D1%83%D0%BA%D0%BE%D0%B2%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%BE-%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8F)
