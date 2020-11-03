function onLoad(fn) {
    $(document).ready(fn);
}

function slideFx(target, duration, option) {
    var t = target || ".slideFx", dur = duration || 1000, opt = Object.assign({
        opacity: 1,
        marginTop: 0
    }, option);
    return $(t).animate(opt, dur);
}