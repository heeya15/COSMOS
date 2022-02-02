<template>
  <div>
    {{ member }}
    <!-- {{ member.userName }} {{ member.userId.userId }} -->
    <button @click="addApplyMember">승인</button>
    <button @click="deleteApplyMember">거절</button>

  
  </div>  
</template>

<script>
import axios from 'axios'

export default {
  name: 'StudyApplyItem',
  props: {
    member: {
      type:Object
    }
  },
  data() {
    return {

    }
  },
  methods: {
    addApplyMember() {
      const memberInfo = {
        study_no: this.$route.params.studyNo,
        user_id: this.member.userId
      }
      axios({
        method: 'POST',
        url: 'http://i6e103.p.ssafy.io:8080/api/studymember/register',
        data: memberInfo
      })
      .then(res => {
        console.log(res)
        alert(`${this.memberInfo.userName}님이 스터디 멤버로 추가되었습니다.`)
        this.deleteApplyMember()
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteApplyMember() {
      axios({
        method: 'DELETE',
        url: `http://i6e103.p.ssafy.io:8080/api/study/applyMember/remove/${this.member.applymemberNo}`
      })
      .then(res => {
        console.log(res)
        // alert(`${this.memberInfo.userName}님이 스터디 신청 멤버에서 삭제되었습니다.`)
        this.$router.go()
      })
    }
  }
}
</script>

<style>

</style>