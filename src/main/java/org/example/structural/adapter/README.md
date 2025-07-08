# 🔌 Adapter Design Pattern

## 📚 Pattern haqida ma'lumot

> *Adapter pattern - bu bir interfeys orqali boshqa interfeysga murojaat qilish imkonini beruvchi strukturaviy dizayn namunasi.*
> (2ta bir biriga mos kelmaydigan interfacelar o'rtasida ulagich vazfasini bajaradi)
> 
> — Gang of Four (GoF)

Adapter design pattern - bu **strukturaviy (structural)** pattern kategoriyasiga kiradi. Bu pattern mavjud klasslarni ularning kodini o'zgartirmasdan yangi interfeys orqali ishlatish imkonini beradi. Adapter real hayotdagi adapter kabi ishlaydi - masalan, elektr toki uchun ishlatadigan adapter.

[Adapter Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/adapter/structure-object-adapter.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Mos kelmaydigan interfeys muammosi
- Mavjud klass interfeysi mijoz kutgan interfeys bilan mos kelmaydi
- Uchinchi tomon kutubxonalari bizning kodimiz bilan to'g'ridan-to'g'ri ishlashga qodir emas
- Legacy kodlarni yangi tizimga integratsiya qilish qiyin

### 2️⃣ Kod qayta yozishdan qochish
- Mavjud kodlarni o'zgartirish xavfli va qimmat
- Stable ishlayotgan kodlarni buzish xavfi
- Uchinchi tomon kutubxonalarini o'zgartirib bo'lmaydi

### 3️⃣ Turli data formatlar bilan ishlash
- JSON va XML orasida konvertatsiya
- Turli database engine'lari bilan ishlash
- Turli xil API'lar bilan integration

### 4️⃣ Polimorfizm va abstraksiya
- Har xil implementatsiyalarni bir xil interfeys orqali ishlatish
- Mijoz kodi konkret implementatsiyadan xabardor bo'lmasligi kerak

---

## 💡 Qanday hal qiladi

### ✅ Interfeys moslashuvi
- Adapter klass orqali ikkita mos kelmaydigan interfeys o'rtasida ko'prik vazifasini bajaradi
- Mijoz o'z interfeysi orqali ishlaydi, adapter esa uni kerakli interfeys formatiga o'zgartiradi

### ✅ Mavjud kodlarni saqlash
- Hech qanday mavjud kodlarni o'zgartirmasdan yangi funksionallik qo'shish imkonini beradi
- Legacy tizimlarni yangi arxitektura bilan birlashtirish

### ✅ Loose coupling
- Mijoz kodi va adaptee (moslashtirilayotgan klass) o'rtasida bog'liqlikni kamaytiradi
- Dependency Inversion Principle'ni amalga oshiradi

### ✅ Qayta foydalanish imkoniyati
- Mavjud kodlarni turli kontekstlarda qayta ishlatish mumkin
- Bir nechta adapter orqali turli interfeyslarga moslashtirish

---

## 🧩 Implementatsiyasi

Adapter Pattern to'rtta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Target** | Mijoz kutgan interfeys |
| **Adaptee** | Moslashtirilishi kerak bo'lgan mavjud klass |
| **Adapter** | Target va Adaptee o'rtasidagi ko'prik |
| **Client** | Target interfeys orqali ishlovchi kod |

### 💻 Java implementatsiyasi

```java
// 1. Target interface - mijoz kutgan interfeys
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

// 2. Adaptee - mavjud klass (moslashtirilishi kerak)
public class Mp3Player {
    public void playMp3(String fileName) {
        System.out.println("Playing MP3 file: " + fileName);
    }
}

// 3. Boshqa Adaptee
public class Mp4Player {
    public void playMp4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}

// 4. Adapter - interfeys o'rtasidagi ko'prik
public class MediaAdapter implements MediaPlayer {
    private Mp3Player mp3Player;
    private Mp4Player mp4Player;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp3")) {
            mp3Player = new Mp3Player();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player = new Mp4Player();
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            mp3Player.playMp3(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mp4Player.playMp4(fileName);
        }
    }
}

// 5. Client - Target interfeys orqali ishlovchi
public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;
    
    @Override
    public void play(String audioType, String fileName) {
        // Default formatni o'zi qo'llab-quvvatlaydi
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } 
        // Boshqa formatlar uchun adapter ishlatadi
        else if (audioType.equalsIgnoreCase("mp4") || 
                 audioType.equalsIgnoreCase("vlc")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Format not supported: " + audioType);
        }
    }
}

// 6. Demo
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        
        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.play("vlc", "movie.vlc");
        audioPlayer.play("avi", "clip.avi");
    }
}
```

---

## 📋 Adapter Pattern turlari

### 1. **Object Adapter (Composition)**
```java
// Composition orqali adaptee'ni o'rab oladi
public class ObjectAdapter implements Target {
    private Adaptee adaptee;
    
    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
```

### 2. **Class Adapter (Inheritance)**
```java
// Inheritance orqali adaptee'dan meros oladi
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }
}
```

### 3. **Two-way Adapter**
```java
// Ikki tomonlama adapter
public class TwoWayAdapter implements Target, AdapteeInterface {
    private Adaptee adaptee;
    private Target target;
    
    @Override
    public void request() {
        adaptee.specificRequest();
    }
    
    @Override
    public void specificRequest() {
        target.request();
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **Database Adapter**
```java
// Turli database'lar uchun adapter
public interface DatabaseConnection {
    void connect();
    void executeQuery(String query);
    void disconnect();
}

public class MySQLAdapter implements DatabaseConnection {
    private MySQLDatabase mysql;
    
    public MySQLAdapter() {
        this.mysql = new MySQLDatabase();
    }
    
    @Override
    public void connect() {
        mysql.mysqlConnect();
    }
    
    @Override
    public void executeQuery(String query) {
        mysql.mysqlExecute(query);
    }
    
    @Override
    public void disconnect() {
        mysql.mysqlDisconnect();
    }
}
```

### 2. **Payment Gateway Adapter**
```java
// Turli to'lov tizimlari uchun adapter
public interface PaymentProcessor {
    void processPayment(double amount);
}

public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    
    public PayPalAdapter() {
        this.payPalGateway = new PayPalGateway();
    }
    
    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}

public class StripeAdapter implements PaymentProcessor {
    private StripeAPI stripeAPI;
    
    public StripeAdapter() {
        this.stripeAPI = new StripeAPI();
    }
    
    @Override
    public void processPayment(double amount) {
        stripeAPI.charge(amount);
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Kod qayta ishlatish** | Mavjud kodlarni o'zgartirmasdan yangi kontekstlarda ishlatish mumkin |
| **Ajratilgan javobgarlik** | Interfeys konvertatsiyasi alohida klassda amalga oshiriladi |
| **Ochiq/Yopiq tamoyil** | Yangi adapterlar qo'shish mumkin, mavjud kodlarni o'zgartirmasdan |
| **Loose coupling** | Mijoz kodi va adaptee o'rtasidagi bog'liqlik kamaytiradi |
| **Legacy integratsiya** | Eski kodlarni yangi tizimga oson integratsiya qilish |
| **Uchinchi tomon kutubxonalari** | Tashqi kutubxonalarni o'z interfeysingizga moslashtirish |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Kod murakkabligi** | Qo'shimcha abstraktsiya qatlami kod murakkabligini oshiradi |
| **Performance overhead** | Qo'shimcha method call'lar performance'ga ta'sir qilishi mumkin |
| **Xotira sarfi** | Adapter obyektlari qo'shimcha xotira sarflaydi |
| **Debugging qiyinligi** | Bir nechta qatlam debug qilishni qiyinlashtirishi mumkin |
| **Ortiqcha abstraktsiya** | Oddiy holatlar uchun ortiqcha bo'lishi mumkin |

---

## 🔄 Adapter vs boshqa pattern'lar

| Jihat | Adapter | Facade | Decorator | Proxy |
|-------|---------|---------|-----------|-------|
| **Maqsad** | Interfeys moslashuvi | Soddalashtirishg | Xususiyat qo'shish | Dostup nazorati |
| **Strukturasi** | Bitta adaptee | Ko'p subsystem | Recursive wrapping | Bitta subject |
| **Interfeys** | Interfeys o'zgaradi | Yangi interfeys | Bir xil interfeys | Bir xil interfeys |
| **Funksionallik** | O'zgartirmaydi | Soddalashtiradi | Kengaytiradi | Nazorat qiladi |

---

## 📚 JDK-da Adapter Pattern misollari

### 1. **InputStreamReader**
```java
// InputStream'ni Reader'ga moslashtiradi
FileInputStream fileInputStream = new FileInputStream("file.txt");
InputStreamReader reader = new InputStreamReader(fileInputStream);
```

### 2. **Arrays.asList()**
```java
// Array'ni List'ga moslashtiradi
String[] array = {"one", "two", "three"};
List<String> list = Arrays.asList(array);
```

### 3. **Collections wrappers**
```java
// List'ni synchronized List'ga moslashtiradi
List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
```

### 4. **MouseAdapter**
```java
// MouseListener interfeysini oddiy klassga moslashtiradi
public class MyMouseAdapter extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        // Faqat kerakli metodlarni implement qilish
    }
}
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Mavjud klassni yangi interfeys orqali ishlatish kerak
- Uchinchi tomon kutubxonalarini integratsiya qilish
- Legacy tizimlarni yangi arxitektura bilan birlashtirish
- Turli data formatlar o'rtasida konvertatsiya
- Polimorfizm va abstraksiya kerak bo'lganda

### ❌ Ishlatmaslik kerak:
- Interfeys allaqachon mos kelsa
- Oddiy funksionallik uchun
- Performance kritik joylarda
- Katta miqdorda adapter kerak bo'lganda

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Adapter + Factory Method**
```java
public abstract class AdapterFactory {
    public abstract MediaPlayer createAdapter(String type);
}

public class MediaAdapterFactory extends AdapterFactory {
    @Override
    public MediaPlayer createAdapter(String type) {
        if (type.equals("mp3")) {
            return new Mp3Adapter();
        } else if (type.equals("mp4")) {
            return new Mp4Adapter();
        }
        return null;
    }
}
```

### 2. **Adapter + Strategy**
```java
public class PaymentContext {
    private PaymentProcessor paymentProcessor;
    
    public void setPaymentProcessor(PaymentProcessor processor) {
        this.paymentProcessor = processor;
    }
    
    public void processPayment(double amount) {
        paymentProcessor.processPayment(amount);
    }
}
```

---

## 🎯 Xulosa

Adapter Pattern:

* ✅ Mavjud kodlarni o'zgartirmasdan yangi interfeys orqali ishlatish imkonini beradi
* ✅ Legacy tizimlar va uchinchi tomon kutubxonalari bilan integratsiyani osonlashtiradi
* ✅ Kod qayta ishlatish va loose coupling'ni ta'minlaydi
* ✅ Polimorfizm va abstraksiyani qo'llab-quvvatlaydi

**Tavsiya**: Adapter pattern integratsiya muammolarini hal qilish uchun juda foydali, lekin ortiqcha abstraktsiya yaratmaslik kerak. Faqat haqiqiy zarurat bo'lganda ishlatish tavsiya etiladi.