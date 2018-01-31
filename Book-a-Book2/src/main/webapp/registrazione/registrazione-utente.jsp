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
        
         <%   if (request.getSession().getAttribute("errore") != null) {
        %>
        <div class="container" style="background-color: #ff6347; text-align: center;" id="modifica">
            <h5 style="color: white;"> Errore Email già presente!</h5></div>
            <%
                    request.getSession().removeAttribute("errore");
                }
                %>

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
                                    <input type="text" class="form-control" name="email" placeholder="Es. utente@mail.it" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                                </div>


                                <div class="form-group">
                                    <div class="row">
                                        <!-- Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password">Password</label>
                                            <input type="password" class="form-control" name="password" placeholder="Mi raccomando. Difficile" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                   title="Deve avere una lunghezza di almeno 8 caratteri che devono essere almeno un numero, una letterea maiuscola ed una lettera minuscola."
                                                   id="password">
                                        </div>

                                        <!-- Conferma Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password">Conferma Password</label>
                                            <input type="password" class="form-control" name="confermapass" placeholder="Come quella di prima" required id="confermapass">
                                        </div>
                                    </div>
                                </div>

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" class="form-control" name="nome" placeholder="Es. Sara" required pattern="[A-Za-z' ]{2,}$">
                                </div>
                                <!-- Cognome -->
                                <div class="form-group">
                                    <label for="cognome">Cognome</label>
                                    <input type="text" class="form-control" name="cognome" placeholder="Es. Caso" required pattern="[A-Za-z' ]{2,}$">
                                </div>

                                <!-- Documento d'Identità -->
                                <div class="form-group">
                                    <label for="documento">Documento di Identita'</label>
                                    <input type="text" class="form-control" name="documento" required pattern="[A-Za-z0-9]{5,20}$">
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="cap">Provincia</label>
                                            <input type="text" class="form-control" name="provincia" placeholder="Es. NA" required pattern="[A-Z]{2}$">
                                        </div>  


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" name="citta" placeholder="Es. Cicciano" required pattern="[A-Za-z'- ]{2,}$">
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="via">Via</label>
                                            <input type="text" class="form-control" name="via" placeholder="Es. via Italia" required pattern="[A-Za-z- ]{2,}$">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">Numero Civico</label>
                                            <input type="text" class="form-control" name="civico" placeholder="Es. 12A" required pattern="[0-9A-Z ]{1,4}$">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">CAP</label>
                                            <input type="text" class="form-control" name="cap" placeholder="Es. 01234" required pattern="[0-9]{5}$">
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
                                    <input type="text" class="form-control" name="numero" placeholder="Es. 1234567890" required pattern="[0-9]{9,10}$">
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
            
             $(document).ready(setTimeout(function () {
                $("#modifica").hide();
            }, 3000));
            
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
