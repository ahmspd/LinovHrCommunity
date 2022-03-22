CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--database name : linov-hr-community
--master data
create table t_role(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(10) NOT NULL,
	role_name varchar(30) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_role add constraint role_pk primary key(id);
alter table t_role add constraint role_bk unique(code);
alter table t_role add constraint role_ck unique(id, code);

create table t_industry(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(10) NOT NULL,
	industry_name varchar(30) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_industry add constraint industry_pk primary key(id);
alter table t_industry add constraint industry_bk unique(code);
alter table t_industry add constraint industry_ck unique(id, code);

create table t_position(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(10) NOT NULL,
	position_name varchar(30) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_position add constraint position_pk primary key(id);
alter table t_position add constraint position_bk unique(code);
alter table t_position add constraint position_ck unique(id, code);

-- berisi tipe dari thread yang akan dibuat
-- ex. polling / thread biasa
create table t_thread_type(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(10) NOT NULL,
	thread_type_name varchar(30) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_thread_type add constraint thread_type_pk primary key(id);
alter table t_thread_type add constraint thread_type_bk unique(code);
alter table t_thread_type add constraint thread_type_ck unique(id, code);

--berisi tipe dari event
--ex. event / course
create table t_event_course_type(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(10) NOT NULL,
	event_type_name varchar(30) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_event_course_type add constraint event_type_pk primary key(id);
alter table t_event_course_type add constraint event_type_bk unique(code);
alter table t_event_course_type add constraint event_type_ck unique(id, code);

create table t_file(
	id uuid DEFAULT uuid_generate_v4 (),
	extensions varchar(10) NOT NULL,
	contents bytea NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_file add constraint file_pk primary key(id);

--berisi detail payment method
--ex. code : db001, name : Debit
create table t_payment_method(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(10) NOT NULL,
	payment_name varchar(30) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_payment_method add constraint payment_method_pk primary key(id);
alter table t_payment_method add constraint payment_method_bk unique(code);

--berisi price list yang dibuat oleh super admin
--ex. code : evt01, name : Event, price: 200000
create table t_price_list(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(10) NOT NULL,
	price_name varchar(30) NOT NULL,
	price int NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_price_list add constraint price_list_pk primary key(id);
alter table t_price_list add constraint price_list_code_bk unique(code);
-- end master data

create table t_user(
	id uuid DEFAULT uuid_generate_v4 (),
	email varchar(30) NOT NULL,
	"password" varchar(255) NOT NULL,
	id_role uuid NOT NULL,
	registration_code varchar(10) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default false
);
alter table t_user add constraint user_pk primary key(id);
alter table t_user add constraint user_bk unique(email);
alter table t_user add constraint user_role_fk foreign key(id_role) references t_role(id);

create table t_province(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(7) NOT NULL,
	province_name varchar(50)NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default false
);

alter table t_province add constraint province_pk primary key(id);
alter table t_province add constraint province_bk unique(code);

create table t_city(
	id uuid DEFAULT uuid_generate_v4 (),
	code varchar(7) NOT NULL,
	city_name varchar(100) NOT NULL,
	code_province varchar(7) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default false
);

alter table t_city add constraint city_pk primary key(id);
alter table t_city add constraint city_bk unique(code);
alter table t_city add constraint city_code_province_fk foreign key(code_province) references t_province(code);

create table t_profile(
	id uuid DEFAULT uuid_generate_v4 (),
	id_user uuid NOT NULL,
	full_name varchar(50) NOT NULL,
	phone_number varchar(15),
	instagram varchar(50),
	twitter varchar(50),
	facebook varchar(50),
	company varchar(30) NOT NULL,
	postal_code varchar(10),
	id_industry uuid NOT NULL,
	id_position uuid NOT NULL,
	id_province uuid,
	id_city uuid,
	id_file uuid,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_profile add constraint profile_pk primary key(id);
alter table t_profile add constraint profile_user_fk foreign key(id_user) references t_user(id);
alter table t_profile add constraint profile_industry_fk foreign key(id_industry) references t_industry(id);
alter table t_profile add constraint profile_position_fk foreign key(id_position) references t_position(id);
alter table t_profile add constraint profile_file_fk foreign key(id_file) references t_file(id);
alter table t_profile add constraint profile_province_fk foreign key(id_province) references t_province(id);
alter table t_profile add constraint profile_city_fk foreign key(id_city) references t_city(id);

--tabel untuk member yang mendaftar menjadi premium
--id file berisi gambar bukti pembayaran
--id price list berisi harga untuk menjadi member premium
--id method berisi cara pembayaran yang dipilih
create table t_user_member(
	id uuid DEFAULT uuid_generate_v4 (),
	id_price_list uuid NOT NULL,
	is_member boolean default false,
	date_end timestamp,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_user_member add constraint t_user_member_pk primary key(id);
alter table t_user_member add constraint t_user_member_price_list_fk foreign key(id_price_list) references t_price_list(id);

create table t_thread(
	id uuid DEFAULT uuid_generate_v4 (),
	title varchar(100) NOT NULL,
	contents text NOT NULL,
	id_file uuid,
	id_thread_type uuid,
	is_premium boolean default false,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_thread add constraint t_threads_pk primary key(id);
alter table t_thread add constraint t_threads_file_fk foreign key(id_file) references t_file(id);
alter table t_thread add constraint t_threads_type_fk foreign key(id_thread_type) references t_thread_type(id);

create table t_thread_detail(
	id uuid DEFAULT uuid_generate_v4 (),
	id_thread uuid NOT NULL,
	contents text NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_thread_detail add constraint thread_detail_pk primary key(id);
alter table t_thread_detail add constraint thread_detail_thread_fk foreign key(id_thread) references t_thread(id);

--tabel untuk thread yang memiliki content polling
create table t_polling(
	id uuid DEFAULT uuid_generate_v4 (),
	id_thread uuid NOT NULL,
	polling_name varchar(50) NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_polling add constraint t_polling_pk primary key(id);
alter table t_polling add constraint t_polling_thread_fk foreign key(id_thread) references t_thread(id);

--tabel untuk content polling dari tabel t_polling
create table t_polling_detail(
	id uuid DEFAULT uuid_generate_v4 (),
	id_polling uuid NOT NULL,
	polling_name varchar(50),
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_polling_detail add constraint t_polling_detail_pk primary key(id);
alter table t_polling_detail add constraint t_polling_detail_polling_fk foreign key(id_polling) references t_polling(id);

--tabel untuk vote polling content
create table t_polling_detail_vote(
	id uuid DEFAULT uuid_generate_v4 (),
	id_polling_detail uuid NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);

alter table t_polling_detail_vote add constraint t_polling_detail_vote_pk primary key(id);
alter table t_polling_detail_vote add constraint t_polling_detail_polling_detail_vote_fk foreign key(id_polling_detail) references t_polling_detail(id);


create table t_bookmark(
	id uuid DEFAULT uuid_generate_v4 (),
	id_thread uuid NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_bookmark add constraint t_bookmark_pk primary key(id);
alter table t_bookmark add constraint t_bookmark_thread_fk foreign key(id_thread) references t_thread(id);

create table t_like(
	id uuid DEFAULT uuid_generate_v4 (),
	id_thread uuid NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_like add constraint t_like_pk primary key(id);
alter table t_like add constraint t_like_thread_fk foreign key(id_thread) references t_thread(id);

create table t_event_course(
	id uuid DEFAULT uuid_generate_v4 (),
	id_event_type uuid NOT NULL,
	title varchar(35) NOT NULL,
	event_location varchar(50) NOT NULL,
	price int NOT NULL,
	date_start timestamp NOT NULL,
	date_end timestamp NOT NULL,
	time_start time NOT NULL,
	time_end time,
	id_file uuid,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_event_course add constraint event_pk primary key(id);
alter table t_event_course add constraint event_type_fk foreign key(id_event_type) references t_event_course_type(id);
alter table t_event_course add constraint id_file_fk foreign key(id_file)  references t_file(id) ;

create table t_event_course_payment(
	id uuid DEFAULT uuid_generate_v4 (),
--	id_event uuid,
	id_payment_method uuid,
	is_accept boolean default false,
	id_file uuid,
	id_price_list uuid NOT NULL,
	invoice varchar(30),
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_event_course_payment add constraint event_payment_pk primary key(id);
--alter table t_event_course_payment add constraint event_payment_event_course_fk foreign key(id_event) references t_event_course(id);
alter table t_event_course_payment add constraint event_payment_fk foreign key(id_payment_method) references t_payment_method(id);
alter table t_event_course_payment add constraint event_payment_file_fk foreign key(id_file) references t_file(id);
alter table t_event_course_payment add constraint event_payment_price_fk foreign key(id_price_list) references t_price_list(id);

create table t_event_course_payment_detail(
	id uuid DEFAULT uuid_generate_v4 (),
	id_event uuid NOT NULL,
	id_event_payment uuid NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_event_course_payment_detail add constraint event_payment_detail_pk primary key(id);
alter table t_event_course_payment_detail add constraint event_payment_detail_id_fk foreign key(id_event) references t_event_course(id);
alter table t_event_course_payment_detail add constraint event_payment_fk foreign key(id_event_payment) references t_event_course_payment(id);

create table t_order(
	id uuid DEFAULT uuid_generate_v4 (),
--	id_event uuid,
	id_user uuid NOT NULL,
	is_accept boolean default false,
	id_file uuid,
	id_payment_method uuid,
	invoice varchar(30),
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_order add constraint t_order_pk primary key(id);
--alter table t_order add constraint t_order_detail_fk foreign key(id_event) references t_event_course(id);
alter table t_order add constraint t_order_user foreign key(id_user) references t_user(id);
alter table t_order add constraint t_order_file foreign key(id_file) references t_file(id);
alter table t_order add constraint t_order_payment foreign key(id_payment_method) references t_payment_method(id);

create table t_order_detail(
	id uuid DEFAULT uuid_generate_v4 (),
	id_event uuid NOT NULL,
	id_order uuid NOT NULL,
	id_user_member uuid NOT NULL,
	created_by uuid,
	created_at timestamp without time zone,
	updated_by uuid,
	updated_at timestamp without time zone,
	"version" int,
	is_active boolean default true
);
alter table t_order_detail add constraint order_detail_pk primary key(id);
alter table t_order_detail add constraint order_detail_event_fk foreign key(id_event) references t_event_course(id);
alter table t_order_detail add constraint order_detail_order_fk foreign key(id_order) references t_order(id);
alter table t_order_detail add constraint order_detail_user_member_fk foreign key(id_user_member) references t_user_member(id);
alter table t_order_detail add constraint order_detail_ck unique(id, id_event);
