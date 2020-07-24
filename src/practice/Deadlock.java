package practice;

public class Deadlock {
	Object o1 = new Object();
	Object o2 = new Object();

	public static void main(String[] args) {
		new Deadlock().testThread();
	}

	private void testThread() {
		Thread t1 = new Thread(() -> {
			synchronized (o1) {
				try {
					Thread.sleep(1000l);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("inside thread 1");
				}
			}
		});
		Thread t2 = new Thread(() -> {
			synchronized (o2) {
				synchronized (o1) {
					System.out.println("inside thread 2");
				}
			}
		});
		t1.start();
		t2.start();
	}

}
