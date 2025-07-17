# ⛓️ Chain of Responsibility Design Pattern

## 📚 Pattern haqida ma'lumot

> *Chain of Responsibility pattern - bu so'rovni bir nechta handler'lar zanjiri orqali yuborish imkonini beruvchi xulq-atvoriy dizayn namunasi. Har bir handler so'rovni qayta ishlash yoki keyingi handler'ga uzatish haqida qaror qabul qiladi.*
> 
> — Gang of Four (GoF)

Chain of Responsibility design pattern - bu **xulq-atvoriy (behavioral)** pattern kategoriyasiga kiradi. Bu pattern so'rovni yuboruvchi (sender) va qabul qiluvchi (receiver) o'rtasidagi bog'liqlikni kamaytiradi, bir nechta obyektga so'rovni qayta ishlash imkoniyatini beradi.

![Chain of Responsibility Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Qattiq bog'liqlik muammosi
- So'rov yuboruvchi aniq handler'ni bilishi shart
- Sender va receiver orasidagi qattiq bog'liqlik
- Yangi handler qo'shish uchun sender kodini o'zgartirish kerak

### 2️⃣ Bir nechta handler'lar
- So'rov turli handler'lar tomonidan qayta ishlanishi mumkin
- Qaysi handler so'rovni qayta ishlashini oldindan bilish qiyin
- Dynamic ravishda handler'larni o'zgartirish kerak

### 3️⃣ Conditional kod muammosi
- Ko'plab if-else yoki switch-case bayonotlari
- Kod maintainability va readability muammolari
- Yangi handler qo'shish uchun conditional logic o'zgartirish

### 4️⃣ Javobgarlik taqsimoti
- Turli xil so'rovlar uchun turli handler'lar
- Handler'larning hierarkiyasini yaratish kerak
- Vazifalarni dinamik tarzda taqsimlash

---

## 💡 Qanday hal qiladi

### ✅ Zanjir tizimi
- Handler'larni zanjir ko'rinishida bog'lash
- Har bir handler keyingi handler'ga reference saqlaydi
- So'rov zanjir bo'ylab uzatiladi

### ✅ Loose coupling
- Sender handler'ni aniq bilmaydi
- Runtime'da zanjir strukturasini o'zgartirish mumkin
- Yangi handler'larni oson qo'shish

### ✅ Javobgarlik taqsimoti
- Har bir handler o'z vazifasiga mas'ul
- Single Responsibility Principle'ni amalga oshiradi
- Kod modularity va maintainability'ni yaxshilaydi

### ✅ Dinamik qayta ishlash
- Runtime'da handler'lar ketma-ketligini o'zgartirish
- Conditional logic'ni handler'larga taqsimlash
- Flexible va kengaytiriladigan arxitektura

---

## 🧩 Implementatsiyasi

Chain of Responsibility Pattern uchta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Handler** | Abstracthandler interfeysi yoki abstract klassi |
| **ConcreteHandler** | Aniq handler implementatsiyasi |
| **Client** | So'rov yuboruvchi va zanjirni sozlovchi |

### 💻 Java implementatsiyasi

```java
// 1. Handler abstract klassi
public abstract class Handler {
    protected Handler nextHandler;
    
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public abstract void handleRequest(Request request);
}

// 2. Request klassi
public class Request {
    private String type;
    private String content;
    private int priority;
    
    public Request(String type, String content, int priority) {
        this.type = type;
        this.content = content;
        this.priority = priority;
    }
    
    // getters va setters
    public String getType() { return type; }
    public String getContent() { return content; }
    public int getPriority() { return priority; }
}

// 3. ConcreteHandler'lar
public class BasicSupportHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() <= 1) {
            System.out.println("Basic Support: " + request.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

public class AdvancedSupportHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() <= 2) {
            System.out.println("Advanced Support: " + request.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

public class ManagerHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() <= 3) {
            System.out.println("Manager: " + request.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

public class DirectorHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() <= 4) {
            System.out.println("Director: " + request.getContent());
        } else {
            System.out.println("Request cannot be handled: " + request.getContent());
        }
    }
}

// 4. Client
public class SupportSystem {
    private Handler handlerChain;
    
    public SupportSystem() {
        buildHandlerChain();
    }
    
    private void buildHandlerChain() {
        Handler basicSupport = new BasicSupportHandler();
        Handler advancedSupport = new AdvancedSupportHandler();
        Handler manager = new ManagerHandler();
        Handler director = new DirectorHandler();
        
        basicSupport.setNextHandler(advancedSupport);
        advancedSupport.setNextHandler(manager);
        manager.setNextHandler(director);
        
        this.handlerChain = basicSupport;
    }
    
    public void handleRequest(Request request) {
        handlerChain.handleRequest(request);
    }
}

// 5. Demo
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        SupportSystem supportSystem = new SupportSystem();
        
        supportSystem.handleRequest(new Request("BASIC", "Password reset", 1));
        supportSystem.handleRequest(new Request("ADVANCED", "System integration", 2));
        supportSystem.handleRequest(new Request("ESCALATED", "Contract negotiation", 3));
        supportSystem.handleRequest(new Request("CRITICAL", "Legal issue", 4));
        supportSystem.handleRequest(new Request("EMERGENCY", "Company merger", 5));
    }
}
```

---

## 📋 Chain of Responsibility Pattern turlari

### 1. **Pure Chain of Responsibility**
```java
// Faqat bitta handler so'rovni qayta ishlaydi
public class PureHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (canHandle(request)) {
            processRequest(request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
    
    private boolean canHandle(Request request) {
        // Handler so'rovni qayta ishlay olishi mumkinligini tekshiradi
        return false;
    }
    
    private void processRequest(Request request) {
        // So'rovni qayta ishlash
    }
}
```

### 2. **Impure Chain of Responsibility**
```java
// Ko'plab handler'lar so'rovni qayta ishlashi mumkin
public class ImpureHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (canHandle(request)) {
            processRequest(request);
        }
        
        // Har doim keyingi handler'ga uzatadi
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
```

### 3. **Conditional Chain**
```java
// Shartli zanjir
public class ConditionalHandler extends Handler {
    @Override
    public void handleRequest(Request request) {
        if (shouldProcess(request)) {
            processRequest(request);
            
            if (shouldContinue(request)) {
                if (nextHandler != null) {
                    nextHandler.handleRequest(request);
                }
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
    
    private boolean shouldProcess(Request request) {
        // Qayta ishlash shartini tekshirish
        return true;
    }
    
    private boolean shouldContinue(Request request) {
        // Davom etish shartini tekshirish
        return false;
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **Web Filter Chain**
```java
// Web-ilovalar uchun filter zanjiri
public abstract class WebFilter {
    protected WebFilter nextFilter;
    
    public void setNextFilter(WebFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
    
    public abstract void doFilter(HttpRequest request, HttpResponse response);
}

public class AuthenticationFilter extends WebFilter {
    @Override
    public void doFilter(HttpRequest request, HttpResponse response) {
        if (isAuthenticated(request)) {
            System.out.println("Authentication passed");
            if (nextFilter != null) {
                nextFilter.doFilter(request, response);
            }
        } else {
            response.setStatus(401);
            response.setMessage("Authentication failed");
        }
    }
    
    private boolean isAuthenticated(HttpRequest request) {
        return request.getHeader("Authorization") != null;
    }
}

public class AuthorizationFilter extends WebFilter {
    @Override
    public void doFilter(HttpRequest request, HttpResponse response) {
        if (isAuthorized(request)) {
            System.out.println("Authorization passed");
            if (nextFilter != null) {
                nextFilter.doFilter(request, response);
            }
        } else {
            response.setStatus(403);
            response.setMessage("Access denied");
        }
    }
    
    private boolean isAuthorized(HttpRequest request) {
        return request.getHeader("Role").equals("ADMIN");
    }
}

public class LoggingFilter extends WebFilter {
    @Override
    public void doFilter(HttpRequest request, HttpResponse response) {
        System.out.println("Request logged: " + request.getUrl());
        if (nextFilter != null) {
            nextFilter.doFilter(request, response);
        }
    }
}
```

### 2. **ATM Cash Dispenser**
```java
// ATM pul berish tizimi
public abstract class CashDispenser {
    protected CashDispenser nextDispenser;
    
    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }
    
    public abstract void dispense(Currency currency);
}

public class Dollar50Dispenser extends CashDispenser {
    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 50) {
            int num = currency.getAmount() / 50;
            int remainder = currency.getAmount() % 50;
            System.out.println("Dispensing " + num + " 50$ note(s)");
            
            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(new Currency(remainder));
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(currency);
        }
    }
}

