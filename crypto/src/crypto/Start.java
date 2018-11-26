package crypto;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Start {
	
	 int numRounds = 10; // z góry narzucona liczba obrotów dla klucza 128 bit
	 int ilosc, licznik, g, j, i, t, k;
	 String FILE_NAME = "";
	 String key = "";
	 private byte[] bytes;
	 private byte[] bytes1;
	 Path path, path1, path2;
	//String klucz = new String("0A935D11496532BC1004865ABDCA4295");
	 
	 public Start(String file, String key) {
		 this.FILE_NAME = file;
		 this.key = key;
	 }
	
		 final String OUTPUT_FILE_NAME = new String("C:\\Temp\\Output_cod.docx");
		 final String OUTPUT_FILE_NAME1 = new String("C:\\Temp\\Output_dec.docx");
		 		 
		 public void readByte( ) throws IOException {
			path = Paths.get(FILE_NAME);
			path1 = Paths.get(OUTPUT_FILE_NAME);
			path2 = Paths.get(OUTPUT_FILE_NAME1);
			bytes = Files.readAllBytes(path);
			bytes1 = Files.readAllBytes(path);
			ilosc = bytes.length;
			licznik = ilosc/16;
		 }

		public void fileCode( ) throws IOException {
			
		     k = 0;
		     t = 0;

			AES aes = new AES();
			int[][] keymatrix = aes.keySchedule(key);

			for (g=0; g < licznik ; g++) {

				int[][] state = new int[4][4];	

				for (j = 0; j < 4; j++) {
					for (int i = 0; i < 4; i++) {
						if(k>(ilosc-1)) {
							state[i][j] = 0;
						}
						else {
							state[i][j] = bytes[k];
							state[i][j] = state[i][j]+128;
						}
						k++;
					}
				}

				aes.addRoundKey(state, aes.subKey(keymatrix, 0)); 

				for (i = 1; i < numRounds; i++) {

					aes.subBytes(state); 
					aes.shiftRows(state);
					aes.mixColumns(state);
					aes.addRoundKey(state, aes.subKey(keymatrix, i));

				}

				aes.subBytes(state);
				aes.shiftRows(state);
				aes.addRoundKey(state, aes.subKey(keymatrix, numRounds));

				for (j = 0; j < 4; j++) {
					for (int i = 0; i < 4; i++) {
						if((ilosc-1)>t) {
							bytes1[t] = (byte) (state[i][j]-128);
						}
						t++;
					}
				}
			}

			Files.write(path1, bytes1);
		
		}

		public void fileDecode( ) throws IOException {

			k = 0;
			t = 0;
			
			AES aes = new AES();
			int[][] keymatrix = aes.keySchedule(key);

			for(g=0; g < licznik; g++) {

				int[][] state1 = new int[4][4];


				for (int j = 0; j < 4; j++) {
					for (int i = 0; i < 4; i++) {
						if(k>(ilosc-1)) {
							state1[i][j] = 0;
						}
						else {
							state1[i][j] = bytes1[k];
							state1[i][j] = state1[i][j]+128;
						}
						k++;
					}
				}

				aes.addRoundKey(state1, aes.subKey(keymatrix, numRounds));

				for (int i = numRounds - 1; i > 0; i--) {
					
					aes.invShiftRows(state1);
					aes.invSubBytes(state1);
					aes.addRoundKey(state1, aes.subKey(keymatrix, i));
					aes.invMixColumns(state1);
				}

				aes.invShiftRows(state1);
				aes.invSubBytes(state1); 
				aes.addRoundKey(state1, aes.subKey(keymatrix, 0));

				for (j = 0; j < 4; j++) {
					for (i = 0; i < 4; i++) {
						if((ilosc-1)>t) {
							bytes1[t] = (byte) (state1[i][j]-128);
						}
						t++;
					}
				}
			}

			Files.write(path2, bytes1);

		}
}