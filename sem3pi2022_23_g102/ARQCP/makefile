#Makefile

main: main.o sens_temp.o sens_velc_vento.o sens_dir_vento.o sens_humd_atm.o sens_humd_solo.o sens_pluvio.o pcg32_random.o read_dev_random.o sensMatrix.o createCSV.o
		gcc main.o sens_temp.o sens_velc_vento.o sens_dir_vento.o sens_humd_atm.o sens_humd_solo.o sens_pluvio.o pcg32_random.o read_dev_random.o sensMatrix.o createCSV.o -o main
		
main.o: main.c sensores.h pcg32_random.h read_dev_random.h sensMatrix.h createCSV.h
		gcc -g -Wall -c main.c -o main.o	

sens_temp.o: sens_temp.s
		gcc -g -Wall -c sens_temp.s -o sens_temp.o

sens_velc_vento.o: sens_velc_vento.s
		gcc -g -Wall -c sens_velc_vento.s -o sens_velc_vento.o

sens_dir_vento.o: sens_dir_vento.s
		gcc -g -Wall -c sens_dir_vento.s -o sens_dir_vento.o

sens_humd_atm.o: sens_humd_atm.s
		gcc -g -Wall -c sens_humd_atm.s -o sens_humd_atm.o

sens_humd_solo.o: sens_humd_solo.s
		gcc -g -Wall -c sens_humd_solo.s -o sens_humd_solo.o

sens_pluvio.o: sens_pluvio.s
		gcc -g -Wall -c sens_pluvio.s -o sens_pluvio.o

sensMatrix.o: sensMatrix.c
		gcc -g -Wall -c sensMatrix.c -o sensMatrix.o

pcg32_random.o: pcg32_random.s
		gcc -g -Wall -c pcg32_random.s -o pcg32_random.o

read_dev_random.o: read_dev_random.c
		gcc -g -Wall -c read_dev_random.c -o read_dev_random.o

createCSV.o: createCSV.c
		gcc -g -Wall -c createCSV.c -o createCSV.o
		
clean:
		rm -f *.o main
		
run: main
		./main
