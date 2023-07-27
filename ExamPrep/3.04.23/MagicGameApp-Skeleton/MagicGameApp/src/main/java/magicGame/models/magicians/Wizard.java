package magicGame.models.magicians;

import magicGame.models.magics.Magic;

public class Wizard extends MagicianImpl{

    protected Wizard(String username, int health, int protection, Magic magic) {
        super(username, health, protection, magic);
    }
}
