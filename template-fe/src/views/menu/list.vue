<template>
  <el-container class="app-iframe-container">
    <el-row style="margin-top: 1em;">
      <el-col :span="24">
        <el-button type="primary" icon="el-icon-circle-plus-outline" size="small" @click="toAddPage">新建
        </el-button>
        <el-button type="primary" icon="el-icon-edit-outline" size="small" :disabled="disableEdit" @click="updateCustomerWay(currentRowId)">修改
        </el-button>
      </el-col>
    </el-row>

    <div class="table-wrapper">
      <el-table
        v-loading="loading"
        :data="tableData"
        style="width: 100%"
        max-height="410"
        cell-class-name="cell-6"
        :border="true"
        :highlight-current-row="true"
        :header-cell-style="headerStyle"
        @current-change="selectRow"
      >
        <el-table-column label="选择" width="50" align="center">
          <template slot-scope="scope">
            <el-radio v-model="currentRowId" :label="scope.row.id"><i /></el-radio>
          </template>
        </el-table-column>

        <el-table-column
          prop="name"
          min-width="160px"
          :show-overflow-tooltip="true"
          label="获客方式"
        />

        <el-table-column
          prop="isMdmsSelected"
          min-width="130px"
          :show-overflow-tooltip="true"
          label="商户端是否可选"
        />

        <el-table-column
          prop="isSicBgCreateSelected"
          min-width="130px"
          label="后台新建是否可选"
        />

        <el-table-column
          prop="status"
          min-width="80px"
          label="状态"
        />

        <el-table-column
          prop="modifyEmpName"
          min-width="130px"
          label="修改人"
        />

        <el-table-column
          prop="modifyTime"
          min-width="130px"
          :show-overflow-tooltip="true"
          label="修改时间"
        />
      </el-table>
    </div>

    <el-footer class="top-15">
      <el-pagination
        :current-page="query.currentPage"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-footer>
  </el-container>
</template>

<script>

export default {
  name: 'List',
  data() {
    return {
      query: {
        pageSize: 10,
        currentPage: 1,
        limit: 0
      },
      enums: {
      },
      disableEdit: true,
      tableData: [],
      totalCount: 0,
      currentRow: '',
      currentRowId: '',
      loading: true,
      statusList: {
        1: '有效',
        2: '失效'
      }
    }
  },
  mounted() {
    this.search()
  },
  methods: {
    handleSizeChange(size) {
      this.query.pageSize = size
      this.search()
    },
    handleCurrentChange(page) {
      this.query.currentPage = page
      this.search()
    },
    search() {
      this.loading = true
      this.query.limit = (this.query.currentPage - 1) * this.query.pageSize

      this.currentRowId = ''
    },
    toAddPage() {
      this.callNewPage('/customerWay/add', { callback: 'search', type: 'new' })
    },
    updateCustomerWay(id) {
      if (!id) {
        this.$message({ type: 'info', message: '请选择一条获客方式' })
        return
      }
      this.callNewPage('/customerWay/edit/' + id, { callback: 'search', type: 'edit' })
    },
    statusFormat(row) {
      return this.statusList[row.status]
    },
    modifyTimeFormat(row) {
    },
    modifyEmpNameFormat(row) {
      if (row.modifyEmpName == null) {
        return '系统'
      }
      return row.modifyEmpName
    },
    selectRow(item) {
      if (!item) {
        this.currentRow = ''
        this.disableEdit = true
        return
      }
      this.currentRow = item
      this.currentRowId = item.id
      this.disableEdit = false
    }
  }
}
</script>

<style scoped>

</style>
