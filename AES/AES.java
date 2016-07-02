import java.io.*;
import java.util.*;

class AES {
    String option = "";
    String keyFile = "";
    String inputFile = "";
    String length = "";
    String mode = "";

    int Nb = 4; // size
    int Nr = 10;// 12, or 14; rounds
    int Nk = 4; // , 6, or 8; key

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

    char rcon[] = { 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a,
            0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4,
            0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d,
            0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc,
            0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61,
            0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04,
            0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97,
            0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25,
            0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20,
            0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4,
            0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33,
            0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b,
            0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa,
            0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83,
            0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d };

    final static int[] LogTable = { 0, 0, 25, 1, 50, 2, 26, 198, 75, 199, 27, 104, 51, 238, 223, 3, 100, 4, 224, 14, 52,
            141, 129, 239, 76, 113, 8, 200, 248, 105, 28, 193, 125, 194, 29, 181, 249, 185, 39, 106, 77, 228, 166, 114,
            154, 201, 9, 120, 101, 47, 138, 5, 33, 15, 225, 36, 18, 240, 130, 69, 53, 147, 218, 142, 150, 143, 219, 189,
            54, 208, 206, 148, 19, 92, 210, 241, 64, 70, 131, 56, 102, 221, 253, 48, 191, 6, 139, 98, 179, 37, 226, 152,
            34, 136, 145, 16, 126, 110, 72, 195, 163, 182, 30, 66, 58, 107, 40, 84, 250, 133, 61, 186, 43, 121, 10, 21,
            155, 159, 94, 202, 78, 212, 172, 229, 243, 115, 167, 87, 175, 88, 168, 80, 244, 234, 214, 116, 79, 174, 233,
            213, 231, 230, 173, 232, 44, 215, 117, 122, 235, 22, 11, 245, 89, 203, 95, 176, 156, 169, 81, 160, 127, 12,
            246, 111, 23, 196, 73, 236, 216, 67, 31, 45, 164, 118, 123, 183, 204, 187, 62, 90, 251, 96, 177, 134, 59,
            82, 161, 108, 170, 85, 41, 157, 151, 178, 135, 144, 97, 190, 220, 252, 188, 149, 207, 205, 55, 63, 91, 209,
            83, 57, 132, 60, 65, 162, 109, 71, 20, 42, 158, 93, 86, 242, 211, 171, 68, 17, 146, 217, 35, 32, 46, 137,
            180, 124, 184, 38, 119, 153, 227, 165, 103, 74, 237, 222, 197, 49, 254, 24, 13, 99, 140, 128, 192, 247, 112,
            7 };

    final static int[] AlogTable = { 1, 3, 5, 15, 17, 51, 85, 255, 26, 46, 114, 150, 161, 248, 19, 53, 95, 225, 56, 72,
            216, 115, 149, 164, 247, 2, 6, 10, 30, 34, 102, 170, 229, 52, 92, 228, 55, 89, 235, 38, 106, 190, 217, 112,
            144, 171, 230, 49, 83, 245, 4, 12, 20, 60, 68, 204, 79, 209, 104, 184, 211, 110, 178, 205, 76, 212, 103,
            169, 224, 59, 77, 215, 98, 166, 241, 8, 24, 40, 120, 136, 131, 158, 185, 208, 107, 189, 220, 127, 129, 152,
            179, 206, 73, 219, 118, 154, 181, 196, 87, 249, 16, 48, 80, 240, 11, 29, 39, 105, 187, 214, 97, 163, 254,
            25, 43, 125, 135, 146, 173, 236, 47, 113, 147, 174, 233, 32, 96, 160, 251, 22, 58, 78, 210, 109, 183, 194,
            93, 231, 50, 86, 250, 21, 63, 65, 195, 94, 226, 61, 71, 201, 64, 192, 91, 237, 44, 116, 156, 191, 218, 117,
            159, 186, 213, 100, 172, 239, 42, 126, 130, 157, 188, 223, 122, 142, 137, 128, 155, 182, 193, 88, 232, 35,
            101, 175, 234, 37, 111, 177, 200, 67, 197, 84, 252, 31, 33, 99, 165, 244, 7, 9, 27, 45, 119, 153, 176, 203,
            70, 202, 69, 207, 74, 222, 121, 139, 134, 145, 168, 227, 62, 66, 198, 81, 243, 14, 18, 54, 90, 238, 41, 123,
            141, 140, 143, 138, 133, 148, 167, 242, 13, 23, 57, 75, 221, 124, 132, 151, 162, 253, 28, 36, 108, 180, 199,
            82, 246, 1

    };

