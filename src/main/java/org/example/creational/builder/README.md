# 🏗️ Builder Design Pattern

## 📚 Pattern haqida ma'lumot

> *Builder pattern - bu murakkab ob'ektlarni bosqichma-bosqich qurish imkonini beruvchi yaratuvchi dizayn namunasi.*
> 
> — Gang of Four (GoF)

Builder design pattern - bu obyektlarni yaratishning eng samarali usullaridan biri bo'lib, u **yaratish (creational)** pattern kategoriyasiga kiradi. Bu pattern bir xil qurilish kodidan foydalangan holda ob'ektning turli xil turlari va ko'rinishlarini yaratish imkonini beradi.

[Builder Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/builder/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Murakkab obyektlar yaratish
- Obyektlar ko'plab parametrlar yoki konfiguratsiyalarga ega bo'lsa, oddiy konstruktor yoki setter metodlari orqali obyekt yaratish qiyin bo'lishi mumkin
- Konstruktor parametrlari soni ko'payib ketganda, kodni tushunish va maintain qilish qiyinlashadi

### 2️⃣ Kod o'qilishi va tushunilishi
- Bir nechta parametrga ega konstruktorlar zanjiridan foydalangan holda obyekt yaratish kodni chalkashtirishi mumkin
- Parametrlar tartibini xato qilish xavfi mavjud bo'ladi

### 3️⃣ Muqobil konfiguratsiya
- Har xil konfiguratsiyalarni talab qiladigan obyektlarni yaratish murakkab bo'lishi mumkin
- Ixtiyoriy parametrlar bilan ishlash qiyin bo'ladi

### 4️⃣ Immutable obyektlar yaratish
- Yaratilgandan so'ng o'zgarmaydigan (immutable) obyektlarni yaratish kerak bo'lganda
- Thread-safe obyektlar yaratish talab qilinganda

---

## 💡 Qanday hal qiladi

### ✅ Obyektni qadam-baqadam yaratish
- Builder pattern orqali obyektni bosqichma-bosqich yaratish va faqat kerakli atributlarni belgilash mumkin
- Har bir qadamda faqat kerakli ma'lumotlarni kiritish imkoniyati

### ✅ Kodning o'qilishi yaxshilanadi
- Builder orqali obyekt yaratish o'quvchan va aniq bo'ladi
- Method chaining (zanjir usuli) orqali kod yozish osonlashadi

### ✅ Moslashuvchanlik
- Har xil konfiguratsiyalarda obyektlar yaratishni osonlashtiradi
- Ixtiyoriy parametrlar bilan ishlashni soddalashtiradi

### ✅ Xavfsizlik
- Yaratilish jarayonida obyektning yaroqsiz holatda bo'lishini oldini oladi
- Immutable obyektlar yaratish imkonini beradi

---

## 🧩 Implementatsiyasi

Builder Pattern to'rtta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Product** | Yaratilayotgan murakkab obyekt |
| **Builder** | Obyektni yaratish uchun interfeys yoki abstrakt klass |
| **Concrete Builder** | Builder interfeysini amalga oshiruvchi klass |
| **Director** | Builder obyektini boshqaruvchi klass (ixtiyoriy) |

### 💻 Java implementatsiyasi

```java
// 1. Product class
public class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private boolean hasWifi;
    private boolean hasBluetooth;
    
    // Private constructor
    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.hasWifi = builder.hasWifi;
        this.hasBluetooth = builder.hasBluetooth;
    }
    
    // Getters
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public boolean hasWifi() { return hasWifi; }
    public boolean hasBluetooth() { return hasBluetooth; }
    
    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                ", hasWifi=" + hasWifi +
                ", hasBluetooth=" + hasBluetooth +
                '}';
    }
    
    // 2. Static nested Builder class
    public static class ComputerBuilder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        private boolean hasWifi;
        private boolean hasBluetooth;
        
        // Required parameters
        public ComputerBuilder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }
        
        // Optional parameters
        public ComputerBuilder storage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public ComputerBuilder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }
        
        public ComputerBuilder hasWifi(boolean hasWifi) {
            this.hasWifi = hasWifi;
            return this;
        }
        
        public ComputerBuilder hasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}

// 3. Client code
public class BuilderPatternExample {
    public static void main(String[] args) {
        // Gaming computer
        Computer gamingComputer = new Computer.ComputerBuilder("Intel i9", "32GB")
                .storage("1TB SSD")
                .gpu("RTX 4090")
                .hasWifi(true)
                .hasBluetooth(true)
                .build();
        
        // Office computer
        Computer officeComputer = new Computer.ComputerBuilder("Intel i5", "16GB")
                .storage("512GB SSD")
                .hasWifi(true)
                .build();
        
        // Basic computer
        Computer basicComputer = new Computer.ComputerBuilder("Intel i3", "8GB")
                .build();
        
        System.out.println("Gaming Computer: " + gamingComputer);
        System.out.println("Office Computer: " + officeComputer);
        System.out.println("Basic Computer: " + basicComputer);
    }
}
```

---

## 📋 Builder Pattern turlari

### 1. **Telescoping Constructor Pattern muammosi**
```java
// Noto'g'ri yondashuv - ko'p konstruktorlar
public class Pizza {
    private String dough;
    private String sauce;
    private String cheese;
    private String pepperoni;
    private String mushrooms;
    
    public Pizza(String dough) { /* ... */ }
    public Pizza(String dough, String sauce) { /* ... */ }
    public Pizza(String dough, String sauce, String cheese) { /* ... */ }
    public Pizza(String dough, String sauce, String cheese, String pepperoni) { /* ... */ }
    // Va hokazo...
}
```

### 2. **Fluent Interface Builder**
```java
// Method chaining bilan
Pizza pizza = new Pizza.Builder()
    .dough("thin")
    .sauce("tomato")
    .cheese("mozzarella")
    .pepperoni(true)
    .mushrooms(true)
    .build();
```

### 3. **Step Builder Pattern**
```java
// Har bir qadam majburiy
public interface DoughStep {
    SauceStep dough(String dough);
}

public interface SauceStep {
    BuildStep sauce(String sauce);
}

public interface BuildStep {
    BuildStep cheese(String cheese);
    BuildStep pepperoni(boolean pepperoni);
    Pizza build();
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Kodning o'qilishi** | Method chaining orqali kod yozish va o'qish osonlashadi. Har bir parametrning maqsadi aniq bo'ladi. |
| **Moslashuvchanlik** | Ixtiyoriy parametrlar bilan oson ishlash. Har xil konfiguratsiyalarda obyektlar yaratish mumkin. |
| **Xavfsizlik** | Obyekt yaratish jarayonida noto'g'ri holatlarni oldini oladi. Validation qo'shish oson. |
| **Immutable obyektlar** | Yaratilgandan so'ng o'zgarmaydigan obyektlarni yaratish imkonini beradi. Thread-safe bo'ladi. |
| **Kodni qayta ishlatish** | Bir xil builder turli obyektlar yaratish uchun ishlatilishi mumkin. |
| **Parametrlar tartibiga bog'liq emas** | Konstruktor parametrlari tartibini xato qilish xavfi yo'q. |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Kod hajmi ortishi** | Har bir klass uchun alohida Builder klass yaratish kerak. Kichik klasslarda ortiqcha bo'lishi mumkin. |
| **Xotira sarfi** | Qo'shimcha Builder obyektlari xotira sarflaydi. Har safar yangi Builder yaratiladi. |
| **Murakkablik** | Oddiy obyektlar uchun ortiqcha murakkablik yaratishi mumkin. Yangi dasturchilar uchun tushunish qiyin. |
| **Performance** | Qo'shimcha method chaining va validationlar performance ga ta'sir qilishi mumkin. |
| **Builder holatini boshqarish** | Builder obyektini qayta ishlatish xavfli bo'lishi mumkin. Immutable builder yaratish kerak. |

---

## 🔄 Builder vs Factory Method farqi

| Jihat | Builder Pattern | Factory Method |
|-------|-----------------|----------------|
| **Maqsad** | Murakkab obyektni bosqichma-bosqich yaratish | Obyekt yaratish mantig'ini yashirish |
| **Konfiguratsiya** | Ko'p parametrlar va konfiguratsiyalar | Oddiy parametrlar |
| **Kod strukturasi** | Method chaining | Simple method call |
| **Ishlatish** | Murakkab obyektlar uchun | Oddiy obyektlar uchun |
| **Moslashuvchanlik** | Juda moslashuvchan | Nisbatan kam moslashuvchan |

---

## 📚 JDK-da Builder Pattern misollari

### 1. `StringBuilder` va `StringBuffer`
```java
String result = new StringBuilder()
    .append("Hello")
    .append(" ")
    .append("World")
    .toString();
```

### 2. `Stream.Builder`
```java
Stream<String> stream = Stream.<String>builder()
    .add("one")
    .add("two")
    .add("three")
    .build();
```

### 3. `Calendar.Builder`
```java
Calendar calendar = new Calendar.Builder()
    .setDate(2024, 1, 15)
    .setTimeOfDay(14, 30, 0)
    .build();
```

### 4. `ProcessBuilder`
```java
ProcessBuilder processBuilder = new ProcessBuilder()
    .command("java", "-version")
    .directory(new File("/tmp"))
    .redirectErrorStream(true);
```

### 5. `HttpRequest.Builder` (Java 11+)
```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://example.com"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString("{}"))
    .build();
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Obyektda 4+ parametr bo'lganda
- Ko'p ixtiyoriy parametrlar mavjud bo'lganda
- Immutable obyektlar yaratish kerak bo'lganda
- Obyekt yaratish jarayoni murakkab bo'lganda
- Validation qo'shish kerak bo'lganda

### ❌ Ishlatmaslik kerak:
- Oddiy obyektlar uchun (2-3 parametr)
- Performance kritik bo'lgan joylarda
- Kichik, oddiy loyihalarda
- Mutable obyektlar kerak bo'lganda

---

## 🎯 Xulosa

Builder Pattern:

* ✅ Murakkab obyektlarni yaratishni soddalashtiradi
* ✅ Kodni o'qish va tushunishni osonlashtiradi
* ✅ Immutable obyektlar yaratish imkonini beradi
* ✅ Xavfsiz va moslashuvchan obyekt yaratish usulini taqdim etadi

**Tavsiya**: Katta loyihalarda, ayniqsa ko'p parametrli va murakkab obyektlar bilan ishlashda Builder Pattern juda foydali. Lekin kichik va oddiy obyektlar uchun ortiqcha murakkablik yaratmasligi kerak.