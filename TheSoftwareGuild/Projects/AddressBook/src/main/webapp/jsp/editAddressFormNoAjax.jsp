<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

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
        </div>
        <div class="container">
            <h1>Edit Address Form</h1>
            <a href="displayAddressBookNoAjax">Address Book (No Ajax)</a><br/>
            <hr/>
            <sf:form class="form-horizontal" 
                     role="form" 
                     modelAttribute="address" 
                     action="editAddressNoAjax"
                     method="POST">
                <div class="form-group">
                    <div for="add-first" class="col-md-4 control-label">First Name:</div>
                    <div class="col-md-8">
                        <sf:input type="text" 
                                  class="form-control" 
                                  id="add-first" 
                                  path="first"
                                  placeholder="First Name"/>
                        <sf:errors path="first" 
                                   cssClass="error">                           
                        </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <div for="add-last" class="col-md-4 control-label">Last Name:</div>
                        <div class="col-md-8">
                        <sf:input type="text" 
                                  class="form-control" 
                                  id="add-last" 
                                  path="last"
                                  placeholder="Last Name"/>
                        <sf:errors path="last" 
                                   cssClass="error">                          
                        </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <div for="add-street" class="col-md-4 control-label">Street Address:</div>
                        <div class="col-md-8">
                        <sf:input type="text" 
                                  class="form-control" 
                                  id="add-street" 
                                  path="street"
                                  placeholder="Street Address"/>
                        <sf:errors path="street" 
                                   cssClass="error">                           
                        </sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <div for="add-city" class="col-md-4 control-label">City:</div>
                        <div class="col-md-8">
                        <sf:input type="text" 
                                  class="form-control" 
                                  id="add-city" 
                                  path="city"
                                  placeholder="City"/>
                        <sf:errors path="city" 
                                   cssClass="error">                            
                        </sf:errors>
                        </div>
                    </div> 
                    <div class="form-group">
                        <div for="add-state" class="col-md-4 control-label">State:</div>
                        <div class="col-md-8">
                        <sf:input type="text" 
                                  class="form-control" 
                                  id="add-state" 
                                  path="state"
                                  placeholder="State"/>
                        <sf:errors path="state" 
                                   cssClass="error">                           
                        </sf:errors>
                        </div>
                    </div>            
                    <div class="form-group">
                        <div for="add-zip" class="col-md-4 control-label">Zip Code:</div>
                        <div class="col-md-8">
                        <sf:input type="text" 
                                  class="form-control" 
                                  id="add-zip" 
                                  path="zip"
                                  placeholder="Zip Code"/>
                        <sf:errors path="zip" 
                                   cssClass="error">                           
                        </sf:errors>
                        <sf:hidden path="addressId" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" 
                                    id="add-button" 
                                    class="btn btn-default">Edit Address</button>
                        </div>
                    </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
