#ifndef SENS_STRUCT_H
#define SENS_STRUCT_H
typedef struct{

    unsigned short id;
    unsigned char sensor_type;
    unsigned short max_limit; // limites do sensor
    unsigned short min_limit;
    unsigned long frequency; // frequency de leituras (em segundos)
    unsigned long readings_size; // tamanho do array de leituras
    unsigned short *readings; // array de leituras diárias

}Sensor;

typedef struct{

    int MAX_TEMP;
    int MIN_TEMP;
    int MAX_VELC;
    int MIN_VELC;
    int MAX_DIR;
    int MIN_DIR;
    int MIN_HUMD_ATM;
    int MAX_HUMD_ATM;
    int MIN_HUMD_GROUND;
    int MAX_HUMD_GROUND;
    int MIN_HUMD_GROUND_RAINING;
    int MAX_HUMD_GROUND_RAINING;
    int MIN_PLUVIO;
    int MAX_PLUVIO_HIGHEST_TEMP;
    int MAX_PLUVIO_HIGH_TEMP;
    int MAX_PLUVIO_MID_TEMP;
    int MAX_PLUVIO_LOW_TEMP;
    int LOW_TEMP;
    int MID_TEMP;
    int HIGH_TEMP;
}Limits;



#endif