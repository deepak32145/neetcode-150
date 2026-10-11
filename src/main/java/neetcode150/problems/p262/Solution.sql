-- 262. Trips and Users  [Hard]
-- https://leetcode.com/problems/trips-and-users/
--
-- Problem:
--   For each day between 2013-10-01 and 2013-10-03, the cancellation rate = cancelled requests /
--   total requests, counting only trips where BOTH the client and the driver are not banned.
--   Round to two decimals.
--
-- Solution - join Trips to Users twice, filter, aggregate by day:
--   1. Join Users as client (c) and as driver (d); keep rows where both have banned = 'No'.
--   2. Restrict request_at to the date window.
--   3. The CASE yields 1.0 for cancelled_by_driver / cancelled_by_client and 0 for completed, so
--      AVG(...) is exactly cancelled / total for the day (1.0 forces decimal, not integer, math).

SELECT t.request_at AS Day,
       ROUND(AVG(CASE WHEN t.status <> 'completed' THEN 1.0 ELSE 0 END), 2) AS `Cancellation Rate`
FROM Trips t
JOIN Users c ON t.client_id = c.users_id AND c.banned = 'No'
JOIN Users d ON t.driver_id = d.users_id AND d.banned = 'No'
WHERE t.request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY t.request_at;
