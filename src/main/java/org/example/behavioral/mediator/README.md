# 🤝 Mediator Design Pattern

## 📚 Pattern haqida ma'lumot

> *Mediator pattern - bu obyektlar o'rtasidagi murakkab bog'lanishlarni soddalashtirish uchun markaziy vositachi obyekt yaratuvchi behavioral dizayn namunasi.*
> (Obyektlar bir-biri bilan to'g'ridan-to'g'ri gaplashmaydi, balki mediator orqali muloqot qiladi)
> 
> — Gang of Four (GoF)

Mediator design pattern - bu **behavioral** pattern kategoriyasiga kiradi. Bu pattern obyektlar o'rtasidagi murakkab bog'lanishlarni kamaytirish va ularni markaziy vositachi (mediator) orqali boshqarish uchun ishlatiladi. Mediator real hayotdagi dispetcher yoki koordinator kabi ishlaydi.

[Mediator Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/mediator/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Murakkab bog'lanishlar muammosi
- Ko'p obyektlar bir-biri bilan to'g'ridan-to'g'ri bog'langan
- Har bir obyekt boshqa obyektlar haqida bilishi kerak
- Spaghetti kod muammosi (chigal kod)
- Tight coupling (qattiq bog'lanish)

### 2️⃣ Kod qayta ishlatish muammosi
- Obyektlar boshqa obyektlarga bog'liq bo'lgan
- Alohida testlash qiyin
- Komponenlarni qayta ishlatish murakkab

### 3️⃣ Kod o'zgarishi muammosi
- Bir obyektni o'zgartirish boshqa obyektlarni buzishi mumkin
- Yangi obyekt qo'shish murakkab
- Kodni saqlab qolish qiyin

### 4️⃣ Javobgarlik taqsimlanishi
- Biznes logikasi obyektlar o'rtasida tarqalgan
- Muloqot mantiqi har yerda
- Kodning o'qilishi qiyin

---

## 💡 Qanday hal qiladi

### ✅ Markaziy vositachi
- Barcha obyektlar bitta mediator orqali muloqot qiladi
- Obyektlar bir-biri haqida bilmasligi kerak
- Mediator barcha muloqotni boshqaradi

### ✅ Loose coupling
- Obyektlar o'rtasidagi bog'liqlikni kamaytiradi
- Har bir obyekt faqat mediator bilan bog'langan
- Dependency Inversion Principle'ni qo'llab-quvvatlaydi

### ✅ Markaziy boshqaruv
- Barcha biznes logikasi bir joyda
- Kod strukturasi aniq va tushunarli
- Oson saqlab qolish va o'zgartirish

### ✅ Kengaytirilishi
- Yangi obyektlar qo'shish oson
- Mavjud kodlarni o'zgartirmasdan funksionallik kengaytirish
- Open/Closed Principle'ni qo'llab-quvvatlaydi

---

## 🧩 Implementatsiyasi

Mediator Pattern beshta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Mediator** | Vositachi interfeysi |
| **ConcreteMediator** | Konkret vositachi implementatsiyasi |
| **Colleague** | Ishtirokchi obyektlar interfeysi |
| **ConcreteColleague** | Konkret ishtirokchi obyektlar |
| **Client** | Mediator va obyektlarni ishlatuvchi kod |

### 💻 Java implementatsiyasi

```java
// 1. Mediator interface - vositachi interfeysi
public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// 2. Abstract Colleague - ishtirokchi obyektlar bazasi
public abstract class User {
    protected ChatMediator mediator;
    protected String name;
    
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    
    public abstract void send(String message);
    public abstract void receive(String message);
}

// 3. ConcreteColleague - konkret foydalanuvchi
public class ConcreteUser extends User {
    
    public ConcreteUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }
    
    @Override
    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}

// 4. ConcreteMediator - konkret vositachi
public class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();
    
    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println(user.name + " joined the chat");
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}

// 5. Demo
public class MediatorPatternDemo {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();
        
        User alice = new ConcreteUser(chatRoom, "Alice");
        User bob = new ConcreteUser(chatRoom, "Bob");
        User charlie = new ConcreteUser(chatRoom, "Charlie");
        
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);
        
        alice.send("Hello everyone!");
        bob.send("Hi Alice!");
        charlie.send("Hey guys!");
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **Air Traffic Control System**
```java
// Havo harakati nazorati tizimi
public interface AirTrafficControlTower {
    void requestTakeoff(Aircraft aircraft);
    void requestLanding(Aircraft aircraft);
    void sendWeatherUpdate(String weather);
}

public class ConcreteAirTrafficControlTower implements AirTrafficControlTower {
    private List<Aircraft> aircrafts = new ArrayList<>();
    private boolean runwayBusy = false;
    
    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }
    
    @Override
    public void requestTakeoff(Aircraft aircraft) {
        if (!runwayBusy) {
            runwayBusy = true;
            System.out.println("Control Tower: " + aircraft.getName() + 
                             " cleared for takeoff");
            aircraft.takeoff();
        } else {
            System.out.println("Control Tower: " + aircraft.getName() + 
                             " wait for takeoff");
        }
    }
    
    @Override
    public void requestLanding(Aircraft aircraft) {
        if (!runwayBusy) {
            runwayBusy = true;
            System.out.println("Control Tower: " + aircraft.getName() + 
                             " cleared for landing");
            aircraft.land();
        } else {
            System.out.println("Control Tower: " + aircraft.getName() + 
                             " wait for landing");
        }
    }
    
    @Override
    public void sendWeatherUpdate(String weather) {
        System.out.println("Control Tower: Weather update - " + weather);
        for (Aircraft aircraft : aircrafts) {
            aircraft.receiveWeatherUpdate(weather);
        }
    }
    
    public void runwayFree() {
        runwayBusy = false;
        System.out.println("Control Tower: Runway is now free");
    }
}

public abstract class Aircraft {
    protected AirTrafficControlTower controlTower;
    protected String name;
    
    public Aircraft(AirTrafficControlTower controlTower, String name) {
        this.controlTower = controlTower;
        this.name = name;
    }
    
    public abstract void takeoff();
    public abstract void land();
    public abstract void receiveWeatherUpdate(String weather);
    
    public String getName() {
        return name;
    }
}
```

### 2. **GUI Dialog System**
```java
// GUI dialog tizimi
public interface DialogMediator {
    void notify(Component sender, String event);
}

public class AuthenticationDialog implements DialogMediator {
    private Button loginButton;
    private Button registerButton;
    private TextField usernameField;
    private TextField passwordField;
    private CheckBox rememberMe;
    
    public AuthenticationDialog() {
        loginButton = new Button(this, "Login");
        registerButton = new Button(this, "Register");
        usernameField = new TextField(this, "Username");
        passwordField = new TextField(this, "Password");
        rememberMe = new CheckBox(this, "Remember Me");
    }
    
    @Override
    public void notify(Component sender, String event) {
        if (sender == loginButton && event.equals("click")) {
            if (usernameField.getText().isEmpty() || 
                passwordField.getText().isEmpty()) {
                showError("Please fill all fields");
            } else {
                authenticate();
            }
        } else if (sender == registerButton && event.equals("click")) {
            openRegistrationForm();
        } else if (sender == usernameField && event.equals("keypress")) {
            validateUsername();
        }
    }
    
    private void authenticate() {
        System.out.println("Authenticating user...");
        // Authentication logic
    }
    
    private void showError(String message) {
        System.out.println("Error: " + message);
    }
    
    private void openRegistrationForm() {
        System.out.println("Opening registration form...");
    }
    
    private void validateUsername() {
        System.out.println("Validating username...");
    }
}
```

### 3. **Smart Home System**
```java
// Aqlli uy tizimi
public interface SmartHomeMediator {
    void deviceStateChanged(SmartDevice device, String state);
    void executeScene(String sceneName);
}

public class SmartHomeController implements SmartHomeMediator {
    private List<SmartDevice> devices = new ArrayList<>();
    
    public void addDevice(SmartDevice device) {
        devices.add(device);
    }
    
    @Override
    public void deviceStateChanged(SmartDevice device, String state) {
        System.out.println(device.getName() + " state changed to: " + state);
        
        // Boshqa qurilmalarni avtomatik boshqarish
        if (device.getName().equals("Motion Sensor") && state.equals("detected")) {
            // Harakat aniqlanganda yorug'likni yoqish
            for (SmartDevice d : devices) {
                if (d.getName().equals("Living Room Light")) {
                    d.setState("on");
                }
            }
        }
    }
    
    @Override
    public void executeScene(String sceneName) {
        System.out.println("Executing scene: " + sceneName);
        
        switch (sceneName) {
            case "Movie Night":
                setDeviceState("TV", "on");
                setDeviceState("Living Room Light", "dim");
                setDeviceState("AC", "cooling");
                break;
            case "Sleep Mode":
                setDeviceState("TV", "off");
                setDeviceState("All Lights", "off");
                setDeviceState("Security System", "armed");
                break;
            case "Away Mode":
                setDeviceState("All Lights", "off");
                setDeviceState("AC", "off");
                setDeviceState("Security System", "armed");
                break;
        }
    }
    
    private void setDeviceState(String deviceName, String state) {
        for (SmartDevice device : devices) {
            if (device.getName().equals(deviceName)) {
                device.setState(state);
                break;
            }
        }
    }
}

public abstract class SmartDevice {
    protected SmartHomeMediator mediator;
    protected String name;
    protected String state;
    
    public SmartDevice(SmartHomeMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    
    public abstract void setState(String state);
    
    public String getName() {
        return name;
    }
    
    public String getState() {
        return state;
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Loose coupling** | Obyektlar o'rtasidagi bog'liqlikni kamaytiradi |
| **Markaziy boshqaruv** | Barcha muloqot mantiqi bir joyda |
| **Kod qayta ishlatish** | Komponenlarni alohida ishlatish mumkin |
| **Oson testlash** | Har bir komponent alohida test qilinadi |
| **Kengaytirilishi** | Yangi ishtirokchilar qo'shish oson |
| **Single Responsibility** | Har bir klass bitta javobgarlikka ega |
| **Saqlab qolish** | Kod strukturasi aniq va tushunarli |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Mediator murakkabligi** | Mediator juda murakkab bo'lishi mumkin |
| **Markaziy nuqta** | Mediator single point of failure bo'lishi mumkin |
| **Performance** | Qo'shimcha abstraktsiya qatlami |
| **God Object** | Mediator juda katta bo'lishi xavfi |
| **Debugging** | Muloqot izini kuzatish qiyin |
| **Xotira sarfi** | Qo'shimcha obyekt va reference'lar |

---

## 🔄 Mediator vs boshqa pattern'lar

| Jihat | Mediator | Observer | Command | Facade |
|-------|----------|----------|---------|---------|
| **Maqsad** | Muloqot boshqaruvi | Voqea xabar berish | Operatsiya enkapsulatsiyasi | Interfeys soddalashtirish |
| **Bog'lanish** | Many-to-many | One-to-many | One-to-one | One-to-many |
| **Markazlashtirish** | Markaziy mediator | Tarqalgan | Markaziy invoker | Markaziy facade |
| **Dinamik** | Dinamik muloqot | Dinamik subscription | Statik command | Statik interfeys |

---

## 📚 Java framework'larda Mediator Pattern

### 1. **Spring Framework - ApplicationEventPublisher**
```java
@Component
public class OrderService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    public void createOrder(Order order) {
        // Order yaratish
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
    }
}

@EventListener
public void handleOrderCreated(OrderCreatedEvent event) {
    // Order yaratilgandan keyin ishlar
    emailService.sendConfirmation(event.getOrder());
    inventoryService.updateStock(event.getOrder());
}
```

### 2. **Java Swing - ActionListener**
```java
public class Calculator extends JFrame {
    private JTextField display;
    private JButton[] buttons;
    
    public Calculator() {
        // GUI yaratish
        ActionListener buttonListener = e -> {
            JButton source = (JButton) e.getSource();
            String command = source.getText();
            handleButtonClick(command);
        };
        
        // Barcha tugmalar bir xil listener ishlatadi
        for (JButton button : buttons) {
            button.addActionListener(buttonListener);
        }
    }
    
    private void handleButtonClick(String command) {
        // Markaziy command handler
        switch (command) {
            case "+": performAddition(); break;
            case "-": performSubtraction(); break;
            case "=": calculateResult(); break;
            default: appendToDisplay(command);
        }
    }
}
```

### 3. **JavaFX - EventHandler**
```java
public class MediaPlayer extends Application {
    @Override
    public void start(Stage stage) {
        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        Button stopButton = new Button("Stop");
        
        MediaController controller = new MediaController();
        
        playButton.setOnAction(e -> controller.handleMediaEvent("play"));
        pauseButton.setOnAction(e -> controller.handleMediaEvent("pause"));
        stopButton.setOnAction(e -> controller.handleMediaEvent("stop"));
    }
}
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Ko'p obyektlar o'rtasida murakkab muloqot bor
- Obyektlar bir-biri bilan qattiq bog'langan
- Biznes logikasi obyektlar o'rtasida tarqalgan
- GUI komponentlari o'rtasida muloqot kerak
- Workflow va process boshqaruvi
- Chat tizimi, oyinlar, kontrollerlar

### ❌ Ishlatmaslik kerak:
- Oddiy obyektlar o'rtasidagi muloqot
- Faqat ikkita obyekt o'rtasida muloqot
- Performance kritik joylar
- Mediator juda murakkab bo'lsa
- Qo'shimcha abstraktsiya kerak bo'lmasa

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Mediator + Observer**
```java
public class ChatRoomMediator implements ChatMediator {
    private List<ChatObserver> observers = new ArrayList<>();
    
    public void addObserver(ChatObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        // Xabar yuborish
        for (User user : users) {
            if (user != sender) {
                user.receive(message);
            }
        }
        
        // Observer'larni xabardor qilish
        for (ChatObserver observer : observers) {
            observer.onMessageSent(message, sender);
        }
    }
}
```

### 2. **Mediator + Command**
```java
public class SmartHomeMediator {
    private Map<String, Command> sceneCommands = new HashMap<>();
    
    public void registerScene(String sceneName, Command command) {
        sceneCommands.put(sceneName, command);
    }
    
    @Override
    public void executeScene(String sceneName) {
        Command command = sceneCommands.get(sceneName);
        if (command != null) {
            command.execute();
        }
    }
}
```

### 3. **Mediator + State**
```java
public class DialogMediator {
    private DialogState currentState;
    
    public void setState(DialogState state) {
        this.currentState = state;
    }
    
    @Override
    public void notify(Component sender, String event) {
        currentState.handle(this, sender, event);
    }
}
```

---

## 📈 Best Practices

### 1. **Mediator'ni kichik saqlash**
```java
// Yomon - juda katta mediator
public class GodMediator {
    // 1000+ qator kod
}

// Yaxshi - kichik, yo'naltirilgan mediator
public class ChatMediator {
    // Faqat chat bilan bog'liq logika
}
```

### 2. **Interface'dan foydalanish**
```java
// Mediator interface yaratish
public interface EventMediator {
    void notify(Component sender, String event);
}

// Turli implementatsiyalar
public class DialogMediator implements EventMediator { ... }
public class GameMediator implements EventMediator { ... }
```

### 3. **Lazy initialization**
```java
public class SmartHomeMediator {
    private Map<String, SmartDevice> devices;
    
    public Map<String, SmartDevice> getDevices() {
        if (devices == null) {
            devices = new HashMap<>();
        }
        return devices;
    }
}
```

---

## 🎯 Xulosa

Mediator Pattern:

* ✅ Obyektlar o'rtasidagi murakkab bog'lanishlarni soddalashtiradi
* ✅ Loose coupling va markaziy boshqaruvni ta'minlaydi
* ✅ Kod qayta ishlatish va testlashni osonlashtiradi
* ✅ Yangi funksionallik qo'shishni sodda qiladi
* ⚠️ Mediator juda murakkab bo'lmasligi kerak
* ⚠️ God Object anti-pattern'dan qochish kerak

**Tavsiya**: Mediator pattern murakkab muloqot tizimlarini boshqarish uchun juda foydali, lekin mediator obyektini oddiy va yo'naltirilgan saqlash muhim. Har bir mediator aniq bir vazifani bajarishi kerak.