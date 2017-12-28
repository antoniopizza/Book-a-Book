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
                <form action="registra-biblioteca" method="post">
                    <div class="row">   

                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                            <div class="widget personal-info">

                                <h3 class="widget-header user">Inserire dati Registrazione Biblioteca</h3>

                                <!-- Isil -->
                                <div class="form-group">
                                    <label for="isil">Isil</label>
                                    <input type="text" class="form-control" name="isil">
                                </div>

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome Biblioteca</label>
                                    <input type="text" class="form-control" name="nomeBiblioteca">
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

                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" name="numero">
                                </div>


                            </div>
                        </div>

                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">

                            <div class="widget personal-info">

                                <h3 class="widget-header user">Inserire dati Registrazione Bibliotecario</h3>



                                <!-- String isil,String nome,String via,String citta,
                                String numeroCivico,String provincia, String CAP,
                                
                                String email,String password,String pathFoto,
                                String tipo,String cognome, String nome -->

                                <!-- email -->
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control" name="email">
                                </div>

                                <!-- Password -->
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="text" class="form-control" name="password">
                                </div>

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" class="form-control" name="nomeBibliotecario">
                                </div>
                                <!-- Cognome -->
                                <div class="form-group">
                                    <label for="cognome">Cognome</label>
                                    <input type="text" class="form-control" name="cognome" >
                                </div>

                                <!-- Tipo-->
                                <div class="form-group">
                                    <label for="tipo">Tipo</label>
                                    <input type="text" class="form-control" name="tipo">
                                </div>

                                <!-- Path Foto -->
                                <div class="form-group">
                                    <label for="foto">Path Foto</label>
                                    <input type="text" class="form-control" name="foto">
                                </div>

                            </div>

                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0"></div>
                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                            <div class="widget personal-info">
                                
                                <!-- Submit button -->
                                <input type="submit" 
                                       class="btn btn-transparent" 
                                       value="Invia"
                                       style=" width: 100%;"
                                       >
                            </div>
                        </div>
                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0"></div>
                    </div>

                </form>

            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
