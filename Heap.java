package ArbolesBinarios;

import java.util.*;
public class Heap {
    int heapSize;
    LinkedList<Integer> registro = new LinkedList<Integer>();
    public void Heap()
    {
        
    }
    public void Swap(int a,int b)
    {
        int aux=registro.get(a);
        registro.set(a, registro.get(b));
        registro.set(b,aux);
    }
    public void Heapify(int i)
    {
        int l=2*i+1;
        int r=2*i+2;
        int largest;
        if(l<=heapSize && registro.get(l)>registro.get(i))
            largest=l;
        else
            largest=i;
        if(r<=heapSize && registro.get(r)>registro.get(largest))
            largest=r;
        if(largest!=i)
        {
            Swap(i,largest);
            Heapify(largest);
        }
    }
    public void BuildHeap()
    {
        heapSize=registro.size()-1;
        int i;
        for(i = (registro.size()-1)/2;i>=0;i--)
        {
            Heapify(i);
        }
    }
    
    public void Add(int nuevo)
    {
        registro.add(nuevo);
        BuildHeap();
    }
    
    public void RemoveRoot()
    {
        if(registro.size()==0)
        {
            System.out.println("El Heap ya está vacío");
            return;
        }
        registro.remove(0);
        BuildHeap();
    }
    
    public void PrintHeap()
    {
        for(int i=0;i<registro.size();i++)
            System.out.print(registro.get(i)+" ");
        System.out.println();
    }
    public void newHeap()
    {
        int no=-9;
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese el no. de elementos con los que desea crear el heap");
        while(no<=0)
        {
            no=read.nextInt();
            if(no<=0)
                System.out.println("Por favor ingrese una cantidad mayor a cero");
        }
        for(int i=0;i<no;i++)
        {
            
        }
        read.close();
    }
}
