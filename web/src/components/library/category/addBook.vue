<template>
  <div style="margin: 2rem;">
    <el-button type="primary" @click="dialogFormVisible = true">添加书目</el-button>
    <el-dialog
      title="添加书籍"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form :model="ebForm" style="text-align: left"
        ref="ebForm" :rules="rules" :inline-message="true">
        <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="ebForm.name" autocomplete="off" placeholder="书名"></el-input>
        </el-form-item>
        <el-form-item label="学科" :label-width="formLabelWidth" prop="subject">
          <el-input disabled autocomplete="off" :placeholder="subject"></el-input>
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="ebForm.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'delResource',
  data () {
    return {
      ebForm: {name: '', id: null},
      formLabelWidth: '120px',
      dialogFormVisible: false,
      rules: {
        name: [
          { required: true, message: '请输入书名', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符' }
        ]
      }
    }
  },
  computed: {
    subject () {
      return this.$store.state.subject
    }
  },
  methods: {
    onSubmit () {
      this.$confirm('书籍一旦添加无法从前台修改或删除，请确认书名无误后, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/new/library/book', {
          id: this.ebForm.id,
          name: this.ebForm.name,
          discipline: this.$store.state.subject
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.$message({type: 'success', message: '添加成功'})
            this.dialogFormVisible = false
            this.$emit('onSubmit')
          }
        })
      }).catch(() => { this.$message({type: 'info', message: '已取消添加'}) })
    },
    clear () {
      this.ebForm = {name: ''}
    }
  }
}
</script>

<style>

</style>
