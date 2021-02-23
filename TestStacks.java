
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestStacks {

    /*
     * Test de stack stack
     * 
     */
    @Test
    public void testStackstack() {
        StackArrayList<Integer> stack = new StackArrayList<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.peek());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.size());
    }

    /*
     * Test de stack Lista Encadenada
     */
    @Test
    public void testStackListaEncadenada() {
        StackListaEncadenada<Integer> stack = new StackListaEncadenada<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.peek());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.size());
    }


    
    /*
     * Test de stack Lista Doblemente Encadenada
     */
    @Test
    public void testStackListaDobleEncadenada() {
        StackListaDobleEncadenada<Integer> stack = new StackListaDobleEncadenada<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.peek());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.size());
    }


     /*
     * Test de stack Lista Doblemente Encadenada
     */
    @Test
    public void testStackVector() {
        StackVector<Integer> stack = new StackVector<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.peek());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.size());
    }

}
