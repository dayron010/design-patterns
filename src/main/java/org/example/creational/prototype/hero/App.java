package org.example.creational.prototype.hero;

import org.example.creational.prototype.hero.elf.ElfBeast;
import org.example.creational.prototype.hero.elf.ElfMage;
import org.example.creational.prototype.hero.elf.ElfWarlord;
import org.example.creational.prototype.hero.factory.HeroFactoryImpl;
import org.example.creational.prototype.hero.orc.OrcBest;
import org.example.creational.prototype.hero.orc.OrcMage;
import org.example.creational.prototype.hero.orc.OrcWarlord;

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
