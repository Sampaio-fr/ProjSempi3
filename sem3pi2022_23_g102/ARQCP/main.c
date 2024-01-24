#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <limits.h>
#include <time.h>
#include <unistd.h>
#include "sensorsStructs.h"
#include "sensores.h"
#include "sensMatrix.h"
#include "pcg32_random.h"
#include "read_dev_random.h"
#include "createCSV.h"

#define MAX_ERRORS 5

#define BUF_SIZE 256

#define COLUMNS 4

// seeds de cada sensor
uint64_t stateTemp;
uint64_t stateVelc;
uint64_t stateDir;
uint64_t stateHumdAtm;
uint64_t stateHumdSolo;
uint64_t statePluvio;

uint64_t incTemp;
uint64_t incVelc;
uint64_t incDir;
uint64_t incHumdAtm;
uint64_t incHumdSolo;
uint64_t incPluvio;

char ult_temp;
unsigned char ult_velc_v;
unsigned short ult_dir_v;
unsigned char ult_pluvio;
unsigned char ult_humd_atm;
unsigned char ult_humd_solo;

char comp_rand_char;
short comp_rand_short;

// inicializar os tamanhos e os vetores de forma global
int sens_temp_vec_size = 0;
int sens_velc_vec_size = 0;
int sens_dir_vec_size = 0;
int sens_atm_vec_size = 0;
int sens_solo_vec_size = 0;
int sens_pluvio_vec_size = 0;
Sensor **vec_sens_temp = NULL;
Sensor **vec_sens_velc_v = NULL;
Sensor **vec_sens_dir_v = NULL;
Sensor **vec_sens_hdm_atm = NULL;
Sensor **vec_sens_hmd_solo = NULL;
Sensor **vec_sens_pluvio = NULL;
unsigned short uni_id = 1;

Limits limits;

// matrix
int matriz_line_size = 0;
short **matrixPtr = NULL;

void run_sensor_temp(Sensor *sens)
{
    int i;
reset_seed_temp:;
    i = 0;
    stateTemp = read_dev_random();
    incTemp = read_dev_random();
    comp_rand_char = pcg32_random(stateTemp, incTemp);
    char temp = sens_temp(ult_temp, comp_rand_char);
    while ((temp > limits.MAX_TEMP) || temp < limits.MIN_TEMP)
    {
        i++;
        temp = sens_temp(ult_temp, comp_rand_char);
        if (i == MAX_ERRORS)
        {
            printf("Temperature sensor seed reseted\n");
            goto reset_seed_temp;
        }
    }
    printf("sensor_id: %hu temp: %hhd\n", sens->id, temp);
    ult_temp = temp;

    sens->readings[sens->readings_size] = (unsigned short)temp;
    sens->readings_size++;
}

void run_sensor_velc(Sensor *sens)
{
    int i;
reset_seed_velc:;
    i = 0;
    stateVelc = read_dev_random();
    incVelc = read_dev_random();
    comp_rand_char = pcg32_random(stateVelc, incVelc);
    unsigned char velc_v = sens_velc_vento(ult_velc_v, comp_rand_char);
    while (velc_v > limits.MAX_VELC || velc_v < limits.MIN_VELC)
    {
        i++;
        velc_v = sens_velc_vento(ult_velc_v, comp_rand_char);

        if (i == MAX_ERRORS)
        {
            printf("Velocity sensor seed reseted\n");
            goto reset_seed_velc;
        }
    }
    printf("sensor_id: %hu velc_v: %hhu\n", sens->id, velc_v);
    ult_velc_v = velc_v;

    sens->readings[sens->readings_size] = (unsigned short)velc_v;
    sens->readings_size++;
}

void run_sensor_dir(Sensor *sens)
{
    int i;
reset_seed_dir:;
    i = 0;
    stateDir = read_dev_random();
    incDir = read_dev_random();
    comp_rand_short = pcg32_random(stateDir, incDir);
    unsigned short dir_v = sens_dir_vento(ult_dir_v, comp_rand_short);
    while (dir_v > limits.MAX_DIR || dir_v < limits.MIN_DIR)
    {
        i++;
        dir_v = sens_dir_vento(ult_dir_v, comp_rand_short);

        if (i == MAX_ERRORS)
        {
            printf("Direction sensor seed reseted\n");
            goto reset_seed_dir;
        }
    }
    printf("sensor_id: %hu dir_v: %hu\n", sens->id, dir_v);
    ult_dir_v = dir_v;

    sens->readings[sens->readings_size] = (unsigned short)dir_v;
    sens->readings_size++;
}

