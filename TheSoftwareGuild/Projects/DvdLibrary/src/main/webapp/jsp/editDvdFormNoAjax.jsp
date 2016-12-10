<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
            <h1>Edit DVD Form</h1>
            <a href="displayDvdLibraryNoAjax">DVD Library(No Ajax)</a><br />
            <hr />
            <sf:form class="form-horizontal"
                     role="form"
                     modelAttribute="dvd"
                     action="editDvdNoAjax"
                     method="POST">
                <div class="form-group">
                    <div for="add-title" class="col-md-4 control-label">Title:</div>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-title" path="title" placeholder="Title" />
                        <sf:errors path="title" cssClass="error"></sf:errors>
                        </div>
                    </div>
                <div class="form-group">
                    <div for="add-released" class="col-md-4 control-label">Release Date:</div>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-released" path="released" placeholder="Release Date" />
                        <sf:errors path="released" cssClass="error"></sf:errors>
                        </div>
                    </div>
                <div class="form-group">
                    <div for="add-mpaa" class="col-md-4 control-label">MPAA Rating:</div>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-mpaa" path="mpaa" placeholder="MPAA Rating" />
                        <sf:errors path="mpaa" cssClass="error"></sf:errors>
                        </div>
                    </div>
                <div class="form-group">
                    <div for="add-director" class="col-md-4 control-label">Director:</div>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-director" path="director" placeholder="Director" />
                        <sf:errors path="director" cssClass="error"></sf:errors>
                        </div>
                    </div>
                <div class="form-group">
                    <div for="add-studio" class="col-md-4 control-label">Studio:</div>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-studio" path="studio" placeholder="Studio" />
                        <sf:errors path="studio" cssClass="error"></sf:errors>
                        </div>
                    </div>
                <div class="form-group">
                    <div for="add-rating" class="col-md-4 control-label">User Rating:</div>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-rating" path="rating" placeholder="User Rating" />
                        <sf:errors path="rating" cssClass="error"></sf:errors>
                        </div>
                    </div>
                <div class="form-group">
                    <div for="add-note" class="col-md-4 control-label">User Note:</div>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-note" path="note" placeholder="User Note" />
                        <sf:errors path="note" cssClass="error"></sf:errors>
                        <sf:hidden path="dvdId" />
                        </div>
                    </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" 
                                id="add-button" 
                                class="btn btn-default">Edit DVD</button>
                    </div>
                </div>
            </sf:form>
        </div>     
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>