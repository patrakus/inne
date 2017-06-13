package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Patryk on 2017-01-13.
 */

public class LevelParser {

    private Pixmap levelMap;

    public LevelParser(){}

    public List<Sprite> parse2DMap()
    {
        Map<Vector2, Color> pixelsColorPosition = findNextPixel();
        return null;
    }

    private Map<Vector2, Color> findNextPixel()
    {
        HashMap<Vector2, Color> pixelsColorPosition = new HashMap<Vector2, Color>();

        for(int i = 0; i < levelMap.getWidth(); i++)
        {
            for(int j = 0; j < levelMap.getHeight(); j++)
            {
                Color color = new Color(levelMap.getPixel(i, j));
                if (color != Color.BLACK && allowedPixelColor(color))
                {
                    pixelsColorPosition.put(new Vector2( i + 1, j + 1), color);
                }
            }
        }

        return pixelsColorPosition;
    }

    private boolean allowedPixelColor(Color color)
    {
        if (color.equals(ColorsToObjects.BACKGRROUND) || color.equals(ColorsToObjects.GROUND))
            return true;

        return false;
    }

    private List<Sprite> generateSprites(Map<Color, Vector2> pixelsColorPosition)
    {
        List<Sprite> spriteObjects = new ArrayList<Sprite>();

        ColorConverter colorConverter = new ColorConverter();

        //spriteObjects = colorConverter.generateSpriteObjects(pixelsColorPosition);

        return null;

    }

    private class ColorConverter // generuje na ich podstawie sprite z daną pozycją
    {
        public ColorConverter(){}

        public List<Sprite> generateSpriteObjects(Map<Vector2, Color> pixelsColorPosition)
        {
            Vector2 SpritePos;

            //pixelsColorPosition. //TODO: odczytanie polorzenia oraz coloru który reprezentuje obiekt nastepnie storzyć na jego podstawie sprite

            return null;
        }
    }

    private static class ColorsToObjects
    {
        final static Color GROUND = Color.BLACK;
        final static Color BACKGRROUND = Color.WHITE;
    }	//"Ma listę obsługiwanych kolorów pixela "
}
