import loadingSpining from './loading-spining';
let loadingSpinInstance;

function getInstance () {
	loadingSpinInstance = loadingSpinInstance || loadingSpining.newInstance({
      isShow: false
  });
	return loadingSpinInstance;
}

function update (options) {
    let instance = getInstance();

    instance.update(options);
}

export default {
	show () {
		update({
      isShow: true
    });
	},

	hide () {
		update({
      isShow: false
    });
	}
}
