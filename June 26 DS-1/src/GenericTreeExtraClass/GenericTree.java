package GenericTreeExtraClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



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
		printAtDepth(this.root,  num);
	}

	private void printAtDepth(Node node,  int depth) {
		if(node ==null ||depth<0) {
			return;
		}
		
		if(depth==0) {
			System.out.print(node.data + " ");
			System.out.println();
			return;
		}
		
		
		for (Node child : node.children) {
			printAtDepth(child,  depth - 1);
		}

	}

	public boolean IsMirrorIso() {
		return IsMirrorIso(this.root);
	}
	// iss krna h bhai aacha quetion ye walla

	private boolean IsMirrorIso(Node node) {

		for (Node child : node.children) {
			IsMirrorIso(child);
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
		ziczac(this.root);
	}

	private void ziczac(Node node) {

		LinkedList<Node> queue1 = new LinkedList<>(); // cl
		LinkedList<Node> stack = new LinkedList<>();// nl
		queue1.addLast(this.root);
		int count = 0;

		while (!queue1.isEmpty()) {

			Node nod = queue1.removeFirst();

			// print
			System.out.print(nod.data + "  ");
			if (count % 2 == 0) {
				for (int i = 0; i < node.children.size(); i++) {
					stack.addFirst(nod.children.get(i));
				}
			} else {
				for (int i = node.children.size() - 1; i >= 0; i--) {
					stack.addFirst(nod.children.get(i));
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

}
