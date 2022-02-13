<template>
  <center>
    <div class="container">

      <!-- 모달 시작-->
      <b-modal ref="my-modal" :id="infoModal.id" hide-footer centered hide-header>
        <center>
          <h4 slot="header" class="card-title">방 입장을 위한 초기 세팅</h4>
        </center>
        <br />
        <b-row>
        <b-col cols="5">초기 장치 설정</b-col>
        <b-col>
          <input type="checkbox" id="mic"  v-model="settings.mic">
          <label for="mic" class="ml-1 mr-4">마이크ON</label>
          <input type="checkbox" id="cam"  v-model="settings.cam">
          <label for="cam" class="ml-1 mr-4">카메라ON</label>
        </b-col>
        </b-row>
        <table class="table table-bordered">
          <tbody>
            <tr>
              <td class="table-danger">방 비밀번호 입력</td>
              <td>
                <input
                  type="text"
                  name="room_pwd"
                  id="room_pwd"
                  class="form-control bd-control"
                  placeholder="비밀번호"
                  v-model="pwd"
                  @keydown.enter="pwdCheck()"
                />
              </td>
            </tr>
          </tbody>
        </table>
        <div class="text-center">
          <button @click="hideModal" class="cancelBtn ml-3 float-right" >취소</button>
          <button @click="pwdCheck()" type="submit" class="enterBtn ml-3 float-right" >입장</button>
        </div>
      </b-modal>
      <!-- 모달 끝 -->

      <h1>STUDY DETAIL</h1>
      <hr>
      <!-- 스터디 정보 받아오기 -->
      <div class="studyContainer my-2 py-5 pr-5">
        <b-row class="mx-0">
          <b-col cols="5" class="pl-5">
            <!-- <img src="@/assets/img/nophoto.png" alt="이미지없음" class="studyImg" v-if="studyInfo.image.length<24"> -->
            <img src="https://bootstrapious.com/i/snippets/sn-img-hover/hoverSet-3.jpg" alt="이미지없음" class="studyImg" v-if="studyInfo.image.length<24">
            <img :src="studyInfo.image" alt="스터디 이미지" class="studyImg" v-else>
          </b-col>
          <b-col>
            <b-row align="left">
              <b-col cols="5">
              <h2>{{studyInfo.studyName}}</h2>
              </b-col>
              <b-col cols="2">
              <button class="enterBtn2 ml-3" @click="info($event.target)">방 입장</button>
              </b-col>
            </b-row>
              <div class="my-2" align="left">
                {{studyInfo.studyRule}}
              </div>
            <b-row class="my-2">
              <b-col>
              <div class="mt-3" align="left">
                <button class="modifyBtn mr-3" v-if="power.leader" @click="$bvModal.show('bv-modal-studyModify')">스터디 수정</button>

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
                          <b-form-input id="studyName" v-model="modifyInfo.studyName"></b-form-input>
                        </b-col>
                      </b-row>
                      <hr class="mt-3">

                      <b-row>
                        <b-col cols="4" class="pr-4">
                          <label for="studyImg" class="mt-2">스터디 이미지</label>
                        </b-col>
                        <b-col>
                        <div class="preview" v-if="studyInfo.image && !modifyInfo.image">
                          <img :src="studyInfo.image" alt="대표이미지" class="studyImg">
                        </div>
                        <div v-if="modifyInfo.image">
                          <img :src="modifyInfo.image" alt="대표이미지" class="studyImg">
                        </div>
                        <b-button class="mt-3" @click="$bvModal.show('bv-modal-studyImg')">이미지변경</b-button>
                          <b-modal id="bv-modal-studyImg" size="xl" centered hide-footer>
                            <template #modal-title>
                            <h3>스터디 이미지 선택</h3>
                            </template>
                            <VueSlickCarousel ref="slick"
                              :arrows="true"
                              :dots="true"
                              :infinite="true" 
                              :speed="500"
                              :slidesToShow="4"
                              :slidesToScroll="1"
                              :swipeToSlide="true"
                              :adaptiveHeight="true"
                              :autoplay="true"
                              :autoplaySpeed="2000"
                              class="mb-5 mx-3"
                              align="center"
                            >
                              <div v-for="(images, idx) in studyImages" :key="idx" class="mb-lg-2" align="center">
                                <button class="imgBtn" @click="[getImageSrc(idx+1),$bvModal.hide('bv-modal-studyImg')]"><img class="studyImg" id="studyImg" for="studyWithMe" :src="studyImages[idx]" alt="study_with_me"></button>
                              </div>
                              <template #prevArrow>
                                <button>
                                </button>
                              </template>
                              <template #nextArrow>
                                <button>                  
                                </button>
                              </template>
                            </VueSlickCarousel>
                          </b-modal>
                        </b-col>
                      </b-row>
                      <hr class="mt-3">

                      <b-row>
                        <b-col cols="3" class="pr-4">
                          <label for="studyPassword" class="mt-2">스터디 비밀번호</label>
                        </b-col>
                        <b-col cols="4">
                          <b-form-input id="studyPassword" v-model="modifyInfo.studyPassword"></b-form-input>
                        </b-col>
                      </b-row>
                      <hr class="mt-3">

                      <b-row>
                        <b-col cols="3">
                          <label for="studytypeNo" class="mt-2">스터디 타입</label>
                        </b-col>
                        <b-col cols="4">
                          <b-form-select v-model="modifyInfo.studytypeNo" :options="options" ></b-form-select>
                        </b-col>
                      </b-row>
                      <hr class="mt-3">

                      <b-row>
                        <b-col cols="3">
                          <label for="totalMember" class="mt-2">스터디 총 인원</label>
                        </b-col>
                        <b-col cols="4">
                          <b-form-input id="totalMember" v-model="modifyInfo.totalMember" @keyup="recruitLimit" type="number" max="10" min="2"></b-form-input>
                        </b-col>
                      </b-row>
                      <hr class="mt-3">

                      <b-row>
                        <b-col cols="3">
                          <label for="studyRule" class="mt-2">스터디 규칙</label>
                        </b-col>
                        <b-col>
                          <b-form-textarea id="studyRule" v-model="modifyInfo.studyRule" rows="3" max-rows="6"></b-form-textarea>
                        </b-col>
                      </b-row>
                      <hr class="mt-3">
                    </div>
                    <div class="d-flex justify-content-center">
                      <b-button class="m-2" variant="warning" @click="updateStudy">수정</b-button>
                      <b-button class="m-2" @click="cancelModify">취소</b-button>
                    </div>
                  </b-modal>

                <b-button v-if="power.leader" variant="danger" @click="deleteStudy" style="border-radius:8px; height:40px;">스터디 삭제</b-button>
                <b-button v-else variant="danger" @click="deleteMember(myStudyMemberNo)" style="border-radius:8px; height:40px;">스터디 탈퇴</b-button>
              </div>
              </b-col>
            </b-row>
          </b-col>
        </b-row>
      </div>

      <hr>
      <div v-if="power.leader" class="buttongroup d-flex justify-content-between" style="width:800px;">
        <b-button :class="{noticeBtn : toggleNotice}" @click="togglenotice">공지사항</b-button>
        <b-button :class="{applyBtn : toggleApply}" @click="toggleapply">가입 요청 확인 </b-button>

        <!-- 스터디장만 회원추가가능 -->
        <b-button :class="{memberBtn : toggleMember}" @click="togglemember">스터디원 정보</b-button>
      </div>
      <div v-else class="buttongroup" style="width:800px;">
        <b-button class="mr-5" :class="{noticeBtn : toggleNotice}" @click="togglenotice">공지사항</b-button>
        <!-- 스터디장만 회원추가가능 -->
        <b-button :class="{memberBtn : toggleMember}" @click="togglemember">스터디원 정보</b-button>
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

