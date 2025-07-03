# 🧬 Prototype Design Pattern

## 📚 Pattern haqida ma'lumot

> *Prototype pattern - bu mavjud obyektning nusxasini (klonini) yaratish imkonini beruvchi yaratuvchi dizayn namunasi.*
> 
> — Gang of Four (GoF)

Prototype design pattern - bu **yaratish (creational)** pattern kategoriyasiga kiradi va obyektlarni noldan yaratish o'rniga mavjud obyektlardan nusxa ko'chirish orqali yangi obyektlar hosil qilishga imkon beradi. Bu pattern, ayniqsa, obyekt yaratish jarayoni resurs talab qiladigan yoki murakkab bo'lgan hollarda foydalidir.

[Prototype Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/prototype/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Resurs sarfini kamaytirish
- Obyektlarni noldan yaratish ko'p resurs (vaqt, xotira) talab qilishi mumkin.
- Masalan, ma'lumotlar bazasi bilan bog'lanish yoki murakkab konfiguratsiyalarni o'rnatish kerak bo'lsa.

### 2️⃣ Obyekt konfiguratsiyasini saqlash
- Bir xil turdagi obyektlarni turli holatlarda qayta-qayta yaratish zarur bo'lganda.
- Har safar bir xil sozlamalarni qo'lda kiritish o'rniga, prototipdan nusxa olish osonroq.

### 3️⃣ Dinamik obyekt turlari
- Obyekt turlari ish vaqtida aniqlanadigan holatlarda, masalan, turli sinflardan obyektlar yaratish kerak bo'lsa.

### 4️⃣ Obyektlarning mustaqil nusxalari
- Asl obyektga ta'sir qilmasdan uning mustaqil nusxasini yaratish kerak bo'lganda.

---

## 💡 Qanday hal qiladi

### ✅ Obyektlarni klonlash
- Prototype pattern orqali obyektlarning nusxasini yaratish mumkin, bu esa yangi obyektlarni noldan yaratishga nisbatan tezroq va samaraliroq.

### ✅ Moslashuvchanlik
- Turli xil obyekt turlarini bir xil interfeys orqali klonlash imkonini beradi.
- Ish vaqtida yangi obyekt turlarini qo'shish osonlashadi.

### ✅ Resurslarni tejash
- Murakkab obyektlarni qayta-qayta yaratish o'rniga, ularning nusxalarini ishlatish orqali resurs sarfi kamayadi.

### ✅ Mustaqil nusxalar
- Klonlangan obyektlar asl obyektdan mustaqil bo'ladi (deep copy ishlatilganda).

---

## 🧩 Implementatsiyasi

Prototype Pattern odatda quyidagi komponentlardan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Prototype** | Klonlash uchun interfeys yoki abstrakt klass (masalan, `clone()` metodi). |
| **Concrete Prototype** | Prototype interfeysini amalga oshiruvchi va klonlashni ta'minlovchi sinf. |
| **Client** | Klonlash jarayonini boshqaruvchi kod. |

### 💻 Java implementatsiyasi

```java
// 1. Prototype interfeysi
public interface Prototype {
    Prototype clone();
}

// 2. Concrete Prototype class
public class Car implements Prototype {
    private String model;
    private String color;
    private int year;
    private List<String> features;

    public Car(String model, String color, int year, List<String> features) {
        this.model = model;
        this.color = color;
        this.year = year;
        this.features = new ArrayList<>(features); // Deep copy uchun
    }

    // Deep copy uchun clone metodi
    @Override
    public Prototype clone() {
        return new Car(this.model, this.color, this.year, new ArrayList<>(this.features));
    }

    // Getters va Setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = new ArrayList<>(features); }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", features=" + features +
                '}';
    }
}

// 3. Client code
public class PrototypePatternExample {
    public static void main(String[] args) {
        // Asl obyektni yaratish
        List<String> features = Arrays.asList("GPS", "Bluetooth", "Sunroof");
        Car originalCar = new Car("Toyota Camry", "Blue", 2023, features);
        
        // Obyektni klonlash
        Car clonedCar = (Car) originalCar.clone();
        
        // Klonlangan obyektni o'zgartirish
        clonedCar.setColor("Red");
        clonedCar.getFeatures().add("Backup Camera");
        
        // Natijalarni ko'rsatish
        System.out.println("Original Car: " + originalCar);
        System.out.println("Cloned Car: " + clonedCar);
    }
}
```

---

## 📋 Prototype Pattern turlari

### 1. **Shallow Copy**
- Faqat obyektning sirtqi (top-level) atributlarini nusxalaydi.
- Ichki obyektlarga havolalar (references) bir xil qoladi.
- Misol: Java'da `Object.clone()` metodi default shallow copy qiladi.

```java
public class ShallowCar implements Cloneable {
    private String model;
    private List<String> features;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow copy
    }
}
```

### 2. **Deep Copy**
- Obyekt va uning barcha ichki obyektlarini to'liq nusxalaydi.
- Mustaqil nusxalar yaratish uchun ishlatiladi.

```java
public class DeepCar implements Cloneable {
    private String model;
    private List<String> features;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCar cloned = (DeepCar) super.clone();
        cloned.features = new ArrayList<>(this.features); // Deep copy
        return cloned;
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Resurs tejamkorligi** | Murakkab obyektlarni noldan yaratish o'rniga nusxalash orqali vaqt va xotira tejaladi. |
| **Moslashuvchanlik** | Ish vaqtida turli xil obyekt turlarini klonlash mumkin. |
| **Oddiylik** | Obyekt yaratish jarayonini soddalashtiradi, ayniqsa murakkab konfiguratsiyalarda. |
| **Mustaqil nusxalar** | Deep copy yordamida asl obyektdan mustaqil nusxalar yaratiladi. |
| **Dinamiklik** | Yangi prototiplarni qo'shish va ishlatish oson. |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Deep copy murakkabligi** | Ichki obyektlarning to'liq nusxasini yaratish murakkab va resurs talab qilishi mumkin. |
| **Klonlash xatolari** | Shallow copy ishlatilganda, asl obyektga bog'liqlik muammolari yuzaga kelishi mumkin. |
| **Kodni murakkablashtirish** | Klonlash logikasini to'g'ri amalga oshirish qiyin bo'lishi mumkin. |
| **Performans** | Deep copy ko'p resurs talab qiladigan operatsiya bo'lishi mumkin. |

---

## 🔄 Prototype vs Factory Method farqi

| Jihat | Prototype Pattern | Factory Method |
|-------|------------------|----------------|
| **Maqsad** | Obyektlarni nusxalash orqali yaratish | Obyekt yaratish mantig'ini yashirish |
| **Texnika** | Klonlash | Yangi obyekt yaratish |
| **Murakkablik** | Deep copy uchun ko'proq kod talab qilinadi | Oddiyroq bo'lishi mumkin |
| **Ishlatish** | Murakkab obyektlarni nusxalash uchun | Oddiy obyektlar uchun |
| **Moslashuvchanlik** | Ish vaqtida yangi turlarni qo'shish oson | Sinf ierarxiyasiga bog'liq |

---

## 📚 JDK-da Prototype Pattern misollari

### 1. `Object.clone()`
- Java'da `Cloneable` interfeysi va `clone()` metodi prototype patternning asosiy misolidir.
```java
public class Employee implements Cloneable {
    private String name;
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

### 2. `ArrayList` va boshqa kolleksiyalar
```java
List<String> originalList = new ArrayList<>(Arrays.asList("A", "B", "C"));
List<String> clonedList = new ArrayList<>(originalList); // Nusxa olish
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Obyekt yaratish resurs talab qilganda.
- Bir xil turdagi obyektlarni turli holatlarda qayta-qayta yaratish kerak bo'lganda.
- Ish vaqtida dinamik ravishda obyekt turlarini qo'shish zarur bo'lganda.
- Mustaqil obyekt nusxalarini yaratish kerak bo'lganda.

### ❌ Ishlatmaslik kerak:
- Oddiy obyektlar uchun (klonlashning hojati yo'q bo'lsa).
- Deep copy murakkab yoki imkonsiz bo'lgan hollarda.
- Performans juda muhim bo'lgan joylarda.

---

## 🎯 Xulosa

Prototype Pattern:

* ✅ Murakkab obyektlarni nusxalash orqali yaratishni soddalashtiradi.
* ✅ Resurslarni tejaydi va dinamik moslashuvchanlikni ta'minlaydi.
* ✅ Deep copy yordamida mustaqil obyektlar yaratish imkonini beradi.
* ⚠️ Deep copy amalga oshirish murakkab bo'lishi mumkin.

**Tavsiya**: Resurs talab qiladigan yoki bir xil turdagi obyektlarni ko'p marta yaratish kerak bo'lgan loyihalarda Prototype Pattern foydali. Ammo oddiy obyektlar uchun yoki klonlash murakkab bo'lsa, boshqa alternativalarni ko'rib chiqish kerak.