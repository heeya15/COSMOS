<template>
  <center>
    <div style="width:1200px;">
      <h1>Study Detail</h1>
      <!-- 스터디 정보 받아오기 -->
      <h2>Study name</h2> 
      <span>url 주소</span>
      <!-- 스터디 정보 수정 추가 -->
      <b-button variant="danger">스터디 수정</b-button>
      <b-button variant="danger">스터디 삭제</b-button>
      <b-button variant="danger">스터디 탈퇴</b-button>
      <div class="buttongroup d-flex justify-content-between" style="width:800px;">
        <b-button @click="toggleNotice=true">공지사항</b-button>
        <!-- 스터디장만 가입요청확인 가능 -->
        <b-button>가입 요청 확인 </b-button>

        <!-- 스터디장만 회원추가가능 -->
        <b-button>스터디원 정보</b-button>
      </div>
      <hr>      
        <StudyNotice v-show="toggleNotice"/>
    </div>
  </center>
</template>

<script>

import StudyNotice from '@/components/study//StudyNotice.vue'

export default {
  name: 'StudyDetail',
  components: {
    StudyNotice,
  },
  data(){
    return {
      studyNo:null,
      toggleNotice: true,
      toggleApply: false,
      apply: null,
      toggleMember: false,
    }
  },
}
</script>

<style>

</style>