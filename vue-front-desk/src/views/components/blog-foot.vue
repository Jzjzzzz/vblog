<template>
  <div class="footNote">
    <p>
      <span> Copyright </span><span>©</span><span> 2022 </span>
      <a href="/"> Vblog </a>
      <span>By {{webConfig.webName}}</span><span></span>
    </p>
    <p>
      <a href="Jzjzzzz.icu" target="_blank">{{webConfig.recordNumber}}</a>
    </p>
  </div>
</template>

<script>
import {getInformation} from '@/api/webInformation'
export default {
  name: 'blogFoot',
  data() {
    return {
      webConfig:{
        recordNumber: '',
        webName:''
      }
    }
  },
  created() {
    this.getConfig()
  },
  methods: {
    getConfig() {
      getInformation().then(res => {
        this.webConfig = res.data
      })
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
