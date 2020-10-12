package com.myIsoland.model;

import lombok.Data;

@Data
public class ImageInfo {

   private int index;

   private String url;

   private int height;

   private int width;


   public ImageInfo(){
       super();
   }

    public ImageInfo(int index, String url,int height,int width) {
        this.index = index;
        this.url = url;
        this.height = height;
        this.width = width;
    }
}
