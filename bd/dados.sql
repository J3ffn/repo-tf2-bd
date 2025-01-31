-- Usuarios --
INSERT INTO USUARIO (ID_USUARIO, NOME, DATANASCIMENTO, CPF, EMAIL, SENHA)
VALUES (SEQ_USUARIO.nextval, 'Daniel Ferraz', TO_DATE('14-02-1993', 'dd-mm-yyyy'), '09499999978', 'daniel.ilovedbc@dbc.com', 'senhasegura123');

INSERT INTO USUARIO (ID_USUARIO, NOME, DATANASCIMENTO, CPF, EMAIL, SENHA)
VALUES (SEQ_USUARIO.nextval, 'Andre Uchoa', TO_DATE('30-09-1990', 'dd-mm-yyyy'), '07811111121', 'andre.ilovedbc@dbc.com', 'senha456');

INSERT INTO USUARIO (ID_USUARIO, NOME, DATANASCIMENTO, CPF, EMAIL, SENHA)
VALUES (SEQ_USUARIO.nextval, 'Jefferson Izaquiel', TO_DATE('18-10-2003', 'dd-mm-yyyy'), '05866666690', 'jefferson.ilovedbc@dbc.com', 'segura789');

INSERT INTO USUARIO (ID_USUARIO, NOME, DATANASCIMENTO, CPF, EMAIL, SENHA)
VALUES (SEQ_USUARIO.nextval, 'Kamila Santos', TO_DATE('21-09-1990', 'dd-mm-yyyy'), '02488888854', 'kamila.ilovedbc@dbc.com', 'supersenha123');


-- Investimento --
INSERT INTO WALLETLIFE.INVESTIMENTO (ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)
VALUES (SEQ_INVESTIMENTO.nextval, 'Binance', 'Renda Variavel', 800.00, TO_DATE('11-03-2023', 'dd-mm-yyyy'), 'Aquisição cotas FII devant', 1);

INSERT INTO WALLETLIFE.INVESTIMENTO (ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)
VALUES (SEQ_INVESTIMENTO.nextval, 'XP', 'Renda Variavel', 1300.00, TO_DATE('19-08-2021', 'dd-mm-yyyy'), 'Aquisição ações Magalu', 2);

INSERT INTO WALLETLIFE.INVESTIMENTO (ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)
VALUES(SEQ_INVESTIMENTO.nextval, 'XP Investimentos', 'Renda Fixa', 200.0, TO_DATE('29-12-2020', 'dd-mm-yyyy'), 'Tesouro Selic', 4);

INSERT INTO WALLETLIFE.INVESTIMENTO (ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)
VALUES(SEQ_INVESTIMENTO.nextval, 'Easynvest', 'Renda Variavel', 400.0, TO_DATE('14-06-2022', 'dd-mm-yyyy'), 'Fundo de Ações', 3);

INSERT INTO WALLETLIFE.INVESTIMENTO (ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)
VALUES(SEQ_INVESTIMENTO.nextval, 'BTG Pactual Digital', 'Renda Fixa', 1000.0, TO_DATE('08-06-2023', 'dd-mm-yyyy'), 'Fundos de Renda Fixa', 4);


-- Despesas fixas --
INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Fixa', 500, 'Alimentação', TO_DATE('27-07/203','dd-mm-yyyy'), 1); 

INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Fixa', 250, 'Energia', TO_DATE('25-07/203','dd-mm-yyyy'), 2);

INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Fixa', 180, 'Água', TO_DATE('20-07/203','dd-mm-yyyy'), 3);

INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Fixa',230 , 'Transporte', TO_DATE('15-07/203','dd-mm-yyyy'), 4);


-- Despesa variável --
INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Variável', 120, 'Remédio', TO_DATE('15-07/203','dd-mm-yyyy'), 1);

INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Variável', 600, 'Manutenção do carro', TO_DATE('12-07/203','dd-mm-yyyy'), 2);

INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Variável', 220, 'Presente', TO_DATE('30-07/203','dd-mm-yyyy'), 3);

INSERT INTO DESPESA (ID_DESPESA, TIPO, VALOR, DESCRICAO, DATA_PAGAMENTO,ID_USUARIO)
VALUES (SEQ_DESPESA.nextval, 'Variável',50 , 'Pneu furado', TO_DATE('10-07/203','dd-mm-yyyy'), 4);


-- Receita --
INSERT INTO RECEITA (ID_RECEITA, BANCO, EMPRESA, VALOR, DESCRICAO, id_usuario)
VALUES (SEQ_RECEITA.nextval, 'Itaú', 'DBCCon', 800, 'Quality Analyst (QA)', 1);

INSERT INTO RECEITA (ID_RECEITA, BANCO, EMPRESA, VALOR, DESCRICAO, id_usuario)
VALUES (SEQ_RECEITA.nextval, 'Itaú', 'DBCCon', 800, 'Quality Analyst (QA)', 2);

INSERT INTO RECEITA (ID_RECEITA, BANCO, EMPRESA, VALOR, DESCRICAO, id_usuario)
VALUES (SEQ_RECEITA.nextval, 'Itaú', 'DBCCon', 800, 'Backend developer', 3);

INSERT INTO RECEITA (ID_RECEITA, BANCO, EMPRESA, VALOR, DESCRICAO, id_usuario)
VALUES (SEQ_RECEITA.nextval, 'Itaú', 'DBCCon', 800, 'Backend developer', 4);


