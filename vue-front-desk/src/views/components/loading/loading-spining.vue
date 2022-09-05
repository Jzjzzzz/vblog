<template>
  <transition name="fadeMask">
    <div v-show="isShow" class="loadSpinner">
      <div class="boxText"> 页 面 正 在 赶 来 的 路 上。</div>
      <div class="boxLoading">
      </div>
    </div>
  </transition>
</template>
<script>
export default {
  props: {
    // isShow: {
    //   type: Boolean,
    //   default: false
    // }
  },
  data () {
    return {
      isShow: false
    };
  }
};
</script>
<style scoped>
.loadSpinner{
  position: fixed;
  top: 0px;
  bottom: 0px;
  left: 0px;
  right:0px;
  width: 100%;
  background-color: rgba(255,255,255,.99);
  z-index: 10000;
}
.boxText{
  position: absolute;
  left: 0px;
  top: 0px;
  right: 0px;
  bottom: 0px;
  margin: auto;
  height: 150px;
  width: 200px;
  text-align: center;
  color:#708090;
}
.boxLoading {
    width: 50px;
    height: 50px;
    margin: auto;
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
}
.boxLoading::before{
  content: '';
  width: 50px;
  height: 5px;
  background: #000;
  opacity: 0.1;
  position: absolute;
  top: 59px;
  left: 0;
  border-radius: 50%;
  animation: shadow .5s linear infinite;
}
.boxLoading::after{
  content: '';
  width: 50px;
  height: 50px;
  background: #708090;
  animation: animate .5s linear infinite;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 3px;
}
@keyframes animate {
  17% {
    border-bottom-right-radius: 3px;
  }
  25% {
    transform: translateY(9px) rotate(22.5deg);
  }
  50% {
    transform: translateY(18px) scale(1, .9) rotate(45deg);
    border-bottom-right-radius: 40px;
  }
  75% {
    transform: translateY(9px) rotate(67.5deg);
  }
  100% {
    transform: translateY(0) rotate(90deg);
  }
}
</style>
