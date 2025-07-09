# 🌉 Bridge Design Pattern

## 📚 Pattern haqida ma'lumot

> *Bridge pattern - bu abstraktsiya va uning implementatsiyasini bir-biridan ajratib, ularning mustaqil ravishda o'zgarishiga imkon beruvchi strukturaviy dizayn namunasi.*
> (Abstraction va Implementation o'rtasidagi bog'liqlikni kamaytiradi)
> 
> — Gang of Four (GoF)

Bridge design pattern - bu **strukturaviy (structural)** pattern kategoriyasiga kiradi. Bu pattern abstraktsiya va uning implementatsiyasini bir-biridan ajratib, ularni mustaqil ravishda rivojlantirish imkonini beradi. Bridge real hayotdagi ko'prik kabi ishlaydi - ikki tomonni birlashtiradi, lekin har bir tomon mustaqil ravishda o'zgarishi mumkin.

[Bridge Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/bridge/structure-en.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Abstraktsiya va implementatsiya o'rtasidagi qattiq bog'liqlik
- Abstraktsiya va implementatsiya bir-biriga qattiq bog'langan
- Implementatsiyani o'zgartirish abstraktsiyani ham ta'sirlaydi
- Yangi implementatsiya qo'shish qiyin va xatarga to'la

### 2️⃣ Kombinatorial portlash muammosi
- Har bir abstraktsiya uchun har bir implementatsiyani alohida klass yaratish kerak
- N ta abstraktsiya va M ta implementatsiya uchun N*M ta klass kerak bo'ladi
- Kod takrorlanishi va saqlash qiyin bo'ladi

### 3️⃣ Platform-specific implementatsiya
- Bir xil funksionallik turli platformalar uchun turli xil amalga oshiriladi
- Cross-platform development qiyin bo'ladi
- Kod duplikatsiyasi va sync muammolari

### 4️⃣ Runtime'da implementatsiya o'zgartirish
- Compile time'da implementatsiya tanlanadi
- Runtime'da implementatsiyani o'zgartirish mumkin emas
- Flexible tizim yaratish qiyin

---

## 💡 Qanday hal qiladi

### ✅ Abstraktsiya va implementatsiyani ajratish
- Abstraktsiya va implementatsiya alohida hierarchy'larda joylashadi
- Har birini mustaqil ravishda o'zgartirish mumkin
- Composition orqali bog'lanish loose coupling ta'minlaydi

### ✅ Kombinatorial portlashning oldini olish
- N ta abstraktsiya + M ta implementatsiya = N+M ta klass
- Yangi abstraktsiya yoki implementatsiya qo'shish oson
- Kod takrorlanishi minimal bo'ladi

### ✅ Runtime flexibility
- Runtime'da implementatsiyani o'zgartirish mumkin
- Dynamic konfiguratsiya imkoniyati
- Flexible va adaptiv tizimlar yaratish

### ✅ Platform mustaqillik
- Abstraktsiya layer platform-specific detaillardan himoyalangan
- Cross-platform development osonlashadi
- Portability va maintainability yaxshilanadi

---

## 🧩 Implementatsiyasi

Bridge Pattern beshta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Abstraction** | Yuqori darajadagi nazorat logikasi |
| **Refined Abstraction** | Abstraction'ning kengaytirilgan versiyasi |
| **Implementation** | Implementatsiya interfeysini belgilaydi |
| **Concrete Implementation** | Implementation'ning konkret amalga oshirilishi |
| **Client** | Abstraction orqali ishlovchi kod |

### 💻 Java implementatsiyasi

```java
// 1. Implementation interface - implementatsiya kontrakti
public interface DrawingAPI {
    void drawCircle(double x, double y, double radius);
    void drawLine(double x1, double y1, double x2, double y2);
}

// 2. Concrete Implementation - konkret implementatsiya
public class DrawingAPI1 implements DrawingAPI {
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("API1: Drawing circle at (%.2f, %.2f) with radius %.2f%n", 
                         x, y, radius);
    }
    
    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        System.out.printf("API1: Drawing line from (%.2f, %.2f) to (%.2f, %.2f)%n", 
                         x1, y1, x2, y2);
    }
}

public class DrawingAPI2 implements DrawingAPI {
    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("API2: Rendering circle at (%.2f, %.2f) with radius %.2f%n", 
                         x, y, radius);
    }
    
    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        System.out.printf("API2: Rendering line from (%.2f, %.2f) to (%.2f, %.2f)%n", 
                         x1, y1, x2, y2);
    }
}

// 3. Abstraction - yuqori darajadagi abstraktsiya
public abstract class Shape {
    protected DrawingAPI drawingAPI;
    
    protected Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }
    
    public abstract void draw();
    public abstract void resize(double factor);
}

// 4. Refined Abstraction - kengaytirilgan abstraktsiya
public class Circle extends Shape {
    private double x, y, radius;
    
    public Circle(double x, double y, double radius, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }
    
    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.printf("Circle resized to radius %.2f%n", radius);
    }
}

public class Rectangle extends Shape {
    private double x1, y1, x2, y2;
    
    public Rectangle(double x1, double y1, double x2, double y2, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    @Override
    public void draw() {
        drawingAPI.drawLine(x1, y1, x2, y1); // top
        drawingAPI.drawLine(x2, y1, x2, y2); // right
        drawingAPI.drawLine(x2, y2, x1, y2); // bottom
        drawingAPI.drawLine(x1, y2, x1, y1); // left
    }
    
    @Override
    public void resize(double factor) {
        x2 *= factor;
        y2 *= factor;
        System.out.printf("Rectangle resized to (%.2f, %.2f, %.2f, %.2f)%n", 
                         x1, y1, x2, y2);
    }
}

// 5. Client - mijoz kodi
public class BridgePatternDemo {
    public static void main(String[] args) {
        // Turli implementatsiyalar bilan turli shakllar yaratish
        Shape circle1 = new Circle(10, 10, 5, new DrawingAPI1());
        Shape circle2 = new Circle(20, 20, 10, new DrawingAPI2());
        Shape rectangle1 = new Rectangle(0, 0, 100, 50, new DrawingAPI1());
        Shape rectangle2 = new Rectangle(10, 10, 200, 100, new DrawingAPI2());
        
        // Shakllarni chizish
        circle1.draw();
        circle2.draw();
        rectangle1.draw();
        rectangle2.draw();
        
        // Shakllarni o'lchami o'zgartirish
        circle1.resize(2.0);
        rectangle1.resize(1.5);
        
        // Qayta chizish
        circle1.draw();
        rectangle1.draw();
    }
}
```

---

## 📋 Bridge Pattern turlari

### 1. **Driver Pattern**
```java
// Database driver bridge
public abstract class DatabaseConnection {
    protected DatabaseDriver driver;
    
    protected DatabaseConnection(DatabaseDriver driver) {
        this.driver = driver;
    }
    
    public abstract void connect();
    public abstract void executeQuery(String query);
    public abstract void disconnect();
}

public class MySQLConnection extends DatabaseConnection {
    public MySQLConnection(DatabaseDriver driver) {
        super(driver);
    }
    
    @Override
    public void connect() {
        driver.connect("jdbc:mysql://localhost:3306/db");
    }
    
    @Override
    public void executeQuery(String query) {
        driver.execute("SELECT * FROM " + query);
    }
    
    @Override
    public void disconnect() {
        driver.close();
    }
}
```

### 2. **Device Control Bridge**
```java
// Smart device control bridge
public abstract class Device {
    protected RemoteControl remote;
    
    protected Device(RemoteControl remote) {
        this.remote = remote;
    }
    
    public abstract void operate();
}

public class TV extends Device {
    public TV(RemoteControl remote) {
        super(remote);
    }
    
    @Override
    public void operate() {
        remote.power();
        remote.volumeUp();
        remote.channelUp();
    }
}

public class Radio extends Device {
    public Radio(RemoteControl remote) {
        super(remote);
    }
    
    @Override
    public void operate() {
        remote.power();
        remote.volumeUp();
        // Radio has no channels
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **Message Sending Bridge**
```java
// Turli message sending service'lari uchun bridge
public abstract class MessageSender {
    protected MessageService messageService;
    
    protected MessageSender(MessageService messageService) {
        this.messageService = messageService;
    }
    
    public abstract void sendMessage(String message, String recipient);
}

public class EmailSender extends MessageSender {
    public EmailSender(MessageService messageService) {
        super(messageService);
    }
    
    @Override
    public void sendMessage(String message, String recipient) {
        String emailMessage = "Subject: Notification\n\n" + message;
        messageService.send(emailMessage, recipient);
    }
}

public class SMSSender extends MessageSender {
    public SMSSender(MessageService messageService) {
        super(messageService);
    }
    
    @Override
    public void sendMessage(String message, String recipient) {
        String smsMessage = message.length() > 160 ? 
                           message.substring(0, 160) : message;
        messageService.send(smsMessage, recipient);
    }
}

// Implementation hierarchy
public interface MessageService {
    void send(String message, String recipient);
}

public class TwilioService implements MessageService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending via Twilio: " + message + " to " + recipient);
    }
}

