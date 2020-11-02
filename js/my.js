onLoad(function() {
    $(".close").click(function(e) {
        if(!confirm("한 번 닫은 롤링 페이퍼는 다시 열 수 없으며, 다른 사람들이 글을 남길 수 없습니다.\n그래도 계속하시겠습니까?")) {
            e.preventDefault();
            return false;
        }
    });
    $(".delete").click(function(e) {
        if(!confirm("롤링 페이퍼를 삭제하면 복구할 수 없습니다. [인쇄하기]를 통해 내용물을 사전에 받아두세요.\n계속하시겠습니까?")) {
            e.preventDefault();
            return false;
        }
    });
});