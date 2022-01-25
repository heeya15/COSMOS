<template>
  <div>
    <h3>게시글</h3>
    <!-- <div v-for="boardItem in boardItems" :key="boardNo"></div> -->
    <span>{{ boardItems }}</span>
    <button @click="getBoardItems">테스트</button>
    <button @click="goBoardDetail">디테일로</button>
  </div>  
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardList',
  data() {
    return {
      boardItems: null,
    }
  },
  methods: {
    getBoardItems() {
      axios({
        method: 'get',
        url: 'http://localhost:8080/api/board/searchAll',
        // headers:
      })
      .then(res => {
        console.log(res)
        this.boardItems = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    goBoardDetail(board_no) {
      console.log(board_no)
      // this.$router.push({
      //   name: 'BoardDetail',
      //   query: { boardNo }  
      // })
    },

  }
}
</script>

<style>

</style>