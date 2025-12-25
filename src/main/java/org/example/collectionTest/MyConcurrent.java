package org.example.collectionTest;

import org.example.model.Inhabitant;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyConcurrent {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> maleNames = List.of("Bob", "Rex", "Hummer", "Bear", "Frank", "Etan", "Ernest", "Neo", "Morpheus", "Pince");
        List<String> femaleNames = List.of("Blondie", "Regina", "Snowflake", "Rapunzel", "Fiona", "Felicia", "Trinity", "Lea", "Candy", "Elle");
        List<String> types = List.of("dog", "cat", "snake", "dog", "cat", "snake", "dog", "cat", "snake", "dove");

        ConcurrentHashMap<String, Inhabitant> concurrentHM = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, AtomicInteger> threadStats = new ConcurrentHashMap<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());

        List<Future<?>> futures = new CopyOnWriteArrayList<>();
        int limit = 10000;
        int threads = 10;
        int chunkSize = limit / threads;

        for (int t = 0; t < threads; t++) {
            int start = t * chunkSize;
            int end = start + chunkSize-1;

            Future<?> future = executor.submit(() -> {
                String name;
                String type;
                String gender;
                Random rnd = new Random();
                String threadName = Thread.currentThread().getName();
                threadStats.computeIfAbsent(threadName, k -> new AtomicInteger(1));

                for (int i = start; i < end; i++) {
                    Inhabitant inh = new Inhabitant();
                    String id = "id" + rnd.nextInt(150000);
                    if (i % 2 == 0) {
                        gender = "male";
                        name = maleNames.get(i % 10);
                    } else {
                        gender = "female";
                        name = femaleNames.get(i % 10);
                    }
                    type = types.get(i % 10);
                    inh.setName(name);
                    inh.setType(type);
                    inh.setGender(gender);
                    concurrentHM.put(id, inh);
                    threadStats.get(threadName).incrementAndGet();
                }
            });
            futures.add(future);
        }

        for (Future<?> f : futures) {
            f.get();
        }
        executor.shutdown();

        int total = 0;
        for (Map.Entry<String, AtomicInteger> entry : threadStats.entrySet()) {
            int count = entry.getValue().get();
            total += count;
            System.out.println(entry.getKey() + " processed " + count + " records");
        }

        System.out.println("TOTAL processed = " + total);
        System.out.println("Finished. Map size = " + concurrentHM.size());
    }

}

