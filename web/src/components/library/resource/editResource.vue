<template>
  <div>
    <el-button type="primary" icon="el-icon-edit" class="card-button"
    @click="dialogFormVisible = true"></el-button>
    <el-dialog
      title="修改资源"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form :model="erForm" style="text-align: left"
        ref="erForm" :rules="rules" :inline-message="true">
        <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="erForm.name" autocomplete="off" placeholder="资源名称"></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth" prop="note">
          <el-input type="textarea" v-model="erForm.note" autocomplete="off" placeholder="资源简介"></el-input>
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
  name: 'editResource',
  props: {
    rid: {type: Number}
  },
  data () {
    return {
      erForm: {name: '', note: ''},
      formLabelWidth: '120px',
      dialogFormVisible: false,
      rules: {
        name: [
          { required: true, message: '请输入资源名称', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        note: [{ min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur' }]
      }
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('/edit/library/resource', {
        id: this.rid,
        name: this.erForm.name,
        note: this.erForm.note
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message({type: 'success', message: '编辑成功'})
          this.dialogFormVisible = false
          this.$emit('onSubmit')
        }
      })
    },
    clear () {
      this.erForm = {name: '', note: ''}
    }
  }
}
</script>

<style>

</style>
