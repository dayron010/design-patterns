# 🧬️ Prototype Pattern

## 📌 Prototype Nima?

**Prototype dizayn patterni** obyektlarni nusxalash jarayonini soddalashtirish uchun ishlatiladi.  
Bu pattern obyektlarni yaratishda **konstruktorlardan foydalanish o‘rniga**, mavjud obyektlardan **nusxa olish**
tamoyiliga asoslanadi.

Bu yondashuv quyidagi afzalliklarni ta’minlaydi:

- 🔹 **Obyekt yaratish jarayonini tezlashtiradi**
- 🔹 **Resurs sarfini kamaytiradi**
- 🔹 **Qimmat yoki murakkab obyektlarni yaratishda samaradorlikni oshiradi**

---

## 🏛️ Prototype Pattern Tuzilishi

| **Komponent**               | **Vazifasi**                                                             |
|-----------------------------|--------------------------------------------------------------------------|
| 🏷️ **Prototype Interface** | `clone()` metodini belgilaydi va nusxa olish mexanizmini ta’minlaydi.    |
| 🧬️ **Concrete Prototype**  | Berilgan sinfning nusxalarini yaratadi.                                  |
| 👤 **Client (Mijoz)**       | Yangi obyekt yaratish o‘rniga, mavjud obyektning nusxasidan foydalanadi. |

---

Bu pattern obyekt yaratish jarayonini **yengillashtiradi**, **kodni qayta ishlatish imkonini beradi** va **ishlash
tezligini oshiradi**. 🚀

## Prototype Pattern Implementatsiyasi:

```java
package org.example.creational.prototype.cars;

import java.util.HashMap;
import java.util.Map;

// 1. Prototype interfeysi
interface Prototype {
    Prototype clone();
}

// 2. Konkret prototip sinfi
class Car implements Prototype {
    private String model;
    private String color;

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    // Clone metodi orqali obyektni nusxalash
    @Override
    public Prototype clone() {
        return new Car(this.model, this.color);
    }

    public void showDetails() {
        System.out.println("Model: " + model + ", Color: " + color);
    }
}

// 3. Prototype Registry (obyektlarni saqlash va qayta foydalanish)
class CarRegistry {
    private Map<String, Prototype> carMap = new HashMap<>();

    public void addCar(String key, Prototype car) {
        carMap.put(key, car);
    }

    public Prototype getCar(String key) {
        return carMap.get(key).clone();
    }
}

// 4. Test qilish
public class PrototypePatternDemo {
    public static void main(String[] args) {
        CarRegistry registry = new CarRegistry();

        // Asl obyektlarni qo'shamiz
        registry.addCar("sport", new Car("Ferrari", "Red"));
        registry.addCar("suv", new Car("Range Rover", "Black"));

        // Yangi obyektlarni nusxalash
        Car car1 = (Car) registry.getCar("sport");
        Car car2 = (Car) registry.getCar("suv");

        // Detallarni ko‘rsatish
        car1.showDetails();
        car2.showDetails();
    }
}
```

## 📌 Muammo (Problem)

Ko‘pgina dasturlash tizimlarida yangi obyektlarni yaratish murakkab va qimmatbaho operatsiya bo‘lishi mumkin.
Ba'zi hollarda obyekt yaratish:Juda ko‘p resurs talab qiladi (masalan, katta obyektlarni yaratish yoki yuklash).
Sekin ishlaydi, chunki yangi obyektlarni yaratish uchun vaqt talab etiladi.
Kompleks obyektlar bo‘lsa, ularni har safar new orqali yaratish kodni murakkablashtiradi.

## 🛠️ Yechim (How Prototype Solves the Problem?)

Prototype patterni bu muammoni obyektni nusxalash orqali hal qiladi.
Yangi obyekt yaratish o‘rniga, mavjud obyektning nusxasini olish (clone()) orqali tezroq va kamroq resurs bilan yangi
obyekt hosil qilish mumkin.

Yechim qanday ishlaydi?

Original obyekt yaratiladi va dasturga yuklanadi.
Obyektni clone() qilish orqali nusxa olinadi, bu new operatoriga qaraganda ancha tez va samarali.
Nusxa olingan obyekt yangi obyekt kabi ishlaydi, lekin eski obyektning barcha xususiyatlarini saqlaydi.

## ✨ Prototype Patternning Afzalliklari (Qulayliklari)

| ✅ Afzalik                                       | 📌 Tavsif                                                                          |
|-------------------------------------------------|------------------------------------------------------------------------------------|
| **1. Yangi obyekt yaratishni tezlashtiradi**    | `new` o‘rniga `clone()` ishlatilgani uchun tezroq ishlaydi.                        |
| **2. Resurslarni tejaydi**                      | Har safar yangi obyekt yaratish o‘rniga, mavjud obyektning nusxasi olinadi.        |
| **3. Murakkab obyektlarni oson nusxalash**      | Konstruktor chaqirmasdan, to‘liq konfiguratsiya qilingan obyektni yaratish mumkin. |
| **4. Obyekt yaratish jarayonini yashiradi**     | Obyekt yaratish jarayoni foydalanuvchidan yashiriladi va kapsullangan bo‘ladi.     |
| **5. Katta obyektlarni boshqarish osonlashadi** | Murakkab tuzilmalarga ega obyektlar tez va samarali nusxalanadi.                   |

## ⚠️ Prototype Patternning Kamchiliklari

| ❌ Kamchilik                                                    | ⚠️ Tavsif                                                                                                       |
|----------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| **1. Deep Copy va Shallow Copy muammosi**                      | Agar obyekt ichida boshqa obyektlar bo‘lsa, ularni qanday nusxalash kerakligini aniqlash qiyin bo‘lishi mumkin. |
| **2. Clone metodi har bir sinfda alohida yozilishi kerak**     | Har bir yangi sinf uchun `clone()` metodini implementatsiya qilish qo‘shimcha ish talab qiladi.                 |
| **3. Murakkab obyektlar uchun ortiqcha yuk**                   | Ba’zan, `clone()` metodi ko‘plab ma’lumotlarni qayta nusxalashi kerak bo‘lib, bu ko‘proq xotira sarf qiladi.    |
| **4. Obyektlar murakkab bo‘lsa, kodni tushunish qiyinlashadi** | Deep Copy va rekursiv nusxalashni to‘g‘ri amalga oshirish kodni qiyinlashtirishi mumkin.                        |

## 📌 Xulosa: Prototype Patternni qachon ishlatish kerak?

✅ Agar yangi obyekt yaratish qimmat yoki sekin bo‘lsa.
✅ Agar obyektni yaratish takroriy bo‘lsa va ko‘p resurs talab qilsa.
✅ Agar obyektning xususiyatlari murakkab bo‘lsa va har safar yangisini yaratish o‘rniga, mavjud nusxani olish osonroq
bo‘lsa.
❌ Agar obyektlar oddiy va har doim yangi yaratilishi kerak bo‘lsa, Prototype Pattern kerak emas.