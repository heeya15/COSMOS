<template>
  <div class="apply m-5" style="width:1000px;">
    <h3>가입 요청 회원</h3>
    <div v-show="applyMembers===null" class="m-5">가입 요청한 회원이 없습니다.</div>
    <b-row v-for="member in applyMembers" :key="member.id" class="m-5 align-items-center">    
      <b-col cols="2"></b-col>
      <b-col cols="4" style="background-color: #ededed; height: 50px; line-height:50px; border-radius:8px;">{{ member.user_name }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;({{ member.user_id }})</b-col>
      <b-col cols="4" v-if="power.leader">
        <button class="okBtn" @click="addApplyMember(member)">승인</button>
        <button class="deleteBtn" @click="deleteApplyMember(member.applymember_no)">거절</button>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
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
      http({
        method: 'GET',
        url: `/study/applyMember/searchAll/${this.studyNo}`
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
      http({
        method: 'POST',
        url: '/studymember/register',
        data: memberInfo
      })
      .then(() => {
        alert(`${memberInfo.user_id}님이 스터디 멤버로 추가되었습니다.`)
        this.deleteApplyMember(member.applymember_no)
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteApplyMember(applymember_no) {
      http({
        method: 'DELETE',
        url: `/study/applyMember/remove/${applymember_no}`
      })
      .then(() => {
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

<style scoped>
.okBtn {
  border: none;
  border-radius: 8px;
  background-color: #afa2dd;
  height: 40px;
  width: 100px;
  margin-right: 10px;
}
.okBtn:hover {
  background-color: #c8c1e4;
  color: white;
}
.deleteBtn {
  color: white;
  border: none;
  border-radius: 8px;
  background-color: #dc3545;
  height: 40px;
  width: 100px;
}
.deleteBtn:hover {
  background-color: #be1e1e;
}
</style>