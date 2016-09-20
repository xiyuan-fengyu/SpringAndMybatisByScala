(function(global){
    "use strict";

    if (global["Log"] == null) {
        var Log = function(remote){
            this.remote = remote;
            this.enable = true;
        };

        Log.prototype.add = function () {
            if (this.enable) {
                var log = "";
                for (var i = 0, len = arguments.length; i < len; i++) {
                    log += arguments[i];
                    if (i + 1 != len) {
                        log += ", ";
                    }
                }
                $.get(this.remote, {log: log});
            }
        };

        global["Log"] = Log;
    }

})(this);