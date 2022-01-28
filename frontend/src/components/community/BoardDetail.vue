<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>상세보기</h3>
    <center>
      <!-- 수정 버튼 수정 모디파이 -->
      <div class="p-5" style="width: 600px" v-if="editButton === false">
        <b-row>
          <b-col cols="3" class="mt-2">
            <p>제목</p>
          </b-col>
          <b-col cols="9" class="mt-2" >
            <input v-if="editButton === true" type="text" v-model="boardInfo.contentTitle">
            <!-- <p>{{ boardInfo.contentTitle }}</p> -->
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>스터디 이름</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <input v-if="editButton === true" type="text" v-model="boardInfo.studyName">
            <p v-else id="studyName_tag">{{ boardInfo.studyName }}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>스터디 인원</p>
          </b-col>
          <b-col cols="9" class="mt-2" >
            <p>{{ boardInfo.recruitNumber }}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>스터디 분류</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <p>{{ boardInfo.studytypeName }}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>작성자</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <p>{{ userInfo.user_name}}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>내용</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <p>{{ boardInfo.content }}</p>
          </b-col>
        </b-row>
      </div>
    </center> 
    <div>
      <!-- 작성자인 경우 수정을 보여주고 아니면 스터디 신청을 보여준다 -->
      <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="boardFormEdit">수정</b-button>
      <b-button v-else style="background-color: #DAC7F9">스터디 신청</b-button>
      <b-button style="background-color: #DAC7F9" @click="goBoardMain">목록</b-button>
      <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="deleteBoardForm">삭제</b-button>
      <!-- <b-button v-if="editButton === true" style="background-color: #DAC7F9" @click="updateForm">저장</b-button> -->
      <b-button v-if="editButton === true" style="background-color: #DAC7F9" @click="updateForm">수정</b-button>
    </div>
    <center>
      <b-row style="width: 600px;">
        <b-col cols="3" class="mt-3">
          <p>댓글</p>
        </b-col>
        <b-col cols="6" class="mt-3">
          <b-input v-model="commentInput.content"></b-input>
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
  name: 'BoardDetail',
  data() {
    return {
      editButton: false,
      loginUserId: null,
      board_no: this.$store.state.boardNo,
      boardInfo: {
        boardNo: null,
        content: null,
        contentStatus: null,
        contentTitle: null,
        createdAt: null,
        header: null,
        recruitNumber: null,
        studyName: null,
        studytypeName: null,
      },
      userInfo: {
        user_name: null,
        user_id: null,
      },
      commentInput: {
        comment_no: null,
        content: null,
        created_at: null,
        // user_id: null,
      }
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
    goBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },
    boardFormEdit() {
      // this.$router.push({name: 'BoardFormUpdate'})
      this.editButton = true
    },
    getBoard() {
      axios({
        method: 'get',
        url: `http://i6e103.p.ssafy.io:8080/api/board/search/${this.board_no}`,
        headers: this.getToken(),
      })
      .then(res => {
        console.log(res)
        this.boardInfo.boardNo = res.data['boardNo']
        this.boardInfo.studyName = res.data['studyName']
        this.boardInfo.recruitNumber = res.data['recruitNumber']
        this.boardInfo.studytypeName = res.data['studytypeName']
        this.boardInfo.content = res.data['content']
        this.userInfo.user_name = res.data.user['userName']
        this.userInfo.user_id = res.data.user['userId']
        console.log('작동확인')
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteBoardForm() {
      axios({
        method: 'delete',
        url: `http://i6e103.p.ssafy.io:8080/api/board/remove/${this.board_no}`,
        headers: this.getToken()
      })
      .then((res) => {
        console.log(res)
        this.$router.push({name: 'MainBoard'})
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
        console.log(res)
        this.loginUserId=res.data['user_id']
      })
      .catch(err =>{
        console.log(err)
      })
    },

    // 게시물 수정 
    updateForm() {
      const updateItem = {
        board_no: this.boardInfo.boardNo,
        content: this.boardInfo.content,
        content_status: this.boardInfo.contentStatus,
        content_title: this.boardInfo.contentTitle,
        recruit_number: this.boardInfo.recruitNumber,
        study_name: this.boardInfo.studyName,
        studytype_name: this.boardInfo.studytypeName,
        user_id: this.userInfo.user_id,
      }
      axios({
        method: 'put',
        url: 'http://i6e103.p.ssafy.io:8080/api/board/update',
        data: updateItem,
        headers: this.getToken(),
      })
      .then(() => {
        this.getBoard()
        this.study_name = this.boardInfo.studyName
        this.editButton = false
        // console.log(this.boardInfo)
        // 500 에러 발생
      })
      .catch(err => {
        console.log(err)
      })
      this.boardInfo.studyName = null
    },
    createComment() {
      const createCommentItem = {
        board_no: this.boardInfo.boardNo,
        // comment_no: this.commentInput.comment_no,
        content: this.commentInput.content,
        user_id: this.userInfo.user_id,
      }
      axios({
        method: 'post',
        url: 'http://i6e103.p.ssafy.io:8080/api/comment/register',
        data: createCommentItem,
        // headers: this.getToken()

      })
      .then(res => {
        console.log('댓글부분')
        console.log(res.config)
        console.log(this.boardInfo.boardNo)
        // console.log(this.commentInput.comment_no)
        console.log(this.userInfo.user_id)
        console.log(this.commentInput.content)
        // this.$router.push({name: "MainBoard"})
        console.log('댓글')
        
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    this.getBoard()
    this.getUserInfo()
    // this.createComment()
  },
}
</script>

<style>
p {
  color: black !important;
  font-size: 1rem !important;  
}
</style>