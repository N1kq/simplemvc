-- msg_and_users.roles определение

-- Drop table

-- DROP TABLE msg_and_users.roles;

CREATE TABLE msg_and_users.roles (
	role_id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	"name" varchar(40) NULL, -- Название роли
	CONSTRAINT roles_pk PRIMARY KEY (role_id)
);
COMMENT ON TABLE msg_and_users.roles IS 'идентификатор роли';

-- Column comments

COMMENT ON COLUMN msg_and_users.roles."name" IS 'Название роли';