package ArbolesBinarios;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** 
 * Esta clase define árboles binarios a partir de una ecuación
 * @author Equipo 8
 * @version 27/11/2021
 * */

public class ArbolEc {
    /**
     * Rama raiz
     */
    Nodo root;
    /**
     * Tipo de operaciones aceptadas
     */
    String op[] = {"+-", "*/", "^r"};
    /**
     * Pila para resolver el arbol
     */
    Stack<Nodo> polacaInv = new Stack<Nodo>();

    /**
     * Construye el árbol binario a partir de una ecuación
     * @param ec El parámetro sera la ecuacion
     */
    public ArbolEc(String ec){
        Boolean stop = false; // Variable bandera para salir de los for
        String mit1 = new String(), mit2 = new String();
        // ciclo para recorrer cada jerarquia de operacion
        for (int i = 0; i < op.length && !stop; i++) {
            // ciclo para recorrer las operaciones
            for (int j = 0; j < op[i].length() && !stop; j++) {
                // ciclo para recorrer la ecuacion
                for (int j2 = ec.length()-1; j2 >= 0 && !stop; j2--) {
                    // Si tiene parentesis los salta
                    if (ec.charAt(j2) == ')') {
                        Integer cor = 1;
                        for (int k = j2-1; k >= 0; k--) {
                            if (ec.charAt(k) == '(') {
                                cor--;
                                if (cor == 0) {
                                    j2 = k;
                                    break;
                                }
                            }else if (ec.charAt(k) == ')') {
                                cor++;
                            }
                        }
                    }
                    // Encuentra la operacion que sera la raiz
                    if (ec.charAt(j2) == op[i].charAt(j)) {
                        root = new Nodo(Character.toString(ec.charAt(j2)));
                        // Separo la ecuacion desde la operacion raiz
                        mit1 = ec.substring(0, j2);
                        mit2 = ec.substring(j2+1);
                        stop = true;
                    }
                }
            }
        }
        // LLamo al metodo recursivo que separara cada rama
        ramaIzq(mit1, root);
        ramaDer(mit2, root);
    }

    private void ramaIzq(String mit, Nodo head){
        Nodo temp = recuRamas(mit, head.nivel);
        temp.nivel = head.nivel+1;
        this.add(head, temp, 0);
    }
    private void ramaDer(String mit, Nodo head){
        Nodo temp = recuRamas(mit, head.nivel);
        temp.nivel = head.nivel+1;
        this.add(head, temp, 1);
    }

    // Metodo recursivo que separa cada operacion
    private Nodo recuRamas(String mit, Integer n){
        Nodo nuevo = new Nodo();
        nuevo.nivel = n+1;
        String mit1 = new String(), mit2 = new String();
        // Si el segmento de ecuacion contiene un parentesis, elimina
        // el mas externo
        if (mit.charAt(mit.length()-1) == ')') {
            mit = mit.substring(0, mit.length()-1);
            Integer cor = 1;
            for (int k = mit.length()-1; k >= 0; k--) {
                if (mit.charAt(k) == '(') {
                    cor--;
                    if (cor == 0) {
                        mit = mit.substring(k+1, mit.length());
                        break;
                    }
                }else if (mit.charAt(k) == ')') {
                    cor++;
                }
            }
        }
        // ciclo para recorrer cada jerarquia de operacion
        for (int i = 0; i < op.length; i++) {
            // ciclo para recorrer las operaciones
            for (int j = 0; j < op[i].length(); j++) {
                // ciclo para recorrer la ecuacion
                for (int j2 = mit.length()-1; j2 >= 0; j2--) {
                    // Si tiene parentesis los salta
                    if (mit.charAt(j2) == ')') {
                        Integer cor = 1;
                        for (int k = j2-1; k >= 0; k--) {
                            if (mit.charAt(k) == '(') {
                                cor--;
                                if (cor == 0) {
                                    j2 = k;
                                    break;
                                }
                            }else if (mit.charAt(k) == ')') {
                                cor++;
                            }
                        }
                    }
                    // Encuentra la operacion que sera el nodo padre
                    if (mit.charAt(j2) == op[i].charAt(j)) {
                        // Coloco la operacion en un nuevo nodo
                        nuevo.valor = Character.toString(mit.charAt(j2));
                        // Divido desde la operacion encontrada y llamada recursiva
                        mit1 = mit.substring(0, j2);
                        ramaIzq(mit1, nuevo);
                        mit2 = mit.substring(j2+1);
                        ramaDer(mit2, nuevo);
                        // Cuando termina de hacer las ramas regresa el nodo
                        return nuevo;
                    }
                }
            }
        }
        // Si no encuentra ninguna operacion agrega el numero como valor del nodo
        nuevo.valor = mit;
        return nuevo;
    }

