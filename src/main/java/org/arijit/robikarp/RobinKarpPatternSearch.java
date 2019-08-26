package org.arijit.robikarp;

/**
 * Search pattern using robin karp algorithm
 * 
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
		int i = 0;
		while (i < pattern.length()) {
			int val = (int) (ascii(pattern.charAt(i)) * Math.pow(primeFactor, i));
			hashValue = hashValue + val;
			i++;
		}
		return hashValue;
	}

	public static void findPattern1(String text, String pattern) {
		int l = pattern.length();
		int primeFactor = 101;
		int patternHashValue = patternHash(pattern, primeFactor);
		int hashValue = 0;

		for (int i = 0; i < l; i++) {
			hashValue = (int) (hashValue + (ascii(text.charAt(i)) * Math.pow(primeFactor, i)));
		}
		System.out.println(text.length());
		for (int i = l; i <= text.length(); i++) {
			int j = i - l;// locating first positing of previous substring
			int k = i; // locating last position of new Substring;
			System.out.println("i= " + i + " j= " + j + " k= " + k);
			if (hashValue == patternHashValue) {
				if (text.substring(j, i).equals(pattern)) {
					System.out.println("Match found between :" + j + " ->" + (i - 1));
				}
			}
			// there is an edge case when k can go beyond aloowed text index. We need to
			// make sure that never happens
			if (k < text.length()) 
			{
				// removing hash of first element of previous substring
				hashValue = hashValue - ascii(text.charAt(j));
				hashValue = hashValue / primeFactor;
				hashValue = (int) (hashValue + (text.charAt(k) * Math.pow(primeFactor, (l - 1))));
			}
		}
	}

	public static void findPattern(String text, String pattern) {
		char[] txtArray = text.toCharArray();
		char[] patternArray = pattern.toCharArray();

		int primeFactor = 101;

		int i = 0;
		int m = patternArray.length;
		int n = 0;
		int hashValue = 0;
		int patternHashValue = patternHash(pattern, primeFactor);
		while (i <= txtArray.length) {
			if (n == m) {
				System.out.println(text.substring(i - m, i));
				// now chek if the substring between i to i+m is equal to pattern
				if (patternHashValue == hashValue) {
					// probable match found
					String subStr = text.substring(i - m, i);
					if (subStr.equals(pattern))
						System.out.println(" match found: at " + (i - m));
				}
				int j = i - m;
				hashValue = hashValue - ascii(txtArray[j]);
				hashValue = hashValue / primeFactor;
				n = m - 1;

			}
			if (i == txtArray.length)
				break;
			char ch = txtArray[i];
			int val = (int) (ascii(ch) * Math.pow(primeFactor, n));
			hashValue = hashValue + val;
			n++;
			i++;
		}
	}

	public static void main(String args[]) {
		String text = "babcdbcdabc";
		String pattern = "abc";
		findPattern(text, pattern);
		findPattern1(text, pattern);

	}
}
