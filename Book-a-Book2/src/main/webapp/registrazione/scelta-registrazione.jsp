<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>
<% String nomePagina = "scelta-registrazione";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>
    <style>

        .section-title h2:after{
            background-color: #ba6a25;
        }

         .btn:active {
            text-decoration: none;
            color: white; 
        }

    </style>
    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <section class="dashboard section" style=" margin-top: 10%; margin-bottom: 30%;">
            <div class="container">
                <div class="widget user-dashboard-profile">
                    <div class="section-title">
                        <h2>              
                            Scegliere tipologia account 
                        </h2>
                    </div>



                    <div class="row">

                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">

                        </div>

                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0">
                            <a class="btn btn-transparent" href="registrazione-scelta?tipo=utente" style=" font-size: 20px; padding: 15px 30px;" >Utente</a>
                        </div>

                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0">
                            <a class="btn btn-transparent" href="registrazione-scelta?tipo=biblioteca" style= " font-size: 20px; padding: 15px 30px;" >Biblioteca</a>
                        </div>
                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">

                        </div>

                    </div>
                </div>

        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
