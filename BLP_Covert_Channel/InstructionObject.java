package security;

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
