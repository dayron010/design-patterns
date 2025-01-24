package org.example.creational.abstract_factory.kingdom;

import lombok.Getter;
import lombok.Setter;
import org.example.creational.abstract_factory.kingdom.elf.ElfKingdomFactory;
import org.example.creational.abstract_factory.kingdom.orc.OrcKingdomFactory;

/**
 * KingdomFactory yaratish uchun Kingdom yordamchi klassi
 */
@Getter
@Setter
public class Kingdom {

    private King king;
    private Castle castle;
    private Army army;

    public static class FactoryMaker {

        public enum KingdomType {
            ELF, ORC
        }

        public static KingdomFactory makeFactory(KingdomType type) {
            return switch (type) {
                case ELF -> new ElfKingdomFactory();
                case ORC -> new OrcKingdomFactory();
            };
        }

    }

}
