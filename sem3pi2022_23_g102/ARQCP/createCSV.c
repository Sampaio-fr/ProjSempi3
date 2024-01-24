#include <stdio.h>
#include <stdlib.h>
#include "createCSV.h"
#include "sensMatrix.h"

#define COLUMNS 4

// recebe apontador do vetor de estruturas
void create_csv_file_sensors(Sensor **vec_sens_temp, Sensor **vec_sens_velc_v, Sensor **vec_sens_dir_v, Sensor **vec_sens_hdm_atm, Sensor **vec_sens_hmd_solo, Sensor **vec_sens_pluvio, int sens_temp_vec_size, int sens_velc_vec_size, int sens_dir_vec_size, int sens_atm_vec_size, int sens_solo_vec_size, int sens_pluvio_vec_size)
{

    FILE *fpt;
    fpt = fopen("readingsCSV.csv", "w");

    // csv header
    fprintf(fpt, "ID, Sensor Type, Readings\n");

    unsigned short id;
    unsigned char sens_type;
    unsigned long r_size;
    unsigned short *readings_vec;

    int i, j;
    for (i = 0; i < sens_temp_vec_size; i++)
    {
        id = vec_sens_temp[i]->id;
        sens_type = vec_sens_temp[i]->sensor_type;
        r_size = vec_sens_temp[i]->readings_size;
        readings_vec = vec_sens_temp[i]->readings;

        for (j = 0; j < r_size; j++)
        {
            short read = (short)*(readings_vec + j);
            fprintf(fpt, "%hu, %c, %hd\n", id, sens_type, read);
        }
    }

    for (i = 0; i < sens_velc_vec_size; i++)
    {
        id = vec_sens_velc_v[i]->id;
        sens_type = vec_sens_velc_v[i]->sensor_type;
        r_size = vec_sens_velc_v[i]->readings_size;
        readings_vec = vec_sens_velc_v[i]->readings;

        for (j = 0; j < r_size; j++)
        {
            short read = (short)*(readings_vec + j);
            fprintf(fpt, "%hu, %c, %hd\n", id, sens_type, read);
        }
    }
    for (i = 0; i < sens_dir_vec_size; i++)
    {
        id = vec_sens_dir_v[i]->id;
        sens_type = vec_sens_dir_v[i]->sensor_type;
        r_size = vec_sens_dir_v[i]->readings_size;
        readings_vec = vec_sens_dir_v[i]->readings;
        
        for (j = 0; j < r_size; j++)
        {
            short read = (short)*(readings_vec + j);
            fprintf(fpt, "%hu, %c, %hd\n", id, sens_type, read);
        }
    }
    for (i = 0; i < sens_atm_vec_size; i++)
    {
        id = vec_sens_hdm_atm[i]->id;
        sens_type = vec_sens_hdm_atm[i]->sensor_type;
        r_size = vec_sens_hdm_atm[i]->readings_size;
        readings_vec = vec_sens_hdm_atm[i]->readings;

        for (j = 0; j < r_size; j++)
        {
            short read = (short)*(readings_vec + j);
            fprintf(fpt, "%hu, %c, %hd\n", id, sens_type, read);
        }
    }
    for (i = 0; i < sens_solo_vec_size; i++)
    {
        id = vec_sens_hmd_solo[i]->id;
        sens_type = vec_sens_hmd_solo[i]->sensor_type;
        r_size = vec_sens_hmd_solo[i]->readings_size;
        readings_vec = vec_sens_hmd_solo[i]->readings;

        for (j = 0; j < r_size; j++)
        {
            short read = (short)*(readings_vec + j);
            fprintf(fpt, "%hu, %c, %hd\n", id, sens_type, read);
        }
    }
    for (i = 0; i < sens_pluvio_vec_size; i++)
    {
        id = vec_sens_pluvio[i]->id;
        sens_type = vec_sens_pluvio[i]->sensor_type;
        r_size = vec_sens_pluvio[i]->readings_size;
        readings_vec = vec_sens_pluvio[i]->readings;

        for (j = 0; j < r_size; j++)
        {
            short read = (short)*(readings_vec + j);
            fprintf(fpt, "%hu, %c, %hd\n", id, sens_type, read);
        }
    }

    fclose(fpt);

    printf("File created successfully!\n");
}

void create_csv_file_matrix(Sensor **vec_sens_temp, Sensor **vec_sens_velc_v, Sensor **vec_sens_dir_v, Sensor **vec_sens_hdm_atm, Sensor **vec_sens_hmd_solo, Sensor **vec_sens_pluvio, int sens_temp_vec_size, int sens_velc_vec_size, int sens_dir_vec_size, int sens_atm_vec_size, int sens_solo_vec_size, int sens_pluvio_vec_size, short **matriz, int matriz_line_size)
{

    if (matriz == NULL)
    {
        matriz = calloc(matriz_line_size, sizeof(short *));

        for (int i = 0; i < matriz_line_size; i++)
        {
            matriz[i] = calloc(COLUMNS, sizeof(short));
        }

        sensMatrix(vec_sens_temp, vec_sens_velc_v, vec_sens_dir_v, vec_sens_hdm_atm, vec_sens_hmd_solo, vec_sens_pluvio, sens_temp_vec_size, sens_velc_vec_size, sens_dir_vec_size, sens_atm_vec_size, sens_solo_vec_size, sens_pluvio_vec_size, matriz);
    }

    FILE *fpt;
    fpt = fopen("matrixCSV.csv", "w");

    // csv header
    fprintf(fpt, "SensorID, Min, Max, Avg\n");

    int r, c;
    for (r = 0; r < matriz_line_size; r++)
    {
        for (c = 0; c < COLUMNS; c++)
        {
            fprintf(fpt, " %hd,", matriz[r][c]);
        }
        fprintf(fpt, "\n");
    }
    fclose(fpt);
    printf("File created successfully!\n");
}