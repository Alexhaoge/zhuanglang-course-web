<template>
  <el-button type="danger" icon="el-icon-delete" style="float:right; margin:1rem;"
    circle @click="deleteLesson()"></el-button>
</template>

<script>
export default {
  name: 'delLesson',
  props: {
    lid: {type: Number},
    sons: {type: Number}
  },
  data () {
    return {

    }
  },
  methods: {
    deleteLesson () {
      this.$confirm('此操作将永久删除该小节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.sons > 0) {
          console.log('sons: ' + this.sons)
          this.$alert('请先删除该小节中所含资源', '无法删除', { confirmButtonText: '确定' })
        } else {
          this.$axios
            .post('/delete/library/lesson', {id: this.lid}).then(resp => {
              if (resp && resp.status === 200) {
                this.$message({type: 'success', message: '删除成功'})
                this.$store.commit('updateCategory', {updateCat: true})
                this.$emit('onSubmit')
              } else { this.$message.error('删除失败') }
            })
        }
      }).catch(() => { this.$message({type: 'info', message: '已取消删除'}) })
    }
  }
}
</script>

<style>

</style>
