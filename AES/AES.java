import java.io.*;
import java.util.*;

class AES {
  String option = "";
  String keyFile = "";
  String inputFile = "";

  int Nb = 4; //size
  int Nr = 10;// 12, or 14; rounds
  int Nk = 4; //, 6, or 8; key

  private static final char sbox[] = { 0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe,
			0xd7, 0xab, 0x76, 0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72,
			0xc0, 0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15, 0x04,
			0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75, 0x09, 0x83, 0x2c,
			0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84, 0x53, 0xd1, 0x00, 0xed, 0x20,
			0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf, 0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33,
			0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8, 0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc,
			0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2, 0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e,
			0x3d, 0x64, 0x5d, 0x19, 0x73, 0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde,
			0x5e, 0x0b, 0xdb, 0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4,
			0x79, 0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08, 0xba,
			0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a, 0x70, 0x3e, 0xb5,
			0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e, 0xe1, 0xf8, 0x98, 0x11, 0x69,
			0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf, 0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42,
			0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16 };

	// Precomputed inverted Rijndael S-BOX
	private static final char rsbox[] = { 0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81,
			0xf3, 0xd7, 0xfb, 0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9,
			0xcb, 0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e, 0x08,
			0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25, 0x72, 0xf8, 0xf6,
			0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92, 0x6c, 0x70, 0x48, 0x50, 0xfd,
			0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84, 0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3,
			0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06, 0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1,
			0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b, 0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf,
			0xce, 0xf0, 0xb4, 0xe6, 0x73, 0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c,
			0x75, 0xdf, 0x6e, 0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe,
			0x1b, 0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4, 0x1f,
			0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f, 0x60, 0x51, 0x7f,
			0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef, 0xa0, 0xe0, 0x3b, 0x4d, 0xae,
			0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61, 0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6,
			0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d };

