//Lista DobleEncadenada

public class StackListaDobleEncadenada<E> implements IStack<E> {

    private class Nodo {
        E info;
        Nodo ant, sig;
    }

    private Nodo raiz;

    public StackListaDobleEncadenada() {
        raiz = null;
    }

    //Agrega un elemento a la lista doble encadenada
    public void push(E x) {
        int pos = 0;
        if (pos <= size() + 1) {
            Nodo nuevo = new Nodo();
            nuevo.info = x;
            if (pos == 1) {
                nuevo.sig = raiz;
                if (raiz != null)
                    raiz.ant = nuevo;
                raiz = nuevo;
            } else if (pos == size() + 1) {
                Nodo reco = raiz;
                while (reco.sig != null) {
                    reco = reco.sig;
                }
                reco.sig = nuevo;
                nuevo.ant = reco;
                nuevo.sig = null;
            } else {
                Nodo reco = raiz;
                for (int f = 1; f <= pos - 2; f++)
                    reco = reco.sig;
                Nodo siguiente = reco.sig;
                reco.sig = nuevo;
                nuevo.ant = reco;
                nuevo.sig = siguiente;
                siguiente.ant = nuevo;
            }
        }
    }

    //Devuleve el primer elemento
    public E peek() {
        int pos = 0;
        if (pos <= size()) {
            E informacion;
            if (pos == 1) {
                informacion = raiz.info;
                raiz = raiz.sig;
                if (raiz != null)
                    raiz.ant = null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1; f <= pos - 2; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                reco.sig = prox.sig;
                Nodo siguiente = prox.sig;
                if (siguiente != null)
                    siguiente.ant = reco;
                informacion = prox.info;
            }
            return informacion;
        } else {
            return null;
        }
    }
    
    //Elimina el primer elemento 
    public E pop() {
        E item = peek();
        int pos = 0;
        if (pos <= size()) {
            if (pos == 1) {
                raiz = raiz.sig;
                if (raiz != null)
                    raiz.ant = null;
            } else {
                Nodo reco;
                reco = raiz;
                for (int f = 1; f <= pos - 2; f++)
                    reco = reco.sig;
                Nodo prox = reco.sig;
                prox = prox.sig;
                reco.sig = prox;
                if (prox != null)
                    prox.ant = reco;
            }
        }

        return item;
    }

    //Devuelve la cantidad de elementos en la lista
    public int size() {
        int cant = 0;
        Nodo reco = raiz;
        while (reco != null) {
            reco = reco.sig;
            cant++;
        }
        return cant;
    }


    //Comprobar si la lista esta vacia
    public boolean empty() {
        if (raiz == null)
            return true;
        else
            return false;
    }

}