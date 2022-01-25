<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>상세보기</h3>
    <div>
      <!-- <span>{{ selectedPost.study_name }}</span>
      <span>{{ selectedPost.recruit_number }}</span>
      <span>{{ selectedPost.content }}</span> -->
    </div>
    <div>
      <!-- 작성자인 경우 수정을 보여주고 아니면 스터디 신청을 보여준다 -->
      <b-button style="background-color: #DAC7F9">스터디 신청</b-button>
      <b-button style="background-color: #DAC7F9" @click="boardFormEdit">수정</b-button>
      <b-button style="background-color: #DAC7F9" @click="goBoardMain">목록</b-button>
      <b-button style="background-color: #DAC7F9" @click="deleteBoardForm">삭제</b-button>
    </div>
  </div>  
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardDetail',
  data() {
    return {
      board_no: this.$store.state.boardNo,
    }
  },
  props: {
    boardItem: Object,
  },
  methods: {
    goBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },
    boardFormEdit() {
      this.$router.push({name: 'BoardFormUpdate'})
    },
    getBoard() {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/board/search/${this.board_no}`,
      })
      .then(res => {
        console.log(res)
        this.board_no = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteBoardForm() {
      axios({
        method: 'delete',
        url: `http://localhost:8080/api/board/remove/${this.board_no}`,
      })
      .then((res) => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  created() {
    this.getBoard()
    this.board_no = this.boardItem.boardNo
    console.log(this.boardItem)
  },
}
</script>

<style>

</style>