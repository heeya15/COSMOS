<template>
  <div align="center" id="studyFormPage">   
    <div class="my-3" id="studyFormpBox">
    <h1 class="pt-5" >스터디 방 생성</h1>
    <div style="width:1000px;" class="p-4">
    <hr>
      <b-row>
        <!-- 스터디 이름 중복체크 http 요청 버튼 -->
        <b-col cols="3">
          <label for="studyName" class="mt-1">스터디 이름</label>
        </b-col>
        <b-col cols="6" class="mr-3">
          <b-form-input id="studyName" v-model="input.studyName"></b-form-input>
        </b-col>  
      </b-row>
      <hr class="mt-3">
        <!-- url은 스터디만드는 사람에게 직접 입력받음 -->
        <!-- url 중복체크 http 요청 버튼-->
      <b-row>        
        <b-col cols="3">
          <label for="url" class="mt-2">스터디 url</label>
        </b-col>
        <b-col cols="6">
          <b-form-input id="url" v-model="input.defaulturl" disabled></b-form-input>
          <b-form-input id="url" v-model="input.url" placeholder="URL을 입력하세요" @keydown="regexp()"></b-form-input>
            <div ref="urlMsg" v-if="this.urlState==true && this.regexpstate == true" style="color:#3C77C9"></div>
            <div ref="urleerorMsg" v-if="this.urlState==false || this.regexpstate==false" style="color:rgb(207, 1, 1);"></div>
        </b-col>
        <b-col cols="3">
          <b-button @click="checkUrl" class="mt-4">중복확인</b-button>
        </b-col>
      </b-row>  
      <hr class="mt-3">

      <b-row>  
        <b-col cols="3">
          <label for="studytypeNo" class="mt-2">스터디 분류</label>
        </b-col>
        <b-col cols="4">
          <b-form-select v-model="input.studytypeNo" :options="input.options" ></b-form-select>
        </b-col>
      </b-row>  
      <hr class="mt-3">
      
      <b-row>  
        <b-col cols="3">
          <label for="studyImg" class="mt-2">대표 이미지</label>
        </b-col>
        <b-col>
          <div class="preview" v-if="input.image">
            <img :src="input.image" alt="대표이미지" class="studyImg">
          </div>
          <div v-else>선택된 이미지가 없습니다.</div>
          
          <b-button class="mt-3" @click="$bvModal.show('bv-modal-studyImg')">이미지변경</b-button>
          <b-modal id="bv-modal-studyImg" size="lg" centered hide-footer>
            <template #modal-title>
            <h3>스터디 이미지 선택</h3>
            </template>
            <b-row class="ml-2">
              <b-col><button class="imgBtn" @click="[getImageSrc(1),$bvModal.hide('bv-modal-studyImg')]"><img class="studyImg" id="studyImg1" for="studyWithMe" src="@/assets/img/study/studywithme.jpg" alt="study_with_me"></button></b-col>
              <b-col><button class="imgBtn" @click="[getImageSrc(2),$bvModal.hide('bv-modal-studyImg')]"><img class="studyImg" id="studyImg2" for="study2" src="@/assets/cosmos_bg.png" alt="study2"></button></b-col>
              <b-col><button class="imgBtn" @click="[getImageSrc(3),$bvModal.hide('bv-modal-studyImg')]"><img class="studyImg" id="studyImg3" for="study3" src="@/../public/테마6.jpg" alt="study3"></button></b-col>
            </b-row>
          </b-modal>
        </b-col>
      </b-row>
      <hr class="mt-3">

      <b-row>
        <b-col cols="3">
          <label for="study_type" class="mt-1">스터디 종류</label>
        </b-col>
        <b-col class="mt-1">
          <input type="radio" id="public" value="public" v-model="input.study_type">
          <label for="public" class="ml-1 mr-5">공개</label>
          <input type="radio" id="private" value="private" v-model="input.study_type">
          <label for="private" class="ml-1 mr-5">비공개</label>
        </b-col>
      </b-row>  
      <hr class="mt-3">

      <b-row>  
        <b-col cols="3">
          <label for="totalMember" class="mt-2">인원 수</label>
        </b-col>
        <b-col cols="5">
          <!-- 비공 -->
          <b-form-input v-if="input.study_type==='private'" id="totalMember" placeholder="인원 수를 입력하세요." v-model="input.totalMember" @keyup="recruitLimit" type="number" max="5" maxlength="2"></b-form-input>
          <!-- 공개 -->
          <b-form-input v-else id="totalMember" placeholder="인원 수를 입력하세요." v-model="input.totalMember" @keyup="recruitLimit" type="number" max="10" maxlength="2"></b-form-input>
        </b-col>
        <b-col cols="1" class="mt-2">명</b-col>
      </b-row>    
      <hr class="mt-3">

      <b-row>
        <b-col cols="3">
          <label for="studyPassword" class="mt-1">비밀번호</label>
        </b-col>
        <b-col>
          <b-form-input id="studyPassword" v-model="input.studyPassword" v-if="input.study_type==='private'" placeholder="4~10자리 숫자만 입력하세요."></b-form-input>
          <div v-else class="studyPassword mt-1">공개스터디는 비밀번호가 필요하지 않습니다.</div>
        </b-col>
      </b-row>
      <hr class="mt-3">

      <b-row>  
        <b-col cols="3">초기 장치 설정</b-col>
        <b-col>
          <input type="checkbox" id="mic" value="mic" v-model="input.settings.mic">
          <label for="mic" class="ml-1 mr-5">마이크</label>
          <input type="checkbox" id="cam" value="cam" v-model="input.settings.cam">
          <label for="cam" class="ml-1 mr-5">카메라</label>
          <input type="checkbox" id="speaker" value="speaker" v-model="input.settings.speaker">
          <label for="speaker" class="ml-1 mr-5">스피커</label>
        </b-col>
      </b-row>
      <hr class="mt-3">

      <b-row>
        <b-col cols="3">
          <label for="studyRule" class="mt-2">스터디 규칙</label>
        </b-col>
        <b-col>
          <b-form-textarea id="studyRule" v-model="input.studyRule" placeholder="스터디 규칙, 공지사항 등 입력" rows="3" max-rows="6"></b-form-textarea>
        </b-col>
      </b-row>
      <button v-if="input.study_type==='public'" class="my-4 createBtn" type="button" @click="createPublicStudy">스터디 생성</button>
      <button v-else class="my-4 createBtn" type="button" @click="createPrivateStudy">스터디 생성</button>
    </div>
  </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

