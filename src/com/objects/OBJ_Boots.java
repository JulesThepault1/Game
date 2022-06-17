package com.objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Boots extends SuperObjects{

    public OBJ_Boots() {

        name = "Boots";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\objects\\boots.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
