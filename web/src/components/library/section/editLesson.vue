<template>
  <div style="float: right; margin: 1rem;">
    <el-button type="primary" icon="el-icon-edit"
      circle @click="dialogFormVisible = true"></el-button>
    <el-dialog
      title="修改小节"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form :model="elForm" style="text-align: left"
        ref="elForm" :rules="rules" :inline-message="true">
        <el-form-item label="小节编号" :label-width="formLabelWidth" prop="number">
          <el-input v-model="elForm.number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth" prop="note">
          <el-input type="textarea" v-model="elForm.note" autocomplete="off" placeholder="资源简介"></el-input>
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
  name: 'editLesson',
  props: {
    lid: {type: Number}
  },
  data () {
    return {
      elForm: {number: null, note: ''},
      formLabelWidth: '120px',
      dialogFormVisible: false,
      rules: {
        number: [
          { required: true, message: '小节编号不为空', trigger: 'blur' },
          { type: 'number', message: '请输入数字' }
        ],
        note: [{ min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur' }]
      }
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('/library/lesson/edit', {
        id: this.lid,
        number: this.elForm.number,
        note: this.elForm.note
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$message({type: 'success', message: '编辑成功'})
          this.dialogFormVisible = false
          this.$store.commit('updateCategory', {updateCat: true})
          this.$emit('onSubmit')
        }
      })
    },
    clear () {
      this.elForm = {number: null, note: ''}
    }
  }
}
</script>

<style>

</style>