void run_sensor_atm(Sensor *sens)
{
    int i;
reset_seed_atm:;
    i = 0;
    stateHumdAtm = read_dev_random();
    incHumdAtm = read_dev_random();
    comp_rand_char = pcg32_random(stateHumdAtm, incHumdAtm);
    unsigned char humd_atm = sens_humd_atm(ult_humd_atm, ult_pluvio, comp_rand_char);
    while (humd_atm > limits.MAX_HUMD_ATM || humd_atm < limits.MIN_HUMD_ATM)
    {
        i++;
        if (ult_pluvio == 0)
        {
            if (humd_atm < limits.MIN_HUMD_ATM)
            {
                humd_atm = sens_humd_atm(ult_humd_atm, ult_pluvio, comp_rand_char);
            }
        }
        else
        {
            if (humd_atm > limits.MAX_HUMD_ATM)
            {
                humd_atm = sens_humd_atm(ult_humd_atm, ult_pluvio, comp_rand_char);
            }
        }
        if (i == MAX_ERRORS)
        {
            printf("Humitidy ATM sensor seed reseted\n");
            goto reset_seed_atm;
        }
    }
    printf("sensor_id: %hu humdAtm: %hhu\n", sens->id, humd_atm);
    ult_humd_atm = humd_atm;

    sens->readings[sens->readings_size] = (unsigned short)humd_atm;
    sens->readings_size++;
}

void run_sensor_solo(Sensor *sens)
{

    int i;
reset_seed_solo:;
    stateHumdSolo = read_dev_random();
    incHumdSolo = read_dev_random();
    comp_rand_char = pcg32_random(stateHumdSolo, incHumdSolo);
    unsigned char humd_solo = sens_humd_solo(ult_humd_solo, ult_pluvio, comp_rand_char);
    for (i = 1; i <= MAX_ERRORS; i++)
    {
        if (ult_pluvio == 0)
        {
            if (humd_solo < limits.MIN_HUMD_GROUND || humd_solo > limits.MAX_HUMD_GROUND)
            {
                humd_solo = sens_humd_solo(ult_humd_solo, ult_pluvio, comp_rand_char);
            }
            else
            {
                break;
            }
        }
        else
        {
            if (humd_solo < limits.MIN_HUMD_GROUND_RAINING || humd_solo > limits.MAX_HUMD_GROUND_RAINING)
            {
                humd_solo = sens_humd_solo(ult_humd_solo, ult_pluvio, comp_rand_char);
            }
            else
            {
                break;
            }
        }
        if (i == MAX_ERRORS)
        {
            printf("Humidity SOLO sensor seed reseted\n");
            goto reset_seed_solo;
        }
    }
    printf("sensor_id: %hu humdSolo: %hhu\n", sens->id, humd_solo);
    ult_humd_solo = humd_solo;

    sens->readings[sens->readings_size] = (unsigned short)humd_solo;
    sens->readings_size++;
}

void run_sensor_pluvio(Sensor *sens)
{
    int i;
reset_seed_pluvio:;
    i = 0;
    statePluvio = read_dev_random();
    incPluvio = read_dev_random();
    comp_rand_char = pcg32_random(statePluvio, incPluvio);
    unsigned char pluvio = sens_pluvio(ult_pluvio, ult_temp, comp_rand_char);
    while (pluvio < limits.MIN_PLUVIO || pluvio > limits.MAX_PLUVIO_LOW_TEMP)
    {
        i++;
        pluvio = sens_pluvio(ult_pluvio, ult_temp, comp_rand_char);

        if (i == MAX_ERRORS)
        {
            printf("Pluvio sensor seed reseted\n");
            goto reset_seed_pluvio;
        }
    }
    printf("sensor_id: %hu pluvio: %hhu\n", sens->id, pluvio);
    ult_pluvio = pluvio;

    sens->readings[sens->readings_size] = (unsigned short)pluvio;
    sens->readings_size++;
}

