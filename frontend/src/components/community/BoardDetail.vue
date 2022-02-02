<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3 v-if="editButton === true">글 수정</h3>
    <h3 v-else>상세보기</h3>
    <center>
      <div class="p-5" style="width: 600px">
        <b-row>
          <b-col cols="3" class="mt-2">
            <p>제목</p>
          </b-col>
          <b-col cols="9" class="mt-2" >
            <input v-if="editButton === true" type="text" v-model="boardInfo.contentTitle">
            <p v-else>{{ boardInfo.contentTitle }}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>스터디 이름</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <p>"이름 불러 오는 곳" {{ boardInfo.studyName }}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>스터디 인원</p>
          </b-col>
          <b-col cols="9" class="mt-2" >
            <input v-if="editButton === true" type="text" v-model="boardInfo.recruitNumber">
            <p v-else>{{ boardInfo.recruitNumber }}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>스터디 분류</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <p>"분류 불러 오는 곳 스프링이나 자바 등"{{ boardInfo.studytypeName }}</p>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p v-if="editButton === true" for="some-radios">상태</p>
          </b-col>
          <b-col cols="9">
            <label v-if="editButton === true" class="mx-3 mt-2"><input v-model="boardInfo.contentStatus" type="radio" name="contentStatus" value="false">진행중</label>
            <label v-if="editButton === true" class="mx-3 mt-2"><input v-model="boardInfo.contentStatus" type="radio" name="contentStatus" value="true">완료</label>
          </b-col>

          <b-col cols="3" class="mt-2">
            <p>내용</p>
          </b-col>
          <b-col cols="9" class="mt-2">
            <b-textarea v-if="editButton === true" type="text" v-model="boardInfo.content"></b-textarea>
            <p v-else>{{ boardInfo.content }}</p>
          </b-col>
          <p>여기 번호 : {{ studyInfo.studyNo }}</p>
        </b-row>
      <!-- </div> -->
      </div>
    </center> 
    <div>
      <!-- 작성자인 경우 수정을 보여주고 아니면 스터디 신청을 보여준다 -->
      <div v-show="editButton === false">
        <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="boardFormEdit">수정</b-button>
        <b-button v-else style="background-color: #DAC7F9" @click="applyStudy">스터디 신청</b-button>
        <b-button style="background-color: #DAC7F9" @click="goBoardMain">목록</b-button>
        <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="deleteBoardForm">삭제</b-button>
      </div>
      <div v-show="editButton === true">
        <b-button v-if="editButton === true" style="background-color: #DAC7F9" @click="updateForm">수정</b-button>
        <b-button style="background-color: #DAC7F9" @click="goBoardMain">목록</b-button>
        <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="deleteBoardForm">삭제</b-button>
        <!-- <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="updateForm">취소</b-button> -->
      </div>
    </div>

    <comment/>
  </div>  
</template>

<script>
import axios from 'axios'
import Comment from '@/components/community/Comment.vue'

export default {
  name: 'BoardDetail',
  components: {
    Comment,
  },
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
      },
      // options: [
      //     { value: 'JavaScript', text: 'JavaScript' },
      //     { value: 'Spring', text: 'Spring' },
      //     { value: 'Java', text: 'Java' },
      //     { value: 'Python', text: 'Python' },
      //     { value: '기타', text: '기타' },
      //   ],
      // comments: null,
      // 스터디 방 번호 값 받아와야 함
      studyInfo: {
        studyName: null,
        studyNo: null,
        studyType: null,
      }
    }
  },
  methods: {
    // 토큰 가져오기
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
      this.editButton = true
    },

    // 스터디 신청
    applyStudy() {
      axios({
        method: 'post',
        url: `http://i6e103.p.ssafy.io:8080/api/study/applyMember/register/${this.study_no}`,
        headers: this.getToken(),
      })
      .then((res) => {
        console.log(res)
        // this.study_no = 4
        // 스터디장이 만든 스터디 이름 조회
        // this.boardInfo.studyName = res.data['studyName']
        alert('신청이 완료되었습니다.')
        // this.$router.push({name: 'BoardDetail'})
        
      })
      .catch((err) => {
        console.log(err)
      })
    },

    // 게시글 가져오기
    getBoard() {
      axios({
        method: 'get',
        url: `http://i6e103.p.ssafy.io:8080/api/board/search/${this.board_no}`,
        // headers: this.getToken(),
      })
      .then(res => {
        console.log(res)
        this.boardInfo.boardNo = res.data['boardNo']
        this.boardInfo.contentStatus = res.data['contentStatus']
        this.boardInfo.contentTitle = res.data['contentTitle']
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

    // 게시글 삭제
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

    // 유저 정보 가져오기
    getUserInfo(){
      axios({
        method: 'GET',
        url: 'http://i6e103.p.ssafy.io:8080/api/user/me',
        headers: this.getToken()
      })
      .then(res =>{
        console.log('유저정보 확인')
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
        contentStatus: this.boardInfo.contentStatus,
        content_title: this.boardInfo.contentTitle,
        recruit_number: this.boardInfo.recruitNumber,
        study_name: this.boardInfo.studyName,
        studytype_name: this.boardInfo.studytypeName,
        user_id: this.userInfo.user_id,
        header: this.boardInfo.header,
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
        this.content_title = this.boardInfo.contentTitle
        this.contentStatus = this.boardInfo.contentStatus
        console.log('상태 확인')
        console.log(this.boardInfo.contentStatus)
        this.editButton = false
      })
      .catch(err => {
        console.log(err)
      })
      this.boardInfo.studyName = null
    },

    // 스터디정보 불러오기(번호)
    getStudyInfo() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/study/search/${this.studyNo}`
      })
      .then(res => {
        console.log('스터디 데이터 확인')
        console.log(res)
        // this.studyInfo.studyName = res.data.studyName
        this.studyInfo.studyNo = 5
        // this.studyInfo.url = res.data.url
      })
      .catch(err => {
        console.log(err)
        console.log('스터디정보 오류 확인')
        console.log(this.studyInfo.studyNo)
      })
    },
  },
  created() {
    this.getBoard()
    this.getUserInfo()
    this.getStudyInfo()
  },
}
</script>

<style scoped>
p {
  color: black ;
  font-size: 1rem ;  
}
</style>