use assignment
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Bảng Users
CREATE TABLE [dbo].[Users](
    [user_id] INT PRIMARY KEY IDENTITY(1,1),
    [name] NVARCHAR(255),
    [dob] DATE,
    [gender] BIT,
    [email] NVARCHAR(255),
    [number] NVARCHAR(20),
    [address] NVARCHAR(255)
);

-- Bảng Account
CREATE TABLE [dbo].[Account](
    [account_id] INT PRIMARY KEY IDENTITY(1,1),
    [username] NVARCHAR(255) NOT NULL,
    [password] NVARCHAR(255) NOT NULL,
    [role] INT NOT NULL
);

-- Bảng Account_User
CREATE TABLE [dbo].[Account_User](
    [account_id] INT,
    [user_id] INT,
    FOREIGN KEY ([account_id]) REFERENCES [Account]([account_id]),
    FOREIGN KEY ([user_id]) REFERENCES [Users]([user_id])
);

-- Bảng Categories
CREATE TABLE [dbo].[Categories](
    [category_id] INT NOT NULL,
    [name] NVARCHAR(50) NULL,
    [describe] NVARCHAR(MAX) NULL,
    CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
    (
        [category_id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY];

-- Bảng Products
CREATE TABLE [dbo].[Products](
    [id] NCHAR(10) NOT NULL,
    [name] NVARCHAR(MAX) NULL,
    [quantity] INT NULL,
    [price] MONEY NULL,
    [releaseDate] DATE NULL,
    [describe] NVARCHAR(MAX) NULL,
    [image] NVARCHAR(MAX) NULL,
    [cid] int null,
    CONSTRAINT [PK_Phone] PRIMARY KEY CLUSTERED 
    (
        [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY];

-- Bảng Orders
CREATE TABLE [dbo].[Orders](
    [order_id] INT PRIMARY KEY IDENTITY(1,1),
    [user_id] INT,
    [product_id] NCHAR(10),
    [Quantity] INT,
    [total_price] FLOAT,
    [date] DATE,
    FOREIGN KEY ([user_id]) REFERENCES [Users]([user_id]),
    FOREIGN KEY ([product_id]) REFERENCES [Products]([id])
);

-- Insert dữ liệu vào bảng Categories
INSERT [dbo].[Categories] ([category_id], [name], [describe]) VALUES (1, N'iPhone', N'Đẹp rực rỡ rạng ngời');
INSERT [dbo].[Categories] ([category_id], [name], [describe]) VALUES (2, N'Samsung', N'thời thượng');
INSERT [dbo].[Categories] ([category_id], [name], [describe]) VALUES (3, N'Oppo', N'dành cho sinh viên sành điệu');
INSERT [dbo].[Categories] ([category_id], [name], [describe]) VALUES (4, N'Vsmart', N'cho 1 Việt Nam đẹp đẽ');
INSERT [dbo].[Categories] ([category_id], [name], [describe]) VALUES (5, N'SS7', N'Mong Manh dễ vỡ');

-- Insert dữ liệu vào bảng Products
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ip1       ', N'iphone 12 series', 15, 19000.0000, CAST(0x12430B00 AS Date), N'Chiếc iPhone siêu nhỏ gọn nhưng mang trên mình sức mạnh không đối thủ. iPhone 12 mini là sự lựa chọn hoàn hảo cho những ai đang cần một chiếc điện thoại có thể làm mọi thứ nhưng lại nằm gọn trong lòng bàn tay và độ tiện dụng đáng kinh ngạc.', N'images/ip1.jpg', 1)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ip2       ', N'iphone 11 series', 30, 16000.0000, CAST(0xA5410B00 AS Date), N'Chiếc iPhone siêu nhỏ gọn nhưng mang trên mình sức mạnh không đối thủ. iPhone 12 mini là sự lựa chọn hoàn hảo cho những ai đang cần một chiếc điện thoại có thể làm mọi thứ nhưng lại nằm gọn trong lòng bàn tay và độ tiện dụng đáng kinh ngạc.', N'images/ip2.jpg', 1)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ip3       ', N'iphone X series', 22, 13000.0000, CAST(0x37400B00 AS Date), N'Chiếc iPhone siêu nhỏ gọn nhưng mang trên mình sức mạnh không đối thủ. iPhone 12 mini là sự lựa chọn hoàn hảo cho những ai đang cần một chiếc điện thoại có thể làm mọi thứ nhưng lại nằm gọn trong lòng bàn tay và độ tiện dụng đáng kinh ngạc.', N'images/ip3.jpg', 1)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'opp1      ', N'oppo find x series', 60, 13000.0000, CAST(0x93400B00 AS Date), N'OPPO Reno4 - Chiếc điện thoại với cấu hình mạnh mẽ và công nghệ sạc siêu nhanh sẽ giúp bạn có được hiệu suất cao để trải nghiệm những điều thú vị trong cuộc sống, nhất là trên bộ tứ camera đẳng cấp cùng thiết kế từ nhà OPPO mà ai cũng phải ngước nhìn.

', N'images/opp1.jpg', 3)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'opp2      ', N'oppo find x series', 60, 13000.0000, CAST(0x93400B00 AS Date), N'OPPO Reno4 - Chiếc điện thoại với cấu hình mạnh mẽ và công nghệ sạc siêu nhanh sẽ giúp bạn có được hiệu suất cao để trải nghiệm những điều thú vị trong cuộc sống, nhất là trên bộ tứ camera đẳng cấp cùng thiết kế từ nhà OPPO mà ai cũng phải ngước nhìn.

', N'images/opp2.jpg', 3)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'opp3      ', N'oppo find x series', 60, 13000.0000, CAST(0x93400B00 AS Date), N'OPPO Reno4 - Chiếc điện thoại với cấu hình mạnh mẽ và công nghệ sạc siêu nhanh sẽ giúp bạn có được hiệu suất cao để trải nghiệm những điều thú vị trong cuộc sống, nhất là trên bộ tứ camera đẳng cấp cùng thiết kế từ nhà OPPO mà ai cũng phải ngước nhìn.

', N'images/opp3.jpg', 3)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'opp4      ', N'oppo find x series', 60, 13000.0000, CAST(0x93400B00 AS Date), N'OPPO Reno4 - Chiếc điện thoại với cấu hình mạnh mẽ và công nghệ sạc siêu nhanh sẽ giúp bạn có được hiệu suất cao để trải nghiệm những điều thú vị trong cuộc sống, nhất là trên bộ tứ camera đẳng cấp cùng thiết kế từ nhà OPPO mà ai cũng phải ngước nhìn.

', N'images/opp4.jpg', 3)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'opp5      ', N'oppo find x series', 60, 13000.0000, CAST(0x93400B00 AS Date), N'OPPO Reno4 - Chiếc điện thoại với cấu hình mạnh mẽ và công nghệ sạc siêu nhanh sẽ giúp bạn có được hiệu suất cao để trải nghiệm những điều thú vị trong cuộc sống, nhất là trên bộ tứ camera đẳng cấp cùng thiết kế từ nhà OPPO mà ai cũng phải ngước nhìn.

', N'images/opp5.jpg', 3)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ss1       ', N'Galaxy Z series', 12, 20000.0000, CAST(0x01420B00 AS Date), N'Samsung Galaxy Note 20 Ultra được chế tác từ những vật liệu cao cấp hàng đầu hiện nay, với sự tỉ mỉ và chất lượng gia công thượng thừa, tạo nên chiếc điện thoại đẹp hơn những gì bạn có thể tưởng tượng.', N'images/ss1.jpg', 2)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ss2       ', N'galaxy note series', 12, 18000.0000, CAST(0xA9410B00 AS Date), N'Samsung Galaxy Note 20 Ultra được chế tác từ những vật liệu cao cấp hàng đầu hiện nay, với sự tỉ mỉ và chất lượng gia công thượng thừa, tạo nên chiếc điện thoại đẹp hơn những gì bạn có thể tưởng tượng.', N'images/ss2.jpg', 2)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ss3       ', N'galaxy F series', 34, 16000.0000, CAST(0x3B400B00 AS Date), N'Samsung Galaxy Note 20 Ultra được chế tác từ những vật liệu cao cấp hàng đầu hiện nay, với sự tỉ mỉ và chất lượng gia công thượng thừa, tạo nên chiếc điện thoại đẹp hơn những gì bạn có thể tưởng tượng.', N'images/ss3.jpg', 2)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ss4       ', N'galaxy HHH', 19, 15000.0000, CAST(0xCE3E0B00 AS Date), N'Samsung Galaxy Note 20 Ultra được chế tác từ những vật liệu cao cấp hàng đầu hiện nay, với sự tỉ mỉ và chất lượng gia công thượng thừa, tạo nên chiếc điện thoại đẹp hơn những gì bạn có thể tưởng tượng.', N'images/ss4.jpg', 2)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ss5       ', N'galaxy KKK', 52, 14000.0000, CAST(0x4C3C0B00 AS Date), N'Samsung Galaxy Note 20 Ultra được chế tác từ những vật liệu cao cấp hàng đầu hiện nay, với sự tỉ mỉ và chất lượng gia công thượng thừa, tạo nên chiếc điện thoại đẹp hơn những gì bạn có thể tưởng tượng.', N'images/ss5.jpg', 2)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ss6       ', N'galaxy note series', 12, 18000.0000, CAST(0xA9410B00 AS Date), N'Samsung Galaxy Note 20 Ultra du?c ch? tác t? nh?ng v?t li?u cao c?p hàng d?u hi?n nay, v?i s? t? m? và ch?t lu?ng gia công thu?ng th?a, t?o nên chi?c di?n tho?i d?p hon nh?ng gì b?n có th? tu?ng tu?ng.', N'images/ss2.jpg', 2)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'ss7       ', N'galaxy note series', 12, 18000.0000, CAST(0xA9410B00 AS Date), N'Samsung Galaxy Note 20 Ultra du?c ch? tác t? nh?ng v?t li?u cao c?p hàng d?u hi?n nay, v?i s? t? m? và ch?t lu?ng gia công thu?ng th?a, t?o nên chi?c di?n tho?i d?p hon nh?ng gì b?n có th? tu?ng tu?ng.', N'images/ss2.jpg', 2)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'vsm1      ', N'Vsmart Joy 4 3GB-64GB', 60, 13000.0000, CAST(0x93400B00 AS Date), N'smart Live 4 6GB-64GB sở hữu cấu hình cực đỉnh, đưa bạn đến trải nghiệm giải trí bất tận với dung lượng pin lớn, màn hình tuyệt đẹp và 4 camera sau AI đầy ấn tượng.

