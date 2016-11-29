/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbook.dao;

import com.thesoftwareguild.addressbook.model.Address;
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
public class AddressBookDaoDbImpl implements AddressBookDao {

    private static final String SQL_INSERT_ADDRESS
            = "insert into addresses (first, last, street, city, state, zip) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_ADDRESS
            = "delete from addresses where address_id = ?";
    private static final String SQL_SELECT_ADDRESS
            = "select * from addresses where address_id = ?";
    private static final String SQL_UPDATE_ADDRESS
            = "UPDATE addresses SET first=?, last=?, street=?, city=?, state=?, zip=?"
            + " WHERE address_id=?";
    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from addresses";
    private static final String SQL_SELECT_ADDRESSES_BY_FIRST
            = "select * from addresses where first = ?";
    private static final String SQL_SELECT_ADDRESSES_BY_LAST
            = "select * from addresses where last = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Address addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getFirst(),
                address.getLast(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip());
        address.setAddressId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return address;
    }

    @Override
    public void removeAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
    }

    @Override
    public void updateAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getFirst(),
                address.getLast(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getAddressId());
    }

    @Override
    public List<Address> getAllAddresses() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
    }

    @Override
    public Address getAddressById(int addressId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS,
                    new AddressMapper(), addressId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria) 
    {
            if (criteria == null || criteria.size() == 0) {
            return getAllAddresses();
        }

        StringBuilder query = new StringBuilder("select * from addresses where ");

        int numParams = criteria.size();
        int paramPosition = 0;

        String[] paramVals = new String[numParams];

        Set<SearchTerm> keyset = criteria.keySet();
        Iterator<SearchTerm> iter = keyset.iterator();

        while (iter.hasNext()) {
            SearchTerm currentKey = iter.next();
            String currentValue = criteria.get(currentKey);

            if (paramPosition > 0) 
            {
                query.append(" and ");
            }

            query.append(currentKey);
            query.append(" =? ");

            paramVals[paramPosition] = currentValue;
            paramPosition++;
        }

        return jdbcTemplate.query(query.toString(), new AddressMapper(), paramVals);
    }

    @Override
    public List<Address> searchAddressesByFirst(String first) {
        return jdbcTemplate.query(SQL_SELECT_ADDRESSES_BY_FIRST, new AddressMapper(), first);
    }

    @Override
    public List<Address> searchAddressByLast(String last) {
        return jdbcTemplate.query(SQL_SELECT_ADDRESSES_BY_LAST, new AddressMapper(), last);
    }

        private static final class AddressMapper implements RowMapper<Address> {

        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setAddressId(rs.getInt("address_id"));
            address.setFirst(rs.getString("first"));
            address.setLast(rs.getString("last"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZip(rs.getString("zip"));
            return address;
        }
    }
    
}
