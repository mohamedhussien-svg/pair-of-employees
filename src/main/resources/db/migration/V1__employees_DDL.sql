DROP TABLE if exists employees;
CREATE TABLE employees
(
    id         int AUTO_INCREMENT primary key,
    emp_id     int,
    project_id int,
    date_from  date,
    date_to    date
);

-- INSERT INTO employees (emp_id,project_id, date_from, date_to)
-- VALUES (
--         143,
--         12,
--         PARSEDATETIME('2013-11-01', 'yyyy-mm-dd', 'en'),
--         PARSEDATETIME('2014-05-16', 'yyyy-mm-dd', 'en'));
--
-- INSERT INTO employees (emp_id,project_id, date_from, date_to)
-- VALUES (
--         218,
--         10,
--         PARSEDATETIME('2013-11-01', 'yyyy-mm-dd', 'en'),
--         null);
--
-- INSERT INTO employees (emp_id,project_id, date_from, date_to)
-- VALUES (
--         143,
--         10,
--         PARSEDATETIME('2009-01-01', 'yyyy-mm-dd', 'en'),
--         PARSEDATETIME('2011-04-21', 'yyyy-mm-dd', 'en'));