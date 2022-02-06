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
          <b-form-input class="mt-3" style="height:50px;" type="password" id="userpw1" v-model="user_password" required placeholder="변경할 비밀번호를 입력하세요."></b-form-input>
          <b-form-input class="mt-3" style="height:50px;" type="password" id="userpw2" v-model="user_password2" required placeholder="변경할 비밀번호 확인" :state="pwState"></b-form-input>
          <b-button variant="primary" class="mt-5" @click="changePassword">변경</b-button>
          <b-button class="ms-3 mt-5">취소</b-button>
        </div>
      </div>
      <!-- My study는 해당멤버 스터디 조회 사용(token 실어서 보내기) -->

      <div class="container py-5">
        <!-- DEMO 1 -->
        <div class="py-5">
          <h3 class="font-weight-bold mb-0">My Study</h3>
          <p class="font-italic text-muted mb-4">현재 가입한 스터디 입니다.</p>

          <div v-if="user_study.length >= 1" >
            <div class="row">
            <div v-for="study in user_study" :key="study.id" @click="goStudyManage(study.studyNo)" class="col-md-4 mb-3 mb-lg-0">
              <!-- DEMO 1 Item -->
              <!-- <div class="col-lg-6 mb-3 mb-lg-0"> -->
                <div class="hover hover-1 text-white rounded"><img src="https://bootstrapious.com/i/snippets/sn-img-hover/hoverSet-3.jpg" alt="">
                  <div class="hover-overlay"></div>
                  <div class="hover-1-content px-5 py-4">
                    <h3 class="hover-1-title text-uppercase font-weight-bold mb-0"> <span class="font-weight-light">{{study.studyName}}</span></h3>
                    <p class="hover-1-description font-weight-light mb-0">{{study.studyRule}}</p>
                  </div>
                <!-- </div>  -->
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
// import http from 'http'
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
    changePassword(){
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
        console.log(this.token)
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


/* DEMO 1 ============================== */
.hover-1 img {
  width: 105%;
  position: absolute;
  top: 0;
  left: -5%;
  transition: all 0.3s;
}

.hover-1-content {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 99;
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