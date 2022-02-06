<template>
  <div class="total">
    <center>
    <h1>스터디 모집 게시판</h1>
    <hr style="width: 80%">
    <h3>글생성</h3>
    <marquee style="font-size: 50px; color: #E0BFE6;" scrolldelay="50">스터디를 모집해 보세요</marquee>
    <img src="사람2.png" style="width: 200px; height: 200px;">
      <div class="form_body_tag p-5" style="width: 600px;">
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
            <b-form-input class="mt-2" id="content_title" v-model="input.content_title" style="height: 30px;"></b-form-input>
          </b-col>
        
          <b-col v-show="this.input.header === false" cols="3" class="mt-2">
            <label v-show="this.input.header === false" for="study_name">스터디이름</label>
          </b-col>
          <b-col v-show="this.input.header === false" cols="9" class="mt-2">
            <b-form-select style="width: 100%; height: 30px; font-size: 15px; " v-show="this.input.header === false" v-model="nameSelected" :options="titleOptions" id="study_name"></b-form-select>
          </b-col>

          <b-col v-show="this.input.header === false" cols="3" class="mt-2">
            <label v-show="this.input.header === false" for="recruit_number">모집인원</label>
          </b-col>
          <b-col v-show="this.input.header === false" class="spinbuttontag mt-2" cols="9">
            <b-form-input v-show="this.input.header === false" id="recruit_number" v-model="input.recruit_number" style="height: 30px;"> 명</b-form-input>
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="studytype_name">스터디분류</label>
          </b-col>
          
          <!-- 스터디원일 때 -->
          <b-col v-show="this.input.header !== false" cols="9" class="mt-2">
            <b-form-select v-model="studyTypeSelected" :options="options" style="width: 100%; height: 30px; font-size: 15px;" id="studytype_name"></b-form-select>
          </b-col>

          <b-col v-show="this.input.header === false" class="selectag mt-2" cols="9">
            <div v-for="temp in temps" :key="temp.idx">
              <p v-if="nameSelected === temp.value" style="background-color: #FDD8E4;">{{ temp.text }}</p>
            </div>
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="content">내용</label>
          </b-col>
          <b-col cols="9" class="mt-2">
            <b-form-textarea v-model="input.content" id="content" row="8" max-rows="10" style="height: 100px;"></b-form-textarea>
          </b-col>
          <!-- 스터디원 구함 -->
          <b-col v-show="this.input.header == false">
            <b-button class="mx-3 mt-4" style="background-color: #DAC7F9" @click="createBoardForm">게시글 생성</b-button>
            <b-button class="mx-3 mt-4" style="background-color: #DAC7F9" @click="backBoardMain">취소</b-button>
          </b-col>
          <!-- 스터디 구함 -->
          <b-col v-show="this.input.header !== false">
            <b-button class="mx-3 mt-4" style="background-color: #DAC7F9" @click="studyWantCreateBoardForm">게시글 생성</b-button>
            <b-button class="mx-3 mt-4" style="background-color: #DAC7F9" @click="backBoardMain">취소</b-button>
          </b-col>
        </b-row>
      </div>
    </center>
  </div>

</template>

// 스터디 구함을 누르면 스터디원 페이지, 스터디원 구하면 스터디장으로
// 게시글 생성 버튼 - 스터디원이 스터디원 구함에서 게시글 생성 버튼을 누르면 알림창
// 떠서 스터디장만 가능하다고 하거나 못 누르게


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
    backBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },
    goBoardDetail(){
      this.$router.push({name: 'BoardDetail'})
    },
    getToken(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`
      }
      return header
    },
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
      .then(res => {
        console.log(res.data)
        this.$router.push({name: "MainBoard"})
        console.log('스터디옵션 확인')
        // console.log(this.studyno[this.studyNameInfo.indexOf(this.nameSelected)], '여기 확인')
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
        // study_name: this.nameSelected,
        content_status: this.input.content_status,
        // recruit_number: this.input.recruit_number,
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
      .then(res => {
        console.log(res.data)
        this.$router.push({name: "MainBoard"})
        console.log('스터디옵션 확인')
        console.log(this.index)
      })
      .catch(err => {
        console.log(err)
      })
    },

    getStudyName() {
      http({
        method: 'GET',
        url: `/board/searchStudyName/${this.header}`,
        headers: this.getToken()
      })
      .then(res => {
        console.log('스터디이름 받아오기')
        // console.log(this.input.header)
        console.log(this.studyNameInfo)
        res.data.forEach(element => {
          this.titleOptions.push({value: element.study_name, text: element.study_name})
          this.temps.push({value: element.study_name, text: element.studytype_name, num: element.study_no})
          this.studyNameInfo.push(element.study_name)
          this.typeInfo.push(element.studytype_name)
          this.studyno.push(element.study_no)
        });
        console.log(res.data)
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    },
    trueHearder() {
      this.input.header = true
      console.log(this.input.header)
    },
    falseHearder() {
      this.input.header = false
      console.log(this.input.header)
      this.$router.go()
    },
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
  }
}
</script>

<style scoped>
.header_label {
  display: flex;
  justify-content: center;
  align-items: center;
}

.total {
  font-size: 15px;
}
.form_body_tag {
  background: repeating-linear-gradient(-45deg, #F3467B, #F3467B 20px, #FDD8E4 20px, #FDD8E4 80px);
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

#studytype_name {
  padding: 6px;
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

</style>