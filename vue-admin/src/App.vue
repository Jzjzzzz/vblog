<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  sockets: {
    broadcast: function(data) {
      this.$notify({
        title: '通知',
        dangerouslyUseHTMLString: true,
        message: data
      })
    }
  }
}
</script>
