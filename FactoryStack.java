// Fabrica de Stack

public class FactoryStack {

    public IStack<Integer> getStack(Integer implementacion) {

        if (implementacion.equals(1)) { //ArrayList
            return new StackArrayList<Integer>();
        } else if (implementacion.equals(2)) { //Vector
            return new StackVector<Integer>();

        } else if (implementacion.equals(3)) { //Lista encadenada
            return new StackListaEncadenada<Integer>();

        } else if (implementacion.equals(4)) { //Lista doblemente encadenada
            return new StackListaDobleEncadenada<Integer>();

        }

        return null;
    }

}
