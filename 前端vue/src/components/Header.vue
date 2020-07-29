<!--  -->
<template>
  <div class="content">
    <h3>欢迎来到个人博客</h3>
    <div>
      <div class="demo-basic--circle">
        <div class="block">
          <el-avatar :size="50" :src="user.avater"></el-avatar>
          <div>{{ user.username }}</div>
        </div>
      </div>
    </div>
    <div class="nav1">
      <span><el-link type="primary" href="/blogs">主页</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span><el-link type="success" href="/blog/add">发表文章</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span>
        <el-link v-show="hasLogin" type="danger" @click="logout">退出</el-link>
        <el-link v-show="!hasLogin" type="primary" href="/login">登录</el-link>
      </span>
    </div>
    
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  data() {
    //这里存放数据
    return {
      user: {
        username: "请登录",
        avater:
          "https://bit-images.bj.bcebos.com/bit-new/file/20200725/kip2.jpeg"
      },
      hasLogin: false
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    logout() {
      this.$axios
        .get("/logout", {
          headers: { authorization: localStorage.getItem("token") }
        })
        .then(res => {
          this.$store.commit("REMOVE_INFO");
          this.$router.push("/login");
        });
    }
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username;
      this.user.avater = this.$store.getters.getUser.avater;
      this.hasLogin = true;
    }
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
<style >
.nav1 {
  margin: 10px 0;
}
.content {
  margin: 0 auto;
  
}
</style>