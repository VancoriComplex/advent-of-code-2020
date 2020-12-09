package day7;

import java.util.Map;

interface BagMap<K, V> extends Map<K, V> {

    default V put(Entry<K,V> entry) {
        return put(entry.getKey(), entry.getValue());
    }
}
