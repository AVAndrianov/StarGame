package com.avandrinov.stargame.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Regions {

    /**
     * Разбивает TextureRegion на фреймы
     *
     * @param region регион
     * @param rows   количество строк
     * @param cols   количество столбцов
     * @param frames количество фреймов
     * @return массив регионов
     */
    public static ArrayList<TextureRegion> split(TextureRegion region, int rows, int cols, int frames) {
        if (region == null) throw new RuntimeException("Split null region");
        ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();
        int tileWidth = region.getRegionWidth() / cols;
        int tileHeight = region.getRegionHeight() / rows;

        int frame = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                regions.add(new TextureRegion(region, tileWidth * j, tileHeight * i, tileWidth, tileHeight));
                if (frame == frames - 1) return regions;
                frame++;
            }
        }
        return regions;
    }
}
