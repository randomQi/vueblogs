<!--  -->
import { log } from 'util';
import { connect } from 'tls';
<template>
  <div>
    <el-container>
      <el-header>
        <!-- <div class="tou"> -->
        <el-avatar
          class="im"
          :size="60"
          src="https://bit-images.bj.bcebos.com/bit-new/file/20200725/kip2.jpeg"
        >
        </el-avatar
        ><br />

        <!-- <div>个人简易博客</div> -->
        <!-- </div>  -->
      </el-header>
      <el-main>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="用户名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        ruleForm: {
          username: 'admin',
          password: '111111',
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'change' }
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const that=this;
              this.$axios.post("/login",this.ruleForm).then(
                res=>{
                  const jwt=res.headers['authorization'];
                  const userInfo=res.data.data;
                  that.$store.commit('SET_TOKEN',jwt);
                  that.$store.commit('SET_USERINFO',userInfo);
                  console.log(this.$store.getters.getUser);
                  this.$router.push("/blogs")
                  
                }
              )
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  /* line-height: 20px; */
  height: 80px;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  /* background-color: #E9EEF3; */
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
.tou {
  text-align: center;
}
.im {
  margin-top: 10px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.demo-ruleForm{
    max-width: 500px;
    margin: 0  auto;
}

</style>