onLoad(function() {
    $("#login_form a").on("click", function(e) {
        alert("와!");
        e.preventDefault();
        return false;
    });
    slideFx();
});