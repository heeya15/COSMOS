<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>글생성</h3>
    <center>
      <div class="p-5" style="width: 600px">
        <b-row>
          <b-col cols="3" class="mt-2">
            <label for="some-radios">말머리</label>
          </b-col>
          <b-col cols="9">
            <label class="mx-3 mt-2"><input v-model="input.header" type="radio" name="header" value="false">스터디원 구함</label>
            <label class="mx-3 mt-2"><input v-model="input.header" type="radio" name="header" value="true">스터디 구함</label>
          </b-col>
        
          <b-col class="title_label" cols="3" >
            <label class="mt-2" for="content_title">제목</label>
          </b-col>
          <b-col cols="9">
            <b-form-input class="mt-2" id="content_title" v-model="input.content_title"></b-form-input>
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="study_name">스터디이름</label>
          </b-col>
          <b-col cols="9" class="mt-2">
            <b-form-input v-model="input.study_name" id="study_name"></b-form-input>
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="recruit_number">모집인원</label>
          </b-col>
          <b-col class="spinbuttontag mt-2" cols="9">
            <b-form-input id="recruit_number" v-model="input.recruit_number"></b-form-input> 명
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="studytype_name">스터디분류</label>
          </b-col>
            <!-- <b-form-select v-for="option in input.options" :key="option.idx">{{ option.studytypeName }}</b-form-select> -->
          <b-col class="selectag mt-2" cols="9">
            <b-form-select v-model="input.studytype_name" :options="input.options" id="studytype_name"></b-form-select>
            <!-- <option value="">123</option> -->
          </b-col>
        
          <b-col cols="3" class="mt-2">
            <label for="content">내용</label>
          </b-col>
          <b-col cols="9" class="mt-2">
            <b-form-textarea v-model="input.content" id="content" row="8" max-rows="10"></b-form-textarea>
          </b-col>
          <b-col>
            <b-button class="mx-3 mt-4" style="background-color: #DAC7F9" @click="createBoardForm">게시글 생성</b-button>
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
import axios from 'axios'

export default {
  name: 'BoardForm',
  data() {
    return {
      input: {
        header: false,
        content_title: null,
        study_name: null,
        content_status: false,
        recruit_number: null,
        studytype_name: null,
        options: [
          // {value: '1', text:'Java'},
          // {value: '2', text:'C'},
          // {value: '3', text:'C++'},
          // {value: '4', text:'Python'},
          // {value: '5', text:'C#'},
          // {value: '6', text:'PHP'},
          // {value: '7', text:'Javascript'},
          // {value: '8', text:'RUBY'},
          // {value: '9', text:'HTML'},
          // {value: '10', text:'CSS'},
          // {value: '11', text:'Kotlin'},
          // {value: '12', text:'Swift'},
          // {value: '13', text:'Spring'},
          // {value: '14', text:'React'},
          // {value: '15', text:'Vuejs'},
          // {value: '16', text:'Angular JS'},
          // {value: '17', text:'Django'},
          // {value: '18', text:'CS'},
          // {value: '19', text:'Database'},
          // {value: '20', text:'Frontend'},
          // {value: '21', text:'Backend'},
          // {value: '22', text:'Android'},
          // {value: '23', text:'IOS'},
          // {value: '24', text:'Mobile'},
          // {value: '25', text:'AI'},
          // {value: '26', text:'BlockChain'},
          // {value: '27', text:'BigData'},
          // {value: '28', text:'IoT'},
          // {value: '29', text:'Arduino'},
          // {value: '30', text:'RaspberryPi'},
          // {value: '31', text:'Study With Me'},
          // {value: '32', text:'기타'},
          ],
        content: null,
        user_id: null,
      },
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
        study_name: this.input.study_name,
        content_status: this.input.content_status,
        recruit_number: this.input.recruit_number,
        studytype_name: this.input.studytype_name,
        content: this.input.content,
        // options: this.$store.state.studyOptions
      }
      axios({
        method: 'post',
        url: 'http://i6e103.p.ssafy.io:8080/api/board/register',
        data: createBoardItem,
        headers: this.getToken()
      })
      .then(res => {
        console.log(res.data)
        this.$router.push({name: "MainBoard"})
        console.log('스터디옵션 확인')
        // this.input.options = this.$store.state.studyOptions
        console.log(this.input.options)
        // this.options = 
      })
      .catch(err => {
        console.log(err)
      })
    },
    getStudyType() {
      this.$store.dispatch('getStudyType')
      console.log('스터디 정보 가져오는지 확인')
      this.input.options = this.$store.state.studyOptions
      console.log(this.input.options)
    },
  },
  created(){
    this.getToken()
    this.getStudyType()
  }
}
</script>

<style>
.form {
  display: flex;
  justify-content: center;
}

.radio_hearder {
  display: flex;
  justify-content: center;
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

button{
  size: 40px;
}
</style>