<template>
  <div>
    <h3>스터디 타입 받아오기</h3>
    
  </div>
</template>

<script>
export default {
  name: "StudyType",
  data() {
    return {
      optins: [],
    }
  },
  methods: {
    getStudyType() {
      this.$store.dispatch('getStudyType')
      console.log('스터디 정보 가져오는지 확인')
      this.options = this.$store.state.studyOptions
      console.log(this.options)
    },
  },
  created(){
    this.getStudyType()
  }
}
</script>

<style>

</style>