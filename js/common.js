function onLoad(fn) {
    $(document).ready(fn);
}
onLoad(function() {
    $("form").submit(function(e) {
        var toSubmit = true;
        $(this).find("*[required]").each(function() {
            if ($(this).val() == "") {
                $(this).addClass("required");
                toSubmit = false;
            }
            else $(this).removeClass("required");
        });
        if(!toSubmit) {
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