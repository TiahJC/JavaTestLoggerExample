CREATE DATABASE assettracking;
CREATE USER assettrackinguser WITH ENCRYPTED PASSWORD 'p@55w0rd123';
GRANT ALL PRIVILEGES ON DATABASE assettracking TO assettrackinguser;

ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO assettrackinguser;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO assettrackinguser;

CREATE TABLE IF NOT EXISTS employee (
  employee_id SERIAL PRIMARY KEY
  , employee_name VARCHAR(30) NOT NULL
  , employee_pw VARCHAR(300)
  , employee_group VARCHAR(30) NOT NULL
);


CREATE TABLE IF NOT EXISTS assets (
  assets_id SERIAL PRIMARY KEY
  , assets_sn VARCHAR(30) NOT NULL
  , assets_desc VARCHAR(300)
  , assets_cd DATE NOT NULL DEFAULT CURRENT_DATE 
  , assets_empID INT FOREIGN KEY REFERENCES employee(employee_id)
  , assets_handover DATE NOT NULL DEFAULT CURRENT_DATE
  , assets_status VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS assets (
  assets_id SERIAL PRIMARY KEY
  , assets_sn VARCHAR(30) NOT NULL
  , assets_desc VARCHAR(300)
  ,	assets_empID INT
  , assets_cd DATE NOT NULL DEFAULT CURRENT_DATE 
  , assets_handover DATE NOT NULL DEFAULT CURRENT_DATE
  , assets_status VARCHAR(30) NOT NULL
  , CONSTRAINT fk_assets_empID
		FOREIGN KEY(assets_empID)
			REFERENCES employee(employee_id)
);

CREATE TABLE IF NOT EXISTS trail (
  trail_id SERIAL PRIMARY KEY
  , trail_time VARCHAR(30) NOT NULL
  , trail_assetID INT 
  , trail_desc VARCHAR(300) NOT NULL
  , CONSTRAINT fk_trail_assetsid
      FOREIGN KEY(trail_assetID)
        REFERENCES assets(assets_id)
);

GRANT ALL ON ALL TABLES IN SCHEMA public TO assettrackinguser;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO assettrackinguser;