<template>
  <el-container class="app-container">
    <el-header>
      <el-col :span="24">
        <el-button type="primary" icon="el-icon-circle-plus-outline" size="small" @click="addMenu">新建
        </el-button>
        <el-button type="primary" icon="el-icon-edit-outline" size="small" @click="editMenu">修改
        </el-button>
      </el-col>
    </el-header>
    <el-container>
      <el-aside width="40%">
        <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick" />
      </el-aside>
      <el-main>Main</el-main>
    </el-container>
  </el-container>
</template>

<script>

export default {
  name: 'List',
  data() {
    return {
      data: [],
      defaultProps: {}
    }
  },
  mounted() {
  },
  methods: {
    handleNodeClick() {

    },
    addMenu() {
      this.$router.push('/menu/edit')
    },
    editMenu() {
      this.$router.push('/menu/edit')
    }
  }
}
</script>

<style scoped>

</style>
