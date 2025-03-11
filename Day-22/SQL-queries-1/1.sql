/* Retrieve the names and salaries of employees who have the job title "SALESMAN" and earn more than 1500.

Expected Output Columns:
+------------+-----------+
| ename      | sal       |
+------------+-----------+


*/
USE test; 
SELECT emp.ename , emp.sal FROM emp WHERE emp.job = "SALESMAN" AND emp.sal > 1500;