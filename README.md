# INFO805
**DI LUNA Tommy**

**PENIN Noah**

### Exemple d'utilisations

---
```
let prixHt = 200;
let prixTtc = prixHt * 119 / 100 .
```
```
DATA SEGMENT
        prixHt DD
        prixTtc DD
DATA ENDS
CODE SEGMENT
        mov eax, 200
        mov prixHt, eax
        mov eax, prixHt
        push eax
        mov eax, 119
        pop ebx
        mul eax, ebx
        push eax
        mov eax, 100
        pop ebx
        div ebx, eax
        mov eax, ebx
        mov prixTtc, eax
CODE ENDS
```
---
```
let a = input;
let b = input;
while (0 < b)
do (let aux=(a mod b); let a=b; let b=aux );
output a
.
```
```
DATA SEGMENT
        a DD
        b DD
        aux DD
DATA ENDS
CODE SEGMENT
        in eax
        mov a, eax
        in eax
        mov b, eax
debut_while_1:
        mov eax, 0
        push eax
        mov eax, b
        pop ebx
        sub eax,ebx
        jle faux_gt_1
        mov eax,1
        jmp sortie_gt_1
faux_gt_1:
        mov eax,0
sortie_gt_1:
        jz sortie_while_1
        mov eax, a
        push eax
        mov eax, b
        pop ebx
        mov ecx,ebx
        div ecx,eax
        mul ecx,eax
        sub ebx,ecx
        mov eax,ebx
        mov aux, eax
        mov eax, b
        mov a, eax
        mov eax, aux
        mov b, eax
        jmp debut_while_1
sortie_while_1:
        mov eax, a
        out eax
CODE ENDS
```
---
```
let number = 40;
let in = input;
if (number = in)
then (output 1)
else (output 0)
endif
.
```
```
DATA SEGMENT
        number DD
        in DD
DATA ENDS
CODE SEGMENT
        mov eax, 40
        mov number, eax
        in eax
        mov in, eax
cond_if_1:
        mov eax, number
        push eax
        mov eax, in
        pop ebx
        sub eax,ebx
        jz else_if_1
        jmp then_if_1
then_if_1:
        mov eax, 1
        out eax
        jmp sortie_if_1
else_if_1:
        mov eax, 0
        out eax
        jmp sortie_if_1
sortie_if_1:
CODE ENDS
```