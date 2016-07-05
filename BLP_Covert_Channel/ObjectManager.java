package security;

import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;
import java.util.ListIterator;

//Objects are managed by the ObjectManager class.
class ObjectManager {
  ArrayList<Obj> objects = new ArrayList<Obj>();

  private Obj getObject (String name) {
    Iterator<Obj> itr = this.objects.iterator();
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

  void destroy(String name){
    this.objects.remove(this.getObject(name)); 
  }

}
