package Marks;

import java.lang.reflect.Array;
import java.util.*;
import Marks.dummy.DefaultNotSupportedSet;

public class SimpleHashMap<K, V> {

    protected HashMapEntry[] table;
    protected int size = 0;

    public SimpleHashMap(int capacity) {
        table = (HashMapEntry[]) Array.newInstance(HashMapEntry.class, capacity);
    }

    public SimpleHashMap() {
        this(16);
    }

    private class HashMapEntry implements Map.Entry<K, V> {

        public K key;
        public V value;
        public HashMapEntry next;

        public HashMapEntry(K key, V value, HashMapEntry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    private int getIndex(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        return index;
    }

    private HashMapEntry getEntry(Object key, int index) {
        if (index < 0) {
            index = getIndex(key);
        }
        for (HashMapEntry curr = table[index]; curr != null; curr = curr.next) {
            if (key.equals(curr.key)) {
                return curr;
            }
        }
        return null;
    }

    public V get(Object key) {
        int index = getIndex(key);
        HashMapEntry item = getEntry(key, index);
        return (item == null) ? null : item.value;
    }

    public V put(K key, V value) {
        int index = getIndex(key);
        HashMapEntry item = getEntry(key, index);
        if (item != null) {
            V oldValue = item.value;
            item.value = value;
            return oldValue;
        }
        table[index] = new HashMapEntry(key, value, table[index]);
        size++;
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return new DefaultNotSupportedSet<>() {
            @Override
            public Iterator<Map.Entry<K, V>> iterator() {
                return new Iterator<>() {
                    int tableIndex = -1;
                    HashMapEntry curr = null;

                    {
                        findNext();
                    }

                    private void findNext() {
                        if (tableIndex >= table.length) {
                            return;
                        }
                        if (curr != null) {
                            curr = curr.next;
                        }
                        if (curr == null) {
                            for (tableIndex = tableIndex + 1; tableIndex < table.length; tableIndex++) {
                                curr = table[tableIndex];
                                if (curr != null) {
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return curr != null;
                    }

                    @Override
                    public Map.Entry<K, V> next() {
                        Map.Entry<K, V> temp = curr;
                        findNext();
                        return temp;
                    }
                };
            }
        };
    }
}