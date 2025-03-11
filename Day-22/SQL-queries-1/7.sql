/* Retrieve employees who were hired between the years 1995 and 1999.

Expected Output Columns:
+--------+----------+
| ename  | hiredate |
+--------+----------+

*/
USE test;
SELECT ename , hiredate FROM emp WHERE hiredate BETWEEN CAST("95-01-01" AS DATE) AND CAST("00-01-01" AS DATE);