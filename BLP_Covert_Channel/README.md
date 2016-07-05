Author: Abraham Adberstein

[Program 2]
[Description]

PROGRAM:

The main file to run is CoverChannel. Inside the file in the "main" function, an instance of the same class is created, named "sys". Two subjects are created. Objects are managed by the ObjectManager object which is located inside the reference monitor object, "refMon". Each instruction object is processed by the reference Monitor, "refMon". The reference monitor will decide what action to take judging by the level of the subject and object.

This is what basically happens:

CovertChannel class
  • Reads every single bit of the file input
  • According to the bit, will run one of two possible set of instructions.
  • When the RUN instruction is ran, the run() function is ran which is inside the subject.
  • The run() will take the value from the subject and concatenate it with a character string, until it has 8 bits.
  * Then, when 8 bits are gotten together, they are converted into ASCII, the ASCII character then is written to a file.


Class Structure, package:

SecurityLevel.class #grants permissions to WRITE, READ, CREATE, DESTROY
Obj.class #OBJECT'S CLASS
Subject.class #SUBJECT'S CLASS
ObjectManager.class #Manages actions on objects
InstructionObject.class #Parsed instruction object
ReferenceMonitor.class #manages a parsed instruction
SecureSystem.class  #parses lines into InstructionObject, passed to Reference monitor.
CovertChannel.class #start here

Program writes to:

log.out #shows instructions ran by Covert Channel Program
message.txt #message passed by Covert Channel

RUNNING PROGRAM:
The program is using a package. Don't forget to run "make" FIRST. 

To compile files:
make

To run files either one of this functions
1. make run  #run the files
2. java security/CovertChannel <input file>
3. java security/CovertChannel v <input file>

make clean # clean folders

[Machine Information]
java version "1.8.0_60"

[Source Description]
The source files are in the folder inputs.
inputs/input.txt
inputs/input1.txt
inputs/input2.txt

[Finish]
I finished everything.

[Results Summary]
[No.]	[DocumentName] 		[Size] 	 	[Bandwidth]
1.     input.txt         732     42.43478260869565 bits/ms
2.     input1.txt        1495    57.77777777777778 bits/ms
3.     input2.txt        4548    82.13092550790067 bits/ms
