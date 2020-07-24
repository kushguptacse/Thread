package practice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallablePractice implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(1000l);
		return "hello from call method";
	}

	public static void main(String[] args) {
		try {
			ExecutorService service = Executors.newFixedThreadPool(2);
			Future<String> f = service.submit(new CallablePractice());
			System.out.println(f.get());
			// get is blocking and hence next line will not reach till thread finishes.
			System.out.println("next line");
			service.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
