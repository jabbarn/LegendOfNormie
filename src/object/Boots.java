package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Boots extends Object{
    public Boots(String name, int worldX, int worldY) {
        super(name, worldX, worldY);
        try{
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
