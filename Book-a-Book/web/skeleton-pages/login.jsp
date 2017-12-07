<%-- 
    Document   : login
    Created on : 7-dic-2017, 15.59.02
    Author     : manuel
--%>
<% String nomePagina = "Accedi"; %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <div id="wrapper">        
        <%@include file="navigation.jsp" %>
        
        <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Accedi                                
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.html">Book a Book</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-file"></i> Accedi
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <%@include file="searchbar.jsp" %>
                    
                    <div class="row ">
                        <div class="col-lg-4"></div>
                        
                        <div class="col-lg-4 jumbotron">
                            <form>
                                <fieldset class="form-group">
                                    
                                    <label>Email</label>
                                    <input type="text" class="form-control" />
                                    
                                     <label>Password</label>
                                    <input type="password" class="form-control" />
                                    
                                </fieldset>
                                
                                <fieldset class="form-group">
                                    <button type="submit" class="btn btn-secondary">Accedi</button>
                                </fieldset>
                                
                            </form>
                        </div>
                        
                        <div class="col-lg-4"></div>
                    </div>
                    
                </div>
                <!-- /.container-fluid -->
                <div style="min-height: 300px"></div>

            </div>
            <!-- /#page-wrapper -->
        
        
        </div>
    </body>
</html>
