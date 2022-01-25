<template>
  <div>
    <h3>게시글</h3>
    <div v-for="boardItem in boardItems" :key="boardItem.idx">
      <span @click="goBoardDetail(boardItem.boardNo)">{{ boardItem.boardNo }}</span>
      <!-- 이거이거{{ boardItem.idx }} 확인 -->
    </div>
    <button @click="goBoardDetail(boardItem.idx)">디테일로</button>
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
        // headers: this.
      })
      .then(res => {
        console.log(res)
        this.boardItems = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    goBoardDetail(boardItemsIdx) {
      console.log(boardItemsIdx)
      this.$store.dispatch('getBoardNo', boardItemsIdx)
      this.$router.push({ name: 'BoardDetail'})
      
    },
  },
  created() {
    this.getBoardItems()
  }
}
</script>

<style>

</style>