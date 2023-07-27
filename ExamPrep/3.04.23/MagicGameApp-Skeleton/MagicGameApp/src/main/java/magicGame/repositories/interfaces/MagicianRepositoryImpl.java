package magicGame.repositories.interfaces;

import magicGame.models.magicians.Magician;

import java.util.Collection;
import java.util.Map;

public class MagicianRepositoryImpl implements MagicianRepository<Magician>{
    private Map<String,Magician> data;
    @Override
    public Collection<Magician> getData() {
        return data.values();
    }

    @Override
    public void addMagician(Magician model) {
        data.put(model.getUsername(),model);

    }

    @Override
    public boolean removeMagician(Magician model) {
        return data.remove(model.getUsername()) !=null;
    }

    @Override
    public Magician findByUsername(String name) {
        return data.get(name);
    }
}
