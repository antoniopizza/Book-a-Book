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
                                    <input type="text" class="form-control" name="isil" placeholder="Es. IT-MI0130" required pattern="[A-Za-z0-9._-]{5,13}$">
                                </div> 

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome Biblioteca</label>
                                    <input type="text" class="form-control" name="nomeBiblioteca" placeholder="Es. Biblioteca Comunale" required pattern="[a-zA-Z-' ]{2,}$">
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="cap">Provincia</label>
                                            <input type="text" class="form-control" name="provincia" placeholder="Es. MI" required pattern="[A-Z]{2}$">
                                        </div>  


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" name="citta" placeholder="Es. Milano" required pattern="[A-Za-z'- ]{2,}$">
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="via">Via</label>
                                            <input type="text" class="form-control" name="via" placeholder="Es. via Roma" required pattern="[A-Z a-z'- ]{2,}$">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">Numero Civico</label>
                                            <input type="text" class="form-control" name="civico" placeholder="Es. 123" required pattern="[0-9]{1,}$">
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">CAP</label>
                                            <input type="text" class="form-control" name="cap" placeholder="Es. 01234" required pattern="[0-9]{5}$">
                                        </div>    
                                    </div>

                                </div>

                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" name="numero" placeholder="Es. 0123456789" required pattern="[0-9]{9,10}$">
                                </div>


                            </div>
                        </div>

                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">

                            <div class="widget personal-info">

                                <h3 class="widget-header user">Inserire dati Registrazione Bibliotecario</h3>

                                <!-- email -->
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control" name="email" placeholder="Es. bibliotecario@biblioteca.bib" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
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
                                            <input type="password" class="form-control" placeholder="Quella di prima" name="confermapass" required id="confermapass">
                                        </div>
                                    </div>
                                </div>

                                <!-- Nome -->
                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" class="form-control" name="nomeBibliotecario" placeholder="Es. Michele" required pattern="[A-Za-z' ]{2,}$">
                                </div>
                                <!-- Cognome -->
                                <div class="form-group">
                                    <label for="cognome">Cognome</label>
                                    <input type="text" class="form-control" name="cognome" placeholder="Di Michele" required pattern="[A-Za-z' ]{2,}$">
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
