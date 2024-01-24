.section .data

.equ MAX_DIF, 25

.section .text

    .global sens_dir_vento

sens_dir_vento:
    pushq %rbp
    movq %rsp, %rbp
    subq $4, %rsp

    movw %si, %ax
    movw $MAX_DIF, %cx
    movw $0, %dx
    
    cwtl
    idivw %cx
    cmpw $0, %si
    jl negate

    movw %dx, %ax
    movw %ax, -2(%rbp)

    addw -2(%rbp), %di
    movw %di, -4(%rbp)

    jmp end

negate:
    movw %dx, %ax
    movw %ax, -2(%rbp)

    subw -2(%rbp), %di
    movw %di, -4(%rbp)

    jmp end

end:
    movw %di, %ax

    movq %rbp, %rsp
    popq %rbp
    ret
