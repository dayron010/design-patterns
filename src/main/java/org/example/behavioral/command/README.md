# ⚡ Command Design Pattern

## 📚 Pattern haqida ma'lumot

> *Command pattern - bu so'rovni obyekt sifatida qadoqlovchi behavioral dizayn namunasi. Bu pattern so'rovlarni parametrlash, navbatga qo'yish, log qilish va undo/redo operatsiyalarini amalga oshirish imkonini beradi.*
> 
> — Gang of Four (GoF)

Command design pattern - bu **behavioral (xulq-atvor)** pattern kategoriyasiga kiradi. Bu pattern so'rovlarni (request) alohida obyektlar sifatida qadoqlab, mijoz va qabul qiluvchi o'rtasidagi bog'liqlikni kamaytiradi. Command pattern "action" yoki "transaction" sifatida ham tanilgan.

[Command Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/command/structure.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Tight coupling muammosi
- Mijoz kodi to'g'ridan-to'g'ri qabul qiluvchi obyekt metodlarini chaqiradi
- Mijoz qabul qiluvchi obyektning strukturasini bilishi kerak
- Kodni o'zgartirish qiyin va xavfli

### 2️⃣ Undo/Redo funksionalligini yo'qligi
- Amalga oshirilgan operatsiyalarni bekor qilish imkonsiz
- Operatsiyalar tarixi saqlanmaydi
- Qayta bajarish (redo) funksionalligini qo'shish murakkab

### 3️⃣ Logging va monitoring yo'qligi
- Operatsiyalarni kuzatish qiyin
- Audit trail yaratish imkonsiz
- Performance monitoring amalga oshirilmaydi

### 4️⃣ Asinxron va navbat boshqaruvi
- Operatsiyalarni kechiktirish imkonsiz
- Navbat tizimini yaratish murakkab
- Parallel operatsiyalarni boshqarish qiyin

### 5️⃣ Macro operatsiyalar
- Bir nechta operatsiyalarni birlashtirib bajarish qiyin
- Transaksiyaviy operatsiyalar yaratish murakkab
- Batch operatsiyalar amalga oshirilmaydi

---

## 💡 Qanday hal qiladi

### ✅ Encapsulation
- Har bir so'rov alohida Command obyektida qadoqlanadi
- Mijoz va qabul qiluvchi o'rtasidagi bog'liqlik ajratiladi
- Operatsiyalar haqidagi barcha ma'lumotlar bitta joyda saqlanadi

### ✅ Undo/Redo mexanizmi
- Har bir command o'zining bekor qilish metodiga ega
- Operatsiyalar tarixi saqlanadi
- Qayta bajarish funksionalligini oson qo'shish mumkin

### ✅ Logging va monitoring
- Har bir command o'zining log metodiga ega bo'lishi mumkin
- Operatsiyalar vaqti va parametrlarini saqlash
- Performance monitoring va debugging osonlashadi

### ✅ Queuing va scheduling
- Commandlarni navbatga qo'yish mumkin
- Asinxron bajarish imkoniyati
- Kechiktirish va scheduling funksionalligini qo'shish

### ✅ Macro va composite operatsiyalar
- Bir nechta commandlarni birlashtirib yangi command yaratish
- Transaksiyaviy operatsiyalar
- Batch processing

---

## 🧩 Implementatsiyasi

Command Pattern beshta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Command** | Barcha commandlar uchun umumiy interfeys |
| **ConcreteCommand** | Aniq operatsiyani amalga oshiruvchi klass |
| **Receiver** | Haqiqiy ishni bajaruvchi obyekt |
| **Invoker** | Commandlarni chaqiruvchi va boshqaruvchi |
| **Client** | ConcreteCommand obyektlarini yaratuvchi |

### 💻 Java implementatsiyasi

```java
// 1. Command interface - barcha commandlar uchun umumiy interfeys
public interface Command {
    void execute();
    void undo();
}

// 2. Receiver - haqiqiy ishni bajaruvchi klass
public class Light {
    private boolean isOn = false;
    
    public void turnOn() {
        isOn = true;
        System.out.println("Light is ON");
    }
    
    public void turnOff() {
        isOn = false;
        System.out.println("Light is OFF");
    }
    
    public boolean isOn() {
        return isOn;
    }
}

// 3. ConcreteCommand - aniq operatsiyani amalga oshiruvchi
public class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOn();
    }
    
    @Override
    public void undo() {
        light.turnOff();
    }
}

public class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOff();
    }
    
    @Override
    public void undo() {
        light.turnOn();
    }
}

// 4. Invoker - commandlarni chaqiruvchi va boshqaruvchi
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command lastCommand;
    
    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        lastCommand = noCommand;
    }
    
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    
    public void onButtonPressed(int slot) {
        onCommands[slot].execute();
        lastCommand = onCommands[slot];
    }
    
    public void offButtonPressed(int slot) {
        offCommands[slot].execute();
        lastCommand = offCommands[slot];
    }
    
    public void undoButtonPressed() {
        lastCommand.undo();
    }
}

// 5. Null Object Pattern - bo'sh command
public class NoCommand implements Command {
    @Override
    public void execute() {
        // Hech narsa qilmaydi
    }
    
    @Override
    public void undo() {
        // Hech narsa qilmaydi
    }
}

// 6. Client - commandlarni yaratuvchi va sozlovchi
public class SmartHomeApp {
    public static void main(String[] args) {
        // Receiver yaratish
        Light livingRoomLight = new Light();
        Light kitchenLight = new Light();
        
        // Command obyektlarini yaratish
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command kitchenLightOff = new LightOffCommand(kitchenLight);
        
        // Invoker yaratish va sozlash
        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, kitchenLightOn, kitchenLightOff);
        
        // Commandlarni bajarish
        remote.onButtonPressed(0);   // Living room light ON
        remote.offButtonPressed(0);  // Living room light OFF
        remote.undoButtonPressed();  // Living room light ON (undo)
        
        remote.onButtonPressed(1);   // Kitchen light ON
        remote.undoButtonPressed();  // Kitchen light OFF (undo)
    }
}
```

---

## 📋 Command Pattern turlari

### 1. **Simple Command**
```java
// Oddiy command - faqat bitta operatsiya
public class SaveCommand implements Command {
    private Document document;
    
    public SaveCommand(Document document) {
        this.document = document;
    }
    
    @Override
    public void execute() {
        document.save();
    }
    
    @Override
    public void undo() {
        document.restore();
    }
}
```

### 2. **Parameterized Command**
```java
// Parametrli command - qo'shimcha ma'lumotlar bilan
public class MoveCommand implements Command {
    private Shape shape;
    private int deltaX, deltaY;
    
    public MoveCommand(Shape shape, int deltaX, int deltaY) {
        this.shape = shape;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
    
    @Override
    public void execute() {
        shape.move(deltaX, deltaY);
    }
    
    @Override
    public void undo() {
        shape.move(-deltaX, -deltaY);
    }
}
```

### 3. **Macro Command**
```java
// Makro command - bir nechta commandlarni birlashtiruvchi
public class MacroCommand implements Command {
    private List<Command> commands;
    
    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }
    
    @Override
    public void execute() {
        commands.forEach(Command::execute);
    }
    
    @Override
    public void undo() {
        // Teskari tartibda undo qilish
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
```

### 4. **Queued Command**
```java
// Navbatli command - asinxron bajarish uchun
public class QueuedCommand implements Command {
    private Command command;
    private ExecutorService executor;
    
    public QueuedCommand(Command command, ExecutorService executor) {
        this.command = command;
        this.executor = executor;
    }
    
    @Override
    public void execute() {
        executor.submit(() -> {
            command.execute();
            System.out.println("Command executed asynchronously");
        });
    }
    
    @Override
    public void undo() {
        executor.submit(() -> {
            command.undo();
            System.out.println("Command undone asynchronously");
        });
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **Text Editor Commands**
```java
// Matn muharriri uchun commandlar
public class TextEditor {
    private StringBuilder text;
    private List<Command> history;
    private int currentPosition;
    
    public TextEditor() {
        this.text = new StringBuilder();
        this.history = new ArrayList<>();
        this.currentPosition = -1;
    }
    
    public void executeCommand(Command command) {
        // Agar undo qilingan commandlar bo'lsa, ularni o'chirish
        if (currentPosition < history.size() - 1) {
            history.subList(currentPosition + 1, history.size()).clear();
        }
        
        command.execute();
        history.add(command);
        currentPosition++;
    }
    
    public void undo() {
        if (currentPosition >= 0) {
            history.get(currentPosition).undo();
            currentPosition--;
        }
    }
    
    public void redo() {
        if (currentPosition < history.size() - 1) {
            currentPosition++;
            history.get(currentPosition).execute();
        }
    }
}

public class TypeCommand implements Command {
    private TextEditor editor;
    private String text;
    private int position;
    
    public TypeCommand(TextEditor editor, String text, int position) {
        this.editor = editor;
        this.text = text;
        this.position = position;
    }
    
    @Override
    public void execute() {
        editor.insertText(text, position);
    }
    
    @Override
    public void undo() {
        editor.deleteText(position, text.length());
    }
}
```

### 2. **Database Transaction Commands**
```java
// Database operatsiyalari uchun commandlar
public interface DatabaseCommand extends Command {
    void rollback();
    boolean isSuccessful();
}

public class InsertCommand implements DatabaseCommand {
    private Database database;
    private String table;
    private Map<String, Object> data;
    private Long insertedId;
    private boolean successful;
    
    public InsertCommand(Database database, String table, Map<String, Object> data) {
        this.database = database;
        this.table = table;
        this.data = data;
    }
    
    @Override
    public void execute() {
        try {
            insertedId = database.insert(table, data);
            successful = true;
        } catch (Exception e) {
            successful = false;
            throw new RuntimeException("Insert failed", e);
        }
    }
    
    @Override
    public void undo() {
        if (successful && insertedId != null) {
            database.delete(table, insertedId);
        }
    }
    
    @Override
    public void rollback() {
        undo();
    }
    
    @Override
    public boolean isSuccessful() {
        return successful;
    }
}
```

### 3. **HTTP Request Commands**
```java
// HTTP so'rovlari uchun commandlar
public class HttpCommand implements Command {
    private HttpClient httpClient;
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;
    private HttpResponse response;
    
    public HttpCommand(HttpClient httpClient, String url, String method) {
        this.httpClient = httpClient;
        this.url = url;
        this.method = method;
        this.headers = new HashMap<>();
    }
    
    @Override
    public void execute() {
        try {
            response = httpClient.sendRequest(url, method, headers, body);
            System.out.println("HTTP " + method + " request sent to " + url);
        } catch (Exception e) {
            System.err.println("HTTP request failed: " + e.getMessage());
        }
    }
    
    @Override
    public void undo() {
        // HTTP so'rovini bekor qilish odatda imkonsiz
        // Lekin kompensatsiya operatsiyasini bajarish mumkin
        System.out.println("Compensating action for HTTP request");
    }
    
    public HttpResponse getResponse() {
        return response;
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Decoupling** | Mijoz va qabul qiluvchi o'rtasidagi bog'liqlik ajratiladi |
| **Undo/Redo** | Operatsiyalarni bekor qilish va qayta bajarish imkoniyati |
| **Logging** | Operatsiyalar tarixini saqlash va kuzatish |
| **Queuing** | Operatsiyalarni navbatga qo'yish va asinxron bajarish |
| **Macro operations** | Bir nechta operatsiyalarni birlashtirib bajarish |
| **Extensibility** | Yangi commandlar qo'shish oson |
| **Reusability** | Commandlarni turli kontekstlarda qayta ishlatish |
| **Testability** | Commandlarni alohida test qilish oson |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Kod hajmi** | Har bir operatsiya uchun alohida klass yaratish kerak |
| **Murakkablik** | Oddiy operatsiyalar uchun ortiqcha abstraksiya |
| **Xotira sarfi** | Command obyektlari xotirada saqlanadi |
| **Performance** | Qo'shimcha abstraktsiya qatlami performance'ga ta'sir qiladi |
| **Undo murakkabligi** | Ba'zi operatsiyalarni bekor qilish qiyin yoki imkonsiz |

---

## 🔄 Command vs boshqa pattern'lar

| Jihat | Command | Strategy | State | Observer |
|-------|---------|----------|-------|----------|
| **Maqsad** | So'rovni qadoqlash | Algoritmni almashtirish | Holat boshqaruvi | Xabar berish |
| **Bog'liqlik** | Invoker-Receiver | Context-Algorithm | Context-State | Subject-Observer |
| **Undo/Redo** | Qo'llab-quvvatlaydi | Qo'llab-quvvatlamaydi | Qo'llab-quvvatlamaydi | Qo'llab-quvvatlamaydi |
| **Parametrlar** | Command ichida | Context orqali | State ichida | Event ma'lumotlari |
| **Lifecycle** | Yaratiladi va saqlanadi | Almashtiriladi | O'zgartiriladi | Subscribe/Unsubscribe |

---

## 📚 JDK-da Command Pattern misollari

### 1. **Runnable interface**
```java
// Runnable - commandning oddiy shakli
Runnable command = () -> System.out.println("Executing command");
Thread thread = new Thread(command);
thread.start();
```

### 2. **Action interface (Swing)**
```java
// Action - GUI componentlar uchun command
Action saveAction = new AbstractAction("Save") {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Save operatsiyasi
    }
};
JButton saveButton = new JButton(saveAction);
```

### 3. **Executor framework**
```java
// ExecutorService - commandlarni bajarish uchun
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.execute(() -> System.out.println("Task executed"));
executor.shutdown();
```

### 4. **CompletableFuture**
```java
// CompletableFuture - asinxron commandlar uchun
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    // Asinxron operatsiya
    return "Result";
});
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Undo/Redo funksionalligini qo'shish kerak
- Operatsiyalarni navbatga qo'yish va scheduling kerak
- Logging va monitoring talab qilinadi
- Macro operatsiyalar yaratish kerak
- Mijoz va qabul qiluvchi o'rtasidagi bog'liqlikni kamaytirish kerak
- Transaksiyaviy operatsiyalar amalga oshirish kerak

### ❌ Ishlatmaslik kerak:
- Oddiy operatsiyalar uchun
- Undo/Redo funksionalligini kerak bo'lmaganda
- Performance kritik bo'lgan joylarda
- Kichik dasturlarda ortiqcha murakkablik yaratganda

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Command + Composite**
```java
// Macro command - Composite pattern bilan
public class CompositeCommand implements Command {
    private List<Command> commands = new ArrayList<>();
    
    public void add(Command command) {
        commands.add(command);
    }
    
    @Override
    public void execute() {
        commands.forEach(Command::execute);
    }
    
    @Override
    public void undo() {
        // Teskari tartibda
        Collections.reverse(commands);
        commands.forEach(Command::undo);
        Collections.reverse(commands);
    }
}
```

### 2. **Command + Factory Method**
```java
// Command factory - turli commandlar yaratish uchun
public abstract class CommandFactory {
    public abstract Command createCommand(String type, Object... params);
}

public class FileCommandFactory extends CommandFactory {
    @Override
    public Command createCommand(String type, Object... params) {
        switch (type) {
            case "save":
                return new SaveCommand((Document) params[0]);
            case "open":
                return new OpenCommand((String) params[0]);
            default:
                throw new IllegalArgumentException("Unknown command type: " + type);
        }
    }
}
```

### 3. **Command + Memento**
```java
// Command va Memento birgalikda - aniq undo uchun
public class EditCommand implements Command {
    private TextEditor editor;
    private String newText;
    private Memento memento;
    
    public EditCommand(TextEditor editor, String newText) {
        this.editor = editor;
        this.newText = newText;
    }
    
    @Override
    public void execute() {
        memento = editor.createMemento(); // Holatni saqlash
        editor.setText(newText);
    }
    
    @Override
    public void undo() {
        editor.restoreMemento(memento); // Holatni qaytarish
    }
}
```

---

## 🎯 Xulosa

Command Pattern:

* ✅ So'rovlarni obyekt sifatida qadoqlab, decoupling ta'minlaydi
* ✅ Undo/Redo, logging, queuing kabi ilg'or funksionalliklarni qo'llab-quvvatlaydi
* ✅ Macro operatsiyalar va transaksiyaviy operatsiyalarni amalga oshirish imkonini beradi
* ✅ Extensible va testable kod yozishga yordam beradi
* ⚠️ Oddiy operatsiyalar uchun ortiqcha murakkablik yaratishi mumkin

**Tavsiya**: Command pattern murakkab operatsiyalar, undo/redo funksionalligini va asinxron operatsiyalar uchun juda foydali. Oddiy funksionallik uchun ishlatmaslik tavsiya etiladi.