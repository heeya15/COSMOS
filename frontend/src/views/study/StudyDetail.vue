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
        <b-button @click="togglenotice">공지사항</b-button>
        <!-- 스터디장만 가입요청확인 가능 -->
        <b-button @click="toggleapply">가입 요청 확인 </b-button>

        <!-- 스터디장만 회원추가가능 -->
        <b-button @click="togglemember">스터디원 정보</b-button>
      </div>
      <hr>      
        <StudyNotice v-show="toggleNotice"/>
        <StudyApply v-show="toggleApply"/>
    </div>
  </center>
</template>

<script>
import StudyNotice from '@/components/study//StudyNotice.vue'
import StudyApply from '@/components/study//StudyApply.vue'

export default {
  name: 'StudyDetail',
  components: {
    StudyNotice,
    StudyApply,

  },
  data(){
    return {
      toggleNotice: true,
      toggleApply: false,
      toggleMember: false,
    }
  },
  methods: {
    togglenotice(){
      this.toggleNotice=true
      this.toggleApply=false
      this.toggleMember=false
    },
    toggleapply(){
      this.toggleNotice=false
      this.toggleApply=true
      this.toggleMember=false
    },
    togglemember(){
      this.toggleNotice=false
      this.toggleApply=false
      this.toggleMember=true
    }

  }
}
</script>

<style>

</style>