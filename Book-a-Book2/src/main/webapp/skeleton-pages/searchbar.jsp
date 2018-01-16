<div class="advance-search">
    <form action="<%=application.getContextPath()%>/libri/cerca-libro" id="search-form-main" method="GET">
        <div class="row">
            <!-- Store Search -->
            <div class="col-lg-1"></div>
            <div class="col-lg-2 col-md-12">
                <select id="selected" class="form-control mb-2 mr-sm-2 mb-sm-0" name="criterio">
                    <option id="idTitolo" value="titolo">Titolo</option>
                    <option id="idAutore" value="autore">Autore</option>
                    <option id="idEditore" value="editore">Editore</option>
                    <option id="idIsbn" value="isbn">ISBN</option>

                </select>
            </div>
            <div class="col-lg-7 col-md-12">
                <div class="block d-flex">
                    <input type="text" minlength="2" class="form-control mb-2 mr-sm-2 mb-sm-0" id="search-main" placeholder="Cerca il tuo libro" name="searchKey" required>

                </div>
            </div>
            <div class="col-lg-2 col-md-12">
                <div class="block d-flex">
                    <!-- Search Button -->
                    <button type="submit" class="btn btn-main" onclick="controlloSearchLibro()">CERCA</button>
                </div>
            </div>
            <div class="col-lg-1"></div>
        </div>
    </form>
    <script>
        $("#search-form-main").submit({
            var text = $("#search-main").val().toString();
            if (text.match("/^.{2,}$/")){
                return true;
            } else {
                alert("Inserire almeno 2 caratteri");
                $("#search-main").focus();
                return false;
            }
        });
    </script>

</div>
<script>
    function controlloSearchLibro(){
        var boolean = true;
        var titolo = document.getElementById("idTitolo");
        var autore = document.getElementById("idAutore");
        var editore = document.getElementById("idEditore");
        var isbn = document.getElementById("idIsbn");
        var search = document.getElementById("search");
        var regexLettere= /^[A-Za-z ]{1,30}$/;
        var regexNumeri = /^[0-9 ]{13}$/ ;
        document.getElementById("erroreSearchLibro").innerHTML ="";
        if(search.value ==""){
            $("#search").focus();
            $("#erroreSearchLibro").text("Il campo non pu� essere vuoto.");
            boolean = false;
        }
        else if(autore.selected && search.value!= ""){
            if(!search.value.match(regexLettere)){
                $("#search").focus();
                $("#erroreSearchLibro").text("Il campo deve contenere solo lettere.");
                boolean = false;
            }
        }
        else if(editore.selected && search.value != ""){
            if(!search.value.match(regexLettere)){
                $("#search").focus();
                $("#erroreSearchLibro").text("Il campo pu� contenere solo lettere.");
                boolean = false;
            }
        }
        else if(isbn.selected && search.value!=""){
            if(!search.value.match(regexNumeri)){
                $("#search").focus();
                $("#erroreSearchLibro").text("Il dato inserito non corrisponde al formato desiderato.");
                boolean = false;
            }
        }
        if(boolean == true){
            document.cercaLibro.submit();
        }
    }
    
</script>