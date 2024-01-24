.section .data

    .equ MAX_PLUVIO_HIGHEST_TEMP, 1
    .equ MAX_PLUVIO_HIGH_TEMP, 2
    .equ MAX_PLUVIO_MID_TEMP, 10
    .equ MAX_PLUVIO_LOW_TEMP, 50
    .equ LOW_TEMP, 5
    .equ MID_TEMP, 15
    .equ HIGH_TEMP, 25

.section .text

    .global sens_pluvio

sens_pluvio:
    pushq %rbp
    movq %rsp, %rbp
    subq $3, %rsp

    movb %dl, -3(%rbp)

    cmpb $0, %dil
    je equal

    jmp continuation

equal:
    cmpb $0, %dil
    jge compare1

    movb $0, -1(%rbp)
    jmp end

compare1:
    cmpb $LOW_TEMP, %sil
    jg compare2

    movb  %dl, %al
    movb $MAX_PLUVIO_LOW_TEMP, %cl
    idivb %cl

    movb %ah, %cl
    movb %cl, -2(%rbp)

    jmp continuation

compare2:
    cmpb $MID_TEMP, %sil
    jg compare3

    movb %dl, %al
    movb $MAX_PLUVIO_MID_TEMP, %cl
    idivb %cl

    movb %ah, %cl
    movb %cl, -2(%rbp)

    jmp continuation

compare3:
    cmpb $HIGH_TEMP, %sil
    jg last_compare

    movb %dl, %al
    movb $MAX_PLUVIO_HIGH_TEMP, %cl
    idivb %cl

    movb %ah, %cl
    movb %cl, -2(%rbp)

    jmp continuation

last_compare:
    movb %dl, %al
    movb $MAX_PLUVIO_HIGHEST_TEMP, %cl
    idivb %cl

    movb %ah, %cl
    movb %cl, -2(%rbp)

    jmp continuation

continuation:
    cmpb $0, -3(%rbp)
    jl negate

    addb -2(%rbp), %dil
    movb %dil, -1(%rbp)

    jmp end

negate:
    subb -2(%rbp), %dil
    movb %dil, -1(%rbp)

end:
    movb -1(%rbp), %al
    movq %rbp, %rsp
    popq %rbp
    ret
