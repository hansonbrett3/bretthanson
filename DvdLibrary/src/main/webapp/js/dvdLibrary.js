/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadDvds();

    $('#add-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'dvd',
            data: JSON.stringify({
                title: $('#add-title').val(),
                released: $('#add-released').val(),
                mpaa: $('#add-mpaa').val(),
                director: $('#add-director').val(),
                studio: $('#add-studio').val(),
                rating: $('#add-rating').val(),
                note: $('#add-note').val()
            }),
            headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#add-title').val('');
            $('#add-released').val('');
            $('#add-mpaa').val('');
            $('#add-director').val('');
            $('#add-studio').val('');
            $('#add-rating').val('');
            $('#add-note').val('');

            $('#validationErrors').empty();
            $('.form-error').text('');
            loadDvds();
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
            url: 'dvd/' + $('#edit-dvd-id').val(),
            data: JSON.stringify({
                dvdId: $('#edit-dvd-id').val(),
                title: $('#edit-title').val(),
                released: $('#edit-released').val(),
                mpaa: $('#edit-mpaa').val(),
                director: $('#edit-director').val(),
                studio: $('#edit-studio').val(),
                rating: $('#edit-rating').val(),
                note: $('#edit-note').val()
            }),
            headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#edit-title').val('');
            $('#edit-released').val('');
            $('#edit-mpaa').val('');
            $('#edit-director').val('');
            $('#edit-studio').val('');
            $('#edit-rating').val('');
            $('#edit-note').val('');

            $('#validationErrors').empty();
            $('.form-error').text('');
            loadDvds();
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
            url: 'search/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                released: $('#search-released').val(),
                mpaa: $('#search-mpaa').val(),
                director: $('#search-director').val(),
                studio: $('#search-studio').val(),
                rating: $('#search-rating').val(),
                note: $('#search-note').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-title').val('');
            $('#search-released').val('');
            $('#search-mpaa').val('');
            $('#search-director').val('');
            $('#search-studio').val('');
            $('#search-rating').val('');
            $('#search-note').val('');

            fillDvdTable(data, status);
        });
    });
});

//===============
// FUNCTIONS
//===============

function fillDvdTable(dvdLibrary, status)
{
    clearDvdTable();
    var dTable = $('#contentRows');

    $.each(dvdLibrary, function (index, dvd) {
        dTable.append($('<tr>')
                .append($('<td>').append(
                        $('<a>')
                        .attr(
                                {
                                    'data-dvd-id': dvd.dvdId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                }
                        )
                        .text(dvd.title + ' | ' + dvd.mpaa))
                        )
                .append($('<td>').text(dvd.released))
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-dvd-id': dvd.dvdId,
                            'data-toggle': 'modal',
                            'data-target': '#editModal'
                        })
                        .text('Edit')))
                .append($('<td>').append($('<a>')
                        .attr({'onClick': 'deleteDvd(' + dvd.dvdId + ')'})
                        .text('Delete')))
                );
    });
}

function loadDvds() {
    $.ajax({
        url: "dvds"
    }).success(function (data, status) {
        fillDvdTable(data, status);
    });
}

function clearDvdTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-released').text(dvd.released);
        modal.find('#dvd-mpaa').text(dvd.mpaa);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-rating').text(dvd.rating);
        modal.find('#dvd-note').text(dvd.note);
    });
});

$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdId);
        modal.find('#edit-dvd-id').val(dvd.dvdId);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-released').val(dvd.released);
        modal.find('#edit-mpaa').val(dvd.mpaa);
        modal.find('#edit-director').val(dvd.director);
        modal.find('#edit-studio').val(dvd.studio);
        modal.find('#edit-rating').val(dvd.rating);
        modal.find('#edit-note').val(dvd.note);
    });
});

function deleteDvd(id) {
    var answer = confirm("Do you really want to delete this dvd?");
    if (answer === true)
    {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDvds();
        });
    }
}

var testDvdData = [
    {
        dvdId: 1,
        title: "Star Wars I",
        released: "1989",
        mpaa: "PG",
        director: "George Lucas",
        studio: "Warner Brothers",
        rating: "Good",
        note: "Seen"},
    {
        dvdId: 2,
        title: "Star Wars II",
        released: "1991",
        mpaa: "PG-13",
        director: "George Lucas II",
        studio: "Universal",
        rating: "Better",
        note: "Seen"},
    {
        dvdId: 1,
        title: "Star Wars III",
        released: "1993",
        mpaa: "R",
        director: "George Lucas III",
        studio: "Paramount",
        rating: "Best",
        note: "Seen"}
];

var dummyDetailsDvd =
        {
            dvdId: 45,
            title: "Star Wars 45",
            released: "1945",
            mpaa: "PG-45",
            director: "George Lucas XLV",
            studio: "Universal",
            rating: "Terrible",
            note: "Seen"
        };

var dummyEditDvd =
        {
            dvdId: 50,
            title: "Star Wars ",
            released: "1950",
            mpaa: "PG-50",
            director: "George Lucas XL",
            studio: "Universal",
            rating: "Amazing",
            note: "Seen"
        };