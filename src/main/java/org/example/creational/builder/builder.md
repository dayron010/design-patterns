# Builder pattern

Buildr pattern - bu murakkab ob'ektlarni bosqichma-bosqich qurish imkonini beruvchi yaratuvchi dizayn namunasi.
Pattern bir xil qurilish kodidan foydalangan holda ob'ektning turli xil turlari va ko'rinishlarini yaratishga imkon
beradi.

## Muammo:

Murakkab obyektlar yaratish: Obyektlar ko'plab parametrlar yoki konfiguratsiyalarga ega bo'lsa, oddiy konstruktor yoki
setter metodlari orqali obyekt yaratish qiyin bo'lishi mumkin. Bu kodni oʻqish va tushunishni qiyinlashtiradi.
Kod o'qilishi qiyinlashadi: Bir nechta parametrga ega konstruktorlar zanjiridan foydalangan holda obyekt yaratish kodni
chalkashtirishi mumkin.
Muqobil konfiguratsiya: Har xil konfiguratsiyalarni talab qiladigan obyektlarni yaratish murakkab bo'lishi mumkin.

## Qanday hal qiladi:

Obyektni qadam-baqadam yaratish: Builder pattern orqali obyektni bosqichma-bosqich yaratishingiz va faqat kerakli
atributlarni belgilashingiz mumkin.
Kodning o'qilishi yaxshilanadi: Builder orqali obyekt yaratish o'quvchan va aniq boʻladi.
Moslashuvchanlik: Har xil konfiguratsiyalarda obyektlar yaratishni osonlashtiradi.

## Misol: 
Uy (House) yaratish uchun Builder pattern.

```java
// Product class
public class House {

    private String foundation;

    private String structure;

    private String roof;

    private boolean hasGarden;

    private boolean hasSwimmingPool;

    // private constructor
    public House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.structure = builder.structure;
        this.roof = builder.roof;
        this.hasGarden = builder.hasGarden;
        this.hasSwimmingPool = builder.hasSwimmingPool;
    }

    @Override
    public String toString() {
        return "House{" +
                "foundation='" + foundation + '\'' +
                ", structure='" + structure + '\'' +
                ", roof='" + roof + '\'' +
                ", hasGarden=" + hasGarden +
                ", hasSwimmingPool=" + hasSwimmingPool +
                '}';
    }

    // Static nested builder class
    public static class HouseBuilder {

        private String foundation;

        private String structure;

        private String roof;

        private boolean hasGarden;

        private boolean hasSwimmingPool;

        public HouseBuilder foundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public HouseBuilder structure(String structure) {
            this.structure = structure;
            return this;
        }

        public HouseBuilder roof(String roof) {
            this.roof = roof;
            return this;
        }

        public HouseBuilder hasGarden(boolean hasGarden) {
            this.hasGarden = hasGarden;
            return this;
        }

        public HouseBuilder hasSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        public House build() {
            return new House(this);
        }

    }
}
```

```java
public class BuilderPatternExample {

    public static void main(String[] args) {

        // House clasini yaratish uchun Builderdan foydalanamiz
        House house = new House.HouseBuilder()
                .foundation("Concrete Foundation")
                .structure("Wooden Structure")
                .roof("Metal Roof")
                .hasGarden(true)
                .hasSwimmingPool(false)
                .build();

        System.out.println("house = " + house);

    }

}
```

## Foydasi:
Kodni o'qish va tushunish oson.
Obyektni moslashuvchan ravishda yaratish imkonini beradi.
Kerakli parametrlarni osonlikcha belgilash imkoniyatini beradi.