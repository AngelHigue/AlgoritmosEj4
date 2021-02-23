import java.util.ArrayList;
import java.util.Scanner;

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


    private Calculadora(){
        
    }

    public static Calculadora getInstancia(){
        if(calculadora == null){
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

        System.out.print(" :: CALCULADORA ::");

        // Implementar patron de diseño Factory para seleccionar que tipo de Stack
        // utilizara
        System.out.print("Ingrese que Stack implementara: ");
        System.out.print("1. ArrayList");
        System.out.print("2. Vector");
        System.out.print("3. Lista encadenada");
        System.out.print("4. Lista doblemente encadenada");
        Integer opcionStack = scInt.nextInt();

        // Instanciar el tipo de Stack
        calculadoraStack = factoryStack.getStack(opcionStack);

        System.out.print("Ingrese la ruta del archivo: ");
        String src = sc.nextLine();
        System.out.print("\n");

        // Leer el archivo de texto y opera cada linea
        try {
            archivo = new File(src);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Recorre todas las lineas del archivo
            String linea;
            while ((linea = br.readLine()) != null) {

                // Convierte la operación de infix a posfix
                String operacion = infixToPosfix(linea);

                // Realiza la operación y la muestra en pantalla
                System.out.println("Resultado: " + operarPosfix(operacion));

            }

        } catch (Exception e) {
            System.out.println("[!] No se encontro el archivo");
        }

    }

    /*
     * Convierte una operación infix a posfix
     * 
     * @params: un texto con la operación en infix
     * 
     * @return: un texto con la operación en posfix
     */
    private String infixToPosfix(String linea) {

        String operacionPosfix = "";
        ArrayList<String> stack = new ArrayList<String>();
        String[] tempLinea = linea.split(" ");

        // Recorre el contenido de la linea
        for (int i = 0; i < tempLinea.length; i++) {

            // Verifica si es un numero o una instruccion
            if (esUnNumero(tempLinea[i])) {
                operacionPosfix += " " + tempLinea[i];
            } else {

                if (tempLinea[i] == "(") {
                    stack.add(tempLinea[i]);
                } else if (tempLinea[i] == ")") {
                    int n = 0;
                    Boolean ejecutar = true;
                    while (ejecutar) {

                        if (stack.get(n) != "(") {
                            operacionPosfix += " " + stack.get(n);
                        } else {
                            ejecutar = false;
                        }
                        n++;
                    }
                } else {
                    stack.add(tempLinea[i]);
                }

            }

        }

        return operacionPosfix;
    }

    /*
     * Realiza una operacion posfix
     * 
     * @params: Un texto con la operación
     * 
     * @return: El resultado de la operación
     */
    private int operarPosfix(String linea) {

        // Separar el contenido de la linea en un array
        String[] tempLinea = linea.split(" ");

        // Recorre el contenido de la linea
        for (int i = 0; i < tempLinea.length; i++) {

            // Verifica si es un numero o una instruccion
            if (esUnNumero(tempLinea[i])) {
                calculadoraStack.push(Integer.parseInt(tempLinea[i]));
            } else {

                int primerNumero = 0;
                int segundoNumero = 0;

                // Comprueba que si existan suficientes numeros para operar
                try {
                    primerNumero = calculadoraStack.pop();
                    segundoNumero = calculadoraStack.pop();
                } catch (Exception e) {
                    System.out.println("[!]");
                }

                // Mira el tipo de operador y llama al método correspondiente para realizar la
                // operación
                // Luego guarda el resultado en el Stack
                switch (linea) {
                    case "*":
                        calculadoraStack.push(multiplicacion(primerNumero, segundoNumero));
                        break;
                    case "+":
                        calculadoraStack.push(suma(primerNumero, segundoNumero));
                        break;
                    case "-":
                        calculadoraStack.push(resta(primerNumero, segundoNumero));
                        break;
                    case "/":
                        calculadoraStack.push(division(primerNumero, segundoNumero));
                        break;
                }

            }
        }

        return calculadoraStack.pop();

    }

    /*
     * Revisa si el dato ingresado es un numero o no
     * 
     * @params: el caracter a revisar
     * 
     * @return: Si es un numero devuelve True de lo contrario False
     */
    private Boolean esUnNumero(String txt) {
        Boolean result;
        try {
            Integer.parseInt(txt);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
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
