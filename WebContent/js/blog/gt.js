/**
 * 极客验证初始
 */
(function (window, document) {

    if (window.initGeetest) {
        return;
    }

    var head = document.getElementsByTagName("head")[0];
    var protocol = location.protocol + "//";
    var callbacks = [];
    var status = "init";

    var random = function () {
        return parseInt(Math.random() * 10000) + (new Date()).valueOf();
    };
    var run = function () {
        for (var i = 0, len = callbacks.length; i < len; i = i + 1) {
            callbacks[i]();
        }
        callbacks = [];
    };
    var detect = function () {
        return window.Geetest || document.getElementById("gt_lib");
    };
    var down = function () {

        var s = document.createElement("script");
        s.charset = "UTF-8";
        s.type = "text/javascript";

        s.onload = s.onreadystatechange = function () {

            if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {

                if (detect()) {

                    status = "loaded";

                    run();

                } else {

                    status = "fail";

                    throw new Error("网络错误");

                }

                s.onload = s.onreadystatechange = null;
            }
        };

        s.onerror = function () {

            status = "fail";
            s.onerror = null;

            throw new Error("网络错误");
        };

        s.src = protocol + "static.geetest.com/static/js/geetest.0.0.0.js";
        head.appendChild(s);

    };

    var getLib = function (gt) {

        status = "loading";

        var cb = "geetest_" + random();

        window[cb] = function () {

            status = "loaded";

            run();

            window[cb] = undefined;
            try {
                delete window[cb];
            } catch (e) {
            }
        };

        var s = document.createElement("script");
        s.charset = "UTF-8";
        s.type = "text/javascript";

        s.onload = s.onreadystatechange = function () {

            if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {

                if (!detect()) {

                    down();

                }

            }
        };

        s.onerror = down;
        s.src = protocol + "api.geetest.com/getfrontlib.php?gt=" + gt + "&callback=" + cb;
        head.appendChild(s);
    };

    if (detect()) {

        status = "loaded";

    }

    window.initGeetest = function (config, callback) {

        if (typeof config.gt !== "string") {
            throw new Error("")
        }

        var init = function () {

            callback(new window.Geetest(config));

        };

        if (status === "loaded") {

            init();

        } else if (status === "fail") {

            throw new Error("网络错误");

        } else if (status === "loading") {

            callbacks.push(function () {

                init();

            });
        } else if (status === "init") {

            callbacks.push(function () {

                init();

            });

            getLib(config.gt);

        }
    };

})(window, document);

/**
 * 极客验证处理
 */
var handler = function (captchaObj) {
     // 将验证码加到id为captcha的元素里
     captchaObj.appendTo("#captcha");
 };
$.ajax({
    // 获取id，challenge，success（是否启用failback）
    url: "startCaptcha.htm",
    type: "get",
    dataType: "json", // 使用jsonp格式
    success: function (data) {
        // 使用initGeetest接口
        // 参数1：配置参数，与创建Geetest实例时接受的参数一致
        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
        initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            product: "float", // 产品形式
            offline: !data.success
        }, handler);
    }
});
