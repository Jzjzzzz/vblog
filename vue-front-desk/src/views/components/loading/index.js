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
		console.log(loadingSpining);
		console.log('开始渲染了');
		update({
      isShow: true
    });
	},

	hide () {
		console.log('隐藏了');
		update({
      isShow: false
    });
	}
}
