package june1;

import java.util.Scanner;

public class abtod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Value enter :");
		Scanner scn = new Scanner(System.in);

		int n = scn.nextInt();
		System.out.println("Given any Base :");
		int ab = scn.nextInt();
		
		int dec = 0;
		int abtp = 1;
		while (n!=0)
		{
			int rem = n % 2;
			n = n / 2;

			dec = dec + rem * abtp;
			abtp = abtp * ab;
		}
		
		System.out.println(dec);
	}

}
