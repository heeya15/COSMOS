<template>
  <div class="container">
    <h1 class="text-center">스터디 모집 게시판</h1>
    <hr>
    <h3 class="mb-4">게시글 목록</h3>
    <!-- <div class="searchbar">
      <b-form-select class="mx-5" v-model="selected" :options="options" style="width: 150px;"></b-form-select>
      <b-form-input style="width: 300px;" placeholder="검색어 입력"></b-form-input>
      <b-button style="background-color: #DAC7F9">검색</b-button>
    </div> -->
    <div>
      <board-list></board-list>
    </div>
    <div class="create_boardform mx-5">
      <b-button style="background-color: #DAC7F9" @click="goCreateForm">글생성</b-button>
    </div>
    <!-- <board-form></board-form> -->
  
  </div>  
</template>

<script>
import BoardList from '@/components/community/BoardList.vue'

export default {
  name: 'MainBoard',
  components: {
    BoardList,
  },
  data() {
      return {
        selected: null,
        options: [
          { value: null, text: '키워드 선택' },
          { value: 0, text: '진행중' },
          { value: 1, text: '완료' },
        ]
      }
    },
  methods: {
    goCreateForm() {
      this.$router.push({name: 'BoardForm'})
    }
  },
}
</script>

<style>

.searchbar {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.create_boardform {
  display: flex;
  justify-content: right;
}
</style>