UTEID: aa63765; kcv296 ;
FIRSTNAME: Abraham; Kiankris;
LASTNAME: Adberstein; Villagonzalo;
CSACCOUNT: sniper; kiankris;
EMAIL: aadberstein@gmail.com; kcv@utexas.edu;

[Program 4]
[Description]
There is one java file, AES.java. It reads a block of text from a file then sends it to the encrypt method. In this method it runs through the rounds process of encrypting the block using the AES scheme. Abraham did the subbytes, shiftrows, and keyexpansion methods. Kiankris did mixColumns and addRoundKey. Upon completion it writes the completed block to a file named plaintext.enc. Subsequently the program runs through a method called decrypt which does the process that encrypt does in reverse. There are seperate inverse methods for subbytes, mix columns, and shift rows. The invMixColumns method uses Dr. Young's code. After completing the decrryption process the decrypted message is written to a file named plaintext.enc.dec.

[Finish]
We finished the most/half/one quarter of this assignment. There are some bugs in that only the base case of a string of 0's as the plaintext will be encrypted, however any key seems to be usable. 

[Test Case 1]

[Command line]

[Timing Information]

[Input Filenames]

[Output Filenames]




[Test Case 2]

[Command line]

[Timing Information]

[Input Filenames]

[Output Filenames]




[Test Case 3]

[Command line]

[Timing Information]

[Input Filenames]

[Output Filenames]



[Test Case 4]

[Command line]

[Timing Information]

[Input Filenames]

[Output Filenames]
