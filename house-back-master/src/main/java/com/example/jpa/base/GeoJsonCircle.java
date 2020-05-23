package com.example.jpa.base;

import javax.sound.midi.SoundbankResource;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonCircle {

    public static List<List<Double>> createGeoJsonCircle(Double lon, Double lat,Integer radiusInKm) {

        // 距离
        List<List<Double>> circle = new ArrayList<>();
        Double distanceX = radiusInKm/(111.320*Math.cos(lat*Math.PI/180));
        Double distanceY = radiusInKm/110.574;

        //画线
        Double theta,x,y;

        //聚圆
        int points = 64;
        for(int i=0;i<points;i++){
            theta = (i/points)*(2*Math.PI);
            x=distanceX*Math.cos(theta);
            y=distanceY*Math.sin(theta);
            List<Double> ret = new ArrayList<Double>();
            ret.add(lon+x);
            ret.add(lat+y);
            circle.add(ret);
        }

        return circle;
    }

    public static void main(String[] args) {

        List<List<Double>> res = GeoJsonCircle.createGeoJsonCircle(120.618793,31.328789,500);
        System.out.println(res);
    }
}
