import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.Date;
import java.util.ListIterator;

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

  void executeWrite(Subject subject, String objectName, String value){
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

  boolean objectStatus(String name){
    return this.getObject(name).lock;
  }

  void changeObjectStatus(String name, boolean status){
    this.getObject(name).lock = status;
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

class Obj{
  String value = "0";
  int level;
  String name;
  boolean lock = false;

  public Obj(String name, int level){
    this.name = name;
    this.level = level;
  }
}

class Subject implements Runnable {
   private Thread t;
   String name;
   String TEMP = "0";
   int level;
   ReferenceMonitor referenceMonitor;
   ThreadSecureSystem sys;
   boolean operationRunning = false;
   String fileName;

   public Subject(String name, int level, ReferenceMonitor referenceMonitor, ThreadSecureSystem sys, String fileName){
       this.name = name;
       this.level = level;
       this.referenceMonitor = referenceMonitor;
       this.sys = sys;
       this.fileName = fileName;
   }

   public void run() {
      // operation = processcing all instructions
      this.operationRunning = true;
      String filename = this.fileName;
      try {
        try {
           String line = null;
           FileReader fileReader = new FileReader(fileName);
           BufferedReader bufferedReader = new BufferedReader(fileReader);

           while( (line = bufferedReader.readLine()) != null) {
              //System.out.println(line);
              String[] parts = line.split(" ");
              String action = parts[0];
              boolean badInstruction = false;

              if (action.equals("sleep")){
                String subjectName = parts[1];
                System.out.println("[+] "+this.name + " sleeping, ceded command to " + subjectName+"\n");
                Subject otherSubject = this.sys.getSubject(subjectName);

                while (otherSubject.operationRunning){
                  Thread.sleep(50);

                }

                System.out.println("[+] "+this.name + " waking up \n");
                continue;
              }

              if ( parts.length == 2 || parts.length == 3){
                //parse instructions, get subject
                Subject subject = this;
                String objectName = parts[1];

                boolean objectExists = sys.referenceMonitor.objectManager.objectExists(objectName);
                String value = "";



                if ( subject != null &&  objectExists ) {
                  boolean objectStatus = sys.referenceMonitor.objectManager.objectStatus(objectName);

                  if (parts.length == 3 && action.equals("write") ){
                    value = parts[2];
                    Date date = new Date();
                    System.out.println(subject.name + " " + action + "s value " + value + " to " +
                                       objectName + "(locked: " + objectStatus + ")"+
                                       " at " +date.toString());

                    while(objectStatus){
                      System.out.println(this.name+" is waiting, object " + objectName + " is locked");
                      Thread.sleep(50);
                      objectStatus = sys.referenceMonitor.objectManager.objectStatus(objectName);
                    }

                    sys.referenceMonitor.objectManager.changeObjectStatus(objectName, true);
                    InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
                    this.referenceMonitor.instruction(instrObj);
                    sys.referenceMonitor.objectManager.changeObjectStatus(objectName, false);

                  }
                  else {
                    if (action.equals("read")) {
                      Date date = new Date();
                      System.out.println(subject.name + " " + action + "s " +
                                         objectName + "(locked: " + objectStatus + ")"+
                                         " at " +date.toString());

                      while(objectStatus){
                        System.out.println(this.name+" is waiting, object " + objectName + " is locked");
                        Thread.sleep(50);
                        objectStatus = sys.referenceMonitor.objectManager.objectStatus(objectName);
                      }

                      sys.referenceMonitor.objectManager.changeObjectStatus(objectName, true);
                      InstructionObject instrObj = new InstructionObject(subject, action, objectName, value);
                      this.referenceMonitor.instruction(instrObj);
                      sys.referenceMonitor.objectManager.changeObjectStatus(objectName, false);

                    }
                    else {
                      badInstruction = true;
                    }
                  } //ELSE

                }
                else {
                  badInstruction = true;
                }

              } else  {
                badInstruction = true;
              }

              /* PRINTING STATUS */
              while(this.sys.printLocked){
                //System.out.println("[-]Printer is locked: waiting to print...");
                Thread.sleep(50);
              }

              this.sys.printLocked = true;
              if (badInstruction)
                System.out.println("Bad Instruction");
              this.sys.printState(sys);

              this.sys.printLocked = false;


           }
           /* FINISHED READING LINE */
           this.operationRunning = false;
           bufferedReader.close();
         }

         catch(FileNotFoundException ex) {
           System.out.println("Unable to open file '" + fileName + "'");
         }
         catch(IOException ex) {
           System.out.println( "Error reading file '"+ fileName + "'");
         }

     } catch (InterruptedException e) {
         System.out.println("Thread " +  name + " interrupted.");
     }
   }

   public void start (){
      if (t == null) {
         t = new Thread (this, name);
         t.start ();
         //starts run() function directly
      }
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

public class ThreadSecureSystem {

  ArrayList<Subject> subjects = new ArrayList<Subject>();
  ReferenceMonitor referenceMonitor = new ReferenceMonitor();
  boolean printLocked = false;

  public void createSubject(String name, int label, ThreadSecureSystem sys, String fileName ){
    Subject newSubject =  new Subject( name, label, referenceMonitor, sys, fileName);
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

  void printState(ThreadSecureSystem sys){
    printLocked = true;
    System.out.println("The current system is: ");

    Iterator<Obj> itrObjects = sys.referenceMonitor.objectManager.objects.iterator();
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
    printLocked = false;
  }

  public static void main(String args[]) {

      ThreadSecureSystem sys = new ThreadSecureSystem();

      int low = sys.referenceMonitor.securityLevel.LOW;
      int high = sys.referenceMonitor.securityLevel.HIGH;

      sys.createSubject("lyle", low, sys, "instructionList2");
      sys.createSubject("hal", high, sys, "instructionList3");

      sys.referenceMonitor.createNewObject("lobj", low);
      sys.referenceMonitor.createNewObject("hobj", high);

      Iterator<Subject> itr = sys.subjects.iterator();
      while (itr.hasNext()) {
        Subject element = itr.next();
                element.start();
      }

  }
}
