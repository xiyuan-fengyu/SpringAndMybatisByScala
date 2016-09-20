(function(global){
    "use strict";

    if (global["Log"] == null) {
        var Log = function(remote){
            this.remote = remote;
            this.enable = true;
        };

        Log.prototype.add = function (log) {
            if (this.enable) {
                $.get(this.remote, {log: log});
            }
        };

        global["Log"] = Log;
    }

})(this);