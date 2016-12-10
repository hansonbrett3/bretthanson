/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.dao;

import com.thesoftwareguild.dvdlibrary.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author bretthanson
 */
public class DvdLibraryDaoInMemImpl implements DvdLibraryDao {

    private Map<Integer, Dvd> dvdMap = new HashMap();

    private static int dvdIdCounter = 0;

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> d = dvdMap.values();
        return new ArrayList(d);
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releasedCriteria = criteria.get(SearchTerm.RELEASED);
        String mpaaCriteria = criteria.get(SearchTerm.MPAA);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        String ratingCriteria = criteria.get(SearchTerm.RATING);
        String noteCriteria = criteria.get(SearchTerm.NOTE);

        Predicate<Dvd> titleMatches;
        Predicate<Dvd> releasedMatches;
        Predicate<Dvd> mpaaMatches;
        Predicate<Dvd> directorMatches;
        Predicate<Dvd> studioMatches;
        Predicate<Dvd> ratingMatches;
        Predicate<Dvd> noteMatches;

        Predicate<Dvd> truePredicate = (d) -> {
            return true;
        };

        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (d) -> d.getTitle().equals(titleCriteria);

        releasedMatches = (releasedCriteria == null || releasedCriteria.isEmpty())
                ? truePredicate
                : (d) -> d.getReleased().equals(releasedCriteria);

        mpaaMatches = (mpaaCriteria == null || mpaaCriteria.isEmpty())
                ? truePredicate
                : (d) -> d.getMpaa().equals(mpaaCriteria);

        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : (d) -> d.getDirector().equals(directorCriteria);

        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : (d) -> d.getStudio().equals(studioCriteria);

        ratingMatches = (ratingCriteria == null || ratingCriteria.isEmpty())
                ? truePredicate
                : (d) -> d.getRating().equals(ratingCriteria);

        noteMatches = (noteCriteria == null || noteCriteria.isEmpty())
                ? truePredicate
                : (d) -> d.getNote().equals(noteCriteria);

        return dvdMap.values().stream()
                .filter(titleMatches
                        .and(releasedMatches)
                        .and(mpaaMatches)
                        .and(directorMatches)
                        .and(studioMatches)
                        .and(ratingMatches)
                        .and(noteMatches))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> searchDvdsByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> searchDvdsByDirector(String director) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
