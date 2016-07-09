import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

class PasswordCrack {

  String fileName = "wordListAugmented";
  String dictionaryFile = "";
  HashSet<String> words = new HashSet<String>();
  int totalTime = 0;
  int passwordsCracked = 0;

  void copyFile(String dictionary) throws IOException  {
    InputStream in = new FileInputStream(new File(dictionary));
    OutputStream out = new FileOutputStream(new File(this.fileName));
    byte[] buf = new byte[1024];
    int len;

    while ((len = in.read(buf)) > 0) {
       out.write(buf, 0, len);
    }

    in.close();
    out.close();

  }

  void appendToFile(String filename, String data) throws IOException  {
    RandomAccessFile file = new RandomAccessFile(filename, "rws");
    byte[] text = new byte[(int) file.length()];
    file.readFully(text);
    file.seek(0);
    file.writeBytes(data);
    file.write(text);
    file.close();
  }

  void emptyFile(String fileName) throws IOException  {
    PrintWriter pw = new PrintWriter(fileName);
    pw.close();
  }

  String reverse(String input){
    char[] in = input.toCharArray();
    int begin=0;
    int end=in.length-1;
    char temp;
    while(end>begin){
        temp = in[begin];
        in[begin]=in[end];
        in[end] = temp;
        end--;
        begin++;
    }
    return new String(in);
  }

  String capitalize(String line){ return line.substring(0, 1).toUpperCase() + line.substring(1); }
  String ncapitalize(String line){ return line.substring(0, 1).toLowerCase() + line.substring(1).toUpperCase(); }
  String mirror1(String line){ return line + reverse(line); }
  String mirror2(String line){ return reverse(line) + line; }
  String duplicate(String line){ return line + line; }
  String substituteE(String line){ return line.replace("e","3"); }
  String insideCapitalize(String line){ 
    return line.substring(0, 1).toLowerCase() + line.substring(1, line.length()-1).toUpperCase() + line.substring(line.length()-1, line.length()).toLowerCase();  }

  void crack(String username, String hash, String firstName, String lastName, String salt) throws IOException  {
    boolean passwordFound = false;
    String passwd = "---";

    // Seed the word list with words that the user might have utilized in constructing his or her password (e.g., his first and last name);
    String extra = username + "\n" + firstName + "\n" + lastName+"\n"; 
    this.copyFile(this.dictionaryFile);
    this.appendToFile(this.fileName, extra);

    // With the salt and augmented wordlist, systematically encrypt words and compare against the stored encrypted password;
    BufferedReader dictionary = new BufferedReader(new FileReader(this.fileName));
    String line = null; 
    String newHash = "";

    //Optimizations
    //use hashset to store the encryptions, encryptions
    // int maxLength = (password.length() < 8) ? password.length() : 8;
    // password = password.substring(0, maxLength);

    //timer
    long startTime = System.currentTimeMillis();

    while ( (line = dictionary.readLine() ) != null){
        //NORMAL
        String password = line.trim();
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //reverse the string, e.g., gnirts;
        password = reverse(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ) {
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //reverse the capitalized string, e.g., gnirts;
        password = reverse(capitalize(line));
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ) {
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //reverse the string and lowercase, e.g., gnirts;
        password = reverse(line).toLowerCase();
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ) {
            passwordFound = true; 
            passwd = password;
            break; 
        }

        // mirror the string 1, e.g., stringgnirts;
        password = mirror1(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ) {
            passwordFound = true; 
            passwd = password;
            break; 
        }

        // mirror the string 2, e.g., stringgnirts;
        password = mirror2(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ) {
            passwordFound = true; 
            passwd = password;
            break; 
        }

        // duplicate the string, e.g., stringstring;
        password = duplicate(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ) {
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //capitalize the string, e.g., String;
        password = capitalize(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //uppercase the string, e.g., STRING;
        password = line.toUpperCase();
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //lowercase the string, e.g., string;
        password = line.toLowerCase();
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //ncapitalize the string, e.g., sTRING;
        password = ncapitalize(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //delete the first character from the string, e.g., tring;
        password = line.substring(1); 
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //reverse and delete the first character from the string, e.g., gnirt;
        password = reverse(capitalize(line)).substring(1); 
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //delete the last character from the string, e.g., strin;
        password = line.substring(0,line.length()-1); 
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //prepend a character to the string, e.g., @string;
        password = line.substring(0,line.length()-1); 
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //substitute e for 3
        password = substituteE(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //toggle case of the string, e.g., STRING;
        password = insideCapitalize(line);
        newHash =  jcrypt.crypt(salt, password);
        if ( newHash.equals(hash) ){
            passwordFound = true; 
            passwd = password;
            break; 
        }

        //append a character to the string, e.g., string9;
        char[] list = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        for(int i = 0; i < list.length; i++){
            password = line+list[i];
            newHash =  jcrypt.crypt(salt, password);
            if ( newHash.equals(hash) ){
                passwordFound = true; 
                passwd = password;
                break; 
            }

            password = list[i]+line;
            newHash =  jcrypt.crypt(salt, password);
            if ( newHash.equals(hash) ){
                passwordFound = true; 
                passwd = password;
                break; 
            }

            password = ncapitalize(line) + list[i];
            newHash =  jcrypt.crypt(salt, password);
            if ( newHash.equals(hash) ){
                passwordFound = true; 
                passwd = password;
                break; 
            }
        }

        if (passwordFound)
            break;


    }

    if(passwordFound)
        passwordsCracked++; 

    //print results
    long endTime = System.currentTimeMillis();
    long completionTime = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
    this.totalTime += completionTime;
    String result = String.format( "Username: %-10s hash: %-14s salt: %-5s Name: %-10s Last Name: %-10s Password: %-15s Time: %-10s", username, hash, salt, firstName, lastName, passwd, completionTime+" s" );

    System.out.println(result);

    this.emptyFile(fileName);

  }

  void getHashes(String dictionaryFile, String passwordFile) throws IOException  {
    BufferedReader dictionary = new BufferedReader(new FileReader(dictionaryFile));
    BufferedReader passwords = new BufferedReader(new FileReader(passwordFile));
    String line = null;

    while ( (line = passwords.readLine() ) != null){
      // Extract the encrypted password and salt for that user:
      String[] rawPasswordList = line.split(":");
      String username = rawPasswordList[0];
      String hash = rawPasswordList[1];
      String firstName = rawPasswordList[4].split(" ")[0];
      String lastName = rawPasswordList[4].split(" ")[1];
      String salt = hash.substring(0,2); 
      this.crack(username, hash, firstName,lastName, salt);
    }

    System.out.println("TOTALTIME: "+this.totalTime);
    System.out.println("PASSWORDS CRACKED: "+this.passwordsCracked);
  }

  public static void main(String[] args) throws IOException  {
    PasswordCrack sys = new PasswordCrack();
    sys.dictionaryFile = args[0];
    String passwords = args[1];
    sys.getHashes(sys.dictionaryFile, passwords);
  }

}
