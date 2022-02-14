<template>
  <div class="total">
    <center>
    <div class="backImg">
    
    <h1>스터디 모집 게시판</h1>
    <hr style="width: 80%">
    <h3>글생성</h3>
    <img class="head_Img" src="https://i.ibb.co/5TfmRGF/image.png" alt="image" border="0" >
    <img class="head_Img2" src="https://i.ibb.co/5TfmRGF/image.png" alt="image" border="0" >
    <marquee behavior=alternate  scrolldelay="50">Hello World!</marquee>
    <img class="sideImg" src="https://i.ibb.co/nMJbfC2/2.png" alt="2" border="0">
    <img class="sideImg2" src="https://i.ibb.co/zRXJt08/image.png" alt="image" border="0">
      <div class="form_body_tag p-5" style="width: 40%;">
        <b-row style="height: 600px; background-color: white;">
          <b-col cols="3" class="header_label mt-2">
            <p for="some-radios">말머리</p>
          </b-col>
          <b-col class="radio_position" cols="9">
            <label class="hearder_radio_tag mx-3 mt-2"><input class="header_radio" v-model="input.header" type="radio" name="header" value="false" @click="falseHearder">스터디원 구함</label>
            <label class="hearder_radio_tag mx-3 mt-2"><input class="header_radio" v-model="input.header" type="radio" name="header" value="true" @click="trueHearder">스터디 구함</label>
          </b-col>
        
          <b-col class="title_label" cols="3" >
            <label class="mt-2" for="content_title">제목</label>
          </b-col>
          <b-col cols="9">
            <b-form-input class="mt-2" id="content_title" v-model="input.content_title" style="height: 35px;" maxlength="30"></b-form-input>
          </b-col>
        
          <b-col v-show="this.input.header === false" cols="3" class="mt-2">
            <label v-show="this.input.header === false" for="study_name">스터디이름</label>
          </b-col>
          <b-col v-show="this.input.header === false" cols="9" class="mt-2">
            <b-form-select style="width: 100%; height: 35px; font-size: 13px; " v-show="this.input.header === false" v-model="nameSelected" :options="titleOptions" id="study_name"></b-form-select>
          </b-col>

          <b-col v-show="this.input.header === false" cols="3" class="mt-2">
            <label v-show="this.input.header === false" for="recruit_number">모집인원</label>
          </b-col>
          <b-col v-show="this.input.header === false" class="spinbuttontag mt-2" cols="9">
            <b-form-input 
            v-show="this.input.header === false" 
            id="recruit_number"
            v-model="input.recruit_number" 
            style="height: 35px;" 
            type="number" 
            min="1" max="5"
            @keyup="recruitLimit"
            >
            </b-form-input>
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="studytype_name">스터디분류</label>
          </b-col>
          
          <!-- 스터디 구할 때 -->
          <b-col v-show="this.input.header !== false" cols="9" class="mt-2">
            <b-form-select class="studyTypeTag" v-model="studyTypeSelected" :options="options" style="width: 100%; height: 35px; line-height: 35px; font-size: 15px;" id="studytype_name"></b-form-select>
          </b-col>


          <!-- 스터디원 구할 때 -->
          <b-col v-show="this.input.header === false" class="selectag mt-2" cols="9">
            <div v-for="temp in temps" :key="temp.idx">
              <p class="studyTypeTag" v-if="nameSelected === temp.value">{{ temp.text }}</p>
            </div>
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="content">내용</label>
          </b-col>
          <b-col cols="9" class="mt-2">
            <b-form-textarea v-model="input.content" id="content" row="8" max-rows="10" style="height: 100px; word-break:break-all; "></b-form-textarea>
          </b-col>
          <!-- 스터디원 구함 -->
          <b-col v-show="this.input.header == false">
            <b-button class="btnTag mx-2 mt-4" @click="createBoardForm">게시글 생성</b-button>
            <b-button class="btnTag mx-2 mt-4" @click="backBoardMain">취소</b-button>
          </b-col>
          <!-- 스터디 구함 -->
          <b-col v-show="this.input.header !== false">
            <b-button class="btnTag mx-2 mt-4" @click="studyWantCreateBoardForm">게시글 생성</b-button>
            <b-button class="btnTag mx-2 mt-4" @click="backBoardMain">취소</b-button>
          </b-col>
        </b-row>
      </div>
      </div>
    </center>
  </div>

</template>


<script>
// import http from 'http'
import http from "@/util/http-common.js";

