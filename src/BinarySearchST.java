public class BinarySearchST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
//arrays keeping keys in order
    //private fields
    private Key[] keys;   //array of keys
    private Value[] values;   //array of values
    private int size;

    public BinarySearchST(int capacity){
        //capacity is the potential space we will use
        //size is the actual space used
        //wanted to do   keys = new Key[capacity];  but java doesn't do it.
        // so this below is a workaround (ugly!)
        keys = (Key[])new Comparable[capacity];
        values = (Value[])new Object[capacity];

    }
    //helper method
    private int rank(Key key){
        //returns the index of where the key is located
        int low = 0;          //low index
        int high = size-1;    //high index

        while(low <= high) {
            int mid = low + (high - low) / 2;   // find halfway/mid index
            int cmp = key.compareTo(keys[mid]);

            if (cmp< 0){                    // if ( key < key[mid])
                high = mid - 1;
            }
            else if (cmp > 0) {             //else if (key > key[mid])
                low = mid + 1;
            }
            else{                          // else we know key == key[mid]
                return mid;
            }
        }

        return low;
    }

    /**
     * Put a key-value pair into the table
     *
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val) {
        int i = rank(key);

        if (i < size && key.compareTo(keys[i]) == 0){
            //we found the key in the keys array
            //so that means a value already exists
            //and we overwrite the existing value and DONE
            values[i] =  val;
            return;
        }

        //if we got here, we know the key is not in the array
        //shift values
        for (int j = size; j > i; j--){
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }

        //once we get here, shifting is done and theres a spot
        keys[i] = key;
        values[i] = val;
        size++;


    }

    /**
     * Returns the value paired with the given key.
     *
     * @param key
     */
    @Override
    public Value get(Key key) {
        if(isEmpty()){
            return null;
        }

        int i = rank(key);
        //rank() is going to give us the index of where key is located
        //or will give us the index of where key should go

       // if (key.equals(keys[i])){
        if (i < size && key.compareTo(keys[i]) == 0){
            return values[i];
        }
        else {
            return null;
        }
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
    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedQueue<>();

        //walk through keys array and enqueue all the keys
        for (int i = 0;  i < size; i++) {
            q.enqueue(keys[i]);
        }
        return q;
    }
}
