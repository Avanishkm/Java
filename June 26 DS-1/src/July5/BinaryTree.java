package July5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;

import org.omg.CORBA.INTERNAL;

import ExtraClassesForDs.GenericHashMap.HashMap;

//import July3.GenericTree.HeapMover;
//import July3.GenericTree.Node;

public class BinaryTree {

	private class Node {
		int data;
		Node left;
		Node right;
	}

	private Node root;
	private int size;

	public BinaryTree(int[] pre, int[] in) {
		this.root = this.construct2(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	private Node construct(int[] pre, int plo, int phi, int[] in, int ilo, int ihi) {
		if (plo > phi) {
			return null;
		}
		// create and attach the node
		Node node = new Node();
		node.data = pre[plo];
		this.size++;

		// search
		int searchIndex = -1;
		for (int i = ilo; i <= ihi; i++) {
			if (in[i] == pre[plo]) {
				searchIndex = i;
				break;
			}
		}
		int nelOnls = searchIndex - ilo;

		// for left
		node.left = this.construct(pre, plo + 1, plo + nelOnls, in, ilo, searchIndex - 1);

		// for right
		node.right = this.construct(pre, plo + nelOnls + 1, phi, in, searchIndex + 1, ihi);

		return node;

	}

	private Node construct2(int[] pos, int plo, int phi, int[] in, int ilo, int ihi) {

		if (plo > phi || ilo > ihi) {
			return null;
		}

		Node node = new Node();

		node.data = pos[phi];
		this.size++;

		// search
		int searchIndex = -1;
		for (int i = ilo; i <= ihi; i++) {
			if (in[i] == pos[phi]) {
				searchIndex = i;
				break;
			}
		}

		int nelOnls = searchIndex - ilo;

		// for left

		node.left = this.construct2(pos, plo, plo + nelOnls - 1, in, ilo, searchIndex - 1);

		// for right

		node.right = this.construct2(pos, plo + nelOnls, phi - 1, in, searchIndex + 1, ihi);

		return node;
	}

	// Binary tree

	public BinaryTree() {
		this.root = this.takeInput(new Scanner(System.in), null, false);
	}

	public Node takeInput(Scanner scn, Node parent, boolean ilc) {

		// prompt

		if (parent == null) {
			System.out.println("Root Node ? ");
		} else if (ilc) {
			System.out.println("Enter the left child for " + parent.data);
		} else {

			System.out.println("Enter the right child for " + parent.data);

		}

		// collect data and create node

		int cdata = scn.nextInt();
		Node child = new Node();
		child.data = cdata;
		this.size++;

		// left
		System.out.println("Do you have Left child for " + child.data);
		boolean hlc = scn.nextBoolean();
		// takeInput(scn, child, true);

		if (hlc) {
			child.left = takeInput(scn, child, true);

		}
		// else {
		//
		// System.out.println(".");
		// }

		// right

		System.out.println("Do you have right child for " + child.data);
		boolean hrc = scn.nextBoolean();
		// takeInpur(scn, child, false);

		if (hrc) {
			child.right = takeInput(scn, child, false);
		}
		// else {
		//
		// System.out.println(".");
		// }

		// return

		return child;

	}

	public void display() {

		display(this.root);
	}

	private void display(Node node) {

		String str = "";

		if (node == null) {
			return;
		}

		if (node.left != null) {
			str += node.left.data;
			// System.out.println(node.left.data + "===>");
		} else {

			str += ".";
			// System.out.println(str + "===>");
		}

		// System.out.println( " " + node.data + " ");
		str += " => " + node.data + " <= ";

		if (node.right != null) {
			str += node.right.data;
			// System.out.println(node.right.data);
		} else {

			str += ".";
		}

		System.out.println(str);
		this.display(node.left);
		this.display(node.right);

	}

	public int size() {
		return this.size;
	}

	public boolean Isempty() {
		return this.size() == 0;

	}

	// Size 2

	public int size2() {

		return this.size2(this.root);
	}

	private int size2(Node node) {
		if (node == null) {

			return 0;
		}

		int ls = this.size2(node.left);
		int rs = this.size2(node.right);

		return ls + rs + 1;
	}

	// MAX

	public int max() {
		return max(this.root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		// if(node.left == null ||node.right == null ) {
		// return node.data;
		// }
		int l = max(node.left);
		int r = max(node.right);

		int k = Math.max(l, Math.max(r, node.data));

		return k;

	}

	// HEIGHT

	public int height() {
		return height(this.root);
	}

	private int height(Node node) {

		if (node == null) {
			return -1;
		}

		int lh = height(node.left);
		int rh = height(node.right);

		return Math.max(lh, rh) + 1;
	}

	// MINIMUM

	public int min() {
		return min(this.root);
	}

	private int min(Node node) {

		if (node == null) {

			return Integer.MAX_VALUE;
		}

		// if (root.left == null || root.right == null) {
		//
		// return root.data;
		// }

		int l = min(node.left);
		int r = min(node.right);

		return Math.min(Math.min(l, r), root.data);

	}

	// FIND

	public boolean find(int data) {
		return find(this.root, data);
	}

	private boolean find(Node node, int data) {

		if (node == null)
			return false;

		if (node.data == data) {
			return true;
		}

		boolean left = find(node.left, data);
		boolean right = find(node.right, data);

		return left || right;

	}

	// DIAMETER

	public int diameter() {

		return diameter(this.root);

	}

	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}

		int l = this.height(node.left);
		int r = this.height(node.right);
		int sum = 2 + l + r;

		int a = this.diameter(node.left);
		int b = this.diameter(node.right);
		int max2 = Math.max(a, b);
		return Math.max(sum, max2);
	}

	// DIAMETER 2

	public int diameter2() {
		return this.diameter2(this.root).diameter;
	}

	private DiaPair diameter2(Node node) {
		if (node == null) {
			DiaPair rv = new DiaPair();

			rv.height = -1;
			rv.diameter = 0;
			return rv;
		}

		DiaPair l = this.diameter2(node.left);
		DiaPair r = this.diameter2(node.right);

		DiaPair dp = new DiaPair();

		dp.height = Math.max(l.height, r.height) + 1;
		dp.diameter = Math.max(l.height + r.height + 2, Math.max(l.diameter, r.diameter));

		return dp;

	}

	private class DiaPair {
		int diameter;
		int height;

	}

	public boolean Isbalanced() {
		return Isbalanced(this.root).Isbal;
	}

	private balPair Isbalanced(Node node) {
		if (node == null) {

			balPair rv = new balPair();
			rv.height = -1;
			rv.Isbal = true;
			return rv;
		}

		balPair lp = this.Isbalanced(node.left);
		balPair rp = this.Isbalanced(node.right);

		balPair bt = new balPair();
		bt.height = Math.max(lp.height, rp.height) + 1;
		if (lp.Isbal && rp.Isbal && Math.abs(lp.height - rp.height) <= 1) {

			bt.Isbal = true;
		} else {

			bt.Isbal = false;
		}

		return bt;

	}

	private class balPair {

		int height;
		boolean Isbal;

	}

	// is binary search tree
	public boolean IsBst() {
		BstPair bspt = IsBst(this.root);

		System.out.println(bspt.largestBSTRoot.data);
		System.out.println(bspt.size);

		return bspt.IsBST;
	}

	private BstPair IsBst(Node node) {
		if (node == null) {
			BstPair rv = new BstPair();
			rv.IsBST = true;
			rv.max = Integer.MIN_VALUE;
			rv.min = Integer.MAX_VALUE;
			rv.largestBSTRoot = null;
			rv.size = 0;
			return rv;
		}

		BstPair l = IsBst(node.left);
		BstPair r = IsBst(node.right);

		BstPair bs = new BstPair();
		bs.min = Math.min(l.min, Math.min(r.min, node.data));
		bs.max = Math.max(l.max, Math.max(r.max, node.data));
		bs.size = l.size + r.size + 1;
		bs.largestBSTRoot = node;
		if (l.IsBST && r.IsBST) {
			if (node.data < r.min && node.data > l.max) {
				bs.IsBST = true;
			} else {
				bs.IsBST = false;
			}

		} else {
			bs.IsBST = false;
		}

		return bs;

	}
	// is BSt iterative by utk

	public static boolean PrineLevelOrder(Node root) {

		LinkedList<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {

			Node temp = queue.remove();

			System.out.println(temp.data);

			PrineLevelOrder(temp.left);
			PrineLevelOrder(temp.right);

		}

		return false;
	}

	private class BstPair {

		int max;
		int min;
		boolean IsBST;
		int size;
		Node largestBSTRoot;

	}

	// PostOrder

	public void postOrder() {

		postOrder(this.root);
	}

	private void postOrder(Node node) {

		if (node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data + " ");

	}

	// Preorder

	public void preOrder() {
		PreOrder(this.root);
	}

	private void PreOrder(Node node) {
		if (node == null) {
			return;
		}

		System.out.println(node.data + " ");
		PreOrder(node.left);
		PreOrder(node.right);

	}

	// Inorder

	public void InOrder() {
		InOrder(this.root);
	}

	private void InOrder(Node node) {
		if (node == null) {
			return;
		}

		InOrder(node.left);
		System.out.println(node.data + " ");
		InOrder(node.right);

	}

	public void levelOrder() {

		LinkedList<Node> queue = new LinkedList<>();
		queue.addLast(root);
		while (!queue.isEmpty()) {

			Node temp = queue.removeFirst();

			System.out.println(temp.data);

			if (temp.left != null) {
				queue.addLast(temp.left);
			}
			if (temp.right != null) {
				queue.addLast(temp.right);
			}

		}
		System.out.print("");

	}

	public void preorderI() {
		LinkedList<orderPair> stack = new LinkedList<>();

		stack.addFirst(new orderPair(this.root));
		while (!stack.isEmpty()) {
			orderPair temp = stack.getFirst();

			if (temp.selfDone == false) {
				System.out.println(temp.node.data);
				temp.selfDone = true;
			} else if (temp.leftDone == false) {
				orderPair lp = new orderPair(temp.node.left);
				if (temp.node.left != null) {
					stack.addFirst(lp);
				}
				temp.leftDone = true;

			} else if (temp.rightDone == false) {
				orderPair np = new orderPair(temp.node.right);

				if (temp.node.right != null) {
					stack.addFirst(np);
				}
				temp.rightDone = true;

			} else {
				stack.removeFirst();
			}

		}
	}

	public void postorderI() {
		LinkedList<orderPair> stack = new LinkedList<>();

		stack.addFirst(new orderPair(this.root));
		while (!stack.isEmpty()) {
			orderPair temp = stack.getFirst();

			if (temp.leftDone == false) {
				orderPair lp = new orderPair(temp.node.left);
				if (temp.node.left != null) {
					stack.addFirst(lp);
				}
				temp.leftDone = true;

			} else if (temp.rightDone == false) {
				orderPair np = new orderPair(temp.node.right);

				if (temp.node.right != null) {
					stack.addFirst(np);
				}
				temp.rightDone = true;

			} else if (temp.selfDone == false) {
				System.out.println(temp.node.data);
				temp.selfDone = true;
			} else {
				stack.removeFirst();
			}

		}
	}

	public void inorderI() {
		LinkedList<orderPair> stack = new LinkedList<>();

		stack.addFirst(new orderPair(this.root));
		while (!stack.isEmpty()) {
			orderPair temp = stack.getFirst();

			if (temp.leftDone == false) {
				orderPair lp = new orderPair(temp.node.left);
				if (temp.node.left != null) {
					stack.addFirst(lp);
				}
				temp.leftDone = true;

			} else if (temp.selfDone == false) {
				System.out.println(temp.node.data);
				temp.selfDone = true;
			} else if (temp.rightDone == false) {
				orderPair np = new orderPair(temp.node.right);

				if (temp.node.right != null) {
					stack.addFirst(np);
				}
				temp.rightDone = true;

			} else {
				stack.removeFirst();
			}

		}
	}

	private class orderPair {
		Node node;
		boolean selfDone;
		boolean rightDone;
		boolean leftDone;

		orderPair(Node node) {
			this.node = node;
		}

	}

	// MULTISOLVER

	public void multiSolver(int data) {
		HeapMover rb = new HeapMover();
		this.multiSolver(this.root, rb, 0, data);

		System.out.println("Size = " + rb.size);
		System.out.println("Max = " + rb.max);
		System.out.println("Min = " + rb.min);
		System.out.println("Height = " + rb.height);
		System.out.println("Found = " + rb.found);

		System.out.println("Pred = " + (rb.pred == null ? "null" : rb.pred.data));
		System.out.println("Succ = " + (rb.succ == null ? "null" : rb.succ.data));
		System.out.println("JL = " + (rb.justLarger == null ? "null" : rb.justLarger.data));
	}

	private void multiSolver(Node node, HeapMover rb, int depth, int data) {
		// bse case

		if (node == null) {
			return;
		}

		// work

		rb.size++;

		if (node.data > rb.max) {
			rb.max = node.data;
		}

		if (node.data < rb.min) {
			rb.min = node.data;
		}

		if (depth > rb.height) {
			rb.height = depth;
		}

		rb.prev = rb.curr;

		if (rb.found == true && rb.succ == null) {
			rb.succ = node;
		}

		if (node.data == data) {
			rb.pred = rb.prev;
			rb.found = true;
		}

		rb.curr = node;

		if (node.data > data) {
			if (rb.justLarger == null) {
				rb.justLarger = node;
			} else {
				if (node.data < rb.justLarger.data) {
					rb.justLarger = node;
				}
			}
		}

		// work

		// for (Node child : node) {
		// this.multiSolver(child, rb, depth + 1, data);
		// }

		this.multiSolver(node.right, rb, depth + 1, data); // right
		this.multiSolver(node.left, rb, depth + 1, data); // left
	}

	private class HeapMover {
		int size = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int height = 0;
		boolean found = false;

		Node curr = null;
		Node prev = null;

		Node pred = null;
		Node succ = null;

		Node justLarger = null;
		int mmaa = Integer.MIN_VALUE;
	}

	public boolean isCompleteBT() {
		return isComplateBT(this.root);
	}

	private boolean isComplateBT(Node node) {

		Queue<Node> queue = new LinkedList<>();
		boolean b = false;
		queue.add(node);

		while (!queue.isEmpty()) {
			Node temp = queue.remove();

			if (temp.left != null) {
				if (b) {
					return false;
				}
				queue.add(temp.left);
			} else {
				b = true;
			}

			if (temp.right != null) {
				if (b) {
					return false;

				}
				queue.add(temp.right);

			} else {
				b = true;
			}
		}
		return true;
	}

	// geeks for geeks

	// private class tNode
	// {
	// int data;
	// tNode left, right;
	//
	// tNode(int item)
	// {
	// data = item;
	// left = right = null;
	// }
	// }

	public void Morris() {
		MorrisTraversal(this.root);
	}

	private void MorrisTraversal(Node root) {
		Node current, pre;
		// Node current = new Node();
		// Node pre = new Node();
		if (root == null)
			return;

		current = root;
		while (current != null) {
			if (current.left == null) {
				System.out.print(current.data + " ");
				current = current.right;
			} else {
				/* Find the inorder predecessor of current */
				pre = current.left;
				while (pre.right != null && pre.right != current)
					pre = pre.right;

				/* Make current as right child of its inorder predecessor */
				if (pre.right == null) {
					pre.right = current;
					current = current.left;
				}

				/*
				 * Revert the changes made in if part to restore the original tree i.e.,fix the
				 * right child of predecssor
				 */
				else {
					pre.right = null;
					System.out.print(current.data + " ");
					current = current.right;
				} /* End of if condition pre->right == NULL */

			} /* End of if condition current->left == NULL */

		} /* End of while */

	}

	// Perfect Binary Tree Specific Level Order Traversal | Set 2

	public void printSpecificLevelOrder() {

		Stack<Node> stack = new Stack<>();
		stack.push(this.root);

		if (this.root.left != null) {
			stack.push(this.root.right);
			stack.push(this.root.left);
		}
		if (this.root.left.left != null) {
			printSpecificLevelOrderUtil(this.root, stack);
		}

		while (!stack.isEmpty()) {
			System.out.println(stack.peek().data);
			stack.pop();
		}
	}

	private void printSpecificLevelOrderUtil(Node root, Stack<Node> stack) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root.left);
		queue.add(root.right);

