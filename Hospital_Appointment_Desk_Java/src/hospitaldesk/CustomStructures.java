package hospitaldesk;

// Both structures are implemented with linked nodes, not Java Queue/Stack classes.
public class CustomStructures {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    public static class WalkInQueue<T> {
        private Node<T> front;
        private Node<T> rear;
        private int size;

        // FIFO: add at rear.
        public void enqueue(T value) {
            Node<T> node = new Node<>(value);
            if (rear == null) front = rear = node;
            else {
                rear.next = node;
                rear = node;
            }
            size++;
        }

        // FIFO: remove from front.
        public T dequeue() {
            if (front == null) throw new IllegalStateException("Queue is empty");
            T value = front.value;
            front = front.next;
            if (front == null) rear = null;
            size--;
            return value;
        }

        public T peek() {
            if (front == null) throw new IllegalStateException("Queue is empty");
            return front.value;
        }

        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
    }

    public static class RecentlyViewedStack<T> {
        private Node<T> top;
        private int size;

        // LIFO: add and remove at top.
        public void push(T value) {
            Node<T> node = new Node<>(value);
            node.next = top;
            top = node;
            size++;
        }

        public T pop() {
            if (top == null) throw new IllegalStateException("Stack is empty");
            T value = top.value;
            top = top.next;
            size--;
            return value;
        }

        public T peek() {
            if (top == null) throw new IllegalStateException("Stack is empty");
            return top.value;
        }

        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
    }
}
