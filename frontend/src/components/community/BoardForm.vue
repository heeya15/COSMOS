<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>글생성</h3>
    
      <b-container fluid style="width: 800px">
        <b-row class="my-1">
          <b-col sm="2">
          <label class="" for="some-radios">말머리</label>
          </b-col>
            <b-col sm="10">
            <label class="mx-5"><input type="radio" name="some-radios" value="A">스터디원 구함</label>
            <label class="mx-5"><input type="radio" name="some-radios" value="B">스터디 구함</label>
          </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-study-title">제목</label>
            </b-col>
            <b-col sm="10">
            <b-form-input id="input-study-title"></b-form-input>
          </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-study-name">스터디이름</label>
            </b-col>
            <b-col sm="10">
            <b-form-input id="input-study-name"></b-form-input>
          </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-study-recruit-people">모집인원</label>
            </b-col>
            <b-col sm="10">
            <b-form-spinbutton id="input-study-recruit-people" v-model="value" inline style="padding: 6px;"></b-form-spinbutton> 명
          </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="2">
            <label for="input-study-type">스터디분류</label>
            </b-col>
            <b-col sm="10">
            <b-form-select v-model="selected" :options="options" id="input-study-type"></b-form-select>
          </b-col>
        </b-row>

        <b-row class="my-1">
          <b-col sm="2">
            <label for="textarea-rows">내용</label>
            </b-col>
            <b-col sm="10">
            <b-form-textarea id="textarea-rows" row="8" max-rows="10"></b-form-textarea>
          </b-col>
        </b-row>
        <b-button class="mx-3 mt-5" style="background-color: #DAC7F9" @click="goBoardDetail">게시글 생성</b-button>
        <b-button class="mx-3 mt-5" style="background-color: #DAC7F9" @click="backBoardMain">취소</b-button>
      </b-container>
    </div>
   
</template>

// 스터디원이 구하면 피그마에 스터디 구함만 체크로 표시되어 있으면 될 듯

<script>
export default {
  name: 'BoardForm',
  data() {
      return {
        value: 4,
        selected: null,
        options: [
          { value: null, text: 'JavaScript' },
          { value: 'a', text: 'Spring' },
          { value: 'b', text: 'Java' },
          { value: { C: '3PO' }, text: 'python' },
        ]
      }
    },
  methods: {
    backBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },
    goBoardDetail(){
      this.$router.push({name: 'BoardDetail'})
    }
  }
}
</script>

<style>
.radio_hearder {
  display: flex;
  justify-content: center;
}

.study_title {
  color: black;
  font-size: 1rem;
}

#input-study-type {
  padding: 6px;
}
</style>