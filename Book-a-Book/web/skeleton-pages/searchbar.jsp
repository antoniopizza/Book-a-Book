<div class="row">
    <div class="col-lg-3"></div>   
    <div class="col-lg-6">
        <div class="form-group input-group">
            <div class="input-group-btn">
                <button type="button" class="btn btn-secondary dropdown-toggle" id="selected-filter" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Filtra
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item search-filter" href="#">Isbn</a>
                    <a class="dropdown-item search-filter" href="#">Editore</a>
                    <a class="dropdown-item search-filter" href="#">Autore</a>
                    <a class="dropdown-item search-filter" href="#">Editore</a>                    
                </div>
            </div>
            <input type="text" name="valore-ricerca" class="form-control" />
            <input type="hidden" name="chiave-ricerca" id="valore-ricerca" />
            <span class="input-group-btn">
                <button class="btn btn-secondary" type="button">
                    <i class="fa fa-search"></i>
                </button>
            </span>
        </div>
    </div>
    <div class="col-lg-3"></div>
</div>

<script type="text/javascript">
    $(".search-filter").click(function() {
        var filter = $(this).text();
        $("#selected-filter").text(filter);
        $("#valore-ricerca").val(filter);
    });
</script>