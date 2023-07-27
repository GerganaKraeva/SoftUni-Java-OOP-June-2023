package magicGame.models.magicians;

import magicGame.models.magics.Magic;
import magicGame.models.magics.MagicImpl;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician {
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    protected MagicianImpl(String username, int health, int protection, Magic magic) {
        this.setUsername(username);
        this.health = health;
        this.protection = protection;
        this.magic = magic;
    }

    @Override
    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        if(username==null || username.trim().isEmpty()){
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    @Override
    public int getHealth() {
        if(health<0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        return health;
    }

    @Override
    public int getProtection() {
        if(protection<0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        return protection;
    }

    @Override
    public Magic getMagic() {
        if(magic==null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        return magic;
    }

    @Override
    public boolean isAlive() {
        return getHealth()<0;
    }

    @Override
    public void takeDamage(int points) {
        protection -=points;
        if(protection<=0){
            health+=protection;
        }
    }
}
