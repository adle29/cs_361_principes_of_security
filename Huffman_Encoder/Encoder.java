
import java.io.*;
import java.util.*;
import java.util.Random;

class Utils {
  int count = 0;

  double log(double x, double base){
      return Math.log(x) / Math.log(base);
  }

  void create_volume_of_text(int k, int alphabet[], int total) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter("testText"));
    int counter = 0;
    int dartboard [] = new int[total];
    Random randomGenerator = new Random();
    String text = "";

    for(int i = 0; i < alphabet.length; i++){
      int num = alphabet[i];
      for(int j = counter; j < counter+num; j++){
        dartboard[j] = i;
      }
      counter += num;
    }

    for(int i = 0; i < k; i++){
      int randomInt = randomGenerator.nextInt(total);
      int letterNumber = dartboard[randomInt];
      text += Character.toString((char)(97+letterNumber));
    }

    out.print(text);
    out.close();

  }

  void encode(HashMap<String, String> code, String inputFile, String outputFile, int set) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader(inputFile));
    PrintWriter out = new PrintWriter(new FileWriter(outputFile));

    String stringToEncode = f.readLine();
    String encodedString = "";

    for(int i =0; i < stringToEncode.length(); i+=set){
      String s = stringToEncode.substring(i,i+set);
      out.println( code.get(s) );
    }

    out.close();

  }

  void decode(HashMap<String,String> code, String inputFile, String outputFile) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader(inputFile));
    PrintWriter out = new PrintWriter(new FileWriter(outputFile));
    double averageBitsPerSymbol = 0;

    String line = null;
    while ( (line = f.readLine()) != null ){
        line = line.trim();
        if ( code.get(line) != null) {
          String c = code.get(line);
          out.println(c);
        }
    }
    out.close();
  }


}//end class util


class Encoder {

  Utils utils = new Utils();
  String fileName = "";
  int k = 0;

  public void read_file_content() throws IOException{
    BufferedReader f = new BufferedReader(new FileReader(this.fileName));
    PrintWriter out = new PrintWriter(new FileWriter("log.out"));
    HashMap<String, String> code = new HashMap<String, String>();
    HashMap<String, String> code2 = new HashMap<String, String>();
    ArrayList<String> alphabetSymbols = new ArrayList<String>();
    String line = null;
    int count = 0;
    int i = 0;
    int alphabet[] =  new int[26];
    double h = 0;

    /* Read lines of file */
    while ( (line = f.readLine() ) != null ){
        int num = Integer.parseInt(line);
        alphabet[i] = num;
        String c = Character.toString( (char)(97 +num) );
        alphabetSymbols.add(c);
        count += num;
        i++;
    }

    /* 1. Calculate entropy, remove unncessary alphabet letters. */
    int newAlphabet[] = new int[i];
    for (i = 0; i < newAlphabet.length; i++){
      double num = alphabet[i];
      h += (num/count) * utils.log(num/count,2);
      newAlphabet[i] = alphabet[i];
    }

    h = -h;

    /* 2. call frequency table. https://github.com/nayuki/Reference-Huffman-coding */
    System.out.println("---------------------------------------");
    System.out.println("Encoding Table:");
    // //FrequencyTable frequencyTable = new FrequencyTable(newAlphabet);
    HuffmanCode table = new HuffmanCode(newAlphabet);
    ArrayList<String> frequencyTable = table.alphabet;
    //System.out.println(frequencyTable);
    double averageBitsPerSymbol = 0;
    //
    System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
    int countSymbols = 0;

    for (i = 0; i < newAlphabet.length; i++){
      String symbol = frequencyTable.get(i);
      int asciiValue = 97 + i;
      String s = Character.toString((char)(asciiValue));
      System.out.println( s+ "\t"+ newAlphabet[i] + "/" +count+"\t"+ symbol );
      code.put( s, symbol );
      code2.put( symbol, s );
      averageBitsPerSymbol += symbol.length();
      countSymbols++;
    }
    averageBitsPerSymbol /= countSymbols;
    //utils.process(alphabet, alphabetSymbols, count, i, k);

    /* 3. volume of text, 10000 characters, file {testText} */
    utils.create_volume_of_text(this.k, newAlphabet, count);
      // /* encoder, file {testText.enc1} */
      utils.encode(code, "testText","testText.enc1", 1);
      // /* decoder, file {file testText.dec1} */
      utils.decode(code2, "testText.enc1","testText.dec1");

    /* 4. Measure efficiency
      Measure the efficiency of your encoding by computing the actual average
      bits per symbol as you do the translation. Compare this with the computed
      entropy of the language (from step 1) and record the percentage difference.
    */
    double percentDifference = Math.round(100*Math.abs(h - averageBitsPerSymbol) / (h + averageBitsPerSymbol));
    System.out.println("---------------------------------------");
    System.out.println("Entropy: " + h);
    System.out.println("Average bits per symbol: " + averageBitsPerSymbol);
    System.out.println("Percent difference: " + percentDifference + "%");
    /* 5. Then, consider the "2-symbol derived alphabet," where you use sequences
    of 2 symbols, considering each pair of symbols to be a single symbol in the
    derived alphabet (there's more on this below). Use Huffman to devise an
    efficient encoding for that alphabet. Print this encoding to the screen
    in a nice format. Note that you still consider each symbol to be independent
    of context. I.e., you don't have to build a second-order model.
    This is just to see if you gain any coding efficiency coding
    two symbols at a time instead of one at a time.
    */
    BufferedReader f2 = new BufferedReader(new FileReader("testText"));
    HashMap<String,Integer> derivedAlphabet = new HashMap<String,Integer>();
    String text = f2.readLine();
    count = 0;

    for (i = 0; i < text.length(); i+=2){
      line = text.substring(i, i+2);
      if ( derivedAlphabet.get(line) != null) {
        int c = derivedAlphabet.get(line);
        derivedAlphabet.put(line, ++c);
      }else {
        derivedAlphabet.put(line, 1);
      }
      count += 1;
    }

    Set<String> keySet = derivedAlphabet.keySet();
    int newDerivedAlphabet[] = new int[keySet.size()];
    String symbolsDerivedAlphabet[] = new String[keySet.size()];
    i = 0; h = 0; averageBitsPerSymbol = 0;

    countSymbols = 0;
    averageBitsPerSymbol = 0;
    code = new HashMap<String, String>();
    code2 = new HashMap<String, String>();

    for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
      String key = it.next();

      //make small the alphabet
      newDerivedAlphabet[i] = derivedAlphabet.get(key);
      symbolsDerivedAlphabet[i] = key;

      //average bit per symbol
      averageBitsPerSymbol += key.length();

      //entropy
      double num = derivedAlphabet.get(key);
      h += (num/count) * utils.log(num/count,2);

      countSymbols++;
      i++;
    }

