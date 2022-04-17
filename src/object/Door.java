package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends Object{

    public Door(String name, int worldX, int worldY) {
        super(name, worldX, worldY);
        super.collision = true;
        try{
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
