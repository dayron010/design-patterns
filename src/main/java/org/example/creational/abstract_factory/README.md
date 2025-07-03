# 🏭 Abstract Factory Design Pattern

## 📚 Pattern haqida ma'lumot

> *Abstract Factory bir-biriga bog'liq yoki birgalikda ishlatilishi kerak bo'lgan obyektlar oilasini ularning konkret turlarini ko'rsatmasdan yaratishga imkon beradi.*
> 
> — Gang of Four (GoF)

Abstract Factory design pattern - bu **yaratish (creational)** pattern kategoriyasiga kiradi va Factory Method patternining kengaytirilgan versiyasi hisoblanadi. Bu pattern bir necha o'zaro bog'liq obyektlar oilasini yaratish uchun ishlatiladi va bitta interfeys orqali turli xil "oila" obyektlarini yaratishga imkon beradi.

[Abstract Factory strukturasi](https://refactoring.guru/images/patterns/diagrams/abstract-factory/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Bog'liq obyektlar oilasini yaratish
- Bir-biriga bog'liq bo'lgan obyektlar guruhini yaratish kerak bo'lganda
- Obyektlar orasida muvofiqlik (consistency) ta'minlash zarur bo'lganda
- Misol: GUI komponentlari - har bir platforma uchun button, checkbox va menular birgalikda ishlatilishi kerak

### 2️⃣ Platformaga bog'liq bo'lgan implementatsiyalar
- Turli platformalar uchun bir xil interfeys, lekin turli implementatsiyalar kerak bo'lganda
- Misol: Windows, macOS, Linux uchun alohida GUI elementlari

### 3️⃣ Konfiguratsiya bo'yicha obyekt yaratish
- Tizim sozlamalariga qarab turli obyektlar oilasini yaratish kerak bo'lganda
- Runtime vaqtida qaysi oila obyektlarini yaratishni tanlash imkoniyati

---

## 💡 Qanday hal qiladi

### ✅ Oila obyektlarini bir joyda yaratish
- Bir factory orqali bir nechta o'zaro bog'liq obyektlarni yaratish
- Obyektlar orasida muvofiqlikni ta'minlash

### ✅ Abstraksiya qatlami yaratish
- Client kod konkret factory klasslar bilan emas, abstrakt interfeys bilan ishlaydi
- Konkret implementatsiyalarni client koddan ajratish

### ✅ Tizimni kengaytirishga tayyorlash
- Yangi oila obyektlarini qo'shish uchun yangi factory yaratish kifoya
- Mavjud client kod o'zgarishsiz qoladi

---

## 🧩 Implementatsiyasi

Abstract Factory patterni beshta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Abstract Factory** | Obyektlar oilasini yaratish uchun methodlar e'lon qiladigan interfeys |
| **Concrete Factory** | Abstract Factory interfeysini implementatsiya qiluvchi va aniq obyektlar oilasini yaratadigan klasslar |
| **Abstract Product** | Yaratilgan obyektlar uchun umumiy interfeys |
| **Concrete Product** | Abstract Product interfeysini amalga oshiruvchi aniq klasslar |
| **Client** | Abstract Factory va Abstract Product interfeyslari bilan ishlovchi klass |

### 💻 Java implementatsiyasi

```java
// 1. Abstract Products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

interface Menu {
    void render();
}

// 2. Concrete Products for Windows
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows style button");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Windows style checkbox");
    }
}

class WindowsMenu implements Menu {
    @Override
    public void render() {
        System.out.println("Rendering Windows style menu");
    }
}

// 3. Concrete Products for macOS
class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering macOS style button");
    }
}

class MacOSCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering macOS style checkbox");
    }
}

class MacOSMenu implements Menu {
    @Override
    public void render() {
        System.out.println("Rendering macOS style menu");
    }
}

// 4. Concrete Products for Linux
class LinuxButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Linux style button");
    }
}

class LinuxCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Linux style checkbox");
    }
}

class LinuxMenu implements Menu {
    @Override
    public void render() {
        System.out.println("Rendering Linux style menu");
    }
}

// 5. Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
    Menu createMenu();
}

// 6. Concrete Factories
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }

    @Override
    public Menu createMenu() {
        return new WindowsMenu();
    }
}

class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }

    @Override
    public Menu createMenu() {
        return new MacOSMenu();
    }
}

class LinuxFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LinuxCheckbox();
    }

    @Override
    public Menu createMenu() {
        return new LinuxMenu();
    }
}

// 7. Client Code
class GUIApplication {
    private Button button;
    private Checkbox checkbox;
    private Menu menu;

    public GUIApplication(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
        menu = factory.createMenu();
    }

    public void render() {
        button.render();
        checkbox.render();
        menu.render();
    }
}

// 8. Configuration and Usage
public class AbstractFactoryExample {
    public static void main(String[] args) {
        // Platform detection (simplified)
        String osName = System.getProperty("os.name").toLowerCase();
        
        GUIFactory factory;
        
        if (osName.contains("windows")) {
            factory = new WindowsFactory();
            System.out.println("Using Windows GUI Factory:");
        } else if (osName.contains("mac")) {
            factory = new MacOSFactory();
            System.out.println("Using macOS GUI Factory:");
        } else {
            factory = new LinuxFactory();
            System.out.println("Using Linux GUI Factory:");
        }
        
        GUIApplication app = new GUIApplication(factory);
        app.render();
        
        // Demonstrating different factories
        System.out.println("\n--- Demonstrating all factories ---");
        
        System.out.println("\nWindows GUI:");
        GUIApplication windowsApp = new GUIApplication(new WindowsFactory());
        windowsApp.render();
        
        System.out.println("\nmacOS GUI:");
        GUIApplication macApp = new GUIApplication(new MacOSFactory());
        macApp.render();
        
        System.out.println("\nLinux GUI:");
        GUIApplication linuxApp = new GUIApplication(new LinuxFactory());
        linuxApp.render();
    }
}
```

---

## 🆚 Factory Method vs Abstract Factory

| Aspect | Factory Method | Abstract Factory |
|--------|----------------|------------------|
| **Maqsad** | Bitta obyekt yaratish | Bir nechta bog'liq obyektlar oilasini yaratish |
| **Murakkablik** | Oddiy | Murakkab |
| **Obyektlar soni** | Bitta | Ko'p |
| **Hierarchiya** | Bitta Product hierarchiyasi | Ko'p Product hierarchiyalari |
| **Kengayish** | Yangi Product qo'shish | Yangi Product oilasi qo'shish |
| **Foydalanish** | Oddiy obyekt yaratish | Platformaga bog'liq obyektlar |

---

## ✅ Qulaylik tomonlari

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Obyektlar orasida muvofiqlik** | Bir oila obyektlari birga ishlatilishini ta'minlaydi. Platformaga mos elementlar yaratiladi. |
| **Yopiq/Ochiq printsipga muvofiqlik** | Yangi oila obyektlarini qo'shish mavjud kodga ta'sir qilmaydi. Kengaytirishga ochiq, o'zgartirishga yopiq. |
| **Bog'liqliklarni kamaytirish** | Client kod konkret klasslar bilan emas, interfeys bilan ishlaydi. Loose coupling ta'minlanadi. |
| **Kod qayta ishlatilishi** | Bir xil obyekt oilasini turli joylarda ishlatish mumkin. DRY (Don't Repeat Yourself) printsipiga mos keladi. |
| **Tizim konfiguratsiyasi** | Runtime vaqtida tizim konfiguratsiyasini o'zgartirish mumkin. Moslashuvchan arxitektura yaratish imkoniyati. |

---

## ⚠️ Kamchilik tomonlari

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Kod murakkabligi ortishi** | Ko'p interfeys va klasslar yaratiladi. Kichik loyihalarda ortiqcha bo'lishi mumkin. |
| **Yangi obyekt qo'shish qiyinligi** | Yangi obyekt turi qo'shish uchun barcha factory klasslarni o'zgartirish kerak. Interfeys o'zgarishi barcha implementatsiyalarni ta'sirlayd. |
| **Abstraksiya qatlamlarining ko'payishi** | Ko'p abstraksiya qatlamlari tushunishni qiyinlashtirishi mumkin. Debugging murakkablashishi mumkin. |
| **Overengineering xavfi** | Oddiy vazifalar uchun ortiqcha murakkablik yaratishi mumkin. Yechim muammosidan murakkab bo'lishi mumkin. |

---

## 🎮 Real hayotdagi misollar

### 1. GUI Framework misolida

```java
// GUI framework uchun Abstract Factory
interface ThemeFactory {
    Button createButton();
    TextField createTextField();
    Dialog createDialog();
}

class DarkThemeFactory implements ThemeFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }
    
    @Override
    public TextField createTextField() {
        return new DarkTextField();
    }
    
    @Override
    public Dialog createDialog() {
        return new DarkDialog();
    }
}

class LightThemeFactory implements ThemeFactory {
    @Override
    public Button createButton() {
        return new LightButton();
    }
    
    @Override
    public TextField createTextField() {
        return new LightTextField();
    }
    
    @Override
    public Dialog createDialog() {
        return new LightDialog();
    }
}
```

### 2. Database Connection misolida

```java
// Database connection uchun Abstract Factory
interface DatabaseFactory {
    Connection createConnection();
    Statement createStatement();
    ResultSet createResultSet();
}

class MySQLFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() {
        return new MySQLConnection();
    }
    
    @Override
    public Statement createStatement() {
        return new MySQLStatement();
    }
    
    @Override
    public ResultSet createResultSet() {
        return new MySQLResultSet();
    }
}

class PostgreSQLFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() {
        return new PostgreSQLConnection();
    }
    
    @Override
    public Statement createStatement() {
        return new PostgreSQLStatement();
    }
    
    @Override
    public ResultSet createResultSet() {
        return new PostgreSQLResultSet();
    }
}
```

---

## 📚 JDK-da Abstract Factory patterni misollari

Java Development Kit (JDK) da Abstract Factory patterni misollari:

### 1. `javax.xml.parsers.DocumentBuilderFactory`
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document document = builder.newDocument();
```
> DocumentBuilderFactory XML parsing uchun bog'liq obyektlar oilasini yaratadi.

### 2. `javax.xml.transform.TransformerFactory`
```java
TransformerFactory factory = TransformerFactory.newInstance();
Transformer transformer = factory.newTransformer();
```
> TransformerFactory XML transformation uchun bog'liq obyektlar yaratadi.

### 3. `java.sql.DriverManager`
```java
Connection connection = DriverManager.getConnection(url);
Statement statement = connection.createStatement();
PreparedStatement preparedStatement = connection.prepareStatement(sql);
```
> Database connection orqali bog'liq obyektlar oilasi yaratiladi.

### 4. `java.awt.Toolkit`
```java
Toolkit toolkit = Toolkit.getDefaultToolkit();
Image image = toolkit.getImage("image.png");
PrintJob printJob = toolkit.getPrintJob(frame, "Print", props);
```
> Platform-specific GUI komponentlari yaratish uchun ishlatiladi.

### 5. `javax.swing.UIManager`
```java
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
```
> Look and Feel ga mos GUI komponentlari yaratish uchun ishlatiladi.

---

## 🎯 Qachon ishlatish kerak?

### ✅ Ishlatish tavsiya etiladi:

- **Bir nechta bog'liq obyektlar** oilasi bilan ishlashda
- **Platformaga bog'liq** implementatsiyalar kerak bo'lganda
- **Kod muvofiqligini** ta'minlash zarur bo'lganda
- **Konfiguratsiya bo'yicha** obyekt yaratish kerak bo'lganda
- **Tizimni kengaytirishga** tayyorlash kerak bo'lganda

### ❌ Ishlatish tavsiya etilmaydi:

- **Oddiy obyekt yaratish** uchun (Factory Method yetarli)
- **Faqat bitta obyekt** turi bilan ishlashda
- **Kichik loyihalarda** (overengineering)
- **Tez-tez o'zgaradigan** obyekt turlari bilan ishlashda

---

## 🔄 Boshqa patternlar bilan bog'liqlik

### 🤝 Birga ishlatiladi:
- **Singleton**: Factory obyektlarini Singleton sifatida yaratish
- **Factory Method**: Abstract Factory ichida Factory Method ishlatish
- **Builder**: Murakkab obyektlar yaratishda birga ishlatish

### 🔄 Muqobil patternlar:
- **Factory Method**: Oddiy obyekt yaratish uchun
- **Builder**: Murakkab obyektlar yaratish uchun
- **Prototype**: Obyektlarni klonlash uchun

---

## 📋 Implementatsiya qo'llanmasi

### 1. **Abstract Factory interfeysini yarating**
```java
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}
```

### 2. **Concrete Factory klasslarini yarating**
```java
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }
    
    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}
