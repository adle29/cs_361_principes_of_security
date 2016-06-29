import java.io.*;
import java.util.*;

class AES {
  String option = "";
  String keyFile = "";
  String inputFile = "";

  int Nb = 4;
  int Nr = 10;// 12, or 14;
  int Nk = 4; //, 6, or 8

  void SubBytes(){
    /*
    for each byte in the array, use its value as an index into a fixed 256-element lookup table, and replace its value in the state by the byte value stored at that location in the table. You can find the table and the inverse table on the web.
    */

  }

  void ShiftRows(){

  }

  void MixColumns(){

  }

  void AddRoundKey(byte[][] state, byte[] w, int start, int end ){
    // XOR the state with a 128-bit round key derived from the original key K by a recursive process.
    for(int i = start; i < end; i++){
      for(int j = 0; j < 4; j++)
        state[j][i] ^= w[j];
    }
  }

  void Cipher(byte[] in, byte out[], byte[] w){
    byte[][] state = new byte[4][Nb];
    state[0] = in;
    state[1] = in;
    state[2] = in;
    state[3] = in;

    AddRoundKey(state, w, 0, Nb - 1);

    //here is where you apply the rounds
    // for (int round = 1; round < Nr; round++){
    //   SubBytes(state);
    //   ShiftRows(state);
    //   MixColumns(state);
    //   AddRoundKey(state, w, round*Nb, (round+1)*Nb - 1); // Section 4
    // }
    //
    // SubBytes(state);
    // ShiftRows(state);
    // AddRoundKey(state, w, Nr*Nb, (Nr+1)*Nb - 1);
    //
    // out = state;
  }

  void encrypt() throws IOException {
    BufferedReader keyRaw = new BufferedReader(new FileReader("key"));
    BufferedReader plaintextRaw = new BufferedReader(new FileReader("plaintext"));
    PrintWriter ciphertext = new PrintWriter(new BufferedWriter(new FileWriter("plaintext.enc")));

    //InputFile: You'll read in a line, converting from Hex to binary for storage into your state array.
    String line = null;
    String key = keyRaw.readLine().trim();

    while ( (line = plaintextRaw.readLine()) != null ){
      line = line.trim();

      //from hex to binary
      //The input data block is broken into a 4x4 byte array (128-bit key)
      byte[] in = new byte[16];
      byte[] out = new byte[16];

      /*The expanded key. The initial key is of size 4*Nk bytes (see table earlier), and this
        is expanded to the array w of 4*Nb*(Nr+1) = 16*(Nr+1) bytes for input to the encryption
        algorithm*/
      byte[] w = new byte[16*(Nr+1)];
      int j = 0;

      //EXPANDING THE KEY
      for(int i = 0; i < w.length; i++){
        if (j == key.length())
          j = 0;

        w[i] = (byte)Character.getNumericValue(key.charAt(j));
        j++;
      }

      //FROM HEX TO INT, BYTES
      for (int i = 0; i < line.length(); i++){
        in[i] = (byte)Character.getNumericValue(line.charAt(i));
      }

      //Apply the AES algorithm to encrypt the string as stored
      Cipher(in, out, w);

      //write out the ciphertext in Hex notation to the output file

    }
  }

  void decrypt(){

  }

  public static void main(String args[]) throws IOException {
    //java AES option keyFile inputFile
    AES sys = new AES();
    if (args.length > 2) {
      sys.option = args[0];
      sys.keyFile = args[1];
      sys.inputFile = args[2];
    }
    //Nb, Nr, Nk
    sys.encrypt();

  }

}
