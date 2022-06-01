CREATE TYPE user_status AS ENUM ('ACTIVE', 'BLOCKED');

CREATE TABLE CX_USER (
                         id uuid PRIMARY KEY,
                         login varchar(200) not null,
                         password varchar(300) not null,
                         view_name varchar(80),
                         created_at date default CURRENT_TIMESTAMP,
                         updated_at date default CURRENT_TIMESTAMP,
                         status user_status not null
);

CREATE TYPE account_type AS ENUM (
    'CORRENTE',
    'POUPANCA',
    'DINHEIRO',
    'CARTAO_CREDITO',
    'INVESTIMENTO'
    );

CREATE TABLE CX_ACCOUNTS(
                            id uuid PRIMARY KEY,
                            description varchar(255) not null,
                            inital_amount int not null,
                            type account_type
);


CREATE TABLE CX_CREDENTIAL_USER_ACCOUNTS(
                                            user_id uuid primary key,
                                            account_id uuid not null,
                                            foreign key (user_id) references CX_USER(id),
                                            foreign key (account_id) references CX_ACCOUNTS(id)
);


CREATE TABLE cx_transaction_type(
                                    id uuid primary key ,
                                    description varchar(100) not null
);

CREATE TABLE cx_transaction_category(
                                        id uuid primary key ,
                                        description varchar(120),
                                        transaction_type_id uuid not null,
                                        FOREIGN KEY(transaction_type_id) REFERENCES cx_transaction_type(id)
);

CREATE TABLE cx_account_transaction(
                                       id uuid primary key,
                                       description varchar(255) not null,
                                       date timestamp not null,
                                       value integer not null,
                                       extra_description text,
                                       account_id uuid not null,
                                       transaction_type_id uuid not null,
                                       transaction_category_id uuid not null,
                                       FOREIGN KEY(account_id) REFERENCES cx_accounts(id),
                                       FOREIGN KEY(transaction_type_id) REFERENCES cx_transaction_type(id),
                                       FOREIGN KEY(transaction_category_id) REFERENCES cx_transaction_category(id)
)