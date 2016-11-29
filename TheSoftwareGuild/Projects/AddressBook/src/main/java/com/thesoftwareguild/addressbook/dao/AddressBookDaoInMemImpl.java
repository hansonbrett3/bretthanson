/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbook.dao;

import com.thesoftwareguild.addressbook.model.Address;
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
public class AddressBookDaoInMemImpl implements AddressBookDao {

    private Map<Integer, Address> addressMap = new HashMap<>();

    private static int addressIdCounter = 0;

    @Override
    public Address addAddress(Address address) {
        address.setAddressId(addressIdCounter);
        addressIdCounter++;
        addressMap.put(address.getAddressId(), address);
        return address;
    }

    @Override
    public void removeAddress(int addressId) {
        addressMap.remove(addressId);
    }

    @Override
    public void updateAddress(Address address) {
        addressMap.put(address.getAddressId(), address);
    }

    @Override
    public List<Address> getAllAddresses() {
        Collection<Address> a = addressMap.values();
        return new ArrayList(a);
    }

    @Override
    public Address getAddressById(int addressId) {
        return addressMap.get(addressId);
    }

    @Override
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria) {
        String firstCriteria = criteria.get(SearchTerm.FIRST);
        String lastCriteria = criteria.get(SearchTerm.LAST);
        String streetCriteria = criteria.get(SearchTerm.STREET);
        String cityCriteria = criteria.get(SearchTerm.CITY);
        String stateCriteria = criteria.get(SearchTerm.STATE);
        String zipCriteria = criteria.get(SearchTerm.ZIP);

        Predicate<Address> firstMatches;
        Predicate<Address> lastMatches;
        Predicate<Address> streetMatches;
        Predicate<Address> cityMatches;
        Predicate<Address> stateMatches;
        Predicate<Address> zipMatches;

        Predicate<Address> truePredicate = (a) -> {
            return true;
        };

        firstMatches = (firstCriteria == null || firstCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getFirst().equals(firstCriteria);

        lastMatches = (lastCriteria == null || lastCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getLast().equals(lastCriteria);

        streetMatches = (streetCriteria == null || streetCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getStreet().equals(streetCriteria);

        cityMatches = (cityCriteria == null || cityCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getCity().equals(cityCriteria);

        stateMatches = (stateCriteria == null || stateCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getState().equals(stateCriteria);

        zipMatches = (zipCriteria == null || zipCriteria.isEmpty())
                ? truePredicate
                : (a) -> a.getZip().equals(zipCriteria);

        return addressMap.values().stream()
                .filter(firstMatches
                        .and(lastMatches)
                        .and(streetMatches)
                        .and(cityMatches)
                        .and(stateMatches)
                        .and(zipMatches))
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> searchAddressesByFirst(String first) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> searchAddressByLast(String last) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
