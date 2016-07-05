package security;

class SecurityLevel {
   final int LOW = 0;
   final int HIGH = 100;

   boolean checkClearance(String action, int subjectLevel, int objectLevel){
     if ( action.equals("read")){
        if ( subjectLevel >= objectLevel )
          return true;
        return false;
     }

     if ( action.equals("write") || action.equals("destroy") ){
        if ( subjectLevel <= objectLevel )
          return true;
        return false;
     }

     return false;
   }
}
