package ExtraClassesForDs.Generics;

import java.util.Comparator;

public class clientGenericHeap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Car[] cars = new Car[5];
		cars[3] = new Car(2000, 20000, "A");
		cars[2] = new Car(300, 45000, "B");
		cars[1] = new Car(4000, 30000, "C");
		cars[4] = new Car(7000, 25000, "D");
		cars[0] = new Car(500, 50000, "E");
		
		GenericHeap<Car> heap = new GenericHeap<>(null);
		heap.add(cars[0]);
		heap.add(cars[1]);
		heap.add(cars[2]);
		heap.add(cars[3]);
		heap.add(cars[4]);
		
		heap.display();
		
		System.out.println(heap.remove());
		heap.display();
		
		System.out.println(heap.remove());
		heap.display();
		
		System.out.println(heap.remove());
		heap.display();
		
		System.out.println(heap.remove());
		heap.display();
		
		System.out.println(heap.remove());
		heap.display();
	}
	
	private static class Car implements Comparable<Car> {
		int speed;
		int price;
		String name;

		Car(int speed, int price, String name) {
			this.speed = speed;
			this.price = price;
			this.name = name;
		}

		@Override
		public int compareTo(Car o) {
			// TODO Auto-generated method stub
			return this.name.compareTo(o.name);
		}

		@Override
		public String toString() {
			return "[" + this.name + "@" + this.speed + "_" + this.price + "]";
		}

		private static class CarSpeedComparator implements Comparator<Car>{
			@Override
			public int compare(Car o1, Car o2) {
				// TODO Auto-generated method stub
				return o1.speed - o2.speed;
			}
		}
		
		private static class CarPriceComparator implements Comparator<Car>{
			@Override
			public int compare(Car o1, Car o2) {
				// TODO Auto-generated method stub
				return o2.price - o1.price;
			}
		}
		
		private static class CarNameComparator implements Comparator<Car>{
			@Override
			public int compare(Car o1, Car o2) {
				// TODO Auto-generated method stub
				return o1.name.compareTo(o2.name);
			}
		}
	}

}