		while (!queue.isEmpty()) {
			Node first;
			Node second;
			first = queue.peek();
			queue.poll();
			second = queue.peek();
			queue.poll();
			stack.push(second.left);
			stack.push(first.right);
			stack.push(second.right);
			stack.push(first.left);

			if (first.left.left != null) {
				queue.add(first.right);
				queue.add(second.left);
				queue.add(first.right);
				queue.add(second.left);
			}
		}

	}
	// Boundary Traversal of binary tree

	public void printleafNOde() {
		leafNode(this.root);
	}

	private void leafNode(Node node) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			System.out.println(node.data);
		}

		leafNode(node.left);
		leafNode(node.right);

	}

	public void leftNode() {
		leftNode(this.root.left);
	}

	private void leftNode(Node node) {

		if (node == null) {
			return;
		}

		if (node.left != null) {
			System.out.println(node.data + " ");
			leftNode(node.left);
		} else if (node.right != null) {
			System.out.println(node.data);
			leftNode(node.right);
		}

	}

	public void rightNode() {
		rightNode(this.root.right);

	}

	private void rightNode(Node node) {
		if (node == null) {
			return;
		}
		if (node.right != null) {
			rightNode(node.right);
			System.out.println(node.data);
		} else if (node.left != null) {
			rightNode(node.left);
			System.out.println(node.data);
		}

	}

	public void printboundary() {

		printboundary(this.root);

	}

	private void printboundary(Node node) {

		System.out.println(node.data);

		leftNode();

		// printleafNOde();

		rightNode();

	}

	// Print a Binary Tree in Vertical Order | Set 2 (Hashmap based Method)

	private class Values {
		int max, min;
	}

	Values val = new Values();

	public void findMinMax() {
		findMinMax(this.root, val, 0);
		System.out.println(val.min);
		System.out.println(val.max);
	}

	private void findMinMax(Node node, Values vpair, int hd) {
		// Base case
		if (node == null)
			return;

		// Update min and max
		if (hd < vpair.min)
			vpair.min = hd;
		else if (hd > vpair.max)
			vpair.max = hd;

		// Recur for left and right subtrees
		findMinMax(node.left, vpair, hd - 1);
		findMinMax(node.right, vpair, hd + 1);
	}

	public void verticalOrder() {
		verticalOrder(this.root);
	}

	private void verticalOrder(Node node) {
		// Find min and max distances with resepect to root
		findMinMax(node, val, 0);

		// Iterate through all possible vertical lines starting
		// from the leftmost line and print nodes line by line
		for (int line_no = val.min; line_no <= val.max; line_no++) {
			printVerticalLine(node, line_no, 0);
			System.out.println("");
		}
	}

	private void printVerticalLine(Node node, int line_no, int i) {
		if (node == null) {
			return;
		}
		if (i == line_no) {
			System.out.print(node.data);
		}

		printVerticalLine(node.left, line_no, i - 1);
		printVerticalLine(node.right, line_no, i + 1);

	}

	// Print a Binary Tree in Vertical Order | Set 2 (Hashmap based Method)

	public void printvertical() throws Exception {
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>(5);
		int hd = 0;
		getverticalorderhm(this.root, hd, hm);
		hm.display();

	}

	private void getverticalorderhm(Node node, int hd, HashMap<Integer, ArrayList<Integer>> hm) throws Exception {
		if (node == null) {
			return;
		}

		ArrayList<Integer> a = hm.get(hd);

		if (a == null) {
			a = new ArrayList<>();
			a.add(node.data);
		} else {
			a.add(node.data);
		}
		hm.put(hd, a);

		getverticalorderhm(node.left, hd - 1, hm);
		getverticalorderhm(node.right, hd + 1, hm);
	}

	// Diagonal Traversal of Binary Tree

	public void printDiagonal() throws Exception {
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>(5);
		int hd = 0;
		getprintDiagonalhm(this.root, hd, hm);

		hm.display();

	}

	private void getprintDiagonalhm(Node node, int hd, HashMap<Integer, ArrayList<Integer>> hm) throws Exception {
		if (node == null) {
			return;
		}

		ArrayList<Integer> a = hm.get(hd);

		if (a == null) {
			a = new ArrayList<>();
			a.add(node.data);
		} else {
			a.add(node.data);
		}
		hm.put(hd, a);

		getprintDiagonalhm(node.left, hd + 1, hm);
		getprintDiagonalhm(node.right, hd, hm);
	}

	// stepwise ho nahi paya ye x

	public void stepWise() {
		stepWise(this.root);
	}

	private void stepWise(Node node) {
		if (node == null) {
			return;
		}

		stepWise(node.left);
		stepWise(node.right);

		if (node.left == null) {
			node.left = node.right;
		} else {
			node.left.right = node.right;
		}

		// this.root.right=null ;
	}

	// mpracrice \\

	public void decode() {
		decode(this.root, "1001011");
	}

	private void decode(Node root, String S) {
		StringBuilder sb = new StringBuilder();
		Node temp = root;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == 1) {
				temp = temp.right;
			} else {
				temp = temp.left;
			}
			if (temp.left == null && temp.right == null) {
				sb.append(temp.data);
			}

			temp = root;

		}
		System.out.print(sb);

	}

	public int sumofSubtree() {
		HeapMover hm = new HeapMover();
		int k = sumofsubtree(this.root, 00, hm);
		System.out.println(hm.mmaa);
		return k;
	}

	private int sumofsubtree(Node node, int ans, HeapMover hm) {

		if (node == null) {
			return 0;
		}

		int val = node.data;
		// int csum =val + sumofsubtree(node.left,ans) + sumofsubtree(node.right,ans);

		int lsum = sumofsubtree(node.left, ans, hm);
		int rsum = sumofsubtree(node.right, ans, hm);

		int aans = Math.max(Math.max(rsum, lsum), val + lsum + rsum);
		if (aans >= hm.mmaa) {
			hm.mmaa = aans;
		}
		return lsum + rsum + val;
	}
	
	public void sumofSubtree2() {
		int ans = Integer.MIN_VALUE;
		int  k = sumofSubtree2(this.root ,ans);
		System.out.println(ans + "real ans");
		System.out.println(k);
	}

	private int sumofSubtree2(Node node, int ans) {
		
		if(node==null) {
			return 0;
			
		}
		
		int left  = sumofSubtree2(node.left, ans);
		int right = sumofSubtree2(node.right, ans);
		
		
		
		int ans1 = node.data + left+right;
		ans=Math.max(ans, ans1);
		return ans1;
		
	}
	
	// tree const from inorder and levelorder 
	public class ctNode{
		int data ;
		Node parent;
		boolean isLeft;
		
		ctNode(int data, Node parent, boolean isLeft){
			this.data = data;
			this.parent = parent;
			this.isLeft = isLeft;
		}
	}
	
	public class TF{
		int index;
		boolean isChecked ;
		TF(int index, boolean isChecked){
			this.index = index;
			this.isChecked = isChecked;
		}
	}
	
	public void constructTree(int [] in, int [] level) {
		HashMap<Integer, TF> hm = new HashMap<>(100);
		this.root = constructTree(in, level,hm,1);
	}

	private Node constructTree(int[] in, int[] level, java.util.HashMap<Integer, TF> hm,int l) {
		// TODO Auto-generated method stub
		//Filling HashMap
		for(int i=0 ; i<in.length ;i++) {
			hm.put(in[i], new TF(i,false));
		}
		
		Queue<ctNode> q = new LinkedList<>();
		q.add(new ctNode(level[0],null,false));	
		
		while(!q.isEmpty()) {
			ctNode removedNode = q.remove();
			
			//update hash map
			TF hmval = hm.get(removedNode.data);
			hmval.isChecked = true;
			hm.put(removedNode.data, hmval);
			
			// make new node 
			Node newNode = new Node();
			newNode.data = removedNode.data;
			
			//a addig node to parent 
			if(removedNode.parent ==null) {
				this.root = newNode;
			}else {
				boolean isLeft = removedNode.isLeft;
				Node parent = removedNode.parent;
				if(isLeft) {
					parent.left = newNode;
				}else if(!isLeft) {
					parent.right = newNode;
				}
			}
			
			
			
			
			
		}
		
		
		return null;
	}

}
