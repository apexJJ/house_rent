<template>
  <div>
    <div class="header-box">
      <div>
        <header class="w">
          <div class="w-box" align="center">
            <div>
              <img style="margin-left:200px;" srcset="" class="img" />
            </div>
            <div class="right-box">
              <div class="nav-list">
                <el-autocomplete
                  icon="el-icon-search"
                  v-model="input"
                  minlength="1"
                  maxlength="100"
                  :fetch-suggestions="querySearch"
                  placeholder="请输入内容"
                  @select="handleSelect"
                >
                </el-autocomplete>
                <el-button type="success" icon="el-icon-search" @click="query()"
                  >搜索</el-button
                >
                <el-link
                  type="danger"
                  icon="el-icon-map-location"
                  @click="goto('/mapSearch', 'MapSearch')"
                  :underline="false"
                  >地图找房</el-link
                >
              </div>
              <el-dropdown class="userDd">
                <div class="userDrop">
                  <img
                    src="../../../assets/personal/user01.jpg"
                    alt="user"
                    style="border-radius: 50%;width: 40px;height: 40px;margin-left:100px;background-repeat: no-repeat"
                  />
                </div>
                <el-dropdown-menu solt="dropdown" style="margin-right:-50px">
                  <el-dropdown-item divided>
                    <a @click="_loginOut()"
                      ><i class="el-icon-s-home"></i>我的租房</a
                    >
                  </el-dropdown-item>
                  <el-dropdown-item divided>
                    <a @click="_loginOut()"
                      ><i class="el-icon-star-on"></i>我的优惠</a
                    >
                  </el-dropdown-item>
                  <el-dropdown-item divided>
                    <a @click="_loginOut()"
                      ><i class="el-icon-service"></i>售后服务</a
                    >
                  </el-dropdown-item>
                  <el-dropdown-item divided>
                    <a @click="_loginOut()"
                      ><i class="el-icon-switch-button"></i>退出</a
                    >
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              <div>
                <img style="margin-left:200px;background-repeat: no-repeat" />
              </div>
            </div>
          </div>
        </header>
      </div>
    </div>
    <div class="distance">
      <div class="header1">
        <span>房源信息</span>
      </div>
      <el-card>
        <span
          >共有
          <p class="ziti">{{ pagedata.totalElements }}</p>
          个符合要求的租房信息</span
        >
        <el-divider></el-divider>
        <ul v-for="v in allHouse">
          <el-row>
            <el-col :span="5">
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
                <el-tag type="success">{{ v.subway }}</el-tag>
                <el-tag>{{ v.state }}</el-tag>
              </ul>
            </el-col>
            <el-col :span="8">
              <h4 class="price">{{ v.price }}/月</h4></el-col
            >
          </el-row>
          <el-divider></el-divider>
        </ul>
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
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  inject: ['reload'],
  name: '',
  input: '',
  data() {
    return {
      pagedata: [],
      currentPage: 0,
      pageSize: 6,
      count: 5,
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
      place: '',
      subway: '',
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
        },
        {
          value: '选项4',
          label: '重庆'
        }
      ],
      options2: [
        {
          value: '选项1',
          label: '1号线'
        },
        {
          value: '选项2',
          label: '3号线'
        },
        {
          value: '选项3',
          label: '4号线'
        },
        {
          value: '选项4',
          label: '10号线'
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
          label: '70m²'
        },
        {
          value: '选项2',
          label: '80m²'
        },
        {
          value: '选项3',
          label: '90m²'
        },
        {
          value: '选项4',
          label: '130m²'
        }
      ],
      options5: [
        {
          value: '选项1',
          label: '二室'
        },
        {
          value: '选项2',
          label: '三室'
        },
        {
          value: '选项3',
          label: '四室'
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
      ]
    }
  },

  methods: {
    query() {
      var vm = this
      console.log(vm.place)
      console.log(vm.price)
      this.axios({
        method: 'get',
        url: 'http://localhost:8082/api/queryData',
        params: {
          place: vm.input
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
    getList() {
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
    handleCurrentChange(val) {
      this.currentPage = val - 1
      this.getList()
    },
    _loginOut() {
      this.$store.dispatch('user/loginOut')
    },
    // 搜索框提示
    querySearch(queryString, cb) {
      var city = this.city
      var results = queryString
        ? city.filter(this.createFilter(queryString))
        : city
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter(queryString) {
      return restaurant => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        )
      }
    },
    loadAll() {
      return [
        { value: '上海闵行区' },
        {
          value: '北京东城区'
        },
        {
          value: '苏州吴中区'
        }
      ]
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
    },
    refresh() {
      var vm = this
      vm.reload()
    }
  },
  mounted() {
    this.city = this.loadAll()
    this.getList()
  }
}
</script>

<style lang="scss" scoped>
@import '../../../assets/style/theme';
@import '../../../assets/style/mixin';

.move_in_cart {
  animation: mymove 0.5s ease-in-out;
}
.img {
  opacity: 0.8;
}
@keyframes mymove {
  0% {
    transform: scale(1);
  }
  25% {
    transform: scale(0.8);
  }
  50% {
    transform: scale(1.2);
  }
  75% {
    transform: scale(0.9);
  }
  100% {
    transform: scale(1);
  }
}

@-moz-keyframes mymove {
  0% {
    transform: scale(1);
  }
  25% {
    transform: scale(0.8);
  }
  50% {
    transform: scale(1.2);
  }
  75% {
    transform: scale(0.9);
  }
  100% {
    transform: scale(1);
  }
}

@-webkit-keyframes mymove {
  0% {
    transform: scale(1);
  }
  25% {
    transform: scale(0.8);
  }
  50% {
    transform: scale(1.2);
  }
  75% {
    transform: scale(0.9);
  }
  100% {
    transform: scale(1);
  }
}

@-o-keyframes mymove {
  0% {
    transform: scale(1);
  }
  25% {
    transform: scale(0.8);
  }
  50% {
    transform: scale(1.2);
  }
  75% {
    transform: scale(0.9);
  }
  100% {
    transform: scale(1);
  }
}

.header-box {
  background: $head-bgc;
  background-image: url(../../../assets/image/背景.jpg);
  // background-image: -webkit-linear-gradient(#1b9e0af6, #1b9e0af6);
  // background-image: linear-gradient(#1b9e0af6, #1b9e0af6);
  width: 100%;
}

header {
  height: 250px;
  z-index: 30;
  position: relative;
}

.w-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  // position: relative;
  h1 {
    height: 100%;
    display: flex;
    align-items: center;
    > a {
      background: url(/assets/image/logo.jpg) no-repeat 50%;
      background-size: cover;
      display: block;
      @include wh(50px, 40px);
      text-indent: -9999px;
      background-position: 0 0;
    }
  }
  .nav-list {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 22px;
    .el-autocomplete {
      width: 305px;
    }
    a {
      width: 110px;
      color: #c8c8c8;
      display: block;
      font-size: 14px;
      padding: 0 25px;
      &:hover {
        color: #fff;
      }
    }
    a:nth-child(2) {
      // width: 5vw;
      margin-left: -10px;
    }
    // a:nth-child(3){
    //   width: 5vw;
    // }
  }
  .nav-aside {
    position: relative;
    &:before {
      background: #333;
      background: hsla(0, 0%, 100%, 0.2);
      content: ' ';
      @include wh(1px, 13px);
      overflow: hidden;
      // position: absolute;
      display: flex;
      align-items: center;
      // top: 4px;
      left: 0;
    }
    &.fixed {
      width: 262px;
      position: fixed;
      left: 50%;
      top: 19px;
      margin-left: 451px;
      margin-top: 0;
      z-index: 32;
      top: -40px;
      -webkit-transform: translate3d(0, 59px, 0);
      transform: translate3d(0, 59px, 0);
      -webkit-transition: -webkit-transform 0.3s
        cubic-bezier(0.165, 0.84, 0.44, 1);
      transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
      .user {
        &:hover {
          a:before {
            background-position: -215px 0;
          }
        }
      }
      .shop {
        &:hover {
          a:before {
            background-position: -210px -22px;
          }
        }
      }
    }
  }

  .right-box {
    display: flex;
  }
  .nav-aside {
    display: flex;
    align-items: center;
  }
  // 用户
  .user {
    margin-left: 41px;
    width: 36px;
    &:hover {
      a:before {
        background-position: -5px 0;
      }
      .nav-user-wrapper {
        top: 18px;
        visibility: visible;
        opacity: 1;
        -webkit-transition: opacity 0.15s ease-out;
        transition: opacity 0.15s ease-out;
      }
    }
    li + li {
      text-align: center;
      position: relative;
      border-top: 1px solid #f5f5f5;
      line-height: 44px;
      height: 44px;
      color: #616161;
      font-size: 12px;
      &:hover {
        background: #fafafa;
      }
      a {
        display: block;
        color: #616161;
      }
    }

    .nav-user-wrapper {
      right: 0;
      width: 360px;
      .nav-user-list {
        &:before {
          right: 34px;
        }
      }
    }
    .nav-user-list {
      padding: 0;
      width: 100%;
      .full {
        border-radius: 8px;
        overflow: hidden;
      }
      .nav-cart-items {
        max-height: 363px;
        overflow-x: hidden;
        overflow-y: auto;
      }
      .cart-item {
        height: 120px;
        width: 100%;
        overflow: hidden;
        border-top: 1px solid #f0f0f0;
        &:hover {
          background: #fcfcfc;
          .del {
            display: block;
          }
        }
      }
      li:first-child .cart-item:first-child {
        border-top: none;
        border-radius: 8px 8px 0 0;
        overflow: hidden;
      }
      .cart-item-inner {
        padding: 20px;
        position: relative;
      }
      .item-thumb {
        position: relative;
        float: left;
        width: 80px;
        height: 80px;
        border-radius: 3px;
        &:before {
          content: '';
          position: absolute;
          left: 0;
          right: 0;
          top: 0;
          bottom: 0;
          z-index: 2;
          border: 1px solid #f0f0f0;
          border: 0 solid transparent;
          box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0.06);
          border-radius: 3px;
        }
        img {
          display: block;
          @include wh(80px, 80px);
          border-radius: 3px;
          overflow: hidden;
        }
      }
      .item-desc {
        margin-left: 98px;
        display: table;
        @include wh(205px, 80px);
        h4 {
          color: #000;
          width: 185px;
          overflow: hidden;
          word-break: keep-all;
          white-space: nowrap;
          text-overflow: ellipsis;
          font-size: 14px;
          line-height: 16px;
          margin-bottom: 10px;
        }
        .attrs span {
          position: relative;
          display: inline-block;
          margin-right: 20px;
          font-size: 14px;
          line-height: 14px;
          color: #999;
        }
        .attrs span:last-child {
          margin-right: 0;
        }
        h6 {
          color: #cacaca;
          font-size: 12px;
          line-height: 14px;
          margin-top: 20px;
          span {
            display: inline-block;
            font-weight: 700;
            color: #cacaca;
          }
          .price-icon,
          .price-num {
            color: #d44d44;
          }
          .price-num {
            margin-left: 5px;
            font-size: 14px;
          }
          .item-num {
            margin-left: 10px;
          }
        }
      }
      .cart-cell {
        display: table-cell;
        vertical-align: middle;
      }
      .del {
        display: none;
        overflow: hidden;
        position: absolute;
        right: 20px;
        top: 50%;
        transform: translateY(-50%);
      }
    }
    .nav-cart-total {
      box-sizing: content-box;
      position: relative;
      padding: 20px;
      height: 40px;
      background: #fafafa;
      border-top: 1px solid #f0f0f0;
      border-radius: 0 0 8px 8px;
      box-shadow: inset 0 -1px 0 hsla(0, 0%, 100%, 0.5),
        0 -3px 8px rgba(0, 0, 0, 0.04);
      background: -webkit-linear-gradient(#fafafa, #f5f5f5);
      background: linear-gradient(#fafafa, #f5f5f5);
      p {
        margin-bottom: 4px;
        line-height: 16px;
        font-size: 12px;
        color: #c1c1c1;
      }
      h5 {
        line-height: 20px;
        font-size: 14px;
        color: #6f6f6f;
        span {
          font-size: 18px;
          color: #de4037;
          display: inline-block;
          font-weight: 700;
        }
        span:first-child {
          font-size: 12px;
          margin-right: 5px;
        }
      }
      h6 {
        position: absolute;
        right: 20px;
        top: 20px;
        width: 108px;
      }
    }
  }
}

@media (max-height: 780px) {
  .nav-cart-items {
    max-height: 423px !important;
  }
}

@media (max-height: 900px) {
  .nav-cart-items {
    max-height: 544px !important;
  }
}

@media (max-height: 1080px) {
  .nav-cart-items {
    max-height: 620px !important;
  }
}

// 用户信息弹出
.nav-user-wrapper {
  position: absolute;
  z-index: 30;
  padding-top: 18px;
  opacity: 0;
  visibility: hidden;
  top: -3000px;
  .nav-user-list {
    position: relative;
    padding-top: 20px;
    background: #fff;
    border: 1px solid #d6d6d6;
    border-color: rgba(0, 0, 0, 0.08);
    border-radius: 8px;
    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.15);
    z-index: 10;
    &:before {
      position: absolute;
      content: ' ';
      background-size: 240px 107px;
      @include wh(20px, 8px);
      top: -8px;
      margin-left: -10px;
    }
  }
}

.nav-sub {
  position: relative;
  z-index: 20;
  height: 90px;
  background: #f7f7f7;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.04);
  &.fixed {
    position: fixed;
    z-index: 21;
    height: 60px;
    top: 0;
    left: 0;
    right: 0;
    border-bottom: 1px solid #dadada;
    background-image: -webkit-linear-gradient(#fff, #f1f1f1);
    background-image: linear-gradient(#fff, #f1f1f1);
  }
  .nav-sub-wrapper {
    padding: 31px 0;
    height: 90px;
    position: relative;
    &.fixed {
      padding: 0;
      height: 100%;
      display: flex;
      align-items: center;
    }
    &:after {
      content: ' ';
      position: absolute;
      top: 89px;
      left: 50%;
      margin-left: -610px;
      width: 1220px;
      background: #000;
      height: 1px;
      display: none;
      opacity: 0;
      -webkit-transition: opacity 0.3s ease-in;
      transition: opacity 0.3s ease-in;
    }
  }
  .w {
    display: flex;
    justify-content: space-between;
  }
  .nav-list2 {
    height: 28px;
    line-height: 28px;
    display: flex;
    align-items: center;
    height: 100%;
    li:first-child {
      padding-left: 0;
      a {
        padding-left: 10px;
      }
    }
    li {
      position: relative;
      float: left;
      padding-left: 2px;
      a {
        display: block;
        padding: 0 10px;
        color: #666;
        &.active {
          font-weight: bold;
        }
      }
      a:hover {
        color: #5683ea;
      }
    }
    li:before {
      content: ' ';
      position: absolute;
      left: 0;
      top: 13px;
      width: 2px;
      height: 2px;
      background: #bdbdbd;
    }
  }
}

@media (min-width: 1px) {
  .nav-sub .nav-sub-wrapper:after {
    display: block;
  }
}

.cart-con {
  /*display: flex;*/
  text-align: center;
  position: relative;
  p {
    padding-top: 185px;
    color: #333333;
    font-size: 16px;
  }
}

.cart-con:before {
  position: absolute;
  content: ' ';
  left: 50%;
  transform: translate(-50%, -70%);
  top: 50%;
  width: 76px;
  height: 62px;
  background-size: cover;
}
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
  font-size: 20px;
  color: #3fd847;
}
.price {
  font-size: 30px;
  color: #f4516c;
  float: right;
}
</style>
