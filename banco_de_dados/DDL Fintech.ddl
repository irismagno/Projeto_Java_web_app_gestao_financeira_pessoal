-- Gerado por Oracle SQL Developer Data Modeler 21.4.1.349.1605
--   em:        2022-10-08 11:30:14 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE t_categorias (
    cd_categoria NUMBER(5) NOT NULL,
    ds_categoria VARCHAR2(10) NOT NULL
);

ALTER TABLE t_categorias ADD CONSTRAINT t_categorias_pk PRIMARY KEY ( cd_categoria );

CREATE TABLE t_cnt_c (
    nr_conta             NUMBER(5) NOT NULL,
    t_inst__fin_cd_inst  NUMBER(5) NOT NULL,
    t_usuario_cd_usuario NUMBER(5) NOT NULL,
    vl_saldo             NUMBER(9, 2) NOT NULL,
    ds_descricao         VARCHAR2(20)
);

ALTER TABLE t_cnt_c ADD CONSTRAINT t_cnt_corrente_pk PRIMARY KEY ( t_inst__fin_cd_inst,
                                                                   nr_conta );

CREATE TABLE t_cnt_invest (
    cd_invst                   NUMBER(10) NOT NULL,
    t_usuario_cd_usuario       NUMBER(5) NOT NULL,
    t_tipo_invest_cd_tp_invest NUMBER(5) NOT NULL,
    t_inst__fin_cd_inst        NUMBER(5) NOT NULL,
    ds_descricao               VARCHAR2(20) NOT NULL,
    vl_valor                   FLOAT(9) NOT NULL,
    dt_dt_aplicacao            DATE NOT NULL,
    nr_prazo                   NUMBER(3)
);

ALTER TABLE t_cnt_invest ADD CONSTRAINT t_invest_pk PRIMARY KEY ( t_usuario_cd_usuario,
                                                                  cd_invst );

CREATE TABLE t_gast (
    cd_gast                     NUMBER(10) NOT NULL,
    t_usuario_cd_usuario        NUMBER(5) NOT NULL,
    t_categorias_cd_categoria   NUMBER(5) NOT NULL,
    vl_valor                    FLOAT(6) NOT NULL,
    dt_data                     DATE NOT NULL,
    ds_descricao                VARCHAR2(15),
    t_cnt_c_t_inst__fin_cd_inst NUMBER(5) NOT NULL,
    t_cnt_c_nr_conta            NUMBER(5) NOT NULL
);

ALTER TABLE t_gast ADD CONSTRAINT t_gastos_pk PRIMARY KEY ( t_usuario_cd_usuario,
                                                            cd_gast );

CREATE TABLE t_genero (
    cd_genero NUMBER(1) NOT NULL,
    ds_genero VARCHAR2(9) NOT NULL
);

ALTER TABLE t_genero ADD CONSTRAINT t_genero_pk PRIMARY KEY ( cd_genero );

CREATE TABLE t_inst__fin (
    cd_inst NUMBER(5) NOT NULL,
    nm_inst VARCHAR2(10) NOT NULL
);

ALTER TABLE t_inst__fin ADD CONSTRAINT t_inst__fin_pk PRIMARY KEY ( cd_inst );

CREATE TABLE t_objetivos (
    cd_obj               NUMBER(5) NOT NULL,
    t_usuario_cd_usuario NUMBER(5) NOT NULL,
    nm_objetivo          VARCHAR2(20) NOT NULL,
    vl_valor             FLOAT NOT NULL,
    ds_descricao         VARCHAR2(30),
    dt_realizacao        DATE
);

ALTER TABLE t_objetivos ADD CONSTRAINT t_objetivos_pk PRIMARY KEY ( cd_obj,
                                                                    t_usuario_cd_usuario );

CREATE TABLE t_receb (
    cd_rec                      NUMBER(10) NOT NULL,
    t_usuario_cd_usuario        NUMBER(5) NOT NULL,
    vl_valor                    FLOAT(6) NOT NULL,
    dt_data                     DATE NOT NULL,
    ds_descricao                VARCHAR2(15),
    t_cnt_c_t_inst__fin_cd_inst NUMBER(5) NOT NULL,
    t_cnt_c_nr_conta            NUMBER(5) NOT NULL
);

ALTER TABLE t_receb ADD CONSTRAINT t_recebimentos_pk PRIMARY KEY ( cd_rec,
                                                                   t_usuario_cd_usuario );

