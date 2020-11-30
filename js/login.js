onLoad(function() {
    $("#login_form a.submit").on("click", function(e) {
        plain = $("#login_form input[name='pwd']").val();
        $("#login_form input[name='pwd']").val(sha256(plain));
        $("#login_form").submit();
    });
    slideFx();
});