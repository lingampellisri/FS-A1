1
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
2
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

3
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
 4
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
5
/* Write a SQL query to find employees who are managers of other employees using 
 a self-join.
 print names
 +---------+
 | manager |
 +---------+
 */
USE test;
select distinct e1.ename as manager from emp e1 join emp e2 on e1.empno=e2.mgr;
6
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

alternate
select e.ename, d.dname from emp e, dept d;
7
/* Write a SQL query to list departments with employees earning more than 2500 
 using EXISTS.
 
 +--------+------------+
 | deptno | dname      |
 +--------+------------+
 */
USE test;
SELECT deptno, dname
FROM dept d
WHERE EXISTS (
    SELECT 1 
    FROM emp e 
    WHERE e.deptno = d.deptno 
    AND e.sal > 2500
);
8
/* Write a SQL query to find departments with number of employees earning less 
 than 1000 using NOT EXISTS.
 
 +------------+--------+
 | dname      | deptno |
 +------------+--------+
 */
USE test;
SELECT d.dname, d.deptno
FROM dept d
WHERE NOT EXISTS (SELECT 1 FROM emp e WHERE e.deptno = d.deptno AND e.sal < 1000);
9
/* Write a SQL query to find managers and the number of employees they manage in
 departments located in 'New York', using the primary key and foreign key 
 constraints.
 
 +--------------+-----------+
 | manager_name | emp_count |
 +--------------+-----------+
 */
USE test;
select e1.ename as manager_name,count(e2.empno) as 
emp_count from emp e1 join emp e2 on e1.empno=e2.mgr join 
dept d on e1.deptno=d.deptno where d.location='New york' group by e1.empno;

10
/* Write a SQL query to list all employees and departments, including those 
 without matches, using a simulated FULL JOIN.
 
 
 +-------+--------+--------+------------+----------+
 | empno | ename  | deptno | dname      | location |
 +-------+--------+--------+------------+----------+
 */
USE test;
SELECT e.empno,e.ename, d.deptno, d.dname, d.location
FROM emp e
LEFT JOIN dept d ON e.deptno = d.deptno

UNION

SELECT e.empno,e.ename, d.deptno, d.dname, d.location
FROM emp e
RIGHT JOIN dept d ON e.deptno = d.deptno
11
/* Write a SQL query to list employee names and department names where the 
 department is in 'Chicago' using INNER JOIN.
 
 +-------+-------+
 | ename | dname |
 +-------+-------+
 */
USE test;
SELECT e.ename, d.dname
FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
WHERE d.location = 'Chicago';
82
/* 
 Retrieve Department-wise Total Salary and Number of Employees (Using GROUP BY and JOIN)
 
 +------------+---------------+--------------+
 | dname      | num_employees | total_salary |
 +------------+---------------+--------------+
 
 */
USE test;
SELECT dname,COUNT(empno) AS num_employees ,SUM(sal) AS 
total_salary FROM emp RIGHT JOIN dept ON emp.deptno=dept.deptno 
GROUP BY dname;
83
/* Write a SQL query to list departments with no assigned employees using 
 RIGHT JOIN.
 
 +--------+---------+
 | deptno | dname   |
 +--------+---------+
 
 */
USE test;
SELECT d.deptno, d.dname
FROM emp e
RIGHT JOIN dept d ON e.deptno = d.deptno
WHERE e.empno IS NULL;
84
/* Write a SQL query to combine employee and department data with duplicates 
 using UNION ALL.
 
 
 +--------+------------+
 | ename  | dname      |
 +--------+------------+
 
 */
 
USE test;
SELECT ename,dname FROM emp LEFT JOIN dept ON emp.deptno=dept.deptno 
UNION ALL
SELECT ename,dname FROM emp RIGHT JOIN dept ON emp.deptno=dept.deptno ;

85
/* Write a SQL query to list employees and their managers’ names using a LEFT 
 JOIN for employees without managers.
 
 +----------+---------+
 | employee | manager |
 +----------+---------+
 
 */
USE test;
SELECT e.ename AS employee, m.ename AS manager
FROM emp e
LEFT JOIN emp m ON e.mgr = m.empno;

86

/* Write a SQL query to retrieve average salaries per department using INNER 
 JOIN and GROUP BY.
 
 +--------+------------+-------------+
 | deptno | dname      | avg_salary  |
 +--------+------------+-------------+
 
 */
USE test;
SELECT d.deptno, d.dname, AVG(e.sal) AS avg_salary
FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
GROUP BY d.deptno, d.dname;

87
/* Write a SQL query to find departments with more than 3 employees using 
 INNER JOIN and HAVING.
 
 +--------+----------+-----------+
 | deptno | dname    | emp_count |
 +--------+----------+-----------+
 
 */
USE test;
SELECT d.deptno, d.dname, COUNT(e.empno) AS emp_count
FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
GROUP BY d.deptno, d.dname
HAVING COUNT(e.empno) > 3;

88
/* Write a SQL query to list employees and departments where the employee’s 
 hire date is after 1980 using INNER JOIN.
 
 +--------+------------+------------+
 | ename  | dname      | hiredate   |
 +--------+------------+------------+
 */
USE test;
SELECT e.ename, d.dname, e.hiredate
FROM emp e
INNER JOIN dept d ON e.deptno = d.deptno
WHERE e.hiredate > '1980-01-01';
89
/* 
 Find Departments Without Employees (Using LEFT JOIN and NULL Check)
 
 +------------+----------+
 | Department | Location |
 +------------+----------+
 
 
 */
USE test;
SELECT d.dname AS Department, d.location
FROM dept d
LEFT JOIN emp e ON d.deptno = e.deptno
WHERE e.empno IS NULL;
90
/* Write a SQL query to list employee names and department names using an
implicit join, ordered by employee name.

+--------+------------+
| ename  | dname      |
+--------+------------+

*/
USE test; 
SELECT e.ename, d.dname
FROM emp e, dept d
WHERE e.deptno = d.deptno
ORDER BY e.ename;
