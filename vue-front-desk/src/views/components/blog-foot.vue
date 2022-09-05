<template>
  <div class="footNote">
    <p>
      <span> Copyright </span><span>©</span><span> 2017 </span>
      <a href="/"> blog </a>
      <span>By Tangyida</span><span></span>
    </p>
    <p class="friendly-link">友情链接: <a v-for="link in friendlyLink" :href="link.website">{{link.name}}</a>&nbsp;&nbsp;
      <!-- <a href="https://www.ailoli.org/">Ailoli</a>&nbsp;&nbsp;
      <a href="https://aoaoao.me/">树洞</a> -->
    </p>
    <p>
      <a href="http://www.miibeian.gov.cn/" target="_blank">浙ICP备17057362号-1</a>
    </p>
  </div>
</template>

<script>
export default {
  name: 'blogFoot',
  data () {
    return {
      friendlyLink: [{
        name: 'Vue.js',
        website: 'https://cn.vuejs.org/'
      }, {
        name: 'Ailoli',
        website: 'https://www.ailoli.org/'
      }, {
        name: '树洞',
        website: 'https://aoaoao.me/'
      }]
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
