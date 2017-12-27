<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>
<% String nomePagina = "registrazione-biblioteca";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <section class="dashboard section">
            <div class="container">

                <div class="row">   

                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0"> </div>
                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                        <div class="widget personal-info">

                            <h3 class="widget-header user">Inserire dati Registrazione</h3>

                            <form action="registra-biblioteca" method="post">



                                <!-- email -->
                                <div class="form-group">
                                    <label for="isil">Isil</label>
                                    <input type="text" class="form-control" nome="isil">
                                </div>

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome Biblioteca</label>
                                    <input type="text" class="form-control" nome="nome">
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">
                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" nome="citta">
                                        </div>
                                        
                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="via">Via</label>
                                        <input type="text" class="form-control" nome="via">
                                        </div>
                                        
                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="cap">CAP</label>
                                        <input type="text" class="form-control" nome="cap">
                                        </div>    
                                    </div>
                                </div>
                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" nome="numero">
                                </div>

                                <!-- Submit button -->
                                <input type="submit" 
                                       class="btn btn-transparent" 
                                       value="Invia"
                                       style="margin-left: 80%; margin-right: 0%;"
                                       >


                            </form>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0"> </div>

                </div>

            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
