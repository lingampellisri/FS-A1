/*Day-3 20 Queries -> 13th March,2025*/

/* 1) Write a SQL query to find the average salary and number of employees per department, 
 ordered by average salary in descending order.
 
 Expected Output Columns:
 ------------------------
 +--------+-------------+----------+
 | deptno | AVG(sal)    | COUNT(*) |
 +--------+-------------+----------+
 
 */
USE test;
SELECT deptno,
    AVG(sal),
    COUNT(*)
FROM emp
GROUP BY deptno
ORDER BY AVG(sal) DESC;


/* 2) Write a SQL query to list the total salary and employee count per job, 
 excluding clerks.
 
 +-----------+----------+----------+
 | job       | SUM(sal) | COUNT(*) |
 +-----------+----------+----------+
 
 */
USE test;
SELECT job,
    SUM(sal),
    COUNT(*)
FROM emp
WHERE job <> "CLERK"
GROUP BY job;


/* 3) Write a SQL query to show the total salary per department where the 
 total salary exceeds 5000, ordered by department number.
 
 +--------+----------+
 | deptno | SUM(sal) |
 +--------+----------+
 
 */
USE test;
SELECT deptno,
    SUM(sal)
FROM emp
GROUP BY deptno
HAVING SUM(sal) > 5000
ORDER BY deptno;


/*  4) Write a SQL query to display the number of employees per job, sorted by job 
 title alphabetically.
 
 +-----------+----------+
 | job       | COUNT(*) |
 +-----------+----------+
 
 */
USE test;
SELECT job,
    COUNT(*)
FROM emp
GROUP BY job
ORDER BY job;


/* 5) Write a SQL query to find the minimum and maximum salaries per department, 
 excluding department 20.
 
 +--------+----------+----------+
 | deptno | MIN(sal) | MAX(sal) |
 +--------+----------+----------+
 
 */
USE test;
SELECT deptno,
    MIN(sal),
    MAX(sal)
FROM emp
WHERE deptno <> 20
GROUP BY deptno;


/* 6) Write a SQL query to list departments with more than 3 employees, ordered 
 by total salary descending.
 
 +--------+----------+----------+
 | deptno | SUM(sal) | COUNT(*) |
 +--------+----------+----------+
 
 */
USE test;
SELECT deptno,
    SUM(sal),
    COUNT(*)
FROM emp
GROUP BY deptno
HAVING COUNT(*) > 3
ORDER BY SUM(sal) DESC;


/* 7) Write a SQL query to show the total commission and average salary per job 
 for jobs with at least 2 employees.
 
 +----------+-----------+-------------+
 | job      | SUM(comm) | AVG(sal)    |
 +----------+-----------+-------------+
 
 */
USE test;
SELECT job,
    SUM(comm),
    AVG(sal)
FROM emp
GROUP BY job
HAVING COUNT(ename) >= 2;


/* 8) Write a SQL query to retrieve employees ordered by hire date and salary 
 in descending order.
 
 +-------+--------+-----------+------+------------+---------+---------+--------+
 | empno | ename  | job       | mgr  | hiredate   | sal     | comm    | deptno |
 +-------+--------+-----------+------+------------+---------+---------+--------+
 
 */
USE test;
SELECT *
FROM emp
ORDER BY hiredate,
    sal DESC;


/* 9) Write a SQL query to find the total salary per department and job
 combination, excluding the president job.
 
 +--------+----------+----------+
 | deptno | job      | SUM(sal) |
 +--------+----------+----------+
 
 */
USE test;
SELECT deptno,
    job,
    SUM(sal)
FROM emp
WHERE job <> "PRESIDENT"
GROUP BY deptno,
    job;


/* 10) Write a SQL query to list departments with an average salary above 2000, 
 ordered by average salary.
 
 +--------+-------------+
 | deptno | AVG(sal)    |
 +--------+-------------+
 
 */
USE test;
SELECT deptno,
    AVG(sal)
FROM emp
GROUP BY deptno
HAVING AVG(sal) > 2000;


