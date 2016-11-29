/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    loadAddresses();

    $('#add-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'address',
            data: JSON.stringify({
                first: $('#add-first').val(),
                last: $('#add-last').val(),
                street: $('#add-street').val(),
                city: $('#add-city').val(),
                state: $('#add-state').val(),
                zip: $('#add-zip').val()
            }),
            headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#add-first').val('');
            $('#add-last').val('');
            $('#add-street').val('');
            $('#add-city').val('');
            $('#add-state').val('');
            $('#add-zip').val('');

            $('#validationErrors').empty();
            $('.form-error').text('');
            loadAddresses();
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.validationErrors, function (index, validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
                $('#' + validationError.fieldName).text(validationError.message);
            });
        });
    });
    $('#edit-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'PUT',
            url: 'address/' + $('#edit-address-id').val(),
            data: JSON.stringify({
                addressId: $('#edit-address-id').val(),
                first: $('#edit-first').val(),
                last: $('#edit-last').val(),
                street: $('#edit-street').val(),
                city: $('#edit-city').val(),
                state: $('#edit-state').val(),
                zip: $('#edit-zip').val()
            }),
            headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#edit-first').val('');
            $('#edit-last').val('');
            $('#edit-street').val('');
            $('#edit-city').val('');
            $('#edit-state').val('');
            $('#edit-zip').val('');

            $('#validationErrors').empty();
            $('.form-error').text('');
            loadAddresses();
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.validationErrors, function (index, validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
                $('#' + validationError.fieldName).text(validationError.message);
            });
        });
    });
    $('#search-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'search/addresses',
            data: JSON.stringify({
                first: $('#search-first').val(),
                last: $('#search-last').val(),
                street: $('#search-street').val(),
                city: $('#search-city').val(),
                state: $('#search-state').val(),
                zip: $('#search-zip').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-first').val('');
            $('#search-last').val('');
            $('#search-street').val('');
            $('#search-city').val('');
            $('#search-state').val('');
            $('#search-zip').val('');

            fillAddressTable(data, status);
        });
    });
});
//===============
// FUNCTIONS
//===============

function loadAddresses() {
    $.ajax({
        url: "addresses"
    }).success(function (data, status) {
        fillAddressTable(data, status);
    });
}

function fillAddressTable(addressBook, status) {

    clearAddressTable();
    var aTable = $('#contentRows');

    $.each(addressBook, function (index, address) {
        aTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-address-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(address.first + ' ' + address.last)
                                )
                        )
                .append($('<td>').text(address.street))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-address-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                )
                        )
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteAddress(' + address.addressId + ')'
                                })
                                .text('Delete')
                                )
                        )
                );
    });
}


function deleteAddress(id) {
    var answer = confirm("Do you really want to delete this address?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'address/' + id
        }).success(function () {
            loadAddresses();
        });
    }
}


function clearAddressTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {
        modal.find('#address-id').text(address.addressId);
        modal.find('#address-first').text(address.first);
        modal.find('#address-last').text(address.last);
        modal.find('#address-street').text(address.street);
        modal.find('#address-city').text(address.city);
        modal.find('#address-state').text(address.state);
        modal.find('#address-zip').text(address.zip);
    });
});
$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {
        modal.find('#address-id').text(address.addressId);
        modal.find('#edit-address-id').val(address.addressId);
        modal.find('#edit-first').val(address.first);
        modal.find('#edit-last').val(address.last);
        modal.find('#edit-street').val(address.street);
        modal.find('#edit-city').val(address.city);
        modal.find('#edit-state').val(address.state);
        modal.find('#edit-zip').val(address.zip);
    });
});

//$('#edit-button').click(function (event) {
//    event.preventDefault();
//    $.ajax({
//        type: 'PUT',
//        url: 'address/' + $('#edit-address-id').val(),
//        data: JSON.stringify({
//            addressId: $('#edit-address-id').val(),
//            first: $('#edit-first').val(),
//            last: $('#edit-last').val(),
//            street: $('#edit-street').val(),
//            city: $('#edit-city').val(),
//            state: $('#edit-state').val(),
//            zip: $('#edit-zip').val()
//        }),
//        headers: {
//            'Accept': 'application/json',
//            'Content-Type': 'application/json'
//        },
//        'dataType': 'json'
//    }).success(function () {
//        loadAddresses();
//    });
//});
var testAddressData = [
    {
        addressId: 1,
        first: "Jane",
        last: "Doe",
        street: "111 First Street",
        city: "Cleveland",
        state: "OH",
        zip: "44118"},
    {
        addressId: 2,
        first: "Ben",
        last: "Doe",
        street: "222 Second Street",
        city: "Chicago",
        state: "IL",
        zip: "44117"},
    {
        addressId: 3,
        first: "Matt",
        last: "Doe",
        street: "333 Third Street",
        city: "St. Louis",
        state: "MO",
        zip: "55703"}
];
var dummyDetailsAddress =
        {
            addressId: "27",
            first: "John",
            last: "Smith",
            street: "27 Main Street",
            city: "Columbus",
            state: "OH",
            zip: "44506"
        };
var dummyEditAddress =
        {
            addressId: 45,
            first: "James",
            last: "Thomas",
            street: "3333 Park Street",
            city: "Cincinnati",
            state: "OH",
            zip: "55012"
        };