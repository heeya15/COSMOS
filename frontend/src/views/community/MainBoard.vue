<template>
  <div class="container">
    <h1 class="text-center">스터디 모집 게시판</h1>
    <hr>
    <center>
      <h3 class="mb-4">게시글 목록</h3>
      <div>
        <board-list></board-list>
      </div>
    </center>
  </div>  
</template>

<script>
import BoardList from '@/components/community/BoardList.vue'

export default {
  name: 'MainBoard',
  components: {
    BoardList,
  },
}
</script>

<style scoped>

</style>