/* 11) Write a SQL query to display the employee count and total salary per 
 department for departments with total salary over 4000.
 
 +--------+----------+----------+
 | deptno | COUNT(*) | SUM(sal) |
 +--------+----------+----------+
 
 */
USE test;
SELECT deptno,
    COUNT(*),
    SUM(sal)
FROM emp
GROUP BY deptno
HAVING SUM(sal) > 4000;


/* 12) Write a SQL query to show all employees sorted by department number and then
 by job title alphabetically.
 
 
 +-------+--------+-----------+------+------------+---------+---------+--------+
 | empno | ename  | job       | mgr  | hiredate   | sal     | comm    | deptno |
 +-------+--------+-----------+------+------------+---------+---------+--------+
 
 */
USE test;
SELECT *
FROM emp
ORDER BY deptno,
    job;

/* 13) Write a SQL query to find the average salary per job in departments other
 than 10, ordered by job.
 
 
 +-----------+-------------+
 | job       | AVG(sal)    |
 +-----------+-------------+
 */
USE test;
SELECT job,
    AVG(sal)
FROM emp
WHERE deptno <> 10
GROUP BY job
ORDER BY job;

/* 14) Write a SQL query to list the total salary and employee count per department, 
 excluding employees named 'FORD'.
 
 
 +--------+----------+----------+
 | deptno | SUM(sal) | COUNT(*) |
 +--------+----------+----------+
 */
USE test;
SELECT deptno,
    SUM(sal),
    COUNT(*)
FROM emp
WHERE ename <> "FORD"
GROUP BY deptno;

/* 15) Write a SQL query to retrieve the total salary per job where the total salary 
 is less than 10000, ordered by total salary descending.
 
 +-----------+----------+
 | job       | SUM(sal) |
 +-----------+----------+
 */
USE test;
SELECT job,
    SUM(sal)
FROM emp
GROUP BY job
HAVING SUM(sal) < 10000
ORDER BY SUM(sal) DESC;

/* 16) Write a SQL query to show employees ordered by salary descending and then by 
 employee name ascending.
 
 +-------+--------+-----------+------+------------+---------+---------+--------+
 | empno | ename  | job       | mgr  | hiredate   | sal     | comm    | deptno |
 +-------+--------+-----------+------+------------+---------+---------+--------+
 */
USE test;
SELECT *
FROM emp
ORDER BY sal DESC,
    ename;

/* 17) Write a SQL query to find the maximum salary and employee count per department 
 for departments with more than 2 employees.
 
 +--------+----------+----------+
 | deptno | MAX(sal) | COUNT(*) |
 +--------+----------+----------+
 */
USE test;
SELECT deptno,
    MAX(sal),
    COUNT(*)
FROM emp
GROUP BY deptno
HAVING COUNT(ename) > 2;

/* 18) Write a SQL query to list the total commission per department, excluding 
 department 40, ordered by total commission.
 
 +--------+-----------+
 | deptno | SUM(comm) |
 +--------+-----------+
 */
USE test;
SELECT deptno,
    SUM(comm)
FROM emp
WHERE deptno <> 40
GROUP BY deptno
ORDER BY SUM(comm); 

/* 19) Write a SQL query to display the average salary and job count per department, 
 sorted by department number and average salary descending.
 
 
 +--------+-------------+------------+
 | deptno | AVG(sal)    | COUNT(job) |
 +--------+-------------+------------+
 */
USE test;
SELECT deptno,
    AVG(sal),
    COUNT(job)
FROM emp
GROUP BY deptno
ORDER BY deptno,
    AVG(sal);

/* 20) Write a SQL query to find departments with a total salary greater than 3000,
 excluding salesmen, ordered by total salary.
 
 
 +--------+----------+
 | deptno | SUM(sal) |
 +--------+----------+
 */
USE test;
SELECT deptno,
    SUM(sal)
FROM emp
WHERE job <> "SALESMAN"
GROUP BY deptno
HAVING SUM(sal) > 3000
ORDER BY SUM(sal);