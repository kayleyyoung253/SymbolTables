public class SeparateChainingHashST<K, V> implements SymbolTable<K, V>{
    //is good for items that are not comparable
    //private fields:
    //array of linked lists
    private SequentialSearchST<K, V>[] table;
    //how many buckets = tableSize; (size of the table array)
    private int tableSize;

    //number of actual keys in the table
    private int size;

    public SeparateChainingHashST(int tableSize){
        this.tableSize = tableSize;
        size = 0;
        //creates an array (each element is default initialized to null)
        table = new SequentialSearchST[tableSize];
        //loop through array. replace null with an empty linked list object
        for (int i = 0; i < tableSize; i++) {
            table[i] = new SequentialSearchST();
        }
    }
    public SeparateChainingHashST(){
        //call the other constructor and set up 997 buckets(this is a random number made for this example)
        this(997);
    }

    //private helper method - the hash function
    private int hash(K key) {
        //take a key and generate an index number
        //simple way: return key.hashCode() % tableSize;
        return (key.hashCode() &0x7fffffff) % tableSize;
    }
    /**
     * Put a key-value pair into the table
     *
     * @param k
     * @param val
     */
    @Override
    public void put(K k, V val) {
        //if the table doesn't contain the key, bump the size up
        if (table[hash(k)].contains(k)){
            size++;
        }
        //add the new key
        //or replace the value associated with the key if already there
        table[hash(k)].put(k, val);
    }

    /**
     * Returns the value paired with the given key.
     *
     * @param k
     */
    @Override
    public V get(K k) {
        //once I get to that bucket, pull the item out
        return table[hash(k)].get(k);
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator that refers to all the keys
     * in the table.
     */
    @Override
    public Iterable<K> keys() {
        //create an empty queue as a collector/container
        Queue<K> q = new LinkedQueue<>();

        //loop through table and collect keys
        for (int i = 0; i < tableSize; i++) {
            for (K singleKey : table[i].keys()){
                q.enqueue(singleKey);
            }
        }
        return q;
    }
}
