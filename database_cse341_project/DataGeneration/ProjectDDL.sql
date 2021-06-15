drop table customer cascade constraints;
drop table customer_payment;
drop table customer_payment_used;
drop table repairs  cascade constraints;
drop table used_vehicles cascade constraints;
drop table vehicle cascade constraints;
drop table vehicleused cascade constraints;
drop table service_location cascade constraints;
drop table recall cascade constraints;
drop table packages cascade constraints;
drop table model cascade constraints;
drop table options cascade constraints;
drop table customer_repair cascade constraints;
drop table customer_buys_used cascade constraints;
drop table customer_buys_new cascade constraints;
drop table vehicle_options cascade constraints;
drop table options_in_packages cascade constraints;
drop table v_model cascade constraints;
drop table v_model_used cascade constraints;
drop table v_packages cascade constraints;
drop table v_packages_used cascade constraints;
drop table new_vehicle cascade constraints;
drop table s_l_recall cascade constraints;
drop table s_l_used cascade constraints;
drop procedure CASCADE_C_CBN_CBU_CR ;
drop procedure options_to_have;
create table customer
    (NAME   varchar(40),
    ID      int,
    STREET_NUMBER   int,
    STREET_NAME varchar(100),
    APT_NUMBER  int,
    CITY    varchar(100),
    STATE   varchar(100),
    ZIP_CODE    int,
    EMAIL_C   varchar(200),
    COUNTRY varchar(200),
    PLANET varchar(200),
    primary key(NAME,ID,EMAIL_C)
    );

create table customer_payment
    (name   varchar(40),
    credit_debit_number int,
    id int,
    email_c varchar(200),
    primary key(name,credit_debit_number, id,email_c),
    constraint cp_c foreign key(name,id,email_c) references customer DEFERRABLE
    );
create table customer_payment_used
    (name   varchar(40),
    credit_debit_number int,
    id int,
    email_c varchar(200),
    primary key(name,credit_debit_number,id,email_c),
    constraint cpu_c foreign key(name,id,email_c) references customer DEFERRABLE
    );
create table repairs
    (location   varchar(100),
    date_REPAIR    DATE,
    cost    float(2),
    primary key(location,date_repair,cost)
    );


create table vehicle
    (v_id   NUMBER,
    primary key(v_id)
    );
create table vehicleused
    (v_id   NUMBER,
    primary key(v_id)
    );
create table service_location
    (location   varchar(50),
    model_type  varchar(30),
    primary key(location,model_type)
    );


create table recall
    (num_of_times   int,
    primary key(num_of_times)
    );

create table packages
    (package_id int,
    tot_price_package   number NOT NULL,
    package_name    varchar(30),
    primary key(package_id)
    );

create table model
    (m_id   int,
    modelname    varchar(40),
    year    int,
    model_type  varchar(30),
    list_price  number,
    primary key(m_id,modelname)
    );

create table options
    (options_name   varchar(50),
    list_price  number,
    description varchar(500),
    primary key(options_name,list_price)
    );
    
create table customer_repair
    (NAME   varchar(40),
    ID      int,
    EMAIL_C varchar(200),
    location   varchar(100),
    date_REPAIR    DATE,
    cost    float(2),
    primary key(name,id,location,date_REPAIR,cost,EMAIL_C),
    constraint cr_r foreign key(location,date_REPAIR,cost) references repairs,
    constraint cr_c foreign key(name,ID,EMAIL_C) references customer DEFERRABLE
    );

create table customer_buys_used
    (name   varchar(40),
    ID      int,
    v_id int,
    EMAIL_C varchar (200),
    price_paid  number,
    new_price   number,
    old_price   number,
    primary key(name,id,v_id,EMAIL_C),
    --constraint cbu_uv foreign key(v_id,new_price,old_price) references used_vehicles,
    constraint cbu_u foreign key(v_id) references vehicleused,
    --foreign key(new_price) references used_vehicles,
    constraint cbu_c foreign key(name,id,EMAIL_C) references customer DEFERRABLE
    );
    
create table customer_buys_new
    (name varchar(40),
    ID      int,
    v_id    int,
    EMAIL_C varchar(200),
    price_paid  number,
    primary key(name,id,v_id,EMAIL_C),
    constraint cbn_v foreign key(v_id) references vehicle,
    constraint cbn_c foreign key(name,id,EMAIL_C) references customer DEFERRABLE
       
    );
    
create table vehicle_options
    (v_id   int,
    option_name varchar(30),
    list_price  number,
    primary key(v_id,option_name,list_price),
    foreign key(option_name,list_price) references options,
    foreign key(v_id) references vehicle
    );

create table options_in_packages
    (option_name    varchar(30),
    package_id  int,
    list_price  number,
    primary key(option_name,package_id),
    foreign key(package_id) references packages,
    foreign key(option_name, list_price)    references options
    );

create table v_model
    (
    v_id    int,
    m_id   int,
    modelname    varchar(40),
    primary key(v_id,m_id,modelname),
    foreign key(v_id) references vehicle,
    foreign key(m_id,modelname) references model
    );
    
