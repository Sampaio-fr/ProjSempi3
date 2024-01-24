.section .text

    .global pcg32_random

    //uint64_t oldstate = state;
    // Advance internal state
    //state = oldstate * 6364136223846793005ULL + (inc | 1);
    // Calculate output function (XSH RR), uses old state for max ILP
    //uint32_t xorshifted = ((oldstate >> 18u) ^ oldstate) >> 27u;
    //uint32_t rot = oldstate >> 59u;
    //return (xorshifted >> rot) | (xorshifted << ((-rot) & 31));

pcg32_random:
    pushq %rbp
    movq %rsp, %rbp

    movq %rdi, %rax
    movq %rax, -8(%rbp)

    movabsq $6364136223846793005, %rdx
    imulq %rdx, %rax
    movq %rsi, %rdx
    orq $1, %rdx
    addq %rdx, %rax
    
    shrq $18, %rax
    xorq -8(%rbp), %rax
    shrq $27, %rax
    movl %eax, -16(%rbp)
    movq -8(%rbp), %rax
    shrq $59, %rax

    movl %eax, -12(%rbp)
    movl -16(%rbp), %edx
    movl %eax, %ecx
    rorl %cl, %edx
    movl %edx, %eax

    movq %rbp, %rsp
    popq %rbp
    ret
