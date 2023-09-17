package ArbolesBinarios;

import ArbolesBinarios.ArbolEc;
import ArbolesBinarios.Heap;
import ArbolesBinarios.ArbolAVL;
import ArbolesBinarios.ArbolAVL;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("\tÁrboles binarios");
            System.out.println("Ingrese una opción:\n(1) Árbol AVL");
            System.out.println("(2) Heap\n(3) Árbol de Expresión Aritmética\n(4) Salir");
            op = entrada.nextInt();
            switch (op) {
                case 1:
                    int opA;
                    ArbolAVL h1 = new ArbolAVL();
                    do {
                        System.out.println("Ingrese una opcion:\n(1) Agregar clave");
                        System.out.println("(2) Buscar un valor\n(3) Eliminar clave");
                        System.out.println("(4) Mostrar arbol\n(5) Salir");
                        opA = entrada.nextInt();
                        switch (opA) {
                            case 1:
                                System.out.println("Ingrese el valor a agregar al arbol: ");
                                int valor = entrada.nextInt();
                                if(h1.Buscar(valor)==false){
                                    h1.Insertar(valor);
                                }else{
                                    System.out.println("El nodo ya se encuentra en el arbol");
                                }
                                break;
                            case 2:
                                System.out.println("Ingrese el valor a buscar");
                                int val=entrada.nextInt();
                                if(h1.Buscar(val)==false){
                                    System.out.println("El nodo no se encuentra");
                                }else{
                                    System.out.println("El nodo fue encontrado");
                                }
                                break;
                            case 3:
                                System.out.println("Ingrese el valor a Eliminar");
                                int eliminado=entrada.nextInt();
                                if(h1.Buscar(eliminado)==false){
                                    System.out.println("El nodo no se encuentra en el arbol");
                                }else{
                                    h1.Eliminar(eliminado);
                                    System.out.println("El nodo ha sido eliminado");
                                }
                                break;
                            case 4:
                                h1.breadthFrist();
                                System.out.println();
                                break;
                            case 5:
                                System.out.println("\033[H\033[2J");
                                break;
                            default:
                                System.out.println("No elegiste nada válido");
                                break;
                        }
                    } while (opA != 5);
                    break;
                case 2:
                int opH;
                Heap h2 = new Heap();
                    do {
                        System.out.println("Ingrese una opción:\n(1) Agregar clave");
                        System.out.println("(2) Eliminar raíz\n(3) Mostrar Árbol");
                        System.out.println("(4) Salir");
                        opH = entrada.nextInt();
                        switch (opH) {
                            case 1:
                                System.out.println("Ingrese el valor de la clave: ");
                                int agregar=entrada.nextInt();
                                h2.Add(agregar);
                                break;
                            case 2:
                                h2.RemoveRoot();
                                System.out.println("Raiz eliminada");
                                break;
                            case 3:
                                h2.PrintHeap();
                                break;
                            case 4:
                                System.out.println("\033[H\033[2J");
                                break;
                            default:
                                System.out.println("No elegiste nada válido");
                                break;
                        }
                    } while (opH != 4);
                    break;
                case 3:
                    int opE;
                    System.out.println("Ingrese la operacion a resolver: ");
                    ArbolEc h3 = new ArbolEc(entrada.next());
                    do {
                        System.out.println("Ingrese una opción:");
                        System.out.println("(1) Mostrar Árbol\n(2) Resolver");
                        System.out.println("(3) Salir");
                        opE = entrada.nextInt();
                        switch (opE) {
                            case 1:
                                h3.pintaArbol();
                                System.out.println();
                                break;
                            case 2:
                                System.out.println("El resultado de la ecuacion es: "+h3.result());
                                break;
                            case 3:
                                System.out.println("\033[H\033[2J");
                                break;
                            default:
                                System.out.println("No elegiste nada válido");
                                break;
                        }
                    } while (opE != 3);
                    break;
                case 4:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("No elegiste nada válido");
                    break;
            }
        } while (op != 4);
        entrada.close();
    }
}
