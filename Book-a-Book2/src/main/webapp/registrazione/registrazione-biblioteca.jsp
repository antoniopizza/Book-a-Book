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
                <form action="registra-biblioteca" method="post">
                    <div class="row">   

                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                            <div class="widget personal-info">

                                <h3 class="widget-header user">Inserire dati Registrazione Biblioteca</h3>

                                <!-- Isil -->
                                <div class="form-group">
                                    <label for="isil">Isil</label>
                                    <input type="text" class="form-control" name="isil" required pattern="[A-Za-z0-9._-]{5,13}$">
                                </div> 

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome Biblioteca</label>
                                    <input type="text" class="form-control" name="nomeBiblioteca" required pattern="[a-zA-Z]{2,}$">
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="cap">Provincia</label>
                                            <input type="text" class="form-control" name="provincia" required pattern="[A-Z]{2,3}$">
                                        </div>  


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" name="citta" required pattern="[A-Za-z]{2,}$">
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="via">Via</label>
                                            <input type="text" class="form-control" name="via" required pattern="[A-Za-z]{2,}$">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">Numero Civico</label>
                                            <input type="text" class="form-control" name="civico" required pattern="[0-9]{1,}$">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">CAP</label>
                                            <input type="text" class="form-control" name="cap" required pattern="[0-9]{4,5}$">
                                        </div>    
                                    </div>

                                </div>

                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" name="numero" required pattern="[0-9]{9,11}$">
                                </div>


                            </div>
                        </div>

                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">

                            <div class="widget personal-info">

                                <h3 class="widget-header user">Inserire dati Registrazione Bibliotecario</h3>

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
                                    <input type="text" class="form-control" name="nomeBibliotecario" required pattern="[A-Za-z]{2,}$">
                                </div>
                                <!-- Cognome -->
                                <div class="form-group">
                                    <label for="cognome">Cognome</label>
                                    <input type="text" class="form-control" name="cognome" required pattern="[A-Za-z]{2,}$">
                                </div>
                                <!-- Foto -->
                                <div class="form-group">
                                    <label for="foto">Foto</label>
                                    <input type="text" class="form-control" name="foto" required>
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
