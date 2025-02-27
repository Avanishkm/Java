package ExtraClassesForDs.GenericTreeExtraClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//import ExtraClassesForDs.BinaryTreeExtraClass.BinaryTree.TraversalPair;

//import July3.GenericTree.Node;

public class GenericTree {

	private class Node {
		int data;
		ArrayList<Node> children = new ArrayList<>();
	}

	private Node root;
	private int size;

	public GenericTree() {
		Scanner scn = new Scanner(System.in);
		this.root = takeInput(null, -1, scn);
	}

	private Node takeInput(Node parent, int children, Scanner scn) {

		if (parent == null) {
			System.out.println("parent node?");
		} else {
			System.out.println("Enter the   " + children + "th child of " + parent.data);
		}

		//

		int cdata = scn.nextInt();
		Node child = new Node();
		size++;
		child.data = cdata;

		System.out.println("Enter the no. of child of " + cdata);
		int alx = scn.nextInt();

		for (int i = 0; i < alx; i++) {
			Node gcgild = takeInput(child, i, scn);
			child.children.add(gcgild);
		}

		return child;

	}

	public void display() {
		display(this.root);
	}

	private void display(Node node) {

		String str = node.data + "=>";

		for (Node child : node.children) {
			str += child.data + ",";

		}
		System.out.println(str);

		for (Node child : node.children) {
			display(child);
		}

	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int size() {
		return this.size;
	}

	public int size2() {
		return size2(this.root);
	}

	private int size2(Node node) {
		int size = 0;
		for (Node child : node.children) {
			int k = size2(child);
			size += k;
		}
		return size + 1;
	}

	public int max() {

		return max(this.root);
	}

	private int max(Node node) {
		int cmax = node.data;
		for (Node child : node.children) {

			cmax = Math.max(this.max(child), cmax);
		}
		return cmax;
	}

	public int height() {
		return height(this.root);
	}

	private int height(Node node) {
		int height = 0;
		int cheight = 0;
		for (Node child : node.children) {
			cheight = height(child);

			height = Math.max(cheight, height);
		}
		return 1 + height;

	}

	public boolean find(int data) {

		return find(this.root, data);

	}

	private boolean find(Node node, int data) {
		if (node.data == data) {
			return true;
		} else {

			for (Node child : node.children) {
				if (find(child, data)) {
					return true;
				}
			}
		}

		return false;

	}

	public void mirror() {

		mirror(this.root);
	}

	private void mirror(Node node) {

		for (Node child : node.children) {
			mirror(child);
		}

		int li = 0;
		int ri = node.children.size() - 1;

		while (li < ri) {

			Node left = node.children.get(li);
			Node right = node.children.get(ri);

			node.children.set(li, right);
			node.children.set(ri, left);

			li++;
			ri--;
		}

	}

	public void printAtDepth(int num) {
		printAtDepth(this.root, num);
	}

	private void printAtDepth(Node node, int depth) {
		if (node == null || depth < 0) {
			return;
		}

		if (depth == 0) {
			System.out.print(node.data + " ");
			System.out.println();
			return;
		}

		for (Node child : node.children) {
			printAtDepth(child, depth - 1);
		}

	}

	public void preOrder() {
		preOrder(this.root);
	}

	private void preOrder(Node node) {
		System.out.print(node.data);
		for (Node child : node.children) {
			preOrder(child);
		}

	}

	public void postOrder() {
		postOrder(this.root);
	}

	private void postOrder(Node node) {

		for (Node child : node.children) {
			postOrder(child);
		}

		System.out.print(node.data);

	}

	public void preOrderI() {

		LinkedList<TraversalPair> stack = new LinkedList<>();

		TraversalPair rp = new TraversalPair();
		rp.node = root;

		stack.add(rp);

		while (!stack.isEmpty()) {

			TraversalPair get = stack.getFirst();

			if (get.self == false) {
				get.self = true;
				if (get.node != null) {
					System.out.println(get.node.data);
				}
			} else if (get.sofardone < get.node.children.size()) {

				// condition for null pointer
				for (int i = 0; i < get.node.children.size(); i++) {
					TraversalPair np = new TraversalPair();
					np.node = get.node.children.get(i);
					stack.addFirst(np);

				}

				get.sofardone++;

			} else {
				stack.removeFirst();
			}

		}

	}

	private class TraversalPair {
		Node node;
		int sofardone = 0;
		boolean self;
	}

	public void levelOrder() {
		levelOrder(this.root);
	}

	private void levelOrder(Node node) {

		LinkedList<Node> queue = new LinkedList<>();

		queue.addLast(this.root);

		while (!queue.isEmpty()) {
			Node rv = queue.removeFirst();
			System.out.print(rv.data + " ");

			for (Node child : rv.children) {
				queue.addLast(child);

			}

		}

	}

	public void levelOrderLineWise() {
		levelOrderLineWise(this.root);
	}

	private void levelOrderLineWise(Node node) {

		LinkedList<Node> queue1 = new LinkedList<>();
		LinkedList<Node> queue2 = new LinkedList<>();
		queue1.addLast(this.root);

		while (!queue1.isEmpty()) {
			Node rv = queue1.removeFirst();
			System.out.print(rv.data + " ");

			for (Node child : rv.children) {
				queue2.addLast(child);
			}

			if (queue1.size() == 0) {

				queue1 = queue2;
				queue2 = new LinkedList<>();
				System.out.println();

			}
		}

	}

	public void ziczac() {

		LinkedList<Node> queue1 = new LinkedList<>(); // cl
		LinkedList<Node> stack = new LinkedList<>();// nl
		queue1.addLast(this.root);
		int count = 0;

		while (!queue1.isEmpty()) {

			Node node = queue1.removeFirst();

			// print
			System.out.print(node.data + "  ");
			if (count % 2 == 0) {
				for (int i = 0; i < node.children.size(); i++) {
					stack.addFirst(node.children.get(i));
				}
			} else {
				for (int i = node.children.size() - 1; i >= 0; i--) {
					stack.addFirst(node.children.get(i));
				}
			}

			if (queue1.size() == 0) {
				count++;
				queue1 = stack;
				stack = new LinkedList<>();
				System.out.println();
			}

		}
	}

	// Multi solver

	public void multisolver(int data) {
		HeapMover mover = new HeapMover();
		this.multisolver(this.root, mover, data, 0);

		System.out.println("SIZE =" + mover.size);
		System.out.println("Max =" + mover.max);
		System.out.println("Min =" + mover.min);
		System.out.println("Height =" + mover.height);
		System.out.println("Found =" + mover.found);

		System.out.println("Pred = " + (mover.pred == null ? "null" : mover.pred.data));
		System.out.println("Succ = " + (mover.succ == null ? "null" : mover.succ.data));
		System.out.println("JL = " + (mover.justlarger == null ? "null" : mover.justlarger.data));
	}

	private void multisolver(Node node, HeapMover mover, int data, int depth) {

		mover.size++;
		mover.prev = mover.curr;
		mover.curr = node;

		if (mover.found == true && mover.succ == null) {
			mover.succ = mover.curr;
		}

		if (node.data == data) {
			mover.found = true;
			mover.pred = mover.prev;
		}

		if (node.data > mover.max) {
			mover.max = node.data;
		}
		if (node.data < mover.min) {
			mover.min = node.data;
		}
		if (depth > mover.height) {
			mover.height = depth;
		}

		// just larger

		if (node.data > data) {
			if (mover.justlarger == null) {
				mover.justlarger = node;
			} else {
				if (mover.justlarger.data > node.data) {
					mover.justlarger = node;
				}
			}
		}

		// successor

		if (node.data == data) {
			node.children.get(0);
		}

		for (Node child : node.children) {
			multisolver(child, mover, data, depth + 1);
		}

	}

	private class HeapMover {
		int size = 0;
		int height;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		boolean found;

		Node justlarger;
		Node succ;
		Node pred;

		Node prev;
		Node curr;
	}

	public int kthSmallest(int num) {
		ArrayList<Integer> list = new ArrayList<>();
		kthSmallest(root, list);
		Collections.sort(list);
		return list.get(num);

	}

	private void kthSmallest(Node node, ArrayList<Integer> list) {

		list.add(node.data);

		for (Node child : node.children) {
			kthSmallest(child, list);
		}

	}

	public int kthsmallest1(int k) {

		int ks = Integer.MIN_VALUE;

		for (int i = 0; i < k; i++) {
			HeapMover mover = new HeapMover();
			kthsmallest1(root, mover, ks);
			ks = mover.justlarger.data;

		}

		return ks;
	}

	private void kthsmallest1(Node node, HeapMover mover, int data) {
		if (node.data > data) {
			if (mover.justlarger == null) {
				mover.justlarger = node;
			} else {
				if (mover.justlarger.data < node.data) {
					mover.justlarger = node;
				}
			}
		}

		for (Node child : node.children) {
			kthsmallest1(child, mover, data);
		}

	}

	public void removeLeaf() {
		removeLeaf(this.root);
	}

	private void removeLeaf(Node node) {

		for (int i = 0; i < node.children.size(); i++) {

			Node child = node.children.get(i);

			if (child.children.size() == 0) {
				node.children.remove(i);
				i--;
			}

		}

		for (Node child : node.children) {
			removeLeaf(child);
		}

	}

	public void linearise() {
		linearise(this.root);
	}

	private void linearise(Node node) {

		for (Node child : node.children) {

			linearise(child);
		}

		while (node.children.size() > 1) {
			Node tail = gettail(node);
			Node rm = node.children.remove(1);
			tail.children.add(rm);
		}

	}

	private Node gettail(Node node) {

		if (node.children.size() == 0) {
			return node;
		}

		return gettail(node.children.get(0));

	}

	// public boolean IsMirrorIso() {
	// return IsMirrorIso(this.root);
	// }
	// iss krna h bhai aacha quetion ye walla

	// private boolean IsMirrorIso(Node node) {
	//
	// for (Node child : node.children) {
	// IsMirrorIso(child);
	// }

	// }

	public boolean isMirrorImage() {
		return isMirrorImage(this.root, this.root);
	}

	private boolean isMirrorImage(Node lnode, Node rnode) {

		if (lnode.children.size() != rnode.children.size()) {
			return false;
		}

		boolean rv = true;

		for (int i = 0; i < lnode.children.size() / 2; i++) {
			Node lchild = lnode.children.get(i);
			Node rchild = lnode.children.get(lnode.children.size() - 1 - i);

			if (isMirrorImage(lchild, rchild) == false) {
				rv = false;
			}
		}
		return rv;

	}

	public void flattentheTree() {
		flattentheTree(this.root);
	}

	private void flattentheTree(Node node) {

		for (Node child : node.children) {
			flattentheTree(child);
		}

		ArrayList<Node> al = new ArrayList<>();

		for (int i = 0; i < node.children.size(); i++) {

			al.add(node.children.get(i));
			al.addAll(node.children.get(i).children);
			node.children.get(i).children = new ArrayList<>();

		}

		node.children = al;
		// mohitbaliyan98@gmail.com // mail him today otherwise ------

	}
	
	

}
