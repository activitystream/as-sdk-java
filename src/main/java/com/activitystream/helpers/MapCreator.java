package com.activitystream.helpers;

import com.activitystream.underware.Factories;

import java.util.Map;

public class MapCreator<K, V> {
    Map<K, V> backend = Factories.getMap();

    private void add(KVPair pair) {
        backend.put(pair.key, pair.value);
    }

    public KVPair key(K key) {
        return new KVPair(key, this);
    }

    public Map<K, V> map() {return backend;}

    public class KVPair {
        private K key;
        private MapCreator<K, V> owner;
        private V value;

        public KVPair(K key, MapCreator<K, V> owner) {
            this.key = key;
            this.owner = owner;
        }

        public MapCreator<K, V> value(V value) {
            this.value = value;
            owner.add(this);
            return owner;
        }
    }
}

