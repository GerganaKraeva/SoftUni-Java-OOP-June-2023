package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final ToyRepository toys;
    private final Map<String, House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        if ("LongHouse".equals(type)) {
            house = new LongHouse(name);
        } else if ("ShortHouse".equals(type)) {
            house = new ShortHouse(name);
        } else {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }
        houses.put(name, house);
        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        if ("Ball".equals(type)) {
            toy = new Ball();
        } else if ("Mouse".equals(type)) {
            toy = new Mouse();
        } else {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }
        toys.buyToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }
        houses.get(houseName).getToys().add(toy);
        toys.removeToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        if ("ShorthairCat".equals(catType)) {
            cat = new ShorthairCat(catName, catBreed, price);
        } else if ("LonghairCat".equals(catType)) {
            cat = new LonghairCat(catName, catBreed, price);
        } else {
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }
        House house = houses.get(houseName);
        String output;
        if (!CanCatLiveInHouse(catType, house)) {
            output = UNSUITABLE_HOUSE;
        } else {
            house.addCat(cat);
            output=String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);
        }
        return output;
    }

    private boolean CanCatLiveInHouse(String catType, House house) {
        String houseType = house.getClass().getSimpleName();
        boolean canLive = "LonghairCat".equals(catType) && "LongHouse".equals(houseType);
        if (!canLive) {
            canLive = "ShorthairCat".equals(catType) && "ShortHouse".equals(houseType);
        }
        return canLive;
    }

    @Override
    public String feedingCat(String houseName) {
        House currentHouse=houses.get(houseName);
        currentHouse.feeding();
        return String.format(FEEDING_CAT,currentHouse.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
       House house= houses.get(houseName);
       double sumToys=house.getToys().stream().mapToDouble(Toy::getPrice).sum();
       double sumCatsPrice=house.getCats().stream().mapToDouble(Cat::getPrice).sum();
       double totalSum=sumToys+sumCatsPrice;
        return String.format(VALUE_HOUSE,houseName,totalSum);
    }

    @Override
    public String getStatistics() {
        return houses.values()
                .stream()
                .map(House::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
