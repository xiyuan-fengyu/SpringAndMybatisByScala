/**
 * Created by xiyuan_fengyu on 2017/1/17.
 */
(function(){
    class Logger {
        constructor() {
            this.dom = $("#logger");
            this.logs = [];
        }

        log(str) {
            this.logs.push(str);
            this.dom.html(this.logs.map(item => `<li class="list-group-item">${item.replace(/\n/g, "<br>")}</li>`).join(""));
        }
    }

    let logger = new Logger();

    $(document).ready(() => {
        let inputLog = $("#inputLog");

        $("#addLog").click(() => {
            logger.log(inputLog.val());
        });
    });

})();