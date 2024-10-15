import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

class Task1 {
    int id;
    int processingTime;

    // Constructor for Task 1 (processing time only)
    public Task(int id, int processingTime) {
        this.id = id;
        this.processingTime = processingTime;
    }
}

public class TaskSchedulerSPT {

    public static void main(String[] args) {
        // Define the priority queue (min-heap) to sort tasks by processing time
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(Comparator.comparingInt(t -> t.processingTime));

        // Read the task1-input.txt file and add tasks to the queue
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get("task1-input.txt"));

            // Process each line
            for (String line : lines) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[1]);
                
                // Add each task to the priority queue
                taskQueue.add(new Task(id, processingTime));
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Variables to calculate total completion time and number of tasks
        int currentTime = 0;
        int totalCompletionTime = 0;
        int jobCount = taskQueue.size();

        // Print the execution order and compute average completion time
        System.out.print("Execution order: [");
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll(); // Retrieve the task with the smallest processing time
            currentTime += task.processingTime;
            totalCompletionTime += currentTime;
            System.out.print(task.id + (taskQueue.isEmpty() ? "]\n" : ", "));
        }

        // Calculate and display the average completion time
        double avgCompletionTime = (double) totalCompletionTime / jobCount;
        System.out.println("Average completion time: " + avgCompletionTime);
    }
}
