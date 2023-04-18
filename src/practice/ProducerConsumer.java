package practice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class ProducerConsumer {

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
		Thread producer = new Thread(() -> {
			IntStream.range(1, 51).forEach(i -> {
				try {
					System.out.println("producing object: " + i);
					queue.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		});
		Thread consumer = new Thread(() -> {
			IntStream.range(1, 51).forEach(i -> {
				try {
					System.out.println("consuming object: " + queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		});
		producer.start();
		consumer.start();
	}

}
