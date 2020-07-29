<!--  -->
<template>
  <div>
    <Header></Header>

    <div class="form">
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-form-item label="摘要" prop="description">
          <el-input v-model="ruleForm.description"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <mavon-editor v-model="ruleForm.content" />
          <!-- <el-input v-model="ruleForm.name"></el-input> -->
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >发表</el-button
          >
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import Header from "@/components/Header";
import { connect } from "tls";
export default {
  //import引入的组件需要注入到对象中才能使用
  components: { Header },
  data() {
    return {
      ruleForm: {
        id: null,
        title: "",
        description: "",
        content: ""
      },
      rules: {
        title: [
          { required: true, message: "请输入标题", trigger: "blur" },
          { min: 3, max: 20, message: "长度在 3 到 5 个字符", trigger: "blur" }
        ],
        description: [
          { required: true, message: "请输入摘要", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    //提交表单数据
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios
            .post("/blog/edit", this.ruleForm, {
              headers: {
                Authorization: localStorage.getItem("token")
              }
            })
            .then(res => {
              this.$alert("操作成功", "提示", {
                confirmButtonText: "确定",
                callback: action => {
                  this.$router.push("/blogs");
                }
              });
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //重置表单数据
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //获取回显数据
    getBackList() {
      const blogId = this.$route.params.blogId;
      if (blogId) {
        this.$axios.get("/blog/" + blogId).then(res => {
          const blog = res.data.data;
          this.ruleForm.id = blog.id;
          this.ruleForm.title = blog.title;
          this.ruleForm.description = blog.description;
          this.ruleForm.content = blog.content;
        });
      }
    }
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    this.getBackList();
  },
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {} //如果页面有keep-alive缓存功能，这个函数会触发
};
</script >
<style lang='scss' scoped>
</style>