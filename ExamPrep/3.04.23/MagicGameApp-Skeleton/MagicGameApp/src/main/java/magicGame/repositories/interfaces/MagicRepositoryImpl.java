package magicGame.repositories.interfaces;

import magicGame.models.magics.Magic;

import java.util.Collection;
import java.util.Map;

public class MagicRepositoryImpl implements MagicRepository<Magic> {
    private Map<String,Magic> data;

    @Override
    public Collection<Magic> getData() {
        return data.values();
    }

    @Override
    public void addMagic(Magic model) {
        data.put(model.getName(),model);
    }

    @Override
    public boolean removeMagic(Magic model) {
        return data.remove(model.getName()) != null;
    }

    @Override
    public Magic findByName(String name) {
        return data.remove(name);
    }
}
