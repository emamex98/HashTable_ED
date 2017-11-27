import java.util.NoSuchElementException;

public class HashTchain <K, V>{


	private int m;
	private NodeH<K,V>[] table;
	private int n;
	private static final int INIT_CAPACITY=11;
	
	public HashTchain(){
		this.m=this.INIT_CAPACITY;
		this.table=(NodeH<K, V>[]) new NodeH[m];
		this.n=0;
	}
	
	public HashTchain(int m){
		this.m=m;
		this.table=(NodeH<K, V>[]) new NodeH[m];
		this.n=0;
	}
	
	private int hash(K k){
		return (k.hashCode()*17)%m;
	}
	
	public int size(){
		return n;
	}

	public boolean isEmpty(){
		return n==0;
	}

	public V get(K key){
		if(key==null){
			throw new NullPointerException("Invalido: clave nula");
		}
		int i=hash(key);
		for(NodeH<K, V> x=table[i];x!=null;x=x.next){
			if(key.equals(x.k)){
				return x.v;
			}
		}
		return null;
	}

	public void put(K key, V value){
		if(key==null || value==null){
			throw new NullPointerException("Valores de Key o value invalidos");
		}
		int i=hash(key);
		for(NodeH<K, V> x=table[i];x!=null;x=x.next){
			if(key.equals(x.k)){
				x.v=value;
				return;
			}
		}
		table[i]=new NodeH<K, V>(key, value,table[i]);
		n++;
	}
	
	public V remove(K key){
		if(isEmpty()){
			throw new NoSuchElementException("Hash vacia, no hay elementos");
		}
		int h = this.hash(key);
		NodeH<K,V> first = this.table[h];
		if(first.k == key){
			V aux = first.v;
			this.table[h] =  first.next; 
			this.n--;
			return aux;
		}
		for(NodeH<K,V> n = first; n.next!= null; n = n.next){
			if(n.k==key){
				V aux = n.next.v;
				n.next = n.next.next;
				this.n--;
				return aux;
			}
		}
		return null;
	}
	

	class NodeH<K, V>{
		private K k;
		private V v;
		private NodeH<K,V> next;
		
		public NodeH(K k, V v, NodeH<K, V> next){
			this.k=k;
			this.v=v;
			this.next=next;
		}
	}
		
	public static void  main(String[] args){
		HashTchain<Integer,Integer> h=new HashTchain<Integer,Integer>(37);
		h.put(1, 100);
		h.put(2, 99);
		h.put(3, 98);
		h.remove(2);
		System.out.println(h.get(3));
	}
}
