<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="文章" min-width="200">
      <template slot-scope="scope">
        {{ scope.row.articleTitle }}
      </template>
    </el-table-column>
    <el-table-column label="点击数" width="195" align="center">
      <template slot-scope="scope">
        {{ scope.row.clickRate }}
      </template>
    </el-table-column>
    <el-table-column label="点赞数" width="100" align="center">
      <template slot-scope="{row}">
        <el-tag :type="row.status">
          {{ row.numberLike }}
        </el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { getRankList } from '@/api/article/article'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getRankList().then(response => {
        this.list = response.data
      })
    }
  }
}
</script>
