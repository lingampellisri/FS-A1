/* Get clerks or analysts hired between 1996 and 2000
 
 Expected Output Columns:
 +------+-------+------------+
 | ID   | Name  | StartDate  |
 +------+-------+------------+
 
 */
USE test;
SELECT empno AS ID,
    ename AS Name,
    hiredate as StartDate
FROM emp
WHERE job IN ("CLERK", "ANALYST")
    AND hiredate BETWEEN CAST('96-01-01' AS DATE)
    AND CAST('01-01-01' AS DATE);