<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVD Library</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayDvdLibraryNoAjax">DVD Library (No Ajax)</a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h2>My DVDs</h2>
                    <table id="dvdTable" class="table table-hover">
                        <tr>
                            <th width="40%">Title</th>
                            <th width="30%">Release Date</th>
                            <th width="15%"></th>
                            <th width="15"></th>
                        </tr>
                        <tbody id="contentRows"></tbody>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Add New DVD</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">Title</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-title"
                                       placeholder="Title"/>
                                <div id="title" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-released" class="col-md-4 control-label">Release Date</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-released"
                                       placeholder="Release Date"/>
                                <div id="released" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-mpaa" class="col-md-4 control-label">MPAA Rating</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-mpaa"
                                       placeholder="MPAA Rating"/>
                                <div id="mpaa" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">Director</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-director"
                                       placeholder="Director"/>
                                <div id="director" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-studio" class="col-md-4 control-label">Studio</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-studio"
                                       placeholder="Studio"/>
                                <div id="studio" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-rating" class="col-md-4 control-label">Rating</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-rating"
                                       placeholder="Rating"/>
                                <div id="rating" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-note" class="col-md-4 control-label">Note</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-note"
                                       placeholder="Note"/>
                                <div id="note" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit"
                                        id="add-button"
                                        class="btn btn-default">Add New DVD</button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors" style="color: red" />
                </div>
            </div>
        </div>
        <!-- DETAILS MODAL -->
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">DVD Details</h4>                          
                    </div>
                    <div class="modal-body">
                        <h3 id="dvd-id"></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Title:</th>
                                <td id="dvd-title"></td>
                            </tr>
                            <tr>
                                <th>Release Date:</th>
                                <td id="dvd-released"></td>
                            </tr>
                            <tr>
                                <th>MPAA Rating:</th>
                                <td id="dvd-mpaa"></td>
                            </tr>
                            <tr>
                                <th>Director Name:</th>
                                <td id="dvd-director"></td>
                            </tr>
                            <tr>
                                <th>Studio:</th>
                                <td id="dvd-studio"></td>
                            </tr>
                            <tr>
                                <th>User Rating:</th>
                                <td id="dvd-rating"></td>
                            </tr>
                            <tr>
                                <th>Note:</th>
                                <td id="dvd-note"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!--EDIT MODAL -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Edit DVD</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="dvd-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-title" class="col-md-4 control-label">Title:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-title" 
                                           placeholder="Title">
                                    <div id="title" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-released" class="col-md-4 control-label">Release Date:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-released"
                                           placeholder="Release Date">
                                    <div id="released" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-mpaa" class="col-md-4 control-label">MPAA Rating:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-mpaa"
                                           placeholder="MPAA Rating">
                                    <div id="mpaa" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-director" class="col-md-4 control-label">Director Name:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-director" 
                                           placeholder="Director Name">
                                    <div id="director" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-studio" class="col-md-4 control-label">Studio:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-studio" 
                                           placeholder="studio">
                                    <div id="studio" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-rating" class="col-md-4 control-label">User Rating:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-rating" 
                                           placeholder="User Rating">
                                    <div id="rating" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-note" class="col-md-4 control-label">User Note:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-note" 
                                           placeholder="User Note">
                                    <div id="note" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" id="edit-button" class="btn btn-default" data-dismiss="modal">Edit DVD</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <input type="hidden" id="edit-dvd-id">
                                </div>
                            </div>
                        </form>
                        <div id="validationErrors" style="color: red" />
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvdLibrary.js"></script>
    </body>
</html>
