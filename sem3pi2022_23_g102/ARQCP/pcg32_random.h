#ifndef read_dev_random_h
#define read_dev_random_h
#include <stdio.h> 
#include <stdint.h>
uint32_t pcg32_random(uint64_t state, uint64_t inc);
#endif