    /**
     * Método que pinta el árbol binario obtenido de la ecuacion
     */
    public void pintaArbol(){
        Nodo r = root;
        Queue<Nodo> queue = new LinkedList<Nodo>();
        Queue<Nodo> queue1 = new LinkedList<Nodo>();
        if(r!=null){
            queue.add(r);
            queue1.add(r);
            //Obtencion del recorrido BFS
            while(!queue.isEmpty()){
                r = queue.poll();
                if(r.izq!=null){
                    queue.add(r.izq);
                    queue1.add(r.izq);
                }
                if(r.der!=null){
                    queue.add(r.der);
                    queue1.add(r.der);
                }
            }
            //Obtencion del maximo nivel del arbol 
            Integer niveles = r.nivel;
            //Arreglo donde se almacenaran los valores de cada nodo
            String tree[][] = new String[niveles+1][(int)Math.pow(2, niveles+1)];
            //Ciclo para recorrer los niveles del arbol
            for (int i = 0; i <= niveles; i++) {
                Integer cont = 0, des = 0;
                //Se tiene a la cola1 como respaldo
                queue = new LinkedList<Nodo>(queue1);
                //Se recorre la cola para obtener los nodos y almacenarlos en la matriz
                while (!queue.isEmpty()) {
                    r = queue.poll();
                    //Si el nivel del nodo revisado es igual
                    if (r.nivel == i) {
                        //Si el valor es null, coloca el valor del nodo
                        //Sino recorre la fila hasta encontrar un espacio
                        if (tree[i][cont] == null){
                            tree[i][cont] = r.valor;
                            cont++;
                        }else{
                            while (tree[i][cont] != null) {
                                cont++;
                            }
                            tree[i][cont] = r.valor;
                            cont++;
                        }
                        //Si no tiene hijo izquierdo y el numero de niveles no alcanza el maximo
                        //coloca en la siguiente fila un espacio y recorre el apuntador 1 espacio
                        if (r.izq == null && i+1 <= niveles){
                            tree[i+1][des] = " ";
                            des++;
                        }
                        //Si no tiene hijo derecho y el numero de niveles no alcanza el maximo
                        //coloca en la siguiente fila un espacio y recorre el apuntador 1 espacio
                        if (r.der == null && i+1 <= niveles){
                            tree[i+1][des] = " ";
                            des++;
                        }
                        //Si tiene hijos recorre el apuntador 2 espacios
                        if (r.izq != null && r.der != null) {
                            des += 2;
                        }
                    }
                }
            }
            //Ciclo que recorrera la matriz
            for (int i = 0; i < tree.length; i++) {
                System.out.print("El nivel "+i);
                System.out.print("\t\t");
                //Ciclo que imprimira el numero de nodos por nivel
                for (int j = 0, k = 0; j < (int)Math.pow(2, i); j++, k++) {
                    //Mantiene a k = 1  exceptuando a la raiz
                    if (k == 2) {
                        k = 1;
                    }
                    //Ciclo que recorre los espacios que necesito para cada nodo
                    for (int j2 = 0; j2 < (int)((tree[0].length/Math.pow(2, i+1))*(k+1))-1; j2++) {
                        System.out.print(" ");
                    }
                    //Si el valor del nodo es null imprimo espacios
                    //Sino imprimo el valor del nodo
                    if (tree[i][j] == null) {
                        System.out.print(" ");
                    }else{
                        System.out.print(tree[i][j]);
                    }
                }
                System.out.println("\n");
            }
            System.out.print("BFS: ");
            while (!queue1.isEmpty()) {
                r = queue1.poll();
                System.out.print(r.valor+" ");
            }
            System.out.println();
        }
    }

