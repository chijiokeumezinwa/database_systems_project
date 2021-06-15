
declare
type_discount number;
type_discount_used float(2);
do_they_want_package number;
package_cost number := 0;
num_of_ops number;
year number;
v_list_price number;
price_copy number;
v_list_price_2 number;
v_name   varchar(40);
v_name_two   varchar(40);
v_name_whole   varchar(40);
v_street_number   number;
v_street_name varchar(100);
v_apt_number  number;
v_city    varchar(100);
v_state   varchar(100);
v_zip_code    number;
v_country   varchar(200);
v_planet   varchar(200);
v_email   varchar(50);
email_p varchar(50);
whole_email varchar(50);
v_location varchar(50);
v_date_REPAIR DATE;
v_cost number;
v_price_paid float(2);
v_new_price number;
v_old_price number;
credit_debit number;
vehicle_id number;
used_vehicle_id number;
v_m_id number;
recall_num number;
recall_num_2 number;
v_o_name varchar(50);
v_description varchar(50);
p_id number;
num_loop number;
 op_name varchar(50);
 op_price float(2);
v_id1  number := 1 ;
v_id2 number := 0;
ex_op_name varchar(50);
ex_pr_name varchar(50);
m_name varchar(50);
package_id number :=1;
package_id_rand number;
b varchar(40);
c number;
a number;
d number;
e number;
f number;
t_loc   varchar(50);
t_mo  varchar(30);
nameofveh   VARCHAR(40 BYTE);
nameofveh_used   VARCHAR(40 BYTE);
nameofmo    VARCHAR(30 BYTE);
nameofmo_used    VARCHAR(30 BYTE);
e_used number;
l_num number:=0;
op1Cost number;
op2Cost number;
op3Cost number;
op4Cost number;
op5Cost number;
begin


--inserts into service_location
insert into service_location 
(location,model_type)
values('Southside Bethlehem','Model K');
insert into service_location 
(location,model_type)
values('Pacific Overlook','Model U');
insert into service_location 
(location,model_type)
values('Pacific Overlook','Model K');
insert into service_location 
(location,model_type)
values('Moon Exploration','Model M');
insert into service_location 
(location,model_type)
values('Mars Bars Center','Model S');

dbms_random.seed (to_number(to_char(sysdate, 'sssss')));

SELECT round(dbms_random.value(0,100)) rnum 
    into recall_num
    FROM dual;
    
insert into recall
(num_of_times)
values(recall_num);

select LOCATION,MODEL_TYPE
into t_loc,t_mo
from(select LOCATION,MODEL_TYPE
    from SERVICE_LOCATION
    ORDER BY dbms_random.value)
    WHERE rownum = 1;

insert into S_L_RECALL
    (LOCATION, NUM_OF_TIMES, MODEL_TYPE)
values(t_loc,recall_num,t_mo);

insert into packages
(package_id,tot_price_package,package_name)
values(package_id, 6500,'Superman Package');
insert into packages
(package_id,tot_price_package,package_name)
values(package_id+1,1800,'Amenities');
insert into packages
(package_id,tot_price_package,package_name)
values(package_id+2,7000,'Luxury');

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Sonic';
SELECT round(dbms_random.value(50000,60000),2)  
    into c
    FROM dual;

insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2020,'Model K',c);--sonic 2020

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Sonic';
SELECT round(dbms_random.value(40000,50000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2019,'Model K',c);--sonic 2019

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Pinata';
SELECT round(dbms_random.value(50000,55000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2020,'Model K',c);--pinata 2020

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Pinata';
SELECT round(dbms_random.value(40000,45000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2019,'Model K',c);--pinata 2019

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Fiesta';
SELECT round(dbms_random.value(40000,45000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2020,'Model K',c);--fiesta 2020

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Fiesta';
SELECT round(dbms_random.value(30000,40000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2019,'Model K',c);--fiesta 2019

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Yaris';
SELECT round(dbms_random.value(40000,45000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2020,'Model K',c);--yaris 2020

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Yaris';
SELECT round(dbms_random.value(35000,40000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2019,'Model K',c);--yaris 2019

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Verse';
SELECT round(dbms_random.value(40000,45000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2020,'Model K',c);--verse 2020

SELECT round(dbms_random.value(1,100)) rnum 
    into v_m_id
    FROM dual;
select column1
into b
from modelk where column1='Verse';
SELECT round(dbms_random.value(35000,40000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id,b,2019,'Model K',c);--verse 2019

SELECT round(dbms_random.value(100,10000)) rnum 
    into v_m_id
    FROM dual;
SELECT round(dbms_random.value(1000,1005)) rnum 
    into d
    FROM dual;
select column1
into b
from modelu where column1='Poseidon';
SELECT round(dbms_random.value(40000,50000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id+d,b, 2020,'Model U',c);

SELECT round(dbms_random.value(100,10000)) rnum 
    into v_m_id
    FROM dual;
SELECT round(dbms_random.value(0,200)) rnum 
    into d
    FROM dual;
select column1
into b
from modelu where column1='Cameron';
SELECT round(dbms_random.value(40000,50000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id+d,b, 2020,'Model U',c);

SELECT round(dbms_random.value(100,10000)) rnum 
    into v_m_id
    FROM dual;
SELECT round(dbms_random.value(0,200)) rnum 
    into d
    FROM dual;
select column1
into b
from modelu where column1='Bluest Whale';
SELECT round(dbms_random.value(40000,50000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id+d,b, 2020,'Model U',c);

l_num :=0;


SELECT round(dbms_random.value(10000,10000000)) rnum 
    into v_m_id
    FROM dual;
SELECT round(dbms_random.value(0,200)) rnum 
    into d
    FROM dual;
select column1
into b
from modelm where column1='2001 Hal';
SELECT round(dbms_random.value(90000,100000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id+d,b, 2020,'Model M',c);

SELECT round(dbms_random.value(10000,10000000)) rnum 
    into v_m_id
    FROM dual;
SELECT round(dbms_random.value(0,200)) rnum 
    into d
    FROM dual;
select column1
into b
from modelm where column1='Trekker';
SELECT round(dbms_random.value(90000,100000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id+d,b, 2020,'Model M',c);

SELECT round(dbms_random.value(10000,10000000)) rnum 
    into v_m_id
    FROM dual;
SELECT round(dbms_random.value(0,200)) rnum 
    into d
    FROM dual;
select column1
into b
from modelm where column1='Infinity';
SELECT round(dbms_random.value(90000,100000),2)  
    into c
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)
values(v_m_id+d,b, 2020,'Model M',c);


l_num :=0;


SELECT round(dbms_random.value(10000000,1000000000)) rnum 
    into v_m_id
    FROM dual;
insert into model
    (m_id,modelname,year,model_type,list_price)    
values(v_m_id+3000,'Armstrong', 2019,'Model S',200000);
--first populate the table listops


insert into options
select * from listops;

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'CD Changer/stacker');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = '4 Wheel drive');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Air Conditioning');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Aluminum/Alloy Wheels');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Automatic Transmission');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Bed Liner');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Captains chairs - 2nd row');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Cruise Control');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Dual Air Conditioning');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Dual Power Seats');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name); 

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Rear DVD/TV');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Hard Top (convertible)');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Heated Seats');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Leather Seats');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Luggage / Roof  Rack');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Specialty Stereo System');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Manual Transmission');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Navigation system');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Power Door Locks');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Power Seat (Drivers)');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Power Steering');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Power Sunroof');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Power Windows');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Running Boards');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Satellite Radio');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Snow / Plow Package');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Remote Starter');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);


select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Theft Deterrent / Alarm');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,2,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Theft Recovery System');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Third Row Seats');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Tilt Wheel');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,3,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Tonneau Cover / Bed Cover');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Towing / Trailer Package');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);

select  options_name,list_price
into ex_op_name,ex_pr_name
from(select options_name,list_price from options where options_name = 'Turbo Diesel');

insert into options_in_packages (OPTION_NAME,PACKAGE_ID, LIST_PRICE)
values(ex_op_name,1,ex_pr_name);