CREATE TABLE t_tipo_invest (
    cd_tp_invest NUMBER(5) NOT NULL,
    ds_tp_invest VARCHAR2(15) NOT NULL
);

ALTER TABLE t_tipo_invest ADD CONSTRAINT t_tipo_invest_pk PRIMARY KEY ( cd_tp_invest );

CREATE TABLE t_usuario (
    cd_usuario         NUMBER(5) NOT NULL,
    t_genero_cd_genero NUMBER(1),
    nm_nome            VARCHAR2(15) NOT NULL,
    nm_sobrenome       VARCHAR2(10) NOT NULL,
    ds_email           VARCHAR2(15) NOT NULL,
    cd_senha           VARCHAR2(8) NOT NULL,
    dt_data_nasc       DATE
);

ALTER TABLE t_usuario ADD CONSTRAINT t_usuario_pk PRIMARY KEY ( cd_usuario );

ALTER TABLE T_USUARIO ALTER COLUMN DS_EMAIL VARCHAR(30);

ALTER TABLE t_cnt_c
    ADD CONSTRAINT t_cnt_c_t_inst__fin_fk FOREIGN KEY ( t_inst__fin_cd_inst )
        REFERENCES t_inst__fin ( cd_inst );

ALTER TABLE t_cnt_c
    ADD CONSTRAINT t_cnt_c_t_usuario_fk FOREIGN KEY ( t_usuario_cd_usuario )
        REFERENCES t_usuario ( cd_usuario );

ALTER TABLE t_cnt_invest
    ADD CONSTRAINT t_cnt_invest_t_inst__fin_fk FOREIGN KEY ( t_inst__fin_cd_inst )
        REFERENCES t_inst__fin ( cd_inst );

ALTER TABLE t_cnt_invest
    ADD CONSTRAINT t_cnt_invest_t_tipo_invest_fk FOREIGN KEY ( t_tipo_invest_cd_tp_invest )
        REFERENCES t_tipo_invest ( cd_tp_invest );

ALTER TABLE t_cnt_invest
    ADD CONSTRAINT t_cnt_invest_t_usuario_fk FOREIGN KEY ( t_usuario_cd_usuario )
        REFERENCES t_usuario ( cd_usuario );

ALTER TABLE t_gast
    ADD CONSTRAINT t_gast_t_categorias_fk FOREIGN KEY ( t_categorias_cd_categoria )
        REFERENCES t_categorias ( cd_categoria );

ALTER TABLE t_gast
    ADD CONSTRAINT t_gast_t_cnt_c_fk FOREIGN KEY ( t_cnt_c_t_inst__fin_cd_inst,
                                                   t_cnt_c_nr_conta )
        REFERENCES t_cnt_c ( t_inst__fin_cd_inst,
                             nr_conta );

ALTER TABLE t_gast
    ADD CONSTRAINT t_gast_t_usuario_fk FOREIGN KEY ( t_usuario_cd_usuario )
        REFERENCES t_usuario ( cd_usuario );

ALTER TABLE t_objetivos
    ADD CONSTRAINT t_objetivos_t_usuario_fk FOREIGN KEY ( t_usuario_cd_usuario )
        REFERENCES t_usuario ( cd_usuario );

ALTER TABLE t_receb
    ADD CONSTRAINT t_receb_t_cnt_c_fk FOREIGN KEY ( t_cnt_c_t_inst__fin_cd_inst,
                                                    t_cnt_c_nr_conta )
        REFERENCES t_cnt_c ( t_inst__fin_cd_inst,
                             nr_conta );

ALTER TABLE t_receb
    ADD CONSTRAINT t_receb_t_usuario_fk FOREIGN KEY ( t_usuario_cd_usuario )
        REFERENCES t_usuario ( cd_usuario );

ALTER TABLE t_usuario
    ADD CONSTRAINT t_usuario_t_genero_fk FOREIGN KEY ( t_genero_cd_genero )
        REFERENCES t_genero ( cd_genero );





-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            10
-- CREATE INDEX                             0
-- ALTER TABLE                             22
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0


 -- teste de select

SELECT     u.cd_usuario   "Código usuário",
           U.nm_nome      "Primeiro nome",
	       U.nm_sobrenome "Sobrenome" ,
           I.vl_valor      "valor" ,
           I.ds_descricao  "gasto"
