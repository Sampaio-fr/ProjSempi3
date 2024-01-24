CREATE OR REPLACE PROCEDURE prcUS212TransferInputsToSensorReadings(userCallerID IN SYSTEMUSER.ID%type, numberValid OUT NUMBER,
                                                                   numberInvalid OUT NUMBER) AS
    numValid    NUMBER(10, 0) := 0;
    numInvalid  NUMBER(10, 0) := 0;
    CUR         SYS_REFCURSOR;
    reading     VARCHAR2(25);
    idSen       VARCHAR2(5);
    senType     VARCHAR2(2);
    value       NUMBER(3);
    uniqueNum   NUMBER(2);
    readingDate date;
    counter     NUMBER(10, 0) := 0;
    id_processo number;

BEGIN
    insert into processo_leitura(nr_registos_inseridos, nr_registos_com_erro) values (0,0)returning id into id_processo;
    open CUR for SELECT * FROM input_sensor;
    LOOP
        FETCH CUR into reading;
        EXIT WHEN CUR%NOTFOUND;
        if (FNCUS212ISVALIDREADING(reading, idSen, senType, value, uniqueNum, readingDate)) then
            numValid := numValid + 1;
            SELECT count(*) into counter FROM SENSOR WHERE SENSOR.ID = idSen;
            if (counter = 0) THEN
                INSERT INTO SENSOR(id, sensortype, uniquenumber) VALUES (idSen, senType, uniqueNum);
            end if;
            INSERT INTO Data_Sensor(data, SENSOR, READING,processo_leitura) VALUES (readingDate, idSen, value, id_processo);
            DELETE INPUT_SENSOR WHERE ID = rid;
        else
            numInvalid := numInvalid + 1;
        end if;
    end LOOP;
    UPDATE processo
    SET nr_registos_inseridos = numValid, nr_registos_com_erro= numInvalid
    WHERE id = id_processo;
    numberValid := numValid;
    numberInvalid := numInvalid;
end;
