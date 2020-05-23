<template>
  <div>
    <div class="header1">
      <el-row>
        <el-col :span="3">
          <el-select v-model="place" clearable placeholder="城市" size="medium">
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="price" clearable placeholder="价格" size="medium">
            <el-option
              v-for="item in options3"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="area" clearable placeholder="面积" size="medium">
            <el-option
              v-for="item in options4"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3">
          <el-select v-model="room" clearable placeholder="户型" size="medium">
            <el-option
              v-for="item in options5"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            >
            </el-option>
          </el-select>
        </el-col>

        <el-col :span="3">
          <el-select v-model="state" clearable placeholder="装修" size="medium">
            <el-option
              v-for="item in options6"
              :key="item.label"
              :label="item.label"
              :value="item.label"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="3"
          ><el-button type="primary" @click="getList()">查询</el-button></el-col
        >
        <el-col :span="5" style="float:right">
          <el-link type="danger" @click="goto('/allHouse', 'AllHouse')"
            >去房屋列表页<i
              class="el-icon-d-arrow-right
"
            ></i> </el-link
        ></el-col>
      </el-row>
    </div>
    <el-row>
      <el-col :span="12">
        <div class="amap-page-container">
          <el-amap
            vid="amapDemo"
            :zoom="zoom"
            :center="center"
            class="amap-demo"
            :plugin="plugin"
          >
            <el-amap-info-window
              v-for="mywindow in mywindows"
              :position="mywindow.position"
              :content="mywindow.content"
              :visible="mywindow.visible"
              :events="mywindow.events"
            ></el-amap-info-window>
            <el-amap-marker
              v-for="marker in markers"
              :position="marker.position"
              :events="marker.events"
              :visible="marker.visible"
              :draggable="marker.draggable"
            ></el-amap-marker>
            <el-amap-circle
              v-for="circle in circles"
              :center="circle.center"
              :radius="circle.radius"
              :fillOpacity="circle.fillOpacity"
              :events="circle.events"
              :strokeColor="circle.strokeColor"
              :strokeStyle="circle.strokeStyle"
              :fillColor="circle.fillColor"
            ></el-amap-circle>
          </el-amap>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="house-page-container">
          <span
            >共有
            <p class="ziti">{{ pagedata.totalElements }}</p>
            个符合要求的租房信息</span
          >
          <el-divider></el-divider>
          <ul v-for="v in allHouse">
            <el-row>
              <el-col :span="10">
                <img
                  v-bind:src="v.image"
                  alt="图片加载不出来"
                  style="width:180px;height:135px;"
              /></el-col>
              <el-col :span="10">
                <h4 class="font1">
                  <el-link
                    type="primary"
                    @click="goto('/houseDetail', 'HouseDetail', v.id)"
                    >{{ v.name }}</el-link
                  >
                </h4>
                <h4 class="font">{{ v.place }}</h4>
                <h4 class="font">{{ v.room }}| {{ v.area }}/m²</h4>
                <ul>
                  <img
                    src="../../../assets/image/subway1.png"
                    style="float:left"
                    alt=""
                  />
                  <el-tag>{{ v.state }}</el-tag>
                </ul>
              </el-col>
              <el-col :span="8">
                <h4 class="price">{{ v.price }}/月</h4></el-col
              >
            </el-row>
            <el-divider></el-divider>
          </ul>
        </div>
        <div style="float:right">
          <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="currentPage + 1"
            layout="prev, pager, next"
            :total="pagedata.totalElements"
          >
          </el-pagination>
          <el-divider></el-divider>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style>
.header1 {
  background-color: #40c9c6;
  font-size: 25px;
  height: 50px;
}
.amap-page-container {
  height: 1200px;
  width: 1000px;
}
.house-page-container {
  margin-left: 250px;
}
.font {
  font-size: 15px;
}

.ziti {
  font-size: 20px;
  color: #3fd847;
}
.price {
  font-size: 20px;
  color: #f4516c;
}
</style>

