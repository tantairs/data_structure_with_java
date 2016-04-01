package com.lianxi.data_structure_with_java;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

/**
 * Created by tantairs on 16/4/1.
 */
public class Map<KeyType,ValueType> {
    public Map(){
        items = new QuadraticProbingHashTable<Entry<KeyType, ValueType>>();
    }

    public void put(KeyType key, ValueType val){
        Entry<KeyType,ValueType> e = new Entry<KeyType,ValueType>(key,val);
        items.insert(e);
    }

    public ValueType get(KeyType key){
        ValueType v = (ValueType)new Object();
        Entry<KeyType,ValueType> e = new Entry<KeyType,ValueType>(key,v);
        e = items.find(e);
        return e.value;
    }

    public boolean isEmpty(){

        return items.isEmpty();
    }

    public void makeEmpty(){
        items.makeEmpty();
    }

    private QuadraticProbingHashTable<Entry<KeyType,ValueType>> items;

    private static class Entry<KeyType,ValueType>{
        Entry(KeyType k, ValueType v){
            key = k;
            value = v;
        }
        KeyType key;
        ValueType value;

        @Override
        public boolean equals(Object o) {
           return o instanceof Entry && key.equals(((Entry<KeyType,ValueType>)o).key);
        }

        @Override
        public int hashCode() {
          return key.hashCode();
        }
    }

}
