package codechef;

import java.util.Scanner;

public class ANIRWO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int T = scn.nextInt();

		for (int i = 0; i < T; i++) {
			int N = scn.nextInt();
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = scn.nextInt();
			}

			System.out.print(getSum(arr, arr.length));

		}
	}
	
//	2
//	2
//	1 2
//	3
//	1 2 3

	public static  int getSum(int arr[], int n) {
		int fact = factorial(n);

		int digitsum = 0;
		for (int i = 0; i < n; i++) {
			digitsum += arr[i];
		}
		digitsum *= (fact / n);

		int res = 0;
		for (int i = 1, k = 1; i <= n; i++) {
			res += (k * digitsum);
			k = k * 10;
		}

		return res;
	}

	public static  int factorial(int num) {
		if (num == 0) {
			return 1;
		}

		int num1 = factorial(num - 1);
		int number = num * num1;
		return number;
	}

}
