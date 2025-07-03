# 🏰 Singleton Design Pattern

## 📚 Pattern haqida ma'lumot

> *Singleton pattern klassning faqat bitta instance yaratilishini ta'minlaydi va unga global kirish nuqtasini beradi.*
> 
> — Gang of Four (GoF)

Singleton design pattern - bu obyekt yaratishning eng taniqli va ko'p tartishilgan usullaridan biri bo'lib, u **yaratish (creational)** pattern kategoriyasiga kiradi. Bu pattern ma'lum bir klassning faqat bitta instance yaratilishini kafolatlaydi va bu instancega global kirish imkonini beradi.

[Singleton strukturasi](https://refactoring.guru/images/patterns/diagrams/singleton/structure-en.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Bitta instance kafolati
- Ma'lum bir klassning faqat bitta obyekti mavjud bo'lishi kerak bo'lganda
- Tizimda faqat bitta konfiguratsiya, logger, database connection pool bo'lishi kerak bo'lganda
- Resurs xarajatlarini kamaytirish uchun qayta ishlatiladigan obyekt kerak bo'lganda

### 2️⃣ Global kirish nuqtasi
- Obyektga dasturning istalgan qismidan kirish kerak bo'lganda
- Parametrlarni uzatish murakkab bo'lganda
- Statik o'zgaruvchilardan ko'ra ko'proq nazorat kerak bo'lganda

### 3️⃣ Resurslarni boshqarish
- Qimmat resurslarni (fayl, database connection, network connection) boshqarish kerak bo'lganda
- Thread-safe bo'lishi kerak bo'lganda
- Lazy initialization kerak bo'lganda

---

## 💡 Qanday hal qiladi

### ✅ Konstruktor yashirish
- Private konstruktor orqali tashqaridan obyekt yaratishni oldini oladi
- Faqat klass o'zi o'zidan instance yarata oladi

### ✅ Statik method orqali kirish
- getInstance() methodi orqali yagona instancega kirish imkonini beradi
- Lazy yoki eager initialization mumkin

### ✅ Thread-safety
- Synchronized methodlar yoki bloklar orqali thread-safe bo'lishini ta'minlaydi
- Double-checked locking pattern dan foydalanish mumkin

---

## 🧩 Implementatsiya usullari

Singleton patterni bir nechta usulda amalga oshirilishi mumkin:

### 1️⃣ Eager Initialization
```java
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    private EagerSingleton() {}
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
```

### 2️⃣ Lazy Initialization
```java
public class LazySingleton {
    private static LazySingleton instance;
    
    private LazySingleton() {}
    
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

### 3️⃣ Thread-Safe Singleton
```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {}
    
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```

### 4️⃣ Double-Checked Locking
```java
public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;
    
    private DoubleCheckedSingleton() {}
    
    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```

### 5️⃣ Enum Singleton (Eng yaxshi usul)
```java
public enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

---

## 💻 Amaliy implementatsiya

### 🏰 IvoryTower klassi (Oddiy Singleton)

```java
public final class IvoryTower {
    
    private IvoryTower() {
        // Private konstruktor - tashqaridan obyekt yaratish mumkin emas
    }
    
    // Eager initialization
    private static final IvoryTower INSTANCE = new IvoryTower();
    
    // Global kirish nuqtasi
    public static IvoryTower getInstance() {
        return INSTANCE;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + hashCode();
    }
}
```

### 🏰 EnumIvoryTower (Enum-based Singleton)

```java
public enum EnumIvoryTower {
    INSTANCE;
    
    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
    
    public void doSomething() {
        System.out.println("Enum singleton is working...");
    }
}
```

### 🖥️ Client kod

```java
public class App {
    public static void main(String[] args) {
        
        // Oddiy singleton
        IvoryTower instance1 = IvoryTower.getInstance();
        IvoryTower instance2 = IvoryTower.getInstance();
        
        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
        System.out.println("Same instance? " + (instance1 == instance2));
        
        // Enum singleton
        EnumIvoryTower enumInstance1 = EnumIvoryTower.INSTANCE;
        EnumIvoryTower enumInstance2 = EnumIvoryTower.INSTANCE;
        
        System.out.println("\nenumInstance1 = " + enumInstance1);
        System.out.println("enumInstance2 = " + enumInstance2);
        System.out.println("Same enum instance? " + (enumInstance1 == enumInstance2));
        
        enumInstance1.doSomething();
    }
}
```

---

## ✅ Qulaylik tomonlari

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Bitta instance kafolati** | Klassning faqat bitta obyekti yaratilishini ta'minlaydi. Xotira va resurslarni tejaydi. |
| **Global kirish** | Dasturning istalgan qismidan singleton obyektga kirish mumkin. Parametr uzatish kerak emas. |
| **Lazy initialization** | Obyekt faqat kerak bo'lganda yaratiladi. Dastur ishga tushish vaqtini kamaytiradi. |
| **Thread-safety** | To'g'ri amalga oshirilsa, ko'p threadli muhitda xavfsiz ishlaydi. |
| **Konfiguratsiya boshqaruvi** | Tizim konfiguratsiyalarini markazlashtirilgan tarzda boshqarish uchun juda qulay. |

---

## ⚠️ Kamchilik tomonlari

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Global holatni buzish** | Global o'zgaruvchilar kabi, tizimning holati haqida qiyinchilik tug'diradi. |
| **Testlash qiyinligi** | Unit testlarda mock qilish qiyin. Testlar bir-biriga ta'sir qilishi mumkin. |
| **Yashirin bog'liqliklar** | Kod bog'liqliklarini yashiradi, bu dependency injection ga zid. |
| **Single Responsibility buzilishi** | Klass ham o'z vazifasini bajaradi, ham singleton xususiyatini ta'minlaydi. |
| **Multithreading muammolari** | Noto'g'ri amalga oshirilsa, thread-safety muammolari yuzaga keladi. |
| **Serialization muammolari** | Deserializatsiya jarayonida yangi instance yaratilishi mumkin. |

---

## 🔐 Singleton buzish usullari va himoya

### 1️⃣ Reflection orqali buzish
```java
// Muammo
Constructor<IvoryTower> constructor = IvoryTower.class.getDeclaredConstructor();
constructor.setAccessible(true);
IvoryTower newInstance = constructor.newInstance();

// Himoya
private IvoryTower() {
    if (INSTANCE != null) {
        throw new IllegalStateException("Singleton instance already created!");
    }
}
```

### 2️⃣ Serialization orqali buzish
```java
// Himoya
protected Object readResolve() {
    return INSTANCE;
}
```

### 3️⃣ Cloning orqali buzish
```java
// Himoya
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Singleton cannot be cloned!");
}
```

---

## 📚 JDK-da Singleton patterni misollari

Java Development Kit (JDK) da Singleton patterni misollari:

### 1. `java.lang.Runtime`
```java
Runtime runtime = Runtime.getRuntime();
```
> `getRuntime()` methodi Runtime klassining yagona instanceni qaytaradi.

### 2. `java.awt.Desktop`
```java
Desktop desktop = Desktop.getDesktop();
```
> `getDesktop()` methodi Desktop klassining singleton instanceni qaytaradi.

### 3. `java.lang.System`
```java
System.out.println("Hello World");
```
> `out`, `err`, va `in` static fieldlar singleton pattern printsipiga asoslanadi.

### 4. Spring Framework
```java
@Component
@Scope("singleton") // Default scope
public class MyService {
    // Spring container faqat bitta instance yaratadi
}
```

### 5. Logger klasslar
```java
Logger logger = LoggerFactory.getLogger(MyClass.class);
```
> Ko'p logging framework larda logger instancelar singleton pattern bo'yicha yaratiladi.

---

## 🏆 Eng yaxshi amaliyotlar

### ✅ Enum Singleton ishlatish
```java
public enum DatabaseConnection {
    INSTANCE;
    
    private Connection connection;
    
    DatabaseConnection() {
        // Initialize database connection
    }
    
    public Connection getConnection() {
        return connection;
    }
}
```

### ✅ Dependency Injection ishlatish
```java
// Singleton o'rniga DI container ishlatish
@Component
public class ConfigurationService {
    // Spring container bu klassni singleton qilib boshqaradi
}
```

### ✅ Thread-safe bo'lishni ta'minlash
```java
public class ThreadSafeCounter {
    private static volatile ThreadSafeCounter instance;
    private final AtomicInteger count = new AtomicInteger(0);
    
    private ThreadSafeCounter() {}
    
    public static ThreadSafeCounter getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeCounter.class) {
                if (instance == null) {
                    instance = new ThreadSafeCounter();
                }
            }
        }
        return instance;
    }
    
    public int increment() {
        return count.incrementAndGet();
    }
}
```

---

## 🎯 Qo'llash sohalari

### ✅ Mos kelgan holatlar
- **Logging**: Tizimda bitta logger instance
- **Configuration**: Tizim konfiguratsiyalarini saqlash
- **Database Connection Pool**: Bitta connection pool
- **Cache**: Tizim bo'ylab bitta cache instance
- **Hardware Interface**: Printer, scanner kabi qurilmalar bilan ishlash

### ❌ Mos kelmaydigan holatlar
- **Stateless utility klasslar**: Math operatsiyalari uchun
- **Data transfer objects**: Oddiy ma'lumot tashuvchi klasslar
- **Value objects**: Qiymat obyektlari
- **Ko'p instancega ehtiyoj**: Har bir foydalanuvchi uchun alohida session

---

## 🔄 Singleton vs Dependency Injection

| Jihat | Singleton | Dependency Injection |
|-------|-----------|---------------------|
| **Boshqarish** | Klass o'zini boshqaradi | DI container boshqaradi |
| **Testlash** | Qiyin | Oson |
| **Bog'liqlik** | Yashirin | Ochiq |
| **Konfiguratsiya** | Kod ichida | Tashqi konfiguratsiya |
| **Kengaytirilishi** | Cheklangan | Yuqori |

---

## 🎯 Xulosa

Singleton patterni:

* ✅ Bitta instance yaratish kerak bo'lganda foydali
* ✅ Global kirish nuqtasi kerak bo'lganda qulay
* ✅ Resurslarni tejash uchun muhim
* ⚠️ Ehtiyotkorlik bilan ishlatish kerak
* ⚠️ Modern dasturlashda DI container lar afzalroq

**Qo'llash tavsiyasi**: Zamonaviy dasturlashda Singleton pattern o'rniga Dependency Injection container laridan foydalanish tavsiya etiladi. Agar Singleton zarur bo'lsa, Enum-based implementatsiya eng xavfsiz va sodda usul hisoblanadi.