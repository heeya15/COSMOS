<template>
  <div>
    <input v-if="editButton === true" type="text" v-model="comment.content">
    <p v-else>내용 : {{ comment.content }}</p>

    <p >작성자 : {{ comment.user_id }}</p>
    <p >작성 시간 : {{ comment.created_at }}</p>
    <p >{{ comment.comment_no }}</p>

    <div v-show="editButton === false">
      <button @click="editButtonChange">수정</button>
      <button v-if="userId === loginUserId" @click="deleteComment">삭제</button>
    </div>
    <div v-show="editButton === true">
      <button @click="updateComment">수정</button>
    </div>
  </div>

</template>

<script>
import axios from 'axios'
import {mapState} from 'vuex'

export default {
  name: 'CommentItem',
  props: {
    comment: {
      type: Object,
    }
  },
  data() {
    return {
      editButton: false,
      comment_no: this.comment.comment_no,
      userId: this.comment.user_id,
      loginUserId: null,
      created_at: this.comment.created_at
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
    deleteComment() {
      axios({
        method: 'delete',
        url: `http://i6e103.p.ssafy.io:8080/api/comment/remove/${this.comment_no}`,
        headers: this.getToken()
      })
      .then((res) => {
        console.log('댓글 부분')
        console.log(res.data)
        this.$router.go(this.$router.currentRoute)
      })
      .catch(err => {
        console.log(err)
      })
    },
    getComment() {
      this.$store.dispatch('getComment')
      // this.$router.go()
    },
    editButtonChange() {
      this.editButton = true
      // this.commentContent = this.comment.content
    },
    updateComment() {
      const updateCommentItem = {
        board_no: this.comment.boardNo,
        comment_no: this.comment.comment_no,
        content: this.comment.content,
        created_at: this.comment.created_at,
        // user_id: this.comment.user_id,
      }
      axios({
        method: 'put',
        url: 'http://i6e103.p.ssafy.io:8080/api/comment/update',
        data: updateCommentItem,
        headers: this.getToken()
      })
      .then(res => {
        this.getComment()
        console.log('수정부분')
        console.log(res)
        this.editButton = false
        // this.created_at = 
      })
      .catch(err => {
        console.log(err)
      })
    },
    getUserInfo(){
      axios({
        method: 'GET',
        url: 'http://i6e103.p.ssafy.io:8080/api/user/me',
        headers: this.getToken()
      })
      .then(res =>{
        console.log(res.data)
        this.loginUserId=res.data['user_id']
        // console.log('유저 확인')
        // console.log(this.loginUserId)
      })
      .catch(err =>{
        console.log(err)
      })
    },
  },
  created() {
    this.getUserInfo()
    this.getComment()
  },
  computed: {
    ...mapState([
      'comments',
    ]),
  },
}

</script>

<style scoped>
.comment_tag {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
</style>