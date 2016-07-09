JFLAGS = -d .
JC = javac -Xlint

all: jcrypt.class PasswordCrack.class

jcrypt.class: jcrypt.java
	$(JC) $(JFLAGS) jcrypt.java

PasswordCrack.class: PasswordCrack.java
	$(JC) $(JFLAGS) PasswordCrack.java

clean:
		$(RM) *.class

run1:
	java PasswordCrack wordlist passwd1 

run2:
	java PasswordCrack wordlist passwd2 
	
# run1:
# 	javac AES.java ; java AES e -length 256 -key key -input plaintext
# 	javac AES.java ; java AES d -length 256 -key key -input plaintext.enc
#
# run2:
# 	javac AES.java ; java AES e -length 128 -key key -input plaintext2
# 	javac AES.java ; java AES d -length 128 -key key -input plaintext2.enc
#
# run3:
# 	javac AES.java ; java AES e -length 256 -key key -input plaintext3
# 	javac AES.java ; java AES d -length 256 -key key -input plaintext3.enc
#
# run4:
# 	javac AES.java ; java AES e -length 256 -key key -input plaintext4
# 	javac AES.java ; java AES d -length 256 -key key -input plaintext4.enc
