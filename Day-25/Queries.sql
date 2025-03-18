/* Write a SQL query to list employee names and department names for employees with a salary greater than 2000 using INNER JOIN.
 
 
 +-------+------------+
 | ename | dname      |
 +-------+------------+
 */
USE test;
SELECT ename,
    dname
FROM emp
    JOIN dept ON emp.deptno = dept.deptno
WHERE emp.sal > 2000;
/* Write a SQL query to retrieve all employees and their department locations, 
 including those without departments, using LEFT JOIN.
 
 +--------+----------+
 | ename  | location |
 +--------+----------+
 */
USE test;
SELECT ename,
    location
FROM emp
    LEFT JOIN dept ON emp.deptno = dept.deptno;
/* Write a SQL query to list all department numbers, department names and their 
 employee counts, including departments with no employees, using RIGHT JOIN.
 
 +--------+------------+-----------+
 | deptno | dname      | emp_count |
 +--------+------------+-----------+
 */
USE test;
SELECT dept.deptno,
    dept.dname,
    COUNT(emp.ename) AS emp_count
FROM emp
    RIGHT JOIN dept ON dept.deptno = emp.deptno
GROUP BY dept.deptno;
/* Write a SQL query to simulate a FULL OUTER JOIN to list all employees and 
 departments, including unmatched rows.
 
 +-------+--------+--------+------------+
 | empno | ename  | deptno | dname      |
 +-------+--------+--------+------------+
 */
USE test;
SELECT emp.empno,
    emp.ename,
    dept.deptno,
    dept.dname
FROM emp
    LEFT JOIN dept ON emp.deptno = dept.deptno
UNION
SELECT emp.empno,
    emp.ename,
    dept.deptno,
    dept.dname
FROM emp
    RIGHT JOIN dept ON emp.deptno = dept.deptno;
/* Write a SQL query to find employees who are managers of other employees using 
 a self-join.
 print names
 +---------+
 | manager |
 +---------+
 */
USE test;
SELECT e1.ename AS manager
FROM emp e1
    JOIN emp e2 ON e1.mgr = e2.empno;
/* Write a SQL query to generate all possible employee-department combinations 
 using CROSS JOIN.
 
 +--------+------------+
 | ename  | dname      |
 +--------+------------+
 */
USE test;
SELECT e1.ename,
    e1.ename
FROM emp e1
    CROSS JOIN emp e2 ON e1.deptno = e2.deptno;
/* Write a SQL query to list departments with employees earning more than 2500 
 using EXISTS.
 
 +--------+------------+
 | deptno | dname      |
 +--------+------------+
 */
USE test;
/* Write a SQL query to find departments with number of employees earning less 
 than 1000 using NOT EXISTS.
 
 +------------+--------+
 | dname      | deptno |
 +------------+--------+
 */
USE test;
/* Write a SQL query to find managers and the number of employees they manage in
 departments located in 'New York', using the primary key and foreign key 
 constraints.
 
 +--------------+-----------+
 | manager_name | emp_count |
 +--------------+-----------+
 */
USE test;
/* Write a SQL query to list all employees and departments, including those 
 without matches, using a simulated FULL JOIN.
 
 
 +-------+--------+--------+------------+----------+
 | empno | ename  | deptno | dname      | location |
 +-------+--------+--------+------------+----------+
 */
USE test;
/* Write a SQL query to list employee names and department names where the 
 department is in 'Chicago' using INNER JOIN.
 
 +-------+-------+
 | ename | dname |
 +-------+-------+
 */
USE test;
/* 
 Retrieve Department-wise Total Salary and Number of Employees (Using GROUP BY and JOIN)
 
 +------------+---------------+--------------+
 | dname      | num_employees | total_salary |
 +------------+---------------+--------------+
 
 */
USE test;
/* Write a SQL query to list departments with no assigned employees using 
 RIGHT JOIN.
 
 +--------+---------+
 | deptno | dname   |
 +--------+---------+
 
 */
USE test;
/* Write a SQL query to combine employee and department data with duplicates 
 using UNION ALL.
 
 
 +--------+------------+
 | ename  | dname      |
 +--------+------------+
 
 */
USE test;
/* Write a SQL query to list employees and their managers’ names using a LEFT 
 JOIN for employees without managers.
 
 +----------+---------+
 | employee | manager |
 +----------+---------+
 
 */
USE test;
/* Write a SQL query to retrieve average salaries per department using INNER 
 JOIN and GROUP BY.
 
 +--------+------------+-------------+
 | deptno | dname      | avg_salary  |
 +--------+------------+-------------+
 
 */
USE test;
/* Write a SQL query to find departments with more than 3 employees using 
 INNER JOIN and HAVING.
 
 +--------+----------+-----------+
 | deptno | dname    | emp_count |
 +--------+----------+-----------+
 
 */
USE test;
/* Write a SQL query to list employees and departments where the employee’s 
 hire date is after 1980 using INNER JOIN.
 
 +--------+------------+------------+
 | ename  | dname      | hiredate   |
 +--------+------------+------------+
 */
USE test;
/* 
 Find Departments Without Employees (Using LEFT JOIN and NULL Check)
 
 +------------+----------+
 | Department | Location |
 +------------+----------+
 
 
 */
USE test;

/* Write a SQL query to list employee names and department names using an
implicit join, ordered by employee name.

+--------+------------+
| ename  | dname      |
+--------+------------+

*/
USE test; 

