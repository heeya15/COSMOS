<template>
  <center>
    <h1>MY PAGE</h1>
    <div class="profile">
    <hr>
      <div class="banner">
        <b-row>
          <b-col>
            <img src="@/assets/img/study/working_at_home.png" alt="프로필" style="height: 200px; width:200px;">
          </b-col>
          <b-col class="p-4">
            <b-row class="my-2">
              <div>아이디: {{user_id}}</div>
            </b-row>
            <b-row class="my-2">
              <div>이름: {{user_name}}</div>
            </b-row>
            <b-row class="my-2">
              <div>이메일: {{user_email}}</div>
            </b-row>
            <b-row class="mt-3">
              <b-button variant="danger" class="mr-3" @click="signOut">회원탈퇴</b-button>
              <b-button variant="warning" @click="togglePwd">비밀번호 변경</b-button>
            </b-row>
          </b-col>
        <!-- 비밀번호 일치하면, 비밀번호 수정가능 -->
        <div v-show="togglePassword" class="passwordBox">
          <hr>
          <b-row class="mt-3 ml-2">
            <b-col cols="8"><b-form-input type="password" style="height:50px;" id="userpw" v-model="user_pw" required placeholder="현재 비밀번호를 입력하세요."></b-form-input></b-col>
            <b-col><button class="checkBtn mt-2" @click="checkPassword">확인</button></b-col>
          </b-row>
          <div v-show="userpwCheck" class="mx-4">
            <b-form-input class="mt-3" style="height:50px;" type="password" id="userpw1" v-model="user_password" placeholder="변경할 비밀번호를 입력하세요." @blur="passwordRuleCheck" required></b-form-input>
            <div ref="pwCheckMsg"></div>
            <b-form-input class="mt-3" style="height:50px;" type="password" id="userpw2" v-model="user_password2" placeholder="변경할 비밀번호 확인" :state="pwState" required></b-form-input>
            <div>
              <b-button variant="warning" class="mt-3 mr-2" @click="changePassword">변경</b-button>
              <b-button class="ms-3 mt-3" @click="[togglePassword=false,userpwCheck=false]">취소</b-button>
            </div>
          </div>
        </div>
        </b-row>
      </div>

      <hr class="mt-5">
      <!-- My study-->
      <div class="container">
        <div class="py-3">
          <h3 class="font-weight-bold mb-0">My Study</h3>
          <p class="font-italic text-muted mb-4">현재 가입한 스터디 입니다.</p>

          <div v-if="user_study.length >= 1" >
            <div class="row">
              <div v-for="study in user_study" :key="study.id" @click="goStudyManage(study.studyNo)" class="col-md-4 mb-3">
                <div class="hover hover-1 text-white rounded">
                  <img src="https://bootstrapious.com/i/snippets/sn-img-hover/hoverSet-3.jpg" alt="기본이미지" v-if="study.image.length<24">
                  <img :src="study.image" alt="스터디이미지" v-else>
                  <div class="hover-overlay"></div>
                  <div class="hover-1-content px-5 py-4">
                    <h4 class="hover-1-title text-uppercase mb-0"><span>{{study.studyName}}</span></h4>
                    <p class="hover-1-description mb-1">{{study.studyType.studytypeName}}</p>
                  </div>
                </div>
              </div>
          </div>
          </div>
          <div v-else>아직 가입한 스터디가 없습니다.</div>
        </div>
      </div>
    </div>
  </center>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: 'MyPage',
  data() {
    return {
      user_id : null,
      user_name : null,
      user_email : null,
      user_study: [],
      togglePassword: false,
      userpwCheck: false,
      user_pw: null,
      pwdRule: null,
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
      http({
        method: 'GET',
        url: '/user/me',
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
      http({
        method: 'DELETE',
        url: `/user/remove/${this.user_id}`,
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
    getMyStudy() {
      http({
        method: 'GET',
        url: '/study/memberStudy',
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
    // 스터디 상세페이지 가면서, 로그인한 유저가 클릭한 스터디의 리더인지 아닌지 판별
    goStudyManage(study){
      this.$router.push({name:'StudyDetail', params:{studyNo: study}})
      this.$store.dispatch('isLeader', study)
    },
    
    togglePwd(){
      this.togglePassword = !this.togglePassword
      if (this.user_pw || this.user_password || this.user_password2) {
        this.user_pw = ''
        this.user_password = ''
        this.user_password2 = ''
        this.$refs.pwCheckMsg.innerText = '';
      }
    },
    checkPassword() {
      http({
        method: 'GET',
        url: '/user/password',
        params: {user_password:this.user_pw},
        headers: this.getToken()
      })
      .then(() => {
        // console.log(res)
        this.userpwCheck = true
      })
      .catch(err => {
        console.log(err)
        // console.log(this.token)
        alert('현재 비밀번호와 입력하신 비밀번호가 다릅니다.')
        this.user_pw=''
      })
    },
    async passwordRuleCheck() {
      var rule = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/;

      if(!rule.test(this.user_password)) {
        this.$refs.pwCheckMsg.innerText = '비밀번호는 8 ~ 12 자리수이며 문자, 숫자, 특수기호를 최소 1개씩 포함해야합니다.';
        this.pwdRule = false;
      } else {
        this.$refs.pwCheckMsg.innerText = '';
        this.pwdRule = true;
      }
    },
    changePassword(){
      window.onbeforeunload = null;
      if (this.pwdRule === false){
        alert('비밀번호를 조건에 맞게 변경해주세요!')
        return 
      }
      const userInfo = {
        user_id: this.user_id,
        user_name: this.user_name,
        user_password: this.user_password
      }
      http({
        method: 'PUT',
        url: '/user/update',
        data: userInfo
      })
      .then(() => {
        // console.log(res)
        alert('비밀번호가 변경되었습니다.')
        this.$router.go()
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  created(){
    this.getUserInfo()
  },
  computed: {
    pwState(){
      if (!this.user_password2){
        return null
      } else {
        return this.user_password===this.user_password2 ? true : false
      }
    }
  }
}
</script>

<style scoped>
.profile {
  width: 1000px;
}

/* user 정보 박스 */
.banner {
  width: 70%;
  height: 60%;
  background-color: #e0dfdf;
  margin: auto;
  border-radius: 30px;
  position: relative;
  padding: 30px;
}

.passwordBox {
  width: 100%;
}

.checkBtn {
  border: none;
  border-radius: 8px;
  background-color: #e4c3f1;
  height: 40px;
  width: 50px;
}

/* 스터디 이미지 */
.hover {
  overflow: hidden;
  position: relative;
  padding-bottom: 60%;
}

.hover-overlay {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 90;
  transition: all 0.4s;
}

.hover img {
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  transition: all 0.3s;
}

.hover-content {
  position: relative;
  z-index: 99;
}

.hover-1 img {
  width: 105%;
  position: absolute;
  top: 0;
  left: -5%;
  transition: all 0.3s;
}

.hover-1-content {
  width:100%;
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 98;
  transition: all 0.4s;
}

.hover-1 .hover-overlay {
  background: rgba(0, 0, 0, 0.5);
}

.hover-1-description {
  transform: translateY(0.5rem);
  transition: all 0.4s;
  opacity: 0;
}

.hover-1:hover .hover-1-content {
  bottom: 2rem;
}

.hover-1:hover .hover-1-description {
  opacity: 1;
  transform: none;
}

.hover-1:hover img {
  left: 0;
}

.hover-1:hover .hover-overlay {
  opacity: 0;
}


</style>