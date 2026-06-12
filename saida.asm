.model small
.stack 100h

.data
    x            dw 0
    y            dw 0
    z            dw 0
    flag         db 0
    nome         db 64 dup(0)
    t0           dw 0
    t1           dw 0
    t2           dw 0
    t3           dw 0
    str0         db "Resultado: ", '$'

.code
main proc
    mov ax, @data
    mov ds, ax

    ; READ x
    call _read_integer
    mov word ptr [x], ax

    ; READ y
    call _read_integer
    mov word ptr [y], ax

    ; t0 = x > y
    mov ax, word ptr [x]
    mov bx, word ptr [y]
    cmp ax, bx
    jg Lcmp0
    mov ax, 0
    jmp Lend0
Lcmp0:
    mov ax, 1
Lend0:
    mov word ptr [t0], ax

    ; IF t0 == 0 GOTO L0
    mov ax, word ptr [t0]
    cmp ax, 0
    je L0

    ; z = x
    mov ax, word ptr [x]
    mov word ptr [z], ax

    ; GOTO L1
    jmp L1

    ; L0:
L0:

    ; z = y
    mov ax, word ptr [y]
    mov word ptr [z], ax

    ; L1:
L1:

    ; L2:
L2:

    ; t1 = z > 0
    mov ax, word ptr [z]
    mov bx, 0
    cmp ax, bx
    jg Lcmp1
    mov ax, 0
    jmp Lend1
Lcmp1:
    mov ax, 1
Lend1:
    mov word ptr [t1], ax

    ; IF t1 == 0 GOTO L3
    mov ax, word ptr [t1]
    cmp ax, 0
    je L3

    ; t2 = z - 1
    mov ax, word ptr [z]
    mov bx, 1
    sub ax, bx
    mov word ptr [t2], ax

    ; z = t2
    mov ax, word ptr [t2]
    mov word ptr [z], ax

    ; GOTO L2
    jmp L2

    ; L3:
L3:

    ; t3 = x == y
    mov ax, word ptr [x]
    mov bx, word ptr [y]
    cmp ax, bx
    je Lcmp2
    mov ax, 0
    jmp Lend2
Lcmp2:
    mov ax, 1
Lend2:
    mov word ptr [t3], ax

    ; flag = t3
    mov ax, word ptr [t3]
    mov byte ptr [flag], al

    ; WRITE "Resultado: "
    lea dx, str0
    mov ah, 09h
    int 21h

    ; WRITE z
    mov ax, word ptr [z]
    push ax
    call _print_integer

    ; WRITE flag
    xor ax, ax
    mov al, byte ptr [flag]
    push ax
    call _print_integer

    ; HALT
    mov ax, 4C00h
    int 21h

main endp
end main
