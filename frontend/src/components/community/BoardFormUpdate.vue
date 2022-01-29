<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>글 수정</h3>
      <center>
        <div style="width: 800px;">
          <b-row>
            <b-col class="title_label" cols="3" >
              <label class="mt-2" for="contentTtitle">제목</label>
            </b-col>
            <b-col cols="9">
              <b-form-input class="mt-2" id="content_title" v-model="boardInfo.contentTitle"></b-form-input>
            </b-col>
          
            <b-col cols="3" class="mt-2">
              <label for="study_name">스터디이름</label>
            </b-col>
            <b-col cols="9" class="mt-2">
              <b-form-input v-model="boardInfo.studyName" id="study_name"></b-form-input>
            </b-col>
          
            <b-col cols="3" class="mt-2">
              <label for="recruit_number">모집인원</label>
            </b-col>
            <b-col class="spinbuttontag mt-2" cols="9">
              <b-form-input id="recruit_number" v-model="boardInfo.recruitNumber"></b-form-input> 명
            </b-col>
          
            <b-col cols="3" class="mt-2">
              <label for="studytype_name">스터디분류</label>
            </b-col>
            <b-col class="selectag mt-2" cols="9">
              <b-form-select v-model="boardInfo.studytypeName" :options="boardInfo.studytypeName" id="studytype_name"></b-form-select>
            </b-col>
          
            <b-col cols="3" class="mt-2">
              <label for="some-radios">상태</label>
            </b-col>
            <b-col cols="9">
              <label class="mx-3 mt-2"><input v-model="boardInfo.header" type="radio" name="header" value="false">진행중</label>
              <label class="mx-3 mt-2"><input v-model="boardInfo.header" type="radio" name="header" value="true">완료</label>
            </b-col>

            <b-col cols="3" class="mt-2">
              <label for="content">내용</label>
            </b-col>
            <b-col cols="9" class="mt-2">
              <b-form-textarea v-model="boardInfo.content" id="content" row="8" max-rows="10"></b-form-textarea>
            </b-col>
          </b-row>
        </div>
      <b-button class="mx-3 mt-5" style="background-color: #DAC7F9" @click="goBoardDetail">게시글 수정</b-button>
      <b-button class="mx-3 mt-5" style="background-color: #DAC7F9" @click="goBoardDetail">취소</b-button>
    </center>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardFormUpdate',
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
      }
    }
  },

  methods: {
    backBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },
    goBoardDetail() {
      this.$router.push({name: 'BoardDetail'})
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
  created() {
    this.getBoard()
    this.getUserInfo()
    },
  }
}
</script>

<style>

</style>