# 🔄 Iterator Design Pattern

## 📚 Pattern haqida ma'lumot

> *Iterator pattern - bu kolleksiyaning ichki strukturasini ochib bermasdan uning elementlarini ketma-ket o'tish imkonini beruvchi xulq-atvor (behavioral) dizayn namunasi.*
> 
> — Gang of Four (GoF)

Iterator design pattern - bu **xulq-atvor (behavioral)** pattern kategoriyasiga kiradi. Bu pattern kolleksiyaning ichki tuzilishini bilmasdan uning elementlarini birma-bir o'tish imkonini beradi. Iterator real hayotda kitobning sahifalarini aylantirish kabi ishlaydi - har safar keyingi sahifaga o'tish imkonini beradi.

[Iterator Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/iterator/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Kolleksiya ichki strukturasini yashirish
- Mijoz kodi kolleksiyaning qanday saqlanganligi haqida bilmasligi kerak
- Array, List, Tree, Stack - har xil strukturalar bir xil interfeys orqali ishlatilishi kerak
- Encapsulation tamoyilini saqlash

### 2️⃣ Turli o'tish usullari
- Bir xil kolleksiyani turli yo'llar bilan o'tish kerak bo'lishi mumkin
- DFS, BFS, inorder, preorder, postorder kabi turli algoritmlar
- Forward va backward yo'nalishda o'tish

### 3️⃣ Bir nechta iteratsiya bir vaqtda
- Bir xil kolleksiyani bir vaqtda bir nechta joyda o'tish kerak
- Har bir iterator o'z holatini saqlab turishi kerak
- Bir-biriga ta'sir qilmasligi kerak

### 4️⃣ Kolleksiya o'zgarishi
- Iteratsiya davomida kolleksiya o'zgarishi mumkin
- Fail-fast behavior - o'zgarish aniqlanganda exception berish
- Concurrent access bilan ishlash

---

## 💡 Qanday hal qiladi

### ✅ Iterator interfeysi
- Barcha iteratorlar uchun yagona interfeys yaratadi
- hasNext() va next() metodlari orqali standart API
- Kolleksiya turidan qat'i nazar bir xil ishlash prinsipi

### ✅ Ichki holatni saqlash
- Har bir iterator o'z pozitsiyasini mustaqil saqlaydi
- Bir nechta iterator bir vaqtda ishlashi mumkin
- Kolleksiya haqida bilmasdan faqat joriy holatni boshqaradi

### ✅ Lazy evaluation
- Elementlar faqat kerak bo'lganda qaytariladi
- Xotira tejash va performance yaxshilash
- Cheksiz kolleksiyalar bilan ishlash imkoniyati

### ✅ Polimorfizm
- Turli kolleksiyalar bir xil interfeys orqali ishlatiladi
- Kodni o'zgartirmasdan turli implementatsiyalarni almashish
- Strategy pattern bilan yaxshi ishlaydi

---

## 🧩 Implementatsiyasi

Iterator Pattern besh asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Iterator** | Elementlarni o'tish uchun interfeys |
| **ConcreteIterator** | Iterator interfeysining konkret implementatsiyasi |
| **Aggregate** | Kolleksiya uchun interfeys |
| **ConcreteAggregate** | Kolleksiyaning konkret implementatsiyasi |
| **Client** | Iterator orqali kolleksiya bilan ishlovchi kod |

### 💻 Java implementatsiyasi

```java
// 1. Iterator interface - elementlarni o'tish uchun
public interface Iterator<T> {
    boolean hasNext();
    T next();
    default void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}

// 2. Aggregate interface - kolleksiya uchun
public interface Aggregate<T> {
    Iterator<T> createIterator();
}

// 3. ConcreteIterator - massiv uchun iterator
public class ArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int position = 0;
    
    public ArrayIterator(T[] array) {
        this.array = array;
    }
    
    @Override
    public boolean hasNext() {
        return position < array.length && array[position] != null;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        return array[position++];
    }
    
    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException("next() not called yet");
        }
        // Elementni o'chirish logikasi
        array[position - 1] = null;
    }
}

// 4. ConcreteAggregate - massiv kolleksiyasi
public class ArrayCollection<T> implements Aggregate<T> {
    private final T[] items;
    private int size = 0;
    
    @SuppressWarnings("unchecked")
    public ArrayCollection(int capacity) {
        this.items = (T[]) new Object[capacity];
    }
    
    public void add(T item) {
        if (size < items.length) {
            items[size++] = item;
        }
    }
    
    @Override
    public Iterator<T> createIterator() {
        return new ArrayIterator<>(items);
    }
    
    // Reverse iterator yaratish
    public Iterator<T> createReverseIterator() {
        return new ReverseArrayIterator<>(items, size);
    }
}

// 5. Reverse iterator - teskari yo'nalishda o'tish
public class ReverseArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int position;
    
    public ReverseArrayIterator(T[] array, int size) {
        this.array = array;
        this.position = size - 1;
    }
    
    @Override
    public boolean hasNext() {
        return position >= 0 && array[position] != null;
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        return array[position--];
    }
}

// 6. Client - Iterator orqali ishlash
public class IteratorPatternDemo {
    public static void main(String[] args) {
        // Kolleksiya yaratish
        ArrayCollection<String> collection = new ArrayCollection<>(5);
        collection.add("Java");
        collection.add("Python");
        collection.add("JavaScript");
        collection.add("Go");
        
        // Forward iterator
        System.out.println("Forward iteration:");
        Iterator<String> iterator = collection.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        // Reverse iterator
        System.out.println("\nReverse iteration:");
        Iterator<String> reverseIterator = collection.createReverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
        
        // Bir nechta iterator bir vaqtda
        System.out.println("\nMultiple iterators:");
        Iterator<String> it1 = collection.createIterator();
        Iterator<String> it2 = collection.createIterator();
        
        while (it1.hasNext() && it2.hasNext()) {
            System.out.println("IT1: " + it1.next() + ", IT2: " + it2.next());
        }
    }
}
```

---

## 📋 Iterator Pattern turlari

### 1. **External Iterator (Active Iterator)**
```java
// Mijoz iterator holatini boshqaradi
public class ExternalIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int currentIndex = 0;
    
    public ExternalIterator(List<T> list) {
        this.list = list;
    }
    
    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }
    
    @Override
    public T next() {
        return list.get(currentIndex++);
    }
}
```

### 2. **Internal Iterator (Passive Iterator)**
```java
// Kolleksiya o'zi iteratsiyani boshqaradi
public class InternalIterator<T> {
    private final List<T> list;
    
    public InternalIterator(List<T> list) {
        this.list = list;
    }
    
    public void forEach(Consumer<T> action) {
        for (T item : list) {
            action.accept(item);
        }
    }
    
    public <R> List<R> map(Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(mapper.apply(item));
        }
        return result;
    }
}
```

### 3. **Bidirectional Iterator**
```java
// Ikki yo'nalishda o'tish imkoniyati
public interface BidirectionalIterator<T> extends Iterator<T> {
    boolean hasPrevious();
    T previous();
    
    // ListIterator kabi
    int nextIndex();
    int previousIndex();
    void set(T element);
    void add(T element);
}
```

### 4. **Filtered Iterator**
```java
// Filterlash bilan iterator
public class FilteredIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Predicate<T> predicate;
    private T nextElement;
    private boolean hasNext;
    
    public FilteredIterator(Iterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        findNext();
    }
    
    private void findNext() {
        hasNext = false;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(element)) {
                nextElement = element;
                hasNext = true;
                break;
            }
        }
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public T next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        T current = nextElement;
        findNext();
        return current;
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **File System Iterator**
```java
// Fayl tizimini o'tish
public class FileSystemIterator implements Iterator<File> {
    private final Stack<File> stack;
    private final boolean recursive;
    
    public FileSystemIterator(File root, boolean recursive) {
        this.stack = new Stack<>();
        this.recursive = recursive;
        if (root.exists()) {
            stack.push(root);
        }
    }
    
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    @Override
    public File next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        File current = stack.pop();
        
        if (current.isDirectory() && recursive) {
            File[] children = current.listFiles();
            if (children != null) {
                for (File child : children) {
                    stack.push(child);
                }
            }
        }
        
        return current;
    }
}
```

### 2. **Database Result Iterator**
```java
// Database natijalarini o'tish
public class DatabaseResultIterator implements Iterator<Map<String, Object>> {
    private final ResultSet resultSet;
    private final ResultSetMetaData metaData;
    private boolean hasNext;
    
