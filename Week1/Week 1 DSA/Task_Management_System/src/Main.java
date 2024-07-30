public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Adding tasks
        manager.addTask(new Task(1, "Task 1", "Open"));
        manager.addTask(new Task(2, "Task 2", "In Progress...."));
        manager.addTask(new Task(3, "Task 3", "Completed"));

        // Traversing tasks
        System.out.println("All tasks:");
        manager.traverseTasks();

        // Searching for a task
        System.out.println("\nSearch for Task ID 2:");
        Task task = manager.searchTask(2);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("Task not found!");
        }

        // Deleting a task
        System.out.println("\nDeleting Task ID 2:");
        boolean isDeleted = manager.deleteTask(2);
        if (isDeleted) {
            System.out.println("Task Successfully deleted.");
        } else {
            System.out.println("Task not found!");
        }

        // Traversing tasks after deletion
        System.out.println("\nAll tasks after deletion:");
        manager.traverseTasks();
    }
}
