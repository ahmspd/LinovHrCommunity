
insert into t_role(id,code, role_name, created_at, "version") values 
	('1','SA001', 'Super Admin', now(), 0),
	('2','MB001', 'Member', now(), 0);

insert into t_industry (id, code, industry_name, created_at, created_by , "version") values 
	('1','IDS01', 'Industry 1', now(),'1', 0),
	('2','IDS02', 'Industry 2', now(),'1', 0),
	('3','IDS03', 'Industry 2', now(),'2', 0),
    ('4','IDS04', 'Industry 2', now(),'2', 0),
    ('5','IDS05', 'Industry 2', now(),'2', 0),
    ('6','IDS06', 'Industry 2', now(),'2', 0),
    ('7','IDS07', 'Industry 2', now(),'2', 0),
    ('8','IDS08', 'Industry 2', now(),'2', 0),
    ('9','IDS09', 'Industry 2', now(),'2', 0),
    ('10','IDS10', 'Industry 2', now(),'2', 0),
    ('11','IDS11', 'Industry 2', now(),'2', 0),
    ('12','IDS12', 'Industry 2', now(),'2', 0),
    ('13','IDS13', 'Industry 2', now(),'2', 0),
    ('14','IDS14', 'Industry 2', now(),'2', 0),
    ('15','IDS15', 'Industry 2', now(),'2', 0);

insert into t_position (id,code, position_name, created_at,created_by ,"version") values 
	('1','PS001', 'Position 1', now(),'1', 0),
	('2','PSS02', 'Position 2', now(),'1', 0);

insert into t_file (id, extensions, contents, created_by, created_at, "version") values 
	('1','.jpg','asdasd','1',now(),0);

insert into t_province(id,code, province_name, created_at,created_by , "version") values
('1','11', 'ACEH',now(),'1',0),
  ('2','12', 'SUMATERA UTARA',now(),'1',0),
  ('3','13', 'SUMATERA BARAT',now(),'1',0),
  ('4','14', 'RIAU',now(),'1',0);
 
insert into t_city (id,code, code_province, city_name, created_at, "version") values
('1','1101', '11', 'KABUPATEN SIMEULUE', now(),0),
  ('2','1102', '11', 'KABUPATEN ACEH SINGKIL', now(),0),
  ('3','1103', '12', 'KABUPATEN ACEH SELATAN', now(),0),
  ('4','1104', '12', 'KABUPATEN ACEH TENGGARA', now(),0),
  ('5','1105', '13', 'KABUPATEN ACEH TIMUR', now(),0),
  ('6','1106', '13', 'KABUPATEN ACEH TENGAH', now(),0),
  ('7','1107', '14', 'KABUPATEN ACEH BARAT', now(),0),
  ('8','1108', '14', 'KABUPATEN ACEH BESAR', now(),0);
  
insert into t_thread_type (id, code, thread_type_name, created_by, created_at, "version") values 
	('1','TYP001','Thread', '1',now(),0),
	('2','TYP002','Artikel', '1',now(),0),
	('3','TPP001','Premium', '1', now(), 0),
	('4','PL0001','Polling','1',now(), 0);
	
insert into t_price_type (id, code, price_type_name, created_by, created_at, "version") values 
	('1','PT0001','Price Member','1',now(), 0),
	('2','PT0002','Price Event','1',now(), 0),
	('3','PT0003','Price Course','1',now(), 0);
	
insert into t_price_list (id, code, price_name, price, id_price_type, created_by, created_at, "version") values 
	('1','PL0001','Member Price 1 bulan',300000,'1','1',now(), 0),
	('2','PL0002','Member Price 3 bulan',450000,'1','1',now(), 0),
	('3','PL0003','Event Price',3900000,'2','1',now(), 0),
	('4','PL0004','Course Price',5900000,'3','1',now(), 0);

insert into t_event_course_type (id, code, event_course_type_name, created_by, created_at, "version") values 
	('1','EV01','Event','1',now(),0),
	('2','CO01','Course','1',now(),0);
	
insert into t_user (id, email, "password",id_role, registration_code, is_member, created_by, created_at, "version") values 
	('1','admin@admin','admin123','1','1212312',false, '1', now(), 0),
	('2','memberpremium@member','member123','2','1212312',true, '1', now(), 0),
	('3','member@member','member123','2','1212312',false, '1', now(), 0);

