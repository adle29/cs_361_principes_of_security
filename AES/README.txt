UTEID: aa63765; kcv296 ;
FIRSTNAME: Abraham; Kiankris;
LASTNAME: Adberstein; Villagonzalo;
CSACCOUNT: sniper; kiankris;
EMAIL: aadberstein@gmail.com; kcv@utexas.edu;

[Program 4]
[Description]
There is one java file, AES.java. It reads a block of text from a file then sends it to the encrypt method. In this method it runs through the rounds process of encrypting the block using the AES scheme. Abraham did the subbytes, shiftrows, and keyexpansion methods. Kiankris did mixColumns and addRoundKey. Upon completion it writes the completed block to a file named plaintext.enc. Subsequently the program runs through a method called decrypt which does the process that encrypt does in reverse. There are seperate inverse methods for subbytes, mix columns, and shift rows. The invMixColumns method uses Dr. Young's code. After completing the decrryption process the decrypted message is written to a file named plaintext.enc.dec.

[Finish]
We finished most of this assignment. We didn't check for keys with larger or lower length than 32. Encryption and Decryption of a plaintext was succesful.  


[Test Case 1]

[Command line]
make run1
[Timing Information]

Encryption with Key Size: 8 Rounds: 14
Start Time: 1467439290091ms
End Time: 1467439290102ms
Completion Time: 11ms

Decryption with Key Size: 8 Rounds: 14 filename: plaintext.enc
Start Time: 1467439291422ms
End Time: 1467439291432ms
Completion Time: 10ms

[Input Filenames]
plaintext
[Output Filenames]
plaintext.enc.dec



[Test Case 2]

[Command line]
make run2
[Timing Information]

Encryption with Key Size: 4 Rounds: 10
Start Time: 1467439314333ms
End Time: 1467439314343ms
Completion Time: 10ms

Decryption with Key Size: 4 Rounds: 10 filename: plaintext2.enc
Start Time: 1467439315677ms
End Time: 1467439315687ms
Completion Time: 10ms

[Input Filenames]
plaintext2
[Output Filenames]
plaintext2.enc.dec

[Test Case 3]

[Command line]
make run3
[Timing Information]
Encryption with Key Size: 8 Rounds: 14
Start Time: 1467439329174ms
End Time: 1467439329188ms
Completion Time: 14ms

Decryption with Key Size: 8 Rounds: 14 filename: plaintext3.enc
Start Time: 1467439330574ms
End Time: 1467439330586ms
Completion Time: 12ms

[Input Filenames]
plaintext3
[Output Filenames]
plaintext3.enc.dec


[Test Case 4]

[Command line]
make run4
[Timing Information]
Encryption with Key Size: 8 Rounds: 14
Start Time: 1467439369501ms
End Time: 1467439369516ms
Completion Time: 15ms

Decryption with Key Size: 8 Rounds: 14 filename: plaintext4.enc
Start Time: 1467439370847ms
End Time: 1467439370862ms
Completion Time: 15ms

[Input Filenames]
plaintext4
[Output Filenames]
plaintext4.enc.dec
