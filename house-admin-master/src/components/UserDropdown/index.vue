<template>
  <div>
    <el-dropdown class="userDd">
      <div class="userDrop">
        <span class="userDrop_text"
          >{{ userName }}<i class="el-icon-caret-bottom"></i
        ></span>
        <img src="./user01.jpg" alt="user" />
      </div>
      <el-dropdown-menu solt="dropdown">
        <el-dropdown-item divided>
          <a @click="_loginOut()"><i class="el-icon-switch-button"></i>登出</a>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      dialogVisible: false
    }
  },
  computed: {
    ...mapGetters(['userName'])
  },
  methods: {
    _loginOut() {
      this.$store.dispatch('user/loginOut')
    },
    addimg() {
      this.dialogVisible = true
    },
    add() {
      this.$message.success('上传成功')
      this.dialogVisible = false
    },
    handleClose() {
      this.$message.error('验证失败')
    }
  }
}
</script>
