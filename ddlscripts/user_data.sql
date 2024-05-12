-- msg_and_users.user_data определение

-- Drop table

-- DROP TABLE msg_and_users.user_data;

CREATE TABLE msg_and_users.user_data (
	recdate timestamptz NULL, -- дата создания записи
	editdate timestamptz NULL, -- дата изменения
	user_id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL, -- идентификатор в таблице
	user_tgname varchar(100) NULL, -- имя пользователя в тг
	"name" varchar(100) NULL, -- Имя заказчика
	surname varchar(100) NULL, -- Фамилия заказчика
	phone_number varchar(20) NULL, -- Номер телефона пользователя
	"e-mail" varchar(100) NULL, -- Электронная почта пользователя
	"role" varchar(20) NULL, -- Роль
	chat_id varchar(100) NULL,
	CONSTRAINT user_data_pkey PRIMARY KEY (user_id)
);
COMMENT ON TABLE msg_and_users.user_data IS 'Здесь указаны пользователи';

-- Column comments

COMMENT ON COLUMN msg_and_users.user_data.recdate IS 'дата создания записи';
COMMENT ON COLUMN msg_and_users.user_data.editdate IS 'дата изменения';
COMMENT ON COLUMN msg_and_users.user_data.user_id IS 'идентификатор в таблице';
COMMENT ON COLUMN msg_and_users.user_data.user_tgname IS 'имя пользователя в тг';
COMMENT ON COLUMN msg_and_users.user_data."name" IS 'Имя заказчика';
COMMENT ON COLUMN msg_and_users.user_data.surname IS 'Фамилия заказчика';
COMMENT ON COLUMN msg_and_users.user_data.phone_number IS 'Номер телефона пользователя';
COMMENT ON COLUMN msg_and_users.user_data."e-mail" IS 'Электронная почта пользователя';
COMMENT ON COLUMN msg_and_users.user_data."role" IS 'Роль';