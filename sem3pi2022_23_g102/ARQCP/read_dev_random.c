#include "read_dev_random.h"

int read_dev_random()
{

    uint32_t buffer[1];
    FILE *f;
    int result;

    f = fopen("/dev/urandom", "r");
    if (f == NULL)
    {
        printf("Error: open() failed to open /dev/random for reading\n");
        return 1;
    }
    result = fread(buffer, sizeof(uint32_t), 1, f);
    if (result < 1)
    {
        printf("error , failed to read and words\n");
        return 1;
    }

    fclose(f);

    return *(buffer);
}
