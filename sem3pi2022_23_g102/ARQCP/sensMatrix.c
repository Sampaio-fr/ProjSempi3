#include <stdio.h>
#include <stdint.h>
#include "sensMatrix.h"
#include "sensorsStructs.h"

short getMin(unsigned short* array, int size){
    unsigned short min = array[0];
    for (int i = 0; i < size; i++){
        if (min > array[i]){
            min = array[i];
        }
    }
    return (short)min;
}
short getMax(unsigned short* array, int size){
    unsigned short max = array[0];
    for (int i = 0; i < size; i++){
        if (max < array[i]){
            max = array[i];
        }
    }
    return (short)max;
}
short getAvg(unsigned short* array, int size){
    unsigned short sum = 0;
    for (int i = 0; i < size; i++){
        sum += array[i];
    }
    short avg = ((short)sum) / size;

    return (short)avg;
}

void sensMatrix(Sensor** vec_sens_temp, Sensor** vec_sens_velc_v, Sensor** vec_sens_dir_v, Sensor** vec_sens_hdm_atm, Sensor** vec_sens_hmd_solo, Sensor** vec_sens_pluvio, int sens_temp_vec_size, int sens_velc_vec_size, int sens_dir_vec_size, int sens_atm_vec_size, int sens_solo_vec_size, int sens_pluvio_vec_size, short** matriz)
{
    int i, counter = 0;

    for(i = 0; i < sens_temp_vec_size; i++){

        *(*(matriz + counter) + 0) = (int) vec_sens_temp[i]->id;
        *(*(matriz + counter) + 1) = getMin(vec_sens_temp[i]->readings, (int)vec_sens_temp[i]->readings_size);
        *(*(matriz + counter) + 2) = getMax(vec_sens_temp[i]->readings, (int)vec_sens_temp[i]->readings_size);
        *(*(matriz + counter) + 3) = getAvg(vec_sens_temp[i]->readings, (int)vec_sens_temp[i]->readings_size);

        counter++;
    }
    for(i = 0; i < sens_velc_vec_size; i++){

        *(*(matriz + counter) + 0) = (int) vec_sens_velc_v[i]->id;
        *(*(matriz + counter) + 1) = getMin(vec_sens_velc_v[i]->readings, (int)vec_sens_velc_v[i]->readings_size);
        *(*(matriz + counter) + 2) = getMax(vec_sens_velc_v[i]->readings, (int)vec_sens_velc_v[i]->readings_size);
        *(*(matriz + counter) + 3) = getAvg(vec_sens_velc_v[i]->readings, (int)vec_sens_velc_v[i]->readings_size);

        counter++;
    }
    for(i = 0; i < sens_dir_vec_size; i++){

        *(*(matriz + counter) + 0) = (int) vec_sens_dir_v[i]->id;
        *(*(matriz + counter) + 1) = getMin(vec_sens_dir_v[i]->readings, (int)vec_sens_dir_v[i]->readings_size);
        *(*(matriz + counter) + 2) = getMax(vec_sens_dir_v[i]->readings, (int)vec_sens_dir_v[i]->readings_size);
        *(*(matriz + counter) + 3) = getAvg(vec_sens_dir_v[i]->readings, (int)vec_sens_dir_v[i]->readings_size);

        counter++;
    }
    for(i = 0; i < sens_atm_vec_size; i++){

        *(*(matriz + counter) + 0) = (int) vec_sens_hdm_atm[i]->id;
        *(*(matriz + counter) + 1) = getMin(vec_sens_hdm_atm[i]->readings, (int)vec_sens_hdm_atm[i]->readings_size);
        *(*(matriz + counter) + 2) = getMax(vec_sens_hdm_atm[i]->readings, (int)vec_sens_hdm_atm[i]->readings_size);
        *(*(matriz + counter) + 3) = getAvg(vec_sens_hdm_atm[i]->readings, (int)vec_sens_hdm_atm[i]->readings_size);

        counter++;
    }
    for(i = 0; i < sens_solo_vec_size; i++){

        *(*(matriz + counter) + 0)= (int) vec_sens_hmd_solo[i]->id;
        *(*(matriz + counter) + 1)= getMin(vec_sens_hmd_solo[i]->readings, (int)vec_sens_hmd_solo[i]->readings_size);
        *(*(matriz + counter) + 2)= getMax(vec_sens_hmd_solo[i]->readings, (int)vec_sens_hmd_solo[i]->readings_size);
        *(*(matriz + counter) + 3)= getAvg(vec_sens_hmd_solo[i]->readings, (int)vec_sens_hmd_solo[i]->readings_size);

        counter++;
    }
    for(i = 0; i < sens_pluvio_vec_size; i++){

        *(*(matriz + counter) + 0)= (int) vec_sens_pluvio[i]->id;
        *(*(matriz + counter) + 1)= getMin(vec_sens_pluvio[i]->readings, (int)vec_sens_pluvio[i]->readings_size);
        *(*(matriz + counter) + 2)= getMax(vec_sens_pluvio[i]->readings, (int)vec_sens_pluvio[i]->readings_size);
        *(*(matriz + counter) + 3)= getAvg(vec_sens_pluvio[i]->readings, (int)vec_sens_pluvio[i]->readings_size);

        counter++;
    }

}
