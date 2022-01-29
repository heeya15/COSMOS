<template>
  <div>
    <h3>댓글하나하나</h3>
    <!-- <button @click="getComment">댓글 가져오기</button> -->
    <!-- <div class="comment_tag" v-for="(comment, idx) in comments" :key="idx"> -->
      <p>내용 : {{ comment.content }}</p>
      <p>작성자 : {{ comment.user_id }}</p>
      <p>작성 시간 : {{ comment.created_at }}</p>
    </div>
  <!-- </div>   -->
</template>

<script>
// import axios from 'axios'

export default {
  name: 'CommentItem',
  props: {
    comment: {
      type: Object,
    }
  },
  // data() {
  //   return {
  //     board_no: this.$store.state.boardNo,
  //     comments: null,
  //   }
  // },
  // methods: {
  //   getToken(){
  //     const token = localStorage.getItem('jwt')
  //     const header = {
  //       Authorization: `Bearer ${token}`
  //     }
  //     return header
  //   },
  //   getComment() {
  //     axios({
  //       method: 'get',
  //       url: `http://i6e103.p.ssafy.io:8080/api/comment/searchAll/${this.board_no}`,
  //       headers: this.getToken(),
  //     })
  //     .then(res => {
  //       this.comments = res.data
  //       console.log('댓글확인!!')
  //       console.log(this.comments)
  //       console.log(res)
  //     })
  //     .catch(err => {
  //       console.log(err)
  //     })
  //   },
  // created() {
  //   this.getComment()
  //   },
  }

</script>

<style scoped>
.comment_tag {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
</style>