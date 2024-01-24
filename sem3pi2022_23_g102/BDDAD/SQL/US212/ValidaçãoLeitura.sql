CREATE OR REPLACE FUNCTION fncUS212IsValidReading(reading IN varchar,
                                                  id OUT VARCHAR2,
                                                  sensorType OUT VARCHAR2,
                                                  value OUT NUMBER,
                                                  uniqueNum OUT NUMBER,
                                                  readingDate OUT date) RETURN boolean AS

    iden       VARCHAR2(5);
    senType    VARCHAR2(2);
    val        VARCHAR2(3);
    idNum      VARCHAR2(2);
    charDate   VARCHAR2(12);
    flag       BOOLEAN      := TRUE;
    dateformat varchar2(15) := 'DDMMYYYYHHMI';

BEGIN

    iden := SUBSTR(reading, 0, 5);
    senType := SUBSTR(reading, 6, 2);
    val := SUBSTR(reading, 8, 3);
    idNum := SUBSTR(reading, 11, 2);
    charDate := SUBSTR(reading, 13);

    if (iden is null OR senType is null OR val is null OR idNum is null OR charDate is null) then
        flag := FALSE;
    end if;
    id := iden;
    if (NOT (senType = 'HS' OR senType = 'PL' OR senType = 'TS' OR senType = 'VV' OR senType = 'TA'
        OR senType = 'HA' OR senType = 'PA')) THEN
        flag := false;
    end if;
    sensorType := senType;
    if (TO_NUMBER(val, '999') > 100 OR TO_NUMBER(val, '999') < 0) THEN
        flag := false;
    end if;
    value := TO_NUMBER(val, '999');
    uniqueNum := TO_NUMBER(idNum, '99');
    readingDate := TO_DATE(charDate, dateformat);
    return flag;
EXCEPTION
    WHEN OTHERS THEN
        return false;
end;