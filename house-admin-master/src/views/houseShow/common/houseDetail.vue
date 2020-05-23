<template>
  <div>
    <YHeader></YHeader>
    <div class="distance">
      <div class="header1">
        <span>房屋详情</span>
      </div>
      <el-card>
        <el-divider></el-divider>
        <ul v-for="v in housedetail">
          <el-row>
            <el-col :span="11">
              <img
                v-bind:src="v.image"
                alt="图片加载不出来"
                style="width:400px;height:350px;"
            /></el-col>
            <el-col :span="10">
              <h4 class="ziti">
                {{ v.name }}
              </h4>
              <h4 class="price">{{ v.price }}/月</h4>

              <ul style="margin-top:10px">
                <img
                  src="../../../assets/image/subway1.png"
                  style="float:left"
                  alt=""
                />
                <el-tag type="success">{{ v.subway }}</el-tag>
                <el-tag>{{ v.state }}</el-tag>
                <el-tag type="warning">{{ v.direction }}</el-tag>
              </ul>
              <h4 class="ziti">位置：{{ v.place }}</h4>
              <h4 class="ziti">户型：{{ v.room }}| 面积：{{ v.area }}/m²</h4>
              <h4 class="ziti">交房：{{ v.createTime }}</h4>
              <div style="margin-top:10px">
                <el-button type="primary" @click="yuyue">马上预约</el-button>
              </div>
            </el-col>
          </el-row>
        </ul>
      </el-card>
    </div>
  </div>
</template>

<script>
import YHeader from './header'
export default {
  name: '',
  data() {
    return {
      housedetail: []
    }
  },
  methods: {
    getDetail() {
      this.axios({
        method: 'get',
        url: 'http://localhost:8082/api/houseDetail',
        params: {
          id: this.id
        }
      })
        .then(res => {
          this.housedetail = res.data.data
          console.log(this.housedetail)
        })
        .catch(response => {
          console.log(response)
        })
    },
    message(m) {
      this.$message.success({
        message: m
      })
    },
    yuyue() {
      this.message('预约成功')
    },
    //页面的刷新
    resh() {
      this.$router.go(0)
    },
    //页面跳转
    goto(path, name, val) {
      this.$router.replace({ name: 'name' })
      this.$router.push({
        path: path,
        name: name
      })
      var params = {
        id: val
      }
      this.Storage.Session.set('data', params)
    }
  },
  components: {
    YHeader
  },
  created() {
    this.id = this.$route.params.id
    console.log(this.id)
    this.getDetail()
  }
}
</script>

<style lang="scss" scoped>
.header1 {
  background-color: #40c9c6;
  font-size: 25px;
  height: 30px;
}
$mgTop: 30px;
@mixin shadow {
  box-shadow: 0 0 10px #e2e2e2;
}
.distance {
  @include shadow();
  margin: 26px 26px 0 250px; //上右下左
  width: 1000px;
}
.font {
  font-size: 20px;
}

.ziti {
  font-size: 25px;
  margin-top: 10px;
}
.price {
  font-size: 30px;
  color: #f4516c;
}
</style>
