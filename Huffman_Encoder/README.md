Author:Abraham Adberstein 

[Program 3]
[Description]
I have a utils function that does the encoding and decoding. Main class is Encoder. The following are the different classes used. In the function read_file_content() of the Encoder.java file you can follow the steps from the assignment.

HuffmanCode.class  <Code found on piazza> https://rosettacode.org/wiki/Huffman_coding#Java

BitOutputStream.class <Used to expand and compress files> Copyright (c) Project Nayuki
HuffmanEncoder.class <Used to expand and compress files> Copyright (c) Project Nayuki
Leaf.class <Used to expand and compress files> Copyright (c) Project Nayuki
InternalNode.class <Used to expand and compress files> Copyright (c) Project Nayuki
Node.class <Used to expand and compress files> Copyright (c) Project Nayuki
CodeTree.class <Used to expand and compress files> Copyright (c) Project Nayuki
FrequencyTable.class <Used to expand and compress files> Copyright (c) Project Nayuki

Encoder.class <class i coded>


To compile the program, you need to do "make", then either "make run" or java Encoder.
For different inputs in the command line, run "make" and then  "java Encoder frequencyFile k"


[Finish]
I finished all the parts of the assignment.

[Test Case 1]

[Command line]
java Encoder frequenciesFile 1000

[Input]
4
2
3
4
5
3
6
3
2

[Output]
---------------------------------------
Using command line arguments.
Filename: frequenciesFile, K = 1000
---------------------------------------
Encoding Table:
SYMBOL	WEIGHT	HUFFMAN CODE
a	4/32	000
b	2/32	001
c	3/32	010
d	4/32	011
e	5/32	100
f	3/32	1010
g	6/32	1011
h	3/32	110
i	2/32	111
---------------------------------------
Entropy: 3.0817475629608078
Average bits per symbol: 3.2222222222222223
Percent difference: 2.0%
---------------------------------------
2-symbol derived alphabet:
SYMBOL	WEIGHT	HUFFMAN CODE
dd	4/500	00000
hh	3/500	0000100
de	12/500	0000101
hi	2/500	000011
df	5/500	000100
dg	15/500	0001010
dh	5/500	0001011
di	2/500	00011
ia	6/500	00100
ic	2/500	001010
id	2/500	0010110
ie	4/500	0010111
ea	12/500	00110
eb	3/500	00111
if	5/500	010000
ec	4/500	010001
ig	5/500	01001
ed	10/500	010100
ih	4/500	010101
ee	12/500	010110
aa	13/500	010111
ii	2/500	011000
ef	6/500	011001
ab	2/500	01101
eg	16/500	011100
ac	7/500	01110100
eh	12/500	01110101
ad	9/500	0111011
ae	8/500	01111
ei	3/500	100000
af	4/500	1000010
ag	15/500	1000011
ah	4/500	10001
ai	3/500	1001000
fa	7/500	10010010
fb	1/500	10010011
fc	4/500	10010100
fd	8/500	10010101
ba	5/500	10010110
fe	9/500	100101110
ff	7/500	100101111
bb	1/500	1001100
fg	5/500	1001101
bc	3/500	1001110
fh	3/500	1001111
bd	6/500	1010000
be	4/500	10100010
fi	1/500	10100011
bf	2/500	101001
bg	7/500	101010
bh	4/500	1010110
bi	2/500	1010111
ga	11/500	101100
gb	7/500	101101
gc	7/500	1011100
gd	11/500	10111010
ge	10/500	10111011
ca	9/500	101111
gf	10/500	110000
gg	19/500	110001
cc	3/500	11001
cd	10/500	110100
gh	9/500	1101010
gi	7/500	1101011
ce	8/500	110110
cf	4/500	1101110
cg	12/500	1101111
ch	3/500	1110000
ci	2/500	1110001
ha	9/500	111001
hb	2/500	111010
hc	5/500	111011
hd	4/500	111100
da	9/500	111101
he	6/500	1111100
db	4/500	111110100
hf	6/500	111110101
hg	11/500	11111011
dc	2/500	111111
---------------------------------------
Entropy: 6.030282336998054
Average bits per symbol: 2.0
Percent difference: 50.0%
---------------------------------------

