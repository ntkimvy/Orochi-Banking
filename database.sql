CREATE DATABASE OROCHIBANKING
GO
USE OROCHIBANKING
GO

CREATE TABLE CUSTOMER(
	userName varchar(50) primary key,
	password varchar(100) not null,
	fullName nvarchar(200) not null,
	dob date not null,
	id varchar(15) unique not null,
	address nvarchar(1000) not null,
	sex bit not null,
	email varchar(100),
	avatar nvarchar(1000),
	tel varchar(10) not null,	
)

INSERT INTO CUSTOMER(userName, password, fullName, dob, id, address, sex, email, tel)
VALUES('orochi', 'orochi', N'NGÂN HÀNG OROCHI', '01-01-2000', '9999999999', N'Thành Phố Đà Nẵng', 1, 'orochi@gmail.com', '0987654321')

CREATE TABLE STAFF(
	staffNo int IDENTITY(1000, 1) primary key,
	userName varchar(50) unique,
	password varchar(100) not null,
	fullName nvarchar(200) not null,
	dob date not null,
	id varchar(15) unique not null,
	address nvarchar(1000) not null,
	sex bit not null,
	email varchar(100),
	avatar nvarchar(1000),
	tel varchar(10) not null,
	userTypeID bit not null default 1,
)

CREATE TABLE USERTYPE(
	userTypeID bit primary key,
	userType nvarchar(100) not null
)

ALTER TABLE STAFF
ADD FOREIGN KEY (userTypeID) REFERENCES USERTYPE(userTypeID)

INSERT INTO USERTYPE(userTypeID, userType)
VALUES(0, N'Giám đốc'),
(1, N'Nhân viên')


INSERT INTO STAFF(userName, password, fullName, dob, id, address, sex, email, tel, userTypeID)
VALUES('orochi-manager', 'orochi', 'NGÂN HÀNG OROCHI', '01-01-2000', '9999999999', N'Thành Phố Đà Nẵng', 1, 'orochi@gmail.com', '0987654321', 0)

CREATE TABLE ACCOUNT(
	userName varchar(50) not null,
	accNo int IDENTITY(100000, 1) PRIMARY KEY,
	password varchar(100) not null,
	balance decimal(18, 0) NOT NULL Default(0) Check(balance>=0),
)

ALTER TABLE ACCOUNT
ADD FOREIGN KEY (userName) REFERENCES CUSTOMER(userName)

INSERT INTO ACCOUNT(userName, password, balance)
VALUES('orochi', 'orochi', 10000000000)

CREATE TABLE TYPE(
	typeID int primary key,
	type nvarchar(100) not null,
)

INSERT INTO TYPE(typeID, type)
VALUES(0, N'Đăng nhập'),
(1, N'Nộp tiền'),
(2, N'Rút tiền'),
(3, N'Chuyển tiền'),
(4, N'Nhận tiền'),
(5, N'Tạo tài khoản'),
(6, N'Giao dịch lỗi'),
(7, N'Thanh toán hóa đơn'),
(8, N'Hủy giao dịch')

CREATE TABLE HISTORY(
	hisID int IDENTITY(1,1) PRIMARY KEY,
	accNo int NOT NULL FOREIGN KEY REFERENCES ACCOUNT(accNo),
	staffNo varchar(50) FOREIGN KEY REFERENCES STAFF(userName),
	amount decimal(18, 0) NOT NULL default(0),
	postBalance decimal(18, 0) NOT NULL,
	time datetime NOT NULL Default(GetDate()),
	typeID int NOT NULL FOREIGN KEY REFERENCES TYPE(typeID),
	note ntext
)

INSERT INTO HISTORY(accNo, staffNo, amount, postBalance, typeID, note)
VALUES(100000, 'orochi-manager', 10000000000, 10000000000, 5, N'Tạo tài khoản vốn cho ngân hàng ')

CREATE TABLE MESSAGE(
	messageID int IDENTITY(1,1),
	typeID bit NOT NULL,
	custID varchar(50) NOT NULL FOREIGN KEY REFERENCES CUSTOMER(userName),
	staffID varchar(50) FOREIGN KEY REFERENCES STAFF(userName),
	message ntext NOT NULL,
	time DATETIME NOT NULL DEFAULT(GETDATE()),
	PRIMARY KEY(messageID)
)

CREATE TABLE ORDERS(
	orderID int IDENTITY(1,1) PRIMARY KEY,
	accNo int NOT NULL FOREIGN KEY REFERENCES ACCOUNT(accNo),
	typeID int NOT NULL FOREIGN KEY REFERENCES TYPE(typeID),
	amount decimal(18, 0) NOT NULL default(0),
	note ntext,
	time DATETIME NOT NULL DEFAULT(GETDATE()),
)

CREATE TABLE BILLTYPE(
	typeID int primary key,
	type nvarchar(50)
)

INSERT INTO BILLTYPE(typeID, type)
VALUES(1, N'Hóa đơn tiền điện'),
(2, N'Hóa đơn tiền nước'),
(3, N'Học phí'),
(4, N'Phí chung cư'),
(5, N'Hóa đơn Internet')

CREATE TABLE BILL(
	billID int identity(100000,1) PRIMARY KEY,
	custName nvarchar(200) not null,
	amount decimal(18, 0) NOT NULL,
	typeID int NOT NULL FOREIGN KEY REFERENCES BILLTYPE(typeID),
	fromDate DATE not null,
	toDate as (DATEADD(month, 1, fromDate)),
	isPay bit default 0
)

INSERT INTO BILL(custName, amount, typeID, fromDate)
VALUES(N'Nguyễn Xuân Việt', 130000, 1, '2022-01-10'),
(N'Lê Văn Huy', 1500000, 3, '2022-01-11'),
(N'Trần Thế Sơn', 259000, 5, '2022-03-05'),
(N'Nguyễn Duy Mạnh', 3450000, 4, '2022-01-22'),
(N'Dương Đình Khanh', 170000, 2, '2022-02-01'),
(N'Võ Thị Trà My', 700000, 1, '2022-01-30'),
(N'Trần Quốc Tuấn', 2000000, 3, '2022-02-01'),
(N'Lê Văn Khôi', 250000, 5, '2022-02-28'),
(N'Dương Thùy Linh', 4000000, 4, '2022-02-01'),
(N'Mai Phương Hằng', 312000, 5, '2022-03-02'),
(N'Nguyễn Phương Nhi', 842000, 2, '2022-04-01'),
(N'Lê Minh Khôi', 640000, 1, '2022-04-28'),
(N'Nguyễn Quang Hoàng', 2500000, 3, '2022-03-01'),
(N'Phạm Thị Luyến', 4260000, 4, '2022-06-29'),
(N'Đặng Quang Hải', 634000, 5, '2022-07-06'),
(N'Bùi Khắc Nhật', 873000, 1, '2022-08-28'),
(N'Võ Thùy Trang', 100000, 2, '2022-09-01'),
(N'Trần Diễm My', 8200000, 3, '2022-10-28'),
(N'Trương Tuấn Kiệt', 531000, 2, '2022-11-29'),
(N'Nguyễn Hải Yến', 12000000, 4, '2022-12-01')
select * from CUSTOMER
select * from account
select * from MESSAGE