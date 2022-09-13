

-- History
CREATE TABLE History (
    ExtraId     INT         NOT NULL COMMENT 'ExtraId', -- ExtraId
    MyLatitude  DOUBLE      NOT NULL COMMENT 'MyLatitude', -- MyLatitude
    MyLongitude DOUBLE      NOT NULL COMMENT 'Mylongitude', -- Mylongitude
    VisitedDate VARCHAR(50) NULL     COMMENT 'VisitedDate' -- VisitedDate
)
COMMENT 'History';

-- WIFI_API_TABLE
CREATE TABLE WifiAPI (
    AuthNum     VARCHAR(50)  NOT NULL COMMENT 'AuthNum', -- AuthNum
    Resident    VARCHAR(50)  NOT NULL COMMENT 'Resident', -- Resident
    WifiName    VARCHAR(50)  NULL     COMMENT 'WifiName', -- WifiName
    RoadAdd     VARCHAR(50)  NULL     COMMENT 'RoadAdd', -- RoadAdd
    DetailAdd   VARCHAR(255) NULL     COMMENT 'DetailAdd', -- DetailAdd
    NetWorkSpot VARCHAR(50)  NULL     COMMENT 'NetWorkSpot', -- NetWorkSpot
    InstallSpot VARCHAR(255) NULL     COMMENT 'InstallSpot', -- InstallSpot
    Agency      VARCHAR(50)  NULL     COMMENT 'Agency', -- Agency
    ServiceType VARCHAR(50)  NULL     COMMENT 'ServiceType', -- ServiceType
    InstallType VARCHAR(50)  NULL     COMMENT 'InstallType', -- InstallType
    InstallDate INT          NULL     COMMENT 'InstallDate', -- InstallDate
    SideType    VARCHAR(50)  NULL     COMMENT 'SideType', -- SideType
    ConnectView VARCHAR(50)  NULL     COMMENT 'ConnectView', -- ConnectView
    Longitude   DOUBLE       NOT NULL COMMENT 'Longitude', -- Longitude
    Latitude    DOUBLE       NOT NULL COMMENT 'Latitude', -- Latitude
    ContactDate VARCHAR(50)  NULL     COMMENT 'ContactDate' -- ContactDate
)
COMMENT 'WIFI_API_TABLE';

-- WIFI_API_TABLE
ALTER TABLE WifiAPI
    ADD CONSTRAINT PK_WifiAPI -- WIFI_API_TABLE 기본키
        PRIMARY KEY (
            AuthNum -- AuthNum
        );
        
-- history table primary key auto increment
    
alter table history modify ExtraId int not null auto_increment primary key first;