[Test Case 2]

[Command line]
java Encoder frequenciesFile 500

[Input]
4
2
3
4
5
3
6
3
2
5
8
4
2
4
5
6
7
7
2
1
3
4
8
6
4
5

[Output]
---------------------------------------
Using command line arguments.
Filename: frequenciesFile, K = 500
---------------------------------------
Encoding Table:
SYMBOL	WEIGHT	HUFFMAN CODE
a	4/113	0000
b	2/113	0001
c	3/113	0010
d	4/113	00110
e	5/113	00111
f	3/113	0100
g	6/113	010100
h	3/113	010101
i	2/113	01011
j	5/113	0110
k	8/113	0111
l	4/113	1000
m	2/113	1001
n	4/113	10100
o	5/113	101010
p	6/113	101011
q	7/113	10110
r	7/113	10111
s	2/113	11000
t	1/113	11001
u	3/113	1101
v	4/113	11100
w	8/113	11101
x	6/113	111100
y	4/113	111101
z	5/113	11111
---------------------------------------
Entropy: 4.560971831192281
Average bits per symbol: 4.884615384615385
Percent difference: 3.0%
---------------------------------------
2-symbol derived alphabet:
SYMBOL	WEIGHT	HUFFMAN CODE
xx	2/250	000000
pr	3/250	000001
pu	1/250	0000100
pw	2/250	00001010
px	3/250	00001011
yb	1/250	000011
py	2/250	000100
yc	1/250	000101
hv	1/250	000110
hw	2/250	000111
yh	1/250	001000
yj	2/250	00100100
yk	1/250	00100101
hz	1/250	00100110
qc	1/250	00100111
qd	1/250	00101000
qf	1/250	00101001
qj	1/250	0010101
ib	1/250	00101100
qk	2/250	00101101
qm	1/250	0010111
ie	1/250	00110000
yv	1/250	00110001
qo	1/250	0011001
qp	1/250	00110100
yx	1/250	00110101
yy	1/250	00110110
aa	1/250	00110111
qr	1/250	00111000
qv	1/250	00111001
qw	1/250	0011101
zb	1/250	0011110
ak	1/250	00111110
ze	1/250	00111111
ao	1/250	01000000
ra	2/250	01000001
iz	1/250	01000010
rd	1/250	01000011
as	2/250	01000100
re	2/250	01000101
rg	1/250	0100011
zo	1/250	01001000
ja	2/250	01001001
zs	2/250	01001010
jc	1/250	01001011
rl	1/250	0100110
jd	2/250	01001110
rn	2/250	01001111
zw	1/250	01010000
jg	1/250	01010001
ro	1/250	01010010
zx	1/250	01010011
rp	1/250	01010100
rv	2/250	01010101
jn	1/250	0101011
rw	3/250	0101100
rx	2/250	01011010
jp	1/250	01011011
jq	1/250	01011100
rz	1/250	01011101
jv	1/250	01011110
bo	1/250	01011111
sa	1/250	0110000
jx	1/250	01100010
bs	1/250	01100011
sg	1/250	01100100
sh	1/250	01100101
by	1/250	0110011
kd	2/250	0110100
sl	1/250	01101010
so	2/250	01101011
kg	1/250	0110110
sq	1/250	01101110
kl	3/250	01101111
ce	1/250	0111000
km	1/250	01110010
kn	3/250	01110011
ko	5/250	01110100
kq	2/250	01110101
ck	1/250	0111011
cm	1/250	01111000
kv	1/250	01111001
kw	1/250	01111010
kx	1/250	01111011
kz	2/250	01111100
cr	2/250	01111101
ti	1/250	0111111
lg	1/250	1000000
lh	1/250	10000010
dd	1/250	10000011
tu	1/250	1000010
de	1/250	10000110
dg	2/250	10000111
dj	1/250	1000100
dk	1/250	10001010
lt	1/250	10001011
dl	1/250	10001100
lv	1/250	10001101
lw	1/250	10001110
dp	1/250	10001111
ub	1/250	10010000
dr	2/250	10010001
lz	1/250	10010010
ue	1/250	10010011
du	1/250	10010100
dz	1/250	10010101
md	1/250	1001011
ul	1/250	1001100
uo	1/250	10011010
up	1/250	10011011
uq	1/250	10011100
ec	1/250	10011101
mo	1/250	10011110
mp	1/250	10011111
uy	1/250	1010000
ek	1/250	1010001
en	1/250	10100100
vb	1/250	10100101
vc	1/250	10100110
er	1/250	10100111
es	1/250	10101000
vj	1/250	10101001
nc	1/250	10101010
vk	2/250	10101011
ez	1/250	1010110
vo	1/250	10101110
vq	1/250	10101111
nj	1/250	10110000
fb	1/250	10110001
fd	1/250	10110010
ff	1/250	10110011
no	1/250	10110100
np	1/250	10110101
nq	3/250	10110110
vy	1/250	10110111
fj	1/250	10111000
nr	1/250	10111001
nu	1/250	1011101
nv	1/250	10111100
wa	1/250	10111101
nx	1/250	10111110
fq	2/250	10111111
wb	1/250	11000000
wc	1/250	11000001
fr	2/250	11000010
ft	1/250	11000011
we	1/250	11000100
fu	1/250	11000101
wg	1/250	11000110
wj	1/250	11000111
oe	1/250	11001000
of	1/250	11001001
wn	1/250	1100101
wp	1/250	1100110
wq	2/250	11001110
oj	1/250	11001111
ok	2/250	11010000
ol	1/250	11010001
ge	1/250	11010010
gf	1/250	11010011
oo	1/250	1101010
oq	2/250	11010110
gj	2/250	11010111
or	3/250	1101100
wz	1/250	1101101
gk	1/250	1101110
ow	1/250	1101111
go	1/250	11100000
gp	1/250	11100001
ox	1/250	11100010
xa	1/250	11100011
gr	2/250	11100100
oz	1/250	11100101
xd	1/250	11100110
gu	1/250	11100111
xg	1/250	1110100
gw	2/250	11101010
pa	1/250	11101011
gy	1/250	1110110
xk	1/250	11101110
gz	1/250	11101111
pd	3/250	11110000
pe	2/250	11110001
xp	2/250	11110010
xq	3/250	11110011
xr	2/250	11110100
pk	3/250	11110101
xs	1/250	11110110
xv	1/250	11110111
hf	1/250	111110
xw	1/250	1111110
po	1/250	1111111
---------------------------------------
Entropy: 7.4571502226778135
Average bits per symbol: 2.0
Percent difference: 58.0%
---------------------------------------

