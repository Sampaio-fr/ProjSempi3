#ifndef CREATECSV_H
#define CREATECSV_H
#include "sensorsStructs.h"

void create_csv_file_sensors(Sensor** vec_sens_temp, Sensor** vec_sens_velc_v, Sensor** vec_sens_dir_v, Sensor** vec_sens_hdm_atm, Sensor** vec_sens_hmd_solo, Sensor** vec_sens_pluvio, int sens_temp_vec_size, int sens_velc_vec_size, int sens_dir_vec_size, int sens_atm_vec_size, int sens_solo_vec_size, int sens_pluvio_vec_size);

void create_csv_file_matrix(Sensor** vec_sens_temp, Sensor** vec_sens_velc_v, Sensor** vec_sens_dir_v, Sensor** vec_sens_hdm_atm, Sensor** vec_sens_hmd_solo, Sensor** vec_sens_pluvio, int sens_temp_vec_size, int sens_velc_vec_size, int sens_dir_vec_size, int sens_atm_vec_size, int sens_solo_vec_size, int sens_pluvio_vec_size, short** matriz, int matriz_line_size);

#endif