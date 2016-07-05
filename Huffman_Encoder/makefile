JFLAGS = -d .
JC = javac -Xlint

all: HuffmanCode.class BitOutputStream.class HuffmanEncoder.class Leaf.class InternalNode.class Node.class CodeTree.class FrequencyTable.class Encoder.class

HuffmanCode.class: HuffmanCode.java
	$(JC) $(JFLAGS) HuffmanCode.java

BitOutputStream.class: BitOutputStream.java
	$(JC) $(JFLAGS) BitOutputStream.java

HuffmanEncoder.class: HuffmanEncoder.java
	$(JC) $(JFLAGS) HuffmanEncoder.java

Leaf.class: Leaf.java
	$(JC) $(JFLAGS) Leaf.java

InternalNode.class: InternalNode.java
	$(JC) $(JFLAGS) InternalNode.java

Node.class: Node.java
	$(JC) $(JFLAGS) Node.java

Encoder.class: Encoder.java
	$(JC) $(JFLAGS) Encoder.java

FrequencyTable.class: FrequencyTable.java
	$(JC) $(JFLAGS) FrequencyTable.java

CodeTree.class: CodeTree.java
	$(JC) $(JFLAGS) CodeTree.java

clean:
		$(RM) *.class

run:
	java Encoder
