# Вариант 4: Переводы

### Последовательность работы
1. [Парсинг CSV файла](https://github.com/InSkipper/JavaFinalProject#Парсинг-CSV-файла)
2. [Подключение БД SQLite к проекту](https://github.com/InSkipper/JavaFinalProject#Подключение-БД-SQLite-к-проекту-(выполнение-Заданий-1-3))
3. [Создание запросов к БД](https://github.com/InSkipper/JavaFinalProject#Создание-запросов-к-БД)

### Парсинг CSV файла
Для того чтобы перенести данные из CSV файла в подключаемую далее БД, нам потребуется для начала разбить каждую строку этого файла на отдельные POJO объекты, у которого будут соответствующие столбцам CSV файла поля. Эти поля мы заполняем по мере прохождения парсера по файлу и сохраняем объекты в отдельный список.

### Подключение БД SQLite к проекту
Для начала подключим с помощью Maven библиотеку для работы с SQLite: `sqlite-jdbc-3.7.2`. 
Создадим [класс](https://github.com/InSkipper/JavaFinalProject/blob/4000d45bc9a52c9ebaa8b050da3bb5c2f38f9d54/src/main/java/DbConn.java#L6)
и набор методов для работы с БД. Для работы с sqlite был выбран драйвер `jdbc`. 

**Создадим таблицу** и заполним её всеми данными с помощью методов `createDB` и `writeDB`

### Создание запросов к БД (выполнение Заданий 1-3)
**Для первого задания** был создан такой запрос к БД:
``` sql
SELECT Period, SUM(Data_value) AS Data_value, CAST(substr(Period, 1, 4) AS INTEGER) AS Year, UNITS 
FROM transactions
WHERE UNITS = 'Dollars' AND Year=2020
GROUP BY Period ORDER BY Period
```
Здесь требовалось найти 
> ...сумму всех переводов(где это возможно) в Долларах за 2020 год, сгрупированные по месяцам в виде графика.

Для построения графика была использована библиотека [JFreeChart](https://jfree.org/jfreechart/). 

В результате, по полученым данным с помощью [метода](https://github.com/InSkipper/JavaFinalProject/blob/7d6233a4d2e56d02c522c3a9c6102541bd0f699a/src/main/java/Main.java#L27) был построен следующий график:
![График](https://github.com/InSkipper/JavaFinalProject/blob/master/Results/chart.JPEG?raw=true)

**Для второго заданий** был создан следующий запрос:
```mysql
SELECT Period, avg(Data_value) AS Average, COUNT(Data_value) AS Count, UNITS 
FROM transactions 
WHERE UNITS = 'Dollars' 
GROUP BY Period ORDER BY Period
```
Здесь требовалось найти
> ...средний размер перевода в долларах, а так же их количество, за каждый уникальный период.

Ниже прдставлена выборка из [полученного результата](https://github.com/InSkipper/JavaFinalProject/blob/master/Results/Task_2.txt) (он получился слишком объемным)
```
Period = 2003.12
Average = 2173.168
Count = 81
Units = Dollars
------------------------
Period = 2004.01
Average = 1052.4325
Count = 40
Units = Dollars
------------------------
Period = 2004.02
Average = 1038.18
Count = 40
Units = Dollars
------------------------
Period = 2004.03
Average = 3585.7656
Count = 96
Units = Dollars
```

Запрос **для третьей задачи**:
```mysql
SELECT Period, MAX(Data_value) AS Max, MIN(Data_value) AS Min,
CAST(substr(Period, 1, 4) as INTEGER) as Year, UNITS 
FROM transactions 
WHERE UNITS = 'Dollars' AND (Year=2014 OR Year=2016 OR Year=2020) 
GROUP BY Year ORDER BY Year
```
В этой задаче требовалось найти:
> ...максимальный и минимальный перевод за период с 2014, 2016 и 2020 год.

Ниже представлен [результат](https://github.com/InSkipper/JavaFinalProject/blob/master/Results/Task_3.txt) задачи
```
~~~~~~~~
Задача 3
~~~~~~~~
=========================================================================================
Вывести в консоль максимальный и минимальный перевод в долларах за 2014, 2016 и 2020 год.
=========================================================================================
Year = 2014
Min = 49.6
Max = 69247.1
Units = Dollars
------------------------
Year = 2016
Min = 48.8
Max = 76488.6
Units = Dollars
------------------------
Year = 2020
Min = 0.0
Max = 93968.7
Units = Dollars
------------------------
```
