<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>
<% String nomePagina = "registrazione-utente";
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

                            <form action="registra-utente" method="post">

                                <!-- email -->
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control" name="email">
                                </div>


                                <div class="form-group">
                                    <div class="row">
                                        <!-- Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password">Password</label>
                                            <input type="text" class="form-control" name="password">
                                        </div>

                                        <!-- Conferma Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password">Conferma Password</label>
                                            <input type="text" class="form-control" name="confermapass">
                                        </div>
                                    </div>
                                </div>

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" class="form-control" name="nome">
                                </div>
                                <!-- Cognome -->
                                <div class="form-group">
                                    <label for="cognome">Cognome</label>
                                    <input type="text" class="form-control" name="cognome" >
                                </div>

                                <!-- Documento d'Identità -->
                                <div class="form-group">
                                    <label for="documento">Documento di Identita'</label>
                                    <input type="text" class="form-control" name="documento">
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="cap">Provincia</label>
                                            <input type="text" class="form-control" name="provincia">
                                        </div>  


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" name="citta">
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="via">Via</label>
                                            <input type="text" class="form-control" name="via">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">Numero Civico</label>
                                            <input type="text" class="form-control" name="civico">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">CAP</label>
                                            <input type="text" class="form-control" name="cap">
                                        </div>    
                                    </div>

                                </div>


                                <!-- Path Foto -->
                                <div class="form-group">
                                    <label for="foto">Path Foto</label>
                                    <input type="text" class="form-control" name="foto">
                                </div>

                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" name="numero">
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
