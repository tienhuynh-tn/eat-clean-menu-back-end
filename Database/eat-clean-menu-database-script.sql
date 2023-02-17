CREATE DATABASE ECM
GO;

USE ECM
GO;

------------------------------ CREATE TABLE ------------------------------
CREATE TABLE [Users] (
    Id INT IDENTITY(1, 1) NOT NULL,
    Gmail VARCHAR(100) NOT NULL,
    Fullname NVARCHAR(100) NOT NULL,
    Gender BIT NULL,
    DateOfBirth DATE NULL,
    Phone CHAR(10) NULL,
    Address NVARCHAR(200) NULL,
    District NVARCHAR(50) NULL,
    Province NVARCHAR(50) NULL,
    Avatar NTEXT NULL,
    SubscriptionStatus VARCHAR(20) NULL,
    SubscriptionDate DATETIME NULL,
    SubscriptionType NVARCHAR(20) NULL
)
GO;

CREATE TABLE [UserDietaryInfo] (
    Id INT IDENTITY(1, 1) NOT NULL,
    UserId INT NOT NULL,
    DietTarget NVARCHAR(20) NOT NULL,
    UserAge INT NOT NULL,
    UserHeight FLOAT NOT NULL,
    UserWeight FLOAT NOT NULL,
    ActivityRate NVARCHAR(50) NOT NULL,
    BMI FLOAT NOT NULL,
    BMR FLOAT NOT NULL,
    CaloriesConsumed FLOAT NULL
)
GO;

CREATE TABLE [UserDietaryTracking] (
    Id INT IDENTITY(1, 1) NOT NULL,
    UserId INT NOT NULL,
    Datetime DATETIME NOT NULL,
    UserWeight FLOAT NULL,
    UserCalories FLOAT NULL
)
GO;

CREATE TABLE [Menu] (
    Id INT IDENTITY(1, 1) NOT NULL,
    UserId INT NOT NULL,
    MenuPeriodStartDate DATE NULL,
    MenuPeriodEndDate DATE NULL
)
GO;

CREATE TABLE [Dish] (
    Id INT IDENTITY(1, 1) NOT NULL,
    Name NVARCHAR(100) NOT NULL,
    Calories FLOAT NOT NULL,
    Image NTEXT NOT NULL,
    Type NVARCHAR(10) NULL
)
GO;

CREATE TABLE [Menu_Dish] (
    Id INT IDENTITY(1, 1) NOT NULL,
    MenuId INT NOT NULL,
    DishId INT NOT NULL,
    MealDate DATE NOT NULL,
    MealTime NVARCHAR(10) NOT NULL,
    Status NVARCHAR(20) NULL
)
GO;

CREATE TABLE [Ingredient] (
    Id INT IDENTITY(1, 1) NOT NULL,
    DishId INT NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    Quantity NVARCHAR(20) NULL,
    Type NVARCHAR(20) NOT NULL
)
GO;

CREATE TABLE [Recipe] (
    Id INT IDENTITY(1, 1) NOT NULL,
    DishId INT NOT NULL,
    StepNo INT NOT NULL,
    Description NTEXT NOT NULL
)
GO;

CREATE TABLE [Tip] (
    Id INT IDENTITY(1, 1) NOT NULL,
    Title NVARCHAR(200) NOT NULL,
    Content NTEXT NOT NULL,
    Image NTEXT NOT NULL,
    CreatedDate DATETIME NOT NULL
)
GO;

------------------------------ CREATE CONSTRAINT ------------------------------
--- PRIMARY KEY ---
ALTER TABLE Users ADD CONSTRAINT PK_Users PRIMARY KEY (Id);
ALTER TABLE [UserDietaryInfo] ADD CONSTRAINT PK_UserDietaryInfo PRIMARY KEY (Id);
ALTER TABLE [UserDietaryTracking] ADD CONSTRAINT PK_UserDietaryTracking PRIMARY KEY (Id);
ALTER TABLE [Menu] ADD CONSTRAINT PK_Menu PRIMARY KEY (Id);
ALTER TABLE [Dish] ADD CONSTRAINT PK_Dish PRIMARY KEY (Id);
ALTER TABLE [Menu_Dish] ADD CONSTRAINT PK_Menu_Dish PRIMARY KEY (Id);
ALTER TABLE [Ingredient] ADD CONSTRAINT PK_Ingredient PRIMARY KEY (Id);
ALTER TABLE [Recipe] ADD CONSTRAINT PK_Recipe PRIMARY KEY (Id);
ALTER TABLE [Tip] ADD CONSTRAINT PK_Tip PRIMARY KEY (Id);

--- FOREIGN KEY ---
ALTER TABLE [UserDietaryInfo] ADD CONSTRAINT FK_UserDietaryInfo_Users
    FOREIGN KEY (UserId) REFERENCES Users(Id);

ALTER TABLE [UserDietaryTracking] ADD CONSTRAINT FK_UserDietaryTracking_Users
    FOREIGN KEY (UserId) REFERENCES Users(Id);

ALTER TABLE [Menu] ADD CONSTRAINT FK_Menu_Users
    FOREIGN KEY (UserId) REFERENCES Users(Id);

ALTER TABLE [Menu_Dish] ADD CONSTRAINT FK_MenuDish_Menu
    FOREIGN KEY (MenuId) REFERENCES [Menu](Id);

ALTER TABLE [Menu_Dish] ADD CONSTRAINT FK_MenuDish_Dish
    FOREIGN KEY (DishId) REFERENCES [Dish](Id);

ALTER TABLE [Ingredient] ADD CONSTRAINT FK_Ingredient_Dish
    FOREIGN KEY (DishId) REFERENCES [Dish](Id);

ALTER TABLE [Recipe] ADD CONSTRAINT FK_Recipe_Dish
    FOREIGN KEY (DishId) REFERENCES [Dish](Id);