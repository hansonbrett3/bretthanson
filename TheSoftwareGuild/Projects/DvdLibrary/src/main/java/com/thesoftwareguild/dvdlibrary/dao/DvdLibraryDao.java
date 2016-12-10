/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.dao;

import com.thesoftwareguild.dvdlibrary.model.Dvd;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bretthanson
 */
public interface DvdLibraryDao {
    
    public Dvd addDvd(Dvd dvd);
    
    public void removeDvd(int dvdId);
    
    public void updateDvd(Dvd dvd);
    
    public List<Dvd> getAllDvds();
    
    public Dvd getDvdById(int dvdId);
    
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);
    
    public List<Dvd> searchDvdsByTitle(String title);
        
    public List<Dvd> searchDvdsByDirector(String director);
}
