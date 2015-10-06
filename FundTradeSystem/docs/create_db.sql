drop table client;
drop table bank_operator;
drop table financial_account;
drop table financial_account_transinfo;
drop table fund;
drop table fund_holding;
drop table fund_transinfo;


create user fundmgr identified by abc123;
grant connect,resource to fundmgr;
grant create any view to fundmgr;

conn fundmgr/abc123;

--1 ��bank_operator �����в�����Ա��Ϣ��
create table bank_operator(
   Oper_code  Char(4) primary key,      	--����Ա���
   Oper_name  varchar2(30) not null unique,	--����Ա����
   Oper_pwd   char(6) not null,			--��½����
   Oper_contact varchar2(30) not null	--����绰
);

create sequence seq_bankoper;

--2 ��fund
create table fund(
   fund_no   int  primary key,       		--��������
   fund_name varchar2(40) not null unique,
   fund_price number(9,3) not null,
   fund_desc varchar2(100),         --��ע
   fund_status  Char(1) not null, 		-- O �C ���š�C �C ���
   create_date Date not null,
   Oper_code Char(4) not null ,				--����Ա���
   constraint FK_f_o_code FOREIGN KEY (Oper_code)references bank_operator(Oper_code)

);

create sequence seq_fund;

--3 ��client �����пͻ���Ϣ��
create table client(
   Client_no   int primary key,     		--��������
   idcard_no   char(18) not null unique,  	--���֤���벻����ͬ
   client_name Varchar2(30) not null,
   client_sex  char(1) ,                    --�У�a Ů��b
   client_phone Varchar2(20) ,
   client_email   varchar2(20),
   client_address  Varchar2(50),
   client_hobby   Varchar2(50),
   create_date  date not null,
   Oper_code  Char(4)  not null,
   constraint FK_c_o_code FOREIGN KEY (Oper_code)references bank_operator(Oper_code)
);

create sequence seq_client;

--4 ��financial_account ���ͻ��ʽ��˻���
create table financial_account(
   acc_no   int  primary key,       	--�ʽ��˻��˺�	
   acc_pwd  char(6) not null,           --����
   acc_amount number(12,2) not null,   --���
   acc_status  char(1) not null,        --״̬������b������a��
   Client_no  int  not null, 			-- �˻������ͻ�
   create_date date not null,
   Oper_code Char(4) not null,		 	--����Ա���
   constraint FK_f_a_c_code FOREIGN KEY (Client_no)references CLIENT(Client_no),
   constraint FK_f_a_o_code FOREIGN KEY (Oper_code) references bank_operator(Oper_code)
);

create sequence seq_finacc;

--5 ��financial_account_transinfo  ���ʽ��˻����׼�¼��
create table financial_account_transinfo(
   trans_id   int primary key,     	 	--��ˮ������
   trans_type   char(1) not null,  		--׷���ʽ�:A ȡ���ʽ�:O �Ϲ�����:B ��ػ���:R
   trans_amount Number(12,2) not null,   --���׽��
   trans_time  date not null, 			--���׷���ʱ��
   Acc_no  int not null,            --�ʽ��˻��˺�
   Oper_code  Char(4) not null,		--����Ա���
   constraint FK_f_a_t_f_account FOREIGN KEY (acc_no)references financial_account(acc_no) on delete cascade,
   constraint FK_f_a_t_o_code FOREIGN KEY (Oper_code) references bank_operator(Oper_code)
);

create sequence seq_finanacctrans;

--6 ��fund_transinfo �������׼�¼��
create table fund_transinfo (
   trans_id   int  primary key,       		--������ˮ��
   trans_type char(1) not null,			--�Ϲ�:b ���:r	
   Acc_no  int  not null,			--�ʽ��˻�
   Fund_no int  not null,			--���׵Ļ�����
   amount  Number(12,2)  not null, 	--����
   price Number(12,2)  not null,				--���׼�λ
   trans_date date not null,			--���׷���ʱ��
   constraint FK_f_t_f_a_no FOREIGN KEY (acc_no)references financial_account(acc_no) on delete cascade,
   constraint FK_f_t_f_no FOREIGN KEY (fund_no) references fund(fund_no) on delete cascade
);
 create sequence seq_fundtrans;


--7 ��fund_holding ���ʽ��˻��ֲ���Ϣ��
create table fund_holding(
   hid   int primary key,     		--���
   acc_no    int not null,  		--�ʽ��˻��˺�
   fund_no int not null,			--������
   amount  Number(9,2) not null,			--����
   constraint FK_f_h_f_a_no FOREIGN KEY (acc_no)references financial_account(acc_no),
   constraint FK_f_h_f_no FOREIGN KEY (fund_no) references fund(fund_no)
);

