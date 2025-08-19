import java.util.Scanner;

// Node for each task
class TaskNode {
    String taskName;
    int priority;
    TaskNode next;
    TaskNode prev;

    TaskNode(String taskName, int priority) {
        this.taskName = taskName;
        this.priority = priority;
    }
}

// Doubly Linked List for tasks
class TaskList {
    TaskNode head, tail;

    // 1) Add task
    void addTask(String name, int priority) {
        TaskNode newNode = new TaskNode(name, priority);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        System.out.println("Task added.");
    }

    // 2) View tasks forward
    void viewForward() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }
        TaskNode temp = head;
        while (temp != null) {
            System.out.println(temp.taskName + " (Priority: " + temp.priority + ")");
            temp = temp.next;
        }
    }

    // 3) View tasks backward
    void viewBackward() {
        if (tail == null) {
            System.out.println("No tasks.");
            return;
        }
        TaskNode temp = tail;
        while (temp != null) {
            System.out.println(temp.taskName + " (Priority: " + temp.priority + ")");
            temp = temp.prev;
        }
    }

    // 4) Delete task
    void deleteTask(String name) {
        TaskNode temp = head;
        while (temp != null && !temp.taskName.equalsIgnoreCase(name)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Task not found.");
            return;
        }
        if (temp.prev != null) temp.prev.next = temp.next;
        else head = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
        else tail = temp.prev;
        System.out.println("Task deleted.");
    }

    // 5) Search task
    void searchTask(String name) {
        TaskNode temp = head;
        while (temp != null) {
            if (temp.taskName.equalsIgnoreCase(name)) {
                System.out.println("Found: " + temp.taskName + " (Priority: " + temp.priority + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Task not found.");
    }
}

// Main program with menu
public class TaskManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        int choice;

        do {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1) Add Task");
            System.out.println("2) View Tasks (Forward)");
            System.out.println("3) View Tasks (Backward)");
            System.out.println("4) Delete Task");
            System.out.println("5) Search Task");
            System.out.println("6) Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter priority: ");
                    int priority = sc.nextInt();
                    list.addTask(name, priority);
                    break;
                case 2:
                    list.viewForward();
                    break;
                case 3:
                    list.viewBackward();
                    break;
                case 4:
                    System.out.print("Enter task name to delete: ");
                    name = sc.nextLine();
                    list.deleteTask(name);
                    break;
                case 5:
                    System.out.print("Enter task name to search: ");
                    name = sc.nextLine();
                    list.searchTask(name);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}
