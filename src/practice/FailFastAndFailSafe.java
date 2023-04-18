package practice;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class FailFastAndFailSafe {
	public static void main(String[] args) {
		test(new ArrayList<>());
		test(new CopyOnWriteArrayList<>());
	}

	private static void test(List<Integer> list) {
		IntStream.range(1, 5).forEach(list::add);
		
		System.out.println("-------"+list.getClass().getName()+"-------");
		System.out.println("Before: "+list);
		try {
			failTest1(list);
		} catch (ConcurrentModificationException cme) {
			System.out.println(cme);
		}
		System.out.println("After: "+list);
		
		System.out.println("Before: "+list);
		try {
			failTest2(list);
		} catch (ConcurrentModificationException cme) {
			System.out.println(cme);
		}
		System.out.println("After: "+list);
	}

	private static void failTest1(List<Integer> list) {
		for (Integer i : list) {
			System.out.print(i+", ");
			list.add(5);
		}
		System.out.println();
	}

	private static void failTest2(List<Integer> list) {
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			System.out.print(it.next()+", ");
			list.add(6);
		}
		System.out.println();
	}

	
	
}
