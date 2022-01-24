<template>
  <center>
    <h1>스터디 방 생성</h1>
    <hr>
    <div style="width:600px;" class="p-5">
      <b-row>
        <b-col cols="3">
          <label for="study_name" class="mt-2">스터디 이름</label>
        </b-col>
        <b-col>
          <b-form-input id="study_name" v-model="input.study_name"></b-form-input>
        </b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="url" class="mt-2">스터디 url</label>
        </b-col>
        <b-col>
          <div id="url">{{input.url}}{{input.study_name}}</div>
        </b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="studytype_name" class="mt-2">스터디 분류</label>
        </b-col>
        <b-col>
          <b-form-input id="studytype_name" v-model="input.studytype_name"></b-form-input>
        </b-col>
        <hr class="mt-3">

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
        <hr class="mt-3">

        <b-col cols="3">
          <label for="study_type" class="mt-1">스터디 종류</label>
        </b-col>
        <b-col class="mt-1">
          <input type="radio" id="public" value="public" v-model="input.study_type" default>
          <label for="public" class="ms-1 me-5">공개</label>
          <input type="radio" id="private" value="private" v-model="input.study_type">
          <label for="private" class="ms-1 me-5">비공개</label>
        </b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="studyTotalMember" class="mt-2">인원 수</label>
        </b-col>
        <b-col cols="5">
          <b-form-input id="studyTotalMember" placeholder="인원 수를 입력하세요."></b-form-input>
        </b-col>
        <b-col cols="1" class="mt-2">명</b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="study_password" class="mt-1">비밀번호</label>
        </b-col>
        <b-col>
          <b-form-input id="study_password" v-model="input.study_password" v-if="input.study_type==='private'" placeholder="4~10자리 숫자만 입력하세요."></b-form-input>
          <div v-else class="study_password mt-1">공개스터디는 비밀번호가 필요하지 않습니다.</div>
        </b-col>
        <hr class="mt-3">
        
        <b-col cols="3">초기 장치 설정</b-col>
        <b-col>
          <input type="checkbox" id="mic" value="mic" v-model="input.settings.mic">
          <label for="mic" class="ms-1 me-5">마이크</label>
          <input type="checkbox" id="cam" value="cam" v-model="input.settings.cam">
          <label for="cam" class="ms-1 me-5">카메라</label>
          <input type="checkbox" id="speaker" value="speaker" v-model="input.settings.speaker">
          <label for="speaker" class="ms-1 me-5">스피커</label>
        </b-col>
        <hr class="mt-3">

        <b-col cols="3">
          <label for="study_rule" class="mt-2">스터디 규칙</label>
        </b-col>
        <b-col>
          <b-form-textarea id="study_rule" v-model="input.study_rule" placeholder="스터디 규칙, 공지사항 등 입력" rows="3" max-rows="6"></b-form-textarea>
        </b-col>
        <button class="mt-4" type="button">스터디 생성</button>
      </b-row>
    </div>


  </center>

</template>

<script>
export default {
  name: 'StudyRoomCreateForm',
  data() {
    return {
      input: {
        study_name: null,
        url: 'http://www.cosmos.com/',
        studytype_name: null,
        image: null,
        study_type: null,
        number_of_member: null,
        study_rule: null,
        settings: {
          mic: null,
          cam: null,
          speaker: null,
        }
      }
    }
  },
  methods: {
    uploadImage() {
      var image = this.$refs.studyImg.files[0]
      const url = URL.createObjectURL(image)
      this.input.image = url
    }
  }
}
</script>

<style>
button {
  border: none;
  border-radius: 8px !important;
  background-color: #DAC7F9;
  height: 40px;
}
button:hover {
  background-color: #b488fc;
}
img {
  height: 150px;
  width: 200px;
}
.study_password {
  height:34px;
}

</style>