onLoad(function() {
    $("#add").click(function() {
        $("#content_form").css("display", "flex");
        $("#content_form form *").val("");
    });

    $(".close_form").click(function() {
        $(this).parents(".form_wrapper").css("display", "none");
    });

    $(".submit_form").click(function() {
        var $form = $(this).parents("form");
        var form = $form[0];
        if(!checkForm(form)) return;

        plain = $form.find("input[name='pwd']").val();

        var rsa = new RSA($("#rsa_modulus").val(), $("#rsa_exp").val());
        $form.find("input[name='pwd']").val(rsa.encrypt(sha256(plain)));
        $(".form_wrapper").css("display", "none");
        form.submit();
    });

    $("#delete_content").click(function() {
        $("#pwd_form").css("display", "flex");
        $("#pwd_form form *").val("");
        $("#pwd_form input[name=\"id\"]").val($("#content_view").data("id"));
        $("#pwd_form form").attr("action", "deleteContent.do");
    });

    $("#pwdChange").click(function() {
        $("#pwd_form").css("display", "flex");
        $("#pwd_form form *").val("");
        $("#pwd_form form").attr("action", "changePassword.do");
    });

    $("#closePaper").click(function() {
        if(!confirm("한 번 마감한 롤링 페이퍼는 다시 열 수 없으며, 다른 사람들이 글을 남길 수 없습니다.\n그래도 계속하시겠습니까?")) return;
        location.href = "closeRP.do";
    });
    
    $("ul li:not(#add)").click(function() {
        console.log($(this).data("id"));
        $("#content_view").data("id", $(this).data("id").toString()).css("display", "flex");
        $("#content").html($(this).children("div:first").html());
        $("#from").html($(this).children("div:last").html());
    })
});