/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitsuhide.dviewer.file;

/**
 *
 * @author Tony
 */
public class ImageSet {
    
    /* */
    private String path;
    
    /* */
    private String label;
    
    /*private List<Image> imgList;*/
    
    /*private String desc;*/

    /* constructor */
    public ImageSet(String path, String label) {
        this.path = path;
        this.label = label;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