public class Dollar20Dispenser extends CashDispenser {
    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 20) {
            int num = currency.getAmount() / 20;
            int remainder = currency.getAmount() % 20;
            System.out.println("Dispensing " + num + " 20$ note(s)");
            
            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(new Currency(remainder));
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(currency);
        }
    }
}

public class Dollar10Dispenser extends CashDispenser {
    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 10) {
            int num = currency.getAmount() / 10;
            int remainder = currency.getAmount() % 10;
            System.out.println("Dispensing " + num + " 10$ note(s)");
            
            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(new Currency(remainder));
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(currency);
        }
    }
}

// ATM Machine
public class ATMDispenseChain {
    private CashDispenser firstDispenser;
    
    public ATMDispenseChain() {
        this.firstDispenser = new Dollar50Dispenser();
        
        CashDispenser dollar20 = new Dollar20Dispenser();
        CashDispenser dollar10 = new Dollar10Dispenser();
        
        firstDispenser.setNextDispenser(dollar20);
        dollar20.setNextDispenser(dollar10);
    }
    
    public void dispense(Currency currency) {
        firstDispenser.dispense(currency);
    }
}
```

### 3. **Logging Framework**
```java
// Logging darajalari uchun chain
public abstract class Logger {
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int ERROR = 3;
    
    protected int level;
    protected Logger nextLogger;
    
    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }
    
    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }
    
    protected abstract void write(String message);
}

public class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

