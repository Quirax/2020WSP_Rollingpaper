onLoad(function() {
    $("#add").click(function() {
        $("#content_form").css("display", "flex");
        $("#content_form form *").val("");
    });

    $(".close_form").click(function() {
        $(this).parents(".form_wrapper").css("display", "none");
    });

    $(".submit_form").click(function() {
        var form = $(this).parents("form")[0];
        if(!checkForm(form)) return;
        $(".form_wrapper").css("display", "none");
        alert("적용되었습니다");
    });

    $("#delete_content").click(function() {
        $("#pwd_form").css("display", "flex");
        $("#pwd_form form *").val("");
    });

    $("#pwdChange").click(function() {
        $("#pwd_form").css("display", "flex");
        $("#pwd_form form *").val("");
    });

    $("#closePaper").click(function() {
        if(!confirm("한 번 닫은 롤링 페이퍼는 다시 열 수 없으며, 다른 사람들이 글을 남길 수 없습니다.\n그래도 계속하시겠습니까?")) return;
        alert("닫혔습니다!");
    });
    
    $("ul li:not(#add)").click(function() {
        $("#content_view").css("display", "flex");
    })
});