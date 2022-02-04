<template>
  <center>
    <div style="width:1200px;">
      <h1>Study Detail</h1>
      <!-- 스터디 정보 받아오기 -->
      <h2>스터디 이름: {{studyInfo.studyName}}</h2> 
      <span>스터디 방 URL : {{studyInfo.url}}</span>

      <button @click="enterRoom">방 입장</button>

      <!-- 스터디 정보 수정 추가 -->
      <b-button v-if="power.leader" variant="danger">스터디 수정</b-button>
      <b-button v-if="power.leader" variant="danger" @click="deleteStudy">스터디 삭제</b-button>
      <b-button v-else variant="danger">스터디 탈퇴</b-button>
      <div class="buttongroup d-flex justify-content-between" style="width:800px;">
        <b-button @click="togglenotice">공지사항</b-button>
        <!-- 스터디장만 가입요청확인 가능 -->
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
        studyName: null,
        url: null,
        image: null,
        totalMember: null,
        numberOfMember: null, //현재 참여중인 스터디 인원
      }
    }
  },
  methods: {
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
        // console.log(res)
        this.studyInfo.studyName = res.data.studyName
        this.studyInfo.url = res.data.url
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
    enterRoom(){
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
      // console.log(this.roomUrl);
      // console.log(this.roomStudyNo);
      // console.log(this.participant);

      this.$router.push({name: "Openvidu"})
    },
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

<style>

</style>