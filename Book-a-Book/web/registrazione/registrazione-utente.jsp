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
                                    <input type="text" class="form-control" name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                                </div>


                                <div class="form-group">
                                    <div class="row">
                                        <!-- Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password">Password</label>
                                            <input type="password" class="form-control" name="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                   title="Deve avere una lunghezza di almeno 8 caratteri che devono essere almeno un numero, una letterea maiuscola ed una lettera minuscola."
                                                   id="password">
                                        </div>

                                        <!-- Conferma Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password">Conferma Password</label>
                                            <input type="password" class="form-control" name="confermapass" required id="confermapass">
                                        </div>
                                    </div>
                                </div>

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" class="form-control" name="nome" required>
                                </div>
                                <!-- Cognome -->
                                <div class="form-group">
                                    <label for="cognome">Cognome</label>
                                    <input type="text" class="form-control" name="cognome" required>
                                </div>

                                <!-- Documento d'Identità -->
                                <div class="form-group">
                                    <label for="documento">Documento di Identita'</label>
                                    <input type="text" class="form-control" name="documento" required>
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="cap">Provincia</label>
                                            <input type="text" class="form-control" name="provincia" required>
                                        </div>  


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" name="citta" required>
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="via">Via</label>
                                            <input type="text" class="form-control" name="via" required>
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">Numero Civico</label>
                                            <input type="text" class="form-control" name="civico" required>
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">CAP</label>
                                            <input type="text" class="form-control" name="cap" required>
                                        </div>    
                                    </div>

                                </div>


                                <!-- Path Foto -->
                                <div class="form-group">
                                    <label for="foto">Path Foto</label>
                                    <input type="text" class="form-control" name="foto" required>
                                </div>

                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" name="numero" required pattern="[0-9]{9,11}$">
                                </div>

                                <!-- Submit button -->
                                <input type="submit" 
                                       class="btn btn-transparent" 
                                       value="Invia"
                                       style="margin-left: 25%; margin-right: 0%; width:50%;"
                                       >


                            </form>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0"> </div>

                </div>

            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>

        <script>
            var password = document.getElementById("password");
            var confirm_password = document.getElementById("confermapass");

            function validatePassword() {
                if (password.value != confirm_password.value) {
                    confirm_password.setCustomValidity("La password non combacia!");
                } else {
                    confirm_password.setCustomValidity('');
                }
            }

            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;

        </script>
    </body>
</html>
