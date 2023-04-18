package practice;

public class Deadlock {
	Object o1 = new Object();
	Object o2 = new Object();

	public static void main(String[] args) {
		new Deadlock().testThread();
	}

	private void testThread() {
		Thread t1 = new Thread(() -> {
			System.out.println("trying to acquire lock on o1 by " + Thread.currentThread().getName());
			synchronized (o1) {
				try {
					System.out.println("acquired lock on o1 by " + Thread.currentThread().getName());
					Thread.sleep(1000l);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println("trying to acquire lock on o2 by " + Thread.currentThread().getName());
				synchronized (o2) {
					System.out.println("acquired lock on o2 by " + Thread.currentThread().getName());
				}
			}
		});
		Thread t2 = new Thread(() -> {
			System.out.println("trying to acquire lock on o2 by " + Thread.currentThread().getName());
			synchronized (o2) {
				try {
					System.out.println("acquired lock on o2 by " + Thread.currentThread().getName());
					Thread.sleep(1000l);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println("trying to acquire lock on o1 by " + Thread.currentThread().getName());
				synchronized (o1) {
					System.out.println("acquired lock on o1 by " + Thread.currentThread().getName());
				}
			}
		});
		t1.start();
		t2.start();
	}

}