```

### 3. **Client kodini abstract factory bilan ishlating**
```java
class Client {
    private AbstractFactory factory;
    
    public Client(AbstractFactory factory) {
        this.factory = factory;
    }
    
    public void doSomething() {
        ProductA productA = factory.createProductA();
        ProductB productB = factory.createProductB();
        // Product objects bilan ishlash
    }
}
```

---

## 🎯 Xulosa

Abstract Factory patterni:

* ✅ Bir-biriga bog'liq obyektlar oilasini yaratishni osonlashtiradi
* ✅ Platformaga bog'liq implementatsiyalarni boshqarishga yordam beradi
* ✅ Kod muvofiqligini ta'minlaydi va kengaytirishga tayyorlaydi
* ✅ Object-oriented design printsiplariga mos keladi

**Qo'llash tavsiya etiladi**: Katta tizimlarda, ayniqsa turli platformalar yoki konfiguratsiyalar uchun obyektlar oilasini yaratish kerak bo'lganda, Abstract Factory patterni juda foydali va samarali yechim hisoblanadi.

---

## 📚 Qo'shimcha resurslar

- [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
- [Head First Design Patterns](https://www.amazon.com/Head-First-Design-Patterns-Brain-Friendly/dp/0596007124)
- [Refactoring Guru - Abstract Factory](https://refactoring.guru/design-patterns/abstract-factory)

---

*© 2024 - Design Patterns Learning Guide*