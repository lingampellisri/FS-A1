/*Youngest students */
SELECT T1.name, T1.age , T2.address FROM T2 JOIN T1 ON T1.roll = T2.roll ORDER BY T1.age;
+---------+------+------------+
| name    | age  | address    |
+---------+------+------------+
| sneha   |   14 | chennai    |
| avi     |   14 | vijayawada |
| nikhi   |   16 | hyd        |
| surabhi |   18 | nalgonda   |
| akhi    |   20 | hyd        |
| sam     |   20 | karimnagar |
+---------+------+------------+
6 rows in set (0.00 sec)


/*Query 1 Student Report */
select * FROM T1 JOIN T2 ON T1.roll = T2.roll JOIN T3 ON T3.roll = T1.roll JOIN T4 ON T4.year = T3.year;
+------+---------+------+------+------------+------+------+------+--------+
| roll | name    | age  | roll | address    | roll | year | year | hostel |
+------+---------+------+------+------------+------+------+------+--------+
|    4 | sneha   |   14 |    4 | chennai    |    4 | II   | II   | H1     |
|    5 | avi     |   14 |    5 | vijayawada |    5 | II   | II   | H1     |
|    3 | nikhi   |   16 |    3 | hyd        |    3 | IV   | IV   | H2     |
|    2 | surabhi |   18 |    2 | nalgonda   |    2 | V    | V    | H3     |
|    1 | akhi    |   20 |    1 | hyd        |    1 | XII  | XII  | H4     |
|    6 | sam     |   20 |    6 | karimnagar |    6 | XII  | XII  | H4     |
+------+---------+------+------+------------+------+------+------+--------+
6 rows in set (0.00 sec)

/*
If there is no where condition it is a cross join
select * from t1 , t2; // this is a cross join (m x n)
select * from t1 , t2 on t1.roll = t2.roll; // this is a inner join (min(m,n))
select * from t1 left join t2 on t1.roll = t2.roll; // this is a left join (m)
select * from t1 right join t2 on t1.roll = t2.roll; // this is a right join (n)
select * from t1 full join t2 on t1.roll = t2.roll; // this is a full join (m+n)
self join 
select * from t1 t1 , t1 t2 where t1.roll = t2.roll; // this is a self join
*/

 select e2.empno , e2.mgr ,e1.empno , e1.mgr from emp e1 , emp e2 where e1.empno = e2.mgr;
+-------+------+-------+------+
| empno | mgr  | empno | mgr  |
+-------+------+-------+------+
|  7369 | 7902 |  7902 | 7566 |
|  7499 | 7698 |  7698 | 7839 |
|  7521 | 7698 |  7698 | 7839 |
|  7566 | 7839 |  7839 | NULL |
|  7654 | 7698 |  7698 | 7839 |
|  7698 | 7839 |  7839 | NULL |
|  7782 | 7839 |  7839 | NULL |
|  7788 | 7566 |  7566 | 7839 |
|  7844 | 7698 |  7698 | 7839 |
|  7876 | 7788 |  7788 | 7566 |
|  7900 | 7698 |  7698 | 7839 |
|  7902 | 7566 |  7566 | 7839 |
|  7934 | 7782 |  7782 | 7839 |
+-------+------+-------+------+
13 rows in set (0.00 sec)

In every job , get the emp maximum ID  
Largest empno should be retained for each job
select job , max(empno) from emp group by job;