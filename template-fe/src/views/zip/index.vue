<template>
  <div class="app-container">
    <el-input v-model="filename" placeholder="Please enter the file name (default file)" style="width:300px;" prefix-icon="el-icon-document" />
    <el-button :loading="downloadLoading" style="margin-bottom:20px;" type="primary" icon="el-icon-document" @click="handleDownload">
      Export Zip
    </el-button>
    <el-button :loading="downloadLoading" style="margin-bottom:20px;" type="primary" icon="el-icon-document" @click="downLoadZip">
      stream saver export
    </el-button>
    <el-table v-loading="listLoading" :data="list" element-loading-text="拼命加载中" border fit highlight-current-row>
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="Title">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="Author" width="95" align="center">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.author }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Readings" width="115" align="center">
        <template slot-scope="scope">
          {{ scope.row.pageviews }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="Date" width="220">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.display_time }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

import streamSaver from '@/utils/streamsaver/StreamSaver'
import ZIP from '@/utils/streamsaver/zip-stream'

export default {
  name: 'ExportZip',
  data() {
    return {
      list: null,
      listLoading: true,
      downloadLoading: false,
      filename: ''
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    downLoadZip() {
      const fileStream = streamSaver.createWriteStream('archive.zip')

      const file1 = new File(['file1 content'], 'streamsaver-zip-example/file1.txt')
      // File Like object works too
      const file2 = {
        name: 'streamsaver-zip-example/file2.txt',
        stream() {
          // if you want to play it cool and use new api's
          //
          // const { readable, writable } = new TextEncoderStream()
          // writable.write('file2 content')
          // writable.close()
          // return readable

          return new ReadableStream({
            start(ctrl) {
              ctrl.enqueue(new TextEncoder().encode('file2 generated with readableStream'))
              ctrl.close()
            }
          })
        }
      }

      const blob = new Blob(['support blobs too'])

      const file3 = {
        name: 'streamsaver-zip-example/blob-example.txt',
        stream: () => blob.stream()
      }
      const readableZipStream = new ZIP({
        start(ctrl) {
          ctrl.enqueue(file1)
          ctrl.enqueue(file2)
          ctrl.enqueue(file3)
          ctrl.enqueue({ name: 'streamsaver-zip-example/empty folder', directory: true })
          // ctrl.close()
        },
        async pull(ctrl) {
          // Gets executed everytime zip.js asks for more data
          const url = 'https://d8d913s460fub.cloudfront.net/videoserver/cat-test-video-320x240.mp4'
          const res = await fetch(url)
          const stream = () => res.body
          const name = 'streamsaver-zip-example/cat.mp4'

          ctrl.enqueue({ name, stream })

          // if (done adding all files)
          ctrl.close()
        }
      })

      // more optimized
      if (window.WritableStream && readableZipStream.pipeTo) {
        return readableZipStream.pipeTo(fileStream).then(() => console.log('done writing'))
      } else {
        // less optimized
        const writer = fileStream.getWriter()
        const reader = readableZipStream.getReader()
        const pump = () => reader.read()
          .then(res => res.done ? writer.close() : writer.write(res.value).then(pump))

        pump()
      }
    },
    fetchData() {
      this.listLoading = true
      const data = this.fetchList()
      console.log(data)
      this.list = data.items
      this.listLoading = false
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Zip').then(zip => {
        const tHeader = ['Id', 'Title', 'Author', 'Readings', 'Date']
        const filterVal = ['id', 'title', 'author', 'pageviews', 'display_time']
        const list = this.list
        const data = this.formatJson(filterVal, list)
        zip.export_txt_to_zip(tHeader, data, this.filename, this.filename)
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    fetchList() {
      const List = []
      List.push({
        id: '1',
        author: 'huage',
        reviewer: 'huage',
        title: '华哥求虐',
        content_short: 'mock data',
        pageviews: 'aaa',
        display_time: 'aaa'
      })
      return { items: List }
    }
  }
}
</script>
