

<!--  -->
<template>
  <div>
    <Header></Header>
    <div class="block">
      <el-timeline  >
        <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs" :key="blog.id">
          <el-card>
              
            <h4><router-link :to="{name:'BlogDetail',params:{blogId:blog.id}}">
                {{blog.title}}</router-link></h4>
            <p>{{blog.content}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
    <el-pagination background layout="prev, pager, next"
    :current-page="currentPage"
    :page-size="pageSize"
     :total="total"
     @current-change="getList"
     >
    </el-pagination>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import Header from "@/components/Header";
import { thistle } from 'color-name';
import { readSync } from 'fs';

export default {
  //import引入的组件需要注入到对象中才能使用
  components: { Header },
  data() {
    //这里存放数据
    return {
        blogs:[],
        currentPage:1,
        total:0,
        pageSize:5,//每页的数量
        // page:0,//多少页
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
      getList(currentPage){
          this.$axios.get("/blogs?currentPage="+currentPage).then(
              res=>{
                  // console.log(res);
                  
                  this.blogs=res.data.data.records;
                  this.total=res.data.data.total;
                  this.currentPage=res.data.data.current;
                  this.pageSize=res.data.data.size;
              }
          )
      }
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {
      this.getList(1);
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