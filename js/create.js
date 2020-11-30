onLoad(function() {
    $("#submit").click(function() {
        plain = $("form input[name='pwd']").val();
        $("form input[name='pwd']").val(sha256(plain));
        $("form").submit();
    });
});