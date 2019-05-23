DROP DATABASE IF EXISTS mbshop;
DROP USER IF EXISTS mbshop;
CREATE USER mbshop;
SET PASSWORD FOR mbshop = 'himitu';
CREATE DATABASE mbshop;
GRANT ALL ON mbshop.* TO mbshop;