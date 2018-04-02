package astar;


import java.awt.*;
import java.util.HashMap;

public class ColorDictionary extends HashMap<String,Color>{

    private static ColorDictionary instance;

    public static ColorDictionary getInstance() {
        if (instance == null) {
            instance = new ColorDictionary();
            instance.put("0",Color.WHITE);
            instance.put("1",Color.BLACK);
            instance.put("S",Color.GREEN);
            instance.put("E",Color.RED);
            instance.put("P",Color.YELLOW);
            instance.put("C",Color.BLUE);
            instance.put("O",Color.CYAN);
        }
        return instance;
    }
}
