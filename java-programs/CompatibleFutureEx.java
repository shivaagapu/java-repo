import java.util.concurrent.CompletableFuture;
public class CompatibleFutureEx {

    public static void main(String[] args) {

        // CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello, World!");
        // future.thenAccept(result -> System.out.println("Result: " + result));

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    return "Account Details";
                });

        System.out.println("Doing other work...");

        String result = future.join();

        System.out.println(result);
    }
}