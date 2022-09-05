var scrollToBottom = {
  getScrollTop: function () {
    var scrollTop = 0;
    var bodyScrollTop = 0;
    var documentScrollTop = 0;
    if (document.body) {
      bodyScrollTop = document.body.scrollTop;
    }
    if (document.documentElement) {
      documentScrollTop = document.documentElement.scrollTop;
    }
    scrollTop = (bodyScrollTop - documentScrollTop >= 0) ? bodyScrollTop : documentScrollTop;
    return scrollTop;
  },
  getScrollHeight: function () {
    var scrollHeight = 0;
    var bodyScrollHeight = 0;
    var documentScrollHeight = 0;
    if (document.body) {
      bodyScrollHeight = document.body.scrollHeight;
    }
    if (document.documentElement) {
      documentScrollHeight = document.documentElement.scrollHeight;
    }
    scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
    // scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
    return scrollHeight;
  },
  getClientHeight: function () {
    var windowHeight = 0;
    if (document.compatMode == 'CSS1Compat') {
      windowHeight = document.documentElement.clientHeight;
    } else {
      windowHeight = document.body.clientHeight;
    }
    return windowHeight;
  },
  onScrollEvent: function (callback) {
    var This = this;
    var scrollSum = Math.ceil(This.getScrollTop() + This.getClientHeight());
    if (scrollSum >= This.getScrollHeight()) {
      typeof callback == 'function' && callback.call(this);
    }
  }
};

var throttle = function (fn, delay) {
  var timer = null;
  return function () {
    var context = this;
    var args = arguments;
    clearTimeout(timer);
    timer = setTimeout(function () {
      fn.apply(context, args);
    }, delay);
  };
};

var isPhone = function () {
   var userAgentInfo = navigator.userAgent;
   var Agents = ['Android', 'iPhone',
      'SymbianOS', 'Windows Phone'];
   var flag = false;
   for (var v = 0; v < Agents.length; v++) {
      if (userAgentInfo.indexOf(Agents[v]) > 0) {
         flag = true;
         break;
      }
   }
   return flag;
}

export {
  scrollToBottom,
  throttle,
  isPhone
}