from  t_cnt_invest I , t_usuario U 
where i.t_usuario_cd_usuario = u.cd_usuario
order by I.vl_valor;

INSERT INTO T_GENERO (CD_GENERO, DS_GENERO) VALUES (01, 'Masculino');

select * from t_genero;

DELETE FROM T_GENERO WHERE CD_GENERO = 2;

INSERT INTO T_GENERO (CD_GENERO, DS_GENERO) VALUES (02, 'Feminino');

INSERT INTO T_USUARIO (CD_USUARIO, T_GENERO_CD_GENERO, NM_NOME, NM_SOBRENOME, DS_EMAIL, CD_SENHA, DT_DATA_NASC) VALUES (SQ_CD_USUARIO.NEXTVAL, 1,'Magno', 'Azevedo', 'magno@yahoo.com', '123456', TO_DATE('13/08/1989','DD/MM/YYYY'));

SELECT * FROM T_USUARIO;

SELECT * FROM T_CATEGORIAS;

SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 1 AND T_USUARIO_CD_USUARIO = 65;

INSERT INTO T_CATEGORIAS (CD_CATEGORIA, DS_CATEGORIA) VALUES (07, 'Moradia');

DELETE FROM T_CATEGORIAS where cd_categoria ! = 1;
DELETE FROM T_USUARIO where cd_usuario != 65;
DELETE FROM T_CNT_C
DELETE FROM T_INST_FIN
DELETE FROM T_OBJETIVOS;
DELETE FROM T_GAST;
DELETE FROM T_RECEB;

INSERT INTO T_INST_FIN (CD_INST, NM_INST) VALUES (033, 'Santander');

SELECT * FROM T_INST_FIN;

INSERT INTO T_CNT_C (NR_CONTA, T_INST_FIN_CD_INST, T_USUARIO_CD_USUARIO, VL_SALDO, DS_DESCRICAO, NR_AGENCIA) VALUES (2474, 104, 03, 1040.00, 'Principal', 1709);

SELECT * FROM T_CNT_C;

UPDATE T_CNT_C SET NR_CONTA = 2222, T_INST_FIN_CD_INST = 1, T_USUARIO_CD_USUARIO = 3, VL_SALDO = 1000, DS_DESCRICAO = 'conta3', NR_AGENCIA = 1751 WHERE NR_CONTA = 2222  AND T_INST_FIN_CD_INST = 1;

UPDATE T_CATEGORIAS SET DS_CATEGORIA = 'Transporte' WHERE CD_CATEGORIA = 3;

SELECT * FROM T_GAST;

SELECT * FROM T_RECEB;

SELECT * FROM T_GAST WHERE T_USUARIO_CD_USUARIO = 3 ORDER BY DT_DATA DESC;

DELETE FROM T_RECEB;

SELECT * FROM T_OBJETIVOS;

INSERT INTO T_OBJETIVOS (CD_OBJ, T_USUARIO_CD_USUARIO, NM_OBJETIVO, VL_VALOR, DS_DESCRICAO, DT_REALIZACAO)
VALUES (33, 3, 'Xbox', 3000.0, 'Comprar Xbox', TO_DATE('10/12/2022','DD/MM/YYYY'));

SELECT * FROM T_TIPO_INVEST;

INSERT INTO T_TIPO_INVEST (CD_TP_INVEST, DS_TP_INVEST) VALUES (2, 'Renda variável');

SELECT * FROM T_CNT_INVEST;

DELETE FROM T_CNT_INVEST;

INSERT INTO T_GAST (CD_GAST, T_USUARIO_CD_USUARIO, T_CATEGORIAS_CD_CATEGORIA, VL_VALOR, DT_DATA, DS_DESCRICAO, T_CNT_C_T_INST_FIN_CD_INST, T_CNT_C_NR_CONTA)
VALUES (SQ_CD_GAST.NEXTVAL, 3, 1, 1.99,  TO_DATE('07/11/2022','DD/MM/YYYY'), 'fosforos', 1, 1122);
 
commit;

UPDATE CASCADE T_CNT_C SET T_INST_FIN_CD_INST = 237  WHERE NR_CONTA = 1122;

DELETE FROM T_CNT_C WHERE NR_CONTA = 2411 AND T_INST_FIN_CD_INST = 1;

SELECT SUM(VL_SALDO) FROM T_CNT_C WHERE T_USUARIO_CD_USUARIO = 3;
