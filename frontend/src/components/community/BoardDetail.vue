<template>
  <div>
    <center>
    <div class="total_body" style="heigt: 100%"> 
      <h1>스터디 모집 게시판</h1>
      <hr class="titleHr_tag">
      <h3 v-if="editButton === true">글 수정</h3>
      <h3 v-else>상세보기</h3>

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
      
        <div class="body_total p-5" style="width: 600px; height: 65vh;">
          <!-- <hr class="hrTag"> -->
          <b-row class="content_rowTag mt-3">
            <b-col cols="3" class="mt-3">
              <p>제목</p>
              <hr class="hrTag">
            </b-col>
            <b-col cols="9" class="mt-3">
              <b-form-input v-if="editButton === true" type="text" v-model="boardInfo.contentTitle" style="height: 35px; text-align: center;" maxlength="30"></b-form-input>
              <p v-else>{{ boardInfo.contentTitle }}</p>
              <hr class="hrTag">
            </b-col>

              <b-col v-show="this.boardInfo.header === false" cols="3" class="mt-2">
                <p v-show="this.boardInfo.header === false" >스터디 이름</p>
                <hr class="hrTag" v-show="this.boardInfo.header === false">
              </b-col>
              <b-col v-show="this.boardInfo.header === false" cols="9" class="mt-2">
                <p>{{ boardInfo.studyName }}</p>
                <hr class="hrTag" v-show="this.boardInfo.header === false">
              </b-col>

              <b-col v-show="this.boardInfo.header === false" cols="3" class="mt-2">
                <p v-show="this.boardInfo.header === false">모집 인원</p>
                <hr class="hrTag" v-show="this.boardInfo.header === false">
              </b-col>
              <b-col v-show="this.boardInfo.header === false" cols="9" class="mt-2" >
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

            <b-col cols="3" class="mt-2">
              <p>스터디 분류</p>
              <hr class="hrTag">
            </b-col>
            <b-col cols="9" class="mt-2">
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

            <b-col cols="3" class="mt-3">
              <p>내용</p>
              <hr class="hrTag">
            </b-col>
            <b-col cols="9" class="mt-2">
              <b-textarea v-if="editButton === true" type="text" v-model="boardInfo.content" style="height: 80px;"></b-textarea>
              <p v-else style="white-space: pre-wrap; text-align: justify;">{{ boardInfo.content }}</p>
              <hr class="hrTag">
            </b-col>
          
        
            <!-- 작성자인 경우 수정을 보여주고 아니면 스터디 신청을 보여준다 -->

              <b-col cols="12" class="btnPart mt-2" v-show="this.boardInfo.header === false">
                <div v-show="editButton === false">
                  <b-button class="updateBtn" v-if="userInfo.user_id === loginUserId" variant="warning" size="sm" @click="boardFormEdit" >수정</b-button>
                  <b-button class="updateBtn" v-else  @click="applyStudy" variant="warning" size="sm">스터디 신청</b-button>
                  <b-button class="backListBtn mx-1" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button class="deleteBtn" v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                </div>
                <div v-show="editButton === true" cols="3" class="btnPart mt-2">
                  <b-button class="updateBtn" v-if="editButton === true" variant="warning" size="sm" @click="updateForm">수정</b-button>
                  <b-button class="backListBtn mx-1" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button v-if="userInfo.user_id === loginUserId" size="sm" @click="updatedCancel" class="cancelBtn mr-1">취소</b-button>
                  <b-button class="deleteBtn" v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                </div>
              </b-col>


          <!-- 스터디 구할 때 수정 부분 -->
              <b-col cols="12" class="btnPart mt-2" v-show="this.boardInfo.header !== false">
                <div v-show="editButton === false">
                  <b-button class="updateBtn" v-if="userInfo.user_id === loginUserId" variant="warning" size="sm" @click="boardFormEdit">수정</b-button>
                  <b-button class="backListBtn mx-1" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button class="deleteBtn" v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                </div>
                <div v-show="editButton === true" class="btnPart mt-2">
                  <b-button class="updateBtn" v-if="editButton === true" variant="warning" size="sm" @click="studyWantBoardFormEdit">수정</b-button>
                  <b-button class="backListBtn mx-1" size="sm" @click="goBoardMain">목록</b-button>
                  <b-button v-if="userInfo.user_id === loginUserId" size="sm" @click="updatedCancel" class="cancelBtn mr-1">취소</b-button>
                  <b-button class="deleteBtn" v-if="userInfo.user_id === loginUserId" variant="danger" size="sm" @click="deleteBoardForm">삭제</b-button>
                </div>
              </b-col>
            </b-row>
          <!-- <hr> -->
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
import http from "@/util/http-common.js";
import Comment from '@/components/community/Comment.vue'
import {mapState} from 'vuex'

