
public class StackListaEncadenada<E> implements IStack<E> {

    private Nodo cabeza = null;
    private int longitud = 0;

    private class Nodo {

        public E item;
        public Nodo siguiente = null;

        public Nodo(E item) {
            this.item = item;
        }

    }

    // Agrega un elemento al comienzo de la lista
    public void push(E item) {
        Nodo nodo = new Nodo(item);
        nodo.siguiente = cabeza;
        cabeza = nodo;
        longitud++;
    }

    // Devuelve el primer elemento de la lista
    public E peek() {
        int n = 0;
        if (cabeza == null) {
            return null;
        } else {
            Nodo puntero = cabeza;
            int contador = 0;
            while (contador < n && puntero.siguiente != null) {
                puntero = puntero.siguiente;
                contador++;
            }

            if (contador != n) {
                return null;
            } else {
                return puntero.item;
            }

        }
    }

    // Elimina el primer elemento de la lista
    public E pop() {

        E item = peek();

        if (cabeza != null) {
            Nodo primerElemento = cabeza;
            cabeza = cabeza.siguiente;
            primerElemento.siguiente = null;
            longitud--;
        }

        return item;
    }

    // Devuelve la cantidad de elementos que hay en la lista
    public int size() {

        return longitud;
    }

    // Indica si la lista esta vacia o no
    public boolean empty() {
        return cabeza == null;
    }

}