import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'

export default {
  name: 'StudyDetail',
  components: {
    StudyNotice,
    StudyApply,
    StudyMember,
    VueSlickCarousel,
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
        image:'',
        // studyName: null,
        // url: null,
        // image: null,
        // totalMember: null,
        // numberOfMember: null, //현재 참여중인 스터디 인원
      },
      modifyInfo: {
        studyName : '',
        image : '',
        studyPassword : '',
        studytypeNo : null,
        totalMember: null,
        studyRule: '',
        url:'',
      },
      infoModal: {
        id: "info-modal",
        user_password: "",
      },
      settings: {
        mic: null,
        cam: null,
        speaker: null,
      },
      options:[],
      modal: false,
      pwd: "",

      studyImages: [
        'https://i.ibb.co/NZ4vqMw/algorithm.jpg',
        'https://i.ibb.co/Jpf8cjs/python.jpg',
        'https://i.ibb.co/3vdLtWV/C.png',
        'https://i.ibb.co/YXrWmN9/react.jpg',
        'https://i.ibb.co/TT3sVMd/java.png',
        'https://i.ibb.co/J2Jwsdc/spring.png',
        'https://i.ibb.co/H48fwjg/c.jpg',
        'https://i.ibb.co/gg6hwsG/studywithme.jpg',
        'https://i.ibb.co/N1gPh3J/working-at-home.png',
        'https://i.ibb.co/PNzm2Cw/artificial-intelligence-4111582-1920.jpg',
        'https://i.ibb.co/qMMbw5D/android.png',
        'https://i.ibb.co/LrxNMLY/angularjs.jpg',
        'https://i.ibb.co/17GKTVr/arduino.png',
        'https://i.ibb.co/JcmCC0T/Blockchain.jpg',
        'https://i.ibb.co/CQmhf14/bigdata.jpg',
        'https://i.ibb.co/KxD9SzZ/C.png',
        'https://i.ibb.co/jz2xT5n/css.webp',
        'https://i.ibb.co/MBV2BhD/html.webp',
        'https://i.ibb.co/BN882vj/javascript.jpg',
        'https://i.ibb.co/hWpp2FS/database.jpg',
        'https://i.ibb.co/k30ywt0/django.png',
        'https://i.ibb.co/8x1Bj1S/oracle.png',
        'https://i.ibb.co/Tg2cF9M/php.jpg',
        'https://i.ibb.co/xsMy3wt/cs.jpg',
        'https://i.ibb.co/QQ6TrHB/mysql.png',
        'https://i.ibb.co/Bfq3WVR/kotlin.png',
        'https://i.ibb.co/BGLHv1j/raspberrypi.gif',
        'https://i.ibb.co/Z200ZZR/ruby.jpg',
        'https://i.ibb.co/JHCL6sY/swift.png',
        'https://i.ibb.co/HtwM0Wx/study1.jpg',
        'https://i.ibb.co/B6dP6Rh/study2.gif',
        'https://i.ibb.co/0yXpH3X/dog.jpg',
      ],
    }
  },
  methods: {
    // 인원 수 제한
    recruitLimit() { 
      if(this.modifyInfo.totalMember >= 0 && this.modifyInfo.totalMember <= 10) {
        return true;
      } else {
          alert('10명까지 입력이 가능합니다');
          this.modifyInfo.totalMember = null
          return false;
        } 
      },

    // 모달 값 셋팅
    info( button) {
      this.$root.$emit("bv::show::modal", this.infoModal.id, button);
    },
    hideModal() {
      this.$refs["my-modal"].hide();
    },

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
        console.log(res.data)
        this.studyInfo = res.data
        this.modifyInfo.studyNo = res.data.studyNo
        this.modifyInfo.studyName = res.data.studyName
        this.modifyInfo.image = res.data.image
        this.modifyInfo.studyPassword = res.data.studyPassword
        this.modifyInfo.studytypeNo = res.data.studyType.studytypeNo
        this.modifyInfo.totalMember = res.data.totalMember
        this.modifyInfo.studyRule = res.data.studyRule
        this.modifyInfo.url = res.data.url
      })
      .catch(err => {
        console.log(err)
      })
    },

    getImageSrc(num) {
      // var image = document.getElementById(`studyImg${num}`).src
      var image = this.studyImages[num-1]
      this.modifyInfo.image = image
    },

    cancelModify() {
      this.modifyInfo.studyName = this.studyInfo.studyName
      this.modifyInfo.image = this.studyInfo.image
      this.modifyInfo.studyPassword = this.studyInfo.studyPassword
      this.modifyInfo.studytypeNo = this.studyInfo.studyType.studytypeNo
      this.modifyInfo.totalMember = this.studyInfo.totalMember
      this.modifyInfo.studyRule = this.studyInfo.studyRule
      this.$bvModal.hide('bv-modal-studyModify')
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

        // 마이크 캠 셋팅
        this.$store.state.audio = this.settings.mic;
        this.$store.state.video = this.settings.cam;
        console.log("디버깅>>>>>>>>>>>>>>>>>>>>>>>")
        console.log(this.$store.state.audio);
        console.log(this.$store.state.video);
        this.$store.state.roomName = this.studyInfo.studyName;

        var str = this.studyInfo.url;
        var url =  str.split('/')[3];
        

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
            this.$router.push({name: "PrivateStudyRoom"})
          })
          .catch(err => {
            console.log(err)
          });  
      }else{
        alert("비밀번호가 틀렸습니다.")
      }
    },
    updateStudy() {
      http({
        method: 'PUT',
        url: '/study/update',
        data: this.modifyInfo
      })
      .then(() => {
        // console.log(res)
        this.$bvModal.hide('bv-modal-studyModify')
        this.$router.go()
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
      'power', 'roomName', 'roomUrl', 'participant', 'roomStudyNo', 'studyMembers', 'audio', 'video'
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
.studyContainer {
  background-color: aliceblue;
  width: 100%;
}

.sessionLink:hover {
  color: #6c757d;
  cursor: pointer;
}

.studyImg {
  height: 180px;
  width: 250px;
  border-radius: 10px;
  border: 3px solid #c8c1e4;
}
.imgBtn {
  outline-width: 5px;
  outline-color: #d5648a;
  border: none;
}
.imgBtn:hover {
  border: none;
  filter: brightness(70%);
}
.imgBtn:focus {
  border-radius: 8px;
}
/* 모달 스타일 */
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

/* 버튼 */
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
  background-color: #afa2dd;
  height: 40px;
  width: 50px;
}
.enterBtn2 {
  border: none;
  border-radius: 8px;
  background-color: #afa2dd;
  height: 40px;
  width: 80px;
}
.enterBtn:hover, .enterBtn2:hover {
  background-color: #c8c1e4;
  color: #fff;
}

.cancelBtn {
  border: none;
  border-radius: 8px;
  color: white;
  background-color: #6c757d;
  height: 40px;
  width: 50px;
}
.cancelBtn:hover {
  background-color: #495057;
}

.noticeBtn, .applyBtn, .memberBtn, .noticeBtn:focus, .applyBtn:focus, .memberBtn:focus {
  background-color: #afa2dd;
  color: black;
  border: none;
}

/* 스터디 이미지 슬라이드 좌,우 화살표 */
.slick-prev::before{
  content: url("https://i.ibb.co/JkGwjx0/icon-prev.png");
}
.slick-next::before{
  content: url("https://i.ibb.co/q1kCmY6/icon-next.png");
}
</style>