    void SubBytes(byte[][] state) {
        /*
         * for each byte in the array, use its value as an index into a fixed
         * 256-element lookup table, and replace its value in the state by the
         * byte value stored at that location in the table. You can find the
         * table and the inverse table on the web.
         */
        // to hex to string, split
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int temp = state[i][j];
                String hex = Integer.toHexString(temp);
                hex = (hex.length() > 2) ? hex.substring(hex.length() - 2) : "0".concat(hex);
                int x = Integer.parseInt(hex.substring(0, 1), 16);
                int y = Integer.parseInt(hex.substring(1), 16);
                // System.out.println(hex + " " + x + " " + y + " " + ((x * 16)
                // + y) + " " + r);
                state[i][j] = (byte) (sbox[(x * 16) + y]);
            }
        }
    }

    void InvSubBytes(byte[][] state) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int temp = state[i][j];
                String hex = Integer.toHexString(temp);
                hex = (hex.length() > 2) ? hex.substring(hex.length() - 2) : "0".concat(hex);
                int x = Integer.parseInt(hex.substring(0, 1), 16);
                int y = Integer.parseInt(hex.substring(1), 16);
                // System.out.println(hex + " " + x + " " + y + " " + ((x * 16)
                // + y) + " " + r);
                state[i][j] = (byte) (rsbox[(x * 16) + y]);
            }
        }
    }

    void ShiftRows(byte[][] state) {
        rotateMatrixCells(state, 1, 1);
        rotateMatrixCells(state, 2, 2);
        rotateMatrixCells(state, 3, 3);
    }

    void InvShiftRows(byte[][] state) {
        rotateMatrixCells(state, 1, 3);
        rotateMatrixCells(state, 2, 2);
        rotateMatrixCells(state, 3, 1);
    }

    void rotateMatrixCells(byte[][] state, int rowIndex, int turns) {
        byte[] row = state[rowIndex];
        byte temp;
        for (int i = 0; i < turns; i++) {
            temp = row[0];
            state[rowIndex][0] = row[1];
            state[rowIndex][1] = row[2];
            state[rowIndex][2] = row[3];
            state[rowIndex][3] = temp;
        }
    }

    byte mul(int a, byte b) {
        int inda = (a < 0) ? (a + 256) : a;
        int indb = (b < 0) ? (b + 256) : b;

        if ((a != 0) && (b != 0)) {
            int index = (LogTable[inda] + LogTable[indb]);
            byte val = (byte) (AlogTable[index % 255]);
            return val;
        } else
            return 0;
    }

    void invMixColumn(byte[][] st) {
        // note that a is just a copy of st[.][c]
        for (int c = 0; c < 4; c++) {
            byte[] a = { st[0][c], st[1][c], st[2][c], st[3][c] };

            st[0][c] = (byte) (mul(0xE, a[0]) ^ mul(0xB, a[1]) ^ mul(0xD, a[2]) ^ mul(0x9, a[3]));
            st[1][c] = (byte) (mul(0xE, a[1]) ^ mul(0xB, a[2]) ^ mul(0xD, a[3]) ^ mul(0x9, a[0]));
            st[2][c] = (byte) (mul(0xE, a[2]) ^ mul(0xB, a[3]) ^ mul(0xD, a[0]) ^ mul(0x9, a[1]));
            st[3][c] = (byte) (mul(0xE, a[3]) ^ mul(0xB, a[0]) ^ mul(0xD, a[1]) ^ mul(0x9, a[2]));
        }
    }

    void MixColumns(byte[][] test) {
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

    void AddRoundKey(byte[][] state, byte[][] w, int startColumn) {
        // XOR the state with a 128-bit round key derived from the original key
        // K by a recursive process.
        int j = 0;
        for (int i = 0; i < 4; i++) {
            // byteToHexString(state[i][j]);
            state[i][j] ^= w[i][j + startColumn];
            state[i][j + 1] ^= w[i][j + 1 + startColumn];
            state[i][j + 2] ^= w[i][j + 2 + startColumn];
            state[i][j + 3] ^= w[i][j + 3 + startColumn];
            // byteToHexString(w[i][j + startColumn]);
            // byteToHexString(state[i][j]);
            // System.out.println();
        }
    }

    // void Cipher(byte[] in, byte out[], byte[][] w) {
    //     byte[][] state = new byte[4][Nb];
    //
    //     System.out.println("plaintext");
    //     printArray(state);
    //     AddRoundKey(state, w, 0);
    //     // printArray(state, "After addRoundKey(0): ");
    //     // here is where you apply the rounds
    //     for (int round = 1; round < Nr; round++) {
    //         SubBytes(state);
    //         // printArray(state, "After subBytes: ");
    //         ShiftRows(state);
    //         // printArray(state, "After shiftRows: ");
    //         MixColumns(state);
    //         // printArray(state, "After mixColumns: ");
    //         AddRoundKey(state, w, round * Nb);
    //         // printArray(state, "After addRoundKey(" + round +"): ");
    //     }
    //
    //     SubBytes(state);
    //     // printArray(state, "After subBytes: ");
    //     // printArray(state);
    //
    //     ShiftRows(state);
    //     // printArray(state, "After shiftRows: ");
    //     // printArray(state);
    //
    //     AddRoundKey(state, w, 10 * Nb);
    //     // printArray(state, "After addRoundKey(" + 10 +"): ");
    //     System.out.println("CipherText");
    //     printArray(state);
    //
    // }

    void printArray(byte[][] state) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                byteToHexString(state[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    void writeArrayToFile(byte[][] state, PrintWriter p) throws IOException {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                p.print(String.format("%02x", state[i][j]));
            }
        }
        p.println("\n");
    }

    void printArray(byte[][] state, String s) {
        System.out.println(s);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                byteToHexString(state[j][i]);

            }
            // System.out.println();
        }
        System.out.println();
    }

    void byteToHexString(byte b) {
        String temp = Integer.toHexString(b);
        if (temp.length() > 2) {
            temp = temp.substring(temp.length() - 2);
        } else if (temp.length() < 2)
            temp = "0".concat(temp);
        System.out.print(temp);
    }

    byte Rcon(int i) {
        return (byte) Math.pow(2, i - 1);
    }

    byte SubWord(byte temp) {
        String hex = Integer.toHexString(temp);
        hex = (hex.length() > 2) ? hex.substring(hex.length() - 2) : "0".concat(hex);
        int x = Integer.parseInt(hex.substring(0, 1), 16);
        int y = Integer.parseInt(hex.substring(1), 16);

        return (byte) sbox[16 * x + y];
    }

    byte[][] KeyExpansion(byte[][] K) {
        // nb = 4, nr = 10,
        byte[][] W = new byte[4][Nb * (Nr + 1)];
        int j;
        int i;

        for (j = 0; j < Nk; j++) {
            for (i = 0; i < 4; i++)
                W[i][j] = K[i][j];
        }

        for (j = Nk; j < Nb * (Nr + 1); j++) {
            if (j % Nk == 0) {
                W[0][j] = (byte) (W[0][j - Nk] ^ SubWord(W[1][j - 1]) ^ rcon[j / Nk]);
                for (i = 1; i < 4; i++)
                    W[i][j] = (byte) (W[i][j - Nk] ^ SubWord(W[(i + 1) % 4][j - 1]));
            } else if (j % Nk == 4 && Nk > 6) {
                for (i = 0; i < 4; i++)
                    W[i][j] = (byte) (W[i][j - Nk] ^ SubWord(W[i][j - 1]));
            } else {
                for (i = 0; i < 4; i++)
                    W[i][j] = (byte) (W[i][j - Nk] ^ W[i][j - 1]);
            }
        }

        return W;
    }

    void Cipher(byte[] in, byte[][] w, PrintWriter p) throws IOException {
        byte[][] state = new byte[4][Nb];
        int count = 0;

        // fill state
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] = in[count];
                count++;
            }
        }
        // System.out.println("plaintext");
        // printArray(state);
        AddRoundKey(state, w, 0);

        // here is where you apply the rounds
        for (int round = 1; round < Nr; round++) {
            SubBytes(state);
            // printArray(state, "After subBytes: ");
            ShiftRows(state);
            // printArray(state, "After shiftRows: ");
            MixColumns(state);
            // printArray(state, "After mixColumns: ");
            AddRoundKey(state, w, round * Nb);
            // printArray(state, "After addRoundKey(" + round +"): ");
        }

        SubBytes(state);
        // printArray(state, "After subBytes: ");
        // printArray(state);

        ShiftRows(state);
        // printArray(state, "After shiftRows: ");
        // printArray(state);

        AddRoundKey(state, w, 10 * Nb);
        // printArray(state, "After addRoundKey(" + 10 +"): ");
        // System.out.println("CipherText");
        // printArray(state);
        // write out the ciphertext in Hex notation to the output file
        writeArrayToFile(state, p);

    }

    void encrypt() throws IOException {
        BufferedReader keyRaw = new BufferedReader(new FileReader(keyFile));
        BufferedReader plaintextRaw = new BufferedReader(new FileReader(inputFile));
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("plaintext.enc.dec")));
        System.out.println("Encryption with Key Size: " + Nk + " Rounds: " + Nr);

        // InputFile: You'll read in a line, converting from Hex to binary for
        // storage into your state array.
        String line = null;
        String key = keyRaw.readLine().toLowerCase().trim();

        while ((line = plaintextRaw.readLine()) != null) {
            line = line.toLowerCase().trim();
            System.out.println(inputFile+ " here");
            // from hex to binary
            // The input data block is broken into a 4x4 byte array (128-bit
            // key)
            byte[] in = new byte[4 * Nb];

            /*
             * The expanded key. The initial key is of size 4*Nk bytes (see
             * table earlier), and this is expanded to the array w of
             * 4*Nb*(Nr+1) = 16*(Nr+1) bytes for input to the encryption
             * algorithm
             */
            byte[][] w = new byte[4][Nk];
            int j = 0;
            int count = 0;

            // EXPANDING THE KEY
            for (int i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    w[i][j] = (byte) Integer.parseInt(key.substring(count, count + 2), 16);
                    count += 2;
                }
            }

            //System.out.println("Key:");
            //System.out.println(Arrays.deepToString(w));

            //System.out.println("Expanded Key:");
            byte[][] exW = KeyExpansion(w);

            // for (j = 0; j < 4; j++) {
            //     for (int i = 0; i < Nb * (Nr + 1); i++) {
            //         System.out.print(String.format("%02X", exW[j][i]) + " ");
            //     }
            //     System.out.println("");
            // }
            // System.out.println("");
            //System.out.println(Arrays.toString(in));
            // System.exit(1);

            // FROM HEX TO INT, BYTES
            count = 0;
            for (int i = 0; i < 4*Nb; i++){
              in [i] = (byte)Integer.parseInt( line.substring(count, count+2),16 );
              count +=2;
            }
            // Apply the AES algorithm to encrypt the string as stored
            Cipher(in, exW, p);

        }
        keyRaw.close();
        plaintextRaw.close();
        p.close();
    }

    void Decipher(byte[] in, byte[][] w, PrintWriter p) throws IOException {
        byte[][] state = new byte[4][Nb];
        int count = 0;

        // fill state
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] = in[count];
                count++;
            }
        }

        //System.out.println("ciphertext");
        // printArray(state);

        AddRoundKey(state, w, Nb * 10);
        // printArray(state, "After addRoundKey(" + (10) + "): ");

        InvShiftRows(state);
        // printArray(state, "After invShiftRows: ");
        InvSubBytes(state);
        // printArray(state, "After invSubBytes: ");

        for (int round = Nr - 1; round > 1; round--) {
            AddRoundKey(state, w, round * Nb);
            // printArray(state, "After addRoundKey(" + round + "): ");

            invMixColumn(state);
            // printArray(state, "After mixColumns: ");

            InvShiftRows(state);
            // printArray(state, "After shiftRows: ");

            InvSubBytes(state);
            // printArray(state, "After subBytes: ");

        }

        AddRoundKey(state, w, Nb);
        // printArray(state, "After addRoundKey(" + 1 + "): ");

        invMixColumn(state);
        // printArray(state, "After mixColumns: ");

        InvShiftRows(state);
        // printArray(state, "After shiftRows: ");

        InvSubBytes(state);
        // printArray(state, "After subBytes: ");

        AddRoundKey(state, w, 0);
        // printArray(state, "After addRoundKey(" + 0 + "): ");

        //printArray(state);
        writeArrayToFile(state, p);
    }

    void decrypt() throws IOException {
        BufferedReader keyRaw = new BufferedReader(new FileReader(keyFile));
        BufferedReader ciphertextRaw = new BufferedReader(new FileReader(inputFile));
        PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("plaintext.enc.dec")));
        System.out.println("Decryption with Key Size: " + Nk + " Rounds: " + Nr);

        String line = null;
        String key = keyRaw.readLine().trim();

        while ((line = ciphertextRaw.readLine()) != null) {
            line = line.toLowerCase().trim();

            byte[] in = new byte[4 * Nb];

            byte[][] w = new byte[4][Nk];
            int j = 0;
            int count = 0;

            // EXPANDING THE KEY
            for (int i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    w[i][j] = (byte) Integer.parseInt(key.substring(count, count + 2), 16);
                    count += 2;
                }
            }

            byte[][] exW = KeyExpansion(w);

            // FROM HEX TO INT, BYTES
            count = 0;

            for (int i = 0; i < 4 * Nb; i++) {
                in[i] = (byte) Integer.parseInt(line.substring(count, count + 2), 16);
                count += 2;
            }

            Decipher(in, exW, p);

        }
        keyRaw.close();
        ciphertextRaw.close();
        p.close();
    }

    public static void main(String args[]) throws IOException {
        // java AES option keyFile inputFile
        AES sys = new AES();

        int i = 0, j;
        String arg;
        char flag;
        boolean vflag = false;
        String outputfile = "";

        while (i < args.length) {
            arg = args[i++];
    // use this type of check for "wordy" arguments
            if (arg.equals("-length")) {
              if (i < args.length)
                  sys.length = args[i++];
            }

    // use this type of check for arguments that require arguments
            else if (arg.equals("-key")) {
                if (i < args.length){
                    sys.keyFile = args[i++];
                }
                else
                    System.err.println("-key requires a file");
            }

            else if (arg.equals("-input")) {
                if (i < args.length)
                    sys.inputFile = args[i++];
                else
                    System.err.println("-output requires an input file");
            }



            //java AES option [length] [mode] keyFile inputFile
            //java AES d key plaintext.enc
    // use this type of check for a series of flag arguments
            else {
              if (arg.length() == 1)
                switch (arg.charAt(0)) {
                case 'd':
                    sys.option = "d";
                    break;
                case 'e':
                    sys.option = "e";
                    break;
                default:
                    System.err.println("ParseCmdLine: illegal option ");
                    break;
                }
            }
        }

        if (i != args.length)
            System.err.println("Usage: java AES option [-length] [-key afile] [-input afile]");

        if (sys.length.equals("128")){
          sys.Nr = 10;
          sys.Nk = 4;
        }
        else if (sys.length.equals("192")){
          sys.Nr = 12;
          sys.Nk = 6;
        }
        else if (sys.length.equals("256")){
          sys.Nr = 14;
          sys.Nk = 8;
        }
        else{
          System.out.println("Key Size is not available");
          System.exit(1);
        }

        if (sys.option.equals("e"))
          sys.encrypt();

        if (sys.option.equals("d"))
          sys.decrypt();

    }

}
