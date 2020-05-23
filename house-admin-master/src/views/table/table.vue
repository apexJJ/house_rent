<template>
  <div class="className">
    <el-card class="anoCard">
      <div slot="header">
        <p class="pTitle">
          <i class="el-icon-s-opportunity"></i>
          房源信息管理
        </p>
      </div>
      <div class="searchDiv">
        <el-input
          type="text"
          placeholder="请输入房源编号"
          class="width1"
          v-model="id"
        ></el-input>
        <el-button type="primary" icon="el-icon-search" @click="searchTab()"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="addTab"
          >添加</el-button
        >
      </div>
      <el-table :data="tableData" border stripe>
        <el-table-column
          prop="id"
          label="房源编号"
          width="70"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="place"
          label="房屋区域"
          width="100"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="name"
          label="房屋标题"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="price"
          label="房屋价格"
          width="110"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="state"
          label="装修情况"
          width="110"
          align="center"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.state === '精装修' ? 'danger' : 'primary'"
              >{{ scope.row.state }}</el-tag
            >
          </template>
        </el-table-column>

        <el-table-column
          prop="createTime"
          label="编辑时间"
          width="100"
        ></el-table-column>

        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              @click="editTable(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button type="danger" @click="toDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        layout="total, sizes, prev, pager, next"
        :page-sizes="pageSizes"
        :page-size="pageSize"
        :current-page="currentPage"
        :total="pagedata.totalElements"
        class="fyDiv"
        @size-change="handleSize"
        @current-change="handlePage"
      >
      </el-pagination>
    </el-card>
    <el-dialog title="信息修改" :visible.sync="diaIsShow" class="diaForm">
      <el-form
        ref="diaForm"
        :model="formData"
        :rules="rules"
        label-width="140px"
      >
        <el-form-item label="编号" prop="id">
          <el-input
            type="text"
            v-model="formData.id"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="标题" prop="name">
          <el-input
            type="text"
            placeholder="请输入标题"
            v-model="formData.name"
          ></el-input>
        </el-form-item>
        <el-form-item label="地区" prop="place">
          <el-input
            type="text"
            v-model="formData.place"
            placeholder="请输入地区"
          ></el-input>
        </el-form-item>
        <el-form-item label="装修情况" prop="state">
          <el-input
            type="text"
            placeholder="请输入信息"
            v-model="formData.state"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateUserInfo">确认</el-button>
          <el-button @click="diaIsShow = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getPageTab2 } from '@/api/table'
export default {
  inject: ['reload'], // 注入 reload 方法
  data() {
    return {
      tableData: [],
      userlist: [],
      schArr: [],
      sch_order: '',
      sch_status: null,
      sch_date: null,
      currentPage: 0,
      pageSize: 6,
      pageable: [],
      pageSizes: [6, 20, 30, 40],
      diaIsShow: false,
      formData: {},
      editType: '',
      rowIndex: 0,
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        phonenumber: [
          { required: true, message: '请输入手机号', trigger: 'blur' }
        ],
        name: [{ required: true, message: '请输入真实姓名', trigger: 'blur' }]
      }
    }
  },
  created() {
    // this._getPageTab2()
    this.getList()
  },
  filters: {},
  methods: {
    handleSize(val) {
      this.pageSize = val
      this.getPageData()
    },
    handlePage(val) {
      this.currentPage = val
      this.getPageData()
    },
    _getPageTab2() {
      getPageTab2()
        .then(res => {
          this.allList = res.data.tableList
          this.schArr = this.allList
          this.getPageData()
          this.total = res.data.total
        })
        .catch(error => {
          this.$message.error(error.message)
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
          this.tableData = res.data.data.content
          this.pagedata = res.data.data
          console.log(this.result)
          console.log(this.pagedata)
        })
        .catch(response => {
          console.log(response)
        })
    },
    updateUserInfo() {
      var vm = this
      this.axios({
        method: 'post',
        url: 'http://localhost:8080/api/updateUserByWorkId',
        data: {
          username: vm.formData.username,
          phonenumber: vm.formData.phonenumber,
          name: vm.formData.name,
          work_id: vm.formData.work_id
        }
      })
        .then(response => {
          this.$notify({
            title: '成功',
            message: '人员信息已修改成功',
            type: 'success'
          })
          this.reload() // 调用刷新方法
          console.log(response)
        })
        .catch(error => {
          console.log(error)
          this.$message.error(error.message)
        })
    },
    // 编辑
    editTable(index, row) {
      this.formData = Object.assign({}, row)
      this.editType = 'update'
      this.diaIsShow = true
      this.$nextTick(() => {
        this.$refs.diaForm.clearValidate()
      })
      this.rowIndex = index
    },
    getPageData() {
      let start = (this.currentPage - 1) * this.pageSize
      let end = start + this.pageSize
      this.tableData = this.schArr.slice(start, end)
    },
    // 查找
    searchTab() {
      let arrList = []
      for (let item of this.userlist) {
        if (
          this.sch_order.trim() === '' &&
          this.sch_status === null &&
          this.sch_date === null
        ) {
          arrList = this.userlist
          break
        } else if (
          item.order.startsWith(this.sch_order) &&
          (this.sch_status !== null ? item.status === this.sch_status : true) &&
          (this.sch_date !== null ? item.time.startsWith(this.sch_date) : true)
        ) {
          let obj = Object.assign({}, item)
          arrList.push(obj)
        }
      }
      this.schArr = arrList
      this.total = arrList.length
      this.currentPage = 1
      this.pageSize = 10
      this.getPageData()
    },
    // add
    addTab() {
      this.formData = {}
      this.diaIsShow = true
      this.formData.order = (Math.random() * 10e18).toString()
      this.formData.id = this.allList.length + 1
      this.editType = 'add'
      this.$nextTick(() => {
        this.$refs.diaForm.clearValidate()
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.fyDiv {
  float: right;
  margin-top: 30px;
  padding-bottom: 20px;
}
.anoCard .el-table .el-button {
  padding: 8px 18px;
  font-size: 12px;
}
.searchDiv {
  margin-bottom: 20px;
  .el-button {
    padding: 11px 20px;
  }
}
.width1 {
  width: 180px;
  margin-right: 10px;
}
.diaForm {
  .el-input {
    width: 350px;
  }
}
</style>
<style lang="scss">
.anoCard {
  .el-card__body:after {
    content: '';
    clear: both;
    width: 0;
    height: 0;
    visibility: hidden;
    display: block;
  }
}
.diaForm .el-form-item__label {
  padding-right: 20px;
}
.searchDiv [class^='el-icon'] {
  color: #fff;
}
</style>
