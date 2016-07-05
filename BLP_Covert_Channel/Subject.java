package security;

import java.io.*;

public class Subject{
  String TEMP = "0";
  int level;
  String characters = "";
  int numBytes = 0;
  String name;

  public Subject(String name, int level){
    this.name = name;
    this.level = level;
  }

  void run(){
  //  System.out.println("INSIDE " + this.TEMP);

    if (numBytes != 8) {
      if ( this.TEMP.equals("1") )
        characters+= "1";
      if ( this.TEMP.equals("0") )
        characters+= "0";
      this.numBytes++;
    }

    if (numBytes == 8){
      String character = ((char)Integer.parseInt(characters, 2))+"";

      try {
        File file = new File("message.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
          file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(character);
        bw.close();

      } catch (IOException e) {
        e.printStackTrace();
      }


      characters = "";
      this.numBytes = 0;

    }

    //convert to ascii if 8 bits and print

    //open file

  }
}
