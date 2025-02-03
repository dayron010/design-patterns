package org.example.creational.prototype;

import org.example.creational.prototype.elf.ElfBeast;
import org.example.creational.prototype.elf.ElfMage;
import org.example.creational.prototype.elf.ElfWarlord;
import org.example.creational.prototype.factory.HeroFactoryImpl;
import org.example.creational.prototype.orc.OrcBest;
import org.example.creational.prototype.orc.OrcMage;
import org.example.creational.prototype.orc.OrcWarlord;

public class App {

    public static void main(String[] args) {

        var factory = new HeroFactoryImpl(
                new ElfBeast("cooking"),
                new ElfMage("cleaning"),
                new ElfWarlord("protecting")
        );

        System.out.println(factory.createBest());
        System.out.println(factory.createMage());
        System.out.println(factory.createWarlord());

        factory = new HeroFactoryImpl(
                new OrcBest("axe"),
                new OrcMage("sword"),
                new OrcWarlord("laser")
        );

        System.out.println(factory.createBest());
        System.out.println(factory.createMage());
        System.out.println(factory.createWarlord());

    }

}
