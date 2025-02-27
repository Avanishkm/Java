package DynamicProgramingFraands;

import javax.sound.midi.Soundbank;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LCSRecursion("AGGTAB", "GXTXAYB" , ""));
		System.out.println(LCSDynamic("AGGTAB", "GXTXAYB"));
		System.out.println(LCSDynamicBEtter("acf", "abefg"));
		
	}
	
	public static int LCSRecursion (String s1 , String s2 , String asf) {
		
		if(s1.length()==0) {
//			System.out.println(asf);
			return 0;
		}
		
		if(s2.length()==0) {
//			System.out.println(asf);
			return 0;
		}
		int rv=0;
		
		char ch1 = s1.charAt(s1.length()-1);
		char ch2 = s2.charAt(s2.length()-1);
		
		String ros1 = s1.substring(0, s1.length()-1);
		String ros2 = s2.substring(0, s2.length()-1);
		
		if(ch1==ch2) {
			rv=1+LCSRecursion(ros1, ros2 , asf+ch1);
		}else {
			int a = LCSRecursion(ros1, s2 ,asf);
			int b = LCSRecursion(s1, ros2 ,asf);
			
			rv = Math.max(a, b);
			
		}
		return rv;
		
	}
	
	public static int LCSDynamic (String s1 , String s2) {
		//bottom up approach 
		int [][] arr = new int[s1.length()+1][s2.length()+1];
		
		for(int i =s1.length()-1 ; i>=0 ; i--) {
			for(int j = s2.length()-1 ; j>=0 ; j--) {
				if(s1.charAt(i)==s2.charAt(j)) {
					arr[i][j] = 1+ arr[i+1][j+1];
				}else {
					arr[i][j] =  Math.max(arr[i+1][j], arr[i][j+1]);
				}
			}
		}
		return arr[0][0];
	}
	
	public static int LCSDynamicBEtter (String s1 , String s2) {
		// bottom up approach 
		int [] arr = new int [s2.length()+1];
		
		for(int i =s1.length()-1 ; i>=0 ; i--) {
			for(int j = s2.length()-1 ; j>=0 ; j--) {
				if(s1.charAt(i)==s2.charAt(j)) {
					arr[j] = arr[j+1] +1;
				}else {
					arr[j] =  Math.max(arr[j+1], arr[j]);
				}
			}
		}
		return arr[0];
	}
}
