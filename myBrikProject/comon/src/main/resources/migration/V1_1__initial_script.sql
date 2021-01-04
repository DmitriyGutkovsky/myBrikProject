create table m_users
(
	id bigserial not null
		constraint m_users_pk
			primary key,
	name varchar(100) not null,
	surname varchar(100),
	login varchar(100) not null,
	password varchar(100) not null,
	email varchar(100) not null,
	gender varchar(50) default 'NOT_SELECTED'::character varying not null,
	created timestamp(3) default CURRENT_TIMESTAMP,
	changed timestamp(3) default CURRENT_TIMESTAMP,
	phone varchar(100) not null,
	address varchar(100),
	is_deleted boolean default false
);

alter table m_users owner to postgres;

create index m_users_address_index
	on m_users (address);

create unique index m_users_id_uindex
	on m_users (id);

create index m_users_name_index
	on m_users (name);

create index m_users_surname_index
	on m_users (surname);

create unique index m_users_login_uindex
	on m_users (login);

create unique index m_users_email_uindex
	on m_users (email);

create unique index m_users_phone_uindex
	on m_users (phone);

create table m_goods
(
	id bigserial not null
		constraint m_goods_pk
			primary key,
	order_code varchar(100) not null,
	name varchar(200) not null,
	photo varchar(300),
	gender varchar(50) default 'unisex'::character varying not null,
	size integer not null,
	color varchar(100) not null,
	description varchar(700),
	is_deleted boolean not null,
	price double precision,
	quantity integer,
	created timestamp(3) default CURRENT_TIMESTAMP,
	changed timestamp(3) default CURRENT_TIMESTAMP,
	category varchar(100) not null
);

alter table m_goods owner to postgres;

create index m_goods_color_index
	on m_goods (color);

create index m_goods_gender_index
	on m_goods (gender);

create unique index m_goods_id_uindex
	on m_goods (id);

create index m_goods_name_index
	on m_goods (name);

create index m_goods_price_index
	on m_goods (price);

create index m_goods_category_index
	on m_goods (category);

create unique index m_goods_order_code_uindex
	on m_goods (order_code);

create index m_goods_size_index
	on m_goods (size);

create table m_standard_order
(
	id bigserial not null
		constraint m_standard_order_pk
			primary key,
	good_id bigint not null
		constraint m_standard_order_m_goods_id_fk
			references m_goods,
	user_id bigint not null
		constraint m_standard_order_m_users_id_fk
			references m_users
				on update cascade on delete cascade,
	quantity integer not null,
	total_price double precision,
	order_status varchar(50) default 'SEND'::character varying not null,
	changed timestamp(3) default CURRENT_TIMESTAMP,
	created timestamp(3) default CURRENT_TIMESTAMP
);

alter table m_standard_order owner to postgres;

create index m_standard_order_good_id_index
	on m_standard_order (good_id);

create unique index m_standard_order_id_uindex
	on m_standard_order (id);

create index m_standard_order_order_status_index
	on m_standard_order (order_status);

create index m_standard_order_user_id_index
	on m_standard_order (user_id);

create table m_price_for_individual_order
(
	id bigserial not null
		constraint m_price_for_individual_order_pk
			primary key,
	product_type varchar(100) not null,
	price double precision not null,
	created timestamp(3) default CURRENT_TIMESTAMP,
	changed timestamp(3) default CURRENT_TIMESTAMP,
	is_deleted boolean default false not null
);

alter table m_price_for_individual_order owner to postgres;

create unique index m_price_for_individual_order_id_uindex
	on m_price_for_individual_order (id);

create unique index m_price_for_individual_order_product_type_uindex
	on m_price_for_individual_order (product_type);

create table m_product_type
(
	id bigserial not null
		constraint m_product_type_pk
			primary key,
	product_type varchar(300) not null,
	photo varchar(300),
	is_deleted boolean default false not null,
	created timestamp(3) default CURRENT_TIMESTAMP,
	changed timestamp(3) default CURRENT_TIMESTAMP
);

alter table m_product_type owner to postgres;

create unique index m_product_type_id_uindex
	on m_product_type (id);

create unique index m_product_type_product_type_uindex
	on m_product_type (product_type);

create table m_textile
(
	id bigserial not null
		constraint m_textile_pk
			primary key,
	code varchar(50) not null,
	name varchar(100) not null,
	color varchar not null,
	description varchar(500),
	photo varchar(500),
	is_deleted boolean default false not null,
	created timestamp(3) default CURRENT_TIMESTAMP,
	changed timestamp(3) default CURRENT_TIMESTAMP
);

alter table m_textile owner to postgres;

create unique index m_textile_code_uindex
	on m_textile (code);

create index m_textile_color_index
	on m_textile (color);

create unique index m_textile_id_uindex
	on m_textile (id);

create unique index m_textile_name_uindex
	on m_textile (name);

create table l_textile_product_type
(
	id bigserial not null
		constraint m_textileproducttype_pk
			primary key,
	textile_id bigint not null
		constraint m_textileproducttype_m_textile_id_fk
			references m_textile
				on update cascade on delete cascade,
	product_type_id bigint not null
		constraint m_textileproducttype_m_product_type_id_fk
			references m_product_type
				on update cascade on delete cascade
);

alter table l_textile_product_type owner to postgres;

create unique index m_textileproducttype_id_uindex
	on l_textile_product_type (id);

create index m_textileproducttype_product_type_id_index
	on l_textile_product_type (product_type_id);

create index m_textileproducttype_textile_id_index
	on l_textile_product_type (textile_id);

create table m_individual_order
(
	id bigserial not null
		constraint m_individual_order_pk
			primary key,
	user_id bigint not null
		constraint m_individual_order_m_users_id_fk
			references m_users,
	textile_id bigint not null
		constraint m_individual_order_m_textile_id_fk
			references m_textile,
	product_type_id bigint not null
		constraint m_individual_order_m_product_type_id_fk
			references m_product_type,
	price_id bigint not null
		constraint m_individual_order_m_price_for_individual_order_id_fk
			references m_price_for_individual_order,
	quantity integer not null,
	total_price double precision not null,
	order_status varchar(50) default 'SEND'::character varying not null,
	created timestamp(3) default CURRENT_TIMESTAMP,
	changed timestamp(3) default CURRENT_TIMESTAMP
);

alter table m_individual_order owner to postgres;

create unique index m_individual_order_id_uindex
	on m_individual_order (id);

create index m_individual_order_order_status_index
	on m_individual_order (order_status);

create index m_individual_order_product_type_id_index
	on m_individual_order (product_type_id);

create index m_individual_order_textile_id_index
	on m_individual_order (textile_id);

create index m_individual_order_user_id_index
	on m_individual_order (user_id);

create table m_roles
(
	id bigserial not null
		constraint m_roles_pk
			primary key,
	role_name varchar(100) default 'ROLE_USER'::character varying not null,
	user_id bigint not null
		constraint m_roles_m_users_id_fk
			references m_users
				on delete cascade
);

alter table m_roles owner to postgres;

create index m_roles_role_name_index
	on m_roles (role_name);

