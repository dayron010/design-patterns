# Abstract Factory

Abstract Factory dizayn patterni – bu Creational (yaratish) dizayn patternlaridan biri bo‘lib, bir-biriga bog‘liq yoki
birgalikda ishlatilishi kerak bo‘lgan obyektlar oilasini ularning konkret turlarini ko‘rsatmasdan yaratishga imkon
beradi. Bu pattern muayyan turdagi obyektlarni yaratishni koddan ajratishga yordam beradi.

## Muammo

Tarkibiy qismlarning bir oilasi mavjud va ular birga ishlashi kerak bo‘lgan turli xil ilovalaringiz bor. Misol uchun,
GUI (Graphical User Interface) ilovasida macOS, Windows va Linux uchun alohida interfeys komponentlarini yaratishingiz
kerak. Har bir platforma uchun o‘ziga xos tugma, oynalar, menyular va boshqa elementlar bo‘lishi kerak.

Agar har bir komponentni alohida yaratib, ulardan foydalanadigan kodni yozsangiz, kod o‘zgaruvchanlikka chidamsiz
bo‘ladi. Har safar yangi turdagi komponent qo‘shilganda, barcha platformaga mos kodni qayta yozishingiz kerak bo‘ladi.

## Abstract Factory Pattern nima muammoni hal qiladi?

Abstract Factory patterni quyidagi muammolarni hal qiladi:

1. Platformaga bog‘liqlikni yo‘q qiladi. Platformaga xos bo‘lgan komponentlar oilasini yaratishni bir joyga jamlaydi.
2. O‘zgartirish qulayligini oshiradi. Yangi komponentlar oilasini qo‘shishda mavjud kodga minimal o‘zgartirish
   kiritishni ta'minlaydi.
3. Kodning moslashuvchanligini oshiradi. Yaratilgan obyektlarning turini kod yozish paytida emas, ishlash paytida
   tanlash imkonini beradi.

## Muammoni qanday hal qiladi?

Abstraktsiyaga asoslanadi. Pattern umumiy interfeyslardan foydalanib, obyektlarni yaratish jarayonini yashiradi.
OOP tamoyillariga mos keladi. Kodni mustaqil komponentlarga bo‘lish orqali bir-biriga bog‘liqlikni kamaytiradi.
O‘zgarishlarga tayyor bo‘ladi. Yangi turdagi obyektlar oilasi qo‘shilganda, faqat yangi factory sinflar qo‘shiladi.

## Kod:

```java
// Abstract Products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

// Concrete Products
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Windows style button");
    }
}

class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("MacOS style button");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Windows style checkbox");
    }
}

class MacOSCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("MacOS style checkbox");
    }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();

    Checkbox createCheckbox();
}

// Concrete Factories
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
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
}

// Client Code
public class AbstractFactoryExample {
    private static void clientCode(GUIFactory factory) {
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.render();
    }

    public static void main(String[] args) {
        // Use Windows Factory
        GUIFactory windowsFactory = new WindowsFactory();
        System.out.println("Using Windows Factory:");
        clientCode(windowsFactory);

        // Use MacOS Factory
        GUIFactory macosFactory = new MacOSFactory();
        System.out.println("\nUsing MacOS Factory:");
        clientCode(macosFactory);
    }
}

```

## Afzalliklari

Kodni mustaqil qilib yozish imkonini beradi.
Obyektlar oilasini qo‘shishda kodni minimal o‘zgartirish talab etadi.
Yaratilgan obyektlar orasida muvofiqlikni saqlaydi.

## Kamchiliklari

Murakkablikni oshirishi mumkin, ayniqsa kichik loyihalarda.
Ko‘p sonli sinflar qo‘shilishi mumkin.