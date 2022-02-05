<template>
  <center>
    <h1>스터디 방 생성</h1>
    <hr>
    <div style="width:600px;" class="p-4">
      <b-row>
        <!-- 스터디 이름 중복체크 axios 요청 버튼 -->
        <b-col cols="3">
          <label for="studyName" class="mt-2">스터디 이름</label>
        </b-col>
        <b-col>
          <b-form-input id="studyName" v-model="input.studyName"></b-form-input>
        </b-col>  
      </b-row>
      <hr class="mt-3">
        <!-- url은 스터디만드는 사람에게 직접 입력받음 -->
        <!-- url 중복체크 axios 요청 버튼-->
      <b-row>        
        <b-col cols="3">
          <label for="url" class="mt-2">스터디 url</label>
        </b-col>
        <b-col cols="6">
          <b-form-input id="url" v-model="input.url"></b-form-input>
        </b-col>
        <b-col cols="3">
          <b-button>중복확인</b-button>
        </b-col>
      </b-row>  
      <hr class="mt-3">

      <b-row>  
        <b-col cols="3">
          <label for="studytypeNo" class="mt-2">스터디 분류</label>
        </b-col>
        <b-col cols="4">
          <b-form-select v-model="input.studytypeNo" :options="input.options" ></b-form-select>
          <!-- <b-form-input id="studytypeNo" v-model="input.studytypeNo"></b-form-input> -->
        </b-col>
      </b-row>  
      <hr class="mt-3">
      
      <b-row>  
        <b-col cols="3">
          <label for="studyImg" class="mt-2">대표 이미지</label>
        </b-col>
        <b-col>
          <div class="preview" v-if="input.image">
            <img :src="input.image" alt="대표이미지">
          </div>
          <div v-else>업로드된 이미지가 없습니다.</div>
          <input ref="studyImg" accept="image/*" type="file" id="studyImg" class="mt-3" @change="uploadImage"/>
        </b-col>
      </b-row>
      <hr class="mt-3">

      <b-row>
        <b-col cols="3">
          <label for="study_type" class="mt-1">스터디 종류</label>
        </b-col>
        <b-col class="mt-1">
          <input type="radio" id="public" value="public" v-model="input.study_type" default>
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
          <b-form-input id="totalMember" placeholder="인원 수를 입력하세요."></b-form-input>
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
        <b-col cols="4" class="pr-5">초기 장치 설정</b-col>
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
import axios from 'axios'

export default {
  name: 'StudyRoomCreateForm',
  data() {
    return {
      input: {
        studyName: null,
        url: 'http://www.cosmos.com/',
        studytypeNo: null,
        options: [
          { value: null, text: '스터디 분류' },
        ],
        image: null,
        study_type: null, // 공개스터디, 비공개스터디
        totalMember: null,
        studyRule: null,
        studyPassword: null,
        settings: {
          mic: null,
          cam: null,
          speaker: null,
        }
      }
    }
  },
  methods: {
    getHeader(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'multipart/form-data'
      }
      return header
    },
    uploadImage() {
      var image = this.$refs.studyImg.files[0]
      const url = URL.createObjectURL(image)
      this.input.image = url
    },
    getStudyType() {
      axios({
        method: 'GET',
        url: 'http://i6e103.p.ssafy.io:8080/api/study/studyType'
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
      const studyInfo = new FormData()
      studyInfo.append('image',this.$refs.studyImg.files[0])
      studyInfo.append('studyName',this.input.studyName)
      studyInfo.append('studyNo',this.input.studyNo)
      studyInfo.append('studyPassword',this.input.studyPassword)
      studyInfo.append('studyRule',this.input.studyRule)
      studyInfo.append('studytypeNo',this.input.studytypeNo)
      studyInfo.append('totalMember',this.input.totalMember)
      studyInfo.append('url',this.input.url)
      console.log(this.$refs.studyImg.files[0])
      axios({
        method: 'POST',
        url: 'http://i6e103.p.ssafy.io:8080/api/study/register',
        headers: this.getHeader(),
        data: studyInfo
      })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    },
    createPublicStudy(){
      console.log('공개 스터디생성')
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
  width: 100%;
}
button:hover {
  background-color: #ddaae6;
}
img {
  height: 150px;
  width: 200px;
}
.study_password {
  height:34px;
}

</style>