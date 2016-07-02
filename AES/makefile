JC = javac -Xlint

all: AES.class

AES.class: AES.java
	$(JC) AES.java

clean:
		$(RM) *.class

run:
	javac AES.java ; java AES e -length 256 -key key -input plaintext
	javac AES.java ; java AES d -length 256 -key key -input plaintext.enc
