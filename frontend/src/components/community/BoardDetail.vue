<template>
  <div>
    <center>
    <div class="total_body">
      <h1>스터디 모집 게시판</h1>
      <hr class="titleHr_tag">
      <h3 v-if="editButton === true">글 수정</h3>
      <h3 v-else>상세보기</h3>
      <!-- <img src="게시판보드.png" alt=""> -->
      <div class="memo">
        <p>함께해요!</p>
      </div>
      <div class="memo1">
        <p>사람을 찾습니다!👀</p>
      </div>
      <div class="memo2">
        <p>나랑 스터디 할 사람~✍</p>
      </div>
      <div class="memo3">
        <p>열쩡🔥열쩡🔥열쩡🔥</p>
      </div>

      <div class="round_box"></div>
      <div class="round_box1"></div>
      <div class="round_box2"></div>
      <div class="round_box3"></div>
      <div class="round_box4"></div>
      <!-- <div class="line4"></div> -->
      <!-- <div class="line5"></div> -->
      
        <div class="body_total p-5" style="width: 500px;">
          <b-row class="content_rowTag">
            <b-col cols="5" class="mt-3">
              <p>제목</p>
              <hr class="hrTag">
            </b-col>
            <b-col cols="7" class="mt-3">
              <b-form-input v-if="editButton === true" type="text" v-model="boardInfo.contentTitle" style="height: 35px; text-align: center;" maxlength="30"></b-form-input>
              <p v-else>{{ boardInfo.contentTitle }}</p>
              <hr class="hrTag">
            </b-col>

              <b-col v-show="this.boardInfo.header === false" cols="5" class="mt-2">
                <p v-show="this.boardInfo.header === false" >스터디 이름</p>
                <hr class="hrTag" v-show="this.boardInfo.header === false">
              </b-col>
              <b-col v-show="this.boardInfo.header === false" cols="7" class="mt-2">
                <p>{{ boardInfo.studyName }}</p>
                <hr class="hrTag" v-show="this.boardInfo.header === false">
              </b-col>

              <b-col v-show="this.boardInfo.header === false" cols="5" class="mt-2">
                <p v-show="this.boardInfo.header === false">스터디 인원</p>
                <hr class="hrTag" v-show="this.boardInfo.header === false">
              </b-col>
              <b-col v-show="this.boardInfo.header === false" cols="7" class="mt-2" >
                <b-form-input 
                  class="recruit_input"
                  v-show="this.boardInfo.header === false" 
                  v-if="editButton === true" type="number" 
                  v-model="boardInfo.recruitNumber" 
                  style="height: 30px; text-align: center;"
                  min="1" max="5"
                  @keypress="recruitLimit"
                  >
                </b-form-input>
                <p v-else v-show="this.boardInfo.header === false">{{ boardInfo.recruitNumber }}</p>
                <hr class="hrTag" v-show="this.boardInfo.header === false">
              </b-col>

            <b-col cols="5" class="mt-2">
              <p>스터디 분류</p>
              <hr class="hrTag">
            </b-col>
            <b-col cols="7" class="mt-2">
              <p>{{ boardInfo.studytypeName }}</p>
              <!-- 수정을 눌렀을 때 값 타입 가져오고 원래는 생성될때의 값 불러와야 함 -->
              <b-form-select v-if="editButton === true" v-show="this.boardInfo.header === true" v-model="studyTypeSelected" :options="options" id="studytype_name"></b-form-select>
              <hr class="hrTag" >
            </b-col>

            <b-col v-if="editButton === true" cols="5" class="mt-2">
              <p v-if="editButton === true" for="some-radios">상태</p>
              <hr class="hrTag">
            </b-col>
            <b-col v-if="editButton === true" cols="7">
              <div v-if="editButton === true">
                <label class="mx-3 mt-2"><input v-model="boardInfo.contentStatus" type="radio" name="contentStatus_0" value="false">진행중</label>
                <label class="mx-3 mt-2"><input v-model="boardInfo.contentStatus" type="radio" name="contentStatus_1" value="true">완료</label>
                <hr class="hrTag">
              </div>
            </b-col>

            <b-col cols="5" class="mt-2">
              <p>내용</p>
              <hr class="hrTag">
            </b-col>
            <b-col cols="7" class="mt-2">
              <b-textarea v-if="editButton === true" type="text" v-model="boardInfo.content" style="height: 80px;"></b-textarea>
              <p v-else>{{ boardInfo.content }}</p>
              <hr class="hrTag">
            </b-col>
            <!-- <p>여기 번호 : {{ studyInfo.studyNo }}</p> -->
          
        
            <!-- 작성자인 경우 수정을 보여주고 아니면 스터디 신청을 보여준다 -->

              <b-col cols="12" class="btnPart mt-2" v-show="this.boardInfo.header === false">
                <div v-show="editButton === false">
                  <b-button v-if="userInfo.user_id === loginUserId" variant="warning" size="sm" @click="boardFormEdit">수정</b-button>
                  <b-button v-else  @click="applyStudy" variant="warning" size="sm">스터디 신청</b-button>
                  <b-button class="backListBtn" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                </div>
                <div v-show="editButton === true" cols="3" class="btnPart mt-2">
                  <b-button v-if="editButton === true" variant="warning" size="sm" @click="updateForm">수정</b-button>
                  <b-button class="backListBtn" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                  <!-- <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="updateForm">취소</b-button> -->
                </div>
              </b-col>


          <!-- 스터디 구할 때 수정 부분 -->
              <b-col cols="12" class="btnPart mt-2" v-show="this.boardInfo.header !== false">
                <div v-show="editButton === false">
                  <b-button v-if="userInfo.user_id === loginUserId" variant="warning" size="sm" @click="boardFormEdit">수정</b-button>
                  <b-button class="backListBtn" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                </div>
                <div v-show="editButton === true" class="btnPart mt-2">
                  <b-button v-if="editButton === true" variant="warning" size="sm" @click="studyWantBoardFormEdit">수정</b-button>
                  <b-button class="backListBtn" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                  <!-- <b-button v-if="userInfo.user_id === loginUserId" style="background-color: #DAC7F9" @click="updateForm">취소</b-button> -->
                </div>
              </b-col>
            </b-row>
          </div>
            <div style="height: 30px;">
              <p></p>
            </div>
        </div>
      </center> 
    <comment/>
  </div>  
