package org.arijit.robikarp;

/**
 * Search pattern using robin karp algorithm
 * @author ARIJIT
 *
 */
public class RobinKarpPatternSearch {

	private static int ascii(char ch) {
		int a = ch;
		return a;
	}
	private static int patternHash(String pattern, int primeFactor) {
		int hashValue = 0;
		int i=0;
		while(i<pattern.length()) {			
			int val = (int)(ascii(pattern.charAt(i))* Math.pow(primeFactor,i));
			hashValue = hashValue + val;			
			i++;
		}
		return hashValue;
	}
	public static void findPattern(String text, String pattern) {
		char[] txtArray = text.toCharArray();
		char[] patternArray = pattern.toCharArray();
		
		int primeFactor = 101;
		
		int i=0;
		int m = patternArray.length;
		int n=0;
		int hashValue = 0;
		int patternHashValue = patternHash(pattern, primeFactor);
		while(i<=txtArray.length) {
			if(n==m) {
				System.out.println(text.substring(i-m,i));
				//now chek if the substring between i to i+m is equal to pattern
				if(patternHashValue == hashValue){
					//probable match found
					String subStr = text.substring(i-m,i);
					if(subStr.equals(pattern))
						System.out.println(" match found: at "+(i-m));
				}
				int j = i-m;
				hashValue = hashValue-ascii(txtArray[j]);
				hashValue = hashValue/primeFactor;
				n=m-1;	
				
			}
			if(i==txtArray.length)
				break;
			char ch = txtArray[i];
			int val = (int)(ascii(ch)* Math.pow(primeFactor,n));
			hashValue = hashValue + val;
			n++;
			i++;
		}
	}
	
	public static void main(String args[]) {
		String text = "babcdbcdabc";
		String pattern = "abc";
		findPattern(text, pattern);
		
		
	}
}
