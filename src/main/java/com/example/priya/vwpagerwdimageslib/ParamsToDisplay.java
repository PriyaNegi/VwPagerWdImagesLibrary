package com.example.priya.vwpagerwdimageslib;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Priya on 20-03-2015.
 */
public class ParamsToDisplay implements Serializable{
  private  ArrayList<String>imagesUrlList;
  private  ArrayList<String>imagesName;
    private int imgSelectedPosition;

    public ArrayList<String> getImagesUrlList() {
        return imagesUrlList;
    }

    public void setImagesUrlList(ArrayList<String> imagesUrlList) {
        this.imagesUrlList = imagesUrlList;
    }

    public ArrayList<String> getImagesName() {
        return imagesName;
    }

    public void setImagesName(ArrayList<String> imagesName) {
        this.imagesName = imagesName;
    }

    public int getImgSelectedPosition() {
        return imgSelectedPosition;
    }

    public void setImgSelectedPosition(int imgSelectedPosition) {
        this.imgSelectedPosition = imgSelectedPosition;
    }
}
