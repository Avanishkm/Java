package SegmentedTree;

import java.util.*;

public class practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] nums = { -1, 1, 1, 1, 2, 1 };
//		List<Integer> l = majorityElement(nums);
//		for (int i : l) {
//			System.out.println(i);
//		}
//		int arr[] = new int[] {1,2,0 };
//		System.out.println(firstMissingPositive(arr));
//		for (int i : arr) {
//			System.out.println(i);
//		}
//		String s = "hello";
//		System.out.println(reverseVowels(s));
//		int[][] arr = { { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
//		System.out.println(pushDominoes(".L.R...LR..L.."));
//		int[][] list1 = {{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}};
//		List<List<Integer>> list = new ArrayList<List<Integer>>(); 
//		
//		for(int i=0;i<list1.length;i++) {
//			ArrayList<Integer> al = new ArrayList<>();
//			for(int j=0;j<list1[i].length;j++) {
//				al.add(list1[i][j]);
//			}
//			list.add(al);
//			al = new ArrayList<>();
//		}
//		
//		int[] ansarr = smallestRange(list);
//		for(int i: ansarr) {
//			System.out.println(i);
//		}
		
		int n =1; // 101
		System.out.println(1^0);

	}

	public static List<Integer> majorityElement(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		if (nums.length == 0) {
			return ans;
		}
		int k = 3;
		if (k == 1) {
			k = 1;
		} else {
			k = k - 1;
		}
		int[] element = new int[k];
		int[] count = new int[k];

		Arrays.fill(element, Integer.MIN_VALUE);
		element[0] = nums[0];
		count[0] = 1;

		for (int i = 1; i < nums.length; i++) {
			int e = 0;
			for (e = 0; e < element.length; e++) {
				if (element[e] == nums[i]) {
					count[e]++;

					break;
				}
			}

			if (e == element.length) {

				int l = 0;
				for (l = 0; l < element.length; l++) {
					if (count[l] == 0) {
						element[l] = nums[i];
						count[l] = 1;
						break;
					}
				}

				if (l == element.length - 1) {

					for (int a = 0; a < element.length; a++) {
						count[a]--;
					}
				}

			}

		}

		int elementans = Integer.MIN_VALUE;
		for (int e = 0; e < element.length; e++) {
			int elm = element[e];
			int count1 = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == elm) {
					count1++;
				}
			}
			if ((count1 > nums.length / (k + 1)) && element[e] != Integer.MIN_VALUE)
				ans.add(elm);

		}

		return ans;
	}

	public static String reverseVowels(String s1) {
		int i = 0;
		int j = s1.length() - 1;
		StringBuilder s = new StringBuilder(s1);

		while (i < j) {

			while ((!isVovl(s.charAt(i))) && i < s.length()) {
				if (i >= j) {
					break;
				}
				i++;
			}
			while ((!isVovl(s.charAt(j))) && j >= 0) {
				if (i >= j) {
					break;
				}
				j--;
			}

			char temp = s.charAt(i);
			s.setCharAt(i, s.charAt(j));
			s.setCharAt(j, temp);
			i++;
			j--;
			if (i >= j) {
				break;
			}

		}
		return s.toString();
	}

	public static boolean isVovl(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'o' || ch == 'i' || ch == 'u')
			return true;
		else
			return false;
	}

	public static int firstMissingPositive(int[] nums) {
		segregate0and1(nums, nums.length);
		// -1 4 3 1

		int i = 0;
		int j = nums.length - 1;
		while (i < nums.length) {
			if (nums[i] <= 0 || nums[i] == i + 1 || nums[i] > nums.length) {
				i++;
				continue;
			} else if (nums[nums[i] - 1] != nums[i]) {
				// swap
				int temp = nums[i]; // 4
				nums[i] = nums[temp - 1];
				nums[temp - 1] = temp;
			} else
				i++;
		}

		int k = 0;
		for (k = 0; k < nums.length; k++) {
			if (nums[k] != k + 1) {
				break;
			}
		}
		return k + 1;
	}

	public static void segregate0and1(int arr[], int size) {

		int left = 0, right = size - 1;

		while (left < right) {

			while (arr[left] <= 0 && left < right)
				left++;

			while (arr[right] > 0 && left < right)
				right--;

			if (left < right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}
	}

	public static int minTotalDistance(int[][] grid) {
		// Write your code here
		// 1 0 0 0 1
		// 0 0 0 0 0
		// 0 0 1 0 0

		// 0,2,4 x axis
		// 0 2 4
		// 0 0 0, 0
		// 0,4
		// 2,2
		ArrayList<pair> al = new ArrayList<pair>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1)
					al.add(new pair(i, j));
			}
		}

		int[] xaxis = new int[al.size()];
		int[] yaxis = new int[al.size()];

		for (int i = 0; i < al.size(); i++) {
			pair p = al.get(i);
			xaxis[i] = p.x;
			yaxis[i] = p.y;
		}

		Arrays.sort(xaxis);
		Arrays.sort(yaxis);

		int xaxislen = xaxis.length;
		int yaxislen = yaxis.length;

		int xmedian = xaxis[xaxislen / 2];
		int ymedian = yaxis[yaxislen / 2];

		int sum = 0;
		for (int i = 0; i < al.size(); i++) {
			pair p = al.get(i);
			int a = Math.abs(p.y - ymedian);
			int b = Math.abs(p.x - xmedian);
			sum += a + b;
		}
		return sum;

	}

	public static class pair {
		int x;
		int y;

		pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static String pushDominoes(String d) {
		StringBuilder s = new StringBuilder();
		s.append("L");
		s.append(d);
		s.append("R");

		StringBuilder ans = new StringBuilder();

		int i = 0;
		int j = i + 1;
		while (i < s.length() && j < s.length()) {
			// System.out.println(s.charAt(i));
			while (((s.charAt(i) + "").equals(".")) && i < s.length()) {
				i++;
			}

			while (((s.charAt(j) + "").equals(".")) && j < s.length()) {
				j++;
			}

			// case i = L..L

			if (s.charAt(i) == 'L' && s.charAt(j) == 'L') {
				StringBuilder temp = new StringBuilder();

				for (int t = 0; t < j - i; t++) {
					temp.append('L');
				}
				ans.append(temp);
			}

			// case ii = R..R

			if (s.charAt(i) == 'R' && s.charAt(j) == 'R') {
				StringBuilder temp = new StringBuilder();

				for (int t = 0; t < j - i; t++) {
					temp.append('R');
				}
				ans.append(temp);
			}

			// case iii = L..R
			if (s.charAt(i) == 'L' && s.charAt(j) == 'R') {
				StringBuilder temp = new StringBuilder();
				temp.append('L');
				for (int t = 0; t < j - i - 1; t++) {
					temp.append('.');
				}
				// temp.append('R');

				ans.append(temp);
			}

			// case iV = R..L
			if (s.charAt(i) == 'R' && s.charAt(j) == 'L' && ((j - i) % 2) != 0) {
				int num = j - i;
				int middle = 0;
				if (num % 2 != 0) {
					middle = num / 2;
				}
				StringBuilder temp = new StringBuilder();

				for (int t = 0; t <= middle; t++) {
					temp.append('R');
				}
				for (int t = middle + 1; t <= j - 1; t++) {
					temp.append('L');
				}

				ans.append(temp);
			}

			// case V = R...L
			if (s.charAt(i) == 'R' && s.charAt(j) == 'L' && ((j - i) % 2) == 0) {
				int num = j - i;
				int middle = 0;
				if (num % 2 == 0) {
					middle = num / 2;
				}
				StringBuilder temp = new StringBuilder();

				for (int t = 0; t < middle; t++) {
					temp.append('R');
				}
				temp.append('.');
				for (int t = middle + 1; t <= j - 1; t++) {
					temp.append('L');
				}

				ans.append(temp);
			}

			i = j;
			j++;

		}

		return ans.toString();

	}

	public static int[] smallestRange(List<List<Integer>> nums) {

		int ans = Integer.MAX_VALUE;
		int minimum=0;
		int maximin=0;
		

		Comparator<pair1> cmin = new Comparator<practice.pair1>() {

			@Override
			public int compare(pair1 o1, pair1 o2) {
				// TODO Auto-generated method stub
				return o1.data - o2.data;
			}
		};
		Comparator<pair1> cmax = new Comparator<practice.pair1>() {

			@Override
			public int compare(pair1 o1, pair1 o2) {
				// TODO Auto-generated method stub
				return o2.data - o1.data;
			}
		};

		PriorityQueue<pair1> pqmin = new PriorityQueue<>(cmin);
		PriorityQueue<pair1> pqmax = new PriorityQueue<>(cmax);
		for (int i = 0; i < nums.size(); i++)
			pqmin.add(new pair1(nums.get(i).get(0), i, 0, nums.get(i).size() - 1));

		for (int i = 0; i < nums.size(); i++)
			pqmax.add(new pair1(nums.get(i).get(0), i, 0, nums.get(i).size() - 1));

		while (true) {
			pair1 pop = pqmin.poll();
			int min = pop.data;
			int max = pqmax.peek().data;
			if (max - min < ans) {
				ans = max-min;
				minimum = min;
				maximin = max;
			}

			if (pop.j >= pop.lastIndex) {
				break;
			} else {
				pqmin.add(new pair1(nums.get(pop.i).get(pop.j + 1), pop.i, pop.j + 1, pop.lastIndex));
				pqmax.add(new pair1(nums.get(pop.i).get(pop.j + 1), pop.i, pop.j + 1, pop.lastIndex));
			}
		}
		return new int[]{minimum,maximin};

	}

	public static class pair1 {
		int data;
		int i;
		int j;
		int lastIndex;

		pair1(int data, int i, int j, int lastInde) {
			this.data = data;
			this.i = i;
			this.j = j;
			this.lastIndex = lastInde;
		}
	}
	
	 public static int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
	        // bhut badhiya question 
		 	Map<Integer, Integer> m1 = new HashMap();       // row number to count of lamps
	        Map<Integer, Integer> m2 = new HashMap();       // col number to count of lamps
	        Map<Integer, Integer> m3 = new HashMap();       // diagonal x-y to count of lamps
	        Map<Integer, Integer> m4 = new HashMap();       // diagonal x+y to count of lamps
	        Map<Integer, Boolean> m5 = new HashMap();       // whether lamp is  ON|OFF
	        String s = "ss";
	        
	        
	        // initialising Hashmaps 
	        for(int i=0 ;i<lamps.length;i++) {
	        	int x = lamps[i][0];
	        	int y = lamps[i][1];
	        	
	        	m1.put(x, m1.getOrDefault(x, 0)+1);
	        	m2.put(y, m2.getOrDefault(y, 0)+1);
	        	m3.put(x+y, m3.getOrDefault(x+y, 0)+1);
	        	m4.put(x-y, m4.getOrDefault(x-y, 0)+1);
	        	m5.put(N*x+y, true);
	        	
	        }
	        
	        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}, {0,0}};
	        int[] ans = new int[queries.length];
	        for(int i=0; i<queries.length;i++) {
	        	int x = queries[i][0];
	        	int y = queries[i][1]; 
	        	ans[i] = (m1.getOrDefault(x,0)>0 ||m2.getOrDefault(y,0)>0||	m3.getOrDefault(x+y,0)>0
	        			||m4.getOrDefault(x-y,0)>0||m5.getOrDefault(N*x+y,false)==true)?1:0;
	        
	        	for(int j=0;j<dirs.length;j++) {
	        		int x1 = x +dirs[i][0];
	        		int y1 = y+ dirs[i][1];
	        		
	        		if(m5.containsKey(N*x1+y1)&&m5.get(N*x1+y1)) {
	        			m1.put(x, m1.getOrDefault(x, 1)-1);
	    	        	m2.put(y, m2.getOrDefault(y, 1)-1);
	    	        	m3.put(x+y, m3.getOrDefault(x+y, 1)-1);
	    	        	m4.put(x-y, m4.getOrDefault(x-y, 1)-1);
	    	        	m5.put(N*x+y, false);
	        		}
	        	}
	        }
	        
		 return ans;
	    }

	 
}
