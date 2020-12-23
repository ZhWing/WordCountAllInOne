-- create database and use database
create database WordCount;
use WordCount;

-- create table
create table wordcount(line string);

-- load data
load data loacl inapth '/root/words.txt' into table wordcount;

-- word count
select word, count(*) from (select explode(split(line, ',')) word from wordcount) a group by word;