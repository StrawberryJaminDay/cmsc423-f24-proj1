import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FastaStats {

	/*
	 * Takes in a file name (string) of a FASTA file
	 * Calculates the following statistics and prints them in JSON format to stdout:
	 * 		min_len - The length of the shortest sequence observed for any 
	 * 		record 		
	 *		max_len - The length of the longest sequence observed for any 
	 *		record 
	 *		mean_len - The average length of sequences observed for the records
	 *		in the input file.
	 *		tot_len - The total length of sequences observed for the records in
	 *		the input file.
	 *		num_records - The total number of input records observed in the 
	 *		input file.
	 *		count_a - The total number of occurrences of the nucleotide A 
	 *		appearing in this file.
	 *		count_c - The total number of occurrences of the nucleotide C 
	 *		appearing in this file.
	 *		count_g - The total number of occurrences of the nucleotide G 
	 *		appearing in this file.
	 *		count_t - The total number of occurrences of the nucleotide T 
	 *		appearing in this file.
	 */
	public static void printStats (String fname) {

		try {
			File fastaFile = new File(fname);
			Scanner fastaReader = new Scanner(fastaFile);

			//vars for stats
			int maxLen = 0;
			int minLen = Integer.MAX_VALUE;
			int totalLen = 0;
			int numRecords = 0;
			int countA = 0;
			int countC = 0;
			int countT = 0;
			int countG = 0;
			
			int currLen = 0; //length of the record currently being inspected
			
			if (fastaReader.hasNextLine()) {
				String fastaLine = fastaReader.nextLine();
				if (fastaLine.charAt(0) == '>') {
					numRecords += 1;
					System.err.format("processing record %d\n", numRecords);
					currLen = 0;
				}
			}
			
			//read through each line
			while (fastaReader.hasNextLine()) {
				String fastaLine = fastaReader.nextLine();
				
				//if a ">" character is seen, add 1 to numRecords, update 
				//min and max if needed, and reset current record len
				if (fastaLine.charAt(0) == '>') {
					
					if(currLen < minLen) {
						minLen = currLen;
					}
					if (currLen > maxLen) {
						maxLen = currLen;
					}
					numRecords += 1;
					System.err.format("processing record %d\n", numRecords);
					currLen = 0;
				}
				//else add length of the line to currRecLen and totalLen, 
				//and count occs of each nucleotide	
				else {
					
					int lineLen = fastaLine.length();
					currLen += lineLen;
					totalLen += lineLen;
					
					//iterate through all chars to count occ of each nucleotide
					for (int i = 0; i < lineLen; i++) {
						
						char currChar = fastaLine.charAt(i);
						if(currChar == 'A') {
							countA ++;
						}
						else if(currChar == 'C') {
							countC ++;
						}
						else if(currChar == 'T') {
							countT ++;
						}
						else if(currChar == 'G') {
							countG ++;
						}
					}
				}
				
			}

			if(numRecords > 0){
				if(currLen < minLen) {
					minLen = currLen;
				}
				if (currLen > maxLen) {
					maxLen = currLen;
				}
			}
			else{
				minLen = 0;
				maxLen = 0;
			}
			
			//print summary of stats as a JSON obj
			//ex. of string in JSON format: {"firstName":"John", "lastName":"Doe"}
			System.out.println("{");
			System.out.format("\"min_len\" : %d,\n", minLen);
			System.out.format("\"max_len\" : %d,\n", maxLen);
			System.out.format("\"mean_len\" : %f,\n", ((double) totalLen / numRecords));
			System.out.format("\"tot_len\" : %d,\n", totalLen);
			System.out.format("\"num_records\" : %d,\n", numRecords);
			System.out.format("\"count_a\" : %d,\n", countA);
			System.out.format("\"count_c\" : %d,\n", countC);
			System.out.format("\"count_g\" : %d,\n", countG);
			System.out.format("\"count_t\" : %d\n", countT);
			System.out.println("}");
			
			fastaReader.close();
		} 
		catch (FileNotFoundException e) {
			System.err.format("input file %s not found!\n", fname);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String fname = args[0];
		//String fname = "test_data/sample.fa";
		System.err.format("input file: %s\n", fname);
		printStats(fname);
	}

}
