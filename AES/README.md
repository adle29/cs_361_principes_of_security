Authors: Abraham Adberstein, Kris Villagonzalo

[Program 4]
[Description]
There is one java file, AES.java. It reads a block of text from a file then sends it to the encrypt method. In this method it runs through the rounds process of encrypting the block using the AES scheme. Abraham did the subbytes, shiftrows, and keyexpansion methods. Kiankris did mixColumns and addRoundKey. Upon completion it writes the completed block to a file named plaintext.enc. Subsequently the program runs through a method called decrypt which does the process that encrypt does in reverse. There are seperate inverse methods for subbytes, mix columns, and shift rows. The invMixColumns method uses Dr. Young's code. After completing the decrryption process the decrypted message is written to a file named plaintext.enc.dec.

To run:

make
make run1 #or any of the following for different test cases
make run2
make run3
make run4


[Finish]
We finished most of this assignment. We didn't check for keys with larger or lower length than 32. The program can work any key size. The program works on the 128 example  and 256 example. However, for our 3rd and 4th cases it did not work :/. For the 256 example in the website, it does decrypt back correctly but for some reason the ciphertext is not the same.

[Test Case 1]

[Command line]
make run1
[Timing Information]

Encryption with Key Size: 8 Rounds: 14
Completion Time: 11ms

Decryption with Key Size: 8 Rounds: 14 filename: plaintext.enc
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
Completion Time: 10ms

Decryption with Key Size: 4 Rounds: 10 filename: plaintext2.enc
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
Completion Time: 14ms

Decryption with Key Size: 8 Rounds: 14 filename: plaintext3.enc
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