public class FirebaseService implements MessageService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Sending via Firebase: " + message + " to " + recipient);
    }
}
```

### 2. **Payment Processing Bridge**
```java
// To'lov tizimi bridge pattern
public abstract class Payment {
    protected PaymentGateway gateway;
    
    protected Payment(PaymentGateway gateway) {
        this.gateway = gateway;
    }
    
    public abstract void processPayment(double amount);
}

public class CreditCardPayment extends Payment {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber, PaymentGateway gateway) {
        super(gateway);
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
        gateway.charge(amount, "Credit Card ending in " + 
                      cardNumber.substring(cardNumber.length() - 4));
    }
}

public class PayPalPayment extends Payment {
    private String email;
    
    public PayPalPayment(String email, PaymentGateway gateway) {
        super(gateway);
        this.email = email;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        gateway.charge(amount, "PayPal account: " + email);
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Ajratilgan rivojlanish** | Abstraktsiya va implementatsiya mustaqil rivojlanadi |
| **Runtime switching** | Runtime'da implementatsiyani o'zgartirish mumkin |
| **Platform mustaqillik** | Cross-platform development osonlashadi |
| **Kengaytirilishlik** | Yangi abstraktsiya va implementatsiya qo'shish oson |
| **Kod qayta ishlatish** | Implementatsiyalar turli abstraktsiyalarda qayta ishlatiladi |
| **Kombinatorial portlashning oldini olish** | N*M o'rniga N+M klasslar kerak |
| **Encapsulation** | Implementation detaillar abstraktsiyadan yashiriladi |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Kod murakkabligi** | Qo'shimcha abstraktsiya qatlami murakkablik oshiradi |
| **Ortiqcha dizayn** | Oddiy holatlar uchun ortiqcha bo'lishi mumkin |
| **Debugging qiyinligi** | Bir nechta qatlam debug qilishni qiyinlashtiradi |
| **Performance overhead** | Qo'shimcha indirection performance'ga ta'sir qilishi mumkin |
| **Xotira sarfi** | Qo'shimcha obyektlar xotira sarflaydi |

---

## 🔄 Bridge vs boshqa pattern'lar

| Jihat | Bridge | Adapter | Strategy | State |
|-------|--------|---------|----------|-------|
| **Maqsad** | Abstraktsiya ajratish | Interfeys moslash | Algoritm almashtirish | Holat boshqarish |
| **Strukturasi** | Ikki hierarchy | Bitta adaptee | Bitta strategy | Bitta state |
| **Vaqt** | Design time | Runtime | Runtime | Runtime |
| **Scope** | Keng abstraksiya | Lokal moslik | Algoritm tanlash | Holat boshqarish |
| **Coupling** | Loose | Tight | Loose | Tight |

---

## 📚 JDK-da Bridge Pattern misollari

### 1. **JDBC Driver**
```java
// DriverManager - Bridge, turli driver'lar - Implementation
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/db", "user", "password");
```

### 2. **AWT/Swing Peer Architecture**
```java
// Component - Abstraction, ComponentPeer - Implementation
Button button = new Button("Click me");
// Har platformda turli peer implementation ishlatiladi
```

### 3. **Collections Framework**
```java
// List - Abstraction, ArrayList/LinkedList - Implementation
List<String> list = new ArrayList<>(); // yoki LinkedList
// Bir xil interfeys, turli implementation
```

### 4. **Logging Framework**
```java
// Logger - Abstraction, Handler - Implementation
Logger logger = Logger.getLogger("MyLogger");
logger.addHandler(new FileHandler("app.log"));
logger.addHandler(new ConsoleHandler());
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Abstraktsiya va implementatsiyani mustaqil rivojlantirish kerak
- Runtime'da implementatsiyani o'zgartirish kerak
- Platform-specific implementatsiyalar bor
- Kombinatorial portlash muammosi bor
- Cross-platform development kerak
- Flexible va kengaytiriluvchi tizim yaratish kerak

### ❌ Ishlatmaslik kerak:
- Oddiy implementatsiya uchun
- Bitta platform uchun
- Implementatsiya o'zgarmaydigan hollarda
- Performance kritik joylarda
- Kichik tizimlar uchun

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Bridge + Abstract Factory**
```java
public abstract class UIFactory {
    public abstract Button createButton();
    public abstract Window createWindow();
}

public class WindowsFactory extends UIFactory {
    @Override
    public Button createButton() {
        return new Button(new WindowsButtonImpl());
    }
    
    @Override
    public Window createWindow() {
        return new Window(new WindowsWindowImpl());
    }
}
```

### 2. **Bridge + Builder**
```java
public class MessageBuilder {
    private MessageService service;
    private String content;
    private String recipient;
    
    public MessageBuilder setService(MessageService service) {
        this.service = service;
        return this;
    }
    
    public MessageBuilder setContent(String content) {
        this.content = content;
        return this;
    }
    
    public MessageBuilder setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }
    
    public MessageSender build() {
        return new EmailSender(service);
    }
}
```

### 3. **Bridge + Strategy**
```java
public class PaymentProcessor {
    private Payment payment;
    
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public void processPayment(double amount) {
        payment.processPayment(amount);
    }
}
```

---

## 🎯 Xulosa

Bridge Pattern:

* ✅ Abstraktsiya va implementatsiyani bir-biridan ajratib, mustaqil rivojlanishga imkon beradi
* ✅ Runtime'da implementatsiyani o'zgartirish imkoniyatini beradi
* ✅ Platform mustaqillik va cross-platform development'ni qo'llab-quvvatlaydi
* ✅ Kombinatorial portlash muammosini hal qiladi
* ✅ Kod qayta ishlatish va kengaytirilishlikni ta'minlaydi

**Tavsiya**: Bridge pattern katta va murakkab tizimlar uchun juda foydali, ayniqsa abstraktsiya va implementatsiya mustaqil rivojlanishi kerak bo'lgan hollarda. Kichik loyihalar uchun ortiqcha bo'lishi mumkin.