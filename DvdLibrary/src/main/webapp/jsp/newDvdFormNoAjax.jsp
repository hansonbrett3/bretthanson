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
        </div>
        <div class="container">
            <h1>New DVD Form</h1>
            <a href="displayDvdLibraryNoAjax">DVD Library (No Ajax)</a><br/>
            <hr/>
            <form class="form-horizontal"
                  role="form"
                  action="addNewDvdNoAjax"
                  method="POST">
                <div class="form-group">
                    <label for="add-title"
                           class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-title"
                               name="title"
                               placeholder="Title"/>
                        <sf:errors path="title" 
                                   cssClass="error">                         
                        </sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-released"
                           class="col-md-4 control-label">Release Date:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-released"
                               name="released"
                               placeholder="Release Date"/>
                        <sf:errors path="released" 
                                   cssClass="error">                          
                        </sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-mpaa"
                           class="col-md-4 control-label">MPAA Rating:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-mpaa"
                               name="mpaa"
                               placeholder="MPAA Rating"/>
                        <sf:errors path="mpaa" 
                                   cssClass="error">                           
                        </sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-director"
                           class="col-md-4 control-label">Director:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-director"
                               name="director"
                               placeholder="Director"/>
                        <sf:errors path="director" 
                                   cssClass="error">                          
                        </sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-studio"
                           class="col-md-4 control-label">Studio:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-studio"
                               name="studio"
                               placeholder="Studio"/>
                        <sf:errors path="studio" 
                                   cssClass="error">                           
                        </sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-rating"
                           class="col-md-4 control-label">User Rating:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-rating"
                               name="rating"
                               placeholder="User Rating"/>
                        <sf:errors path="rating" 
                                   cssClass="error">                          
                        </sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-note"
                           class="col-md-4 control-label">User Note:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-note"
                               name="note"
                               placeholder="User Note"/>
                        <sf:errors path="note" 
                                   cssClass="error">                           
                        </sf:errors>
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
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>