</template>

<script>
// import http from 'http'
import http from "@/util/http-common.js";
import Comment from '@/components/community/Comment.vue'

export default {
  name: 'BoardDetail',
  components: {
    Comment,
  },
  data() {
    return {
      savePosition: this.$store.state.saveCurrentPage,
      studyTypeSelected: null,
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
      options: [],
      // comments: null,
      // 스터디 방 번호 값 받아와야 함
      studyInfo: {
        studyName: null,
        studyNo: null,
        studyType: null,
      },
      applyMembers: [],
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

    // 인원 수 제한
    recruitLimit(event) {
      console.log(event, '이벤트 확인')
      if(event.key >= 0 && event.key <= 5) {
        return true;
      } alert('5명 까지 입력이 가능합니다');
        this.getBoard()
        return false;
      },

    goBoardMain() {
      this.$router.push({name: 'MainBoard', query: {pageId: this.savePosition}})
      console.log(this.savePosition)
      // this.$router.go(this.savePosition);
    },
    boardFormEdit() {
      this.editButton = true
    },

    // 스터디 신청
    applyStudy() {
      console.log(this.applyMembers.length, "신청 확인")
      for(var i=0; i < this.applyMembers.length; i++) {
          if (this.applyMembers[i].user_id === this.loginUserId) {
            alert('이미 신청한 스터디 입니다.')
            return
          }
        }
      http({
        method: 'post',
        url: `/study/applyMember/register/${this.studyInfo.studyNo}`,
        headers: this.getToken(),
      })
      .then((res) => {
        console.log(this.applyMembers)
        console.log(res)
        // 스터디장이 만든 스터디 이름 조회
        console.log(this.studyInfo.studyNo)
        // this.boardInfo.studyName = res.data['studyName']
        alert('신청이 완료되었습니다.')
        
      })
      .catch((err) => {
        console.log(err)
        console.log(this.studyInfo.studyNo)
      })
    },

    // 스터디 신청 멤버 조회
    getApplyMember() {
      http({
        method: 'GET',
        url: `/study/applyMember/searchAll/${this.studyInfo.studyNo}`
      })
      .then(res =>{
        console.log(res.data)
        if (res.data.length === 0) {
          this.applyMembers = null
        }
        else {
          this.applyMembers = res.data
        }
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 게시글 가져오기
    getBoard() {
      http({
        method: 'get',
        url: `/board/search/${this.board_no}`,
      })
      .then(res => {
        console.log(res, '여기보드')
        this.boardInfo.boardNo = res.data['boardNo']
        this.boardInfo.contentStatus = res.data['contentStatus']
        this.boardInfo.contentTitle = res.data['contentTitle']
        this.boardInfo.studyName = res.data['studyName']
        this.boardInfo.recruitNumber = res.data['recruitNumber']
        this.boardInfo.studytypeName = res.data['studytypeName']
        this.studyTypeSelected = res.data['studytypeName']
        this.boardInfo.content = res.data['content']
        this.boardInfo.header = res.data['header']
        this.userInfo.user_name = res.data.user['userName']
        this.userInfo.user_id = res.data.user['userId']
        this.studyInfo.studyNo = res.data['studyNo']
        if (this.boardInfo.header === false) {
          this.getApplyMember()
        }
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 게시글 삭제
    deleteBoardForm() {
      http({
        method: 'delete',
        url: `/board/remove/${this.board_no}`,
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
      http({
        method: 'GET',
        url: '/user/me',
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
        content_status: this.boardInfo.contentStatus,
        content_title: this.boardInfo.contentTitle,
        recruit_number: this.boardInfo.recruitNumber,
        study_name: this.boardInfo.studyName,
        studytype_name: this.boardInfo.studytypeName,
        user_id: this.userInfo.user_id,
        header: this.boardInfo.header,
      }
      http({
        method: 'put',
        url: '/board/update',
        data: updateItem,
        headers: this.getToken(),
      })
      .then(() => {
        this.getBoard()
        console.log('상태 확인')
        console.log(this.boardInfo.contentStatus)
        this.editButton = false
      })
      .catch(err => {
        console.log(err)
      })
      this.boardInfo.studyName = null
    },

    // 스터디 구할 때 수정
    studyWantBoardFormEdit() {
      const updateItem = {
        board_no: this.boardInfo.boardNo,
        content: this.boardInfo.content,
        content_status: this.boardInfo.contentStatus,
        content_title: this.boardInfo.contentTitle,
        recruit_number: this.boardInfo.recruitNumber,
        study_name: this.boardInfo.studyName,
        studytype_name: this.studyTypeSelected,
        user_id: this.userInfo.user_id,
        header: this.boardInfo.header,
      }
      http({
        method: 'put',
        url: '/board/update',
        data: updateItem,
        headers: this.getToken(),
      })
      .then(() => {
        this.getBoard()
        console.log('상태 확인')
        console.log(this.boardInfo.contentStatus)
        this.editButton = false
      })
      .catch(err => {
        console.log(err)
      })
      this.boardInfo.studyName = null
    },

    // 스터디 분류 가져오기
    getStudyType() {
      http({
        method: 'GET',
        url: '/study/studyType'
      })
      .then(res => {
        // console.log(res)
        res.data.forEach(element => {
          this.options.push({value: element.studytypeName, text:element.studytypeName})
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  created() {
    this.getBoard()
    this.getUserInfo()
    this.getStudyType()
  },
}
</script>

<style scoped>
p {
  color: black ;
  font-size: 1rem ;
}

.titleHr_tag {
  width: 80%;
  background-color: #afa2dd ;
  border: solid 2px #afa2dd;
}
.hrTag {
  background-color: #afa2dd;
  border: solid 1px #afa2dd;
  width: 100%;
}

.content_rowTag {
  height: 500px; 
  /* background-color: rgb(252, 252, 252); */
}

.memo {
  width: 150px; 
  height: 150px; 
  background-color: rgb(252, 252, 135);
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 150px;
  top: 250px;
  /* bottom: 100px; */
}

.memo1 {
  width: 200px; 
  height: 200px; 
  background-color: #f19cb0;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  right: 150px;
  top: 550px;
  /* bottom: 100px; */
}

.memo2 {
  width: 300px; 
  height: 300px; 
  background-color: #75cff3;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 120px;
  top: 470px;
  /* bottom: 100px; */
}

.memo3 {
  width: 250px; 
  height: 250px; 
  background-color: #caff08;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  right: 150px;
  top: 220px;
  /* bottom: 100px; */
}

.round_box {
  width: 15px;
  height: 15px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: rgb(255, 141, 10);
  position: absolute;
  left: 220px;
  top: 260px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
}

.round_box1 {
  width: 20px;
  height: 20px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: rgb(248, 64, 64);
  position: absolute;
  left: 50%;
  top: 260px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
  z-index: 1;
}

.round_box2 {
  width: 17px;
  height: 17px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: rgb(3, 184, 3);
  position: absolute;
  right: 240px;
  top: 560px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
}

.round_box3 {
  width: 18px;
  height: 18px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: yellow;
  position: absolute;
  left: 260px;
  top: 480px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
}

.round_box4 {
  width: 18px;
  height: 18px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: rgb(198, 82, 252);
  position: absolute;
  right: 270px;
  top: 240px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
}

.line4 {
  position: absolute;
  width: 30px;
  height: 120px;
  background-color: rgb(250, 171, 92);
  transform: rotate(-45deg);
  right: 38%;
  top: 220px;
  z-index: 1;
}

.line5 {
  position: absolute;
  width: 30px;
  height: 120px;
  background-color: rgb(250, 171, 92);
  transform: rotate(45deg);
  left: 38%;
  top: 220px;
  z-index: 1;
}

.backListBtn {
  width: 47px;
  height: 31px;
  background-color: #f45384 !important;
  border: none;
  margin-left: 3px;
  margin-right: 3px;
}

.backListBtn:hover {
  background-color: #f8bfd1 !important;
}

.body_total {
  /* background: repeating-linear-gradient(-45deg, #B96BC6, #B96BC6 10px, #e4c3f1 10px, #e4c3f1 40px); */
  /* background-image: url('https://thumb.ac-illust.com/72/72acde4a88378f62cf580ed7024d7a0d_t.jpeg');
  background-repeat: no-repeat;
  background-size: cover; */
  /* width: 800px; */
  /* font-size: 13px; */
  position: relative;
  background: rgb(249, 250, 250);
}

.total_body {
  /* background-image: url('https://thumb.ac-illust.com/72/72acde4a88378f62cf580ed7024d7a0d_t.jpeg');
  background-repeat: no-repeat;
  background-size: cover; */
  border: 30px solid;
  border-color: #afa2dd;
  background-color: #c8c1e4;
}

.btnPart{
  display: flex;
  justify-content: right;
  align-items: flex-end;
  border: none;
}
</style>