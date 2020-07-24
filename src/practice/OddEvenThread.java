package practice;

public class OddEvenThread {

	public static void main(String[] args) {
		new OddEvenThread().testThread(20);
	}

	private void testThread(int num) {
		Thread oddThread = new Thread(() -> {
			synchronized (this) {
				for (int i = 1; i <= num; i = i + 2) {
					System.out.println("Odd:" + i);
					try {
						wait();
						notify();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread evenThread = new Thread(() -> {
			synchronized (this) {
				for (int i = 2; i <= num; i = i + 2) {
					System.out.println("Even:" + i);
					try {
						notify();
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
		oddThread.setPriority(10);
		evenThread.setPriority(1);
		oddThread.start();
		evenThread.start();
	}

}
