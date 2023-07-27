package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;


public class OperationImpl implements Operation {

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        //take discoverer
        //take exhibit
        //remove exhibit,add in museum of discoverer
        Collection<String> exhibits = spot.getExhibits();
        for (Discoverer discoverer : discoverers) {
            while (discoverer.canDig() && exhibits.iterator().hasNext()) {
                discoverer.dig();
                String currentExhibit = exhibits.iterator().next();
                discoverer.getMuseum().getExhibits();
                exhibits.remove(currentExhibit);
            }
        }


    }
}
