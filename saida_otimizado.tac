    READ x
    READ y
    t0 = x > y
    IF t0 == 0 GOTO L0
    z = x
    GOTO L1
L0:
    z = y
L1:
L2:
    t1 = z > 0
    IF t1 == 0 GOTO L3
    t2 = z - 1
    z = t2
    GOTO L2
L3:
    t3 = x == y
    flag = t3
    WRITE "Resultado: "
    WRITE z
    WRITE flag
    HALT
