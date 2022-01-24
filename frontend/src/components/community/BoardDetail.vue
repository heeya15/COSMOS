<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>상세보기</h3>
    <div>
      <!-- 작성자인 경우 수정을 보여주고 아니면 스터디 신청을 보여준다 -->
      <b-button style="background-color: #DAC7F9">스터디 신청</b-button>
      <b-button style="background-color: #DAC7F9" @click="boardFormEdit">수정</b-button>
      <b-button style="background-color: #DAC7F9" @click="goBoardMain">목록</b-button>
    </div>
  </div>  
</template>

<script>
export default {
  name: 'BoardDetail',
  methods: {
    goBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },
    boardFormEdit() {
      this.$router.push({name: 'BoardFormUpdate'})
    },
  },
}
</script>

<style>

</style>