while (v_id1 < 1000)
loop
    --name
    select name 
    into v_name
    from(select name
        from student
        ORDER BY dbms_random.value)
        WHERE rownum = 1;
    --dbms_output.put_line(v_name);
    
    select name 
    into v_name_two
    from(select name
        from student
        ORDER BY dbms_random.value)
        WHERE rownum = 1;
    
    v_name_whole := v_name || ' ' || v_name_two;
    
    --street number
    SELECT round(dbms_random.value(1,500)) rnum 
        into v_street_number
        FROM dual;
    
    
    --First bring in the name of street
    select nameofstreet
    into v_street_name
    from(select nameofstreet
        from street_names
        ORDER BY dbms_random.value
        )
        where rownum =1 ;
    
    
    --Bring in the city
    select thecitynames
    into v_city
    from(select thecitynames
        from namesofcities
        order by dbms_random.value
        )
        where rownum=1;
    
    
    
    --bring in the state
    select statename
    into v_state
    from(select statename
        from namesofstates
        order by dbms_random.value
        )
        where rownum=1;
    
    
    
    --random zip code 
    select round(dbms_random.value(10000,99999))rnum 
    into v_zip_code 
    from dual;
    
    
    
    --email
    
    
    v_email := v_name || '_' || v_name_two;
    select listofemails
    into email_p
    from(select listofemails
        from listemails
        order by dbms_random.value
        )
        where rownum=1;
    
    whole_email := v_email || email_p;
    
    
    --credit card number
    select round(dbms_random.value(1000000000000000,9999999999999999))rnum 
    INTO credit_debit
    from dual;
    
    
    select LOCATIONNAME
    into v_location
    from(select LOCATIONNAME
        from locations
        order by dbms_random.value
        )
        where rownum=1;
    
    select to_date('2019-01-01', 'yyyy-mm-dd')+dbms_random.value(1,1000) 
    into v_date_repair
    from dual;
    
    select round(dbms_random.value(50.00, 9999.99),2) 
    into v_cost
    from dual;
    
    
    
    --vehicle id
    select round(dbms_random.value(10000000000000000000,99999999999999999999))rnum 
    INTO vehicle_id
    from dual;
    
    
    SELECT round(dbms_random.value(0,1)) rnum 
        into f
        FROM dual;
    dbms_output.put_line(f);
    IF f = 0 then
         
        --apt number
        SELECT round(dbms_random.value(1,500)) rnum 
        into v_apt_number
        FROM dual;
        
        --INSERT INTO CUSTOMER
        insert into customer
        (name,ID,street_number,street_name,apt_number,city,state,zip_code,email_c,country,planet)
        VALUES(v_name_whole,v_id1,v_street_number,v_street_name,v_apt_number,v_city,v_state,v_zip_code,whole_email,'USA','Earth');
    ELSIF f = 1 THEN
        v_apt_number := NULL;
        --INSERT INTO CUSTOMER
        insert into customer
        (name,ID,street_number,street_name,apt_number,city,state,zip_code,email_c,country,planet)
        VALUES(v_name_whole,v_id1,v_street_number,v_street_name,v_apt_number,v_city,v_state,v_zip_code,whole_email,'USA','Earth');
    END IF;
    
    
    insert into customer_payment
        (name,credit_debit_number,id,email_c)
    values(v_name_whole,credit_debit,v_id1,whole_email);
    
    
    insert into vehicle
        (v_id)
    values(vehicle_id);
    
    SELECT round(dbms_random.value(0,1)) rnum 
        into f
        FROM dual;
    IF f = 0 then
    --repairs
    insert into repairs
        (location,date_REPAIR,cost)
    values(v_location,v_date_repair,v_cost);
    
    insert into customer_repair
        (name,id,email_c,location,date_repair,cost)
    values(v_name_whole,v_id1,whole_email,v_location,v_date_repair,v_cost);
    end if;
    
    select M_ID,modelname,MODEL_TYPE
    into e,nameofveh,nameofmo
    from(select M_ID,modelname,MODEL_TYPE
        from model
        ORDER BY dbms_random.value)
        WHERE rownum = 1;
    
    --I should have to randomize if a person wants a package or just a list of options to choose from.
    SELECT round(dbms_random.value (1,2)) rnum 
    into do_they_want_package 
    FROM dual;  
    
    
    
    
    if do_they_want_package =1 and (nameofmo = 'Model K' or nameofmo = 'Model M') then
        SELECT distinct round(dbms_random.value(1,3)) rnum 
        into package_id_rand
        FROM dual;
        
        insert into v_packages
        values(vehicle_id,package_id_rand);
        
        select tot_price_package into package_cost from packages where package_id = package_id_rand;
        --dbms_output.put_line(package_cost);
    else 
        --package_id_rand := 2;--choose amenities package
        
        insert into v_packages
        values(vehicle_id,2);
        select tot_price_package into package_cost from packages where package_id = 2;
        
    end if;

    --
    
    --first get the list price 
    select list_price
    into v_list_price
    from(select list_price
        from model
        where model_type = nameofmo and modelname = nameofveh and m_id = e );
    v_new_price := v_list_price;
    
    --then i needed to randomly pick a discount.
    SELECT round(dbms_random.value (1,6)) rnum 
    into type_discount 
    FROM dual; 
    
    if type_discount = 1 THEN
        v_new_price := v_list_price;  
    ELSIF type_discount = 2 THEN
        v_list_price_2 := v_new_price - (v_new_price* (0.05));
        v_list_price_2 := round(v_list_price_2,2);
        v_new_price :=  v_list_price_2;
    ELSIF type_discount = 3 THEN
        v_list_price_2 := v_new_price - (v_new_price* (0.10));
        v_list_price_2 := round(v_list_price_2,2);
        v_new_price :=  v_list_price_2;
    ELSIF type_discount = 4 THEN
        v_list_price_2 := v_new_price - (v_new_price* (0.15));
        v_list_price_2 := round(v_list_price_2,2);
        v_new_price :=  v_list_price_2;
    ELSIF type_discount = 5 THEN
        v_list_price_2 := v_new_price - (v_new_price* (0.20));
        v_list_price_2 := round(v_list_price_2,2);
        v_new_price := v_list_price_2;
    ELSIF type_discount = 6 THEN
        v_list_price_2 := v_new_price - (v_new_price* (0.25));
        v_list_price_2 := round(v_list_price_2,2);
        v_new_price :=  v_list_price_2;
    END IF;
    
    if do_they_want_package =1 then
        v_new_price := v_new_price + package_cost;
        
    ELSE    v_new_price := v_new_price + package_cost;
    end if;
    
    
    insert into customer_buys_new
        (name,id,v_id,email_c,price_paid)
    VALUES(v_name_whole,v_id1,vehicle_id,whole_email,v_new_price);
    
    
    
    
    insert into v_model
        (V_ID, M_ID, modelname)
        values(vehicle_id,e,nameofveh);
      
        
    IF nameofmo = 'Model K' THEN
        insert into new_vehicle
            (V_ID, LOCATION, MODEL_TYPE)
            values(vehicle_id,'Southside Bethlehem',nameofmo);
        
    ELSIF   nameofmo = 'Model K' or  nameofmo = 'Model U' THEN
        insert into new_vehicle
            (V_ID, LOCATION, MODEL_TYPE)
            values(vehicle_id,'Pacific Overlook',nameofmo);
    
    
    ELSIF   nameofmo = 'Model S'    THEN
        insert into new_vehicle
            (V_ID, LOCATION, MODEL_TYPE)
            values(vehicle_id,'Mars Bars Center',nameofmo);
        
    
    ELSIF   nameofmo = 'Model M'    THEN
        insert into new_vehicle
            (V_ID, LOCATION, MODEL_TYPE)
            values(vehicle_id,'Moon Exploration',nameofmo);
    
    END IF;
    
    
    v_id1 := v_id1 +1;