    public DatabaseResultIterator(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        this.metaData = resultSet.getMetaData();
        this.hasNext = resultSet.next();
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }
    
    @Override
    public Map<String, Object> next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        
        try {
            Map<String, Object> row = new HashMap<>();
            int columnCount = metaData.getColumnCount();
            
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(i);
                row.put(columnName, value);
            }
            
            hasNext = resultSet.next();
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

### 3. **Tree Iterator**
```java
// Daraxt strukturasini o'tish
public class TreeIterator<T> implements Iterator<T> {
    private final Stack<TreeNode<T>> stack;
    private final TreeTraversalType type;
    
    public enum TreeTraversalType {
        PREORDER, INORDER, POSTORDER, LEVEL_ORDER
    }
    
    public TreeIterator(TreeNode<T> root, TreeTraversalType type) {
        this.stack = new Stack<>();
        this.type = type;
        
        if (root != null) {
            switch (type) {
                case PREORDER:
                    stack.push(root);
                    break;
                case INORDER:
                    pushLeftPath(root);
                    break;
                // Boshqa traversal turlari...
            }
        }
    }
    
    private void pushLeftPath(TreeNode<T> node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        TreeNode<T> current = stack.pop();
        
        if (type == TreeTraversalType.INORDER && current.right != null) {
            pushLeftPath(current.right);
        }
        
        return current.data;
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Encapsulation** | Kolleksiyaning ichki strukturasini yashiradi |
| **Polymorphism** | Turli kolleksiyalar uchun yagona interfeys |
| **Lazy evaluation** | Elementlar faqat kerak bo'lganda qaytariladi |
| **Multiple iteration** | Bir nechta iterator bir vaqtda ishlashi mumkin |
| **Flexible traversal** | Turli o'tish usullarini qo'llab-quvvatlaydi |
| **Memory efficiency** | Butun kolleksiyani xotiraga yuklamaslik |
| **Fail-fast behavior** | O'zgarishlarni aniqlash va xatolikni bildirish |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Kod murakkabligi** | Qo'shimcha klasslar va interfeyslarga ehtiyoj |
| **Performance overhead** | Har bir element uchun method call |
| **Memory usage** | Iterator obyektlari qo'shimcha xotira sarflaydi |
| **Concurrent modification** | Iteratsiya davomida kolleksiya o'zgarishi muammosi |
| **Debugging qiyinligi** | Iterator holatini kuzatish qiyin |
| **Overkill for simple cases** | Oddiy massivlar uchun ortiqcha |

---

## 🔄 Iterator vs boshqa pattern'lar

| Jihat | Iterator | Visitor | Observer | Strategy |
|-------|----------|---------|----------|----------|
| **Maqsad** | Elementlarni o'tish | Operatsiyalar qo'shish | Holatni kuzatish | Algoritmni almashtirish |
| **Kolleksiya** | Har doim kerak | Kerak | Kerak emas | Kerak emas |
| **Traversal** | Bir yo'nalish | Recursiv | Event-based | Algoritmik |
| **Holatni saqlash** | Pozitsiya | Natija | Holat | Parametrlar |

---

## 📚 JDK-da Iterator Pattern misollari

### 1. **Collections Framework**
```java
// ArrayList, LinkedList, HashSet va boshqalar
List<String> list = new ArrayList<>();
Iterator<String> iterator = list.iterator();

// Enhanced for loop ham Iterator ishlatadi
for (String item : list) {
    System.out.println(item);
}
```

### 2. **Scanner**
```java
// Scanner ham Iterator interfeysi implement qiladi
Scanner scanner = new Scanner(System.in);
while (scanner.hasNext()) {
    String input = scanner.next();
    System.out.println(input);
}
```

### 3. **ResultSet**
```java
// JDBC ResultSet Iterator pattern ishlatadi
ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
while (resultSet.next()) {
    String name = resultSet.getString("name");
    System.out.println(name);
}
```

### 4. **Stream API**
```java
// Java 8 Stream ham Iterator pattern ishlatadi
List<String> names = Arrays.asList("John", "Jane", "Bob");
names.stream()
     .filter(name -> name.startsWith("J"))
     .forEach(System.out::println);
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Kolleksiyaning ichki strukturasini yashirish kerak
- Turli o'tish usullarini qo'llab-quvvatlash kerak
- Bir nechta iterator bir vaqtda ishlashi kerak
- Lazy evaluation kerak bo'lganda
- Katta kolleksiyalar bilan ishlashda
- Polymorphic traversal kerak bo'lganda

### ❌ Ishlatmaslik kerak:
- Oddiy massivlar uchun
- Faqat bir marta o'tish kerak bo'lganda
- Performance juda muhim bo'lganda
- Kolleksiya o'zgarmas bo'lganda
- Memory juda cheklangan bo'lganda

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Iterator + Composite**
```java
// Composite strukturani o'tish
public class CompositeIterator implements Iterator<Component> {
    private final Stack<Iterator<Component>> stack;
    
    public CompositeIterator(Iterator<Component> iterator) {
        this.stack = new Stack<>();
        stack.push(iterator);
    }
    
    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        
        Iterator<Component> iterator = stack.peek();
        if (!iterator.hasNext()) {
            stack.pop();
            return hasNext();
        }
        
        return true;
    }
    
    @Override
    public Component next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        Iterator<Component> iterator = stack.peek();
        Component component = iterator.next();
        
        if (component instanceof Composite) {
            stack.push(((Composite) component).createIterator());
        }
        
        return component;
    }
}
```

### 2. **Iterator + Factory Method**
```java
// Turli iteratorlar yaratish
public abstract class IteratorFactory<T> {
    public abstract Iterator<T> createIterator();
}

public class ForwardIteratorFactory<T> extends IteratorFactory<T> {
    private final List<T> list;
    
    public ForwardIteratorFactory(List<T> list) {
        this.list = list;
    }
    
    @Override
    public Iterator<T> createIterator() {
        return new ForwardIterator<>(list);
    }
}

public class ReverseIteratorFactory<T> extends IteratorFactory<T> {
    private final List<T> list;
    
    public ReverseIteratorFactory(List<T> list) {
        this.list = list;
    }
    
    @Override
    public Iterator<T> createIterator() {
        return new ReverseIterator<>(list);
    }
}
```

### 3. **Iterator + Strategy**
```java
// Traversal strategiyasi
public interface TraversalStrategy<T> {
    Iterator<T> createIterator(TreeNode<T> root);
}

public class PreorderStrategy<T> implements TraversalStrategy<T> {
    @Override
    public Iterator<T> createIterator(TreeNode<T> root) {
        return new TreeIterator<>(root, TreeIterator.TreeTraversalType.PREORDER);
    }
}

public class InorderStrategy<T> implements TraversalStrategy<T> {
    @Override
    public Iterator<T> createIterator(TreeNode<T> root) {
        return new TreeIterator<>(root, TreeIterator.TreeTraversalType.INORDER);
    }
}

public class TreeTraverser<T> {
    private TraversalStrategy<T> strategy;
    
    public void setStrategy(TraversalStrategy<T> strategy) {
        this.strategy = strategy;
    }
    
    public void traverse(TreeNode<T> root) {
        Iterator<T> iterator = strategy.createIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

---

## 🎯 Xulosa

Iterator Pattern:

* ✅ Kolleksiyaning ichki strukturasini yashiradi va encapsulation'ni ta'minlaydi
* ✅ Turli kolleksiyalar uchun yagona interfeys yaratadi
* ✅ Bir nechta iterator bir vaqtda ishlashi mumkin
* ✅ Lazy evaluation va memory efficiency'ni ta'minlaydi
* ✅ Polymorphic traversal va flexible o'tish usullarini qo'llab-quvvatlaydi

**Tavsiya**: Iterator pattern kolleksiyalar bilan ishlashda juda foydali va Java Collections Framework'ning asosiy qismidir. Kichik va oddiy kolleksiyalar uchun ortiqcha bo'lishi mumkin, lekin murakkab strukturalar uchun juda muhim pattern hisoblanadi.