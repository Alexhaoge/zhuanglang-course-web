<template>
  <el-button type="danger" icon="el-icon-delete" class="card-button"
          @click="deleteResource()"></el-button>
</template>

<script>
export default {
  name: 'delResource',
  props: {
    rid: {type: Number}
  },
  data () {
    return {

    }
  },
  methods: {
    deleteResource () {
      this.$confirm('此操作将永久删除该教学资源, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/library/resource/delete', {id: this.rid}).then(resp => {
            if (resp && resp.status === 200) {
              this.$emit('onSubmit')
              this.$message({type: 'success', message: '删除成功'})
            } else { this.$message.error('删除失败') }
          })
      }).catch(() => { this.$message({type: 'info', message: '已取消删除'}) })
    }
  }
}
</script>

<style>

</style>
