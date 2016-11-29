<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Book</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
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
                        <a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">Address Book (No Ajax)</a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h2>My Addresses</h2>
                    <table id="addressTable" class="table table-hover">
                        <tr>
                            <th width="40%">Name</th>
                            <th width="30%">Street</th>
                            <th width="15%"></th>
                            <th width="15"></th>
                        </tr>
                        <tbody id="contentRows"></tbody>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Add New Address</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-first" class="col-md-4 control-label">First Name</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-first"
                                       placeholder="First Name"/>
                                <div id="first" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-last" class="col-md-4 control-label">Last Name</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-last"
                                       placeholder="Last Name"/>
                                <div id="last" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street" class="col-md-4 control-label">Street Address</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-street"
                                       placeholder="Street Address"/>
                                <div id="street" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-city"
                                       placeholder="City"/>
                                <div id="city" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-state"
                                       placeholder="State"/>
                                <div id="state" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zip" class="col-md-4 control-label">Zip Code</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-zip"
                                       placeholder="Zip Code"/>
                                <div id="zip" 
                                     style="color:red" 
                                     class="form-error">                                        
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit"
                                        id="add-button"
                                        class="btn btn-default">Add New Address</button>
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
                        <h4 class="modal-title" id="detailsModalLabel">Address Details</h4>                          
                    </div>
                    <div class="modal-body">
                        <h3 id="address-id"></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>First Name:</th>
                                <td id="address-first"></td>
                            </tr>
                            <tr>
                                <th>Last Name:</th>
                                <td id="address-last"></td>
                            </tr>
                            <tr>
                                <th>Street Address:</th>
                                <td id="address-street"></td>
                            </tr>
                            <tr>
                                <th>City:</th>
                                <td id="address-city"></td>
                            </tr>
                            <tr>
                                <th>State:</th>
                                <td id="address-state"></td>
                            </tr>
                            <tr>
                                <th>Zip Code:</th>
                                <td id="address-zip"></td>
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
                        <h4 class="modal-title" id="detailsModalLabel">Edit Address</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="address-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-first" class="col-md-4 control-label">First Name:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-first"
                                           placeholder="First Name">
                                    <div id="first" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-last" class="col-md-4 control-label">Last Name:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-last" 
                                           placeholder="Last Name">
                                    <div id="last" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-street" class="col-md-4 control-label">Street Address:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-street" 
                                           placeholder="Street Address">
                                    <div id="street" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-city" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-city" 
                                           placeholder="City">
                                    <div id="city" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-state" class="col-md-4 control-label">State:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-state" 
                                           placeholder="State">
                                    <div id="state" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-zip" class="col-md-4 control-label">Zip Code:</label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           class="form-control" 
                                           id="edit-zip" 
                                           placeholder="Zip Code">
                                    <div id="zip" 
                                         style="color:red" 
                                         class="form-error">                                        
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" id="edit-button" class="btn btn-default" data-dismiss="modal">Edit Address</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <input type="hidden" id="edit-address-id">
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
        <script src="${pageContext.request.contextPath}/js/addressBook.js"></script>
    </body>
</html>
