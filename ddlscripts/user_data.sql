-- public.user_data определение

-- Drop table

-- DROP TABLE public.user_data;

CREATE TABLE public.user_data (
	recdate timestamp(6) NULL, -- дата создания записи
	editdate timestamp(6) NULL, -- дата изменения
	user_id int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL, -- идентификатор в таблице
	user_tgname varchar(100) NULL, -- имя пользователя в тг
	"name" varchar(100) NULL, -- Имя заказчика
	surname varchar(100) NULL, -- Фамилия заказчика
	phone_number varchar(20) NULL, -- Номер телефона пользователя
	"e-mail" varchar(100) NULL, -- Электронная почта пользователя
	"role" int8 NULL, -- Роль
	chat_id varchar(100) NULL,
	user_tg_id int8 NULL, -- id пользователя telegram
	CONSTRAINT user_data_pkey PRIMARY KEY (user_id)
);
COMMENT ON TABLE public.user_data IS 'Здесь указаны пользователи';

-- Column comments

COMMENT ON COLUMN public.user_data.recdate IS 'дата создания записи';
COMMENT ON COLUMN public.user_data.editdate IS 'дата изменения';
COMMENT ON COLUMN public.user_data.user_id IS 'идентификатор в таблице';
COMMENT ON COLUMN public.user_data.user_tgname IS 'имя пользователя в тг';
COMMENT ON COLUMN public.user_data."name" IS 'Имя заказчика';
COMMENT ON COLUMN public.user_data.surname IS 'Фамилия заказчика';
COMMENT ON COLUMN public.user_data.phone_number IS 'Номер телефона пользователя';
COMMENT ON COLUMN public.user_data."e-mail" IS 'Электронная почта пользователя';
COMMENT ON COLUMN public.user_data."role" IS 'Роль';
COMMENT ON COLUMN public.user_data.user_tg_id IS 'id пользователя telegram';