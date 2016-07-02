JC = javac -Xlint

all: AES.class

AES.class: AES.java
	$(JC) AES.java

clean:
		$(RM) *.class

run:
	javac AES.java ; java AES d 256 -length 256 -mode ECB -key key -input plaintext.enc
	javac AES.java ; java AES d 256 -length 256 -mode ECB -key key -input plaintext.enc
