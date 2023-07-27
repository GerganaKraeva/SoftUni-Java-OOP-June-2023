package magicGame.models.magics;

public class BlackMagic extends MagicImpl {
    private static final int FIRE_BULLETS_COUNT = 10;

    public BlackMagic(String name, int bulletsCount) {
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
