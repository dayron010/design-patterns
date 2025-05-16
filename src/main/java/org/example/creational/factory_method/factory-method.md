# 🏭 Factory Method Design Pattern

## 📚 Pattern haqida ma'lumot

> *Factory Method obyekt yaratish uchun interfeys yaratadi, lekin qaysi sinfni instansiyalashni subklasslarga qaror qilish imkonini beradi.*
> 
> — Gang of Four (GoF)

Factory Method design pattern - bu obyektlarni yaratishning eng ko'p qo'llaniladigan usullaridan biri bo'lib, u **yaratish (creational)** pattern kategoriyasiga kiradi. Bu pattern obyekt yaratishni bevosita konstruktorni chaqirish o'rniga, maxsus method orqali amalga oshirishni taklif qiladi.

[Factory Method strukturasi](https://refactoring.guru/images/patterns/diagrams/factory-method/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Yaratilishi kerak bo'lgan aniq klassni oldindan bilmaslik
- Dastur ishlash jarayonida qanday obyekt yaratilishi kerakligi faqat runtime vaqtida aniqlanishi kerak bo'lganda
- Client kod yaratilayotgan aniq klass bilan bog'liq bo'lishini oldini olish kerak bo'lganda

### 2️⃣ O'zgarishlar va kengayishlar
- Yangi obyekt turlarini tizimga qo'shishda mavjud kodga minimal ta'sir qilish kerak bo'lganida
- Tizimning yopiq/ochiq printsipi (Open/Closed Principle) ga muvofiq bo'lishini ta'minlash kerak bo'lganda

### 3️⃣ Obyekt yaratish mantig'ini markazlashtirish
- Obyekt yaratish bilan bog'liq kodni bir joyda saqlash kerak bo'lganda
- Kodning takrorlanishini kamaytirish kerak bo'lganda

---

## 💡 Qanday hal qiladi

### ✅ Abstraksiya orqali ajratish
- Obyekt yaratish interfeysi (factory method) bilan aniq implementatsiyani ajratadi
- Client kod faqat abstrakt interfeys bilan ishlaydi, konkret klasslar bilan emas

### ✅ Kengaytirishga imkoniyat
- Yangi obyekt turlarini qo'shish uchun yangi konkret factory klassni yaratish kifoya
- Mavjud kod o'zgarishsiz qoladi

### ✅ Yaratish mantig'ini enkapsulatsiya qilish
- Obyekt qanday yaratilishi factory klassida yashiringan
- Bu client kodni soddalashtiradi va takrorlanishni kamaytiradi

---

## 🧩 Implementatsiyasi

Factory Method patterni to'rtta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Product** | Yaratilgan obyektlar uchun umumiy interfeys |
| **Concrete Product** | Product interfeysini amalga oshiruvchi aniq klasslar |
| **Creator** | Factory methodini e'lon qiladigan abstrakt klass |
| **Concrete Creator** | Factory methodini implementatsiya qiladigan va aniq obyektlarni yaratadigan klasslar |

### 💻 Java implementatsiyasi

```java
// 1. Product interface
interface Transport {
    void move();
}

// 2. Concrete Products
class Car implements Transport {
    @Override
    public void move() {
        System.out.println("Car is moving on the road...");
    }
}

class Airplane implements Transport {
    @Override
    public void move() {
        System.out.println("Airplane is flying in the sky...");
    }
}

class Ship implements Transport {
    @Override
    public void move() {
        System.out.println("Ship is sailing on water...");
    }
}

// 3. Creator - abstract class
abstract class TransportFactory {
    // Factory method
    public abstract Transport createTransport();
    
    // Creator class can include operations with the product
    public void startTransport() {
        // Call the factory method
        Transport transport = createTransport();
        
        // Work with the created product
        transport.move();
    }
}

// 4. Concrete Creators
class CarFactory extends TransportFactory {
    @Override
    public Transport createTransport() {
        return new Car();
    }
}

class AirplaneFactory extends TransportFactory {
    @Override
    public Transport createTransport() {
        return new Airplane();
    }
}

class ShipFactory extends TransportFactory {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}

// 5. Client code
public class FactoryMethodExample {
    public static void main(String[] args) {
        // Creating different types of transport
        TransportFactory carFactory = new CarFactory();
        TransportFactory airplaneFactory = new AirplaneFactory();
        TransportFactory shipFactory = new ShipFactory();
        
        // Starting the transport
        System.out.println("Creating and starting a car:");
        carFactory.startTransport();
        
        System.out.println("\nCreating and starting an airplane:");
        airplaneFactory.startTransport();
        
        System.out.println("\nCreating and starting a ship:");
        shipFactory.startTransport();
    }
}
```

---

## ✅ Qulaylik tomonlari

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Yopiq/Ochiq printsipga muvofiqlik** | Yangi obyekt turlarini qo'shish mavjud kodga ta'sir qilmaydi. Kodni o'zgartirish emas, kengaytirish imkonini beradi. |
| **Bog'liqliklarni kamaytirish** | Client kod konkret klasslar bilan emas, interfeys bilan ishlaydi. Bu tizim komponentlari orasidagi bog'liqlikni kamaytiradi. |
| **Kod qayta ishlatilishi** | Obyekt yaratish mantig'i markazlashtirilgan va qayta ishlatiladigan. Bir xil yaratish mantig'i turli qismlarda takrorlanmaydi. |
| **Kodni testlash qulayligi** | Mock obyektlardan foydalanish orqali factory methodlarini test qilish oson. Unit testlarni yozishni soddalashtiradi. |
| **Kodning tushunarliligi** | Yaratish mantig'i va biznes mantig'i ajratilgan. Har bir qism o'z vazifasiga e'tibor qaratadi. |

---

## ⚠️ Kamchilik tomonlari

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Kod murakkabligi ortishi** | Ko'p klasslar yaratilishi kerak bo'lishi mumkin. Kichik loyhalarda ortiqcha bo'lishi mumkin. |
| **Abstraksiya qatlamlarining ko'payishi** | Qo'shimcha interfeys va klasslar tizimni murakkablashtirishi mumkin. Yangi dasturchilar uchun tushunish qiyinroq bo'lishi mumkin. |
| **Factory hierarchiyasini boshqarish** | Ko'p product turlarida factory klasslari soni ham ortadi. Factory hierarchiyasini boshqarish murakkablashishi mumkin. |
| **Parametrlarga bog'liqlik** | Factory method parametrlarga asoslangan bo'lsa, kod o'qilishi murakkablashishi mumkin. Method signaturasini o'zgartirish qiyin bo'lishi mumkin. |

---

## 📚 JDK-da Factory Method patterni misollari

Java Development Kit (JDK) da Factory Method patterni keng qo'llaniladi:

### 1. `java.util.Calendar`
```java
Calendar calendar = Calendar.getInstance();
```
> `getInstance()` factory method hisoblanadi va tegishli Calendar obyektini qaytaradi.

### 2. `java.text.NumberFormat`
```java
NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
NumberFormat percentFormatter = NumberFormat.getPercentInstance();
```
> `getCurrencyInstance()` va `getPercentInstance()` methodlari turli NumberFormat obyektlarini qaytaradi.

### 3. `java.util.ResourceBundle`
```java
ResourceBundle bundle = ResourceBundle.getBundle("messages");
```
> `getBundle()` methodi ResourceBundle obyektini yaratadi.

### 4. `java.nio.charset.Charset`
```java
Charset charset = Charset.forName("UTF-8");
```
> `forName()` methodi belgilangan encoding uchun Charset obyektini qaytaradi.

### 5. `java.net.URLStreamHandlerFactory`
```java
URL url = new URL("http://www.example.com");
```
> URL konstruktori ichida protokolga mos URLStreamHandler obyektini olish uchun factory methoddan foydalaniladi.

### 6. `javax.xml.parsers.DocumentBuilderFactory`
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
```
> `newInstance()` va `newDocumentBuilder()` factory methodlar hisoblanadi.

---

## 🎯 Xulosa

Factory Method patterni:

* ✅ Obyekt yaratish jarayonini abstrakt qiladi 
* ✅ Client kodni konkret klasslardan ajratadi
* ✅ Tizimni kengaytirishga tayyorlaydi 
* ✅ Object-oriented design printsiplariga muvofiq kod yozishga yordam beradi

**Qo'llash tavsiya etiladi**: Kattaroq tizimlarda, ayniqsa o'zgarishlar tez-tez bo'ladigan va yangi obyekt turlari qo'shiladigan holatda, Factory Method patterni juda foydali bo'ladi.