end loop;

--
v_id1 := 1;
while(v_id1 < 200)
loop
    --v_id1 := l_num;


    --this is for used cars.
    --name
    select name 
    into v_name
    from(select name
        from student
        ORDER BY dbms_random.value)
        WHERE rownum = 1;
    --dbms_output.put_line(v_name);
    
    select name 
    into v_name_two
    from(select name
        from student
        ORDER BY dbms_random.value)
        WHERE rownum = 1;
    --dbms_output.put_line(v_name_two);
    v_name_whole := v_name || ' ' || v_name_two;
    
        --street number
    SELECT round(dbms_random.value(1,500)) rnum 
        into v_street_number
        FROM dual;
    --dbms_output.put_line(v_street_number);
    
    
    --First bring in the name of street
    select nameofstreet
    into v_street_name
    from(select nameofstreet
        from street_names
        ORDER BY dbms_random.value
        )
        where rownum =1 ;
    
    
    --Bring in the city
    select thecitynames
    into v_city
    from(select thecitynames
        from namesofcities
        order by dbms_random.value
        )
        where rownum=1;
    --dbms_output.put_line(v_city);
    
    
    --bring in the state
    select statename
    into v_state
    from(select statename
        from namesofstates
        order by dbms_random.value
        )
        where rownum=1;
    --dbms_output.put_line(v_state);
    
    
    --random zip code 
    select round(dbms_random.value(10000,99999))rnum 
    into v_zip_code 
    from dual;
    --dbms_output.put_line(v_zip_code);
    
    
    --email
    
    
    v_email := v_name || '_' || v_name_two;
    select listofemails
    into email_p
    from(select listofemails
        from listemails
        order by dbms_random.value
        )
        where rownum=1;
    
    whole_email := v_email || email_p;
    v_id2 := v_id1+5001;
    
    SELECT round(dbms_random.value(0,1)) rnum 
        into f
        FROM dual;
    IF f = 0 then
         
        --apt number
        SELECT round(dbms_random.value(1,500)) rnum 
        into v_apt_number
        FROM dual;
        
        --INSERT INTO CUSTOMER
        insert into customer
        (name,ID,street_number,street_name,apt_number,city,state,zip_code,email_c,country,planet)
        VALUES(v_name_whole,v_id2,v_street_number,v_street_name,v_apt_number,v_city,v_state,v_zip_code,whole_email,'USA','Earth');
    ELSIF f = 1 THEN
        v_apt_number := NULL;
        --INSERT INTO CUSTOMER
        insert into customer
        (name,ID,street_number,street_name,apt_number,city,state,zip_code,email_c,country,planet)
        VALUES(v_name_whole,v_id2,v_street_number,v_street_name,v_apt_number,v_city,v_state,v_zip_code,whole_email,'USA','Earth');
    END IF;
    
    select round(dbms_random.value(100000000000000000000,999999999999999999999))rnum 
    INTO used_vehicle_id
    from dual;
    
    

    insert into vehicleused
    (v_id)
    values(used_vehicle_id);
    select M_ID,modelname,MODEL_TYPE
    into e_used,nameofveh_used,nameofmo_used
    from(select M_ID,modelname,MODEL_TYPE
        from model
        ORDER BY dbms_random.value)
    WHERE rownum = 1;
    
    --credit card number
    select round(dbms_random.value(1000000000000000,9999999999999999))rnum 
    INTO credit_debit
    from dual;

    insert into customer_payment_used
    (name,credit_debit_number,id,email_c)
    values(v_name_whole,credit_debit,v_id2,whole_email);
    
    
