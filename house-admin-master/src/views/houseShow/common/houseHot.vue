<template>
  <div class="" id="box">
    <div class="header1">
      <span>热门房源</span>
      <div style="float:right;">
        <el-link
          type="danger"
          icon="el-icon-s-home"
          @click="goto('/allhouse', 'AllHouse')"
          >查看更多</el-link
        >
      </div>
    </div>
    <el-card>
      <ul>
        <li v-for="v in json.list">
          <img
            v-bind:src="v.src"
            alt="图片加载不出来"
            style="width:450px;height:300px;"
          />
          <h4 class="font">
            <el-link
              type="primary"
              @click="goto('/houseDetail', 'HouseDetail', id), resh()"
              >{{ v.name }}</el-link
            >
          </h4>
          <ul>
            <el-tag type="success">{{ v.des }}</el-tag>
            <el-tag type="info">{{ v.info }}</el-tag>
            <el-tag type="danger">{{ v.price }}</el-tag>
          </ul>
        </li>
      </ul>
      <ul>
        <li v-for="v in json.list2">
          <img
            v-bind:src="v.src"
            alt="图片加载不出来"
            style="width:450px;height:300px;"
          />
          <h4>
            <el-link type="primary">{{ v.name }}</el-link>
          </h4>
          <ul>
            <el-tag type="success">{{ v.des }}</el-tag>
            <el-tag type="info">{{ v.info }}</el-tag>
            <el-tag type="danger">{{ v.price }}</el-tag>
          </ul>
        </li>
      </ul>
    </el-card>
  </div>
</template>

<script>
export default {
  inject: ['reload'],
  name: '',
  data() {
    return {
      id: 18,
      json: {
        list: [
          {
            src: require('../../../assets/image/租房1.jpg'),
            name: '华莘花园   闵行-七宝 新龙路1058弄1-28号',
            des: '三室一厅 ',
            info: '建筑面积：95㎡',
            price: '1600/月'
          },
          {
            src: require('../../../assets/image/租房2.jpg'),
            name: '东兰路37弄小区   闵行-静安新城 东兰路37弄',
            des: '三室一厅 ',
            info: '建筑面积：130㎡',
            price: '1800/月'
          },
          {
            src: require('../../../assets/image/租房5.jpg'),
            name: '万科城市花园   闵行-七宝 七莘路3333弄',
            des: '四室一厅 ',
            info: '建筑面积：200㎡',
            price: '20000/月'
          }
        ],
        list2: [
          {
            src: require('../../../assets/image/租房4.jpg'),
            name: '阳光欧洲城金色米兰苑   浦东-金桥 佳乐路255弄',
            des: '三室一厅 ',
            info: '建筑面积：21㎡',
            price: '2020/月'
          },
          {
            src: require('../../../assets/image/租房3.jpg'),
            name: '沿海丽水馨庭(公寓住宅)   松江-莘闵 明中路1588弄',
            des: '三室一厅 ',
            info: '建筑面积：86㎡',
            price: '3500/月'
          },
          {
            src: require('../../../assets/image/租房6.jpg'),
            name: '东碧林湾   闵行-七宝 七莘路1890弄',
            des: '四室一厅 ',
            info: '建筑面积：21㎡',
            price: '1740/月'
          }
        ]
      }
    }
  },
  methods: {
    reload() {
      this.reload()
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
        name: name,
        params: {
          id: val
        }
      })
      var params = {
        id: val
      }
      this.Storage.Session.set('data', params)
    }
  }
}
</script>

<style>
#box ul {
  display: flex;
  flex-wrap: wrap;
}
#box li {
  padding: 3px;
  list-style: none;
  margin-right: 15px;
  border: 1px solid #eee;
}
#box img {
  width: 200px;
  height: 150px;
}

.header1 {
  background: #40c9c6;
  font-size: 25px;
  height: 50px;
}
.font {
  font-size: 20px;
}
.font2 {
  font-size: 20px;
  position: absolute;
  right: 100px;
  background: #f4516c;
}
</style>
