.section .data

    .equ MAX_DIF_RAINING, 30
    .equ MAX_DIF_NOT_RAINING, 7

.section .text

    .global sens_humd_atm

sens_humd_atm:
    pushq %rbp
    movq %rsp, %rbp
    subq $3, %rsp

    movb %dl, -3(%rbp)

    cmpb $0, %sil
    je equal

    movb %dl, %al
    movb $MAX_DIF_RAINING, %cl
    idivb %cl

    cmpb $0, %sil
    jl negate

    movb %ah, %cl
    movb %cl, %al
    movb %al, -1(%rbp)

    addb %dil, %al
    movb %dil, -2(%rbp)

    jmp end

equal:
    movb %dl, %al
    movb $MAX_DIF_NOT_RAINING, %cl
    idivb %cl

    cmpb $0, -3(%rbp)
    jl negate

    movb %ah, %cl
    movb %cl, %al
    movb %al, -1(%rbp)

    addb %dil, %al
    movb %dil, -2(%rbp)

    jmp end

negate:
    
    movb %ah, %cl
    movb %cl, %al
    movb %al, -1(%rbp)

    subb %dil, %al
    movb %dil, -2(%rbp)


end:
    movb %dil, %al

    movq %rbp, %rsp
    popq %rbp
    ret
