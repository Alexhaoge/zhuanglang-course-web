<template>
  <div style="float: right; margin: 1rem;">
    <el-button type="primary" icon="el-icon-edit"
      circle @click="dialogFormVisible = true"></el-button>
    <el-dialog
      title="修改模块"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form :model="esForm" style="text-align: left"
        ref="esForm" :rules="rules" :inline-message="true">
        <el-form-item label="模块编号" :label-width="formLabelWidth" prop="number">
          <el-input v-model="esForm.number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth" prop="note">
          <el-input type="textarea" v-model="esForm.note" autocomplete="off" placeholder="资源简介"></el-input>
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
  name: 'editSection',
  props: {
    sid: {type: Number}
  },
  data () {
    return {
      esForm: {number: null, note: ''},
      formLabelWidth: '120px',
      dialogFormVisible: false,
      rules: {
        number: [
          { required: true, message: '模块编号不为空', trigger: 'blur' },
          { type: 'number', message: '请输入数字' }
        ],
        note: [{ min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur' }]
      }
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('/library/section/edit', {
        id: this.sid,
        number: this.esForm.number,
        note: this.esForm.note
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.dialogFormVisible = false
          this.$store.commit('updateCategory', {updateCat: true})
          this.$emit('onSubmit')
          this.$message({type: 'success', message: '编辑成功'})
        }
      })
    },
    clear () {
      this.esForm = {number: null, note: ''}
    }
  }
}
</script>

<style>

</style>
