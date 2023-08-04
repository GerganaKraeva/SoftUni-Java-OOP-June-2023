package magicGame.core;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.MagicianImpl;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepository;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepository;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static magicGame.common.ExceptionMessages.*;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGIC;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN;

public class ControllerImpl implements Controller {

    private MagicianRepositoryImpl magicians;
    private MagicRepositoryImpl magics;
    private Region region;

    public ControllerImpl() {
        this.magics=new MagicRepositoryImpl();
        this.magicians=new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magicToAdd;
        if ("RedMagic".equals(type)) {
            magicToAdd = new RedMagic(name, bulletsCount);
        } else if ("BlackMagic".equals(type)) {
            magicToAdd = new BlackMagic(name, bulletsCount);
        } else {
            throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }
        magics.addMagic(magicToAdd);
        return String.format(SUCCESSFULLY_ADDED_MAGIC, magicToAdd.getName());
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        MagicianImpl magicianToAdd;
        if (magicName == null || magicName.trim().isEmpty()) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        } else if ("Wizard".equals(type)) {
            magicianToAdd = new Wizard(username, health, protection, magics.findByName(magicName));
        } else if ("BlackWidow".equals(type)) {
            magicianToAdd = new BlackWidow(username, health, protection, magics.findByName(magicName));
        } else {
            throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }
        magicians.addMagician(magicianToAdd);
        return String.format(SUCCESSFULLY_ADDED_MAGICIAN, magicianToAdd.getUsername());

    }

    @Override
    public String startGame() {
        Collection<Magician> magicianAlive = magicians.getData()
                .stream()
                .filter(Magician::isAlive)
                .collect(Collectors.toList());
        return region.start(magicianAlive);
    }


    @Override
    public String report() {
        List<Magician>magicianList=magicians.getData()
                .stream()
                .sorted(Comparator.comparing(Magician::getHealth).thenComparing(Magician::getUsername)).collect(Collectors.toList());

       return magicianList.stream().map(Magician::toString).collect(Collectors.joining(System.lineSeparator()));

    }
}
