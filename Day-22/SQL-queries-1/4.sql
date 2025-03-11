/* Get the employee names and their commission details 
where the job is "CLERK" or the commission is more than 1000.

Expected Output Columns:
+--------+----------+---------+
| ename  | job      | comm    |
+--------+----------+---------+

*/
USE test;
SELECT emp.ename , emp.job , emp.comm FROM emp 
WHERE emp.job = "CLERK" OR emp.comm > 1000;