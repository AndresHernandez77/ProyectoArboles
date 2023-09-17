
package ArbolesBinarios;

public class Nodo_AVL {
	int valor,balan;//balan factor de balance 
	Nodo_AVL par;//para ubicar al padre
	Nodo_AVL izq; //Hijo izquierdo
	Nodo_AVL der; //Hijo derecho
        //Método constructor
	public Nodo_AVL(int data) {
		this.valor = data;
		this.par = null;
		this.izq = null;
		this.der= null;
		this.balan = 0;
	}
}

