Author: Abraham Adberstein

[Program 1]
[Description]
The main file to run is SecureSystem. Inside the file in the "main" function, an instance of the same class is created, named "sys". Two objects and two subjects are created as well by SecureSystem class methods. Then, the file "instructionList" is read. Every line of the file, "instructionList", is read and parsed as an instruction object, "InstructionObject". Objects are managed by the ObjectManager object which is located inside the reference monitor object, "refMon". Each instruction object is processed by the reference Monitor, "refMon". The reference monitor will decide what action to take judging by the level of the subject and object. There "executeRead" or "executeWrite" are ran accordingly. How to run the program: make ; java SecureSystem. Or you can run: javac SecureSystem.java ;  java SecureSystem

[Finish]
I finished all the assignment. No bugs found.

[Test Cases]
[Input of test 1]
write hal hobj
read hal
write lyle lobj 10
read hal lobj
write lyle hobj 20
write hal lobj 200
read hal hobj
read lyle lobj
read lyle hobj
foo lyle lobj
Hi lyle, This is hal
The missile launch code is 1234567

[Output of test 1]
Reading from file: instructionList

Bad Instruction
The current state is:
   lobj has value: 0
   hobj has value: 0
   lyle has recently read: 0
   hal has recently read: 0

Bad Instruction
The current state is:
   lobj has value: 0
   hobj has value: 0
   lyle has recently read: 0
   hal has recently read: 0

lyle writes value 10 to lobj
The current state is:
   lobj has value: 10
   hobj has value: 0
   lyle has recently read: 0
   hal has recently read: 0

hal reads lobj
The current state is:
   lobj has value: 10
   hobj has value: 0
   lyle has recently read: 0
   hal has recently read: 10

lyle writes value 20 to hobj
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 0
   hal has recently read: 10

hal writes value 200 to lobj
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 0
   hal has recently read: 10

hal reads hobj
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 0
   hal has recently read: 20

lyle reads lobj
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 10
   hal has recently read: 20

lyle reads hobj
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 0
   hal has recently read: 20

Bad Instruction
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 0
   hal has recently read: 20

Bad Instruction
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 0
   hal has recently read: 20

Bad Instruction
The current state is:
   lobj has value: 10
   hobj has value: 20
   lyle has recently read: 0
   hal has recently read: 20

[Input of test 2]
read hal hobj
read hal lobj
write hal lobj 1000
write hal hobj 1000
123 213 2131

[Output of test 2]
hal reads hobj
The current system is:
 lobj has value 0
 hobj has value 0
 lyle has recently read 0
 hal has recently read 0

hal reads lobj
The current system is:
 lobj has value 0
 hobj has value 0
 lyle has recently read 0
 hal has recently read 0

hal writes value 1000 to lobj
The current system is:
 lobj has value 0
 hobj has value 0
 lyle has recently read 0
 hal has recently read 0

hal writes value 1000 to hobj
The current system is:
 lobj has value 0
 hobj has value 1000
 lyle has recently read 0
 hal has recently read 0

Bad Instruction
The current system is:
 lobj has value 0
 hobj has value 1000
 lyle has recently read 0
 hal has recently read 0

[Input of test 3]
read lyle lobj
read lyle hobj
write lyle lobj 300
write lyle hobj 300
run run run

[Output of test 3]
lyle reads lobj
The current system is:
 lobj has value 0
 hobj has value 0
 lyle has recently read 0
 hal has recently read 0

lyle reads hobj
The current system is:
 lobj has value 0
 hobj has value 0
 lyle has recently read 0
 hal has recently read 0

lyle writes value 300 to lobj
The current system is:
 lobj has value 300
 hobj has value 0
 lyle has recently read 0
 hal has recently read 0

lyle writes value 300 to hobj
The current system is:
 lobj has value 300
 hobj has value 300
 lyle has recently read 0
 hal has recently read 0

Bad Instruction
The current system is:
 lobj has value 300
 hobj has value 300
 lyle has recently read 0
 hal has recently read 0

[Input of test 4]
1234314
write hal hobj 1
read hal lobj
write lyle lobj 300
read lyle hobj
write hal hobj 500

[Output of test 4]
Bad Instruction
The current system is:
 lobj has value 0
 hobj has value 0
 lyle has recently read 0
 hal has recently read 0

hal writes value 1 to hobj
The current system is:
 lobj has value 0
 hobj has value 1
 lyle has recently read 0
 hal has recently read 0

hal reads lobj
The current system is:
 lobj has value 0
 hobj has value 1
 lyle has recently read 0
 hal has recently read 0

lyle writes value 300 to lobj
The current system is:
 lobj has value 300
 hobj has value 1
 lyle has recently read 0
 hal has recently read 0

lyle reads hobj
The current system is:
 lobj has value 300
 hobj has value 1
 lyle has recently read 0
 hal has recently read 0

hal writes value 500 to hobj
The current system is:
 lobj has value 300
 hobj has value 500
 lyle has recently read 0
 hal has recently read 0
