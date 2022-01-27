<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>상세보기</h3>
    <center>
      <div class="p-5" style="width: 600px">
        <b-row>
          <b-col cols="3" class="mt-2">
            <p>스터디 이름</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <p id="studyName_tag"><input type="text">{{ boardInfo.studyName }}</p>
            
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
    </div>
    <center>
      <b-row style="width: 600px;">
        <b-col cols="3" class="mt-3">
          <p>댓글</p>
        </b-col>
        <b-col cols="6" class="mt-3">
          <b-textarea></b-textarea>
        </b-col>
        <b-col cols="3" class="mt-3">
          <b-button>작성</b-button>
        </b-col>
      </b-row>
    </center>
      <!-- {{userInfo.user_id}}
      {{loginUserId }} -->
  </div>  
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardDetail',
  data() {
    return {
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
      this.$router.push({name: 'BoardFormUpdate'})
    },
    getBoard() {
      axios({
        method: 'get',
        url: `http://localhost:8080/api/board/search/${this.board_no}`,
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
        url: `http://localhost:8080/api/board/remove/${this.board_no}`,
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
        url: 'http://localhost:8080/api/user/me',
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
  },
  created() {
    this.getBoard()
    this.getUserInfo()
  },
}
</script>

<style>
p {
  color: black !important;
  font-size: 1rem !important;  
}
</style>