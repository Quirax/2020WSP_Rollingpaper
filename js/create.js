onLoad(function() {
    $("#submit").click(function() {
        plain = $("form input[name='pwd']").val();

        var rsa = new RSA($("#rsa_modulus").val(), $("#rsa_exp").val());
        $("form input[name='pwd']").val(rsa.encrypt(sha256(plain)));
        $("form").submit();
    });
});