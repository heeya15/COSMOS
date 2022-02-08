<template>
  <center>
    <div class="container">

      <div class="black-bg" v-if="modal">
        <div class="white-bg">
          <h4>비밀번호 입력</h4>
          <b-form-input class="mb-3" style="width: 40%;" type="text" v-model="pwd"></b-form-input>
          <div>
            <button class="enterBtn mx-1"  @click="pwdCheck()">입장</button>
            <button class="cancelBtn mx-1" @click="modal=false"><span style="color:white;">취소</span></button>
          </div>
        </div>
      </div>

      <h1>Study Detail</h1>
      <!-- 스터디 정보 받아오기 -->
      <h2>스터디 이름: {{studyInfo.studyName}}</h2> 
      <span>스터디 방 URL : {{studyInfo.url}}</span>

      <!-- 권한있는 사람만 방입장 가능(세션생성 가능) -->
      <button class="enterBtn2" @click="modal=true">방 입장</button>

      <!-- 스터디 정보 수정 추가 -->
      <button class="modifyBtn" v-if="power.leader" @click="$bvModal.show('bv-modal-studyModify')">스터디 수정</button>

        <b-modal id="bv-modal-studyModify" centered hide-footer size="lg">
          <template #modal-title>
            <h3>스터디 정보 수정</h3>
          </template>
          <div class="d-block text-center">
            <b-row>
              <b-col cols="3">
                <label for="studyName" class="mt-2">스터디 이름</label>
              </b-col>
              <b-col>
                <b-form-input id="studyName" v-model="studyInfo.studyName"></b-form-input>
              </b-col>
            </b-row>
            <hr class="mt-3">

            <b-row>
              <b-col cols="4" class="pr-4">
                <label for="studyImg" class="mt-2">스터디 이미지</label>
              </b-col>
              <b-col>
                <b-form-input id="studyImg" v-model="studyInfo.image"></b-form-input>
              </b-col>
            </b-row>
            <hr class="mt-3">

            <b-row>
              <b-col cols="3" class="pr-4">
                <label for="studyPassword" class="mt-2">스터디 비밀번호</label>
              </b-col>
              <b-col cols="4">
                <b-form-input id="studyPassword" v-model="studyInfo.studyPassword"></b-form-input>
              </b-col>
            </b-row>
            <hr class="mt-3">

            <b-row>
              <b-col cols="3">
                <label for="studytypeNo" class="mt-2">스터디 타입</label>
              </b-col>
              <b-col cols="4">
                <b-form-select v-model="studyInfo.studyType.studytypeNo" :options="options" ></b-form-select>
              </b-col>
            </b-row>
            <hr class="mt-3">

            <b-row>
              <b-col cols="3">
                <label for="totalMember" class="mt-2">스터디 총 인원</label>
              </b-col>
              <b-col>
                <b-form-input id="totalMember" v-model="studyInfo.totalMember"></b-form-input>
              </b-col>
            </b-row>
            <hr class="mt-3">

            <b-row>
              <b-col cols="3">
                <label for="studyRule" class="mt-2">스터디 규칙</label>
              </b-col>
              <b-col>
                <b-form-textarea id="studyRule" v-model="studyInfo.studyRule" rows="3" max-rows="6"></b-form-textarea>
              </b-col>
            </b-row>
            <hr class="mt-3">
          </div>
          <div class="d-flex justify-content-center">
            <b-button class="m-2" variant="success" @click="updateStudy">수정</b-button>
            <b-button class="m-2" @click="$bvModal.hide('bv-modal-studyModify')">취소</b-button>
          </div>
        </b-modal>


      <b-button v-if="power.leader" variant="danger" @click="deleteStudy">스터디 삭제</b-button>
      <b-button v-else variant="danger" @click="deleteMember(myStudyMemberNo)">스터디 탈퇴</b-button>
      <div class="buttongroup d-flex justify-content-between" style="width:800px;">
        <b-button @click="togglenotice">공지사항</b-button>
        <b-button @click="toggleapply">가입 요청 확인 </b-button>

        <!-- 스터디장만 회원추가가능 -->
        <b-button @click="togglemember">스터디원 정보</b-button>
      </div>
      <hr>      
        <StudyNotice v-show="toggleNotice"/>
        <StudyApply v-show="toggleApply"/>
        <StudyMember v-show="toggleMember"/>
    </div>
  </center>
</template>

<script>
import StudyNotice from '@/components/study/StudyNotice.vue'
import StudyApply from '@/components/study/StudyApply.vue'
import StudyMember from '@/components/study/StudyMember.vue'

import JwtDecode from 'jwt-decode'

import http from "@/util/http-common.js";
import { mapState } from 'vuex'

