function onLoad(fn) {
    $(document).ready(fn);
}

function checkForm(form) {
    var toSubmit = true;
    $(form).find("*[required]").each(function() {
        if ($(this).val() == "") {
            $(this).addClass("required");
            toSubmit = false;
        }
        else $(this).removeClass("required");
    });
    return toSubmit;
}

onLoad(function() {
    $("form").submit(function(e) {
        if(!checkForm(this)) {
            e.preventDefault();
            return false;
        }
    });
});

function slideFx(target, duration, option) {
    var t = target || ".slideFx", dur = duration || 1000, opt = Object.assign({
        opacity: 1,
        marginTop: 0
    }, option);
    return $(t).animate(opt, dur);
}