package July8;

public class HeapClient {

	public static void main (String [] agrs) {
	
		Heap h = new Heap();
		System.out.println(h.isEmpty());
		h.add(65);
		h.add(60);
		System.out.println(h.isEmpty());
		h.add(70);
		h.add(10);
		h.add(80);
		h.display();
		System.out.println(h.size() + " size");
		System.out.println(h.remove());
		
		h.display();
		System.out.println(h.size() + " Size");
		System.out.println(h.remove());
		
		h.display();
		System.out.println(h.size());
		System.out.println(h.remove());
		
	}
	
	
	
}
