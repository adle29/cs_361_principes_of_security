package security;

import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;

class SecureSystem {

  ArrayList<Subject> subjects = new ArrayList<Subject>();
  ReferenceMonitor refMon = new ReferenceMonitor();
  boolean verbose = false;

  public String printState(){
    //print each instruction and result
    String PRINT = "The current system is: \n";

    Iterator<Obj> itrObjects = this.refMon.objectManager.objects.iterator();
    while (itrObjects.hasNext()) {
      Obj object = itrObjects.next();
      PRINT += " "+object.name + " has value " + object.value +"\n";
    }

    Iterator<Subject> itr = this.subjects.iterator();
    while (itr.hasNext()) {
      Subject element = itr.next();
      PRINT += " "+element.name + " has recently read " + element.TEMP +"\n";
    }

    return PRINT;
  }

  public void createSubject(String name, int label){
    Subject newSubject = new Subject(name, label);
    subjects.add(newSubject);
  }

  public Subject getSubject(String name){
    Iterator<Subject> itr = subjects.iterator();
    while (itr.hasNext()) {
      Subject element = itr.next();
      if (element.name.equals(name))
        return element;
    }
    return null;
  }

  public void parseInstruction(String line) {
    //System.out.println(line);
    String[] parts = line.split(" ");
    String PRINT = "";

    /* RUN */
    if ( parts.length == 2) {
      Subject subject = this.getSubject(parts[1]);
      String action = parts[0];
      if ( subject != null && action.equals("run")) {
        PRINT += subject.name + " " + action + "s\n";
        subject.run();
      } else {
        PRINT += "Bad Instruction" + "\n";
      }
    }

    /* READ, WRITE, DESTROY */
    else if ( parts.length == 3 || parts.length == 4){
      //parse instructions, get subject
      Subject subject = this.getSubject(parts[1]);
      String objectName = parts[2];
      String value = "";
      String action = parts[0];

      if ( subject != null ) {
        //make instruction object and call reference monitor
        if (parts.length == 4 && action.equals("write")){
          value = parts[3];
          PRINT += subject.name + " " + action + "s value " + value + " to " + objectName + "\n";
          InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
          this.refMon.instruction(instrObj);
        }
        else if (parts.length == 3 && action.equals("read")) {
          PRINT += subject.name + " " + action + "s " + objectName + "\n";
          InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
          this.refMon.instruction(instrObj);
        }
        else if (parts.length == 3 && action.equals("create")) {
          /* The semantics of CREATE is that a new object is added to the state with SecurityLevel
          equal to the level of the creating subject. It is given an initial value of 0. If there
          already exists an object with that name at any level, the operation is a no-op. */
          PRINT += subject.name + " " + action + "s " + objectName + "\n";
          InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
          this.refMon.instruction(instrObj);
        }
        else if (parts.length == 3 && action.equals("destroy")) {
          /* DESTROY will eliminate the designated object from the state, assuming
          that the object exists and the subject has WRITE access to the object
          according to the *-property of BLP. Otherwise, the operation is a no-op. */
          PRINT += subject.name + " " + action + "s " + objectName + "\n";
          InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
          this.refMon.instruction(instrObj);
        }
        else {
          PRINT += "Bad Instruction" +"\n";
        }

      }
      else {
        PRINT += "Bad Instruction" + "\n";
      }

    } else  {
      PRINT += "Bad Instruction" + "\n";
    }

    /* Prints log */ 
    if (verbose) {
      try {
        File file = new File("log.out");
        PRINT += this.printState();

        // if file doesnt exists, then create it
        if (!file.exists()) {
          file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(PRINT);
        bw.close();

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }

  public void parseListInstructions(String[] lines) {
    for(int i = 0; i < lines.length; i++){
      this.parseInstruction(lines[i]);
    }
  }

  public void cleanFiles() {
    try{

    		File file = new File("log.out");
        File file2 = new File("message.txt");

        file.delete();
        file2.delete();

    	}catch(Exception e){

    		e.printStackTrace();

    	}
  }

  public void readFile(String fileName){
    //read file
    String line = null;

    try {
       FileReader fileReader = new FileReader(fileName);
       BufferedReader bufferedReader = new BufferedReader(fileReader);

       while( (line = bufferedReader.readLine()) != null) {
         this.parseInstruction(line);
       }
       bufferedReader.close();
     }
     catch(FileNotFoundException ex) {
       System.out.println("Unable to open file '" + fileName + "'");
     }
     catch(IOException ex) {
       System.out.println( "Error reading file '"+ fileName + "'");
     }
  }
}
