CREATE SCHEMA IF NOT EXISTS ResidentialMapper;

USE ResidentialMapper;

DROP TABLE IF EXISTS FavoriteBusiness;
DROP TABLE IF EXISTS Recommendation;
DROP TABLE IF EXISTS Review;
DROP TABLE IF EXISTS BusinessCategory;
DROP TABLE IF EXISTS RegionHouseValue;
DROP TABLE IF EXISTS RegionHouseRental;
DROP TABLE IF EXISTS YelpBusiness;
DROP TABLE IF EXISTS Region;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS User;


CREATE TABLE User (
	UserName VARCHAR(255),
    Password VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    PhoneNumber VARCHAR(255) NOT NULL,
    CONSTRAINT pk_User_UserName PRIMARY KEY (UserName)
);


CREATE TABLE Category (
	CategoryId INTEGER AUTO_INCREMENT,
    Type VARCHAR(255) NOT NULL,
    CONSTRAINT pk_Category_CategoryId PRIMARY KEY (CategoryId)
);

CREATE TABLE Region (
	ZipCode VARCHAR(255),
    StateName VARCHAR(255),
    City VARCHAR(255),
    CountyName VARCHAR(255),
    CONSTRAINT pk_Region_ZipCode PRIMARY KEY (ZipCode)
);

CREATE TABLE YelpBusiness (
	BusinessId VARCHAR(255),
    Name VARCHAR(255) NOT NULL,
    Address VARCHAR(255) NOT NULL,
    ZipCode VARCHAR(255),
    Latitude DOUBLE NOT NULL,
    Longitude DOUBLE NOT NULL,
    Rating DECIMAL NOT NULL,
    ReviewCount INT NOT NULL,
    isOpen BOOLEAN NOT NULL,
    CONSTRAINT pk_YelpBusiness_BusinessId PRIMARY KEY (BusinessId),
    CONSTRAINT fk_YelpBusiness_ZipCode FOREIGN KEY (ZipCode)
		REFERENCES Region(ZipCode)
	ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE RegionHouseRental(
	ZipCode VARCHAR(255),
    Rental2020 DECIMAL,
    Rental2021 DECIMAL,
    Rental2022 DECIMAL,
    CONSTRAINT pk_RegionHouseRental_ZipCode PRIMARY KEY (ZipCode),
    CONSTRAINT fk_RegionHouseRental_ZipCode FOREIGN KEY (ZipCode)
		REFERENCES Region(ZipCode)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE RegionHouseValue (
	ZipCode VARCHAR(255),
    Value2020 DECIMAL,
    Value2021 DECIMAL,
    Value2022 DECIMAL,
    CONSTRAINT pk_RegionHouseValue_ZipCode PRIMARY KEY (ZipCode),
    CONSTRAINT fk_RegionHouseValue_ZipCode FOREIGN KEY (ZipCode)
		REFERENCES Region(ZipCode)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE BusinessCategory (
	BusinessCategoryId INTEGER AUTO_INCREMENT,
	BusinessId VARCHAR(255),
    CategoryId INTEGER,
    CONSTRAINT pk_BusinessCategory_BusinessCategoryId PRIMARY KEY (BusinessCategoryId),
    CONSTRAINT fk_BusinessCategory_BusinessId FOREIGN KEY (BusinessId)
		REFERENCES YelpBusiness(BusinessId)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_BusinessCategory_CategoryId FOREIGN KEY (CategoryId)
		REFERENCES Category(CategoryId)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Review (
	ReviewId INTEGER AUTO_INCREMENT,
    Rating DECIMAL NOT NULL,
    Content VARCHAR(1024),
    Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    UserName VARCHAR(255),
    BusinessId VARCHAR(255),
    CONSTRAINT pk_Review_ReviewId PRIMARY KEY (ReviewId),
    CONSTRAINT fk_Review_UserName FOREIGN KEY (UserName)
		REFERENCES User(UserName)
        ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Review_BusinessId FOREIGN KEY (BusinessId)
		REFERENCES YelpBusiness(BusinessId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Recommendation (
	RecommendationId INTEGER AUTO_INCREMENT,
    BusinessId VARCHAR(255),
    UserName VARCHAR(255),
    CONSTRAINT pk_Recommendation_FavoriteId PRIMARY KEY (RecommendationId),
    CONSTRAINT fk_Recommendation_BusinessId FOREIGN KEY (BusinessId)
		REFERENCES YelpBusiness(BusinessId)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Recommendation_UserName FOREIGN KEY (UserName)
		REFERENCES User(UserName)
        ON UPDATE CASCADE ON DELETE SET NULL
);


CREATE TABLE FavoriteBusiness (
	FavoriteId INTEGER AUTO_INCREMENT,
    BusinessId VARCHAR(255),
    UserName VARCHAR(255),
    CONSTRAINT pk_FavoriteBusiness_FavoriteId PRIMARY KEY (FavoriteId),
    CONSTRAINT fk_FavoriteBusiness_BusinessId FOREIGN KEY (BusinessId)
		REFERENCES YelpBusiness(BusinessId)
        ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_FavoriteBusiness_UserName FOREIGN KEY (UserName)
		REFERENCES User(UserName)
        ON UPDATE CASCADE ON DELETE SET NULL
);

LOAD DATA INFILE 'E:\ALIGN\\2022summer\\CS5200Database\\project\\csv\\Region.csv' INTO TABLE Region
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;


LOAD DATA INFILE 'E:\ALIGN\\2022summer\\CS5200Database\\project\\csv\\YelpBusiness.csv' INTO TABLE YelpBusiness
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'E:\ALIGN\\2022summer\\CS5200Database\\project\\csv\\RegionHouseRental.csv' INTO TABLE RegionHouseRental
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'E:\ALIGN\\2022summer\\CS5200Database\\project\\csv\\RegionHouseValue.csv' INTO TABLE RegionHouseValue
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'E:\ALIGN\\2022summer\\CS5200Database\\project\\csv\\Category.csv' INTO TABLE Category
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'E:\ALIGN\\2022summer\\CS5200Database\\project\\csv\\BusinessCategory.csv' INTO TABLE BusinessCategory
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;