    //Metodo recursivo para el recorrido postorden
    private void postOrdenRecu(Nodo r, Queue<Nodo> queue){
        while (!queue.isEmpty()) {
            r = queue.poll();
            if(r.izq!=null){
                queue.add(r.izq);
                postOrdenRecu(r.izq, queue);
            }
            if(r.der!=null){
                queue.add(r.der);
                postOrdenRecu(r.der, queue);
            }
            polacaInv.push(r);
        }
    }

    //Metodo que ejecuta el metodo recursivo de postorden
    private void postOrden(){
        Nodo r = root;
        Queue<Nodo> queue = new LinkedList<Nodo>();
        queue.add(r);
        postOrdenRecu(r, queue);
    }

    /**
     * Método que resuelve la ecuacion
     * @return El resultado obtenido al resolver la ecuacion 
     */
    public Double result(){
        Double r;
        if (root == null) {
            //Si no hay ecuacion devuelve 0
            return Double.valueOf(0);
        }else{
            //Consigue la ecuacion en forma polaca inversa
            postOrden();
            System.out.println();
            //Recorre la pila polaca inversa
            for (int i = 0; i < polacaInv.size(); i++) {
                r = Double.valueOf(0);
                //Coloco una bandera para realizar una operacion a la vez
                Boolean ready = false;
                try {
                    //Trato de convertir el valor del nodo a entero
                    Double.parseDouble(polacaInv.get(i+2).valor);
                } catch (NumberFormatException e) {
                    //Si no puedo convertirlo se que es la operacion
                    //por lo que la realizo y voy eliminando
                    //los numeros operador con la operacion de la pila
                    if (polacaInv.get(i+2).valor.contains("+") && !ready) {
                        r = Double.parseDouble(polacaInv.remove(i).valor);
                        r += Double.parseDouble(polacaInv.remove(i).valor);
                        polacaInv.remove(i);
                        polacaInv.add(i, new Nodo(String.valueOf(r)));
                        i = -1;
                        ready = true;
                    }else if (polacaInv.get(i+2).valor.contains("-") && !ready){
                        r = Double.parseDouble(polacaInv.remove(i).valor);
                        r -= Double.parseDouble(polacaInv.remove(i).valor);
                        polacaInv.remove(i);
                        polacaInv.add(i, new Nodo(String.valueOf(r)));
                        i = -1;
                        ready = true;
                    }else if (polacaInv.get(i+2).valor.contains("*") && !ready){
                        r = Double.parseDouble(polacaInv.remove(i).valor);
                        r *= Double.parseDouble(polacaInv.remove(i).valor);
                        polacaInv.remove(i);
                        polacaInv.add(i, new Nodo(String.valueOf(r)));
                        i = -1;
                        ready = true;
                    }else if (polacaInv.get(i+2).valor.contains("/") && !ready){
                        r = Double.parseDouble(polacaInv.remove(i).valor);
                        r /= Double.parseDouble(polacaInv.remove(i).valor);
                        polacaInv.remove(i);
                        polacaInv.add(i, new Nodo(String.valueOf(r)));
                        i = -1;
                        ready = true;
                    }else if (polacaInv.get(i+2).valor.contains("^") && !ready){
                        Double exp;
                        r = Double.parseDouble(polacaInv.remove(i).valor);
                        exp = Double.parseDouble(polacaInv.remove(i).valor);
                        r = Math.pow(r, exp);
                        polacaInv.remove(i);
                        polacaInv.add(i, new Nodo(String.valueOf(r)));
                        i = -1;
                        ready = true;
                    }else if (polacaInv.get(i+2).valor.contains("r") && !ready){
                        Double sqrt;
                        sqrt = Double.parseDouble(polacaInv.remove(i).valor);
                        r = Double.parseDouble(polacaInv.remove(i).valor);
                        r = Math.pow(r, 1/sqrt);
                        polacaInv.remove(i);
                        polacaInv.add(i, new Nodo(String.valueOf(r)));
                        i = -1;
                        ready = true;
                    }
                }catch (ArrayIndexOutOfBoundsException max){
                    //Cuando se realizan todas las operaciones se rompe el ciclo
                    break;
                }
            }
        }
        return Double.parseDouble(polacaInv.remove(0).valor);
    }

    private void add(Nodo padre, Nodo hijo, int lado){
	if(lado==0)
        padre.setIzq(hijo);
	else
        padre.setDer(hijo);
    }
}