
import java.util.Vector;

public class StackVector<E> implements IStack<E> {

    Vector<E> vector = new Vector<E>();

    public void push(E item) {
        vector.addElement(item);
    }

    public E pop() {
        return vector.remove(size() - 1);
    }

    public E peek() {
        return vector.get(size() - 1);
    }

    public int size() {
        return vector.size();
    }

    public boolean empty() {
        return size() == 0;
    }
}