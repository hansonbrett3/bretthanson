/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.controller;

import com.thesoftwareguild.dvdlibrary.dao.DvdLibraryDao;
import com.thesoftwareguild.dvdlibrary.dao.SearchTerm;
import com.thesoftwareguild.dvdlibrary.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author bretthanson
 */
@Controller
public class SearchController {

    private DvdLibraryDao dao;

    @Inject
    public SearchController(DvdLibraryDao dao) 
    {
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySeachPage() 
    {
        return "search";
    }

    @RequestMapping(value = "search/dvds", method = RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchDvds(@RequestBody Map<String, String> searchMap) 
    {
        Map<SearchTerm, String> criteriaMap = new HashMap<>();

        String currentTerm = searchMap.get("title");       
        if (!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }
        
        currentTerm = searchMap.get("released");
        if (!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.RELEASED, currentTerm);
        }
        
        currentTerm = searchMap.get("mpaa");
        if (!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.MPAA, currentTerm);
        }
        
        currentTerm = searchMap.get("director");
        if (!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.DIRECTOR, currentTerm);
        }
        
        currentTerm = searchMap.get("studio");
        if (!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.STUDIO, currentTerm);
        }
        
        currentTerm = searchMap.get("rating");
        if (!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.RATING, currentTerm);
        }
        
        currentTerm = searchMap.get("note");
        if (!currentTerm.isEmpty()) 
        {
            criteriaMap.put(SearchTerm.NOTE, currentTerm);
        }
        
        return dao.searchDvds(criteriaMap);
    }
}

