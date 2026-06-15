.model small
.stack 100h

.data
    a            dw 0
    x            dw 0
    y            dw 0
    z            dw 0
    t1           dw 0

.code
main proc
    mov ax, @data
    mov ds, ax

    ; READ a
    call _read_integer
    mov word ptr [a], ax

    ; x = 7
    mov word ptr [x], 7

    ; t1 = a << 2
    mov ax, word ptr [a]
    shl ax, 2
    mov word ptr [t1], ax

    ; y = t1
    mov ax, word ptr [t1]
    mov word ptr [y], ax

    ; z = 17
    mov word ptr [z], 17

    ; GOTO L0
    jmp L0

    ; L0:
L0:

    ; WRITE x
    mov ax, word ptr [x]
    push ax
    call _print_integer

    ; WRITE y
    mov ax, word ptr [y]
    push ax
    call _print_integer

    ; WRITE z
    mov ax, word ptr [z]
    push ax
    call _print_integer

    ; HALT
    mov ax, 4C00h
    int 21h

main endp
end main
