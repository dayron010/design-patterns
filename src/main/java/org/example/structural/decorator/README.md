# 🎨 Decorator Design Pattern

## 📚 Pattern haqida ma'lumot

> *Decorator pattern - bu obyektga dinamik ravishda yangi xususiyatlar qo'shish imkonini beruvchi strukturaviy dizayn namunasi.*
> (Obyektni boshqa obyektlar bilan o'rab, yangi funksionallik qo'shadi)
> 
> — Gang of Four (GoF)

Decorator design pattern - bu **strukturaviy (structural)** pattern kategoriyasiga kiradi. Bu pattern obyektning tuzilishini o'zgartirmasdan unga yangi xususiyatlar qo'shish imkonini beradi. Decorator real hayotdagi bezatish kabi ishlaydi - masalan, tortni turli qatlamlar bilan bezash.

[Decorator Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/decorator/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Inheritance cheklovlari
- Subclassing orqali barcha kombinatsiyalar uchun klass yaratish mantiqsiz
- Compile time'da xususiyatlar belgilanadi, runtime'da o'zgartirib bo'lmaydi
- Bir nechta xususiyatlar kombinatsiyasi uchun exponential miqdorda klass kerak

### 2️⃣ Dinamik xususiyat qo'shish
- Runtime'da obyektga yangi xususiyatlar qo'shish kerak
- Xususiyatlarni ketma-ket qo'shish va olib tashlash
- Conditional logic bilan kodni murakkablashtirmasdan

### 3️⃣ Single Responsibility Principle
- Bir klass bir nechta xususiyatni boshqarmasligi kerak
- Har bir xususiyat alohida klass bilan boshqarilishi
- Kodning qayta ishlatilishi va test qilish osonligi

### 4️⃣ Open/Closed Principle
- Yangi xususiyatlar qo'shish uchun mavjud kodlarni o'zgartirmaslik
- Kengaytish uchun ochiq, o'zgartirish uchun yopiq

---

## 💡 Qanday hal qiladi

### ✅ Obyekt wrapping
- Decorator obyekt asosiy obyektni o'rab oladi
- Asosiy obyektga murojaat qilishdan oldin qo'shimcha amallar bajaradi
- Bir nechta decorator ketma-ket ishlatish mumkin

### ✅ Dinamik kompozitsiya
- Runtime'da qaysi xususiyatlar kerakligini aniqlash
- Xususiyatlarni dinamik ravishda qo'shish va olib tashlash
- Flexible va konfiguratsiya qilinadigan tizim

### ✅ Recursive tuzilish
- Decorator ham Component interfeysi implement qiladi
- Decorator boshqa decorator'ni ham o'rashi mumkin
- Cheksiz kombinatsiyalar yaratish imkoniyati

### ✅ Transparent wrapping
- Mijoz uchun decorator mavjudligi ko'rinmaydi
- Bir xil interfeys orqali ishlash
- Seamless integratsiya

---

## 🧩 Implementatsiyasi

Decorator Pattern to'rtta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Component** | Asosiy interfeys yoki abstrakt klass |
| **ConcreteComponent** | Asosiy funksionallik implementatsiyasi |
| **Decorator** | Wrapper'ning abstrakt klassi |
| **ConcreteDecorator** | Konkret xususiyat qo'shuvchi klass |

### 💻 Java implementatsiyasi

```java
// 1. Component - asosiy interfeys
public interface Coffee {
    double getCost();
    String getDescription();
}

// 2. ConcreteComponent - asosiy implementatsiya
public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 2.0;
    }
    
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}

// 3. Decorator - abstrakt wrapper
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    @Override
    public double getCost() {
        return coffee.getCost();
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
}

// 4. ConcreteDecorator - konkret xususiyatlar
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
}

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
}

public class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.8;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whipped Cream";
    }
}

// 5. Demo
public class CoffeeShop {
    public static void main(String[] args) {
        // Oddiy qahva
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + " - $" + simpleCoffee.getCost());
        
        // Sut qo'shilgan qahva
        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println(milkCoffee.getDescription() + " - $" + milkCoffee.getCost());
        
        // Sut va shakar qo'shilgan qahva
        Coffee milkSugarCoffee = new SugarDecorator(milkCoffee);
        System.out.println(milkSugarCoffee.getDescription() + " - $" + milkSugarCoffee.getCost());
        
        // Barcha qo'shimchalar bilan
        Coffee fancyCoffee = new WhippedCreamDecorator(
            new SugarDecorator(
                new MilkDecorator(simpleCoffee)
            )
        );
        System.out.println(fancyCoffee.getDescription() + " - $" + fancyCoffee.getCost());
    }
}
```

---

## 📋 Decorator Pattern turlari

### 1. **Simple Decorator**
```java
// Oddiy decorator - bitta xususiyat qo'shadi
public class LoggingDecorator extends ServiceDecorator {
    public LoggingDecorator(Service service) {
        super(service);
    }
    
    @Override
    public void execute() {
        System.out.println("Logging before execution");
        service.execute();
        System.out.println("Logging after execution");
    }
}
```

### 2. **Transparent Decorator**
```java
// Transparent decorator - mijoz uchun ko'rinmaydi
public class CachingDecorator implements DataService {
    private final DataService dataService;
    private final Map<String, Object> cache = new HashMap<>();
    
    public CachingDecorator(DataService dataService) {
        this.dataService = dataService;
    }
    
    @Override
    public Object getData(String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        Object data = dataService.getData(key);
        cache.put(key, data);
        return data;
    }
}
```

### 3. **Composite Decorator**
```java
// Bir nechta decorator'ni birlashtiradigan decorator
public class CompositeDecorator implements Component {
    private final List<Decorator> decorators = new ArrayList<>();
    private final Component component;
    
    public CompositeDecorator(Component component) {
        this.component = component;
    }
    
    public void addDecorator(Decorator decorator) {
        decorators.add(decorator);
    }
    
    @Override
    public void operation() {
        // Pre-processing
        decorators.forEach(d -> d.preProcess());
        
        component.operation();
        
        // Post-processing
        decorators.forEach(d -> d.postProcess());
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **Text Processing Decorator**
```java
// Matn ishlash uchun decorator
public interface TextProcessor {
    String process(String text);
}

public class PlainTextProcessor implements TextProcessor {
    @Override
    public String process(String text) {
        return text;
    }
}

public class UpperCaseDecorator extends TextDecorator {
    public UpperCaseDecorator(TextProcessor processor) {
        super(processor);
    }
    
    @Override
    public String process(String text) {
        return processor.process(text).toUpperCase();
    }
}

public class HtmlDecorator extends TextDecorator {
    public HtmlDecorator(TextProcessor processor) {
        super(processor);
    }
    
    @Override
    public String process(String text) {
        return "<html>" + processor.process(text) + "</html>";
    }
}

public class BoldDecorator extends TextDecorator {
    public BoldDecorator(TextProcessor processor) {
        super(processor);
    }
    
    @Override
    public String process(String text) {
        return "<b>" + processor.process(text) + "</b>";
    }
}
```

### 2. **Security Decorator**
```java
// Xavfsizlik uchun decorator
public interface UserService {
    User getUser(String id);
    void updateUser(User user);
}

public class AuthenticationDecorator implements UserService {
    private final UserService userService;
    private final SecurityContext securityContext;
    
    public AuthenticationDecorator(UserService userService, SecurityContext securityContext) {
        this.userService = userService;
        this.securityContext = securityContext;
    }
    
    @Override
    public User getUser(String id) {
        if (!securityContext.isAuthenticated()) {
            throw new SecurityException("User not authenticated");
        }
        return userService.getUser(id);
    }
    
    @Override
    public void updateUser(User user) {
        if (!securityContext.hasPermission("UPDATE_USER")) {
            throw new SecurityException("Insufficient permissions");
        }
        userService.updateUser(user);
    }
}

public class AuditDecorator implements UserService {
    private final UserService userService;
    private final AuditLogger auditLogger;
    
    public AuditDecorator(UserService userService, AuditLogger auditLogger) {
        this.userService = userService;
        this.auditLogger = auditLogger;
    }
    
    @Override
    public User getUser(String id) {
        auditLogger.log("Accessing user: " + id);
        return userService.getUser(id);
    }
    
    @Override
    public void updateUser(User user) {
        auditLogger.log("Updating user: " + user.getId());
        userService.updateUser(user);
        auditLogger.log("User updated successfully");
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Dinamik xususiyat qo'shish** | Runtime'da obyektga yangi xususiyatlar qo'shish mumkin |
| **Flexible kombinatsiya** | Xususiyatlarni turli usulda kombinatsiya qilish |
| **Single Responsibility** | Har bir decorator bitta xususiyat uchun javobgar |
| **Open/Closed Principle** | Yangi decorator'lar qo'shish, mavjud kodlarni o'zgartirmasdan |
| **Inheritance muqobili** | Subclassing'dan ko'ra moslashuvchan yondashuv |
| **Transparent wrapping** | Mijoz uchun decorator mavjudligi ko'rinmaydi |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Murakkab debugging** | Bir nechta qatlam debug qilishni qiyinlashtiradi |
| **Object identity** | Wrapped obyekt identity'si o'zgaradi |
| **Performance overhead** | Qo'shimcha method call'lar performance'ga ta'sir qiladi |
| **Memory usage** | Har bir decorator qo'shimcha xotira sarflaydi |
| **Order dependency** | Decorator'lar tartibi natijaga ta'sir qilishi mumkin |
| **Konfiguratsiya murakkabligi** | To'g'ri kombinatsiyani tanlash qiyin bo'lishi mumkin |

---

## 🔄 Decorator vs boshqa pattern'lar

| Jihat | Decorator | Adapter | Proxy | Facade |
|-------|-----------|---------|-------|---------|
| **Maqsad** | Xususiyat qo'shish | Interfeys moslash | Dostup nazorati | Soddalashtirish |
| **Obyekt soni** | Bir xil | Bir xil | Bir xil | Ko'p obyekt |
| **Interfeys** | Bir xil | O'zgaradi | Bir xil | Yangi interfeys |
| **Recursive** | Ha | Yo'q | Yo'q | Yo'q |
| **Funksionallik** | Kengaytiradi | O'zgartirmaydi | Nazorat qiladi | Soddalashtiradi |

---

## 📚 JDK-da Decorator Pattern misollari

### 1. **I/O Streams**
```java
// Input stream decorator'lari
FileInputStream fis = new FileInputStream("file.txt");
BufferedInputStream bis = new BufferedInputStream(fis);
DataInputStream dis = new DataInputStream(bis);

// Output stream decorator'lari
FileOutputStream fos = new FileOutputStream("output.txt");
BufferedOutputStream bos = new BufferedOutputStream(fos);
DataOutputStream dos = new DataOutputStream(bos);
```

### 2. **Collections**
```java
// Collection decorator'lari
List<String> list = new ArrayList<>();
List<String> synchronizedList = Collections.synchronizedList(list);
List<String> unmodifiableList = Collections.unmodifiableList(list);
```

### 3. **GUI Components**
```java
// Swing component decorator'lari
JTextField textField = new JTextField();
JScrollPane scrollPane = new JScrollPane(textField);
JPanel panel = new JPanel();
panel.setBorder(BorderFactory.createTitledBorder("Input"));
```

### 4. **HttpServletRequestWrapper**
```java
// Servlet API decorator
public class CustomRequestWrapper extends HttpServletRequestWrapper {
    public CustomRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return value != null ? value.trim() : null;
    }
}
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Runtime'da obyektga xususiyatlar qo'shish kerak
- Bir nechta xususiyatlar kombinatsiyasi kerak
- Inheritance orqali subclass'lar soni ko'p bo'lishi mumkin
- Xususiyatlarni dinamik ravishda boshqarish kerak
- Cross-cutting concerns (logging, security, caching) qo'shish
- Existing code'ni o'zgartirmasdan funksionallik kengaytirish

### ❌ Ishlatmaslik kerak:
- Oddiy xususiyat qo'shish kerak bo'lsa
- Compile time'da barcha xususiyatlar ma'lum bo'lsa
- Performance kritik joylarda
- Object identity muhim bo'lsa
- Decorator'lar soni juda ko'p bo'lishi mumkin

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Decorator + Factory Method**
```java
public abstract class DecoratorFactory {
    public abstract Component createDecorator(Component component, String type);
}

public class ConcreteDecoratorFactory extends DecoratorFactory {
    @Override
    public Component createDecorator(Component component, String type) {
        switch (type) {
            case "logging":
                return new LoggingDecorator(component);
            case "caching":
                return new CachingDecorator(component);
            case "security":
                return new SecurityDecorator(component);
            default:
                return component;
        }
    }
}
```

### 2. **Decorator + Builder**
```java
public class ServiceBuilder {
    private Service service;
    
    public ServiceBuilder(Service service) {
        this.service = service;
    }
    
    public ServiceBuilder withLogging() {
        service = new LoggingDecorator(service);
        return this;
    }
    
    public ServiceBuilder withCaching() {
        service = new CachingDecorator(service);
        return this;
    }
    
    public ServiceBuilder withSecurity() {
        service = new SecurityDecorator(service);
        return this;
    }
    
    public Service build() {
        return service;
    }
}

// Ishlatish
Service service = new ServiceBuilder(new BaseService())
    .withLogging()
    .withCaching()
    .withSecurity()
    .build();
```

### 3. **Decorator + Strategy**
```java
public class ConfigurableDecorator implements Component {
    private final Component component;
    private final DecorationStrategy strategy;
    
    public ConfigurableDecorator(Component component, DecorationStrategy strategy) {
        this.component = component;
        this.strategy = strategy;
    }
    
    @Override
    public void operation() {
        strategy.preProcess();
        component.operation();
        strategy.postProcess();
    }
}
```

---

## 💡 Best Practices

### 1. **Interface Segregation**
```java
// Katta interfeys o'rniga kichik interfeys'larni ishlatish
public interface Readable {
    String read();
}

public interface Writable {
    void write(String data);
}

public interface ReadWritable extends Readable, Writable {
}
```

### 2. **Immutable Decorators**
```java
// Immutable decorator yaratish
public final class ImmutableDecorator implements Component {
    private final Component component;
    private final String decoration;
    
    public ImmutableDecorator(Component component, String decoration) {
        this.component = Objects.requireNonNull(component);
        this.decoration = Objects.requireNonNull(decoration);
    }
    
    @Override
    public String process(String input) {
        return decoration + component.process(input);
    }
}
```

### 3. **Fluent Interface**
```java
// Fluent interface bilan decorator
public class FluentDecorator {
    private Component component;
    
    public static FluentDecorator wrap(Component component) {
        return new FluentDecorator(component);
    }
    
    public FluentDecorator withLogging() {
        component = new LoggingDecorator(component);
        return this;
    }
    
    public FluentDecorator withCaching() {
        component = new CachingDecorator(component);
        return this;
    }
    
    public Component build() {
        return component;
    }
}
```

---

## 🎯 Xulosa

Decorator Pattern:

* ✅ Obyektga dinamik ravishda yangi xususiyatlar qo'shish imkonini beradi
* ✅ Inheritance'dan ko'ra moslashuvchan yondashuv taqdim etadi
* ✅ Single Responsibility va Open/Closed tamoyillariga amal qiladi
* ✅ Cross-cutting concerns'ni elegant tarzda hal qiladi

**Tavsiya**: Decorator pattern xususiyatlarni dinamik ravishda boshqarish uchun juda foydali, lekin murakkablik va performance'ni hisobga olib ishlatish kerak. Oddiy holatlar uchun ortiqcha bo'lishi mumkin.