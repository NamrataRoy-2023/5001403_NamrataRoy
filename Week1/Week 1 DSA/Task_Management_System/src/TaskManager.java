class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskManager {
    private Node head;

    // Method to add a new task
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to search for a task by taskId
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Task not found
    }

    // Method to delete a task by taskId
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false; // List is empty
        }

        if (head.task.taskId == taskId) {
            head = head.next;
            return true; // Task found and deleted
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.taskId == taskId) {
                current.next = current.next.next;
                return true; // Task found and deleted
            }
            current = current.next;
        }
        return false; // Task not found
    }

    // Method to traverse and print all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

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
