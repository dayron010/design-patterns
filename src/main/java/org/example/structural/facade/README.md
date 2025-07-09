# 🏢 Facade Design Pattern

## 📚 Pattern haqida ma'lumot

> *Facade pattern - bu murakkab subsystem'ga sodda interfeys taqdim etuvchi strukturaviy dizayn namunasi. Murakkab tizimning soddalashtirilgan ko'rinishini ta'minlaydi*
> 
> (Quyi tizimdagi interfacelar to'plamiga yagona interfaceni taqdim etadi. Facade namunasi quyi tizimdan foydalanishni osonlashtiradigan yuqori darajadagi interfaceni belgilaydi.)
> 
> — Gang of Four (GoF)

Facade design pattern - bu **strukturaviy (structural)** pattern kategoriyasiga kiradi. Bu pattern murakkab subsystem'lar to'plamiga bitta sodda interfeys orqali kirish imkonini beradi. Facade real hayotdagi fasad kabi - binoning murakkab ichki tuzilishini yashirib, tashqaridan sodda ko'rinish beradi.

[Facade Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/facade/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Murakkab subsystem'lar bilan ishlash
- Ko'plab bog'liq klasslar bilan ishlash qiyin
- Har bir subsystem bilan alohida tanishish vaqt talab etadi
- Subsystem'lar o'rtasidagi bog'liqliklar murakkab

### 2️⃣ Tight coupling muammosi
- Mijoz kodi subsystem'ning ichki tuzilishini bilishi kerak
- Subsystem o'zgarganda mijoz kodi ham o'zgarishi kerak
- Qayta ishlatish qiyin

### 3️⃣ Kod takrorlash
- Bir xil subsystem operatsiyalari turli joylarda takrorlanadi
- Murakkab initialization ketma-ketligi har joyda yoziladi
- Xatolik ehtimoli yuqori

### 4️⃣ API murakkabligi
- Katta kutubxonalar juda ko'p interfeys taqdim etadi
- Oddiy vazifalar uchun murakkab konfiguratsiya kerak
- Yangi ishchi uchun o'rganish qiyin

---

## 💡 Qanday hal qiladi

### ✅ Sodda interfeys
- Murakkab subsystem'lar uchun bitta, oson interfeys taqdim etadi
- Eng muhim funksionallikni to'plangan holda beradi
- Mijoz faqat facade bilan ishlaydi

### ✅ Abstraksiya
- Subsystem'ning ichki murakkabligini yashiradi
- Mijoz kod subsystem'dan mustaqil bo'ladi
- Implementatsiya tafsilotlari facade orqasida berkitiladi

### ✅ Loose coupling
- Mijoz va subsystem o'rtasidagi bog'liqlikni kamaytiradi
- Subsystem o'zgarishi facade orqali boshqariladi
- Qayta ishlatish osonlashadi

### ✅ Convenience methods
- Eng keng tarqalgan use case'lar uchun qulay metodlar
- Murakkab workflow'larni sodda qiladi
- Default qiymatlar va oson konfiguratsiya

---

## 🧩 Implementatsiyasi

Facade Pattern uchta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Facade** | Sodda interfeys taqdim etuvchi klass |
| **Subsystem classes** | Murakkab funksionallik amalga oshiruvchi klasslar |
| **Client** | Facade orqali subsystem'dan foydalanadigan kod |

### 💻 Java implementatsiyasi

```java
// 1. Subsystem klasslar - murakkab funksionallik
public class CPU {
    public void start() {
        System.out.println("CPU started");
    }
    
    public void execute() {
        System.out.println("CPU executing commands");
    }
    
    public void stop() {
        System.out.println("CPU stopped");
    }
}

public class Memory {
    public void load() {
        System.out.println("Memory loaded");
    }
    
    public void clear() {
        System.out.println("Memory cleared");
    }
}

public class Storage {
    public void read() {
        System.out.println("Storage reading data");
    }
    
    public void write() {
        System.out.println("Storage writing data");
    }
}

// 2. Facade - sodda interfeys
public class ComputerFacade {
    private final CPU cpu;
    private final Memory memory;
    private final Storage storage;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.storage = new Storage();
    }
    
    // Murakkab startup jarayonini sodda qiladi
    public void startComputer() {
        System.out.println("Starting computer...");
        cpu.start();
        memory.load();
        storage.read();
        cpu.execute();
        System.out.println("Computer started successfully!");
    }
    
    // Murakkab shutdown jarayonini sodda qiladi
    public void shutdownComputer() {
        System.out.println("Shutting down computer...");
        cpu.stop();
        memory.clear();
        storage.write();
        System.out.println("Computer shut down successfully!");
    }
}

// 3. Client - facade orqali ishlovchi
public class User {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        
        // Oddiy operatsiyalar
        computer.startComputer();
        System.out.println("--- Working ---");
        computer.shutdownComputer();
    }
}
```

---

## 📋 Facade Pattern turlari

### 1. **Simple Facade**
```java
// Oddiy facade - faqat sodda interfeys
public class DatabaseFacade {
    private ConnectionManager connectionManager;
    private QueryExecutor queryExecutor;
    
    public List<User> getAllUsers() {
        Connection conn = connectionManager.getConnection();
        return queryExecutor.executeQuery(conn, "SELECT * FROM users");
    }
    
    public void saveUser(User user) {
        Connection conn = connectionManager.getConnection();
        queryExecutor.executeUpdate(conn, "INSERT INTO users ...", user);
    }
}
```

### 2. **Layered Facade**
```java
// Qatlamli facade - har bir qatlam uchun alohida facade
public class ServiceLayer {
    private final BusinessLogicFacade businessLogic;
    private final DataAccessFacade dataAccess;
    
    public ServiceLayer() {
        this.businessLogic = new BusinessLogicFacade();
        this.dataAccess = new DataAccessFacade();
    }
    
    public void processOrder(Order order) {
        businessLogic.validateOrder(order);
        dataAccess.saveOrder(order);
        businessLogic.sendNotification(order);
    }
}
```

### 3. **Configurable Facade**
```java
// Konfiguratsiya qilinadigan facade
public class ReportFacade {
    private final ReportGenerator generator;
    private final ReportFormatter formatter;
    private final ReportExporter exporter;
    
    public void generateReport(ReportConfig config) {
        Data data = generator.generateData(config.getDataSource());
        FormattedReport report = formatter.format(data, config.getFormat());
        exporter.export(report, config.getOutputPath());
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **E-commerce Facade**
```java
// E-commerce tizimi uchun facade
public class OrderFacade {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final NotificationService notificationService;
    
    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
    }
    
    public OrderResult placeOrder(Order order) {
        try {
            // Murakkab biznes logikasi
            if (!inventoryService.checkAvailability(order.getItems())) {
                return OrderResult.OUT_OF_STOCK;
            }
            
            PaymentResult payment = paymentService.processPayment(order.getPayment());
            if (!payment.isSuccessful()) {
                return OrderResult.PAYMENT_FAILED;
            }
            
            inventoryService.reserveItems(order.getItems());
            ShippingInfo shipping = shippingService.scheduleDelivery(order);
            notificationService.sendOrderConfirmation(order, shipping);
            
            return OrderResult.SUCCESS;
        } catch (Exception e) {
            // Rollback operatsiyalari
            inventoryService.releaseItems(order.getItems());
            paymentService.refund(order.getPayment());
            return OrderResult.ERROR;
        }
    }
}
```

### 2. **Media Player Facade**
```java
// Media player tizimi uchun facade
public class MediaPlayerFacade {
    private final AudioDecoder audioDecoder;
    private final VideoDecoder videoDecoder;
    private final AudioOutput audioOutput;
    private final VideoOutput videoOutput;
    private final SubtitleRenderer subtitleRenderer;
    
    public void playMedia(String fileName) {
        MediaInfo info = getMediaInfo(fileName);
        
        if (info.hasAudio()) {
            audioDecoder.decode(info.getAudioStream());
            audioOutput.play();
        }
        
        if (info.hasVideo()) {
            videoDecoder.decode(info.getVideoStream());
            videoOutput.display();
        }
        
        if (info.hasSubtitles()) {
            subtitleRenderer.render(info.getSubtitleStream());
        }
    }
    
    public void stopMedia() {
        audioOutput.stop();
        videoOutput.stop();
        subtitleRenderer.hide();
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Soddalik** | Murakkab subsystem'lar uchun sodda interfeys |
| **Loose coupling** | Mijoz va subsystem o'rtasidagi bog'liqlik kamaytiradi |
| **Yashirish** | Subsystem'ning ichki murakkabligini berkitadi |
| **Centralized control** | Subsystem'ga kirish markazlashtiriladi |
| **Oson test qilish** | Facade orqali test yozish osonroq |
| **Qayta ishlatish** | Umumiy operatsiyalarni qayta ishlatish mumkin |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Cheklangan funksionallik** | Faqat eng muhim funksiyalar taqdim etiladi |
| **God object xavfi** | Facade juda katta va murakkab bo'lishi mumkin |
| **Qo'shimcha qatlam** | Ortiqcha abstraksiya qatlami qo'shiladi |
| **Dependency on facade** | Mijoz facade'ga bog'liq bo'lib qoladi |
| **Konfiguratsiya murakkabligi** | Facade o'zi murakkab konfiguratsiya talab qilishi mumkin |

---

## 🔄 Facade vs boshqa pattern'lar

| Jihat | Facade | Adapter | Proxy | Mediator |
|-------|--------|---------|-------|----------|
| **Maqsad** | Soddalashtirishg | Interfeys moslashuvi | Dostup nazorati | Kommunikatsiya |
| **Strukturasi** | Ko'p subsystem | Bitta adaptee | Bitta subject | Ko'p object |
| **Interfeys** | Yangi sodda interfeys | Interfeys o'zgaradi | Bir xil interfeys | Yangi interfeys |
| **Munosabat** | Bir yo'nalishli | Bir yo'nalishli | Ikki yo'nalishli | Ko'p yo'nalishli |

---

## 📚 JDK-da Facade Pattern misollari

### 1. **java.net.URL**
```java
// Murakkab network operatsiyalarini sodda qiladi
URL url = new URL("https://api.example.com/data");
InputStream inputStream = url.openStream();
```

### 2. **javax.faces.context.FacesContext**
```java
// JSF subsystem'iga sodda interfeys
FacesContext context = FacesContext.getCurrentInstance();
ExternalContext externalContext = context.getExternalContext();
```

### 3. **java.util.logging.Logger**
```java
// Logging subsystem'ini sodda qiladi
Logger logger = Logger.getLogger("MyApp");
logger.info("Application started");
```

### 4. **ExecutorService**
```java
// Thread management'ni sodda qiladi
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(() -> System.out.println("Task executed"));
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Murakkab subsystem'lar bilan ishlash kerak
- Subsystem'ning faqat ma'lum qismini ishlatish kerak
- Layered architecture yaratishda
- Third-party kutubxonalarni wrapper qilishda
- Legacy tizimlarni modernizatsiya qilishda
- API'ni soddalashtirishda

### ❌ Ishlatmaslik kerak:
- Subsystem allaqachon sodda bo'lsa
- Barcha subsystem funksionallik kerak bo'lsa
- Juda ko'p facade kerak bo'lganda
- Performance kritik joylarda

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Facade + Singleton**
```java
public class DatabaseFacade {
    private static DatabaseFacade instance;
    private final ConnectionManager connectionManager;
    
    private DatabaseFacade() {
        this.connectionManager = new ConnectionManager();
    }
    
    public static DatabaseFacade getInstance() {
        if (instance == null) {
            instance = new DatabaseFacade();
        }
        return instance;
    }
    
    public void executeQuery(String query) {
        Connection conn = connectionManager.getConnection();
        // Query execution logic
    }
}
```

### 2. **Facade + Factory Method**
```java
public abstract class ServiceFacade {
    protected abstract PaymentService createPaymentService();
    protected abstract ShippingService createShippingService();
    
    public final void processOrder(Order order) {
        PaymentService paymentService = createPaymentService();
        ShippingService shippingService = createShippingService();
        
        paymentService.processPayment(order);
        shippingService.shipOrder(order);
    }
}
```

### 3. **Facade + Observer**
```java
public class OrderFacade {
    private final List<OrderObserver> observers = new ArrayList<>();
    
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    
    public void placeOrder(Order order) {
        // Order processing logic
        processOrder(order);
        
        // Notify observers
        observers.forEach(observer -> observer.onOrderPlaced(order));
    }
}
```

---

## 🎯 Xulosa

Facade Pattern:

* ✅ Murakkab subsystem'larni sodda interfeys orqali boshqarish imkonini beradi
* ✅ Kod o'qilishini va saqlashini osonlashtiradi
* ✅ Tight coupling muammolarini hal qiladi
* ✅ Layered architecture yaratishda juda foydali

**Tavsiya**: Facade pattern murakkab tizimlarni boshqarishda juda foydali, lekin ortiqcha soddalashtirishdan saqlaning. Mijoz ehtiyojlariga mos keladigan to'g'ri abstraktsiya darajasini tanlash muhim.