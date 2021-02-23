import java.util.Scanner;
import java.util.Stack;

import java.io.*;

public class Calculadora implements ICalculadora {

    // Implementación patron de diseño Singleton
    public static Calculadora calculadora;

    // Utilidades
    private Scanner sc = new Scanner(System.in);
    private Scanner scInt = new Scanner(System.in);
    private FactoryStack factoryStack = new FactoryStack();
    private IStack<Integer> calculadoraStack;

    // Variables para almacenar los datos del archivo de texto
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    private Calculadora() {

    }

    public static Calculadora getInstancia() {
        if (calculadora == null) {
            calculadora = new Calculadora();
        }
        return calculadora;
    }

    /*
     * Incia la calculadora y lee el archivo de texto con las operaciones
     * 
     * @return: void
     */
    public void start() {

        System.out.println(" :: CALCULADORA ::");

        // Implementar patron de diseño Factory para seleccionar que tipo de Stack
        // utilizara
        System.out.println("Ingrese que Stack implementara: ");
        System.out.println("1. ArrayList");
        System.out.println("2. Vector");
        System.out.println("3. Lista encadenada");
        System.out.println("4. Lista doblemente encadenada");
        Integer opcionStack = scInt.nextInt();

        // Instanciar el tipo de Stack
        calculadoraStack = factoryStack.getStack(opcionStack);

        // System.out.print("Ingrese la ruta del archivo: ");
        String src = "./datos.txt";
        // System.out.print("\n");

        // Leer el archivo de texto y opera cada linea
        try {
            archivo = new File(src);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch (Exception e) {
            System.out.println("[!] No se encontro el archivo");
        }

        try {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Convierte la operación de infix a posfix
                String operacion = infixToPosfix(linea);
                // Realiza la operación y la muestra en pantalla
                System.out.println("Resultado: " + operarPosfix(operacion));

            }
        } catch (Exception e) {
            System.out.println("[!] No se realizo la operacion correctamente");
        }
        // Recorre todas las lineas del archivo

    }

    public int precedencia(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    /*
     * Convierte una operación infix a posfix
     * 
     * @params: un texto con la operación en infix
     * 
     * @return: un texto con la operación en posfix
     */
    private String infixToPosfix(String linea) {

        String resultado = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < linea.length(); i++) {

            char c = linea.charAt(i);

            // check if char is operator
            if (precedencia(c) > 0) {
                while (stack.isEmpty() == false && precedencia(stack.peek()) >= precedencia(c)) {
                    resultado += stack.pop();
                }
                stack.push(c);
            } else if (c == ')') {
                char x = stack.pop();
                while (x != '(') {
                    resultado += x;
                    x = stack.pop();
                }
            } else if (c == '(') {
                stack.push(c);
            } else {
                // character is neither operator nor (
                resultado += c;
            }
        }
        for (int i = 0; i <= stack.size(); i++) {
            resultado += stack.pop();
        }
        return resultado;
    }

    /*
     * Realiza una operacion posfix
     * 
     * @params: Un texto con la operación
     * 
     * @return: El resultado de la operación
     */
    private int operarPosfix(String linea) {
        Stack<Integer> calculadoraStack = new Stack<>();

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (Character.isDigit(c))
                calculadoraStack.push(c - '0');

            else {
                int val1 = calculadoraStack.pop();
                int val2 = calculadoraStack.pop();

                switch (c) {
                    case '+':
                        calculadoraStack.push(val2 + val1);
                        break;

                    case '-':
                        calculadoraStack.push(val2 - val1);
                        break;

                    case '/':
                        calculadoraStack.push(val2 / val1);
                        break;

                    case '*':
                        calculadoraStack.push(val2 * val1);
                        break;
                }
            }
        }
        return calculadoraStack.pop();
    }

    /*
     * Realiza una multiplicación entre el número X y Y
     * 
     * @params: Los números a multiplicar
     * 
     * @return: El resultado de la operación
     */
    @Override
    public int multiplicacion(int x, int y) {
        return x * y;
    }

    /*
     * Realiza una resta entre el número X y Y
     * 
     * @params: Los números a restar
     * 
     * @return: El resultado de la operación
     */
    @Override
    public int resta(int x, int y) {
        return x - y;
    }

    /*
     * Realiza una suma entre el número X y Y
     * 
     * @params: Los números a sumar
     * 
     * @return: El resultado de la operación
     */
    @Override
    public int suma(int x, int y) {
        return x + y;
    }

    /*
     * Realiza una división entre el número X y Y
     * 
     * @params: Los números a dividir
     * 
     * @return: El resultado de la operación
     */
    @Override
    public int division(int x, int y) {
        int result;
        try {
            result = x / y;
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

}
