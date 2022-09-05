<template>
  <div class="pagefoot">
    <nav aria-label="...">
      <ul class="pager">
        <li v-for="item in pageList" v-on:click="pageClick(item)" :class="item.class">
          <a href="javascript:void(0)">{{item.isIcon?'':item.name}}
            <i v-if="item.isIcon" :class="item.iconClass" aria-hidden="true"></i>
          </a>
        </li>
        <li>
          <a href="javascript:void(0)">
            <span aria-hidden="true">Page {{current}} / {{pageAll}}</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  props: {
    currentPage: {
      type: Number,
      require: true
    },
    pageAll: {
      type: Number,
      require: true
    }
  },
  name: 'article-page',
  data () {
    return {
      itemAll: -1,
      current: this.currentPage,
      pageList: [
        {
          id: 'home',
          name: '首页',
          class: 'previous',
          isIcon: true,
          iconClass: 'fa fa-step-backward'
        }, {
          id: 'last',
          name: '最后一页',
          class: 'next',
          isIcon: true,
          iconClass: 'fa fa-step-forward'
        }, {
          id: 'previous',
          name: '上一页',
          class: 'previous',
          isIcon: false,
          iconClass: ''
        }, {
          id: 'next',
          name: '下一页',
          class: 'next',
          isIcon: false,
          iconClass: ''
        }
      ]
    }
  },
  methods: {
    pageClick: function (item) {
      const id = item.id;
      let message;
      switch (id) {
        case 'home':
          this.current = 1; message = '您点击了首页'; break;
        case 'last':
          this.current = this.pageAll; message = '您点击了最后一页'; break;
        case 'previous':
          message = '已经是第一页啦，无法在往前了';
          if (this.current > 1) {
            message = '您点击的上一页';
            this.current = this.current - 1;
          };
          break;
        case 'next':
          message = '已经是最后一页啦，无法在往后了';
          if (this.current < this.pageAll) {
            message = '您点击的下一页';
            this.current = this.current + 1;
          }; break;
      };
      this.$emit('pageprocss', {
        current: this.current,
        message: message
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.pager{
  margin: 0;
}
.pagefoot{
  background-color: #fff;
  text-align: center;
  padding: 6px;
  margin-top: -10px;
}
.pager li a{
  border: 1px solid #fff;
  color: #616161;
}

</style>
