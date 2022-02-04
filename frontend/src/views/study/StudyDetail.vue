<template>
  <center>
    <div class="container">

      <div class="black-bg" v-if="modal">
        <div class="white-bg">
          <h4>비밀번호 입력</h4>
          <b-form-input class="mb-3" style="width: 40%;" type="text" v-model="pwd"></b-form-input>
          <div>
            <button class="mx-1" style="width:40px; height: 30px" @click="pwdCheck()">입장</button>
            <button class="mx-1" style="width:40px; height: 30px" @click="modal=false">취소</button>
          </div>
        </div>
      </div>

      <h1>Study Detail</h1>
      <!-- 스터디 정보 받아오기 -->
      <h2>스터디 이름: {{studyInfo.studyName}}</h2> 
      <span>스터디 방 URL : {{studyInfo.url}}</span>

      <!-- 권한있는 사람만 방입장 가능(세션생성 가능) -->
      <button @click="modal=true">방 입장</button>

      <!-- 스터디 정보 수정 추가 -->
      <button type="button" v-if="power.leader" @click="$bvModal.show('bv-modal-example')">스터디 수정</button>

        <b-modal id="bv-modal-example" hide-footer>
          <template #modal-title>
            스터디 정보 수정
          </template>
          <div class="d-block text-center">
            <b-col cols="3">
              <label for="studyName" class="mt-2">스터디 이름</label>
            </b-col>
            <b-col>
              <b-form-input id="studyName" v-model="studyInfo.studyName"></b-form-input>
            </b-col>
            <hr class="mt-3">

            <b-col cols="3">
              <label for="studyImg" class="mt-2">스터디 이미지</label>
            </b-col>
            <b-col>
              <b-form-input id="studyImg" v-model="studyInfo.image"></b-form-input>
            </b-col>
            <hr class="mt-3">

            <b-col cols="">
              <label for="studyPassword" class="mt-2">스터디 비밀번호</label>
            </b-col>
            <b-col>
              <b-form-input id="studyPassword" v-model="studyInfo.studyPassword"></b-form-input>
            </b-col>
            <hr class="mt-3">

            <b-col cols="3">
              <label for="studyRule" class="mt-2">스터디 규칙</label>
            </b-col>
            <b-col>
              <b-form-input id="studyRule" v-model="studyInfo.studyRule"></b-form-input>
            </b-col>
            <hr class="mt-3">

            <b-col cols="3">
              <label for="studytypeNo" class="mt-2">스터디 타입</label>
            </b-col>
            <b-col>
              <b-form-input id="studytypeNo" v-model="studyInfo.studyType.studytypeNo"></b-form-input>
            </b-col>
            <hr class="mt-3">

            <b-col cols="3">
              <label for="totalMember" class="mt-2">스터디 총 인원</label>
            </b-col>
            <b-col>
              <b-form-input id="totalMember" v-model="studyInfo.totalMember"></b-form-input>
            </b-col>
            <hr class="mt-3">
          </div>
          <b-button class="mt-3" block @click="updateStudy">수정</b-button>
          <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-example')">취소</b-button>
        </b-modal>


      <b-button v-if="power.leader" variant="danger" @click="deleteStudy">스터디 삭제</b-button>
      <b-button v-else variant="danger">스터디 탈퇴</b-button>
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

import axios from 'axios'
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
      studyInfo: {
        // studyName: null,
        // url: null,
        // image: null,
        // totalMember: null,
        // numberOfMember: null, //현재 참여중인 스터디 인원
      },
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
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/study/search/${this.studyNo}`
      })
      .then(res => {
        console.log(res)
        // this.studyInfo.studyName = res.data.studyName
        // this.studyInfo.url = res.data.url
        this.studyInfo = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteStudy() {
      axios({
        method: 'DELETE',
        url: `http://i6e103.p.ssafy.io:8080/api/study/remove/${this.studyNo}`
      })
      .then(() => {
        this.$router.push({name:'MyPage'})
      })
      .catch(err => {
        console.log(err)
      })
    },
    pwdCheck(){
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
        axios({
            method: 'POST',
            url: `http://i6e103.p.ssafy.io:8080/api/privateroom/register`,
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
      axios({
        method: 'PUT',
        url: 'http://i6e103.p.ssafy.io:8080/api/study/update',
        data: modifyInfo
      })
      .then(res => {
        console.log(res)
        this.$bvModal.hide('bv-modal-example')
      })
      .catch(err => {
        console.log(modifyInfo)
        console.log(err)
      })
    }
  },
  computed:{
    ...mapState([
      'power', 'roomName', 'roomUrl', 'participant', 'roomStudyNo'
    ])
  },
  created() {
    this.getStudyInfo()
  }
}
</script>

<style scoped>

.black-bg{
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
  width: 30%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}

</style>