# gRPC клиент-серверное приложение или "Убить босса"

#Цель:
Научиться разрабатывать сетевые приложения с gRPC

#Описание/Пошаговая инструкция выполнения домашнего задания:
Разработать клиент-серверное приложение с применением технологии gRPC.
1. Серверная часть.</br> 
Сервер по запросу клиента генерирует последовательность чисел. 
Запрос от клиента содержит начальное значение (firstValue) и конечное(lastValue). 
Раз в две секунды сервер генерирует новое значение и "стримит" его клиенту: firstValue + 1 firstValue + 2 ... lastValue
2. Клиентская часть.</br> 
Клиент отправляет запрос серверу для получения последовательности чисел от 0 до 30. 
Клиент запускает цикл от 0 до 50. 
Раз в секунду выводит в консоль число (currentValue) по такой формуле: 
currentValue = [currentValue] + [ПОСЛЕДНЕЕ число от сервера] + 1. 
Начальное значение: currentValue = 0. 
Число, полученное от сервера должно учитываться только один раз. 
Обратите внимание, сервер может вернуть несколько чисел, надо взять именно ПОСЛЕДНЕЕ.</br> 

Должно получиться примерно так: </br>
currentValue:1</br>
число от сервера:2</br> 
currentValue:4 <--- число от сервера учитываем только один раз</br>
currentValue:5 <--- тут число от сервера уже не учитывается</br>
число от сервера:3 </br>
currentValue:9 </br>
currentValue:10 </br>
число от сервера:4 </br>
currentValue:15 </br>
currentValue:16</br>
Для коммуникации используйте gRPC. 
Клиент и сервер не обязательно разделять по модулям. 
Можно сделать один модуль с двумя main-классами для клиента и сервера.