    h = -h;

    averageBitsPerSymbol /= countSymbols;

    // /* 6. Re-run your text from step 3 above with this new encoding and
    //   corresponding decoding. You should generate files testText.enc2 and
    //   testText.dec2. Compute the actual efficiency and compare with
    //   the entropy and with the efficiency using your 1-symbol encoding.
    // */
    System.out.println("---------------------------------------");
    System.out.println("2-symbol derived alphabet: ");

    table = new HuffmanCode(newDerivedAlphabet);
    frequencyTable = table.alphabet;

    System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");

    for (i = 0; i < newDerivedAlphabet.length; i++){
      String symbol = frequencyTable.get(i);
      System.out.println( symbolsDerivedAlphabet[i]+ "\t"+ newDerivedAlphabet[i] + "/" +count+"\t"+ symbol );
      code.put( symbolsDerivedAlphabet[i], symbol );
      code2.put( symbol, symbolsDerivedAlphabet[i] );
    }

    // /* encoder, file {testText.enc1} */
    utils.encode(code, "testText","testText.enc2", 2);
    // /* decoder, file {file testText.dec1} */
    utils.decode(code2, "testText.enc2","testText.dec2");


    percentDifference = Math.round(100*Math.abs(h - averageBitsPerSymbol) / (h + averageBitsPerSymbol));
    System.out.println("---------------------------------------");
    System.out.println("Entropy: " + h);
    System.out.println("Average bits per symbol: " + averageBitsPerSymbol);
    System.out.println("Percent difference: " + percentDifference + "%");
    System.out.println("---------------------------------------");
    /* closed writing file */
    out.close();
  }

  public Encoder(String fileName, int k){
    this.fileName = fileName;
    this.k = k;
  }

  public static void main(String args[]) throws IOException{
    if ( args.length == 2 ) {
      System.out.println("---------------------------------------");
      System.out.println("Using command line arguments.");
      Encoder sys = new Encoder(args[0], Integer.parseInt(args[1]));
      System.out.println("Filename: "+sys.fileName + ", K = " + sys.k);
      sys.read_file_content();
    }
    else {
      System.out.println("---------------------------------------");
      Encoder sys = new Encoder("frequenciesFile", 100);
      System.out.println("Filename: "+sys.fileName + ", K = " + sys.k);
      sys.read_file_content();
    }
  }

}
