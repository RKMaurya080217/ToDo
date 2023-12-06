use mydocument;

INSERT INTO todo (task, date, status) VALUES ('Sleep', '05/12/2023', 0);

SELECT * FROM mydocument.todo;

SELECT * FROM todo where status ='1';

SELECT * FROM todo where status ='0';

UPDATE todo SET task = 'Breakfast' WHERE task ='Eat';

DELETE FROM `mydocument`.`todo` WHERE task= 'Sleep';


