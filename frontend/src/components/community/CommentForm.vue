<template>
  <div>
    <center>
      <b-row style="width: 600px;">
        <b-col cols="3" class="mt-3">
          <p>댓글</p>
        </b-col>
        <b-col cols="6" class="mt-3">
          <b-input v-model="commentInput.content" @keyup.enter="createComment"></b-input>
        </b-col>
        <b-col cols="3" class="mt-3">
          <b-button @click="createComment">작성</b-button>
        </b-col>
      </b-row>
    </center>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'CommentForm',
  data() {
    return {
      commentInput: {
        content: null,
        user_id: null,
      },
    }
  },
  methods: {
    getToken(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`
      }
      return header
    },
    createComment() {
      const createCommentItem = {
        board_no: this.$store.state.boardNo,
        content: this.commentInput.content
      }
      axios({
        method: 'post',
        url: 'http://i6e103.p.ssafy.io:8080/api/comment/register',
        data: createCommentItem,
        headers: this.getToken()

      })
      .then(res => {
        console.log('댓글부분')
        console.log(res)
        console.log('댓글')
        // 작성하고 바로 뜨게 새로고침
        this.$router.go(this.$router.currentRoute)
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
}
</script>

<style>

</style>