public class FileLogger extends Logger {
    public FileLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

public class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.err.println("Error Console::Logger: " + message);
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Loose coupling** | Sender va receiver o'rtasidagi bog'liqlik kamaytiradi |
| **Flexible configuration** | Runtime'da zanjir strukturasini o'zgartirish mumkin |
| **Single Responsibility** | Har bir handler o'z vazifasiga mas'ul |
| **Open/Closed Principle** | Yangi handler'larni qo'shish oson |
| **Reusability** | Handler'larni turli zanjurlarda qayta ishlatish mumkin |
| **Conditional logic elimination** | If-else zanjirlarini kamaytirid |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Performance overhead** | Uzun zanjir bo'ylab so'rov uzatilishi |
| **Debugging qiyinligi** | Qaysi handler so'rovni qayta ishlaganini aniqlash qiyin |
| **Guarantee yo'qligi** | So'rov qayta ishlanishi kafolatlanmagan |
| **Memory overhead** | Handler obyektlari qo'shimcha xotira sarflaydi |
| **Zanjir uzilishi** | Noto'g'ri konfiguratsiya natijasida zanjir uzilishi mumkin |

---

## 🔄 Chain of Responsibility vs boshqa pattern'lar

| Jihat | Chain of Responsibility | Command | Observer | Strategy |
|-------|-------------------------|---------|----------|----------|
| **Maqsad** | So'rov uzatish | So'rov encapsulation | Event notification | Algorithm selection |
| **Coupling** | Loose coupling | Loose coupling | Loose coupling | Tight coupling |
| **Handler soni** | Ko'p handler | Bitta receiver | Ko'p observer | Bitta strategy |
| **Execution** | Ketma-ket | Deferred/immediate | Parallel | Immediate |

---

## 📚 JDK-da Chain of Responsibility misollari

### 1. **Servlet Filters**
```java
public void doFilter(ServletRequest request, ServletResponse response, 
                    FilterChain chain) throws IOException, ServletException {
    // Pre-processing
    chain.doFilter(request, response); // Keyingi filter'ga uzatish
    // Post-processing
}
```

### 2. **Exception Handling**
```java
try {
    // some code
} catch (SpecificException e) {
    // handle specific exception
} catch (GeneralException e) {
    // handle general exception
} catch (Exception e) {
    // handle any exception
}
```

### 3. **Event Handling**
```java
// AWT/Swing event handling
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // handle event or pass to next handler
    }
});
```

### 4. **Logger Hierarchy**
```java
// java.util.logging
Logger parentLogger = Logger.getLogger("com.example");
Logger childLogger = Logger.getLogger("com.example.service");
// Child logger avval o'z handler'larini, keyin parent'ning handler'larini ishlatadi
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- So'rov turli handler'lar tomonidan qayta ishlanishi mumkin
- Runtime'da handler'lar ketma-ketligini o'zgartirish kerak
- Sender aniq receiver'ni bilmasligi kerak
- Ko'plab if-else conditional logic mavjud
- Middleware yoki filter pipeline kerak

### ❌ Ishlatmaslik kerak:
- Faqat bitta handler so'rovni qayta ishlashi mumkin
- Handler'lar ketma-ketligi o'zgarmaydigan
- Performance kritik joylarda
- Oddiy conditional logic uchun

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Chain of Responsibility + Command**
```java
public abstract class CommandHandler {
    protected CommandHandler nextHandler;
    
    public void setNextHandler(CommandHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public abstract void handle(Command command);
}

public class CreateCommandHandler extends CommandHandler {
    @Override
    public void handle(Command command) {
        if (command instanceof CreateCommand) {
            command.execute();
        } else if (nextHandler != null) {
            nextHandler.handle(command);
        }
    }
}
```

### 2. **Chain of Responsibility + Factory Method**
```java
public abstract class HandlerFactory {
    public abstract Handler createHandler();
    
    public static Handler createHandlerChain() {
        Handler basicHandler = new BasicHandlerFactory().createHandler();
        Handler advancedHandler = new AdvancedHandlerFactory().createHandler();
        
        basicHandler.setNextHandler(advancedHandler);
        return basicHandler;
    }
}
```

### 3. **Chain of Responsibility + Decorator**
```java
public class LoggingHandlerDecorator extends Handler {
    private Handler handler;
    
    public LoggingHandlerDecorator(Handler handler) {
        this.handler = handler;
    }
    
    @Override
    public void handleRequest(Request request) {
        System.out.println("Logging: " + request);
        handler.handleRequest(request);
    }
}
```

---

## 🎯 Xulosa

Chain of Responsibility Pattern:

* ✅ Sender va receiver o'rtasidagi bog'liqlikni kamaytiradi
* ✅ Flexible va kengaytiriladigan arxitekturani ta'minlaydi
* ✅ Conditional logic'ni taqsimlaydi va soddalashtiradi
* ✅ Runtime'da dinamik konfiguratsiya imkonini beradi

**Tavsiya**: Chain of Responsibility pattern middleware, filter pipeline va multi-level approval kabi stsenariylarda juda foydali. Lekin uzun zanjirlar performance'ga ta'sir qilishi mumkin, shuning uchun ehtiyotkorlik bilan ishlatish kerak.