JFLAGS = -d .
JC = javac

all: SecurityLevel.class Obj.class Subject.class ObjectManager.class InstructionObject.class ReferenceMonitor.class SecureSystem.class CovertChannel.class

SecurityLevel.class: SecurityLevel.java
	$(JC) $(JFLAGS) SecurityLevel.java

Subject.class: Subject.java
	$(JC) $(JFLAGS) Subject.java

Obj.class: Obj.java
	$(JC) $(JFLAGS) Obj.java

InstructionObject.class: InstructionObject.java
	$(JC) $(JFLAGS) InstructionObject.java

ObjectManager.class: ObjectManager.java
	$(JC) $(JFLAGS) ObjectManager.java

ReferenceMonitor.class: ReferenceMonitor.java
	$(JC) $(JFLAGS) ReferenceMonitor.java

SecureSystem.class: SecureSystem.java
	$(JC) $(JFLAGS) SecureSystem.java

CovertChannel.class: CovertChannel.java
	$(JC) $(JFLAGS) CovertChannel.java

clean:
		$(RM) *.class
		rm -r security

run:
	java security/CovertChannel ./inputs/input.txt
