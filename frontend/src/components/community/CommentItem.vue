<template>
  <center>
    <div class="comment_position" style="width: 600px">
      <p>{{ comment.comment_no }}</p>
      <input v-if="editButton === true" type="text" v-model="comment.content" @keyup.enter="updateComment">
      <p v-else style="text-align: left;">내용 : {{ comment.content }}</p>
      <p>작성자 : {{ comment.user_id }}</p>
      <p>작성 시간 : {{ comment.created_at }}</p>
      <p>인덱스 확인 >> {{ comment.idx }} // 뜨나?</p>

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
    deleteComment() {
      // 댓글 인덱스에 맞게 삭제
      // delete_todo: funcion (state, todoItem){} 이게 store에 있을 때
      // todoItem이 첫 번째로 만나는 요소의 index를 가져옴
      // const commentIdx = state.todos.indexOf(todoItem)
      // 해당 index 1개만 삭제하고 나머지 요소를 토대로 새로운 배열 생성
      // state.todos.splice(index, 1)
      axios({
        method: 'delete',
        url: `http://i6e103.p.ssafy.io:8080/api/comment/remove/${this.comment_no}`,
        headers: this.getToken()
      })
      .then((res) => {
        console.log('댓글 삭제')
        console.log(res)
        this.$router.go(this.$router.currentRoute)
        // this.comment.splice(this.comment.comment_no, 1)
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
  },
  created() {
    this.getUserInfo()
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