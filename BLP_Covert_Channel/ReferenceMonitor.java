package security;

class ReferenceMonitor {

  ObjectManager objectManager = new ObjectManager();
  SecurityLevel securityLevel = new SecurityLevel();

  void createNewObject(String name, int level){
    Obj newObject = new Obj(name, level);
    this.objectManager.objects.add(newObject);
  }

  void executeRead (Subject subject, String objectName) {
    subject.TEMP = objectManager.read(objectName);
  }

  void executeWrite(Subject subject, String objectName, String value) {
    this.objectManager.write(objectName, value);
  }

  void executeCreate(String objectName, int level){
    this.createNewObject(objectName, level);
  }

  void executeDestroy(String objectName){
    this.objectManager.destroy(objectName);
  }

  void instruction(InstructionObject instr){
    //process instruction
    Subject subject = instr.subject;
    String action = instr.action;

    if (action.equals("read") ){
      int objectLevel = this.objectManager.getObjectLevel(instr.objectName);
      if ( this.securityLevel.checkClearance( action, subject.level, objectLevel) )
        this.executeRead(subject, instr.objectName);
      else
        subject.TEMP = "0";
    }

    if (action.equals("write") ){
      int objectLevel = this.objectManager.getObjectLevel(instr.objectName);
      if ( this.securityLevel.checkClearance( action, subject.level, objectLevel) )
        this.executeWrite(subject, instr.objectName, instr.value);
    }

    if (action.equals("create") ){
      boolean objectExists = this.objectManager.objectExists(instr.objectName);
      if ( !objectExists )
        this.createNewObject(instr.objectName, subject.level);
    }

    if (action.equals("destroy") ){
      int objectLevel = this.objectManager.getObjectLevel(instr.objectName);
      if ( this.securityLevel.checkClearance( action, subject.level, objectLevel) )
        this.executeDestroy(instr.objectName);
    }

  } //end instruction method

}
