<template>
  <div class="timeline-container">
    <ul class="timeline timeline-centered">
      <li :id="$index" v-for="(timeline,$index) in timelineList" :class="[timeline.class,timeline.isSlide?'timelineOpacity':'']" class="timeline-item">
          <div class="timeline-info">
              <span>{{timeline.date}}</span>
          </div>
          <div class="timeline-marker"></div>
          <div class="timeline-content">
              <h3 class="timeline-title">{{timeline.title}}</h3>
              <p>{{timeline.text}}</p>
          </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ['timelineList'],
  name: 'timeLine',
  data () {
    return {
      items: []
    }
  },
  mounted () {
    this.items = document.querySelectorAll('.timeline li');
    window.addEventListener('scroll', this.handleScroll);
  },
  methods: {
    handleScroll: function (e) {
      var _self = this;
      _self.callbackFunc(_self.items);
    },
    callbackFunc: function (items) {
      for (var i = 0; i < items.length; i++) {
        if (this.isElementInViewport(items[i])) {
          document.getElementById('0').classList.add('move');
          this.$set(this.timelineList[i], 'isSlide', true);
        } else {
          this.$set(this.timelineList[i], 'isSlide', false);
        }
      }
    },
    // 判断是否看得见这个timeline
    isElementInViewport: function (el) {
      var rect = el.getBoundingClientRect();
      if ((rect.top + (rect.height)) <= (window.innerHeight || document.documentElement.clientHeight)) {
        return true;
      } else {
        return false;
      }
    }
  },
  beforeDestroy () {
    console.log('时间轴组件开始销毁');
    window.removeEventListener('scroll', this.handleScroll);
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.timeline{
	line-height: 1.4em;
	list-style: none;
	margin: 0;
	padding: 0;
	width: 100%;
}
.timeline-item{
	padding-left: 40px;
	position: relative;
	cursor: pointer;
	opacity: 0;
	transition: all .4s;
	-webkit-transition: all .4s;
	-moz-transition: all .4s;
	transform: translate3d(-80%,0,0);
	-webkit-transform: translate3d(-80%,0,0);
	-moz-transform: translate3d(-80%,0,0);
}
.timeline-content{
	padding-bottom: 40px;
	overflow: hidden;
}
.timeline-info{
	font-size: 12px;
	font-weight: 700;
	letter-spacing: 3px;
	margin: 0 0 .5em 0;
	text-transform: uppercase;
	white-space: nowrap;
}
.timeline-marker{
	position: absolute;
	top:0px;
	bottom:0px;
	left:0px;
	width:15px;
}
.timeline-content p:last-child{
	margin-bottom: 0;
}
.period{
	padding: 0;
}
.timeline-marker:before{
	background: #FF6B6B;
	border: 3px solid transparent;
	border-radius: 100%;
	content: "";
	display: block;
	height: 15px;
	position: absolute;
	top: 4px;
	left: 0;
	width: 15px;
	transition: background 0.3s ease-in-out, border 0.3s ease-in-out;
}
/*.timeline h1, .timeline h2, .timeline h3, .timeline h4, .timeline h5, .timeline h6{
	line-height: inherit;
}*/
.timeline-marker:after{
	content: "";
	width: 3px;
	background: #CCD5DB;
	display: block;
	position: absolute;
	top: 24px;
	bottom: 0;
	left: 6px;
}
.period .timeline-content{
	padding: 40px 0 70px;
}
.period .timeline-marker:before{
	background: transparent;
	content: "";
	width: 15px;
	height: auto;
	border: none;
	border-radius: 0;
	top: 0;
	bottom: 30px;
	position: absolute;
	border-top: 3px solid #CCD5DB;
	border-bottom: 3px solid #CCD5DB;
}
.period .timeline-marker:after{
	content: "";
	height: 32px;
	top: auto;
}
.timeline-item:not(.period):hover .timeline-marker:before{
	background: transparent;
	border: 3px solid #FF6B6B;
}
.timelineOpacity{
	opacity: 1;
	transform: translate3d(0,0,0)!important;
	-webkit-transform: translate3d(0,0,0)!important;
	-moz-transform: translate3d(0,0,0)!important;
}
.timeline-container{
	overflow: hidden;
}
@media (min-width: 768px) {
  .timeline-split .timeline-item, .timeline-centered .timeline-item{
		display: table-row;
		padding: 0;
	}
	.timeline-split .timeline-marker, .timeline-centered .timeline-marker{
		position: relative;
	}
	.timeline-split .timeline-info,
	.timeline-centered .timeline-info,
	.timeline-split .timeline-marker,
	.timeline-centered .timeline-marker,
	.timeline-split .timeline-content,
	.timeline-centered .timeline-content,
	.timeline-split .period .timeline-info,
	.timeline-centered .period .timeline-info{
		display: table-cell;
		vertical-align: top;
	}

	.timeline-split .timeline-info, .timeline-centered .timeline-info{
		padding-right: 30px;
	}
	.period .timeline-title{
		margin: 0;
	}
	.timeline-split .period .timeline-title, .timeline-centered .period .timeline-title{
		position: relative;
		left: -45px;
	}
	.timeline-centered .period .timeline-marker:after{
		height: 30px;
		bottom: 0;
		top: auto;
	}
	.timeline-centered .timeline-item.period{
		padding: 50px 0 90px;
	}
	.timeline-split .timeline-content, .timeline-centered .timeline-content{
		padding-left: 30px;
	}
}
@media (min-width:992px){
	.timeline-centered,
	.timeline-centered .timeline-item,
	.timeline-centered .timeline-info,
	.timeline-centered .timeline-marker,
	.timeline-centered .timeline-content{
		display: block;
		margin: 0;
		padding: 0;
	}
	.timeline-centered .period .timeline-title{
		left: auto;
	}
	.timeline-centered .timeline-marker{
		position: absolute;
		left: 50%;
		margin-left: -7.5px;
	}
	.timeline-centered .timeline-info, .timeline-centered .timeline-content{
		width: 50%;
	}
	.timeline-centered > .timeline-item:nth-child(odd) .timeline-content{
		float: right;
		text-align: left;
		padding-left: 30px;
	}
	.timeline-centered > .timeline-item:nth-child(odd) .timeline-info{
		float: left;
		text-align: right;
		padding-right: 30px;
	}
	.timeline-centered > .timeline-item:nth-child(even) .timeline-content{
		float: left;
		text-align: right;
		padding-right: 30px;
	}
	.timeline-centered > .timeline-item:nth-child(even) .timeline-info{
		float: right;
		text-align: left;
		padding-left: 30px;
	}
	.timeline-centered > .timeline-item.period .timeline-content{
		float: none;
		padding: 0;
		width: 100%;
		text-align: center;
	}
	.timeline-centered .timeline-item{
		padding-bottom: 40px;
		overflow: hidden;
	}
	.timeline-centered > .timeline-item:nth-child(even){
		transition: all .4s;
		-webkit-transition: all .4s;
		-moz-transition: all .4s;
		transform: translate3d(80%,0,0);
		-webkit-transform: translate3d(80%,0,0);
		-moz-transform: translate3d(80%,0,0);
	}
}
</style>
