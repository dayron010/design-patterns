package org.example.behavioral.template_method;

// Abstract Class (Template Class)
public abstract class HouseTemplate {

    // Template method: final bo'lib, o'zgartirib bo'lmaydi
    public final void buildHouse() {
        buildFoundation();
        buildWalls();
        buildRoof();
        buildWindows();
        System.out.println("Uy qurib bo'lindi!");
    }

    // Abstrakt metodlar; Subclasslar tomonidan implement qilinadi
    abstract void buildWalls();
    abstract void buildRoof();

    // Konkret metod: Umumiy qatlam
    private void buildFoundation() {
        System.out.println("Poydevor qurildi: Beton va temir konstruksiyalar.");
    }

    // Hook metod: Subklasslar tomonidan override qilinishi mumkin
    void buildWindows() {
        System.out.println("Oddiy uynalar o'rnatildi.");
    }

}
