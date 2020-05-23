<template>
  <div class="homeBox">
    <components :is="componentOn" />
  </div>
</template>

<script>
import adminComponent from './admin'
// import userComponent from '../houseShow/index.vue'
import { mapGetters } from 'vuex'
export default {
  computed: {
    ...mapGetters(['userName']),
    componentOn() {
      if (this.userName === 'admin') {
        return 'adminComponent'
      } else {
        return 'adminComponent'
      }
    }
  },
  components: {
    adminComponent
  }
}
</script>
