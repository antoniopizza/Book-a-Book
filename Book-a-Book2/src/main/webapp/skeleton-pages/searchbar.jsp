
<div class="row">
    <div class="advance-search col-lg-12">
        <% if (request.getSession().getAttribute("bibliotecario") != null) { %>
        <h3 class="widget-header">Cerca libri nella tua biblioteca</h3>
        <% } else { %>
        <h3 class="widget-header">Cerca un libro</h3>
        <% }%>
        <form action="<%=application.getContextPath()%>/libri/cerca-libro" id="search-form-main" method="GET">
            <div class="row">
                <!-- Store Search -->

                <div class="col-lg-12">
                    <div class="form-row">
                        <div class="col-3">
                            <select id="selected" class="form-control" name="criterio">
                                <option id="idTitolo" value="titolo">Titolo</option>
                                <option id="idAutore" value="autore">Autore</option>
                                <option id="idEditore" value="editore">Editore</option>
                                <option id="idIsbn" value="isbn">ISBN</option>

                            </select>
                        </div>
                        <div class="col">
                            <input type="text" minlength="2" class="form-control mb-2 mr-sm-2 mb-sm-0" id="search-main" placeholder="Cerca il tuo libro" name="searchKey" required>
                        </div>
                        <div class="col-2">
                            <button type="submit" class="btn btn-main" onclick="controlloSearchLibro()">CERCA</button>
                        </div>
                    </div>

                </div>

            </div>
        </form>    
    </div>
</div>
<script>
    function controlloSearchLibro() {
        var boolean = true;
        var titolo = document.getElementById("idTitolo");
        var autore = document.getElementById("idAutore");
        var editore = document.getElementById("idEditore");
        var isbn = document.getElementById("idIsbn");
        var search = document.getElementById("search");
        var regexLettere = /^[A-Za-z ]{1,30}$/;
        var regexNumeri = /^[0-9 ]{13}$/;
        document.getElementById("erroreSearchLibro").innerHTML = "";
        if (search.value == "") {
            $("#search").focus();
            $("#erroreSearchLibro").text("Il campo non pu� essere vuoto.");
            boolean = false;
        } else if (autore.selected && search.value != "") {
            if (!search.value.match(regexLettere)) {
                $("#search").focus();
                $("#erroreSearchLibro").text("Il campo deve contenere solo lettere.");
                boolean = false;
            }
        } else if (editore.selected && search.value != "") {
            if (!search.value.match(regexLettere)) {
                $("#search").focus();
                $("#erroreSearchLibro").text("Il campo pu� contenere solo lettere.");
                boolean = false;
            }
        } else if (isbn.selected && search.value != "") {
            if (!search.value.match(regexNumeri)) {
                $("#search").focus();
                $("#erroreSearchLibro").text("Il dato inserito non corrisponde al formato desiderato.");
                boolean = false;
            }
        }
        if (boolean == true) {
            document.cercaLibro.submit();
        }
    }

</script>
