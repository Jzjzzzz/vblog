<template>
  <div class="life-content">
    <div v-for="item in lifeList" class="row-item-detail shadow">
      <div class="detail-body">
        <div class="detail-img"><img :src="item.banner">
          <div @click="jumpTo(item.id,item.name)" class="detail-title">
            <div class="title"><i class="fa fa-title" aria-hidden="true"></i> {{ item.name }}</div>
            <div class="content">
              <div class="content-flex">
                <div class="content-desc"><i class="fa fa-user" aria-hidden="true"></i> {{ item.author }}</div>
              </div>
              <div class="content-flex">
                <div class="content-desc"><i class="fa fa-calendar-o" aria-hidden="true"></i>
                  {{formatDate(new Date(item.createTime)) }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import moment from 'moment';
export default {
  props: {
    lifeList: {
      type: Array,
      require: true
    }
  },
  name: 'life-item',
  data() {
    return {}
  },
  methods: {
    formatDate(date) {
      return moment(date).format('YYYY-MM-DD');
    },
    jumpTo: function (id, name) {
      this.$router.push({
        path: `/list`,
        name: 'list',
        query: {
          summaryId: id,
          summaryName: name,
          refresh: 'true'
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.life-content {
  margin-bottom: 6px;
}

.row-item-detail {
  background-color: #fff;
  margin-bottom: 20px;
}

.detail-body {
  border-bottom: 1px solid #eee;
}

.detail-img {
  position: relative;
  overflow: hidden;
  width: 100%;
  max-height: 600px;
  cursor: pointer;
}

.detail-img img {
  width: 100%;
  height: auto;
  min-height: 180px;
  transition: transform .4s;
  -webkit-transition: transform .4s;
}

.detail-title {
  font-size: 18px;
  font-weight: 700;
  padding: 32px 48px 28px 28px;
  height: 100%;
  text-align: left;
  position: absolute;
  bottom: 0;
  width: 100%;
  opacity: 0;
  z-index: 1;
  transform: translateY(-100%);
  -webkit-transform: translateY(-100%);
  -moz-transform: translateY(-100%);
  -ms-transform: translateY(-100%);
  transition: transform .4s;
  -webkit-transition: transform .4s;
  -moz-transition: transform .4s;
}

.detail-title > .title {
  font-size: 20px;
  margin-bottom: 18px;
}

.detail-title .content {
  margin-bottom: 8px;
  display: flex;
  display: -webkit-flex;
  display: -moz-flex;
  display: -ms-flexbox;
}

.content-flex {
  flex: 1;
}

.content-flex > .content-desc {
  margin-bottom: 4px;
  font-size: 14px;
  font-weight: 300;
  color: #777;
}

.detail-img .detail-title {
  background-color: rgba(255, 255, 255, .9);
  color: #666;
}

.detail-img:hover .detail-title {
  opacity: 1;
  transform: translateY(0);
  -webkit-transform: translateY(0);
  -moz-transform: translateY(0);
  -ms-transform: translateY(0);
}

.detail-img:hover img {
  transform: scale(1.2, 1.2);
  -ms-transform: scale(1.2, 1.2); /* IE 9 */
  -moz-transform: scale(1.2, 1.2); /* Firefox */
  -webkit-transform: scale(1.2, 1.2); /* Safari 和 Chrome */
  -o-transform: scale(1.2, 1.2); /* Opera */
}

.detail-img:hover .detail-title {
  color: #666;
  opacity: 1;
}
</style>
