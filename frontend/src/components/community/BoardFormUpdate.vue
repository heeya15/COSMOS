<template>
  <div>
    <h1>스터디 모집 게시판</h1>
    <hr>
    <h3>글 수정</h3>
    <b-button class="mx-3 mt-5" style="background-color: #DAC7F9" @click="backBoardMain">게시글 수정</b-button>
        <b-button class="mx-3 mt-5" style="background-color: #DAC7F9" @click="goBoardDetail">취소</b-button>
  </div>
   
</template>

<script>
export default {
  name: 'BoardFormUpdate',
  methods: {
    backBoardMain() {
      this.$router.push({name: 'MainBoard'})
    },
    goBoardDetail(){
      this.$router.push({name: 'BoardDetail'})
    },
  }
}
</script>

<style>

</style>