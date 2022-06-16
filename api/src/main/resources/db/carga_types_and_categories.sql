
INSERT INTO cx_transaction_type (id, description, slugname) VALUES ('42a18f9b-28a7-43e3-8dd9-7b72bb91ab74', 'Receita', 'receita');
INSERT INTO cx_transaction_type (id, description, slugname) VALUES ('f4a5830f-626b-4987-97ee-1e60a39a9430', 'Despesa', 'despesa');

INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Saldo Anterior', '42a18f9b-28a7-43e3-8dd9-7b72bb91ab74', 'saldo_anterior');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Salario liquido', '42a18f9b-28a7-43e3-8dd9-7b72bb91ab74', 'salario_liquido');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Venda feita', '42a18f9b-28a7-43e3-8dd9-7b72bb91ab74', 'venda_feita');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Servico prestado', '42a18f9b-28a7-43e3-8dd9-7b72bb91ab74', 'servico_prestado');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Reembolso', '42a18f9b-28a7-43e3-8dd9-7b72bb91ab74', 'reembolso');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Outras receitas', '42a18f9b-28a7-43e3-8dd9-7b72bb91ab74', 'outras_receitas');

INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Saldo anterior', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'despesa_saldo_anterior');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Alimentacao e Refeicao', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'alimentacao_refeicao');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Saude', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'saude');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Contas de casa ', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'contas_casa');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Compras para a casa ', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'compras_casa');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Internet', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'internet');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Educacao', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'educacao');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Vestuario e acessorios', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'vestuario_acessorios');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Aparelhos e acessorios tecnologicos', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'aparelhos_tecnologicos');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Assinaturas e streams', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'assinaturas_streams');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Multas e taxas', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'multa_taxas');
INSERT INTO cx_transaction_category(ID, DESCRIPTION, TRANSACTION_TYPE_ID, SLUGNAME) VALUES (uuid_generate_v4(),'Outras despesas', 'f4a5830f-626b-4987-97ee-1e60a39a9430', 'outras_despesas');

