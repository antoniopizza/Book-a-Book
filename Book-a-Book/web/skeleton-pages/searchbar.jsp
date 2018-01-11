<div class="advance-search">
    <form action="<%=application.getContextPath()%>/libri/cerca-libro" id="search-form-main" method="GET">
        <div class="row">
            <!-- Store Search -->
            <div class="col-lg-1"></div>
            <div class="col-lg-2 col-md-12">
                <select class="form-control mb-2 mr-sm-2 mb-sm-0" name="criterio">
                    <option>Titolo</option>
                    <option>Autore</option>
                    <option>Editore</option>
                    <option>ISBN</option>
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
                    <input type="submit" class="btn btn-main" value="CERCA">
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