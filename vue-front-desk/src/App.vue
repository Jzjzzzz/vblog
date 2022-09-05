<template>
  <div id="app" class="conetent" :class="headCoverSilde?'slide':''">
    <nav-header v-show="isShowNav" :nav-list="navList" v-on:slidechange="headSilde"></nav-header>
    <router-view/>

  </div>
</template>

<script>
import navHeader from './views/components/blog-header.vue';
import config from '@/config/blog-config.json'

export default {
  name: 'App',
  components: {
    navHeader
  },
  data () {
    return {
      headCoverSilde: false,
      isShowNav: true,
      navList: [],
      noNavList: [{
        name: 'index',
        router: '/'
      }]
    }
  },
  methods: {
    headSilde (data) {
      this.headCoverSilde = data;
    },
    routerChange (oldRoute, newRoute) {
      console.log(newRoute);
      const routerItem = this.noNavList.find(value => {
        return newRoute.name === value.name;
      });
      if (!routerItem) {
        this.isShowNav = true;
        return;
      }
      this.isShowNav = false;
      console.log(this.isShowNav);
    }
  },
  watch: {
    $route (to, from) {
      this.routerChange(from, to);
    }
  },
  created: function () {
    this.navList = config.nav.navList;
    this.navList.forEach(item => {
      // 这里因为webpack的图片打包的原因，需要重新require一下图片的地址进行加载
      item.icon = require('./' + item.icon)
    })
    this.routerChange(null, this.$route);
  }
}
</script>

<style>
</style>