<script>
export default {
  data() {
    return {
      pagedata: [],
      currentPage: 0,
      pageSize: 6,
      allHouse: [
        {
          image: require('../../../assets/image/租房4.jpg'),
          name: '阳光欧洲城金色米兰苑   浦东-金桥 佳乐路255弄',
          room: '三室一厅 ',
          area: '建筑面积：21㎡',
          price: '2020/月',
          place: '上海',
          subway: '郊环外',
          state: '简装修'
        },
        {
          image: require('../../../assets/image/租房4.jpg'),
          name: '阳光欧洲城金色米兰苑   浦东-金桥 佳乐路255弄',
          room: '三室一厅 ',
          area: '建筑面积：21㎡',
          price: '2020/月',
          place: '上海',
          subway: '郊环外',
          state: '简装修'
        },
        {
          image: require('../../../assets/image/租房4.jpg'),
          name: '阳光欧洲城金色米兰苑   浦东-金桥 佳乐路255弄',
          room: '三室一厅 ',
          area: '建筑面积：21㎡',
          price: '2020/月',
          place: '上海',
          subway: '郊环外',
          state: '简装修'
        }
      ],
      //房屋条件数据
      place: '',
      price: '',
      area: '',
      room: '',
      state: '',
      options1: [
        {
          value: '选项1',
          label: '上海'
        },
        {
          value: '选项2',
          label: '北京'
        },
        {
          value: '选项3',
          label: '苏州'
        }
      ],
      options3: [
        {
          value: '选项1',
          label: '3000'
        },
        {
          value: '选项2',
          label: '5000'
        },
        {
          value: '选项3',
          label: '6000'
        },
        {
          value: '选项4',
          label: '10000'
        }
      ],
      options4: [
        {
          value: '选项1',
          label: '70'
        },
        {
          value: '选项2',
          label: '80'
        },
        {
          value: '选项3',
          label: '90'
        },
        {
          value: '选项4',
          label: '130'
        }
      ],
      options5: [
        {
          value: '选项1',
          label: '二室一厅'
        },
        {
          value: '选项2',
          label: '三室一厅'
        },
        {
          value: '选项3',
          label: '四室一厅'
        },
        {
          value: '选项4',
          label: '别墅'
        }
      ],
      options6: [
        {
          value: '选项1',
          label: '毛胚'
        },
        {
          value: '选项2',
          label: '简装修'
        },
        {
          value: '选项3',
          label: '精装修'
        }
      ],
      //地图数据
      zoom: 5,
      center: [120.62, 31.32],
      circles: [
        {
          clickable: true,
          center: [121.38, 31.12],
          radius: 50000,
          fillOpacity: 1,
          strokeStyle: 'dashed',
          fillColor: '#FFFF00',
          strokeColor: '#00BFFF'
        },
        {
          clickable: true,
          center: [116.41, 39.92],
          radius: 100000,
          fillOpacity: 1,
          strokeStyle: 'dashed',
          fillColor: '#FFFF00',
          strokeColor: '#00BFFF'
        },
        {
          clickable: true,
          center: [120.62, 31.32],
          radius: 50000,
          fillOpacity: 1,
          strokeStyle: 'dashed',
          fillColor: '#FFFF00',
          strokeColor: '#00BFFF'
        }
      ],
      markers: [
        {
          position: [121.38, 31.12],

          events: {
            click: () => {
              var vm = this
              vm.place = '上海'
              this.getList()
            },
            dragend: e => {
              this.markers[0].position = [e.lnglat.lng, e.lnglat.lat]
            }
          },
          visible: true,
          draggable: false
        },
        {
          position: [116.41, 39.92],

          events: {
            click: () => {
              var vm = this
              vm.place = '北京'
              this.getList()
            },
            dragend: e => {
              this.markers[0].position = [e.lnglat.lng, e.lnglat.lat]
            }
          },
          visible: true,
          draggable: false
        },
        {
          position: [120.62, 31.32],

          events: {
            click: () => {
              var vm = this
              vm.place = '苏州'
              this.getList()
            },
            dragend: e => {
              this.markers[0].position = [e.lnglat.lng, e.lnglat.lat]
            }
          },
          visible: true,
          draggable: false
        }
      ],
      mywindows: [
        {
          position: [121.38, 31.12],
          content: '<h4>上海市闵行区</h4><div class="ziti">5处</div>',
          visible: true,
          events: {
            close() {
              this.mywindow.visible = false
            }
          }
        },
        {
          position: [116.41, 39.92],
          content: '<h4>北京市东城区</h4><div class="ziti">3处</div>',
          visible: true,
          events: {
            close() {
              this.mywindow.visible = false
            }
          }
        },
        {
          position: [120.62, 31.32],
          content: '<h4>苏州吴中区</h4><div class="ziti">3处</div>',
          visible: true,
          events: {
            close() {
              this.mywindow.visible = false
            }
          }
        }
      ],
      plugin: {
        pName: 'Scale',
        events: {
          init(instance) {
            console.log(instance)
          }
        }
      }
    }
  },
  methods: {
    getAllList() {
      var vm = this
      this.axios({
        method: 'get',
        url: 'http://localhost:8082/api/getAllData',
        params: {
          NowPage: vm.currentPage,
          size: vm.pageSize
        }
      })
        .then(res => {
          this.allHouse = res.data.data.content
          this.pagedata = res.data.data
          console.log(this.result)
          console.log(this.pagedata)
        })
        .catch(response => {
          console.log(response)
        })
    },
    getList() {
      var vm = this
      console.log(vm.place)
      console.log(vm.price)
      this.axios({
        method: 'get',
        url: 'http://localhost:8082/api/queryData',
        params: {
          place: vm.place,
          price: vm.price,
          area: vm.area,
          room: vm.room,
          state: vm.state
        }
      })
        .then(res => {
          this.allHouse = res.data.data
          this.pagedata.totalElements = this.allHouse.length
          console.log(res)
        })
        .catch(response => {
          console.log(response)
        })
    },
    handleCurrentChange(val) {
      this.currentPage = val - 1
      this.getAllList()
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
  mounted() {
    this.getAllList()
  },
  computed: {
    // 点坐标组getter方法 通过i从点坐标组数组中获取点坐标组
    markers: function() {
      // `this` points to the vm instance
      return this.markerGroups[this.i]
    }
  }
}
</script>