--    select trunc(dbms_random.value(0, 9999),2) into type_discount_used from dual;
--    
    --first get the list price 
    select list_price
    into v_list_price
    from(select list_price
        from model
        where model_type = nameofmo_used and modelname = nameofveh_used and m_id = e_used );
    v_new_price := v_list_price;

   select trunc(dbms_random.value(7000, 20000),2) into type_discount_used from dual;
    v_old_price := v_new_price;
    v_new_price := v_new_price - type_discount_used;
    

    --what about packages and options?
    SELECT round(dbms_random.value (1,2)) rnum 
    into do_they_want_package 
    FROM dual;  
    
    if do_they_want_package =1 and (nameofmo = 'Model K' or nameofmo = 'Model M') then
        SELECT distinct round(dbms_random.value(1,3)) rnum 
        into package_id_rand
        FROM dual;
        
        insert into v_packages_used
        values(used_vehicle_id,package_id_rand);
        
        select tot_price_package into package_cost from packages where package_id = package_id_rand;
        --dbms_output.put_line(package_cost);
--        v_new_price := v_new_price + package_cost;
    
    
    else 
        package_id_rand := 2;--choose amenities package
        
        insert into v_packages_used
        values(used_vehicle_id,package_id_rand);
        select tot_price_package into package_cost from packages where package_id = package_id_rand;
        
        op1cost :=0;
    end if;
    
    if do_they_want_package =1 then
    v_new_price := v_new_price + package_cost;
    
    ELSE    v_new_price := v_new_price + package_cost;
    end if;

    
    
    
    insert into customer_buys_used
    (name,id,v_id,email_c,price_paid,new_price,old_price)
    values(v_name_whole,v_id2,used_vehicle_id,whole_email,v_new_price,v_new_price,v_old_price);
    
    IF nameofmo_used = 'Model K' THEN
    
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Southside Bethlehem',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Southside Bethlehem',nameofmo_used);
    ELSIF nameofmo_used = 'Model U' THEN
        
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Pacific Overlook',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Pacific Overlook',nameofmo_used);
    ELSIF   nameofmo_used = 'Model S'    THEN
        
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Mars Bars Center',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Mars Bars Center',nameofmo_used);
    ELSIF   nameofmo_used = 'Model M'    THEN
        
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Moon Exploration',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Moon Exploration',nameofmo_used);
    END IF;    
        
    
    insert into v_model_used
    (V_ID, M_ID, modelname)
    values(used_vehicle_id,e_used,nameofveh_used); 
