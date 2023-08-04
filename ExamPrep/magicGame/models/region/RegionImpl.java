package magicGame.models.region;

import magicGame.models.magicians.Magician;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;


public class RegionImpl implements Region {


    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizard = new ArrayList<>();
        List<Magician> blackWidow = new ArrayList<>();

        for (Magician magician : magicians) {
            if ("Wizard".equals(magician.getClass().getSimpleName())) {
                wizard.add(magician);
            } else if ("BlackWidow".equals(magician.getClass().getSimpleName())) {
                blackWidow.add(magician);
            }
        }
        while (!wizard.isEmpty() && !blackWidow.isEmpty()) {
            Magician magicianWizard = wizard.get(0);
            Magician magicianBlackWidow = blackWidow.get(0);
            magicianBlackWidow.takeDamage(magicianWizard.getMagic().fire());
            if (magicianBlackWidow.isAlive()) {
                magicianWizard.takeDamage(magicianBlackWidow.getMagic().fire());
                if (!magicianWizard.isAlive()) {
                    wizard.remove(0);
                }
            } else {
                blackWidow.remove(0);
            }
        }
        if (blackWidow.isEmpty()) {
            return "Wizards win!";
        }
        return "Black widows win!";
    }
}