void run_sensors()
{
    // variaveis state, a variavel state sera a seed do gerador

    stateTemp = read_dev_random();
    stateVelc = read_dev_random();
    stateDir = read_dev_random();
    stateHumdAtm = read_dev_random();
    stateHumdSolo = read_dev_random();
    statePluvio = read_dev_random();

    incTemp = read_dev_random();
    incVelc = read_dev_random();
    incDir = read_dev_random();
    incHumdAtm = read_dev_random();
    incHumdSolo = read_dev_random();
    incPluvio = read_dev_random();

    ult_temp = sens_temp(pcg32_random(stateTemp, incTemp), pcg32_random(stateTemp, incTemp));
    while (ult_temp < limits.MIN_TEMP || ult_temp > limits.MAX_TEMP)
    {
        stateTemp = read_dev_random();
        incTemp = read_dev_random();
        ult_temp = sens_temp(pcg32_random(stateTemp, incTemp), pcg32_random(stateTemp, incTemp));
    }

    ult_velc_v = sens_velc_vento((unsigned char)pcg32_random(stateVelc, incVelc), (char)pcg32_random(stateVelc, incVelc));
    while (ult_velc_v < limits.MIN_VELC || ult_velc_v > limits.MAX_VELC)
    {
        stateVelc = read_dev_random();
        incVelc = read_dev_random();
        ult_velc_v = sens_velc_vento((unsigned char)pcg32_random(stateVelc, incVelc), (char)pcg32_random(stateVelc, incVelc));
    }

    ult_dir_v = sens_dir_vento((unsigned short)pcg32_random(stateDir, incDir), (short)pcg32_random(stateDir, incDir));
    while (ult_dir_v < limits.MIN_DIR || ult_dir_v > limits.MAX_DIR)
    {
        stateDir = read_dev_random();
        incDir = read_dev_random();
        ult_dir_v = sens_dir_vento((unsigned short)pcg32_random(stateDir, incDir), (short)pcg32_random(stateDir, incDir));
    }

    ult_pluvio = sens_pluvio(pcg32_random(statePluvio, incPluvio), ult_temp, pcg32_random(statePluvio, incPluvio));
    while (ult_pluvio < limits.MIN_PLUVIO || ult_pluvio > limits.MAX_PLUVIO_LOW_TEMP)
    {
        statePluvio = read_dev_random();
        incPluvio = read_dev_random();
        ult_pluvio = sens_pluvio(pcg32_random(statePluvio, incPluvio), ult_temp, pcg32_random(statePluvio, incPluvio));
    }

    ult_humd_atm = sens_humd_atm(pcg32_random(stateHumdAtm, incHumdAtm), ult_pluvio, pcg32_random(stateHumdAtm, incHumdAtm));
    while (ult_humd_atm < limits.MIN_HUMD_ATM || ult_humd_atm > limits.MAX_HUMD_ATM)
    {
        stateHumdAtm = read_dev_random();
        incHumdAtm = read_dev_random();
        ult_humd_atm = sens_humd_atm(pcg32_random(stateHumdAtm, incHumdAtm), ult_pluvio, pcg32_random(stateHumdAtm, incHumdAtm));
    }

    if (ult_pluvio == 0)
    {
        ult_humd_solo = sens_humd_solo(pcg32_random(stateHumdSolo, incHumdSolo), ult_pluvio, pcg32_random(stateHumdSolo, incHumdSolo));
        while (ult_humd_solo < limits.MIN_HUMD_GROUND || ult_humd_solo > limits.MAX_HUMD_GROUND)
        {
            stateHumdSolo = read_dev_random();
            incHumdSolo = read_dev_random();
            ult_humd_solo = sens_humd_solo(pcg32_random(stateHumdSolo, incHumdSolo), ult_pluvio, pcg32_random(stateHumdSolo, incHumdSolo));
        }
    }
    else
    {
        ult_humd_solo = sens_humd_solo(pcg32_random(stateHumdSolo, incHumdSolo), ult_pluvio, pcg32_random(stateHumdSolo, incHumdSolo));
        while (ult_humd_solo < limits.MIN_HUMD_GROUND_RAINING || ult_humd_solo > limits.MAX_HUMD_GROUND_RAINING)
        {
            stateHumdSolo = read_dev_random();
            incHumdSolo = read_dev_random();
            ult_humd_solo = sens_humd_solo(pcg32_random(stateHumdSolo, incHumdSolo), ult_pluvio, pcg32_random(stateHumdSolo, incHumdSolo));
        }
    }

    int counter = 0;
    int i;
    if (sens_temp_vec_size == 0)
    {
        printf("There are no Temperature sensors\n");
    }
    if (sens_velc_vec_size == 0)
    {
        printf("There are no Velocity sensors\n");
    }
    if (sens_dir_vec_size == 0)
    {
        printf("There are no Direction sensors\n");
    }
    if (sens_atm_vec_size == 0)
    {
        printf("There are no Humidity ATM sensors\n");
    }
    if (sens_solo_vec_size == 0)
    {
        printf("There are no Humidity SOLO sensors\n");
    }
    if (sens_pluvio_vec_size == 0)
    {
        printf("There are no Pluviosity sensors\n");
    }
    // um dia tem 864000 segundos
    while (counter < 86400)
    {
        if (sens_temp_vec_size != 0)
        {
            for (i = 0; i < sens_temp_vec_size; i++)
            {
                if (counter % vec_sens_temp[i]->frequency == 0)
                {
                    run_sensor_temp(vec_sens_temp[i]);
                }
            }
        }
        if (sens_velc_vec_size != 0)
        {
            for (i = 0; i < sens_velc_vec_size; i++)
            {
                if (counter % vec_sens_velc_v[i]->frequency == 0)
                {
                    run_sensor_velc(vec_sens_velc_v[i]);
                }
            }
        }
        if (sens_dir_vec_size != 0)
        {
            for (i = 0; i < sens_dir_vec_size; i++)
            {
                if (counter % vec_sens_dir_v[i]->frequency == 0)
                {
                    run_sensor_dir(vec_sens_dir_v[i]);
                }
            }
        }
        if (sens_pluvio_vec_size != 0)
        {
            for (i = 0; i < sens_pluvio_vec_size; i++)
            {
                if (counter % vec_sens_pluvio[i]->frequency == 0)
                {
                    run_sensor_pluvio(vec_sens_pluvio[i]);
                }
            }
        }
        if (sens_atm_vec_size != 0)
        {
            for (i = 0; i < sens_atm_vec_size; i++)
            {
                if (counter % vec_sens_hdm_atm[i]->frequency == 0)
                {
                    run_sensor_atm(vec_sens_hdm_atm[i]);
                }
            }
        }
        if (sens_solo_vec_size != 0)
        {
            for (i = 0; i < sens_solo_vec_size; i++)
            {
                if (counter % vec_sens_hmd_solo[i]->frequency == 0)
                {
                    run_sensor_solo(vec_sens_hmd_solo[i]);
                }
            }
        }
        counter++;
        // sleep(1);
        //  usleep(1000); // 10ms
    }
}

