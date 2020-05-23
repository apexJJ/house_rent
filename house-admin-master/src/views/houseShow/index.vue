<template>
  <div>
    <YHeader></YHeader>
    <div class="distance">
      <div class="header1">
        <span>房屋风景展示</span>
      </div>
      <el-card>
        <el-carousel :interval="2000" type="card">
          <el-carousel-item v-for="item in dataimg" :key="item">
            <div class="grid-content">
              <el-col :md="12" :offset="6">
                <div>
                  <img :src="item.src" />
                  <p>{{ item.txt }}</p>
                </div>
              </el-col>
            </div>
          </el-carousel-item>
        </el-carousel>
      </el-card>
    </div>
    <div class="distance">
      <houseHot></houseHot>
    </div>
  </div>
</template>

<script>
import YHeader from './common/header'
import houseHot from './common/houseHot'
export default {
  name: '',
  data() {
    return {
      dataimg: [
        {
          src: require('../../assets/image/广告1.jpg')
        },
        {
          src: require('../../assets/image/广告2.jpg')
        },
        {
          src: require('../../assets/image/广告3.jpg')
        },
        {
          src: require('../../assets/image/广告4.jpg')
        }
      ]
    }
  },
  components: {
    YHeader,
    houseHot
  }
}
</script>

<style lang="scss" scoped>
.header1 {
  background-color: #40c9c6;
}
$mgTop: 30px;
@mixin shadow {
  box-shadow: 0 0 10px #e2e2e2;
}
.distance {
  @include shadow();
  margin: 26px 26px 0 26px;
}
</style>
