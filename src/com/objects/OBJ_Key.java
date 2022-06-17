package com.objects;

import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObjects {

    public OBJ_Key() {

        name = "key";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\objects\\key.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
