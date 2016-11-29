/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.dao;

import com.thesoftwareguild.dvdlibrary.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bretthanson
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    private static final String SQL_INSERT_DVD
            = "insert into dvds (title, released, mpaa, director, studio, rating, note) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_DVD
            = "delete from dvds where dvd_id = ?";
    private static final String SQL_SELECT_DVD
            = "select * from dvds where dvd_id = ?";
    private static final String SQL_UPDATE_DVD
            = "UPDATE dvds SET title=?, released=?, mpaa=?, director=?, studio=?, rating=?, note=?"
            + " WHERE dvd_id=?";
    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvds";
    private static final String SQL_SELECT_DVDS_BY_TITLE
            = "select * from dvds where title = ?";
    private static final String SQL_SELECT_DVDS_BY_DIRECTOR
            = "select * from dvds where director = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleased(),
                dvd.getMpaa(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getRating(),
                dvd.getNote());
        dvd.setDvdId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getReleased(),
                dvd.getMpaa(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getRating(),
                dvd.getNote(),
                dvd.getDvdId());
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,
                    new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) { 
        if (criteria == null || criteria.size() == 0) {
            return getAllDvds();
        }

        StringBuilder query = new StringBuilder("SELECT * FROM dvds WHERE ");

        int numParams = criteria.size();
        int paramPosition = 0;

        String[] paramVals = new String[numParams];

        Set<SearchTerm> keyset = criteria.keySet();
        Iterator<SearchTerm> iter = keyset.iterator();

        while (iter.hasNext()) {
            SearchTerm currentKey = iter.next();
            String currentValue = criteria.get(currentKey);

            if (paramPosition > 0) {
                query.append(" and ");
            }

            query.append(currentKey);
            query.append(" =? ");

            paramVals[paramPosition] = currentValue;
            paramPosition++;
        }

        return jdbcTemplate.query(query.toString(), new DvdMapper(), paramVals);
    }

    @Override
    public List<Dvd> searchDvdsByTitle(String title) {
        return jdbcTemplate.query(SQL_SELECT_DVDS_BY_TITLE, new DvdMapper(), title);
    }

    @Override
    public List<Dvd> searchDvdsByDirector(String director) {
        return jdbcTemplate.query(SQL_SELECT_DVDS_BY_DIRECTOR, new DvdMapper(), director);
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getInt("dvd_id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleased(rs.getString("released"));
            dvd.setMpaa(rs.getString("mpaa"));
            dvd.setDirector(rs.getString("director"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setRating(rs.getString("rating"));
            dvd.setNote(rs.getString("note"));
            return dvd;
        }
    }

}
