package DynamicProgramingFraands;

public class EditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "sunday";
		String s2 = "saturday";
		System.out.println(editDistance("sunday","saturday"));
		System.out.println(editDistanceDynamic2(s1, s2, new int [s1.length()+1][s2.length()+1]));
	}
	
	public static int editDistance(String s1 , String s2) {
		
		if(s1.length()==0) {
			return s2.length();
		}
		if(s2.length()==0) {
			return s1.length();
		}
		
		if(s1.charAt(s1.length()-1)==s2.charAt(s2.length()-1)) {
			s1 = s1.substring(0, s1.length()-1);
			s2 = s2.substring(0, s2.length()-1);
			return editDistance(s1, s2);
			
		} 	
		
		
		int insert	 = editDistance(s1.substring(0,s1.length()), s2.substring(0,s2.length()-1));
		int remove	 = editDistance(s1.substring(0,s1.length()-1), s2.substring(0,s2.length()));
		int replce	 = editDistance(s1.substring(0,s1.length()-1), s2.substring(0,s2.length()-1));
		return 1+Math.min(Math.min(insert, remove), replce);
		
	}
	
	public static int editDistanceDynamic(String s1 , String s2 ,  mover[] mv) {
		
		// top down apporach 
		int [][] mem = new int[s1.length()][s2.length()+1];
		
		if(s1.length()==0) {
			return s2.length();
		}
		if(s2.length()==0) {
			return s1.length();
		}
		
		
		
		if(s1.charAt(s1.length()-1)==s2.charAt(s2.length()-1)) {
			s1 = s1.substring(0, s1.length()-1);
			s2 = s2.substring(0, s2.length()-1);
			return editDistance(s1, s2);
			
		} 	
		
		
		int insert	 = editDistanceDynamic(s1.substring(0,s1.length()), s2.substring(0,s2.length()-1),mv);
		int remove	 = editDistanceDynamic(s1.substring(0,s1.length()-1), s2.substring(0,s2.length()),mv);
		int replce	 = editDistanceDynamic(s1.substring(0,s1.length()-1), s2.substring(0,s2.length()-1),mv);
		int res = 1+Math.min(Math.min(insert, remove), replce);
		
		return res;
		
	}
	
	public class mover {
		int a ;
		int b;
		int value ;
	}
	
	public static int editDistanceDynamic2 (String s1 , String s2 , int [][] memory) {
		//bottom up manner 
		for(int i =0; i<s1.length()+1 ; i++) {
			for (int j= 0 ; j < s2.length()+1 ; j++) {
				
				if(i==0) {
					memory[i][j] = j; 
				}else if(j==0) {
					memory[i][j] = i;
				}
				
				else if(s1.charAt(i-1) == s2.charAt(j-1)) {
					memory[i][j]=memory[i-1][j-1];
				}else
				memory[i][j] = 1+Math.min(Math.min(memory[i][j-1], memory[i-1][j]),memory[i-1][j-1]);
				
			}
		}
		return memory[s1.length()][s2.length()];
	}
	

}
