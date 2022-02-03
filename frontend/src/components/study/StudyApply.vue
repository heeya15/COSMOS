<template>
  <div class="apply" style="width:1000px;">
    <h3>가입 요청 회원</h3>
    <div v-show="applyMembers===null">가입 요청한 회원이 없습니다.</div>
    <b-row v-for="member in applyMembers" :key="member.id">    
      <b-col style="background-color:lightgray;">{{ member.user_name }}</b-col>
      <b-col style="background-color:lightgray;">{{ member.user_id }}</b-col>
      <b-col v-if="power.leader">
        <button @click="addApplyMember(member)">승인</button>
        <button @click="deleteApplyMember(member.applymember_no)">거절</button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  name: 'StudyApply',
  data(){
    return {
      studyNo: this.$route.params.studyNo,
      applyMembers: null,
    }
  },
  methods: {
    showApplyMember() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/study/applyMember/searchAll/${this.studyNo}`
      })
      .then(res =>{
        if (res.data.length === 0) {
          this.applyMembers = null
        }
        else {
          this.applyMembers = res.data
        }
      })
      .catch(err => {
        console.log(err)
      })
    },
    addApplyMember(member) {
      const memberInfo = {
        authority: true,
        leader: true,
        study_no: this.$route.params.studyNo,
        user_id: member.user_id
      }
      axios({
        method: 'POST',
        url: 'http://i6e103.p.ssafy.io:8080/api/studymember/register',
        data: memberInfo
      })
      .then(res => {
        console.log(res)
        alert(`${memberInfo.user_id}님이 스터디 멤버로 추가되었습니다.`)
        this.deleteApplyMember(member.applymember_no)
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteApplyMember(applymember_no) {
      axios({
        method: 'DELETE',
        url: `http://i6e103.p.ssafy.io:8080/api/study/applyMember/remove/${applymember_no}`
      })
      .then(res => {
        console.log(res)
        // alert(`${this.memberInfo.userName}님이 스터디 신청 멤버에서 삭제되었습니다.`)
        this.showApplyMember()
        this.$store.dispatch('getStudyMembers', this.studyNo)
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  computed:{
    ...mapState([
      'power',
    ])
  },
  created() {
    this.showApplyMember()
  }
}
</script>

<style>

</style>