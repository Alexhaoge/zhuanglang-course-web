<template>
  <div style="float:right">
    <el-button type="info" style="float:right; margin-right:5rem;"
      @click="clickshow">添加教学资源</el-button>
    <el-dialog
      title="添加资源"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form :model="form" style="text-align: left"
        ref="dataForm" :rules="rules" :inline-message="true">
        <el-form-item label="名称" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off" placeholder="资源名称"></el-input>
        </el-form-item>
        <el-form-item label="文件" :label-width="formLabelWidth">
          <el-upload ref="upload"
            :limit="1" multiple
            :on-exceed="handleExceed"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :on-success="handleSuccess"
            :file-list="fileList"
            :action="getUrl()">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth" prop="note">
          <el-input type="textarea" v-model="form.note" autocomplete="off" placeholder="资源简介"></el-input>
        </el-form-item>
        <el-form-item label="类型" :label-width="formLabelWidth" prop="vorS">
          <el-select v-model="form.vorS" placeholder="请选择资源类型" @change="changeBilibili">
            <el-option label="视频" :value="'VIDEO'"></el-option>
            <el-option label="课件" :value="'SLIDE'"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="B站bv视频号" :label-width="formLabelWidth" prop="bilibili">
          <el-input v-model="form.bilibili" :disabled="!bilibiliVisible" autocomplete="off" placeholder='B站bv号'></el-input>
        </el-form-item>
        <el-form-item prop="id" style="height: 0">
          <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = bilibiliVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'newResource',
  props: {
    belongID: {type: Number}
  },
  data () {
    return {
      dialogFormVisible: false,
      bilibiliVisible: false,
      form: {id: null, name: '', note: '', path: '', vorS: '', bilibili: ''},
      formLabelWidth: '120px',
      fileList: [],
      rules: {
        name: [{ required: true, message: '请输入资源名称', trigger: 'blur' }, { min: 1, max: 10, message: '长度在 1 到 10 个字符' }],
        vorS: [{ required: true, message: '请选择资源类型', trigger: ['blur', 'change'] }],
        note: [{ min: 1, max: 255, message: '长度在 1 到 255 个字符' }],
        bilibili: [{ min: 12, max: 12, message: '请输入正确的bv号（12个字符）' }]
      }
    }
  },
  methods: {
    clear () {
      if (this.form.path !== '') {
        this.handleRemove(null, null)
      }
      this.form = {id: null, name: '', note: '', path: '', vorS: '', bilibili: ''}
      this.$refs.upload.clearFiles()
    },
    onSubmit () {
      this.$axios
        .post('/new/library/resource', {
          id: this.form.id,
          name: this.form.name,
          path: this.form.path,
          note: this.form.note,
          vorS: this.form.vorS,
          bilibili: this.form.bilibili,
          belong: {id: this.belongID},
          upload: null,
          md5: null
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.$message({type: 'success', message: '资源添加成功'})
            this.form.path = ''
            this.dialogFormVisible = false
            this.$emit('onSubmit')
          }
        })
    },
    changeBilibili () {
      if (this.form.vorS === 'VIDEO') {
        this.bilibiliVisible = true
      } else {
        this.bilibiliVisible = false
        this.form.bilibili = ''
      }
    },
    handleExceed (files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    handleSuccess (response) {
      this.form.path = response
    },
    handleRemove (file, fileList) {
      this.$axios.post('/uploaddelete', this.form.path).then(resp => {
        // console.log(resp)
        if (resp && resp.status === 200 && resp.data.code === 200) {
          this.$message({type: 'success', message: '删除成功'})
          this.form.path = ''
        } else { this.$message.error('删除失败') }
      })
    },
    getUrl () {
      var url = process.env.BASE_URI + '/upload/' + this.$store.state.subject + '/' + this.$store.state.bookID + '/' + this.$store.state.sectionID + '/' + this.$store.state.lessonID
      return url
    },
    clickshow () {
      if (this.$store.state.username === '' || this.$store.state.username == null) {
        this.dialogFormVisible = false
      } else { this.dialogFormVisible = true }
    }
  }
}
</script>

<style>

</style>
