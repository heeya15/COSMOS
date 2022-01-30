<template>
  <center>
    <h1>MY PAGE</h1>
    <div class="profile">
      <div>아이디: {{user_id}}</div>
      <div>이름: {{user_name}}</div>
      <div>이메일: {{user_email}}</div>
      <b-button variant="danger" class="me-3" @click="signOut">회원탈퇴</b-button>
      <b-button variant="warning" @click="togglePassword=true">비밀번호 변경</b-button>

      <!-- 비밀번호 일치하면, 비밀번호 수정가능 -->
      <div v-show="togglePassword" style="width: 600px;">
        <b-form-input class="mt-3" type="password" style="height:50px;" id="userpw" v-model="user_pw" required placeholder="현재 비밀번호를 입력하세요."></b-form-input>
        <button @click="checkPassword">확인</button>
        <div v-show="userpwCheck">
          <b-form-input class="mt-3" style="height:50px;" id="userpw1" v-model="user_password" required placeholder="변경할 비밀번호를 입력하세요."></b-form-input>
          <b-form-input class="mt-3" style="height:50px;" id="userpw2" v-model="user_password2" required placeholder="변경할 비밀번호 확인" :state="pwState"></b-form-input>
          <b-button variant="primary" class="mt-5" @click="changePassword">변경</b-button>
          <b-button class="ms-3 mt-5">취소</b-button>
        </div>
      </div>
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
      user_study: null,
      togglePassword: false,
      userpwCheck: false,
      user_pw: null,
      user_password : null,
      user_password2: null,
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
      const userInfo = {
        user_id: this.user_id,
        user_name: this.user_name,
        user_password: this.user_password
      }
      axios({
        method: 'PUT',
        url: 'http://i6e103.p.ssafy.io:8080/api/user/update',
        data: userInfo
      })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    },
    getMyStudy() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/study/memberStudy/`,
        headers: this.getToken()
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
    },
    checkPassword() {
      const header = {
        Authorization: `Bearer ${this.token}`
      }
      axios({
        method: 'GET',
        url: 'http://i6e103.p.ssafy.io:8080/api/user/password',
        params: {user_password:this.user_pw},
        headers: header,
      })
      .then(() => {
        // console.log(res)
        this.userpwCheck = true
      })
      .catch(err => {
        console.log(err)
        alert('현재 비밀번호와 입력하신 비밀번호가 다릅니다.')
      })
    }
  },
  created(){
    this.getUserInfo()
  },
  computed: {
    pwState(){
      return this.user_password===this.user_password2 ? true : false
    }
  }
}
</script>

<style scoped>
.profile {
  width: 1000px;
  border: 1px solid rgb(204, 143, 143);
}
</style>