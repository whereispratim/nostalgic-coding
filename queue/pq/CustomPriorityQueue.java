import java.util.PriorityQueue;

public class CustomPriorityQueue<T> {

    private PriorityQueue<Element<T>> elements;

    public CustomPriorityQueue() {
        elements = new PriorityQueue<>();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void put(T item, int priority) {
        elements.add(new Element<>(item, priority));
    }

    public T get() {
        return elements.poll().getItem();
    }

    @Override
    public String toString() {
        return elements.toString();
    }


    class Element<T> implements Comparable<Element<T>> {
        private final T item;
        private final int priority;

        public Element(T item, int priority) {
            this.item = item;
            this.priority = priority;
        }

        public T getItem() {
            return item;
        }

        @Override
        public int compareTo(Element<T> other) {
            return Integer.compare(this.priority, other.priority);
        }

        @Override
        public String toString() {
            return "(" + item + ", " + priority + ")";
        }

    }
}
