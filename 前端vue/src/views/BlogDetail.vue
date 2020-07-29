<!--  -->
<template>
  <div>
      <Header></Header>
      <div class="blog">
          <h3 class="ctitle"> {{blog.title}} </h3>
          <el-link icon="el-icon-edit" v-if="ownBlog" ><router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}">编辑</router-link></el-link>
          <h4>{{blog.created}}</h4>
           <el-divider></el-divider>
           <div class="markdown-body" v-html="blog.content"></div>
      </div>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import Header from "@/components/Header";
import 'github-markdown-css/github-markdown.css'
export default {
  //import引入的组件需要注入到对象中才能使用
  components: { Header },
  data() {
    //这里存放数据
    return {
        blog:{
            id:"",
            title:'',
            content:"",
            created:''
        },
        ownBlog:false,
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
      getBackList() {
      const blogId = this.$route.params.blogId;
      if (blogId) {
        this.$axios.get("/blog/" + blogId).then(res => {
          const blog = res.data.data;
          // console.log(blog);
          
          this.blog.id=blog.id;
          this.blog.title = blog.title;
          var MarkdownIt = require('markdown-it'),
                md = new MarkdownIt();
          var content=md.render(blog.content)
          this.blog.content = content;
          this.blog.created=blog.created;
          this.ownBlog=(blog.userId===this.$store.getters.getUser.id)
        });
      }
    }
  },
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
<style  scoped>
.blog{
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    margin: 0 auto;
    width: 100%;
    min-height: 700px
}
.ctitle{
    padding-top: 20px;}

</style>