# task_6
10.	(*) Исходные данные соответствуют предыдущей задаче, только теперь необходимо найти все группы студентов имеющих абсолютно одинаковые оценки по предметам (вывести  в виде (вариант набора оценок по предметам –> список студентов, имеющий такой набор оценок).	
Необходимо реализовать класс StudentMarks для хранения оценок одного студента. Данный класс должен быть сравним (Comparable<StudentMarks>). Проще всего внутри класса StudentMarks хранить оценки в виде словаря, а сравнение обеспечить по упорядоченным парам (предмет, оценка) – реализация на основе итераторов по словарям.	
Создаем словарь Map<ФИО, StudentMarks>, заполняем. Далее на основе данных в этом словаре создаем словарь Map<StudentMarks, List<ФИО>>, который и является ответом на задачу.	
Программа может быть консольной.
  
9.	(*) Есть файл с информацией об оценках студентов по предметам. Формат файла – CSV (предмет, ФИО, оценка). Оценка – число или зачет/незачет (т.е. строка). Записи в файле расположены в произвольном порядке. Обеспечить поиск информации обо всех оценках указанного студента.
