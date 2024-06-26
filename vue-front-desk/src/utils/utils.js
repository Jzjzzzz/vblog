const scrollToBottom = {
  getScrollTop: function () {
    let scrollTop = 0;
    let bodyScrollTop = 0;
    let documentScrollTop = 0;
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
    let scrollHeight = 0;
    let bodyScrollHeight = 0;
    let documentScrollHeight = 0;
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
    let windowHeight = 0;
    if (document.compatMode === 'CSS1Compat') {
      windowHeight = document.documentElement.clientHeight;
    } else {
      windowHeight = document.body.clientHeight;
    }
    return windowHeight;
  },
  onScrollEvent: function (callback) {
    const This = this;
    const scrollSum = Math.ceil(This.getScrollTop() + This.getClientHeight());
    if (scrollSum >= This.getScrollHeight()) {
      typeof callback == 'function' && callback.call(this);
    }
  }
};

const throttle = function (fn, delay) {
  let timer = null;
  return function () {
    const context = this;
    const args = arguments;
    clearTimeout(timer);
    timer = setTimeout(function () {
      fn.apply(context, args);
    }, delay);
  };
};

const isPhone = function () {
  const userAgentInfo = navigator.userAgent;
  const Agents = ['Android', 'iPhone',
    'SymbianOS', 'Windows Phone'];
  let flag = false;
  for (let v = 0; v < Agents.length; v++) {
    if (userAgentInfo.indexOf(Agents[v]) > 0) {
      flag = true;
      break;
    }
  }
  return flag;
};

export {
  scrollToBottom,
  throttle,
  isPhone
}
