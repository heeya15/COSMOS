<template>
  <center>
    <h1>스터디 방 생성</h1>
    <hr>
    <div style="width:1000px;" class="p-4">
      <b-row>
        <!-- 스터디 이름 중복체크 http 요청 버튼 -->
        <b-col cols="3">
          <label for="studyName" class="mt-2">스터디 이름</label>
        </b-col>
        <b-col>
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
          <b-form-input id="url" v-model="input.url"></b-form-input>
          <div ref="urlMsg"></div>
        </b-col>
        <b-col cols="3">
          <b-button @click="checkUrl">중복확인</b-button>
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
          <!-- <input ref="studyImg" accept="image/*" type="file" id="studyImg" class="mt-3" @change="uploadImage"/> -->
          
          <b-button class="mt-3" @click="$bvModal.show('bv-modal-studyImg')">이미지변경</b-button>
          <b-modal id="bv-modal-studyImg" size="lg" centered>
            <template #modal-title>
            <h3>스터디 이미지 선택</h3>
            </template>
            <b-row class="ml-2">
              <b-col><button class="imgBtn" @click="getImageSrc(1)"><img class="studyImg" id="studyImg1" for="studyWithMe" src="@/assets/img/study/studywithme.jpg" alt="study_with_me"></button></b-col>
              <b-col><button class="imgBtn" @click="getImageSrc(2)"><img class="studyImg" id="studyImg2" for="study2" src="@/assets/cosmos_bg.png" alt="study2"></button></b-col>
              <b-col><button class="imgBtn" @click="getImageSrc(3)"><img class="studyImg" id="studyImg3" for="study3" src="@/../public/테마6.jpg" alt="study3"></button></b-col>
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
          <b-form-input id="totalMember" placeholder="인원 수를 입력하세요." v-model="input.totalMember"></b-form-input>
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
      <button v-if="input.study_type==='public'" class="mt-4 createBtn" type="button" @click="createPublicStudy">스터디 생성</button>
      <button v-else class="mt-4 createBtn" type="button" @click="createPrivateStudy">스터디 생성</button>
    </div>


  </center>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: 'StudyRoomCreateForm',
  data() {
    return {
      input: {
        studyName: null,
        url: 'https://i6e103.p.ssafy.io/',
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
    }
  },
  methods: {
    getHeader(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`,
        // 'Content-Type': 'multipart/form-data'
      }
      return header
    },
    getImageSrc(num) {
      // var image = this.$refs.studyImg.files[0]
      // const url = URL.createObjectURL(image)
      // this.input.image = url
      var image = document.getElementById(`studyImg${num}`).src
      this.input.image = image
      // console.log(this.input.image)
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
      // const studyInfo = new FormData()
      // studyInfo.append('image',this.$refs.studyImg.files[0])
      // studyInfo.append('studyName',this.input.studyName)
      // studyInfo.append('studyNo',this.input.studyNo)
      // studyInfo.append('studyPassword',this.input.studyPassword)
      // studyInfo.append('studyRule',this.input.studyRule)
      // studyInfo.append('studytypeNo',this.input.studytypeNo)
      // studyInfo.append('totalMember',this.input.totalMember)
      // studyInfo.append('url',this.input.url)
      // console.log(this.$refs.studyImg.files[0])
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
        url: this.input.url
      }
      http({
        method: 'POST',
        url: '/study/register',
        headers: this.getHeader(),
        data: studyInfo
      })
      .then(res => {
        // console.log(res)
        if (res.status !== 200){
          alert('입력을 다시 한 번 확인하세요.')
        }else {
          this.$router.push({name: 'StudyDetail', params: {studyNo: res.data.studyNo}})  
        }
      })
      .catch(err => {
        alert('입력을 다시 한 번 확인하세요.')
        console.log(err)
      })
    },
    createPublicStudy(){
      if (this.urlState === false){
        alert("url 중복확인을 해주세요!")
        return
      }
      const studyInfo = {
        image: this.input.image,
        numberOfMember: this.input.totalMember,
        publicstudyroomId: this.input.url.split('/')[3],
        studyName: this.input.studyName,
        studyRule: this.input.studyRule,
        studytypeNo: this.input.studytypeNo,
        url: this.input.url
      }
      http({
        method: 'POST',
        url: '/publicroom/register/publicRoom',
        headers: this.getHeader(),
        data: studyInfo,
      })
      .then(res => {
        console.log(res)
        if (res.status !== 200){
          alert('입력을 다시 한 번 확인하세요.')
        }else { 
          // this.$router.push({name: 'Openvidu'})
          this.$router.push({name: 'MainPage'})
        }
      })
      .catch(err => {
        console.log(err)
      })
    },
    checkUrl() {
      http({
        method: 'GET',
        url: '/study/urlcheck/',
        params: {url: this.input.url}
      })
      .then(res => {
        // console.log(res)
        if(res.data === false){
          this.$refs.urlMsg.innerText = '사용가능한 url주소 입니다.';
          this.urlState = true
        }else{
          this.$refs.urlMsg.innerText = '사용할 수 없는 url주소 입니다.';
          this.urlState = false
        }
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    this.getStudyType()
  }
}
</script>

<style scoped>
.createBtn {
  border: none;
  border-radius: 8px;
  background-color: #e4c3f1;
  height: 40px;
  width: 300px;
}
.createBtn:hover {
  background-color: #ddaae6;
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