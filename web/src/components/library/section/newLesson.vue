<template>
  <div style="float:right">
    <el-button type="info" style="float:right; margin-right:5rem;"
      @click="dialogFormVisible = true">添加小节</el-button>
    <el-dialog
      title="添加小节"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form :model="form" style="text-align: left"
        ref="dataForm" :rules="rules" :inline-message="true">
        <el-form-item label="小节编号" :label-width="formLabelWidth" prop="number">
          <el-input v-model="form.number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth" prop="note">
          <el-input type="textarea" v-model="form.note" autocomplete="off" placeholder="小节简介"></el-input>
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
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
  name: 'newLesson',
  props: {
    belongID: {type: Number}
  },
  data () {
    return {
      dialogFormVisible: false,
      form: {id: null, number: null, note: ''},
      formLabelWidth: '120px',
      rules: {
        number: [
          { required: true, message: '小节编号不为空', trigger: 'blur' },
          { type: 'number', message: '请输入数字' }
        ],
        note: [{ min: 1, max: 255, message: '长度在 1 到 255 个字符' }]
      }
    }
  },
  methods: {
    clear () {
      this.form = {id: null, number: null, note: ''}
    },
    onSubmit () {
      this.$axios
        .post('/library/lesson/new', {
          id: this.form.id,
          number: this.form.number,
          note: this.form.note,
          belong: {id: this.belongID},
          lesson: []
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.$message({type: 'success', message: '添加成功'})
            this.dialogFormVisible = false
            this.$store.commit('updateCategory', {updateCat: true})
            this.$emit('onSubmit')
          }
        })
    }
  }
}
</script>

<style>

</style>