insert into t_profile (id, id_user, full_name, phone_number, postal_code,company , id_industry, id_position,id_province,id_city, created_at,created_by,"version") values 
	('1','1','user admin','1203011230','40532','company1','1','2','1','1',now(),'1',0),
	('2','2','user member premium','1203011230','40532','company1','1','2','1','1',now(),'1',0),
	('3','3','user member','1203011230','40532','company1','1','2','1','1',now(),'1',0);
	
insert into t_thread (id, title, contents, id_file, id_thread_type,is_premium, created_by, created_at, "version") values
	('1','thread biasa','content1', '1', '1',false, '1', now(),0 ),
	('2','thread premium','content1', '1', '3',true, '1', now(),0 ),
	('3','artikel','content1', '1', '2',false, '1', now(),0 ),
	('4','Polling','polling content', '1', '4',false, '1', now(),0 );

insert into t_polling (id, id_thread, polling_name, created_by, created_at, "version") values 
	('1','4','siapakah yang paling', '1',now(), 0);

insert into t_polling_detail (id, id_polling, polling_name, created_by, created_at, "version") values 
	('1','1','aku','1',now(), 0),
	('2','1','dia','1',now(),0),
	('3','1','mereka','1',now(),0);

insert into t_polling_detail_vote (id, id_polling_detail, created_by, created_at, "version") values 
	('1','1','2',now(), 0),
	('2','1','3',now(), 0);

insert into t_bookmark (id, id_thread, created_by, created_at, "version") values 
	('1','1','1',now(),0);

insert into t_like (id, id_thread, created_by, created_at, "version") values 
	('1','1','1',now(),0);

insert into t_thread_detail (id, id_thread, contents,created_by, created_at, "version") values
	('1','1','content','1',now(),0);

insert into t_category (id, category_name, code, created_by, created_at, "version") values 
	('1', 'Sport', 'SPORT01', '1', now(), 0),
	('2', 'Music', 'MUSIC01', '1', now(), 0),
	('3', 'Bootcamp', 'BTCMP01', '1', now(), 0);

insert into t_payment_method (id, code, payment_name, created_by, created_at, "version") values 
	('1', 'CASH1', 'Cash', '1', now(), 0),
	('2', 'CASH2', 'Cashless', '1', now(), 0);
	
	
select * from t_thread;
select * from t_bookmark;
--get all thread
select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name
from t_thread tt left join t_file tf on tt.id_file = tf.id 
left join t_thread_type ttt on tt.id_thread_type = ttt.id 
left join t_user tu on tu.id = tt.created_by 
left join t_profile tp on tp.id_user = tu.id;

--get all thread by id user 
select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, 
tt.created_at , tt.created_by , tp.full_name
from t_thread tt
left join t_file tf 
on tt.id_file = tf.id 
left join t_thread_type ttt 
on tt.id_thread_type = ttt.id 
left join t_user tu 
on tu.id = tt.created_by 
left join t_profile tp 
on tp.id_user = tu.id
where tu.id = '1';

--get thread by premium or not
select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, 
tt.created_at , tt.created_by , tp.full_name
from t_thread tt
left join t_file tf 
on tt.id_file = tf.id 
left join t_thread_type ttt 
on tt.id_thread_type = ttt.id 
left join t_user tu 
on tu.id = tt.created_by 
left join t_profile tp 
on tp.id_user = tu.id
where tt.is_premium = true;

--get thread by type
select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, 
tt.created_at , tt.created_by , tp.full_name
from t_thread tt
left join t_file tf 
on tt.id_file = tf.id 
left join t_thread_type ttt 
on tt.id_thread_type = ttt.id 
left join t_user tu 
on tu.id = tt.created_by 
left join t_profile tp 
on tp.id_user = tu.id
where tt.id_thread_type = '1';

--get thread by bookmark
select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, 
tt.created_at , tt.created_by , tp.full_name
from t_bookmark tb 
left join t_thread tt
on tb.id_thread = tt.id 
left join t_file tf 
on tt.id_file = tf.id 
left join t_thread_type ttt 
on tt.id_thread_type = ttt.id 
left join t_user tu 
on tu.id = tt.created_by 
left join t_profile tp 
on tp.id_user = tu.id
where tb.created_by = '1';

