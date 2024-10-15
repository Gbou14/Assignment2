import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

class Task3 {
    int id;
    int priority;
    int time;

    public Task3(int id, int priority, int time) {
        this.id = id;
        this.priority = priority;
        this.time = time;
    }
}

public class TaskSchedulerTask3 {

    public static void main(String[] args) {
        PriorityQueue<Task3> task3Queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.priority));

        // Read input from task3-input.txt
        try {
            List<String> lines = Files.readAllLines(Paths.get("task3-input.txt"));

            for (String line : lines) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                int priority = Integer.parseInt(parts[1]);
                int time = Integer.parseInt(parts[2]);

                // Add task to queue
                task3Queue.add(new Task3(id, priority, time));
            }
        } catch (IOException e) {
            System.out.println("Error reading task3-input.txt file: " + e.getMessage());
            return;
        }

        // Process and print the results
        System.out.print("Execution order by priority: [");
        while (!task3Queue.isEmpty()) {
            Task3 task = task3Queue.poll();
            System.out.print(task.id + (task3Queue.isEmpty() ? "]\n" : ", "));
        }
    }
}