  void SubBytes(byte[][] state){
    /*
    for each byte in the array, use its value as an index into a fixed 256-element
    lookup table, and replace its value in the state by the byte value stored at that
    location in the table. You can find the table and the inverse table on the web.
    */
    //to hex to string, split
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        int temp = state[i][j];
        String hex = Integer.toHexString(temp);
        String[] values = hex.split("");
        int x =  Integer.parseInt(values[0], 16);
        int y = Integer.parseInt(values[1], 16);
        char r = sbox[x+y];
        state[i][j] = (byte)Character.getNumericValue(r);
      }
    }

  }

  void ShiftRows(byte[][] state){
    rotateMatrixCells(state, 1, 1);
    rotateMatrixCells(state, 2, 2);
    rotateMatrixCells(state, 3, 3);
  }

  void rotateMatrixCells(byte[][] state, int rowIndex, int turns){
    byte[] row = state[rowIndex];
    for(int i = 0; i < turns; i++){
      state[0][rowIndex] = row[1];
      state[1][rowIndex] = row[2];
      state[2][rowIndex] = row[3];
      state[3][rowIndex] = row[0];
    }
  }

  void MixColumns(byte[][] test){
  	for (int j = 0; j < 4; j++) {
  		byte i[] = { test[0][j], test[1][j], test[2][j], test[3][j] };
  		test[0][j] = (byte) (mult(2, i[0]) ^ mult(3, i[1]) ^ mult(1, i[2]) ^ mult(1, i[3]));
  		test[1][j] = (byte) (mult(1, i[0]) ^ mult(2, i[1]) ^ mult(3, i[2]) ^ mult(1, i[3]));
  		test[2][j] = (byte) (mult(1, i[0]) ^ mult(1, i[1]) ^ mult(2, i[2]) ^ mult(3, i[3]));
  		test[3][j] = (byte) (mult(3, i[0]) ^ mult(1, i[1]) ^ mult(1, i[2]) ^ mult(2, i[3]));
    }
  }

  byte mult(int x, byte b) {
		byte y = b;
		if (x == 1) {
			return y;
		}
		byte original = y;
		byte cond = (byte) ((y < 1) ? 27 : 0);
		y <<= 1;
		y = (byte) (y ^ cond);
		return (byte) ((x == 2) ? y : (y ^ original));
	}

  void AddRoundKey(byte[][] state, byte[] w, int start, int end ){
    // XOR the state with a 128-bit round key derived from the original key K by a recursive process.
    int count = 0;
    for(int i = start; i < end; i++){
      for(int j = 0; j < 3; j++){
        state[i][j] ^= w[count];
        count++;
      }
    }
  }

  void Cipher(byte[] in, byte out[], byte[] w){
    byte[][] state = new byte[4][Nb];
    int count = 0;

    //fill state
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        state[i][j] = in[count];
        count++;
      }
    }

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

    //out = state;
  }

  byte[] Rcon(int i){
    byte[] newArray = {(byte)Math.pow(2,i-1),0,0,0};
    return newArray;
  }

  byte[] RotWord(byte[] x){
    byte[] row = x;
    x[0] = row[1];
    x[1] = row[2];
    x[2] = row[3];
    x[3] = row[0];

    return x;
  }

  byte[] SubWord(byte[] w){
    for (int i = 0; i < 3; i++){
      int temp = w[i];
      String hex = String.format("%02X", temp);//Integer.toHexString(temp);
      String[] values = hex.split("");
      int x =  Integer.parseInt(values[0], 16);
      int y = Integer.parseInt(values[1], 16);
      char r = sbox[x+y];
      w[i] = (byte)Character.getNumericValue(r);
    }

    return w;
  }

  void KeyExpansion(byte[][] K, byte[][] W) {
    //byte K[4][Nk], byte W[4][Nb(Nr+1)]
    // for(j = 0; j < Nk; j++)
    //   for(i = 0; i < 4; i++)
    //     W[i][j] = K[i][j];
    //
    // for(j = Nk; < Nb(Nr + 1); j++)
    //   if (j%Nk == 0) {
    //       W[0][j] = W[0][j — Nk] ^ SubWord(W[0][j — Nk]) ^ rcon[j/Nk];
    //       for(i = 1; i < 4; i++)
    //         W[i][j] =  W[i][j - Nk] ^ SubWord(W[0][i + 1%j][j - 1]);
    //   }
    //   // else if (j % Nk == 4) {
    //   //   for = o; i < 4.; = Nk] (c) stwtillj :01 ;
    //   // else
    //   //   for(i = 0; i < 4; i++) WHIM 7= — N ;
    // }
  }


  // byte[] keyExpansion(byte[] key) {
  //   int Nr = Nk + 6;
  //   byte[] w = new byte[4*Nb*(Nr+1)];
  //   int i = 0;
  //
  //   while ( i < Nk) {
  //       w[4*i] = key[4*i];
  //       w[4*i+1] = key[4*i+1];
  //       w[4*i+2] = key[4*i+2];
  //       w[4*i+3] = key[4*i+3];
  //     i++;
  //   }
  //
  //   i = Nk;
  //
  //   while(i < Nb*(Nr+1)) {
  //     byte[] temp = {w[4*i], w[4*i+1], w[4*i+2], w[4*i+3] };
  //
  //     if (i % Nk == 0) {
  //       byte[] temp2 = SubWord(RotWord(temp));
  //       byte[] rcon = Rcon(i/Nk);
  //       for (int j = 0; j < 3; j++){
  //         temp[j] = (byte)(temp2[j] ^ rcon[j]);
  //       }
  //     }
  //
  //     else if (Nk > 6 && (i%Nk) == 4)
  //       temp = SubWord(temp);
  //
  //     for (int j = 0; j < 3; j++){
  //       w[4*i+j] = (byte)(w[4*i-Nk+j] ^ temp[j]);
  //     }
  //
  //     i++;
  //   }
  //   return w;
  //
  // }


  void encrypt() throws IOException {
    BufferedReader keyRaw = new BufferedReader(new FileReader("key"));
    BufferedReader plaintextRaw = new BufferedReader(new FileReader("plaintext"));
    PrintWriter ciphertext = new PrintWriter(new BufferedWriter(new FileWriter("plaintext.enc")));
    System.out.println("Encryption with Key Size: "+Nk + " Rounds: " + Nr);

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
      byte[] w = new byte[4*Nk];
      int j = 0;

      //EXPANDING THE KEY
      for(int i = 0; i < 32; i+=2){
        w[i/2] = (byte)Integer.parseInt( key.substring(i,i+2),16 );
      }

      System.out.println("Key:");
      System.out.println(Arrays.toString(w));

      System.out.println("Expanded Key:");
      // w = keyExpansion(w);
      // for (j = 0; j < w.length; j++) {
      //   if ( j != 0 && j%4 ==0)
      //     System.out.println("" );
      //   System.out.print( String.format("%02X",w[j]) + " " );
      // }
      //System.out.println(Arrays.toString(w));
    //  System.exit(1);

      //FROM HEX TO INT, BYTES
      for (int i = 0; i < line.length(); i++){
        in[i] = (byte)Character.getNumericValue(line.charAt(i));
      }

      //Apply the AES algorithm to encrypt the string as stored
      Cipher(in, out, w);

      //write out the ciphertext in Hex notation to the output file

    }
  }

  void Decipher(byte[] in, byte out[], byte[] w) {
    // AddRoundKey(state, w[Nr*Nb, (Nr+1)*Nb-1])

    // for round = Nr-1 step -1 downto 1
      // InvShiftRows(state)
      // InvSubBytes(state)
      // AddRoundKey(state, w[round*Nb, (round+1)*Nb-1])
      // InvMixColumns(state)
    // end for

    // InvShiftRows(state)
    // InvSubBytes(state)
    // AddRoundKey(state, w[0, Nb-1])
    // out = state
  }

  void decrypt() throws IOException {
    BufferedReader keyRaw = new BufferedReader(new FileReader("key"));
    BufferedReader ciphertextRaw = new BufferedReader(new FileReader("ciphertext"));
    PrintWriter ciphertext = new PrintWriter(new BufferedWriter(new FileWriter("plaintext.enc.dec")));

    byte[] in = new byte[4*Nb];
    byte[] out = new byte[4*Nb];
    byte[] w = new byte[Nb*(Nr+1)];
    // byte state[4,Nb]
    // state = in
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