export default {
  name: 'BoardForm',
  data() {
    return {
      index: null,
      header: false,
      nameSelected: null,
      typeSelected: null,
      studyTypeSelected: null,
      studytype_name: null,
      input: {
        header: false,
        content_title: null,
        study_name: null,
        content_status: false,
        recruit_number: null,
        studytype_name: null,
        content: null,
        user_id: null,
        // study_no: null,
      },
      temps: [],
      titleOptions: [],
      studyNameInfo: [],
      typeInfo: [],
      studyno:[],
      options:[],
    }
  },
  methods: {
    // 인원 수 제한
    recruitLimit(event) {
      if(event.key >= 0 && event.key <= 5) {
        return true;
      } else if (event.key !== 'Backspace' && event.key !== 'F5' && event.key !== 'Enter') {
          alert('5명 까지 입력이 가능합니다');
          this.input.recruit_number = null
          return false;
        } 
      },
    
    // 게시판으로
    backBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },

    // 토큰 가져오기
    getToken(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`
      }
      return header
    },

    // 게시글 생성
    createBoardForm() {
      const createBoardItem = {
        header: this.input.header,
        content_title: this.input.content_title,
        study_name: this.nameSelected,
        content_status: this.input.content_status,
        recruit_number: this.input.recruit_number,
        studytype_name: this.typeInfo[this.studyNameInfo.indexOf(this.nameSelected)],
        content: this.input.content,
        study_no: this.studyno[this.studyNameInfo.indexOf(this.nameSelected)],
      }
      http({
        method: 'post',
        url: '/board/register',
        data: createBoardItem,
        headers: this.getToken()
      })
      .then(() => {
        this.$router.push({name: "MainBoard"})
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 스터디 구함일 때
    studyWantCreateBoardForm () {
      const createBoardItem = {
        header: this.input.header,
        content_title: this.input.content_title,
        content_status: this.input.content_status,
        studytype_name: this.studyTypeSelected,
        content: this.input.content,
        study_no: this.studyno[this.studyNameInfo.indexOf(this.nameSelected)],
      }
      http({
        method: 'post',
        url: '/board/register',
        data: createBoardItem,
        headers: this.getToken()
      })
      .then(() => {
        this.$router.push({name: "MainBoard"})
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 스터디 이름 불러오기
    getStudyName() {
      http({
        method: 'GET',
        url: `/board/searchStudyName/${this.header}`,
        headers: this.getToken()
      })
      .then(res => {
        res.data.forEach(element => {
          this.titleOptions.push({value: element.study_name, text: element.study_name})
          this.temps.push({value: element.study_name, text: element.studytype_name, num: element.study_no})
          this.studyNameInfo.push(element.study_name)
          this.typeInfo.push(element.studytype_name)
          this.studyno.push(element.study_no)
        });
      })
      .catch(err => {
        console.log(err)
      })
    },

    // 진행중, 완료일 때
    trueHearder() {
      this.input.header = true
      console.log(this.input.header)
    },
    falseHearder() {
      this.input.header = false
      console.log(this.input.header)
      this.$router.go()
    },

    // 스터디 분류 불러오기
    getStudyType() {
      http({
        method: 'GET',
        url: '/study/studyType'
      })
      .then(res => {
        // console.log(res)
        res.data.forEach(element => {
          this.options.push({value: element.studytypeName, text:element.studytypeName})
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  created(){
    this.getToken()
    // this.getStudyInfo()
    this.getStudyName()
    this.getStudyType()
  },
}
</script>

<style scoped>
marquee {
  font-size: 50px;
  color: #ceb4f0;
  width: 30%;
  background-color: #fff;
}

.head_Img {
  width: 38%;
  height: 25%;
  transform: rotate(16deg);
  position: absolute;
  right: 0;
}

.head_Img2 {
  width: 38%;
  height: 25%;
  transform: rotate(-16deg);
  position: absolute;
  left: 0;
}

.sideImg {
  width: 20%; 
  height: 40%;
  position: absolute;
  top: 65%;
  right: 5%;
}

.sideImg2 {
  width: 17%; 
  height: 30%;
  position: absolute;
  top: 72%;
  left: 6%;
}

.backImg {
  background-image: url('https://t1.daumcdn.net/cfile/blog/1532170949754B963C');
  /* background-color: #afa2dd; */
}

.btnTag {
  background-color: #afa2dd;
  border: none;
}

.btnTag:hover {
  background-color: #F3467B;
}

.header_label {
  display: flex;
  justify-content: center;
  align-items: center;
}

.total {
  font-size: 15px;
  
}
.form_body_tag {
  background: repeating-linear-gradient(-45deg, #afa2dd, #afa2dd 20px, #c8c1e4 20px, #c8c1e4 80px);
}

.studyTypeTag {
  background-color: #d7cff7;
  width: 370px;
  height: 35px;
  line-height: 35px;
  border-radius: 4px;
  text-align: center;
}

.radio_position {
  display: flex;
  justify-content: center;
  align-items: center;
}

.form {
  display: flex;
  justify-content: center;
}

.radio_hearder {
  display: flex;
  justify-content: center;
  /* color: #DAC7F9; */
}

.study_title {
  color: black;
  font-size: 1rem;
}

.selectag {
  display: flex;
  justify-content: left;
}

.spinbuttontag {
  display: flex;
  justify-content: left;
}

.titel_label {
  display: flex;
  justify-content: center;
}

.sideImg2{
  transform-origin: 50% 50%;
  animation-name: shake;
  animation-duration: 3s;
  animation-iteration-count: infinite;
  animation-delay: 1s;
}
    
@keyframes shake{
  0%{
      transform: rotate(0deg);
    }
    10%{
      transform: rotate(5deg);
    }
    20%{
      transform: rotate(-5deg);
    }
    30%{
      transform: rotate(5deg);
    }
    40%{
      transform: rotate(-5deg);
    }
    50%{
      transform: rotate(5deg);
    }
    60%{
      transform: rotate(-5deg);
    }
    70%{
      transform: rotate(0deg);
    }
    100%{
      transform: rotate(0deg);
    }
}

.sideImg{
  transform-origin: 50% 50%;
  animation-name: shake;
  animation-duration: 3s;
  animation-iteration-count: infinite;
  animation-delay: 1s;
}
    
@keyframes shake{
  0%{
      transform: rotate(0deg);
    }
    10%{
      transform: rotate(5deg);
    }
    20%{
      transform: rotate(-5deg);
    }
    30%{
      transform: rotate(5deg);
    }
    40%{
      transform: rotate(-5deg);
    }
    50%{
      transform: rotate(5deg);
    }
    60%{
      transform: rotate(-5deg);
    }
    70%{
      transform: rotate(0deg);
    }
    100%{
      transform: rotate(0deg);
    }
}

</style>