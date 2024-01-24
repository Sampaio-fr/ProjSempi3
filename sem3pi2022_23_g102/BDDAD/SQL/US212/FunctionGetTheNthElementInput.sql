CREATE OR REPLACE FUNCTION fncUS212GetTheNthSensorReading(entryNumber IN NUMBER(20, 0)) RETURN VARCHAR2(25) AS
    result   VARCHAR2(25);
    tmp      VARCHAR2(25);
    readings NUMBER(20, 0);
    cur      SYS_REFCURSOR;
    tmpC     NUMBER(20, 0);
BEGIN
    result := NULL;
    SELECT count(*) into readings FROM input_sensor;
    if (entryNumber > readings) THEN
        RAISE_APPLICATION_ERROR(-20005, 'There is no entry for the ' || entryNumber || ' position! There are only ' ||
                                        readings || ' entries!');
    end if;
    OPEN cur FOR SELECT * from input_sensor;
    LOOP
        FETCH cur INTO tmp;
        EXIT WHEN cur%notfound;
        if (tmpC = entryNumber) THEN
            result := tmp;
        end if;
        tmpc := tmpC + 1;
    end loop;
    close cur;
    return result;
end;