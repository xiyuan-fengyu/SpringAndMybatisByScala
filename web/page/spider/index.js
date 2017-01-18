/**
 * Created by xiyuan_fengyu on 2017/1/17.
 */
(function(){

    let formDiv;
    let form;
    let resultDiv;
    let inputUrl;
    let resultIF;

    $(document).ready(() => {
        formDiv = $("#formDiv");
        form = formDiv.find("form");
        resultDiv = $("#resultDiv");
        inputUrl = $("#inputUrl");
        resultIF = $("iframe[name=resultIF]");

        $("#getHtml").click(function () {
           let htmlUrl = inputUrl.val();
           if (htmlUrl != "") {
               form.submit();
           }
        });

        resize();
    });

    $(window).resize(function () {
        resize();
    });

    function resize() {
        let formDivH = formDiv.height();
        let winW = $(window).width();
        let winH = $(window).height();
        resultIF.css({
            width: winW + "px",
            height: (winH - formDivH - 30) + "px"
        });
    }

})();