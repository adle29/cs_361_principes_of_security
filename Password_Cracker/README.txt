UTEID: aa63765;
FIRSTNAME: Abraham;
LASTNAME: Adberstein;
CSACCOUNT: sniper;
EMAIL: aadberstein@gmail.com;

[Program 5]
[Description]
I finished the assignment. There is a crack function that takes the important paramaters (username, name, lastname, hash, salt) and then calls crack for each account. Account is a big for loop that iterates over the dictionary. Username, name, and lastname are appended to the beginning of the dictionary. There are many tranformations inside the while loop. If the password's hash matches the original hash, then it exits the loop and continues to the next accound. 

To run 

make; make run1
make; make run2 

[Finish]
I found 19/20 on passwd1 in time 849 seconds. We found 11/20 on passwd2 in time 1277 seconds. Probably if I moved more comabinations of the transformations inside the for loop that adds extra characters, I could have gotten more passwords from passwd2, but would increment the time much more.

[Test Case 1]

[Input]

passwd1

michael:atbWfKL4etk4U:500:500:Michael Ferris:/home/michael:/bin/bash
abigail:&i4KZ5wmac566:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bUx9LiAcW8As:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qt0.GlIrXuKs:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:feohQuHOnMKGE:504:504:Alexander Brown:/home/alexander:
james:{ztmy9azKzZgU:505:505:James Dover:/home/james:/bin/bash
benjamin:%xPBzF/TclHvg:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:khnVjlJN3Lyh2:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4DBHapAtnjGk:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7we09tBSVT76o:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3dIJJXzELzcRE:510:510:Evan Whitney:/home/evan:/bin/bash
jack:jsQGVbJ.IiEvE:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@EbBlXGLTue6:512:512:Victor Esperanza:/home/victor:
caleb:8joIBJaXJvTd2:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxsr/UAKmKnvo:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gwjT8yTnSCVQo:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KelgNcBOZdHmA:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5WW698tSZJE9I:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cI6tOT/9D2r6:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8jwuve9rQBo.:519:519:Paige Reiser:/home/paige:/bin/bash

[Output]

java PasswordCrack wordlist passwd1
Username: michael    hash: atbWfKL4etk4U  salt: at    Name: Michael    Last Name: Ferris     Password: michael         Time: 0 s
Username: abigail    hash: &i4KZ5wmac566  salt: &i    Name: Abigail    Last Name: Smith      Password: liagiba         Time: 0 s
Username: samantha   hash: (bUx9LiAcW8As  salt: (b    Name: Samantha   Last Name: Connelly   Password: amazing         Time: 2 s
Username: tyler      hash: <qt0.GlIrXuKs  salt: <q    Name: Tyler      Last Name: Jones      Password: eeffoc          Time: 15 s
Username: alexander  hash: feohQuHOnMKGE  salt: fe    Name: Alexander  Last Name: Brown      Password: squadro         Time: 77 s
Username: james      hash: {ztmy9azKzZgU  salt: {z    Name: James      Last Name: Dover      Password: icious          Time: 89 s
Username: benjamin   hash: %xPBzF/TclHvg  salt: %x    Name: Benjamin   Last Name: Ewing      Password: abort6          Time: 0 s
Username: morgan     hash: khnVjlJN3Lyh2  salt: kh    Name: Morgan     Last Name: Simmons    Password: rdoctor         Time: 23 s
Username: jennifer   hash: e4DBHapAtnjGk  salt: e4    Name: Jennifer   Last Name: Elmer      Password: doorrood        Time: 23 s
Username: nicole     hash: 7we09tBSVT76o  salt: 7w    Name: Nicole     Last Name: Rizzo      Password: keyskeys        Time: 43 s
Username: evan       hash: 3dIJJXzELzcRE  salt: 3d    Name: Evan       Last Name: Whitney    Password: Impact          Time: 39 s
Username: jack       hash: jsQGVbJ.IiEvE  salt: js    Name: Jack       Last Name: Gibson     Password: sATCHEL         Time: 72 s
Username: victor     hash: w@EbBlXGLTue6  salt: w@    Name: Victor     Last Name: Esperanza  Password: THIRTY          Time: 83 s
Username: caleb      hash: 8joIBJaXJvTd2  salt: 8j    Name: Caleb      Last Name: Patterson  Password: teserP          Time: 63 s
Username: nathan     hash: nxsr/UAKmKnvo  salt: nx    Name: Nathan     Last Name: Moore      Password: sHREWDq         Time: 73 s
Username: connor     hash: gwjT8yTnSCVQo  salt: gw    Name: Connor     Last Name: Larson     Password: enoggone        Time: 34 s
Username: rachel     hash: KelgNcBOZdHmA  salt: Ke    Name: Rachel     Last Name: Saxon      Password: obliqu3         Time: 53 s
Username: dustin     hash: 5WW698tSZJE9I  salt: 5W    Name: Dustin     Last Name: Hart       Password: litpeR          Time: 68 s
Username: maria      hash: !cI6tOT/9D2r6  salt: !c    Name: Maia       Last Name: Salizar    Password: Salizar         Time: 0 s
Username: paige      hash: T8jwuve9rQBo.  salt: T8    Name: Paige      Last Name: Reiser     Password: ---             Time: 92 s
TOTALTIME: 849 s
PASSWORDS CRACKED: 19

[Test Case 2]

[Input]

passwd2

michael:atQhiiJLsT6cs:500:500:Michael Ferris:/home/michael:/bin/bash
abigail:&ileDTgJtzCRo:501:501:Abigail Smith:/home/abigail:/bin/tcsh
samantha:(bt0xiAwCf7nA:502:502:Samantha Connelly:/home/samantha:/bin/bash
tyler:<qf.L9z1/tZkA:503:503:Tyler Jones:/home/tyler:/bin/tcsh
alexander:fe8PnYhq6WoOQ:504:504:Alexander Brown:/home/alexander:
james:{zQOjvJcHMs7w:505:505:James Dover:/home/james:/bin/bash
benjamin:%xqFrM5RVA6t6:506:506:Benjamin Ewing:/home/benjamin:/bin/bash
morgan:kh/1uC5W6nDKc:507:507:Morgan Simmons:/home/morgan:/bin/bash
jennifer:e4EyEMhNzYLJU:508:508:Jennifer Elmer:/home/jennifer:/bin/bash
nicole:7wKTWsgNJj6ac:509:509:Nicole Rizzo:/home/nicole:/bin/tcsh
evan:3d1OgBYfvEtfg:510:510:Evan Whitney:/home/evan:/bin/bash
jack:js5ctN1leUABo:511:511:Jack Gibson:/home/jack:/bin/bash
victor:w@FxBZv.d0y/U:512:512:Victor Esperanza:/home/victor:
caleb:8jGWbU0xbKz06:513:513:Caleb Patterson:/home/caleb:/bin/bash
nathan:nxr9OOqvZjbGs:514:514:Nathan Moore:/home/nathan:/bin/ksh
connor:gw9oXmw1L08RM:515:515:Connor Larson:/home/connor:/bin/bash
rachel:KenK1CTDGr/7k:516:516:Rachel Saxon:/home/rachel:/bin/bash
dustin:5Wb2Uqxhoyqfg:517:517:Dustin Hart:/home/dustin:/bin/csh
maria:!cSaQELm/EBV.:518:518:Maia Salizar:/home/maria:/bin/zsh
paige:T8U5jQaDVv/o2:519:519:Paige Reiser:/home/paige:/bin/bash


[Output]

java PasswordCrack wordlist passwd2
Username: michael    hash: atQhiiJLsT6cs  salt: at    Name: Michael    Last Name: Ferris     Password: tremors         Time: 83 s
Username: abigail    hash: &ileDTgJtzCRo  salt: &i    Name: Abigail    Last Name: Smith      Password: Saxon           Time: 5 s
Username: samantha   hash: (bt0xiAwCf7nA  salt: (b    Name: Samantha   Last Name: Connelly   Password: ---             Time: 89 s
Username: tyler      hash: <qf.L9z1/tZkA  salt: <q    Name: Tyler      Last Name: Jones      Password: eltneg          Time: 31 s
Username: alexander  hash: fe8PnYhq6WoOQ  salt: fe    Name: Alexander  Last Name: Brown      Password: ---             Time: 91 s
Username: james      hash: {zQOjvJcHMs7w  salt: {z    Name: James      Last Name: Dover      Password: ---             Time: 91 s
Username: benjamin   hash: %xqFrM5RVA6t6  salt: %x    Name: Benjamin   Last Name: Ewing      Password: soozzoos        Time: 90 s
Username: morgan     hash: kh/1uC5W6nDKc  salt: kh    Name: Morgan     Last Name: Simmons    Password: dIAMETER        Time: 21 s
Username: jennifer   hash: e4EyEMhNzYLJU  salt: e4    Name: Jennifer   Last Name: Elmer      Password: ElmerJ          Time: 0 s
Username: nicole     hash: 7wKTWsgNJj6ac  salt: 7w    Name: Nicole     Last Name: Rizzo      Password: INDIGNITIES     Time: 39 s
Username: evan       hash: 3d1OgBYfvEtfg  salt: 3d    Name: Evan       Last Name: Whitney    Password: ---             Time: 91 s
Username: jack       hash: js5ctN1leUABo  salt: js    Name: Jack       Last Name: Gibson     Password: ellows          Time: 6 s
Username: victor     hash: w@FxBZv.d0y/U  salt: w@    Name: Victor     Last Name: Esperanza  Password: ---             Time: 91 s
Username: caleb      hash: 8jGWbU0xbKz06  salt: 8j    Name: Caleb      Last Name: Patterson  Password: zoossooz        Time: 92 s
Username: nathan     hash: nxr9OOqvZjbGs  salt: nx    Name: Nathan     Last Name: Moore      Password: uPLIFTr         Time: 88 s
Username: connor     hash: gw9oXmw1L08RM  salt: gw    Name: Connor     Last Name: Larson     Password: nosral          Time: 0 s
Username: rachel     hash: KenK1CTDGr/7k  salt: Ke    Name: Rachel     Last Name: Saxon      Password: ---             Time: 92 s
Username: dustin     hash: 5Wb2Uqxhoyqfg  salt: 5W    Name: Dustin     Last Name: Hart       Password: ---             Time: 93 s
Username: maria      hash: !cSaQELm/EBV.  salt: !c    Name: Maia       Last Name: Salizar    Password: ---             Time: 92 s
Username: paige      hash: T8U5jQaDVv/o2  salt: T8    Name: Paige      Last Name: Reiser     Password: ---             Time: 92 s
TOTALTIME: 1277 s
PASSWORDS CRACKED: 11
