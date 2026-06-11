import java.util.concurrent.CompletableFuture;
public class CompatibleFutureEx {

    public static void main(String[] args) {

        // CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello, World!");
        // future.thenAccept(result -> System.out.println("Result: " + result));

        // CompletableFuture<String> future =
        //         CompletableFuture.supplyAsync(() -> {
        //             try {
        //                 Thread.sleep(2000);
        //             } catch (Exception e) {
        //             }
        //             return "Account Details";
        //         });

        // System.out.println("Doing other work...");

        // String result = future.join();

        // System.out.println(result);

        // running multiple tasks in parallel
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            return "Task 1 Result";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            return "Task 2 Result";
        });
        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> {
            return result1 + " + " + result2;
        });
        System.out.println("Doing other work while tasks are running...");
        long startTime = System.currentTimeMillis();
        String combinedResult = combinedFuture.join();
        System.out.println("Combined Result: " + combinedResult);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

    }
}