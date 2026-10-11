-- 185. Department Top Three Salaries  [Hard]
-- https://leetcode.com/problems/department-top-three-salaries/
--
-- Problem:
--   Return the employees whose salary is among the top three UNIQUE salaries of their department.
--
-- Solution - DENSE_RANK per department:
--   DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) numbers salaries 1,2,3,...
--   within each department without gaps, and equal salaries share a rank. That is exactly "top three
--   unique salaries": RANK() would skip numbers after ties, ROW_NUMBER() would split ties.
--   Filter rnk <= 3, then join Department to get the department name.
--   (MySQL 8+ / SQL Server / Postgres window-function syntax.)

SELECT d.name   AS Department,
       e.name   AS Employee,
       e.salary AS Salary
FROM (
    SELECT name,
           salary,
           departmentId,
           DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS rnk
    FROM Employee
) e
JOIN Department d ON e.departmentId = d.id
WHERE e.rnk <= 3;