v_id1 := v_id1 +1;
end loop;


--lets put 300 used vehicles for sale

v_id1 := 1;
while(v_id1 < 50)
loop
select round(dbms_random.value(1000000000000000000000,9999999999999999999999))rnum 
        INTO used_vehicle_id
        from dual;
    
    insert into vehicleused
        (v_id)
        values(used_vehicle_id);
    select M_ID,modelname,MODEL_TYPE
    into e_used,nameofveh_used,nameofmo_used
    from(select M_ID,modelname,MODEL_TYPE
        from model
        ORDER BY dbms_random.value)
    WHERE rownum = 1;

    select trunc(dbms_random.value(0, 9999),2) into type_discount_used from dual;
    
    --first get the list price 
    select list_price
    into v_list_price
    from(select list_price
        from model
        where model_type = nameofmo_used and modelname = nameofveh_used and m_id = e_used );
    v_new_price := v_list_price;

    select trunc(dbms_random.value(7000, 20000),2) into type_discount_used from dual;
    v_old_price := v_new_price;
    v_new_price := v_new_price - type_discount_used;
    
    
    --what about packages and options?
    SELECT round(dbms_random.value (1,2)) rnum 
    into do_they_want_package 
    FROM dual;  
    
    if do_they_want_package =1 and (nameofmo = 'Model K' or nameofmo = 'Model M') then
        SELECT distinct round(dbms_random.value(1,3)) rnum 
        into package_id_rand
        FROM dual;
        
        insert into v_packages_used
        values(used_vehicle_id,package_id_rand);
        
        select tot_price_package into package_cost from packages where package_id = package_id_rand;
        --dbms_output.put_line(package_cost);
--        v_new_price := v_new_price + package_cost;
        
    else
        package_id_rand := 2;--choose amenities package
        
        insert into v_packages_used
        values(used_vehicle_id,package_id_rand);
        select tot_price_package into package_cost from packages where package_id = package_id_rand;
        
        op1cost :=0;
    end if;
    
    if do_they_want_package =1 then
    v_new_price := v_new_price + package_cost;
    
    ELSE    v_new_price := v_new_price + package_cost;
    end if;
    
    IF nameofmo_used = 'Model K' THEN
    
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Southside Bethlehem',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Southside Bethlehem',nameofmo_used);
    ELSIF nameofmo_used = 'Model U' THEN
        
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Pacific Overlook',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Pacific Overlook',nameofmo_used);
    ELSIF   nameofmo_used = 'Model S'    THEN
        
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Mars Bars Center',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Mars Bars Center',nameofmo_used);
    ELSIF   nameofmo_used = 'Model M'    THEN
        
        insert into S_L_USED
            (LOCATION, V_ID, MODEL_TYPE, NEW_PRICE, OLD_PRICE)
            values('Moon Exploration',used_vehicle_id,nameofmo_used,v_new_price,v_old_price);
        insert into used_vehicles
            (v_id,location, model_type)
            values(used_vehicle_id,'Moon Exploration',nameofmo_used);
    END IF;    
        
    
    insert into v_model_used
    (V_ID, M_ID, modelname)
    values(used_vehicle_id,e_used,nameofveh_used); 

v_id1 := v_id1 +1;
end loop;
    
commit;
end; 