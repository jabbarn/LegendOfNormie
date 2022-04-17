package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chest extends Object{
    public Chest(String name, int worldX, int worldY) {
        super(name, worldX, worldY);
        try{
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
