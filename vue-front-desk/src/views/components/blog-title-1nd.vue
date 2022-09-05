<template>
  <div>
    <div v-if="navList" class="main-nav">
      <ul>
        <li v-for="nav in navList">
          <router-link :to="nav.router" replace>{{nav.name}}</router-link>
        </li>
      </ul>
    </div>
    <div class="introduce-title">
      <h1>{{pageTitle}}</h1>
      <p style="margin-bottom:600px;" class="subline">{{pageDesc}}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ['navList', 'pageTitle', 'pageDesc'],
  name: 'nav-title',
  data () {
    return {

    }
  },
  mounted () {

  },
  methods: {

  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main-nav{
  position: absolute;
  top: 70%;
  width: 100%;
  text-align: center;
  z-index: 9;
}
.main-nav ul{
  padding: 0px;
}
.main-nav li{
  display: inline-block;
  margin: 0 15px;
}
.main-nav li a{
  color: #fff;
  border-bottom: 1px solid #fff;
  padding-bottom: 5px;
  font-weight: 300;
  font-style: initial;
  font-size: 16px;
  transition: all .4s;
  -webkit-transition: all .4s;
}
.introduce-title{
  position: absolute;
  text-align: center;
  margin: 0 auto;
  width: 100%;
  height: 100%;
  color: #fff;
  z-index: 1;
  transform: translate3d(0,20%,0);
  -webkit-transform: translate3d(0,20%,0);
  -moz-transform: translate3d(0,20%,0);
}
.introduce-title, .introduce-title *{
  transition: all .4s ease;
  -webkit-transition: all .4s ease;
  -moz-transition: all .4s ease;
}

.slide .main-nav ul li a{
  color: #444;
  border-bottom: 1px solid #222;
  font-size: 20px;
  font-weight: 300;
}
</style>
