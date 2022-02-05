<template>
  <center>
    <div class="comment_position" style="width: 600px">
      <p>{{ comment.comment_no }}</p>
      <input v-if="editButton === true" type="text" v-model="comment.content" @keyup.enter="updateComment">
      <p v-else style="text-align: left;">내용 : {{ comment.content }}</p>
      <p>작성자 : {{ comment.user_id }}</p>
      <p>작성 시간 : {{ makeDate(comment.created_at) }}</p>
      <p>인덱스 확인 >> {{ idx }} // 뜨나?</p>

      <div v-show="editButton === false">
        <button v-if="userId === loginUserId" @click="editButtonChange">수정</button>
        <button v-if="userId === loginUserId" @click="deleteComment">삭제</button>
      </div>
      <div v-show="editButton === true">
        <button v-if="userId === loginUserId" @click="updateComment">수정</button>
        <button v-if="userId === loginUserId" @click="deleteComment">삭제</button>
      </div>

    </div>
  </center>
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
      created_at: null,
      idx: null,
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
    getComment() {
      this.$store.dispatch('getComment')
      // this.$router.go()
      console.log('댓글 가져오기')
    },
    deleteComment() {
      const commentIdx = this.$store.state.comments.indexOf(this.comment)
      this.$store.state.comments.splice(commentIdx, 1)
      axios({
        method: 'delete',
        url: `http://i6e103.p.ssafy.io:8080/api/comment/remove/${this.comment_no}`,
        headers: this.getToken()
      })
      .then((res) => {
        console.log(res)
        this.comment_no = this.comment.comment_no
      })
      .catch(err => {
        console.log(err)
      })
    },
    editButtonChange() {
      this.editButton = true
    },
    updateComment() {
      const updateCommentItem = {
        board_no: this.comment.boardNo,
        comment_no: this.comment.comment_no,
        content: this.comment.content,
      }
      axios({
        method: 'put',
        url: 'http://i6e103.p.ssafy.io:8080/api/comment/update',
        data: updateCommentItem,
        headers: this.getToken(),
      })
      .then(res => {
        console.log('수정부분')
        console.log(res.data)
        this.editButton = false
        this.$router.go(this.$router.currentRoute)
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
        // console.log(res.data)
        this.loginUserId=res.data['user_id']
        // console.log('유저 확인')
        // console.log(this.loginUserId)
      })
      .catch(err =>{
        console.log(err)
      })
    },
    makeDate(datetime) {
      const old = ''+datetime
      return old.substring(0, 10)
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
.comment_position {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  justify-content: space-evenly;
}

</style>