<template>
  <div id="view-body" class="view-body view-body-life">
    <div class="row">
      <div class="col-sm-6 col-md-3 video-left">
        <life-item :life-list="getLifeList(0)"></life-item>
      </div>
      <div class="col-sm-6 col-md-3 video-center">
        <life-item :life-list="getLifeList(2)"></life-item>
      </div>
      <div class="col-sm-6 col-md-3 video-center">
        <life-item :life-list="getLifeList(1)"></life-item>
      </div>
      <div class="col-sm-6 col-md-3 video-right">
        <life-item :life-list="getLifeList(3)"></life-item>
      </div>
    </div>
    <blog-foot></blog-foot>
  </div>
</template>

<script>
import blogFoot from '@/views/components/blog-foot.vue';
import lifeItem from '@/views/components/life-item.vue';
import {list} from '@/api/summary'

export default {
  name: 'life',
  components: {
    blogFoot: blogFoot,
    lifeItem: lifeItem
  },
  data() {
    return {
      lifeList: []
    }
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      list().then(res => {
        this.lifeList = res.data
      })
    },
    getLifeList(lifeIndex) {
      return this.lifeList.filter(function (item, index) {
        return index % 4 == lifeIndex;
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.view-body-life {
  padding-top: 60px;
}

@media (max-width: 540px) {
  .view-body-life {
    padding: 60px 4% 2% 4%;
  }
}

</style>