create sequence seq_fundhold;


--������ͼ-�ͻ���ϸ��Ϣ
create view v_cusinfodetail as 
select client.* ,table2.acc_no,table2.acc_amount,table2.amount
from client,(select financial_account.*,amount
             from financial_account,(select fund_holding.acc_no,sum(fund_holding.amount*fund.fund_price) as amount
                                     from fund_holding, fund
                                     where fund_holding.fund_no = fund.fund_no
                                     group by acc_no) table1
                                     where financial_account.acc_no=table1.acc_no(+)) table2
where table2.client_no(+) = client.client_no
order by client.client_no;

--������ͼ-�ʽ��˻���Ϣ
create view v_financialaccount as 
select acc_no,acc_pwd,acc_amount,acc_status,financial_account.client_no,financial_account.create_date,idcard_no,financial_account.oper_code
from financial_account,client
where financial_account.client_no = client.client_no;

--������ͼ-�����˻���Ϣ
create view v_fundaccountinfo as 
select a2.acc_no, a1.idcard_no, a1.client_name, a4.fund_name, a3.amount 
from client a1, financial_account a2, fund_transinfo a3,fund a4 
where a1.client_no=a2.client_no and a3.fund_no=a2.acc_no and a4.fund_no=a3.fund_no;

--������ͼ-�����
create view V_REDEPTION AS 
select a2.acc_no,a3.fund_no, a3.fund_name, a3.fund_price, a3.fund_desc, a2.hid,a3.fund_status, a2.amount
from financial_account a1, fund_holding a2,fund a3
where a1.acc_no= a2.acc_no and a2.fund_no= a3.fund_no;


insert into bank_operator values(seq_bankoper.nextval,'����Ա1','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա2','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա3','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա4','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա5','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա6','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա7','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա8','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա9','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'����Ա10','123456','123456');
commit;

INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101001', '�ͻ�1', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101002', '�ͻ�2', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101003', '�ͻ�3', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101004', '�ͻ�4', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101005', '�ͻ�5', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101006', '�ͻ�6', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101007', '�ͻ�7', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101008', '�ͻ�8', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101009', '�ͻ�9', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101010', '�ͻ�10', 'a', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101011', '�ͻ�11', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101012', '�ͻ�12', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101013', '�ͻ�13', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101014', '�ͻ�14', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101015', '�ͻ�15', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101016', '�ͻ�16', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101017', '�ͻ�17', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101018', '�ͻ�18', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101019', '�ͻ�19', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101020', '�ͻ�20', 'b', '123456789', '123456@qq.com', '������·', '������', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
commit;

INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '����A��', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��ӯ����', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��ʱ���Ի��', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '���ǻ���', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��ʢ����', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��������ծȯ', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '̩��', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��ɻ���', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��������', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '����������', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��̩����', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '����ծȯ', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '����ծȯ', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��ŷ������', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��ʵ����', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��ӥ����', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '��������', '23', '��ע��ע', 'o', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
commit;

INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '111111', 'a', '1', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '222222', 'a', '2', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '333333', 'a', '3', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '444444', 'a', '4', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '555555', 'a', '5', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '666666', 'a', '6', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '777777', 'a', '7', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '888888', 'a', '8', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '999999', 'a', '9', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '00', 'a', '10', TO_DATE('29-12��-12', 'DD-MON-RR'), '1');
commit;


INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '1', '1', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '2', '2', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '3', '3', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '4', '4', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '5', '5', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '6', '6', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '7', '7', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '8', '8', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '9', '9', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '10', '10', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '10', '9', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '10', '8', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '10', '7', '500');
INSERT INTO "FUNDMGR"."FUND_HOLDING" (HID, ACC_NO, FUND_NO, AMOUNT) VALUES (seq_fundhold.nextval, '10', '6', '500');
commit;

INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '1', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '2', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '3', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '4', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '5', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '6', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '7', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '8', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '9', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12��-12', 'DD-MON-RR'), '10', '1');
commit;

INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '1', '100', '700', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '2', '200', '800', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '3', '300', '900', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '4', '400', '46', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '3', '5', '104', '456', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '4', '6', '50', '654', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '5', '7', '500', '312', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '3', '8', '21', '78', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '3', '4', '021', '12', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '3', '5', '021', '123', TO_DATE('04-1�� -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '3', '4', '021', '123', TO_DATE('04-1�� -13', 'DD-MON-RR'));
commit;









