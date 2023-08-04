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
        this.setHealth(health);
        this.setProtection(protection);;
        this.magic =magic;   }

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

        return health;
    }
    protected void setHealth(int health) {
        if(health<0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;

    }

    @Override
    public int getProtection() {
        return protection;
    }


    protected void setProtection(int protection) {
        if(protection<0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    @Override
    public Magic getMagic() {
        return magic;
    }

    protected void setMagic(Magic magic) {
        if(magic==null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.magic = magic;
    }

    @Override
    public boolean isAlive() {
        return getHealth()>0;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void takeDamage(int points) {
        int currentProtection =protection-points;
        if(currentProtection<0){
            setProtection(0);
            health+=currentProtection;
            if(health<=0){
                setHealth(0);
                setAlive(false);
            }
        }
    }

    @Override
    public String toString() {


        return String.format("%s: %s%n"+
                "Health: %d%n"+
                "Protection: %d%n"+
                "Magic: %s",getClass().getSimpleName(),username,
                health,
                protection,
                magic.getName());
    }
}
