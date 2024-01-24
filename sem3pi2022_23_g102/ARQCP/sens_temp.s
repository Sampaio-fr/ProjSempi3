.section .data

.equ MAX_DIF, 5

.section .text
	.global	sens_temp

sens_temp:	
	pushq %rbp
	movq %rsp, %rbp
	subq $2, %rsp

    movl $0, %eax
    movb $MAX_DIF, %cl
    
	movb %sil, %al
    idivb %cl

	cmpb $0, %sil
	jl negate

	movb %ah, %cl
	movb %cl, %al

	movb %al, -1(%rbp)

    addb %al, %dil
	movb %dil, -2(%rbp)

	jmp end

negate:
	movb %ah, %cl
	movb %cl, %al

	movb %al, -1(%rbp)

    subb %al, %dil
	movb %dil, -2(%rbp)

	jmp end

end:
    movb %dil, %al

	movq %rbp, %rsp
	popq %rbp
	ret
	