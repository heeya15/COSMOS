<template>
  <div class="apply" style="width:1000px;">
    <h3>가입 요청 회원</h3>
    <StudyApplyItem v-for="member in applyMembers" :key="member.id" :member="member"/>
  </div>
</template>

<script>
import StudyApplyItem from '@/components/study/StudyApplyItem.vue'
import axios from 'axios'

export default {
  name: 'StudyApply',
  components: {
    StudyApplyItem,
  },
  data(){
    return {
      studyNo: this.$route.params.studyNo,
      applyMembers: null,
      memberInfo: {
        userId: null,
        userEmail: null,
        userName: null,
      }
    }
  },
  methods: {
    showApplyMember() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/study/applyMember/searchAll/${this.studyNo}`
      })
      .then(res =>{
        console.log(res.data)
        this.applyMembers = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    
  },
  created() {
    this.showApplyMember()
  }
}
</script>

<style>

</style>