export default {
  name: 'BoardDetail',
  components: {
    Comment,
  },
  data() {
    return {
      timeOut: null,
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

      },
      options: [],

      studyInfo: {
        studyName: null,
        studyNo: null,
        studyType: null,
      },
      applyMembers: [],
      studyTotalMember: null,
      nowStudyMember: null,
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

    // 게시판으로
    goBoardMain() {
      this.$router.push({name: 'MainBoard', query: {pageId: this.savePosition}})
      console.log(this.savePosition)
      // this.$router.go(this.savePosition);
    },

    // 게시판 수정 버튼
    boardFormEdit() {
      this.editButton = true
    },

    
      
    // 스터디 신청
    applyStudy() {
      console.log(this.studyTotalMember, "신청 확인")
      console.log(this.nowStudyMember, '현재 멤버 수')
      if (this.studyTotalMember <= this.nowStudyMember) {
        alert('이미 인원이 가득찬 스터디입니다.')
        return
      }
      if (this.applyMembers !== null) {
        for(var i=0; i < this.applyMembers.length; i++) {
            if (this.applyMembers[i].user_id === this.loginUserId) {
              alert('이미 신청한 스터디 입니다.')
              return
            }
          }
      }
      http({
        method: 'post',
        url: `/study/applyMember/register/${this.studyInfo.studyNo}`,
        headers: this.getToken(),
      })
      .then(() => {
        this.getApplyMember()
        // 스터디장이 만든 스터디 이름 조회
        alert('신청이 완료되었습니다.')
        
        
      })
      .catch((err) => {
        console.log(err)
        console.log(this.studyInfo.studyNo)
      })
    },
    // 스터디 멤버 확인
    getStudyMembers() {
      this.$store.dispatch('getStudyMembers', this.studyInfo.studyNo)
    },

    getStudyInfo() {
      http({
        method: 'GET',
        url: `/study/search/${this.studyInfo.studyNo}`
      })
      .then(res => {
        console.log(this.studyTotalMember, '멤버 확인')
        this.studyTotalMember = res.data.totalMember
        this.nowStudyMember = res.data.numberOfMember
        console.log(this.nowStudyMember, '>>>>작동확인<<<<')        
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 스터디 신청 멤버 조회
    getApplyMember() {
      http({
        method: 'GET',
        url: `/study/applyMember/searchAll/${this.studyInfo.studyNo}`
      })
      .then(res =>{
        console.log(res.data, '신청 멤버 조회')
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

    // 수정 취소
    updatedCancel() {
      this.getBoard()
      this.editButton = false
    },

    // 게시글 가져오기
    getBoard() {
      http({
        method: 'get',
        url: `/board/search/${this.board_no}`,
      })
      .then(res => {
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
          this.getStudyInfo()
          this.getApplyMember()
          this.getStudyMembers()
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
      .then(() => {
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
        res.data.forEach(element => {
          this.options.push({value: element.studytypeName, text:element.studytypeName})
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  computed: {
      ...mapState([
        'studyMembers',
      ])
    },

  created() {
    this.getBoard()
    this.getUserInfo()
    this.getStudyType()
    // this.getStudyMembers()
    // this.getStudyInfo()
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
  border: solid 1px #afa2dd;
}
.hrTag {
  background-color: #afa2dd;
  border: solid 0.5px #afa2dd;
  width: 100%;
}

.content_rowTag {
  height: 500px; 
}

.detail-content {
  display: flex; 
  justify-content: left;
  margin-top: 3%;
  margin-left: 3%;
}

.memo {
  width: 10%; 
  height: 20%; 
  background-color: rgb(252, 252, 57);
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 220px;
  top: 330px;
  border-radius: 4px;
  box-shadow: 3px 2px 2px 1px #a8a8a8;
}

.memo1 {
  width: 13%; 
  height: 25%; 
  background-color: #f19cb0;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  right: 180px;
  top: 600px;
  border-radius: 4px;
  box-shadow: 3px 2px 2px 1px #a8a8a8;
}

/* .memo2 {
  width: 17%; 
  height: 33%; 
  background-color: #75cff3;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 170px;
  top: 570px;
  border-radius: 4px;
  box-shadow: 3px 2px 2px 1px #a8a8a8;
} */

.memo3 {
  width: 15%; 
  height: 30%; 
  background-color: #caff08;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  right: 200px;
  top: 280px;
  border-radius: 4px;
  box-shadow: 3px 2px 2px 1px #a8a8a8;
}


.round_box {
  width: 15px;
  height: 15px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: rgb(255, 141, 10);
  position: absolute;
  left: 290px;
  top: 340px;
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
  top: 300px;
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
  right: 280px;
  top: 610px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
}

/* .round_box3 {
  width: 18px;
  height: 18px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: yellow;
  position: absolute;
  left: 300px;
  bottom: 320px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
} */

.round_box4 {
  width: 18px;
  height: 18px; 
  border-radius: 70%;
  overflow: hidden;
  background-color: rgb(198, 82, 252);
  position: absolute;
  right: 310px;
  top: 290px;
  box-shadow: 2px 2px 1px 1px rgb(215, 218, 218);
}

.container {
  width: 70%;
  /* background-color: #fafafa; */
  /* border: 10px solid #afa2dd; */
  /* box-shadow: 3px 3px 1px 1px #afa2ddd0; */
  /* margin-top: 30px; */
}

.backListBtn {
  width: 47px;
  height: 31px;
  background-color: #f45384 !important;
  border: none;
  /* color: black; */
  color: white;
}

.backListBtn:hover {
  background-color: #f8bfd1 !important;
}

.body_total {
  position: relative;
  background: rgb(249, 250, 250);
  border-radius: 4px;
  box-shadow: 2px 2px 2px 2px #a8a8a8;
}

.total_body {
  border: 30px solid;
  border-color: #afa2dd;
  background-color: #c8c1e4;
  width: 90%;
  height: 100%;
}

.btnPart{
  display: flex;
  justify-content: right;
  align-items: flex-end;
  border: none;
}

.cancelBtn {
  width: 47px;
  height: 31px;
  background-color: rgb(71, 166, 255);
  border: none;
  /* color: black; */
  color: white;
}

.cancelBtn:hover {
  width: 47px;
  height: 31px;
  background-color: rgb(112, 184, 252);
}

.deleteBtn {
  /* color: black; */
  color: white;
}

.updateBtn {
  color: white;
}

.updateBtn:hover {
  color: white;
}
</style>