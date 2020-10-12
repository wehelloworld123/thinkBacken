package com.myIsoland.model;

import lombok.Data;

@Data
public class ImageInfo {

   private int index;

   private String url;


   public ImageInfo(){
       super();
   }

    public ImageInfo(int index, String url) {
        this.index = index;
        this.url = url;
    }
}
