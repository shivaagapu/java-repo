import java.util.concurrent.CompletableFuture;

public class RealTimeCompatibleFuture {

    private static String getAccountDetails() {
        try {
            Thread.sleep(2000); // Simulate some work
        } catch (InterruptedException e) {
            System.out.println("Future was interrupted");
        }
        return "Account Details";
    }

    private static String getCustomerDetails() {
        try {
            Thread.sleep(2000); // Simulate some work
        } catch (InterruptedException e) {
            System.out.println("Future was interrupted");
        }
        return "Customer Details";
    }

    private static String getTransactionDetails() {
        try {
            Thread.sleep(2000); // Simulate some work
        } catch (InterruptedException e) {
            System.out.println("Future was interrupted");
        }
        return "Transaction Details";
    }
    public static void main(String[] args) {
        // Simulating a real-time compatible future using a simple thread
        // Thread futureThread = new Thread(() -> {
        //     try {
        //         Thread.sleep(2000); // Simulate some work
        //         System.out.println("Future completed with result: Account Details");
        //     } catch (InterruptedException e) {
        //         System.out.println("Future was interrupted");
        //     }
        // });

        // futureThread.start();

        // System.out.println("Doing other work while waiting for the future to complete...");

        // try {
        //     futureThread.join(); // Wait for the future to complete
        // } catch (InterruptedException e) {
        //     System.out.println("Main thread was interrupted while waiting for the future to complete");
        // }

        //get time before starting the future
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> getAccountDetails());
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> getCustomerDetails());
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> getTransactionDetails());
        System.out.println("Doing other work while waiting for the future to complete...");
        String result = future.join();
        String result2 = future2.join();
        String result3 = future3.join();
        long endTime = System.currentTimeMillis();
        System.out.println("time taken to complete the future: " + (endTime - startTime) + " milliseconds");
        System.out.println(result);
        System.out.println(result2);        
        System.out.println(result3);
    }
}