[Test Case 3]

[Command line]
java Encoder frequenciesFile 70

[Input]
4
2
3
4
5
3
6
3
2
3
8
4
2
4
5
6
7
7
2
1


[Output]
---------------------------------------
Using command line arguments.
Filename: frequenciesFile, K = 70
---------------------------------------
Encoding Table:
SYMBOL	WEIGHT	HUFFMAN CODE
a	4/81	000
b	2/81	0010
c	3/81	0011
d	4/81	01000
e	5/81	01001
f	3/81	0101
g	6/81	0110
h	3/81	01110
i	2/81	01111
j	3/81	1000
k	8/81	1001
l	4/81	101000
m	2/81	101001
n	4/81	10101
o	5/81	1011
p	6/81	11000
q	7/81	11001
r	7/81	1101
s	2/81	1110
t	1/81	1111
---------------------------------------
Entropy: 4.160080251341565
Average bits per symbol: 4.5
Percent difference: 4.0%
---------------------------------------
2-symbol derived alphabet:
SYMBOL	WEIGHT	HUFFMAN CODE
dd	1/35	00000
lm	1/35	00001
fh	1/35	00010
lt	1/35	00011
hq	1/35	00100
fr	1/35	00101
ds	1/35	0011
se	1/35	01000
oe	1/35	01001
oh	1/35	01010
qj	1/35	01011
ok	1/35	01100
gd	1/35	01101
ge	2/35	01110
gg	1/35	01111
ef	1/35	10000
km	1/35	10001
af	1/35	10010
gm	1/35	10011
kr	1/35	10100
gr	1/35	10101
eq	1/35	10110
rc	1/35	10111
ct	1/35	11000
re	1/35	11001
pc	1/35	1101
rf	1/35	11100
rh	1/35	111010
rk	2/35	111011
rl	1/35	111100
da	1/35	111101
ro	1/35	111110
lk	1/35	111111
---------------------------------------
Entropy: 5.01499730265925
Average bits per symbol: 2.0
Percent difference: 43.0%
---------------------------------------