create table v_model_used
    (
    v_id    int,
    m_id   int,
    modelname    varchar(40),
    primary key(v_id,m_id,modelname),
    foreign key(v_id) references vehicleused,
    foreign key(m_id,modelname) references model
    );  
    
create table v_packages
    (v_id   int,
    package_id  int,
    primary key(v_id,package_id),
    foreign key(package_id) references packages,
    foreign key(v_id) references vehicle
    );

create table v_packages_used
    (v_id   int,
    package_id  int,
    primary key(v_id,package_id),
    foreign key(package_id) references packages,
    foreign key(v_id) references vehicleused
    );
create table new_vehicle
    (v_id   int,
    location   varchar(30),
    model_type varchar(30),
    primary key(v_id),
    foreign key(location,model_type) references service_location,
    foreign key(v_id) references vehicle
    );
    
create table used_vehicles
    (V_ID   int,
    location   varchar(30),
    model_type varchar(30),
    primary key(v_id),
    foreign key(location,model_type) references service_location,
    foreign key(v_id) references vehicleused
    );    
    
create table s_l_recall
    (location   varchar(30),
    num_of_times    int,
    model_type  varchar(30),
    primary key(location),
    foreign key(num_of_times) references recall,
    foreign key(location,model_type) references service_location
    );
    
create table s_l_used
    (location   varchar(30),
    v_id int,
    model_type  varchar(30),
    new_price  number,
    old_price   number,
    primary key(location,v_id,model_type),
   --foreign key(v_id,new_price,old_price) references used_vehicles,
    foreign key(v_id) references vehicleused,
   --foreign key(new_price) references used_vehicles,
    foreign key(location,model_type) references service_location
    );
CREATE OR REPLACE PROCEDURE CASCADE_C_CBN_CBU_CR (
    OLD_NAME_C IN VARCHAR2, 
    NEW_NAME_C IN VARCHAR2)
IS 
BEGIN
   execute immediate 'set constraint cr_c deferred';
   execute immediate 'set constraint cbu_c deferred';
   execute immediate 'set constraint cbn_c deferred';
   execute immediate 'set constraint cp_c deferred';
   execute immediate 'set constraint cpu_c deferred';
       
   update customer set name = NEW_NAME_C where name = OLD_NAME_C;
   update customer_repair set name = NEW_NAME_C where name = OLD_NAME_C;
   update customer_buys_used set name = NEW_NAME_C where name = OLD_NAME_C;
   update customer_buys_new set name = NEW_NAME_C where name = OLD_NAME_C;
   update customer_payment set name = NEW_NAME_C where name = OLD_NAME_C;
   update customer_payment_used set name = NEW_NAME_C where name = OLD_NAME_C;
   execute immediate 'set constraint cr_c immediate';
   execute immediate 'set constraint cbu_c immediate';
   execute immediate 'set constraint cbn_c immediate';
   execute immediate 'set constraint cp_c immediate';
   execute immediate 'set constraint cpu_c immediate';   
END CASCADE_C_CBN_CBU_CR;
/
CREATE OR REPLACE PROCEDURE options_to_have (
    MY_VEH_NUM IN NUMBER,
    OPTION_WANTED IN VARCHAR2, 
    PRICE_OF_OPTION IN NUMBER)
IS 
BEGIN

   insert into vehicle_options(V_ID,OPTION_NAME,LIST_PRICE) VALUES(MY_VEH_NUM, OPTION_WANTED, PRICE_OF_OPTION);
END options_to_have;
/
CREATE OR REPLACE PROCEDURE REPLACE_C_EMAIL (
    OLD_NAME_EMAIL IN VARCHAR2, 
    NEW_NAME_EMAIL IN VARCHAR2)
IS
BEGIN
    execute immediate 'set constraint cr_c deferred';
    execute immediate 'set constraint cbu_c deferred';
    execute immediate 'set constraint cbn_c deferred';
    execute immediate 'set constraint cp_c deferred';
    execute immediate 'set constraint cpu_c deferred';
 
    UPDATE CUSTOMER SET EMAIL_C = NEW_NAME_EMAIL WHERE EMAIL_C = OLD_NAME_EMAIL;
    UPDATE customer_repair SET EMAIL_C = NEW_NAME_EMAIL WHERE EMAIL_C = OLD_NAME_EMAIL;
    UPDATE customer_buys_used SET EMAIL_C = NEW_NAME_EMAIL WHERE EMAIL_C = OLD_NAME_EMAIL;
    UPDATE customer_buys_new SET EMAIL_C = NEW_NAME_EMAIL WHERE EMAIL_C = OLD_NAME_EMAIL;
    UPDATE customer_payment SET EMAIL_C = NEW_NAME_EMAIL WHERE EMAIL_C = OLD_NAME_EMAIL;    
    UPDATE customer_payment_used SET EMAIL_C = NEW_NAME_EMAIL WHERE EMAIL_C = OLD_NAME_EMAIL; 
    
    execute immediate 'set constraint cr_c immediate';
    execute immediate 'set constraint cbu_c immediate';
    execute immediate 'set constraint cbn_c immediate';
    execute immediate 'set constraint cp_c immediate';
    execute immediate 'set constraint cpu_c immediate'; 
END REPLACE_C_EMAIL;