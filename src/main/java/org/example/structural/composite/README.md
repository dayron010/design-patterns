# 🌳 Composite Design Pattern

## 📚 Pattern haqida ma'lumot

> *Composite pattern - bu obyektlarni daraxt strukturasida tashkil etib, individual obyektlar va obyektlar kompozitsiyasini bir xil tarzda ishlatish imkonini beruvchi strukturaviy dizayn namunasi.*
> 
> — Gang of Four (GoF)

Composite design pattern - bu **strukturaviy (structural)** pattern kategoriyasiga kiradi. Bu pattern "part-whole" ierarxiyasini ifodalaydi va mijozlarga individual obyektlar bilan kompozit obyektlarni bir xil tarzda ishlatish imkonini beradi. Composite pattern daraxt strukturasidagi ma'lumotlarni boshqarish uchun eng mos keladi.

[Composite Pattern strukturasi](https://refactoring.guru/images/patterns/diagrams/composite/structure-en.png)

---

## 🎯 Qanday muammoni hal qiladi

### 1️⃣ Ierarxik struktura muammosi
- Obyektlar daraxt strukturasida tashkil etilgan
- Leaf va composite obyektlarni turli xil tarzda ishlatish kerak
- Rekursiv strukturalar bilan ishlash murakkab

### 2️⃣ Uniform interface muammosi
- Oddiy obyektlar va kompozit obyektlar uchun turli interfeys
- Mijoz kodi obyekt turini tekshirib turishi kerak
- Kod takrorlanishi va murakkablashishi

### 3️⃣ Operatsiyalarni butun daraxt bo'yicha bajarish
- Barcha elementlar bo'yicha bir xil operatsiya bajarish kerak
- Rekursiv traversal qiyin va xatoga moyil
- Performance muammolari

### 4️⃣ Dinamik struktura boshqaruvi
- Runtime'da daraxt strukturasini o'zgartirish kerak
- Yangi elementlar qo'shish va olib tashlash
- Strukturaning butunligini saqlash

---

## 💡 Qanday hal qiladi

### ✅ Uniform interface
- Leaf va composite obyektlar uchun bir xil interfeys
- Mijoz kodi obyekt turini bilishi shart emas
- Polymorphism orqali bir xil tarzda ishlatish

### ✅ Rekursiv kompozitsiya
- Composite obyektlar boshqa composite obyektlarni o'z ichiga olishi mumkin
- Cheksiz chuqur daraxt strukturalari
- Rekursiv operatsiyalar avtomatik bajariladi

### ✅ Oson kengaytirish
- Yangi leaf yoki composite turlarini qo'shish oson
- Mavjud kodlarni o'zgartirmasdan yangi funksionallik
- Open/Closed Principle'ni qo'llab-quvvatlaydi

### ✅ Transparent composition
- Mijoz kodi uchun leaf va composite obyektlar bir xil ko'rinadi
- Kod soddalashadi va takrorlanish kamayadi
- Xatoliklar ehtimoli pasayadi

---

## 🧩 Implementatsiyasi

Composite Pattern uchta asosiy komponentdan tashkil topadi:

| Komponent | Tavsifi |
|-----------|---------|
| **Component** | Leaf va composite obyektlar uchun umumiy interfeys |
| **Leaf** | Daraxtning yakuniy tugunlari (child'lari yo'q) |
| **Composite** | Child'larga ega bo'lgan tugunlar (container) |

### 💻 Java implementatsiyasi

```java
// 1. Component - umumiy interfeys
public abstract class FileSystemComponent {
    protected String name;
    
    public FileSystemComponent(String name) {
        this.name = name;
    }
    
    public abstract void showDetails();
    public abstract long getSize();
    
    // Composite obyektlar uchun default implementatsiya
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }
    
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }
    
    public FileSystemComponent getChild(int index) {
        throw new UnsupportedOperationException("Cannot get child from a leaf");
    }
}

// 2. Leaf - yakuniy obyekt
public class File extends FileSystemComponent {
    private long size;
    
    public File(String name, long size) {
        super(name);
        this.size = size;
    }
    
    @Override
    public void showDetails() {
        System.out.println("File: " + name + " (Size: " + size + " bytes)");
    }
    
    @Override
    public long getSize() {
        return size;
    }
}

// 3. Composite - container obyekt
public class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();
    
    public Directory(String name) {
        super(name);
    }
    
    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }
    
    @Override
    public FileSystemComponent getChild(int index) {
        return children.get(index);
    }
    
    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent child : children) {
            child.showDetails();
        }
    }
    
    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }
}

// 4. Client - foydalanuvchi kodi
public class FileSystemDemo {
    public static void main(String[] args) {
        // Leaf obyektlar yaratish
        File file1 = new File("document.txt", 1024);
        File file2 = new File("image.jpg", 2048);
        File file3 = new File("video.mp4", 5120);
        
        // Composite obyektlar yaratish
        Directory documents = new Directory("Documents");
        Directory media = new Directory("Media");
        Directory root = new Directory("Root");
        
        // Daraxt strukturasini qurish
        documents.add(file1);
        media.add(file2);
        media.add(file3);
        
        root.add(documents);
        root.add(media);
        
        // Uniform interface orqali ishlatish
        System.out.println("File system structure:");
        root.showDetails();
        
        System.out.println("\nTotal size: " + root.getSize() + " bytes");
    }
}
```

---

## 📋 Composite Pattern turlari

### 1. **Safety Composite**
```java
// Leaf va composite uchun alohida interfeys
public interface Component {
    void operation();
}

public interface Composite extends Component {
    void add(Component component);
    void remove(Component component);
    Component getChild(int index);
}

public class Leaf implements Component {
    @Override
    public void operation() {
        System.out.println("Leaf operation");
    }
}

public class CompositeImpl implements Composite {
    private List<Component> children = new ArrayList<>();
    
    @Override
    public void add(Component component) {
        children.add(component);
    }
    
    @Override
    public void remove(Component component) {
        children.remove(component);
    }
    
    @Override
    public Component getChild(int index) {
        return children.get(index);
    }
    
    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }
}
```

### 2. **Transparent Composite**
```java
// Barcha metodlar umumiy interfeys orqali
public abstract class Component {
    public abstract void operation();
    
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }
    
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }
    
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }
}
```

### 3. **Iterator Composite**
```java
// Iterator pattern bilan birgalikda
public class CompositeIterator implements Iterator<Component> {
    private Stack<Iterator<Component>> stack = new Stack<>();
    
    public CompositeIterator(Iterator<Component> iterator) {
        stack.push(iterator);
    }
    
    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        
        Iterator<Component> iterator = stack.peek();
        if (!iterator.hasNext()) {
            stack.pop();
            return hasNext();
        }
        
        return true;
    }
    
    @Override
    public Component next() {
        if (hasNext()) {
            Iterator<Component> iterator = stack.peek();
            Component component = iterator.next();
            
            if (component instanceof Composite) {
                stack.push(((Composite) component).createIterator());
            }
            
            return component;
        }
        
        return null;
    }
}
```

---

## 🌐 Real hayot misollari

### 1. **GUI komponentlar**
```java
// GUI elementlar uchun composite
public abstract class GUIComponent {
    protected String name;
    
    public GUIComponent(String name) {
        this.name = name;
    }
    
    public abstract void render();
    public abstract void handleEvent(Event event);
}

public class Button extends GUIComponent {
    public Button(String name) {
        super(name);
    }
    
    @Override
    public void render() {
        System.out.println("Rendering button: " + name);
    }
    
    @Override
    public void handleEvent(Event event) {
        System.out.println("Button " + name + " handling event: " + event);
    }
}

public class Panel extends GUIComponent {
    private List<GUIComponent> components = new ArrayList<>();
    
    public Panel(String name) {
        super(name);
    }
    
    public void add(GUIComponent component) {
        components.add(component);
    }
    
    public void remove(GUIComponent component) {
        components.remove(component);
    }
    
    @Override
    public void render() {
        System.out.println("Rendering panel: " + name);
        for (GUIComponent component : components) {
            component.render();
        }
    }
    
    @Override
    public void handleEvent(Event event) {
        System.out.println("Panel " + name + " handling event: " + event);
        for (GUIComponent component : components) {
            component.handleEvent(event);
        }
    }
}
```

### 2. **Tashkilot strukturasi**
```java
// Kompaniya ierarxiyasi
public abstract class Employee {
    protected String name;
    protected String position;
    protected double salary;
    
    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    
    public abstract void showDetails();
    public abstract double getTotalSalary();
}

public class Developer extends Employee {
    public Developer(String name, double salary) {
        super(name, "Developer", salary);
    }
    
    @Override
    public void showDetails() {
        System.out.println("Developer: " + name + " - $" + salary);
    }
    
    @Override
    public double getTotalSalary() {
        return salary;
    }
}

public class Manager extends Employee {
    private List<Employee> subordinates = new ArrayList<>();
    
    public Manager(String name, double salary) {
        super(name, "Manager", salary);
    }
    
    public void addSubordinate(Employee employee) {
        subordinates.add(employee);
    }
    
    public void removeSubordinate(Employee employee) {
        subordinates.remove(employee);
    }
    
    @Override
    public void showDetails() {
        System.out.println("Manager: " + name + " - $" + salary);
        for (Employee subordinate : subordinates) {
            subordinate.showDetails();
        }
    }
    
    @Override
    public double getTotalSalary() {
        double total = salary;
        for (Employee subordinate : subordinates) {
            total += subordinate.getTotalSalary();
        }
        return total;
    }
}
```

### 3. **Matematik ifodalar**
```java
// Matematik ifodalar uchun composite
public abstract class Expression {
    public abstract double evaluate();
    public abstract String toString();
}

public class Number extends Expression {
    private double value;
    
    public Number(double value) {
        this.value = value;
    }
    
    @Override
    public double evaluate() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

public class Addition extends Expression {
    private Expression left;
    private Expression right;
    
    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public double evaluate() {
        return left.evaluate() + right.evaluate();
    }
    
    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }
}

public class Multiplication extends Expression {
    private Expression left;
    private Expression right;
    
    public Multiplication(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public double evaluate() {
        return left.evaluate() * right.evaluate();
    }
    
    @Override
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }
}
```

---

## ✅ Afzalliklar

| Afzalliklari | Tavsifi |
|--------------|---------|
| **Uniform treatment** | Leaf va composite obyektlarni bir xil tarzda ishlatish |
| **Oson kengaytirish** | Yangi komponent turlarini qo'shish oson |
| **Rekursiv kompozitsiya** | Cheksiz chuqur daraxt strukturalari |
| **Kod soddalashtirish** | Mijoz kodi sodda va tushunarli |
| **Polymorphism** | Obyekt turini bilmasdan ishlatish mumkin |
| **Flexible structure** | Runtime'da struktura o'zgartirish mumkin |

---

## ⚠️ Kamchiliklar

| Kamchiliklari | Tavsifi |
|---------------|---------|
| **Dizayn cheklovlari** | Komponentlar juda umumiy bo'lishi mumkin |
| **Type safety** | Compile time'da komponent turini tekshirish qiyin |
| **Performance** | Chuqur daraxt strukturalarda sekinlashtirishi mumkin |
| **Xotira sarfi** | Har bir komponent uchun qo'shimcha xotira |
| **Debugging qiyinligi** | Rekursiv strukturalarni debug qilish murakkab |
| **Ortiqcha abstraktsiya** | Oddiy holatlar uchun murakkab bo'lishi mumkin |

---

## 🔄 Composite vs boshqa pattern'lar

| Jihat | Composite | Decorator | Adapter | Facade |
|-------|-----------|-----------|---------|---------|
| **Maqsad** | Daraxt strukturasi | Funksionallik qo'shish | Interfeys moslash | Soddalashtirish |
| **Strukturasi** | Tree hierarchy | Linear wrapping | Single adaptation | Subsystem wrapper |
| **Komponenta** | Bir nechta child | Bitta component | Bitta adaptee | Ko'p subsystem |
| **Operatsiya** | Rekursiv | Kengaytirish | Konvertatsiya | Yig'ish |

---

## 📚 JDK-da Composite Pattern misollari

### 1. **Swing GUI komponentlar**
```java
// Container va Component ierarxiyasi
JFrame frame = new JFrame("Window");
JPanel panel = new JPanel();
JButton button = new JButton("Click me");

panel.add(button);
frame.add(panel);
frame.setVisible(true);
```

### 2. **AWT komponentlar**
```java
// Component hierarxiyasi
Component button = new Button("OK");
Container panel = new Panel();
panel.add(button);
```

### 3. **XML DOM**
```java
// Node ierarxiyasi
Document document = DocumentBuilderFactory.newInstance()
    .newDocumentBuilder().newDocument();
Element root = document.createElement("root");
Element child = document.createElement("child");
root.appendChild(child);
```

### 4. **JavaFX Scene Graph**
```java
// Node va Parent ierarxiyasi
VBox vbox = new VBox();
Button button = new Button("Click");
Label label = new Label("Hello");

vbox.getChildren().addAll(button, label);
```

---

## 🎯 Qachon ishlatish kerak

### ✅ Ishlatish tavsiya etiladi:
- Daraxt strukturasidagi ma'lumotlar bilan ishlash
- Part-whole ierarxiyasini ifodalash kerak
- Leaf va composite obyektlarni bir xil tarzda ishlatish
- Rekursiv kompozitsiya kerak
- GUI komponentlar ierarxiyasi
- Fayl tizimi strukturasi
- Tashkilot ierarxiyasi

### ❌ Ishlatmaslik kerak:
- Sodda linear strukturalar uchun
- Faqat bitta turdagi obyektlar bilan ishlash
- Performance kritik joylarda
- Juda chuqur daraxt strukturalari
- Type safety muhim bo'lganda

---

## 🏗️ Boshqa pattern'lar bilan birga ishlatish

### 1. **Composite + Visitor**
```java
// Visitor pattern bilan birgalikda
public interface FileSystemVisitor {
    void visitFile(File file);
    void visitDirectory(Directory directory);
}

public abstract class FileSystemComponent {
    public abstract void accept(FileSystemVisitor visitor);
}

public class File extends FileSystemComponent {
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }
}

public class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children = new ArrayList<>();
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
        for (FileSystemComponent child : children) {
            child.accept(visitor);
        }
    }
}
```

### 2. **Composite + Iterator**
```java
// Iterator pattern bilan birgalikda
public class CompositeIterator<T> implements Iterator<T> {
    private Stack<Iterator<T>> stack = new Stack<>();
    
    public CompositeIterator(Iterator<T> iterator) {
        stack.push(iterator);
    }
    
    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        
        Iterator<T> iterator = stack.peek();
        if (!iterator.hasNext()) {
            stack.pop();
            return hasNext();
        }
        
        return true;
    }
    
    @Override
    public T next() {
        if (hasNext()) {
            Iterator<T> iterator = stack.peek();
            T component = iterator.next();
            
            if (component instanceof Composite) {
                stack.push(((Composite<T>) component).createIterator());
            }
            
            return component;
        }
        
        return null;
    }
}
```

### 3. **Composite + Command**
```java
// Command pattern bilan birgalikda
public class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>();
    
    public void add(Command command) {
        commands.add(command);
    }
    
    public void remove(Command command) {
        commands.remove(command);
    }
    
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
    
    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
```

---

## 🎯 Xulosa

Composite Pattern:

* ✅ Daraxt strukturasidagi obyektlar bilan ishlash uchun ideal
* ✅ Leaf va composite obyektlarni uniform tarzda ishlatish imkonini beradi
* ✅ Rekursiv kompozitsiya va cheksiz chuqur struktura qo'llab-quvvatlaydi
* ✅ Kod soddaligi va kengaytirish qulayligi ta'minlaydi
* ✅ GUI, fayl tizimi, tashkilot ierarxiyasi kabi real dunyoning ko'p sohalarda qo'llaniladi

**Tavsiya**: Composite pattern daraxt strukturasidagi ma'lumotlar bilan ishlashda juda kuchli vosita, lekin sodda holatlar uchun ortiqcha murakkab bo'lishi mumkin. Faqat haqiqiy ierarxik strukturalar kerak bo'lganda ishlatish tavsiya etiladi.