[Test Case 4]

[Command line]
java Encoder frequenciesFile 150

[Input]
4
2
3
4
5
3
6
3
2
3
8
4
2
4
5
6
7
7
2
1
5
3
6
7

[Output]
---------------------------------------
Using command line arguments.
Filename: frequenciesFile, K = 150
---------------------------------------
Encoding Table:
SYMBOL	WEIGHT	HUFFMAN CODE
a	4/102	0000
b	2/102	0001
c	3/102	00100
d	4/102	001010
e	5/102	001011
f	3/102	0011
g	6/102	0100
h	3/102	0101
i	2/102	01100
j	3/102	01101
k	8/102	01110
l	4/102	01111
m	2/102	1000
n	4/102	1001
o	5/102	10100
p	6/102	101010
q	7/102	101011
r	7/102	1011
s	2/102	1100
t	1/102	1101
u	5/102	11100
v	3/102	11101
w	6/102	11110
x	7/102	11111
---------------------------------------
Entropy: 4.436276936968375
Average bits per symbol: 4.75
Percent difference: 3.0%
---------------------------------------
2-symbol derived alphabet:
SYMBOL	WEIGHT	HUFFMAN CODE
de	1/75	000000
df	1/75	000001
lq	1/75	000010
dn	1/75	000011
lx	1/75	000100
dq	1/75	000101
hw	1/75	00011
du	1/75	001000
qk	1/75	001001
ql	1/75	001010
uq	1/75	001011
ee	1/75	001100
qq	1/75	001101
uv	1/75	001110
qu	1/75	001111
ag	2/75	010000
el	1/75	010001
qx	1/75	010010
ah	1/75	010011
ak	1/75	010100
es	1/75	010101
ao	1/75	010110
ra	1/75	010111
eu	1/75	011000
rd	1/75	011001
vk	1/75	011010
ja	1/75	011011
ne	1/75	011100
rk	1/75	011101
jd	1/75	011110
rn	1/75	011111
jg	1/75	10000
rp	1/75	100010
bc	1/75	100011
fk	1/75	100100
nu	1/75	100101
nv	1/75	100110
fo	1/75	100111
nx	1/75	10100
fw	1/75	101010
fx	1/75	101011
oa	1/75	101100
bx	1/75	101101
ka	1/75	101110
sk	1/75	101111
wp	1/75	110000
wq	1/75	110001
gc	2/75	110010
kh	2/75	110011
sq	1/75	110100
gf	1/75	1101010
cb	1/75	1101011
kj	1/75	1101100
wv	1/75	1101101
kk	1/75	1101110
kp	2/75	1101111
gm	1/75	1110000
kr	1/75	1110001
ov	1/75	1110010
ow	1/75	1110011
kt	1/75	1110100
cl	2/75	1110101
gp	1/75	1110110
ku	1/75	1110111
xf	1/75	111100
cr	1/75	1111010
xq	1/75	1111011
xr	1/75	111110
xu	1/75	1111110
lk	1/75	1111111
---------------------------------------
Entropy: 6.095485357162556
Average bits per symbol: 2.0
Percent difference: 51.0%
---------------------------------------
