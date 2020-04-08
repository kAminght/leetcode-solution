# 第二高的薪水
SELECT(
          SELECT DISTINCT `Salary`
          FROM Employee
          ORDER BY `Salary` DESC
          LIMIT 1 OFFSET 1
      ) AS `SecondHighestSalary`;

# 员工薪水高于经理
SELECT e.Name AS `Employee`
FROM `Employee` e
         INNER JOIN `Employee` m
                    ON e.ManagerId = m.Id
WHERE e.Salary > m.Salary;

# 查找特定重复次数的数据
SELECT `Email`
FROM `Person`
GROUP BY `Email`
HAVING COUNT(*) > 1;

# 查找没关系，不存在的数据
select customers.name as 'Customers'
from customers
where customers.id not in
      (
          select customerid
          from orders
      );

# 删除重复的电子邮箱，MySQL删除表数据时不能执行子查询
DELETE p1
FROM Person p1,
     Person p2
WHERE p1.Email = p2.Email
  AND p1.Id > p2.Id;

# 日期函数和多表联结综合运用
SELECT t.Id AS `Id`
FROM Weather t
         INNER JOIN Weather ys
                    ON DATEDIFF(t.RecordDate, ys.RecordDate) = 1
WHERE t.Temperature > ys.Temperature;

# 聚合函数的DISTINCT子句
SELECT `class`
FROM `courses`
GROUP BY `class`
HAVING COUNT(DISTINCT `student`) >= 5;

# case when then表达式的灵活运用
UPDATE `salary`
SET `sex`=
        IF(`sex` = 'm', 'f', 'm');

# GROUP BY分组，聚合函数和IF函数综合运用实现给记录分级
SELECT `id`,
       MAX(
               IF(month = 'Jan', revenue, null)
           ) AS `Jan_Revenue`,
       MAX(
               IF(month = 'Feb', revenue, null)
           ) AS `Feb_Revenue`,
       MAX(
               IF(month = 'Mar', revenue, null)
           ) AS `Mar_Revenue`,
       MAX(
               IF(month = 'Apr', revenue, null)
           ) AS `Apr_Revenue`,
       MAX(
               IF(month = 'May', revenue, null)
           ) AS `May_Revenue`,
       MAX(
               IF(month = 'Jun', revenue, null)
           ) AS `Jun_Revenue`,
       MAX(
               IF(month = 'Jul', revenue, null)
           ) AS `Jul_Revenue`,
       MAX(
               IF(month = 'Aug', revenue, null)
           ) AS `Aug_Revenue`,
       MAX(
               IF(month = 'Sep', revenue, null)
           ) AS `Sep_Revenue`,
       MAX(
               IF(month = 'Oct', revenue, null)
           ) AS `Oct_Revenue`,
       MAX(
               IF(month = 'Nov', revenue, null)
           ) AS `Nov_Revenue`,
       MAX(
               IF(month = 'Dec', revenue, null)
           ) AS `Dec_Revenue`
FROM `Department`
GROUP BY `id`;

# 查询第N高的薪水
SELECT(
          SELECT DISTINCT `Salary`
          FROM `Employee`
          ORDER BY `Salary`
          LIMIT 1 OFFSET n
      );

# 针对MYSQL5.7版本的SQL模式only_full_group_by的解决方案
SELECT d.`Name` AS `Department`, e.`Name` AS `Employee`, e.`Salary` AS `Salary`
FROM Employee e
         INNER JOIN Department d
         INNER JOIN (
    SELECT DepartmentId, MAX(Salary) AS `Salary`
    FROM Employee
    GROUP BY DepartmentId
) t
                    ON e.DepartmentId = d.Id AND e.DepartmentId = t.DepartmentId AND
                       e.Salary = t.Salary;

# 使用INNER JOIN和联结条件去除不符合条件的数据
SELECT DISTINCT a.`Num` AS `ConsecutiveNums`
FROM `Logs` a
         INNER JOIN `Logs` b
         INNER JOIN `Logs` c
                    ON a.`Num` = b.`Num` AND b.`Num` = c.`Num` AND a.`Id` = b.`Id` + 1 AND
                       b.`Id` = c.`Id` + 1;

# 活用自定义变量和表连接生成行号，INNER JOIN内连接扩充表字段
SELECT s.Score AS Score, t.Rank AS `Rank`
FROM Scores s
         INNER JOIN (
    SELECT Score, (@auto := @auto + 1) AS `Rank`
    FROM (SELECT DISTINCT Score
          FROM Scores
          ORDER BY Score DESC) a
             INNER JOIN (SELECT @auto := 0) c
) t
                    ON s.Score = t.Score
ORDER BY s.Score
    DESC;

# 灵活使用表连接操作拼接基于一定规则的表记录，使用IF函数作为列字段的路由，使用IFNULL函数为null值字段设默认值
# 注意，多个表JOIN操作，把每次JOIN操作的关联条件分开写
SELECT tmp.id AS id, IF(tmp.id % 2 = 1, `odd`, `even`) AS student
FROM (
         SELECT a.id                         AS id,
                a.student,
                IFNULL(b.student, a.student) AS `odd`,
                IFNULL(c.student, a.student) AS `even`
         FROM seat a
                  LEFT JOIN seat b ON a.id = b.id - 1
                  LEFT JOIN seat c ON a.id = c.id + 1
         ORDER BY a.id
     ) AS tmp;