void show_matrix()
{

    if (matrixPtr != NULL)
    {
        free(matrixPtr);
    }

    matrixPtr = calloc(matriz_line_size, sizeof(short *));

    for (int i = 0; i < matriz_line_size; i++)
    {
        matrixPtr[i] = calloc(COLUMNS, sizeof(short));
    }

    // save on matrix
    sensMatrix(vec_sens_temp, vec_sens_velc_v, vec_sens_dir_v, vec_sens_hdm_atm, vec_sens_hmd_solo, vec_sens_pluvio, sens_temp_vec_size, sens_velc_vec_size, sens_dir_vec_size, sens_atm_vec_size, sens_solo_vec_size, sens_pluvio_vec_size, matrixPtr);

    // print the matrix
    printf("SensorID, Max, Min, Avg\n");
    for (int i = 0; i < matriz_line_size; i++)
    {
        for (int j = 0; j < COLUMNS; j++)
        {
            printf("%hd ", matrixPtr[i][j]);
        }
        printf("\n");
    }
    printf("\n");
}

void add_sensor(unsigned char type, unsigned long freq)
{

    Sensor **ptr_tmp = NULL;

    if (type == 'A')
    {
        // temperatura
        if (vec_sens_temp == NULL)
        {
            vec_sens_temp = (Sensor **)calloc(1, sizeof(Sensor *));
        }
        else
        {
            ptr_tmp = (Sensor **)realloc(vec_sens_temp, (sens_temp_vec_size + 1) * sizeof(Sensor *));
            // check realloc() return
            if (ptr_tmp != NULL)
            {
                vec_sens_temp = ptr_tmp;
                ptr_tmp = NULL;
            }
        }
        unsigned long readings_size = 0;
        unsigned short *readings_vec = (unsigned short *)calloc((86400 / freq), sizeof(unsigned short *));

        vec_sens_temp[sens_temp_vec_size] = (Sensor *)calloc(1, sizeof(Sensor));
        vec_sens_temp[sens_temp_vec_size]->id = uni_id;
        vec_sens_temp[sens_temp_vec_size]->sensor_type = type;
        vec_sens_temp[sens_temp_vec_size]->max_limit = limits.MAX_TEMP;
        vec_sens_temp[sens_temp_vec_size]->min_limit = limits.MIN_TEMP;
        vec_sens_temp[sens_temp_vec_size]->frequency = freq;
        vec_sens_temp[sens_temp_vec_size]->readings_size = readings_size;
        vec_sens_temp[sens_temp_vec_size]->readings = readings_vec;

        sens_temp_vec_size++;
        uni_id++;

        printf("Sensor added!\n");
    }
    else if (type == 'B')
    {
        // velc_V
        if (vec_sens_velc_v == NULL)
        {
            vec_sens_velc_v = (Sensor **)calloc(1, sizeof(Sensor *));
        }
        else
        {
            ptr_tmp = (Sensor **)realloc(vec_sens_velc_v, (sens_velc_vec_size + 1) * sizeof(Sensor *));
            // check realloc() return
            if (ptr_tmp != NULL)
            {
                vec_sens_velc_v = ptr_tmp;
                ptr_tmp = NULL;
            }
        }
        unsigned long readings_size = 0;
        unsigned short *readings_vec = (unsigned short *)calloc(86400 / freq, sizeof(unsigned short *));

        vec_sens_velc_v[sens_velc_vec_size] = (Sensor *)calloc(1, sizeof(Sensor));
        vec_sens_velc_v[sens_velc_vec_size]->id = uni_id;
        vec_sens_velc_v[sens_velc_vec_size]->sensor_type = type;
        vec_sens_velc_v[sens_velc_vec_size]->max_limit = limits.MAX_VELC;
        vec_sens_velc_v[sens_velc_vec_size]->min_limit = limits.MIN_VELC;
        vec_sens_velc_v[sens_velc_vec_size]->frequency = freq;
        vec_sens_velc_v[sens_velc_vec_size]->readings_size = readings_size;
        vec_sens_velc_v[sens_velc_vec_size]->readings = readings_vec;

        sens_velc_vec_size++;
        uni_id++;

        printf("Sensor added!\n");
    }
    else if (type == 'C')
    {
        // dir_v
        if (vec_sens_dir_v == NULL)
        {
            vec_sens_dir_v = (Sensor **)calloc(1, sizeof(Sensor *));
        }
        else
        {
            ptr_tmp = (Sensor **)realloc(vec_sens_dir_v, (sens_dir_vec_size + 1) * sizeof(Sensor *));
            // check realloc() return
            if (ptr_tmp != NULL)
            {
                vec_sens_dir_v = ptr_tmp;
                ptr_tmp = NULL;
            }
        }
        unsigned long readings_size = 0;
        unsigned short *readings_vec = (unsigned short *)calloc(86400 / freq, sizeof(unsigned short *));

        vec_sens_dir_v[sens_dir_vec_size] = (Sensor *)calloc(1, sizeof(Sensor));
        vec_sens_dir_v[sens_dir_vec_size]->id = uni_id;
        vec_sens_dir_v[sens_dir_vec_size]->sensor_type = type;
        vec_sens_dir_v[sens_dir_vec_size]->max_limit = limits.MAX_DIR;
        vec_sens_dir_v[sens_dir_vec_size]->min_limit = limits.MIN_DIR;
        vec_sens_dir_v[sens_dir_vec_size]->frequency = freq;
        vec_sens_dir_v[sens_dir_vec_size]->readings_size = readings_size;
        vec_sens_dir_v[sens_dir_vec_size]->readings = readings_vec;

        sens_dir_vec_size++;
        uni_id++;

        printf("Sensor added!\n");
    }
    else if (type == 'D')
    {
        // humd_atm
        if (vec_sens_hdm_atm == NULL)
        {
            vec_sens_hdm_atm = (Sensor **)calloc(1, sizeof(Sensor *));
        }
        else
        {
            ptr_tmp = (Sensor **)realloc(vec_sens_hdm_atm, (sens_atm_vec_size + 1) * sizeof(Sensor *));
            // check realloc() return
            if (ptr_tmp != NULL)
            {
                vec_sens_hdm_atm = ptr_tmp;
                ptr_tmp = NULL;
            }
        }
        unsigned long readings_size = 0;
        unsigned short *readings_vec = (unsigned short *)calloc(86400 / freq, sizeof(unsigned short *));

        vec_sens_hdm_atm[sens_atm_vec_size] = (Sensor *)calloc(1, sizeof(Sensor));
        vec_sens_hdm_atm[sens_atm_vec_size]->id = uni_id;
        vec_sens_hdm_atm[sens_atm_vec_size]->sensor_type = type;
        vec_sens_hdm_atm[sens_atm_vec_size]->max_limit = limits.MAX_HUMD_ATM;
        vec_sens_hdm_atm[sens_atm_vec_size]->min_limit = limits.MIN_HUMD_ATM;
        vec_sens_hdm_atm[sens_atm_vec_size]->frequency = freq;
        vec_sens_hdm_atm[sens_atm_vec_size]->readings_size = readings_size;
        vec_sens_hdm_atm[sens_atm_vec_size]->readings = readings_vec;

        sens_atm_vec_size++;
        uni_id++;

        printf("Sensor added!\n");
    }
    else if (type == 'E')
    {
        // humd_solo
        if (vec_sens_hmd_solo == NULL)
        {
            vec_sens_hmd_solo = (Sensor **)calloc(1, sizeof(Sensor *));
        }
        else
        {
            ptr_tmp = (Sensor **)realloc(vec_sens_hmd_solo, (sens_solo_vec_size + 1) * sizeof(Sensor *));
            // check realloc() return
            if (ptr_tmp != NULL)
            {
                vec_sens_hmd_solo = ptr_tmp;
                ptr_tmp = NULL;
            }
        }
        unsigned long readings_size = 0;
        unsigned short *readings_vec = (unsigned short *)calloc(86400 / freq, sizeof(unsigned short *));

        vec_sens_hmd_solo[sens_solo_vec_size] = (Sensor *)calloc(1, sizeof(Sensor));
        vec_sens_hmd_solo[sens_solo_vec_size]->id = uni_id;
        vec_sens_hmd_solo[sens_solo_vec_size]->sensor_type = type;
        vec_sens_hmd_solo[sens_solo_vec_size]->max_limit = limits.MAX_HUMD_GROUND_RAINING;
        vec_sens_hmd_solo[sens_solo_vec_size]->min_limit = limits.MIN_HUMD_GROUND;
        vec_sens_hmd_solo[sens_solo_vec_size]->frequency = freq;
        vec_sens_hmd_solo[sens_solo_vec_size]->readings_size = readings_size;
        vec_sens_hmd_solo[sens_solo_vec_size]->readings = readings_vec;

        sens_solo_vec_size++;
        uni_id++;

        printf("Sensor added!\n");
    }
    else if (type == 'F')
    {
        // pluvio
        if (vec_sens_pluvio == NULL)
        {
            vec_sens_pluvio = (Sensor **)calloc(1, sizeof(Sensor *));
        }
        else
        {
            ptr_tmp = (Sensor **)realloc(vec_sens_pluvio, (sens_pluvio_vec_size + 1) * sizeof(Sensor *));
            // check realloc() return
            if (ptr_tmp != NULL)
            {
                vec_sens_pluvio = ptr_tmp;
                ptr_tmp = NULL;
            }
        }
        unsigned long readings_size = 0;
        unsigned short *readings_vec = (unsigned short *)calloc(86400 / freq, sizeof(unsigned short *));

        vec_sens_pluvio[sens_pluvio_vec_size] = (Sensor *)calloc(1, sizeof(Sensor));
        vec_sens_pluvio[sens_pluvio_vec_size]->id = uni_id;
        vec_sens_pluvio[sens_pluvio_vec_size]->sensor_type = type;
        vec_sens_pluvio[sens_pluvio_vec_size]->max_limit = limits.MAX_PLUVIO_LOW_TEMP;
        vec_sens_pluvio[sens_pluvio_vec_size]->min_limit = limits.MIN_PLUVIO;
        vec_sens_pluvio[sens_pluvio_vec_size]->frequency = freq;
        vec_sens_pluvio[sens_pluvio_vec_size]->readings_size = readings_size;
        vec_sens_pluvio[sens_pluvio_vec_size]->readings = readings_vec;

        sens_pluvio_vec_size++;
        uni_id++;

        printf("Sensor added!\n");
    }
    else
    {
        // erro de input
        printf("Enter a valid input from A to F\n");
    }

    matriz_line_size = sens_temp_vec_size + sens_velc_vec_size + sens_dir_vec_size + sens_atm_vec_size + sens_solo_vec_size + sens_pluvio_vec_size;
}

