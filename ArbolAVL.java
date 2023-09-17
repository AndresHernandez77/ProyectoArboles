
package ArbolesBinarios;

import java.util.LinkedList;
import java.util.Queue;


public class ArbolAVL {
    public Nodo_AVL raiz;//raíz del árbol AVL

    //Método constructor
    public ArbolAVL() {
		raiz = null;
	}
    
    //rotacione para acomodar el arbol para que siga siendo balanceado
    //rotacion izquierda
   public void rotacionIzquierda(Nodo_AVL nod) {
	Nodo_AVL auxiliar = nod.der;
	nod.der = auxiliar.izq;
      if (auxiliar.izq != null) {
		auxiliar.izq.par = nod;
	}
	auxiliar.par = nod.par;
  if (nod.par == null) {
      this.raiz = auxiliar;
	} else if (nod == nod.par.izq) {
	nod.par.izq = auxiliar;
    } else {
	nod.par.der = auxiliar;
	}
	auxiliar.izq = nod;
	nod.par = auxiliar;
	// actualiza el factor de balance
     nod.balan = nod.balan - 1 - Math.max(0,auxiliar.balan);
     auxiliar.balan = auxiliar.balan - 1 + Math.min(0,nod.balan);
	}
// rotacion derecha
  public void rotacionDerecha(Nodo_AVL nod) {
    Nodo_AVL auxi = nod.izq;
    nod.izq= auxi.der;
      if (auxi.der != null) {
	auxi.der.par = nod;
	}
      auxi.par = nod.par;
	if (nod.par == null) {
      this.raiz = auxi;
	} else if (nod == nod.par.der) {
        nod.par.der = auxi;
     } else {
	nod.par.izq = auxi;
	}
	auxi.der = nod;
	nod.par = auxi;

	// actualiza el factor de balance
	nod.balan = nod.balan + 1 - Math.min(0, auxi.balan);
	auxi.balan = auxi.balan + 1 + Math.max(0, nod.balan);
	}
  //Insertar un valor en el árbol AVL (incluyendo el rebalanceo para que se mantenga la estructura de un árbol AVL 
  public void Insertar(int dato) {
    Nodo_AVL nod = new Nodo_AVL(dato);
    Nodo_AVL pad = null;
    Nodo_AVL no = this.raiz;
      while (no != null) {
	pad = no;
	if (nod.valor < no.valor) {
	    no = no.izq;
	} else {
	    no= no.der;
	}
	   }
	nod.par = pad;
      if (pad == null) {
	 raiz = nod;
	 } else if (nod.valor < pad.valor) {
	    pad.izq = nod;
      } else {
	   pad.der = nod;
	 }
	 actualizarBalance(nod);
          
	}
  //Reestructuración del árbol para que se mantenga perfectamente balanceado 
  public void actualizarBalance(Nodo_AVL nodo) {
  if (nodo.balan < -1 || nodo.balan > 1) {
	Rebalancear(nodo);
	return;
}
    if (nodo.par != null) {
	if (nodo== nodo.par.izq) {
   nodo.par.balan -= 1;
    } 
    if (nodo == nodo.par.der) {
	nodo.par.balan += 1;
	}
    if (nodo.par.balan != 0) {
	actualizarBalance(nodo.par);
	 }
	   }
	}

 //Método encargado de determinar si es necesario hacer rotaciones para mantener el árbol balanceado
 public void Rebalancear(Nodo_AVL nodo) {
 if (nodo.balan > 0) {
	if (nodo.der.balan < 0) {
      rotacionDerecha(nodo.der);
     rotacionIzquierda(nodo);
	} else {
     rotacionIzquierda(nodo);
	}
 } else if (nodo.balan < 0) {
	if (nodo.izq.balan > 0) {
       rotacionIzquierda(nodo.izq);
	rotacionDerecha(nodo);
	} else {
	rotacionDerecha(nodo);
		}
	   }
	}
   
 //Determinar si un valor se encuentra en el árbol AVL 
 public boolean Buscar(int valor){
        Nodo_AVL nod=raiz;
        if(raiz==null){
            return false;
        }else if(nod.valor==valor){
        return true;
    }else{
     while(nod.valor!=valor){
            if(valor<nod.valor){
                nod=nod.izq;
            }else{
                nod=nod.der;
            }
            if(nod==null){
                return false;
            }
            
        }
        return true;
        }
    }
    //Devuelve el nodo_AVL en el que se encuentra el valor deseado
    public Nodo_AVL HallarValor(int v){
        Nodo_AVL r = raiz;
        Nodo_AVL location = null;
	Queue<Nodo_AVL> queue = new LinkedList<Nodo_AVL>();
	if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r = (Nodo_AVL)queue.poll();
		if(r.valor==v){
                    location=r;
                }
		if(r.izq!=null)
                    queue.add(r.izq);
		if(r.der!=null)
                    queue.add(r.der);
            }
	}
        return location;
    }

    //Eliminar un valor
    public void Eliminar(int eliminar) {
      LinkedList<Integer> contenido = new LinkedList<Integer>();
      Queue<Nodo_AVL> queue = new LinkedList<Nodo_AVL>();
      Nodo_AVL r = raiz;
      Nodo_AVL quitar=HallarValor(eliminar),reemplazo;
      if (quitar == null){ 
         return;
      }
      reemplazo=quitar;
      if(reemplazo.der!=null)
      {
          reemplazo=reemplazo.der;
          while(reemplazo.izq!=null)
          {
              reemplazo=reemplazo.izq;
          }
      }
      else if(reemplazo.izq!=null)
      {
          reemplazo=reemplazo.izq;
          while(reemplazo.der!=null)
          {
              reemplazo=reemplazo.der;
          }
      }
      
      quitar.valor=reemplazo.valor;
      reemplazo.valor=eliminar;
	
      if(r!=null){
        queue.add(r);
        while(!queue.isEmpty()){
            r = (Nodo_AVL)queue.poll();
            contenido.addLast(r.valor);
            if(r.izq!=null)
               queue.add(r.izq);
            if(r.der!=null)
               queue.add(r.der);
        }
      }
      raiz.izq=null;
      raiz.der=null;
      raiz.balan=0;
      raiz.valor=contenido.getFirst();
      contenido.remove(Integer.valueOf(eliminar));
      for(int i=1;i<contenido.size();i++)
      {
          Insertar(contenido.get(i));
      } 
    }

//Método para imprimir un nodo visitado
  protected void visit(Nodo_AVL n){
       System.out.println(n.valor+" ");
    }    
        
  //Método para imprimir el recorrido BFS del árbol AVL
  public void breadthFrist(){
        if(raiz==null)
        {
            System.out.println("El árbol está vacío");
            return;
        }
        Nodo_AVL r = raiz;
	Queue<Nodo_AVL> queue = new LinkedList<Nodo_AVL>();
	if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r = queue.poll();
		visit(r);
		if(r.izq!=null)
                    queue.add(r.izq);
		if(r.der!=null)
		queue.add(r.der);
            }
	}
    }  
}
