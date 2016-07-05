import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;

class Subject{
  String TEMP = "0";
  int level;
  String name;

  public Subject(String name, int level){
    this.name = name;
    this.level = level;
  }
}

class Obj{
  String value = "0";
  int level;
  String name;

  public Obj(String name, int level){
    this.name = name;
    this.level = level;
  }
}

class InstructionObject {
  Subject subject;
  String objectName;
  String action;
  String value;

  public InstructionObject(Subject subject, String action, String objectName, String value ){
    this.subject = subject;
    this.action = action;
    this.objectName = objectName;
    this.value = value;
  }
}

class SecurityLevel {
   final int LOW = 0;
   final int HIGH = 100;

   boolean checkClearance(String action, int subjectLevel, int objectLevel){
     if ( action.equals("read")){
        if ( subjectLevel >= objectLevel )
          return true;
        return false;
     }

     if ( action.equals("write")){
        if ( subjectLevel <= objectLevel )
          return true;
        return false;
     }

     return false;
   }
}

//Objects are managed by the ObjectManager class.
class ObjectManager {
  ArrayList<Obj> objects = new ArrayList<Obj>();

  private Obj getObject (String name) {
    Iterator<Obj> itr = objects.iterator();
    while (itr.hasNext()){
      Obj object = itr.next();
      if ( object.name.equals(name) )
        return object;
    }
    return null;
  }

  boolean objectExists(String name){
    return this.getObject(name) != null;
  }

  int getObjectLevel(String name){
    return this.getObject(name).level;
  }

  String read(String name){
    return this.getObject(name).value;
  }

  void write(String name, String value){
    this.getObject(name).value = value;
  }
}

class ReferenceMonitor {

  ObjectManager objectManager = new ObjectManager();
  SecurityLevel securityLevel = new SecurityLevel();

  void createNewObject(String name, int level){
    Obj newObject = new Obj(name, level);
    objectManager.objects.add(newObject);
  }

  void executeRead (Subject subject, String objectName) {
    subject.TEMP = objectManager.read(objectName);
  }

  void executeWrite(Subject subject, String objectName, String value) {
    objectManager.write(objectName, value);
  }

  void instruction(InstructionObject instr){
    //process instruction
    int objectLevel = this.objectManager.getObjectLevel(instr.objectName);
    Subject subject = instr.subject;
    String action = instr.action;

    if (action.equals("read") ){
      if ( this.securityLevel.checkClearance( action, subject.level, objectLevel) )
        this.executeRead(subject, instr.objectName);
      else
        subject.TEMP = "0";
    }

    if (action.equals("write") ){
      if ( this.securityLevel.checkClearance( action, subject.level, objectLevel) )
        this.executeWrite(subject, instr.objectName, instr.value);
    }

  } //end instruction method

}


public class SecureSystem {

  ArrayList<Subject> subjects = new ArrayList<Subject>();
  ReferenceMonitor refMon = new ReferenceMonitor();

  public void printState(SecureSystem sys){
    //print each instruction and result
    System.out.println("The current system is: ");

    Iterator<Obj> itrObjects = sys.refMon.objectManager.objects.iterator();
    while (itrObjects.hasNext()) {
      Obj object = itrObjects.next();
      System.out.println(" "+object.name + " has value " + object.value);
    }

    Iterator<Subject> itr = subjects.iterator();
    while (itr.hasNext()) {
      Subject element = itr.next();
      System.out.println(" "+element.name + " has recently read " + element.TEMP);
    }
    System.out.println();
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

  public static void main(String[] args) {
    //System.out.println("Starting Secure System");
    SecureSystem sys = new SecureSystem();
    // top-level class (SecureSystem) manages subjects and the reference monitor,
    // and also serves as the command interpreter.

    String fileName = "instructionList";
    String line = null;

    // We add two subjects, one high and one low.
    sys.createSubject("lyle", sys.refMon.securityLevel.LOW);
    sys.createSubject("hal", sys.refMon.securityLevel.HIGH);

    // We add two objects, one high and one low.
    sys.refMon.createNewObject("lobj", sys.refMon.securityLevel.LOW);
    sys.refMon.createNewObject("hobj", sys.refMon.securityLevel.HIGH);

    //read file
    try {
       FileReader fileReader = new FileReader(fileName);
       BufferedReader bufferedReader = new BufferedReader(fileReader);

       while( (line = bufferedReader.readLine()) != null) {
          //System.out.println(line);
          String[] parts = line.split(" ");

          if ( parts.length == 3 || parts.length == 4){
            //parse instructions, get subject
            Subject subject = sys.getSubject(parts[1]);
            String objectName = parts[2];
            boolean objectExists = sys.refMon.objectManager.objectExists(objectName);
            String value = "";
            String action = parts[0];

            if ( subject != null && objectExists ) {
              //make instruction object and call reference monitor

              if (parts.length == 4 && action.equals("write")){ //check if numeric value
                value = parts[3];
                System.out.println(subject.name + " " + action + "s value " + value + " to " + objectName);
                InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
                sys.refMon.instruction(instrObj);
              }
              else {
                if (action.equals("read")) {
                  System.out.println(subject.name + " " + action + "s " + objectName);
                  InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
                  sys.refMon.instruction(instrObj);
                } else {
                  System.out.println("Bad Instruction");
                }
              }

            }
            else {
              System.out.println("Bad Instruction");
            }

          } else  {
            System.out.println("Bad Instruction");
          }

          sys.printState(sys);
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
