## LRU Cache

### Problem Statement

Design a data structure that implements the LRU (Least Recently Used) cache mechanism. Your implementation should support the following operations:

- `LRUCache(int capacity)`: Initialize the LRU cache with positive size capacity.
- `int get(int key)`: Get the value of the key if the key exists in the cache, otherwise return -1.
- `void put(int key, int value)`: Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up: Could you do both operations in O(1) time complexity?

#### Example 1:
```
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1); // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2); // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1); // return -1 (not found)
lRUCache.get(3); // return 3
lRUCache.get(4); // return 4
```
### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
from collections import OrderedDict

class LRUCache:
def __init__(self, capacity: int):
self.capacity = capacity
self.cache = OrderedDict()

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        self.cache.move_to_end(key)
        return self.cache[key]

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.cache.move_to_end(key)
        self.cache[key] = value
        if len(self.cache) > self.capacity:
            self.cache.popitem(last=False)

cache = LRUCache(2)
cache.put(1, 1)
cache.put(2, 2)
print(cache.get(1))       # Output: 1
cache.put(3, 3)
print(cache.get(2))       # Output: -1
cache.put(4, 4)
print(cache.get(1))       # Output: -1
print(cache.get(3))       # Output: 3
print(cache.get(4))       # Output: 4
</code></pre>
</td>
<td>
<pre><code class="java">
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
</code></pre>
</td>
</tr>
</table>

### Explanation

In this implementation, we leverage the `LinkedHashMap` class in Java, which provides a hash table and linked list implementation of the `Map` interface, with predictable iteration order. By setting the access-order parameter to true in the constructor, we ensure that the iteration order is the order in which its entries were last accessed, from least-recently accessed to most-recently.

1. We extend `LinkedHashMap` to create our `LRUCache` class.
2. In the constructor, we call the superclass constructor with:
    - `capacity`: initial capacity
    - `0.75f`: load factor (default value)
    - `true`: access-order (instead of insertion-order)
3. We override the `removeEldestEntry` method to remove the least recently used entry when the size exceeds the capacity.
4. The `get` method uses `getOrDefault` to return null if the key is not found (equivalent to returning -1 in the original problem).
5. The `put` method simply calls the superclass `put` method, as `LinkedHashMap` already handles moving the entry to the end of the access order.

### Complexity Analysis

- Time Complexity: O(1) for both `get` and `put` operations.
- Space Complexity: O(capacity) to store at most `capacity` number of key-value pairs.

### Key Observations

1. `LinkedHashMap` in Java provides a built-in implementation that's very close to what we need for an LRU cache.
2. By setting `accessOrder` to `true` in the constructor, we get the LRU behavior for free.
3. Overriding `removeEldestEntry` allows us to automatically remove the least recently used item when the cache is full.
4. This implementation is more concise and leverages the Java standard library, potentially making it more robust and easier to maintain.


### LeetCode Link

[146. LRU Cache](https://leetcode.com/problems/lru-cache/)