--get thread by like
select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, 
tt.created_at , tt.created_by , tp.full_name
from t_like tl left join t_thread tt on tl.id_thread = tt.id 
left join t_file tf 
on tt.id_file = tf.id 
left join t_thread_type ttt 
on tt.id_thread_type = ttt.id 
left join t_user tu 
on tu.id = tt.created_by 
left join t_profile tp 
on tp.id_user = tu.id
where tl.created_by = '1';

--get thread detail by thread id 
select ttd.id , ttd.id_thread , ttd.contents , ttd.created_by, ttd.created_at , tp.full_name 
from t_thread_detail ttd left join t_user tu  on ttd.created_by = tu.id 
left join t_profile tp on tu.id = tp.id_user where ttd.id_thread = '1';

select * from t_thread;
--get thread polling
select tt.id , tt.title , tt.contents, tt.id_file , tf.extensions , tf.contents, ttt.thread_type_name, tt.is_premium, tt.created_at , tt.created_by , tp.full_name, tpl.polling_name, tpl.id 
from t_thread tt left join t_file tf on tt.id_file = tf.id 
left join t_thread_type ttt on tt.id_thread_type = ttt.id 
left join t_user tu on tu.id = tt.created_by 
left join t_profile tp on tp.id_user = tu.id
left join t_polling tpl on tpl.id_thread = tt.id 
where tt.id_thread_type = (select id from t_thread_type ttt where ttt.code='PL0001');

select * from t_thread tt ;
select * from t_polling tp;
select * from t_polling_detail;
select * from t_polling_detail_vote tpdv ;
select * from t_industry ti 

select count(tpdv.id) from t_polling_detail_vote tpdv left join t_polling_detail tpd 
on tpdv.id_polling_detail = tpd.id where tpd.id_polling = '1';

select id from t_polling_detail tpd where tpd.id_polling = '1'

select count(id) from t_polling_detail_vote tpdv where tpdv.id_polling_detail = '2';


select * from t_polling_detail_vote;
select * from t_thread_type;

select count(id) as totalId from t_polling_detail_vote tpdv where tpdv.id_polling_detail = '1';

SELECT * FROM t_event_course_payment_detail
WHERE created_by = '1' AND id_event_course_payment ISNULL;

SELECT id, id_event_course, id_event_course_payment, version, is_active
FROM t_event_course_payment_detail
WHERE id_event_course_payment = :id;

SELECT id, id_event_course, id_event_course_payment, version, is_active FROM t_event_course_payment_detail WHERE id_event_course_payment = '72c3e975-b2da-4bd4-be34-290dbf538e7d';

SELECT ec.id, ec.contents, ec.title, ec.event_course_location, ec.price, ec.date_start, ec.date_end, ec.time_start, ec.time_end, ec.id_file, f.extensions, f.contents, ec.created_by, p.full_name, ec.created_at, ec.version, ec.is_active FROM t_event_course ec INNER JOIN t_event_course_type ect ON ec.id_event_course_type = ect.id INNER JOIN t_file f ON ec.id_file = f.id INNER JOIN t_user u ON ec.created_by = u.id INNER JOIN t_profile p ON u.id = p.id_user WHERE ect.event_course_type_name = 'Event' AND ec.is_active = true;

SELECT tum.id, tp.full_name, tu.email, tpm.payment_name, tor.is_accept, tor.id_file, tpl.price_name, tum.is_active
FROM t_order_detail tod
LEFT JOIN t_user_member tum on tum.id = tod.id_user_member
LEFT JOIN t_order tor on tor.id = tod.id_order
LEFT JOIN t_user tu on tu.id = tor.id_user
LEFT JOIN t_profile tp on tu.id = tp.id_user
LEFT JOIN t_payment_method tpm on tpm.id = tor.id_payment_method
LEFT JOIN t_file tf on tf.id = tor.id_file
LEFT JOIN t_price_list tpl on tpl.id = tum.id_price_list
WHERE tor.is_accept = true OR tor.is_accept ISNULL;






