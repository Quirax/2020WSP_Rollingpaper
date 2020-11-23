onLoad(function() {
    $("#login_form a.submit").on("click", function(e) {
        $("#login_form").submit();
    });
    slideFx();
});