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

--1 表bank_operator （银行操作人员信息表）
create table bank_operator(
   Oper_code  Char(4) primary key,      	--操作员编号
   Oper_name  varchar2(30) not null unique,	--操作员姓名
   Oper_pwd   char(6) not null,			--登陆密码
   Oper_contact varchar2(30) not null	--联络电话
);

create sequence seq_bankoper;

--2 表fund
create table fund(
   fund_no   int  primary key,       		--自增序列
   fund_name varchar2(40) not null unique,
   fund_price number(9,3) not null,
   fund_desc varchar2(100),         --备注
   fund_status  Char(1) not null, 		-- O – 开放、C – 封闭
   create_date Date not null,
   Oper_code Char(4) not null ,				--操作员编号
   constraint FK_f_o_code FOREIGN KEY (Oper_code)references bank_operator(Oper_code)

);

create sequence seq_fund;

--3 表client （银行客户信息表）
create table client(
   Client_no   int primary key,     		--自增序列
   idcard_no   char(18) not null unique,  	--身份证号码不能雷同
   client_name Varchar2(30) not null,
   client_sex  char(1) ,                    --男：a 女：b
   client_phone Varchar2(20) ,
   client_email   varchar2(20),
   client_address  Varchar2(50),
   client_hobby   Varchar2(50),
   create_date  date not null,
   Oper_code  Char(4)  not null,
   constraint FK_c_o_code FOREIGN KEY (Oper_code)references bank_operator(Oper_code)
);

create sequence seq_client;

--4 表financial_account （客户资金账户）
create table financial_account(
   acc_no   int  primary key,       	--资金账户账号	
   acc_pwd  char(6) not null,           --密码
   acc_amount number(12,2) not null,   --金额
   acc_status  char(1) not null,        --状态（冻结b，可用a）
   Client_no  int  not null, 			-- 账户所属客户
   create_date date not null,
   Oper_code Char(4) not null,		 	--操作员编号
   constraint FK_f_a_c_code FOREIGN KEY (Client_no)references CLIENT(Client_no),
   constraint FK_f_a_o_code FOREIGN KEY (Oper_code) references bank_operator(Oper_code)
);

create sequence seq_finacc;

--5 表financial_account_transinfo  （资金账户交易记录）
create table financial_account_transinfo(
   trans_id   int primary key,     	 	--流水号自增
   trans_type   char(1) not null,  		--追加资金:A 取出资金:O 认购基金:B 赎回基金:R
   trans_amount Number(12,2) not null,   --交易金额
   trans_time  date not null, 			--交易发生时间
   Acc_no  int not null,            --资金账户账号
   Oper_code  Char(4) not null,		--操作员编号
   constraint FK_f_a_t_f_account FOREIGN KEY (acc_no)references financial_account(acc_no) on delete cascade,
   constraint FK_f_a_t_o_code FOREIGN KEY (Oper_code) references bank_operator(Oper_code)
);

create sequence seq_finanacctrans;

--6 表fund_transinfo （基金交易记录）
create table fund_transinfo (
   trans_id   int  primary key,       		--交易流水号
   trans_type char(1) not null,			--认购:b 赎回:r	
   Acc_no  int  not null,			--资金账户
   Fund_no int  not null,			--交易的基金编号
   amount  Number(12,2)  not null, 	--数量
   price Number(12,2)  not null,				--交易价位
   trans_date date not null,			--交易发生时间
   constraint FK_f_t_f_a_no FOREIGN KEY (acc_no)references financial_account(acc_no) on delete cascade,
   constraint FK_f_t_f_no FOREIGN KEY (fund_no) references fund(fund_no) on delete cascade
);
 create sequence seq_fundtrans;


--7 表fund_holding （资金账户持仓信息）
create table fund_holding(
   hid   int primary key,     		--编号
   acc_no    int not null,  		--资金账户账号
   fund_no int not null,			--基金编号
   amount  Number(9,2) not null,			--数量
   constraint FK_f_h_f_a_no FOREIGN KEY (acc_no)references financial_account(acc_no),
   constraint FK_f_h_f_no FOREIGN KEY (fund_no) references fund(fund_no)
);

create sequence seq_fundhold;


--创建视图-客户详细信息
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

--创建视图-资金账户信息
create view v_financialaccount as 
select acc_no,acc_pwd,acc_amount,acc_status,financial_account.client_no,financial_account.create_date,idcard_no,financial_account.oper_code
from financial_account,client
where financial_account.client_no = client.client_no;

