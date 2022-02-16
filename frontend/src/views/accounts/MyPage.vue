<template>
  <center>
    <h1>MY PAGE</h1>
    <div class="profile">
    <hr>
      <div class="banner">
        <b-row>
          <b-col>
            <img src="https://i.ibb.co/N1gPh3J/working-at-home.png" alt="프로필" style="height: 200px; width:200px;">
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

      <!-- <hr class="mt-5 mx-4"> -->
      <div class="score">
        <b-row class="mt-4" style="height:200px; margin-left: 0px;">
          <!-- <b-col cols="1"></b-col> -->
          <b-col cols="4" id="level">
            <div class="d-flex justify-content-center">
              <div class="mt-2" align="center" style="font-size: 15pt;">My 등급</div>
              <div class="ml-2" style="margin-top: 10px;"><img class="question" src="https://i.ibb.co/tsY4tg4/question.png" 
                title="등급 정보를 보려면 클릭해주세요." v-b-modal.modal-level></div>
            </div>
            <div style="margin-top: 30px;">
              <span class="mr-1" style="font-size: 20pt;">{{ myLevel[myInfoIdx] }}</span>
              <img class="mb-2" :src="myLevelImg[myInfoIdx]" style="width: 30px; height: 30px;" />
            </div>
            <div>
              <p>{{ myTotalTime }}</p>
            </div>
            <b-modal ref="myModal" id="modal-level" hide-header hide-footer size="m">
              <div class="pt-2 px-3 d-flex justify-content-between" style='font-family: "yg-jalnan";'>
                <h3>스터디 분류 수정</h3>
                <img id="exitBtn" src="https://i.ibb.co/GWXqhqv/close.png" alt="close" border="0" @click="$bvModal.hide('modal-level')">
              </div>
              
              <hr>
              
              <div class="px-2">
                <table class="table table-hover" style="width: 100%;">
                  <colgroup>
                    <col width="50%"/>
                    <col width="50%"/>
                  </colgroup>
                  <thead style="background-color: #afa2dd; font-family: yg-jalnan;">
                    <tr>
                      <th>등급</th>
                      <th>누적시간</th>
                    </tr>
                  </thead>
                  <tbody style="font-family: BMJua;">
                    <tr class="">
                      <td class="">Ruby <img class="smLevelImg" :src="myLevelImg[0]"></td>
                      <td>5년 초과</td>
                    </tr>
                    <tr>
                      <td>Diamond <img class="smLevelImg" :src="myLevelImg[1]"></td>
                      <td>~ 5년</td>
                    </tr>
                    <tr>
                      <td>Platinum <img class="smLevelImg" :src="myLevelImg[2]"></td>
                      <td>~ 3년</td>
                    </tr>
                    <tr>
                      <td>Gold <img class="smLevelImg" :src="myLevelImg[3]"></td>
                      <td>~ 1년 6개월 (547일)</td>      
                    </tr>
                    <tr>
                      <td>Silver <img class="smLevelImg" :src="myLevelImg[4]"></td>
                      <td>~ 9개월 (270일)</td>      
                    </tr>
                    <tr>
                      <td>Bronze <img class="smLevelImg" :src="myLevelImg[5]"></td>
                      <td>~ 3개월 (90일)</td>      
                    </tr>
                    <tr>
                      <td>코린이 <img class="smLevelImg" :src="myLevelImg[6]"></td>
                      <td>~ 1개월 (30일)</td>      
                    </tr>
                  </tbody>
                </table>
              </div>
          </b-modal>
          </b-col>
          <b-col cols="4" id="rank">
            <div class="d-flex justify-content-center">
              <div class="mt-2" align="center" style="font-size: 15pt;">My 랭킹</div>
              <div class="ml-2" style="margin-top: 10px;">
                <img class="question" src="https://i.ibb.co/tsY4tg4/question.png" title="전날 공부 데이터를 활용한 일별 랭킹입니다." v-b-modal.modal-rank>
              </div>
            </div>
            <div v-if="myRank == -1" style="margin-top: 40px;">
              <span>전날 놀았네요? 😛</span>
            </div>
            <div v-else style="margin-top: 10px;">
              <span style="font-size: 40pt;">{{ myRank }}</span><span> 위</span>
            </div>
            <div>{{ myDailyTime }}</div>
            <b-modal ref="myModal" id="modal-rank" hide-header hide-footer size="m">
              <div class="pt-2 px-3 d-flex justify-content-between" style='font-family: "yg-jalnan";'>
                <h3>일별 랭킹</h3>
                <img id="exitBtn" src="https://i.ibb.co/GWXqhqv/close.png" alt="close" border="0" @click="$bvModal.hide('modal-rank')">
              </div>
              <hr>
              <div class="px-2">
                <p style="font-family: BMJua">My 랭킹은 전날 공부 데이터를 활용한 일별(데일리) 랭킹입니다. <br/>높은 순위권을 위해서 열공해주세요!! 📖</p>
              </div>
            </b-modal>
          </b-col>
          <b-col cols="4" id="cosmos">
            <div class="d-flex justify-content-center">
              <div class="mt-2" align="center" style="font-size: 15pt;">My 코스모스</div>
              <div class="ml-2" style="margin-top: 10px;"><img class="question" src="https://i.ibb.co/tsY4tg4/question.png" 
                title="코스모스 성장기를 보려면 클릭해주세요." v-b-modal.modal-cosmos></div>
            </div>
            <b-modal ref="myModal" id="modal-cosmos" hide-header hide-footer size="m">
              <div class="pt-2 px-3 d-flex justify-content-between" style='font-family: "yg-jalnan";'>
                <h3>코스모스 성장기</h3>
                <img id="exitBtn" src="https://i.ibb.co/GWXqhqv/close.png" alt="close" border="0" @click="$bvModal.hide('modal-cosmos')">
              </div>
              
              <hr>
              
              <div class="px-2">
                <table class="table table-hover" style="width: 100%;">
                  <colgroup>
                    <col width="50%"/>
                    <col width="50%"/>
                  </colgroup>
                  <thead style="background-color: #afa2dd; font-family: yg-jalnan;">
                    <tr>
                      <th class="pl-5">성장기</th>
                      <th>누적시간</th>
                    </tr>
                  </thead>
                  <tbody style="font-family: BMJua;">
                    <tr class="">
                      <td class="pl-5"><img class="lLevelImg" :src="myCosmos[0]"></td>
                      <td>5년 초과</td>
                    </tr>
                    <tr>
                      <td class="pl-5"><img class="lLevelImg" :src="myCosmos[1]"></td>
                      <td>~ 5년</td>
                    </tr>
                    <tr>
                      <td class="pl-5"><img class="lLevelImg" :src="myCosmos[2]"></td>
                      <td>~ 3년</td>
                    </tr>
                    <tr>
                      <td class="pl-5"><img class="lLevelImg" :src="myCosmos[3]"></td>
                      <td>~ 1년 6개월 (547일)</td>      
                    </tr>
                    <tr>
                      <td class="pl-5"><img class="lLevelImg" :src="myCosmos[4]"></td>
                      <td>~ 9개월 (270일)</td>      
                    </tr>
                    <tr>
                      <td class="pl-5"><img class="lLevelImg" :src="myCosmos[5]"></td>
                      <td>~ 3개월 (90일)</td>      
                    </tr>
                    <tr>
                      <td class="pl-5"><img class="lLevelImg" :src="myCosmos[6]"></td>
                      <td>~ 1개월 (30일)</td>      
                    </tr>
                  </tbody>
                </table>
              </div>
            </b-modal>
            <img id="mycosmos" :src="myCosmos[myInfoIdx]"/>
          </b-col>
          <!-- <b-col cols="1"></b-col> -->
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
                  <img class="studyImg" :src="study.image" alt="스터디이미지" v-else>
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

      userRank: [],
      myInfoIdx: 6,
      myRank: -1,
      myScore: 0,
      myTotalTime: '',
      myDailyTime: '',

      myLevel: [
        'Ruby',
        'Diamond',
        'Platinum',
        'Gold',
        'Silver',
        'Bronze',
        '코린이',
      ],

      myLevelImg: [
          'https://d2gd6pc034wcta.cloudfront.net/tier/26-a.svg',
          'https://d2gd6pc034wcta.cloudfront.net/tier/21-a.svg',
          'https://d2gd6pc034wcta.cloudfront.net/tier/16-a.svg',
          'https://d2gd6pc034wcta.cloudfront.net/tier/11-a.svg',
          'https://d2gd6pc034wcta.cloudfront.net/tier/6-a.svg',
          'https://d2gd6pc034wcta.cloudfront.net/tier/1-a.svg',
          'https://i.ibb.co/mtdCMsg/baby.png',
      ],

      myCosmos: [
        'https://i.ibb.co/N9GzZcP/main-img11.png',
        'https://i.ibb.co/dQcCZ3D/plant-pot.png',
        'https://i.ibb.co/9ty51BP/plant-1.png',
        'https://i.ibb.co/cw1MCFg/plant.png',
        'https://i.ibb.co/hdDCDZc/seed.png',
        'https://i.ibb.co/WxJ29bG/seeds.png',
        'https://i.ibb.co/2npFXgF/sunflower-seed.png',
      ],
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
      })
      .then(res => {
        // console.log(res)
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

    getDailyRank() {
      http({
        method: 'GET', 
        url: '/history/searchAll/day',
      })
      .then(res => {

        this.userRank = []   // 이전 데이터 비우기
        this.userRank = res.data

        
        
        // 내 랭킹 저장   
        for(var i=0; i<res.data.length; i++) {
          if(res.data[i].userhistoryDayId.user_id == this.$store.state.userId) {
            this.myRank = i+1     // 순위 저장

            // 공부 시간 계산
            var seconds = res.data[i].totalTime;
            var hour = parseInt(seconds/3600);
            var min = parseInt((seconds%3600)/60);
            var sec = seconds%60;

            this.myDailyTime = (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) 
                          + ":" + (sec < 10 ? "0" + sec : sec)
          }
        }
      })
      .catch(err => {
        console.log(err)
      })
    },
    
    async getUserTotalTime() {
      console.log("here!!")
      http({
        method: 'GET',
        url: '/history/totalTime',
        headers: this.getToken(),
      })
      .then(res =>{
        console.log(res.data)
        var day = parseInt(res.data/86400);
        var time = res.data - day * 86400
        var hour = parseInt(time/3600);
        var min = parseInt((time%3600)/60);
        var sec = time%60;

        this.myTotalTime = day + "일 " + (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) 
                          + ":" + (sec < 10 ? "0" + sec : sec)
        
        if(res.data >= 157680000) {     // 5년
          this.myInfoIdx = 0
        } else if(res.data >= 94608000) {   // 3년
          this.myInfoIdx = 1
        } else if(res.data >= 47260800) {   // 1년 6개월 (547일)
          this.myInfoIdx = 2
        } else if(res.data >= 23328000) {   // 9개월 (270일)
          this.myInfoIdx = 3
        } else if(res.data >= 7776000) {    // 3개월 (90일)
          this.myInfoIdx = 4
        } else if(res.data >= 2592000) {    // 1개월 (30일)
          this.myInfoIdx = 5
        } else {
          this.myInfoIdx = 6
        }
      })
      .catch(err =>
        console.log(err)
      )
    },
  },
  created(){
    this.getUserInfo()
    this.getDailyRank()
    this.getUserTotalTime()
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

#level{
  border-top: 15px solid #ccc;
  border-bottom: 15px solid #ccc;
  border-left: 15px solid #ccc;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}

#rank {
  border-top: 15px solid #ccc;
  border-bottom: 15px solid #ccc;
  border-left: 15px solid #ccc;
}

#cosmos {
  border: 15px solid #ccc;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}

#noRank {
  font-family: "BMJua";
  font-size: 15pt;
}



.passwordBox {
  width: 100%;
}

.checkBtn {
  border: none;
  border-radius: 8px;
  background-color: #afa2dd;
  height: 40px;
  width: 50px;
}

.checkBtn:hover {
  background-color: #c8c1e4;
  color: #fff;
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

#mycosmos {
  margin-top: 20px;
  width: 80px;
  height: 80px;
}

.question {
  width: 20px;
  height: 20px;
  cursor: pointer;
}

.score {
  width: 80%;
  height: 50%;
  margin: auto;
  border-radius: 30px;
  position: relative;
}

.studyImg {
  height: 100%;
}

#exitBtn{ 
  width: 20px;
  height: 20px;
  cursor: pointer;
}

.noDot {
  list-style-type: none;
}

.smLevelImg {
  width: 20px;
  height: 20px;
}

.lLevelImg {
  width: 50px;
  height: 50px;
}

</style>