void delete_sensor(unsigned short id, unsigned char type)
{
    // free() da estrutura em determinada posicao do determinado vetor

    int i, flg = 0;
    Sensor **ptr_tmp = NULL;

    if (type == 'A')
    {
        for (i = 0; i < sens_temp_vec_size; i++)
        {
            if (vec_sens_temp[i]->id == id)
            {
                free(vec_sens_temp[i]);
                sens_temp_vec_size--;
                ptr_tmp = (Sensor **)realloc(vec_sens_temp, sens_temp_vec_size * sizeof(Sensor *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    vec_sens_temp = ptr_tmp;
                    ptr_tmp = NULL;
                }
                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'B')
    {
        for (i = 0; i < sens_velc_vec_size; i++)
        {
            if (vec_sens_velc_v[i]->id == id)
            {
                free(vec_sens_velc_v[i]);
                sens_velc_vec_size--;
                ptr_tmp = (Sensor **)realloc(vec_sens_velc_v, sens_velc_vec_size * sizeof(Sensor *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    vec_sens_velc_v = ptr_tmp;
                    ptr_tmp = NULL;
                }
                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'C')
    {
        for (i = 0; i < sens_dir_vec_size; i++)
        {
            if (vec_sens_dir_v[i]->id == id)
            {
                free(vec_sens_dir_v[i]);
                sens_dir_vec_size--;
                ptr_tmp = (Sensor **)realloc(vec_sens_dir_v, sens_dir_vec_size * sizeof(Sensor *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    vec_sens_dir_v = ptr_tmp;
                    ptr_tmp = NULL;
                }
                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'D')
    {
        for (i = 0; i < sens_atm_vec_size; i++)
        {
            if (vec_sens_hdm_atm[i]->id == id)
            {
                free(vec_sens_hdm_atm[i]);
                sens_atm_vec_size--;
                ptr_tmp = (Sensor **)realloc(vec_sens_hdm_atm, sens_atm_vec_size * sizeof(Sensor *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    vec_sens_hdm_atm = ptr_tmp;
                    ptr_tmp = NULL;
                }
                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'E')
    {
        for (i = 0; i < sens_solo_vec_size; i++)
        {
            if (vec_sens_hmd_solo[i]->id == id)
            {
                free(vec_sens_hmd_solo[i]);
                sens_solo_vec_size--;
                ptr_tmp = (Sensor **)realloc(vec_sens_hmd_solo, sens_solo_vec_size * sizeof(Sensor *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    vec_sens_hmd_solo = ptr_tmp;
                    ptr_tmp = NULL;
                }
                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'F')
    {
        for (i = 0; i < sens_pluvio_vec_size; i++)
        {
            if (vec_sens_pluvio[i]->id == id)
            {
                free(vec_sens_pluvio[i]);
                sens_pluvio_vec_size--;
                ptr_tmp = (Sensor **)realloc(vec_sens_pluvio, sens_pluvio_vec_size * sizeof(Sensor *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    vec_sens_pluvio = ptr_tmp;
                    ptr_tmp = NULL;
                }
                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else
    {
        // erro de input
        printf("Enter a valid input from A to F\n");
    }
}

void update_freq(unsigned short id, unsigned char type, unsigned long freq)
{

    int i, flg = 0;
    unsigned short *ptr_tmp = NULL;
    unsigned short *readings_vec;

    if (type == 'A')
    {
        for (i = 0; i < sens_temp_vec_size; i++)
        {
            readings_vec = vec_sens_temp[i]->readings;

            if (vec_sens_temp[i]->id == id)
            {
                vec_sens_temp[i]->frequency = freq;

                ptr_tmp = (unsigned short *)realloc(readings_vec, (86400 / freq) * sizeof(unsigned short *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    readings_vec = ptr_tmp;
                    ptr_tmp = NULL;
                }
                vec_sens_temp[i]->readings_size = (86400 / freq);
                vec_sens_temp[i]->readings = readings_vec;

                printf("Frequencie changed successfully!\n");

                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'B')
    {
        for (i = 0; i < sens_velc_vec_size; i++)
        {
            if (vec_sens_velc_v[i]->id == id)
            {
                vec_sens_velc_v[i]->frequency = freq;

                ptr_tmp = (unsigned short *)realloc(readings_vec, (86400 / freq) * sizeof(unsigned short *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    readings_vec = ptr_tmp;
                    ptr_tmp = NULL;
                }
                vec_sens_velc_v[i]->readings = readings_vec;

                printf("Frequencie changed successfully!\n");

                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'C')
    {
        for (i = 0; i < sens_dir_vec_size; i++)
        {
            if (vec_sens_dir_v[i]->id == id)
            {
                vec_sens_dir_v[i]->frequency = freq;

                ptr_tmp = (unsigned short *)realloc(readings_vec, (86400 / freq) * sizeof(unsigned short *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    readings_vec = ptr_tmp;
                    ptr_tmp = NULL;
                }
                vec_sens_dir_v[i]->readings = readings_vec;

                printf("Frequencie changed successfully!\n");

                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'D')
    {
        for (i = 0; i < sens_atm_vec_size; i++)
        {
            if (vec_sens_hdm_atm[i]->id == id)
            {
                vec_sens_hdm_atm[i]->frequency = freq;

                ptr_tmp = (unsigned short *)realloc(readings_vec, (86400 / freq) * sizeof(unsigned short *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    readings_vec = ptr_tmp;
                    ptr_tmp = NULL;
                }
                vec_sens_hdm_atm[i]->readings = readings_vec;

                printf("Frequencie changed successfully!\n");

                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'E')
    {
        for (i = 0; i < sens_solo_vec_size; i++)
        {
            if (vec_sens_hmd_solo[i]->id == id)
            {
                vec_sens_hmd_solo[i]->frequency = freq;

                ptr_tmp = (unsigned short *)realloc(readings_vec, (86400 / freq) * sizeof(unsigned short *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    readings_vec = ptr_tmp;
                    ptr_tmp = NULL;
                }
                vec_sens_hmd_solo[i]->readings = readings_vec;

                printf("Frequencie changed successfully!\n");

                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else if (type == 'F')
    {
        for (i = 0; i < sens_pluvio_vec_size; i++)
        {
            if (vec_sens_pluvio[i]->id == id)
            {
                vec_sens_pluvio[i]->frequency = freq;

                ptr_tmp = (unsigned short *)realloc(readings_vec, (86400 / freq) * sizeof(unsigned short *));
                // check realloc() return
                if (ptr_tmp != NULL)
                {
                    readings_vec = ptr_tmp;
                    ptr_tmp = NULL;
                }
                vec_sens_pluvio[i]->readings = readings_vec;

                printf("Frequencie changed successfully!\n");

                flg++;
            }
        }
        if (flg == 0)
        {
            printf("Sensor not found\n");
        }
    }
    else
    {
        // erro de input
        printf("Enter a valid input from A to F\n");
    }
}

int main()
{

    // Open the configuration file
    FILE *config_file = fopen("config.txt", "r");
    if (config_file == NULL)
    {
        perror("Error opening config file");
        return 1;
    }

    // Read the configuration file line by line
    char buf[BUF_SIZE];

    while (fgets(buf, BUF_SIZE, config_file) != NULL)
    {
        // Parse the line and extract the relevant information
        if (sscanf(buf, "MAX_TEMP = %d", &limits.MAX_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MIN_TEMP = %d", &limits.MIN_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_VELC = %d", &limits.MAX_VELC) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MIN_VELC = %d", &limits.MIN_VELC) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_DIR = %d", &limits.MAX_DIR) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MIN_DIR = %d", &limits.MIN_DIR) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MIN_HUMD_ATM = %d", &limits.MIN_HUMD_ATM) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_HUMD_ATM = %d", &limits.MAX_HUMD_ATM) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MIN_HUMD_GROUND = %d", &limits.MIN_HUMD_GROUND) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_HUMD_GROUND = %d", &limits.MAX_HUMD_GROUND) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MIN_HUMD_GROUND_RAINING = %d", &limits.MIN_HUMD_GROUND_RAINING) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_HUMD_GROUND_RAINING = %d", &limits.MAX_HUMD_GROUND_RAINING) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MIN_PLUVIO = %d", &limits.MIN_PLUVIO) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_PLUVIO_HIGHEST_TEMP = %d", &limits.MAX_PLUVIO_HIGHEST_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_PLUVIO_HIGH_TEMP = %d", &limits.MAX_PLUVIO_HIGH_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_PLUVIO_MID_TEMP = %d", &limits.MAX_PLUVIO_MID_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MAX_PLUVIO_LOW_TEMP = %d", &limits.MAX_PLUVIO_LOW_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "LOW_TEMP = %d", &limits.LOW_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "MID_TEMP = %d", &limits.MID_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else if (sscanf(buf, "HIGH_TEMP = %d", &limits.HIGH_TEMP) == 1)
        {
            // Do nothing, just read the value
        }
        else
        {
            printf("Ignoring invalid line: %s\n", buf);
        }
    }

    while (1)
    {
        int j;
        int menuItem = 0;
        short id;
        char type;
        long freq;
        printf("Menu:\n1-Run sensors\n2-View matrix\n3-Add sensor\n4-Remove sensor\n5-Change sensor frequency\n6-Create CSV for sensors and their readings\n7-Create CSV for summary matrix\n0-Exit\n");
        printf("Insert choice:\n");
        scanf(" %d", &menuItem);
        printf("\n");

        switch (menuItem)
        {
        case 1:
            run_sensors();
            break;
        case 2:
            show_matrix();
            break;
        case 3:
            printf("Enter Sensor Type from A to F: \n");
            printf("A - Temperature Sensor\nB - Wind Velocity Sensor\nC - Wind Direction Sensor\nD - ATM Humidity Sensor\nE - Solo Humidity Sensor\nF - Pluviosity Sensor\n");
            scanf(" %c", &type);
            printf("Enter Sensor Frequencie in seconds\n");
            scanf(" %ld", &freq);
            printf("%c, %ld\n", type, freq);
            add_sensor((unsigned char)type, (unsigned long)freq);
            break;
        case 4:
            printf("Enter Sensor ID: \n");
            scanf(" %hd", &id);
            printf("Enter Sensor Type from A to F: \n");
            scanf(" %c", &type);
            delete_sensor((unsigned short)id, (unsigned char)type);
            break;
        case 5:
            printf("Enter Sensor ID: \n");
            scanf(" %hd", &id);
            printf("Enter Sensor Type from A to F: \n");
            scanf(" %c", &type);
            printf("Enter Sensor Frequencie update in seconds\n");
            scanf(" %ld", &freq);
            update_freq((unsigned short)id, (unsigned char)type, (unsigned long)freq);
            break;
        case 6:
            create_csv_file_sensors(vec_sens_temp, vec_sens_velc_v, vec_sens_dir_v, vec_sens_hdm_atm, vec_sens_hmd_solo, vec_sens_pluvio, sens_temp_vec_size, sens_velc_vec_size, sens_dir_vec_size, sens_atm_vec_size, sens_solo_vec_size, sens_pluvio_vec_size);
            break;
        case 7:
            create_csv_file_matrix(vec_sens_temp, vec_sens_velc_v, vec_sens_dir_v, vec_sens_hdm_atm, vec_sens_hmd_solo, vec_sens_pluvio, sens_temp_vec_size, sens_velc_vec_size, sens_dir_vec_size, sens_atm_vec_size, sens_solo_vec_size, sens_pluvio_vec_size, matrixPtr, matriz_line_size);
            break;
        case 0:

            for (j = 0; j < sens_temp_vec_size; j++)
            {
                free(vec_sens_temp[j]->readings);
                free(vec_sens_temp[j]);
            }
            for (j = 0; j < sens_velc_vec_size; j++)
            {
                free(vec_sens_velc_v[j]->readings);
                free(vec_sens_velc_v[j]);
            }
            for (j = 0; j < sens_dir_vec_size; j++)
            {
                free(vec_sens_dir_v[j]->readings);
                free(vec_sens_dir_v[j]);
            }
            for (j = 0; j < sens_atm_vec_size; j++)
            {
                free(vec_sens_hdm_atm[j]->readings);
                free(vec_sens_hdm_atm[j]);
            }
            for (j = 0; j < sens_solo_vec_size; j++)
            {
                free(vec_sens_hmd_solo[j]->readings);
                free(vec_sens_hmd_solo[j]);
            }
            for (j = 0; j < sens_pluvio_vec_size; j++)
            {
                free(vec_sens_pluvio[j]->readings);
                free(vec_sens_pluvio[j]);
            }

            free(matrixPtr);

            exit(0);
            break;
        }
    }
    return 0;
}
