/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitsuhide.dviewer.services;

import com.mitsuhide.dviewer.file.FolderReader;
import com.mitsuhide.dviewer.file.ImageSet;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Define webservices resources.
 * 
 * @author Tony
 */
@Path("init")
public class initService {
    
    private FolderReader folderRead = new FolderReader();

    /* */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONImageSets() {
        //FolderReader folderRead = new FolderReader();
        try {
            JSONArray JSONImageSets  = folderRead.parseToJSONArray(folderRead.getImageDirList());
            String jsonResponse = JSONImageSets.toString();
            System.out.println("JSON Data sent to client");
            return jsonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    
    /* */
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONImageSets(@PathParam("name") String name) {
        //FolderReader folderRead = new FolderReader();
        try {
            ArrayList<ImageSet> imgsList = folderRead.readFilesFolder(name);
            JSONObject JSONImageSets  = folderRead.parseToJSONObject(imgsList);
            String jsonResponse = JSONImageSets.toString();
            System.out.println("JSON Data sent to client from URI");
            return jsonResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
