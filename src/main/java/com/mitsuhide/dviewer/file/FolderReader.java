/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitsuhide.dviewer.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Object for reading into a folder.
 *
 * @author Tony
 */
public class FolderReader {

    /* Default folder path */
    private static final String defaultPath = "E:\\Workspace\\dviewer\\src\\main\\webapp\\WEB-INF\\resources";

    /* Default Folder File */
    private File defaultFolder;

    /* List of ImageSet */
    private ArrayList<ImageSet> imageDirList;

    public ArrayList<ImageSet> getImageDirList() {
        return imageDirList;
    }

    public void setImageDirList(ArrayList<ImageSet> imageDirList) {
        this.imageDirList = imageDirList;
    }

    public File getDefaultFolder() {
        return defaultFolder;
    }
    
    public void setDefaultFolder(File defaultFolder) {
        this.defaultFolder = defaultFolder;
    }

    /*constructor */
    public FolderReader() {
        this.defaultFolder = new File(defaultPath);
        this.readDefaultFolder();
    }

    /*
     * Read the content of the default folder.
     */
    public void readDefaultFolder() {

        File[] set = defaultFolder.listFiles();
        ArrayList<ImageSet> sets = new ArrayList();

        int i = 0;
        for (File file : set) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
                sets.add(new ImageSet(file.getAbsolutePath(), file.getName()));
                i++;
            }
        }
        this.imageDirList = sets;
    }

    /* 
     * Read content from a images folder and return a list of images.
     *
     */
    public ArrayList<ImageSet> readFilesFolder(String folderName) throws IOException {

        Iterator<ImageSet> dirIte = imageDirList.iterator();

        boolean found = false;
        String folderPath = "";
        while (!found && dirIte.hasNext()) {
            ImageSet imgSet = dirIte.next();
            if (folderName.equals(imgSet.getLabel())) {
                found = true;
                folderPath = imgSet.getPath();
            }
        }

        if (!folderPath.isEmpty()) {

            File folder = new File(folderPath);
            File[] set = folder.listFiles();
            ArrayList<ImageSet> sets = new ArrayList();

            int i = 0;
            for (File file : set) {
                if (!file.isDirectory()) {
                    System.out.println(file.getName());
                    sets.add(new ImageSet(file.getAbsolutePath(), file.getName()));
                    i++;
                }
            }
            return sets;
        }
        
        return null;
    }

    /*
     * Return an JSON array of the ImageSet list.  
     */
    public JSONArray parseToJSONArray(ArrayList<ImageSet> imgSetList) {

        JSONArray jsonImageSets = new JSONArray();

        for (ImageSet imageSet : imgSetList) {

            String label = imageSet.getLabel();
            String path = imageSet.getPath();

            JSONObject object = new JSONObject();
            object.put("label", label);
            object.put("path", path);
            System.out.println(label + " : " + path);

            jsonImageSets.put(object);
        }

        System.out.println(jsonImageSets.toString());

        return jsonImageSets;
    }

    /*
     * Return a JSON object composed of the array of ImageSet list and the number of element.
     */
    public JSONObject parseToJSONObject(ArrayList<ImageSet> imgSetList) {

        JSONObject container = new JSONObject();
        JSONArray jsonImageSets = new JSONArray();

        for (ImageSet imageSet : imgSetList) {

            String label = imageSet.getLabel();
            String path = imageSet.getPath();

            JSONObject object = new JSONObject();
            object.put("label", label);
            object.put("path", path);
            System.out.println(label + " : " + path);

            jsonImageSets.put(object);
        }
        container.put("nbImages", imgSetList.size());
        container.put("images", jsonImageSets);

        System.out.println(container.toString());

        return container;
    }
}
