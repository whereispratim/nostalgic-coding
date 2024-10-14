package lrucache;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> {
    private final int givenSize;
    //LinkedHashMap has <removeEldestEntry > method, which decides the eviction policy.
    private final LinkedHashMap<K, V> cache;

    public LRUCache(int givenSize) {
        this.givenSize = givenSize;
        // true for access-order (used for LRU), so  true : access, false : insertion
        this.cache = new LinkedHashMap<K, V>(givenSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                // Evict the least recently used entry when cache exceeds the size limit, here size() default method of map
                return size() > givenSize;
            }
        };
    }

    public V get(K key) {
        return cache.getOrDefault(key, null);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lru = new LRUCache<>(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));       // Output: 1
        lru.put(3, 3);
        System.out.println(lru.get(2));       // Output: null
        lru.put(4, 4);
        System.out.println(lru.get(1));       // Output: null
        System.out.println(lru.get(3));       // Output: 3
        System.out.println(lru.get(4));       // Output: 4
    }
}