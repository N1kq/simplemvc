-- public.message_data определение

-- Drop table

-- DROP TABLE public.message_data;

CREATE TABLE public.message_data (
	message varchar(1000) NULL, -- содержимое сообщения
	recdate timestamp(6) NULL, -- дата создания записи
	editdate timestamp(6) NULL, -- дата изменения записи
	sender_id int8 NULL, -- идентификатор отправителя сообщения
	message_id int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL, -- идентификатор сообщения в таблице
	sender_tg_id int8 NULL,
	CONSTRAINT message_data_pkey PRIMARY KEY (message_id)
);

-- Column comments

COMMENT ON COLUMN public.message_data.message IS 'содержимое сообщения';
COMMENT ON COLUMN public.message_data.recdate IS 'дата создания записи';
COMMENT ON COLUMN public.message_data.editdate IS 'дата изменения записи';
COMMENT ON COLUMN public.message_data.sender_id IS 'идентификатор отправителя сообщения';
COMMENT ON COLUMN public.message_data.message_id IS 'идентификатор сообщения в таблице';


-- public.message_data внешние включи

ALTER TABLE public.message_data ADD CONSTRAINT message_data_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.user_data(user_id);