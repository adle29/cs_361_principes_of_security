package security;

import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;

import java.io.File;
import java.io.FileInputStream;

public class CovertChannel {

  public static void main(String[] args) {
    /* top-level class (SecureSystem) manages subjects and the reference monitor,
       and also serves as the command interpreter. */
       
    SecureSystem sys = new SecureSystem();
    String fileName = "instructionList";
    String file = "covertfile.txt";
    boolean verbose = false;

    /* Command line arguments */
    if (args.length == 1) {
      file = args[0];
    }
    else if (args.length == 2){
      file = args[1];
      if ( args[0].equals("v") )
        verbose = true;
    }

    /* activate verbose */
    sys.verbose = verbose;

    /* We add two subjects, one high and one low. */
    sys.createSubject("lyle", sys.refMon.securityLevel.LOW);
    sys.createSubject("hal", sys.refMon.securityLevel.HIGH);

    /* We add two objects, one high and one low. */
    // sys.refMon.createNewObject("lobj", sys.refMon.securityLevel.LOW);
    // sys.refMon.createNewObject("hobj", sys.refMon.securityLevel.HIGH);

    /* deletes log.out and messages.txt */
    sys.cleanFiles();

    /* set of instructions */
    String[] instructionsOne = {  "create hal xobj",
                                  "create lyle xobj",
                                  "write lyle xobj 1",
                                  "read lyle xobj",
                                  "destroy lyle xobj",
                                  "run lyle" };

    String[] instructionsTwo = {  "create lyle xobj",
                                  "write lyle xobj 1",
                                  "read lyle xobj",
                                  "destroy lyle xobj",
                                  "run lyle" };

    /* READING FILE */
    FileInputStream fileInputStream = null;
    File f = new File(file);
    int fileSize = (int)f.length();
    byte[] bFile = new byte[fileSize];

    final double startTime = System.currentTimeMillis();

    try {
       //convert file into array of bytes
       fileInputStream = new FileInputStream(file);
       fileInputStream.read(bFile);
       fileInputStream.close();
       for (int i = 0; i < bFile.length; i++){
          byte one = bFile[i];
          String stringBinary = String.format("%8s", Integer.toBinaryString(one)).replace(' ', '0');
          String bits[] = stringBinary.split("");
          for (int j = 0; j < bits.length; j++){
            /* Parsing every single bit */
            int bit = Integer.parseInt(bits[j]);
            /* parse instrucitons */
            if ( bit == 1)
              sys.parseListInstructions(instructionsTwo);
            else
              sys.parseListInstructions(instructionsOne);
          }
       }
    }

    catch (Exception e){
       e.printStackTrace();
    }

    final double endTime = System.currentTimeMillis();
    double bandwith = (double)(8*fileSize/(endTime - startTime));
    System.out.println("File length: " + fileSize);
    System.out.println("Total execution time: " + (endTime - startTime) );
    System.out.println("Bandwidth: " + bandwith );
  }
}
