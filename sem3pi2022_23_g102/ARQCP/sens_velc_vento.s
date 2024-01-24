.section .data

.equ MAX_DIF, 50

.section .text

    .global sens_velc_vento

sens_velc_vento:
    pushq %rbp
    movq %rsp, %rbp
    subq $2, %rsp

    movb %sil, %al

    movb $MAX_DIF, %cl
    idivb %cl

    cmpb $0, %sil
    jl negate

    movb %ah, -1(%rbp)

    addb -1(%rbp), %dil
    movb %dil, -2(%rbp)

    jmp end

negate:
    movb %ah, -1(%rbp)

    subb -1(%rbp), %dil
    movb %dil, -2(%rbp)

    jmp end

end:
    movb %dil, %al

    movq %rbp, %rsp
    popq %rbp
    ret
    