import JwtDecode from 'jwt-decode'
import { mapState } from 'vuex'

export default {
  name: 'StudyRoomCreateForm',
  data() {
    return {
      input: {
        defaulturl: 'https://i6e103.p.ssafy.io/',
        studyName: null,
        url: null,
        studytypeNo: null,
        options: [
          { value: null, text: '스터디 분류' },
        ],
        image: null,
        study_type: 'public', // 공개스터디, 비공개스터디
        totalMember: null,
        studyRule: null,
        studyPassword: null,
        settings: {
          mic: null,
          cam: null,
          speaker: null,
        }
      },
      urlState: false,
      regexpstate:false,
      study_no:"",
    }
  },
  methods: {
    // 인원 수 제한
    recruitLimit() { 
      // 공개 일 때
      if (this.input.study_type !=='private') {
        if(this.input.totalMember >= 0 && this.input.totalMember <= 10) {
          return true;
        } else {
            alert('10명까지 입력이 가능합니다');
            this.input.totalMember = null
            return false;
          }
      // 비공개 일 때
      } else {
          if(this.input.totalMember >= 0 && this.input.totalMember <= 5) {
          return true;
        } else {
            alert('5명까지 입력이 가능합니다');
            this.input.totalMember = null
            return false;
          }
        } 
      },

    getHeader(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`,
      }
      return header
    },
    getImageSrc(num) {
      var image = document.getElementById(`studyImg${num}`).src
      this.input.image = image
    },
    getStudyType() {
      http({
        method: 'GET',
        url: '/study/studyType'
      })
      .then(res => {
        // console.log(res)
        res.data.forEach(element => {
          this.input.options.push({value: element.studytypeNo, text:element.studytypeName})
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
    createPrivateStudy() {
      if (this.urlState === false){
        alert("url 중복확인을 해주세요!")
        return
      }
      const studyInfo = {
        image: this.input.image,
        studyName: this.input.studyName,
        studyPassword: this.input.studyPassword,
        studyRule: this.input.studyRule,
        studytypeNo: this.input.studytypeNo,
        totalMember: this.input.totalMember,
        url: this.input.defaulturl + this.input.url
      }
      // 가장 최근에 생긴 스터디 번호 조회후 studyNo 넣어주면 됨
      http({
        method: 'POST',
        url: '/study/register',
        headers: this.getHeader(),
        data: studyInfo
      })
      .then(res => {
        // console.log(res)
        if (res.status !== 200){
          alert('입력사항을 모두 입력하였는지 확인해주세요.')
        }else {
          this.getStudyNo()// 가장 최근에 생긴 스터디 번호 들고오는 함수
        }
      })
      .catch(err => {
        alert('입력사항을 모두 입력하였는지 확인해주세요.')
        console.log(err)
      })
    },
    getStudyNo(){ // 가장 최근에 생긴 스터디 번호 들고오는 함수
      http({
        method: 'GET',
        url: '/studymember/resent/search',
        headers: this.getHeader(),
      })
      .then(res => {
          console.log("가장 최근에 생긴 스터디 번호는?")
          console.log(res.data);
          console.log(res.data.study_no);
          this.study_no = res.data.study_no;
          this.$router.push({name: 'StudyDetail', params: {studyNo: this.study_no}})  
          this.$router.push({name: 'StudyDetail', params: {studyNo: this.study_no}})  
          this.$router.push({name: 'StudyDetail', params: {studyNo: this.study_no}})  
      })
      .catch(err => {
        alert('요청을 다시 한 번 확인하세요.')
        console.log(err)
      })
    }
    ,
    async createPublicStudy(){ // 공개 스터디룸 생성(백엔드에서 생성시 바로 공개스터디 멤버 테이블에 추가)
      if (this.urlState === false){
        alert("url 중복확인을 해주세요!")
        return
      }
      // 공개스터디룸 만들기위해 정보 저장
      var token = localStorage.getItem('jwt')
      var decoded = JwtDecode(token);
      var myId = decoded.sub;
      this.$store.state.roomName = this.input.studyName; // 방 이름
      this.$store.state.roomUrl = this.input.url; // 스터디룸 아이디
      this.$store.state.participant = myId; // 내 아뒤

      const studyInfo = {
        image: this.input.image,
        numberOfMember: this.input.totalMember,
        publicstudyroomId: this.input.url,
        studyName: this.input.studyName,
        studyRule: this.input.studyRule,
        studytypeNo: this.input.studytypeNo,
        url: this.input.defaulturl+this.input.url
      }
      await http({
        method: 'POST',
        url: '/publicroom/register/publicRoom',
        headers: this.getHeader(),
        data: studyInfo,
      })
      .then(res => {
        console.log(res)
        if (res.status !== 200){
          alert('입력을 다시 한 번 확인해주세요.')
          console.log(studyInfo);
        }else { 
          // this.getPublicMemberAdd();
          this.$router.push({name: "PublicStudyRoom"})
        }
      })
      .catch(err => {
        alert('입력사항을 모두 입력하였는지 확인해주세요.')
        console.log(err)
      })
    },
    // getPublicMemberAdd(){
    //   // 공개 스터디 생성 후 생성자가 바로 스터디 룸에 들어가서 공개 스터디 참가자 등록 로직 구현
    //   var token = localStorage.getItem('jwt')
    //   var decoded = JwtDecode(token);
    //   var myId = decoded.sub;
    //   this.$store.state.roomName = this.input.studyName; // 방 이름
    //   this.$store.state.roomUrl = this.input.url; // 스터디룸 아이디
    //   this.$store.state.participant = myId; // 내 아뒤
    //   console.log("공개 스터디 룸 생성 후 데이터 디버깅 부분>>>>>>>>>>>>>>>")
    //   console.log(this.input.studyName);
    //   console.log(this.input.url);
    //   console.log(myId);
    //   http({
    //     method: 'POST',
    //     url: '/publicroom/register/publicMember',
    //     headers: this.getHeader(),
    //     data: {publicstudyroomId: this.roomUrl},
    //   })
    //   .then(() => {
    //       this.$router.push({name: "PublicStudyRoom"})
    //   })
    //   .catch(err => {
    //       console.log(err)
    //   });  
    // },
    regexp(){
    const notPhoneticSymbolExp = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
      if(notPhoneticSymbolExp.test(this.input.url)){ // 한글이 아니라면
          this.regexpstate = false;
          this.$refs.urleerorMsg.innerText = '영어로 url주소를 입력해주세요';
      }else{
          this.regexpstate = true;
          this.$refs.urleerorMsg.innerText = '';
          this.$refs.urlMsg.innerText ='';
      }
  },
    checkUrl() {   
      http({
        method: 'GET',
        url: '/study/urlcheck/',
        params: {url: this.input.defaulturl+this.input.url}
      })
      .then(res => {
        if(res.data == false && this.regexpstate == true){
          this.urlState = true
          this.$refs.urlMsg.innerText = '사용가능한 url주소 입니다.';
        }else if(res.data == true && (this.regexpstate == false || this.regexpstate == true)  ){ 
          this.urlState = false;
          this.$refs.urleerorMsg.innerText = '사용할 수 없는 url주소 입니다.';
        }    
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  computed:{
     ...mapState([ // store state 접근
      'roomName', 'roomUrl', 'participant'
    ])
  },
  created() {
    this.getStudyType()
  }
}
</script>

<style scoped>
#studyFormPage {
  /* padding: 100px 0; */
  height: 140%;
  position: relative;
  background-color: #DAC7F9;
}
#studyFormpBox {
  height: auto;
  width: auto;
  background-color: rgb(255, 255, 255);
  box-shadow: 10px 10px 10px rgb(235, 235, 235);
  border-radius: 10px;
  position:absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}
.createBtn {
  border: none;
  border-radius: 8px;
  background-color: #afa2dd;
  height: 40px;
  width: 300px;
}
.createBtn:hover {
  background-color: #c8c1e4;
}
.studyImg {
  height: 150px;
  width: 200px;
  border-radius: 8px;
}
.study_password {
  height:34px;
}
* {
  font-family: 'yg-jalnan';
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
</style>