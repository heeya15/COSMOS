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
          <b-col class="selectag mt-2" cols="9">
            <b-form-select v-model="input.studytype_name" :options="input.options" id="studytype_name"></b-form-select>
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
            { value: 'JavaScript', text: 'JavaScript' },
            { value: 'Spring', text: 'Spring' },
            { value: 'Java', text: 'Java' },
            { value: 'Python', text: 'Python' },
            { value: '기타', text: '기타' },
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
    createBoardForm() {
      const createBoardItem = {
        header: this.input.header,
        content_title: this.input.content_title,
        study_name: this.input.study_name,
        content_status: this.input.content_status,
        recruit_number: this.input.recruit_number,
        studytype_name: this.input.studytype_name,
        content: this.input.content,
        user_id: this.$store.state.user_ID,
      }
      axios({
        method: 'post',
        url: 'http://localhost:8080/api/board/register',
        data: createBoardItem,
      })
        .then(res => {
          console.log(res.data)
          this.$router.push({name: "MainBoard"})
          
        })
        .catch(err => {
          console.log(err)
        })
    },
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
  /* align-content: center; */
}

button{
  size: 40px;
}
</style>