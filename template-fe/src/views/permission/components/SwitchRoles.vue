<template>
  <div>
    <div style="margin-bottom:15px;">
      Your roles: {{ roles }}
    </div>
    Switch roles:
    <el-radio-group v-model="switchRoles">
      <el-radio-button label="editor" />
      <el-radio-button label="admin" />
    </el-radio-group>
  </div>
</template>

<script>
export default {
  computed: {
    roles() {
      return ['admin']
    },
    switchRoles: {
      get() {
        return this.roles[0]
      },
      set(val) {
        this.$emit('change')
        // this.$store.dispatch('user/changeRoles', val).then(() => {
        // })
      }
    }
  }
}
</script>
