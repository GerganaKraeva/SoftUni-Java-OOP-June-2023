package goldDigger.core;

import goldDigger.models.discoverer.*;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int count;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.count=0;
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        if ("Archaeologist".equals(kind)) {
            discoverer = new Archaeologist(discovererName);
        } else if ("Anthropologist".equals(kind)) {
            discoverer = new Anthropologist(discovererName);
        } else if ("Geologist".equals(kind)) {
            discoverer = new Geologist(discovererName);
        } else {
            throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibitsToAdd) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibitToAdd : exhibitsToAdd) {
            spot.getExhibits().add(exhibitToAdd);
        }

        spotRepository.add(spot);
        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        discovererRepository.remove(discoverer);
        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {

        List<Discoverer> suitableDiscoverers = discovererRepository
                .getCollection()
                .stream()
                .filter(d -> d.getEnergy() > 45)
                .collect(Collectors.toList());

        if (suitableDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Spot spot = spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, suitableDiscoverers);
        long tiresDiscoverers = suitableDiscoverers.stream()
                .filter(d -> d.getEnergy() == 0)
                .count();
        count++;
        return String.format(INSPECT_SPOT, spotName, tiresDiscoverers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_SPOT_INSPECT, count)).append(System.lineSeparator());
        sb.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        Collection<Discoverer> discoverers = discovererRepository.getCollection();
        for (Discoverer discoverer : discoverers) {
            sb.append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy())).append(System.lineSeparator());
            Collection<String> discovererExhibits = discoverer.getMuseum().getExhibits();
            if (discovererExhibits.isEmpty()) {
                sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None")).append(System.lineSeparator());
            } else {
                String allMuseumExhibits = String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, discovererExhibits);
                sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, allMuseumExhibits)).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();

    }
}