', N'images/vsm1.jpg', 4)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'vsm2      ', N'Vsmart Joy 4 3GB-64GB', 60, 13000.0000, CAST(0x93400B00 AS Date), N'smart Live 4 6GB-64GB sở hữu cấu hình cực đỉnh, đưa bạn đến trải nghiệm giải trí bất tận với dung lượng pin lớn, màn hình tuyệt đẹp và 4 camera sau AI đầy ấn tượng.

', N'images/vsm2.jpg', 4)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'vsm3      ', N'Vsmart Joy 4 3GB-64GB', 60, 13000.0000, CAST(0x93400B00 AS Date), N'smart Live 4 6GB-64GB sở hữu cấu hình cực đỉnh, đưa bạn đến trải nghiệm giải trí bất tận với dung lượng pin lớn, màn hình tuyệt đẹp và 4 camera sau AI đầy ấn tượng.

', N'images/vsm3.jpg', 4)
INSERT [dbo].[Products] ([id], [name], [quantity], [price], [releaseDate], [describe], [image], [cid]) VALUES (N'vsm4      ', N'Vsmart Joy 4 3GB-64GB', 60, 13000.0000, CAST(0x93400B00 AS Date), N'smart Live 4 6GB-64GB sở hữu cấu hình cực đỉnh, đưa bạn đến trải nghiệm giải trí bất tận với dung lượng pin lớn, màn hình tuyệt đẹp và 4 camera sau AI đầy ấn tượng.

', N'images/vsm4.jpg', 4)
-- Thêm dữ liệu cho các sản phẩm và danh mục khác
-- Chèn giá trị mẫu cho bảng Users
INSERT INTO [dbo].[Users] ([name], [dob], [gender], [email], [number], [address])
VALUES 
    (N'Nguyễn Văn Thắng', '2003-11-08', 1, N'nguyenvanthang08112003@gmail.com', N'123456789', N'123 Main St'),
    (N'Nguyễn Văn Vinh', '2005-05-22', 0, N'thangnvhe171327@fpt.edu.vn', N'987654321', N'456 Oak St');

-- Chèn giá trị mẫu cho bảng Orders
INSERT INTO [dbo].[Orders] ([user_id], [product_id], [Quantity], [total_price], [date])
VALUES 
    (2, N'ip1       ', 2, 38000.00, '2024-03-03'),
    (2, N'opp3      ', 1, 13000.00, '2024-03-02');

-- Chèn giá trị mẫu cho bảng Account
INSERT INTO [dbo].[Account] ([username], [password], [role])
VALUES 
    (N'admin', N'123', 1),
    (N'vinh', N'123', 2);

-- Chèn giá trị mẫu cho bảng Account_User
INSERT INTO [dbo].[Account_User] ([account_id], [user_id])
VALUES 
    (1, 1),
    (2, 2);
-- Ràng buộc khóa ngoại của bảng Products
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Phone_Category] FOREIGN KEY([cid])
REFERENCES [dbo].[Categories] ([category_id])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Phone_Category]
GO
