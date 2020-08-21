<template>
  <el-container direction="vertical">
  <el-row>
    <h1 class="login-title">平台教师用户注册</h1>
  </el-row >
  <el-row type="flex" justify="center">
  <el-card class="login-card" style="width:50%;">
    <!-- <div slot="header" style="display:flex; items-align:center">
      <span>用户注册</span>
    </div> -->
    <el-form slot="header"
      title="添加资源"
      style="text-align: left"
      :model="regForm"
      ref="regForm"
      :rules="rules"
      :inline-message="true">
    <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
      <el-input type="text" v-model="regForm.username"
        auto-complete="off" placeholder="请输入您的用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
      <el-input type="password" v-model="regForm.password"
        auto-complete="off" placeholder="请输入您的密码"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" :label-width="formLabelWidth" prop="checkpass">
      <el-input type="password" v-model="regForm.checkpass"
        auto-complete="off" placeholder="请确认您的密码"></el-input>
    </el-form-item>
    <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
      <el-input type="text" v-model="regForm.name"
        auto-complete="off" placeholder="请输入您的姓名"></el-input>
    </el-form-item>
    <el-form-item label="所属学校" :label-width="formLabelWidth" prop="nkuorzl">
      <el-select v-model="regForm.nkuorzl" placeholder="请选择您的学校">
        <el-option label="南开大学" :value="'NKU'"></el-option>
        <el-option label="庄浪县的中学" :value="'ZHUANGLANG'"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="工号或学号" :label-width="formLabelWidth" prop="personNo">
      <el-input type="text" v-model="regForm.personNo"
        auto-complete="off" placeholder="请输入您的工号或学号"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" :label-width="formLabelWidth" prop="mail" required>
      <el-input type="text" v-model="regForm.mail"
        auto-complete="off" placeholder="请输入您的邮箱"></el-input>
    </el-form-item>
  </el-form>
  <el-button type="primary" style="" v-on:click="register">注册</el-button>
  </el-card>
  </el-row>
  </el-container>
</template>

<script>
export default {
  name: 'register',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (value.length < 5) {
          callback(new Error('密码至少5位'))
        } else if (this.regForm.checkpass !== '') {
          this.$refs.ruleForm.validateField('checkpass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.regForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var validateMail = (rule, value, callback) => {
      var pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if (value === '') {
        callback(new Error('请输入邮箱'))
      } else if (!pattern.test(value)) {
        callback(new Error('请输入正确的邮箱'))
      } else {
        callback()
      }
    }
    return {
      formLabelWidth: '120px',
      regForm: {
        username: '',
        password: '',
        checkpass: '',
        nkuorzl: '',
        mail: '',
        name: '',
        personNo: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 16, message: '用户名字符长度必须在3~16之间' }
        ],
        password: [{ validator: validatePass, trigger: 'blur' }],
        checkpass: [{ validator: validatePass2, trigger: 'blur' }],
        nkuorzl: [{ required: true, message: '请选择您的学校', trigger: ['blur', 'change'] }],
        personNo: [{ min: 1, max: 10, message: '学号字符长度必须在1~10之间' }],
        mail: [{ validator: validateMail, trigger: 'blur' }],
        name: [
          { required: true, message: '请输入姓', trigger: 'blur' },
          { min: 1, max: 20, message: '超过20个字符' }
        ]
      }
    }
  },
  methods: {
    register () {
      var _this = this
      this.$axios.post('/register', {
        username: this.regForm.username,
        password: this.regForm.password,
        nkuorzl: this.regForm.nkuorzl,
        personNo: this.regForm.personNo,
        name: this.regForm.name,
        mail: this.regForm.mail
      }).then(resp => {
        if (resp.data.code === 200) {
          this.$alert('注册成功', '提示', {
            confirmButtonText: '确定'
          })
          _this.$router.replace('/login')
        } else {
          this.$alert(resp.data.message, '提示', {
            confirmButtonText: '确定'
          })
        }
      }).catch(failResponse => {
        console.log(failResponse)
        this.$alert('未知错误，请联系管理员', '错误', { confirmButtonText: '确定' })
      })
    }
  }
}
</script>

<style>
.el-card.login-card {
  border: unset;
  background-color: rgba(255, 255, 255, 0.8);
}
h1.login-title {
  font-family: "STKaiti", "Microsoft YaHei","微软雅黑",Arial,sans-serif;
  color: white;
  font-size: 1.5rem;
}
</style>
