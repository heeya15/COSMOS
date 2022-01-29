<template>
  <center>
    <h1>MY PAGE</h1>
    <div class="profile">
      <div>아이디: {{user_id}}</div>
      <div>이름: {{user_name}}</div>
      <div>이메일: {{user_email}}</div>
      <b-button variant="danger" class="me-3" @click="signOut">회원탈퇴</b-button>
      <b-button variant="warning" @click="changePassword">비밀번호 변경</b-button>

      <!-- 비밀번호 일치하면, 비밀번호 수정가능 -->
      <!-- My study는 해당멤버 스터디 조회 사용(token 실어서 보내기) -->
      <div>MY STUDY</div>
      <div v-if="user_study">
        <div v-for="study in user_study" :key="study.id" @click="goStudyManage(study.studyNo)">{{study.studyName}}</div>
      </div>
      <div v-else>아직 가입한 스터디가 없습니다.</div>
    </div>
  </center>
</template>

<script>
import axios from 'axios'

export default {
  name: 'MyPage',
  data() {
    return {
    user_id : null,
    user_name : null,
    user_email : null,
    user_password : null,
    user_study: null,
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
    getUserInfo(){
      axios({
        method: 'GET',
        url: 'http://i6e103.p.ssafy.io:8080/api/user/me',
        headers: this.getToken()
      })
      .then(res =>{
        // console.log(res)
        this.user_id=res.data['user_id']
        this.user_name=res.data['user_name']
        this.user_email=res.data['user_email']
        this.user_password=res.data['user_password']
        this.getMyStudy()
      })
      .catch(err =>{
        console.log(err)
      })
    },
    signOut(){
      axios({
        method: 'DELETE',
        url: `http://i6e103.p.ssafy.io:8080/api/user/remove/${this.user_id}`,
        headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.$store.dispatch('logOut')
        this.$router.push({ name:'SignUp' })
        alert('COSMOS에서 회원탈퇴완료')
      })
      .catch(err => {
        console.log(err)
      })
    },
    changePassword(){
      this.$router.push({name:'ChangePasswordForm'})
    },
    // router 파라미터 넣어서 보내기
    // router.push({name:'StudyDetail', params:{studyNo: this.studyNo}})
    
    // header에 토큰넣어서 보내는건가??? 
    getMyStudy() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/study/memberStudy/${this.user_id}`
      })
      .then(res => {
        // console.log(res.data)
        this.user_study = res.data        
      })
      .catch(err => {
        console.log(err)
      })
    },
    goStudyManage(study){
      this.$router.push({name:'StudyDetail', params:{studyNo: study}})
    }
  },
  created(){
    this.getUserInfo()
  },
}
</script>

<style scoped>
.profile {
  width: 1000px;
  border: 1px solid rgb(204, 143, 143);
}
</style>