--创建视图-基金账户信息
create view v_fundaccountinfo as 
select a2.acc_no, a1.idcard_no, a1.client_name, a4.fund_name, a3.amount 
from client a1, financial_account a2, fund_transinfo a3,fund a4 
where a1.client_no=a2.client_no and a3.fund_no=a2.acc_no and a4.fund_no=a3.fund_no;

--创建视图-赎回用
create view V_REDEPTION AS 
select a2.acc_no,a3.fund_no, a3.fund_name, a3.fund_price, a3.fund_desc, a2.hid,a3.fund_status, a2.amount
from financial_account a1, fund_holding a2,fund a3
where a1.acc_no= a2.acc_no and a2.fund_no= a3.fund_no;


insert into bank_operator values(seq_bankoper.nextval,'操作员1','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员2','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员3','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员4','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员5','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员6','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员7','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员8','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员9','123456','123456');
insert into bank_operator values(seq_bankoper.nextval,'操作员10','123456','123456');
commit;

INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101001', '客户1', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101002', '客户2', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101003', '客户3', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101004', '客户4', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101005', '客户5', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101006', '客户6', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101007', '客户7', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101008', '客户8', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101009', '客户9', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101010', '客户10', 'a', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101011', '客户11', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101012', '客户12', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101013', '客户13', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101014', '客户14', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101015', '客户15', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101016', '客户16', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101017', '客户17', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101018', '客户18', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101019', '客户19', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."CLIENT" (CLIENT_NO, IDCARD_NO, CLIENT_NAME, CLIENT_SEX, CLIENT_PHONE, CLIENT_EMAIL, CLIENT_ADDRESS, CLIENT_HOBBY, CREATE_DATE, OPER_CODE) VALUES (seq_client.nextval, '350206199910101020', '客户20', 'b', '123456789', '123456@qq.com', '福州南路', '打篮球', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
commit;

INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '安信A类', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '宝盈货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '博时策略混合', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '长城货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '长盛货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '长信利丰债券', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '泰和', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '大成货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '工银货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '国联安货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '国泰保本', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '银丰债券', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '华夏债券', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '中欧新趋势', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '嘉实货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '金鹰货币', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FUND" (FUND_NO, FUND_NAME, FUND_PRICE, FUND_DESC, FUND_STATUS, CREATE_DATE, OPER_CODE) VALUES (seq_fund.nextval, '民生加银', '23', '备注备注', 'o', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
commit;

INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '111111', 'a', '1', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '222222', 'a', '2', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '333333', 'a', '3', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '444444', 'a', '4', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '555555', 'a', '5', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '666666', 'a', '6', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '777777', 'a', '7', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '888888', 'a', '8', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '999999', 'a', '9', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT" (ACC_NO, ACC_PWD, ACC_AMOUNT, ACC_STATUS, CLIENT_NO, CREATE_DATE, OPER_CODE) VALUES (seq_finacc.nextval, '123456', '00', 'a', '10', TO_DATE('29-12月-12', 'DD-MON-RR'), '1');
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

INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '1', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '2', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '3', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '4', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'a', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '5', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '6', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '7', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '8', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '9', '1');
INSERT INTO "FUNDMGR"."FINANCIAL_ACCOUNT_TRANSINFO" (TRANS_ID, TRANS_TYPE, TRANS_AMOUNT, TRANS_TIME, ACC_NO, OPER_CODE) VALUES (seq_finanacctrans.nextval, 'o', '5000', TO_DATE('29-12月-12', 'DD-MON-RR'), '10', '1');
commit;

INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '1', '100', '700', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '2', '200', '800', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '3', '300', '900', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '1', '4', '400', '46', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '3', '5', '104', '456', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '4', '6', '50', '654', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '5', '7', '500', '312', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'r', '3', '8', '21', '78', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '3', '4', '021', '12', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '3', '5', '021', '123', TO_DATE('04-1月 -13', 'DD-MON-RR'));
INSERT INTO "FUNDMGR"."FUND_TRANSINFO" (TRANS_ID, TRANS_TYPE, ACC_NO, FUND_NO, AMOUNT, PRICE, TRANS_DATE) VALUES (seq_fundtrans.nextval, 'b', '3', '4', '021', '123', TO_DATE('04-1月 -13', 'DD-MON-RR'));
commit;









