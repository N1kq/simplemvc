-- msg_and_users.message_data определение

-- Drop table

-- DROP TABLE msg_and_users.message_data;

CREATE TABLE msg_and_users.message_data (
	message varchar(1000) NULL, -- содержимое сообщения
	recdate timestamptz NULL, -- дата создания записи
	editdate timestamptz NULL, -- дата изменения записи
	sender_id int4 NULL, -- идентификатор отправителя сообщения
	message_id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL, -- идентификатор сообщения в таблице
	CONSTRAINT message_data_pkey PRIMARY KEY (message_id)
);

-- Column comments

COMMENT ON COLUMN msg_and_users.message_data.message IS 'содержимое сообщения';
COMMENT ON COLUMN msg_and_users.message_data.recdate IS 'дата создания записи';
COMMENT ON COLUMN msg_and_users.message_data.editdate IS 'дата изменения записи';
COMMENT ON COLUMN msg_and_users.message_data.sender_id IS 'идентификатор отправителя сообщения';
COMMENT ON COLUMN msg_and_users.message_data.message_id IS 'идентификатор сообщения в таблице';


-- msg_and_users.message_data внешние включи

ALTER TABLE msg_and_users.message_data ADD CONSTRAINT message_data_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES msg_and_users.user_data(user_id);