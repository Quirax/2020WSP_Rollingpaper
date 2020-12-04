onLoad(function() {
    $("#login_form a.submit").on("click", function(e) {
        plain = $("#login_form input[name='pwd']").val();

        var rsa = new RSA($("#rsa_modulus").val(), $("#rsa_exp").val());
        $("#login_form input[name='pwd']").val(rsa.encrypt(sha256(plain)));
        $("#login_form").submit();
    });
    slideFx();
});