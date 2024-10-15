import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

class Task2 {
    int id;
    int processingTime;
    int weight;  // Additional field for Task 2

    public WeightedTask(int id, int processingTime, int weight) {
        this.id = id;
        this.processingTime = processingTime;
        this.weight = weight;
    }
}

public class TaskSchedulerWeighted {

    public static void main(String[] args) {
        PriorityQueue<WeightedTask> weightedQueue = new PriorityQueue<>(Comparator.comparingInt(t -> t.weight));

        // Read input from task2-input.txt
        try {
            List<String> lines = Files.readAllLines(Paths.get("task2-input.txt"));

            for (String line : lines) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[1]);
                int weight = Integer.parseInt(parts[2]);

                // Add task to queue
                weightedQueue.add(new WeightedTask(id, processingTime, weight));
            }
        } catch (IOException e) {
            System.out.println("Error reading task2-input.txt file: " + e.getMessage());
            return;
        }

        // Variables for analysis
        int currentTime = 0;
        int totalWeightedCompletionTime = 0;
        int totalWeight = 0;

        System.out.print("Execution order by weight: [");
        while (!weightedQueue.isEmpty()) {
            WeightedTask task = weightedQueue.poll();
            currentTime += task.processingTime;
            totalWeightedCompletionTime += currentTime * task.weight;
            totalWeight += task.weight;
            System.out.print(task.id + (weightedQueue.isEmpty() ? "]\n" : ", "));
        }

        // Display results
        System.out.println("Total weighted completion time: " + totalWeightedCompletionTime);
        System.out.println("Average weighted completion time: " + (double) totalWeightedCompletionTime / totalWeight);
    }
}
