<script>
export default {
  created() {
    const { params, query } = this.$route
    console.log(this.$route)
    const { path } = params
    console.log(this.$router)
    this.$router.replace({ path: '/' + path, query })
  },
  render: function(h) {
    return h() // avoid warning message
  }
}
</script>
