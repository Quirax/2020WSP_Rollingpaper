function onLoad(fn) {
    $(window).on("load", fn);
}

function slideFx(target, duration, option) {
    var t = target || ".slideFx", dur = duration || 1000, opt = option || {
        opacity: 1,
        marginTop: 0
    };
    return $(t).animate(opt, dur);
}