export default {
  name: 'StudyDetail',
  components: {
    StudyNotice,
    StudyApply,
    StudyMember
  },
  data(){
    return {
      toggleNotice: true,
      toggleApply: false,
      toggleMember: false,
      studyNo: this.$route.params.studyNo,
      myStudyMemberNo: '',
      studyInfo: {
        studyType:{},
        // studyName: null,
        // url: null,
        // image: null,
        // totalMember: null,
        // numberOfMember: null, //현재 참여중인 스터디 인원
      },
      options:[],
      modal: false,
      pwd: "",
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
    togglenotice(){
      this.toggleNotice=true
      this.toggleApply=false
      this.toggleMember=false
    },
    toggleapply(){
      this.toggleNotice=false
      this.toggleApply=true
      this.toggleMember=false
    },
    togglemember(){
      this.toggleNotice=false
      this.toggleApply=false
      this.toggleMember=true
    },
    getStudyInfo() {
      http({
        method: 'GET',
        url: `/study/search/${this.studyNo}`
      })
      .then(res => {        
        this.studyInfo = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteStudy() {
      http({
        method: 'DELETE',
        url: `/study/remove/${this.studyNo}`
      })
      .then(() => {
        this.$router.push({name:'MyPage'})
      })
      .catch(err => {
        console.log(err)
      })
    },
    pwdCheck(){ // 방 입장시 비번 체크
      if(this.pwd == this.studyInfo.studyPassword){
        var token = localStorage.getItem('jwt')
        var decoded = JwtDecode(token);
        var myId = decoded.sub;

        this.$store.state.roomName = this.studyInfo.studyName;

        var str = this.studyInfo.url;
        var urlLen = this.studyInfo.url.length;
        var url = str.substr(22,urlLen);
        

        // console.log(str);
        // console.log(urlLen);
        // console.log(url);

        this.$store.state.roomUrl = url;
        this.$store.state.roomStudyNo = this.studyNo;
        this.$store.state.participant = myId;

        // console.log(this.roomName);
        console.log(this.roomUrl);
        // console.log(this.roomStudyNo);
        // console.log(this.participant);
        console.log("😃");
       // 비밀번호 치고 방 입장 성공 시 비공개 스터디 참가자 등록 시킴
        http({
            method: 'POST',
            url: `/privateroom/register`,
            headers: this.getToken(),
            params: {privatestudyroom_id: this.roomUrl},
          })
        .then(() => {
            this.$router.push({name: "Openvidu"})
          })
          .catch(err => {
            console.log(err)
          });  
      }else{
        alert("비밀번호가 틀렸습니다.")
      }
    },
    updateStudy() {
      const modifyInfo = {
        studyNo: this.$route.params.studyNo,
        studyName: this.studyInfo.studyName,
        image: this.studyInfo.image,
        studyPassword: this.studyInfo.studyPassword,
        studyRule: this.studyInfo.studyRule,
        studytypeNo: this.studyInfo.studyType.studytypeNo,
        totalMember: this.studyInfo.totalMember,
        url: this.studyInfo.url,
      }
      http({
        method: 'PUT',
        url: '/study/update',
        data: modifyInfo
      })
      .then(() => {
        // console.log(res)
        this.$bvModal.hide('bv-modal-studyModify')
      })
      .catch(err => {
        console.log(err)
      })
    },
    getStudyType() {
      http({
        method: 'GET',
        url: '/study/studyType'
      })
      .then(res => {
        // console.log(res)
        res.data.forEach(element => {
          this.options.push({value: element.studytypeNo, text:element.studytypeName})
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
    getStudyMemberNo(){
      this.$store.dispatch('getStudyMembers', this.studyNo)

      var token = localStorage.getItem('jwt')
      var decoded = JwtDecode(token);
      var myId = decoded.sub;
      this.studyMembers.forEach(member => {
        if(member.user_id === myId){
          this.myStudyMemberNo = member.studymember_no
        }
      })
    },
    deleteMember(studymember_no) {
      http({
        method: 'DELETE',
        url: `/studymember/remove/${studymember_no}`
      })
      .then(() => {
        // console.log(res)
        this.$router.push({name: 'MyPage'})
      })
      .catch(err => {
        console.log(err)
      })
    },
    // preventNav(event) {
    //   event.preventDefault()
    //   event.returnValue = ""
    // },
  },
  // beforeMount() {
  //   window.addEventListener("beforeunload", this.preventNav)
  // },
  // beforeDestroy() {
  //   window.removeEventListener("beforeunload", this.preventNav);
  // },
  computed:{
    ...mapState([
      'power', 'roomName', 'roomUrl', 'participant', 'roomStudyNo', 'studyMembers'
    ])
  },
  created() {
    this.getStudyInfo()
    this.getStudyType()
    this.getStudyMemberNo()
  }
}
</script>

<style scoped>
.black-bg{
  z-index: 2;
  width: 100vw;
  margin-left: calc(-50vw + 50%);
  height: 100vw;
  background: rgba(0,0,0,0.5);
  position: fixed;
  left: 0;
  top: 0;
  padding: 20px;
}
.white-bg{
	margin-top: 10%;
	z-index: 3;
  width: 30%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}

* {
  font-family:'yg-jalnan';
}

.modifyBtn {
  border: none;
  border-radius: 8px;
  background-color: #ffc107;
  height: 40px;
  width: 110px;
}
.modifyBtn:hover {
  background-color: #e2ab07;
}

.enterBtn {
  border: none;
  border-radius: 8px;
  background-color: #e4c3f1;
  height: 40px;
  width: 50px;
}
.enterBtn2 {
  border: none;
  border-radius: 8px;
  background-color: #e4c3f1;
  height: 40px;
  width: 80px;
}
.enterBtn:hover, .enterBtn2:hover {
  background-color: #ddaae6;
}

.cancelBtn {
  border: none;
  border-radius: 8px;
  background-color: #6c757d;
  height: 40px;
  width: 50px;
}
.cancelBtn:hover {
  background-color: #495057;
}
</style>