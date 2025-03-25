/*
1. BEFORE INSERT Trigger
Write a SQL Trigger to ensure salary is not below the department minimum salary 
before inserting a new employee into the emp table.
*/



DELIMITER //

CREATE TRIGGER before_insert_trigger
BEFORE INSERT 
ON emp FOR EACH ROW 
BEGIN
    DECLARE minSal INT;
    DECLARE error_message VARCHAR(255);

    -- Get minimum salary in the department
    SELECT MIN(sal) INTO minSal 
    FROM emp 
    WHERE deptno = NEW.deptno;

    -- Check only if minSal is NOT NULL
    IF minSal IS NOT NULL AND NEW.sal < minSal THEN
        SET error_message = CONCAT(
            'Warning: Dept :- ', CAST(NEW.deptno AS CHAR), 
            ' | Cannot insert salary less than ', CAST(minSal AS CHAR)
        );

        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = error_message;
    END IF;

END //

DELIMITER ;


/*
2Q) AFTER INSERT Trigger
Write a SQL Trigger to log new employee hires with their department details in the 
emp_audit table after insertion into the emp table.
*/mysql> INSERT INTO emp VALUES (9992, 'TEST2', 'ANALYST', 7566, '23/1/2', 2000, NULL, 20);


DELIMITER //

CREATE TRIGGER after_insert_audit
AFTER INSERT
ON emp FOR EACH ROW 
BEGIN
    INSERT INTO emp_audit
    SET 
        empno = NEW.empno,
        ename = NEW.ename,
        deptno = NEW.deptno,
        changedat = NOW(),
        action = 'After insert, storing new emp logs',
        updated_sal = NEW.sal;
END //

DELIMITER ;

    





