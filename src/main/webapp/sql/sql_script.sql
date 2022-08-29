-- 고객정보
CREATE TABLE TABLE (
)
COMMENT '고객정보';

-- History
CREATE TABLE History (
	MyLatitude  DOUBLE      NOT NULL COMMENT 'MyLatitude', -- MyLatitude
	Mylogitude  DOUBLE      NOT NULL COMMENT 'Mylogitude', -- Mylogitude
	ExtraId     INT         NOT NULL COMMENT 'ExtraId', -- ExtraId
	VisitedDate DATE        NULL     COMMENT 'VisitedDate', -- VisitedDate
	AuthNum     VARCHAR(50) NULL     COMMENT 'AuthNum', -- AuthNum
	Resident    VARCHAR(50) NULL     COMMENT 'Resident' -- Resident
)
COMMENT 'History';

-- History
ALTER TABLE History
	ADD CONSTRAINT PK_History -- History 기본키
		PRIMARY KEY (
			MyLatitude, -- MyLatitude
			Mylogitude, -- Mylogitude
			ExtraId     -- ExtraId
		);

-- WIFI_API_TABLE
CREATE TABLE WifiAPI (
	AuthNum     VARCHAR(50)  NOT NULL COMMENT 'AuthNum', -- AuthNum
	Resident    VARCHAR(50)  NOT NULL COMMENT 'Resident', -- Resident
	WifiName    VARCHAR(50)  NULL     COMMENT 'WifiName', -- WifiName
	RoadAdd     VARCHAR(50)  NULL     COMMENT 'RoadAdd', -- RoadAdd
	DetailAdd   VARCHAR(255) NULL     COMMENT 'DetailAdd', -- DetailAdd
	InstallSpot VARCHAR(255) NULL     COMMENT 'InstallSpot', -- InstallSpot
	InstallType VARCHAR(50)  NULL     COMMENT 'InstallType', -- InstallType
	Agency      VARCHAR(50)  NULL     COMMENT 'Agency', -- Agency
	ServiceType VARCHAR(50)  NULL     COMMENT 'ServiceType', -- ServiceType
	InstallDate INT          NULL     COMMENT 'InstallDate', -- InstallDate
	SideType    VARCHAR(50)  NULL     COMMENT 'SideType', -- SideType
	NetWorkSpot VARCHAR(50)  NULL     COMMENT 'NetWorkSpot', -- NetWorkSpot
	Latitude    DOUBLE       NOT NULL COMMENT 'Latitude', -- Latitude
	Logitude    DOUBLE       NOT NULL COMMENT 'Logitude', -- Logitude
	ContactDate DATE         NULL     COMMENT 'ContactDate' -- ContactDate
)
COMMENT 'WIFI_API_TABLE';

-- WIFI_API_TABLE
ALTER TABLE WifiAPI
	ADD CONSTRAINT PK_WifiAPI -- WIFI_API_TABLE 기본키
		PRIMARY KEY (
			AuthNum,  -- AuthNum
			Resident  -- Resident
		);

-- History
ALTER TABLE History
	ADD CONSTRAINT FK_WifiAPI_TO_History -- WIFI_API_TABLE -> History
		FOREIGN KEY (
			AuthNum,  -- AuthNum
			Resident  -- Resident
		)
		REFERENCES WifiAPI ( -- WIFI_API_TABLE
			AuthNum,  -- AuthNum
			Resident  -- Resident
		);