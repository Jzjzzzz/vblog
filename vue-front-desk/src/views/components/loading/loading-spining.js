import loadingSpining from './loading-spining.vue';
import Vue from 'vue'

loadingSpining.newInstance = properties => {
  const props = properties || {};

  const Instance = new Vue({
    data: props,
    render (h) {
      return h(loadingSpining, {
          props: props
      });
    }
  });

  const component = Instance.$mount();
  document.body.appendChild(component.$el);
  const loading_spining = Instance.$children[0];
  console.log(Instance);
  console.log(props);

  return {
    component: loading_spining,
    update (options) {
      loading_spining.isShow = options.isShow;
    },
    destroy () {
      document.body.removeChild(document.getElementsByClassName('loadSpinner')[0]);
    }
  };
};

export default loadingSpining;
