package magicGame.models.magics;

public class RedMagic extends MagicImpl {

private static final int FIRE_BULLETS_COUNT=1;

    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int currentBullets=getBulletsCount()-FIRE_BULLETS_COUNT;
        if(currentBullets<0){
            return 0;
        }
        return FIRE_BULLETS_COUNT;
    }
}
