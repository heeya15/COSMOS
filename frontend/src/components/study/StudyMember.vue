<template>
  <div class="notice" style="width:1000px;">
    <h3>스터디원 정보</h3>
    <!-- 스터디장만 회원 추가 가능 -->
    <b-row class="m-3">
      <b-col cols="2" offset="2"><label for="apply-member-form">회원 추가</label></b-col>
      <b-col cols="5"><b-form-input
        id="apply-member-form"
        class="mb-2 mr-sm-2 mb-sm-0"
        placeholder="추가할 회원의 아이디를 입력하세요." v-model="newMemberId"
      ></b-form-input></b-col>
      <b-col cols="1"><button @click="addMember">추가</button></b-col>
    </b-row>

    <b-row>
      <hr>
      <b-col cols="2">이름</b-col>
      <b-col cols="2">Email</b-col>
      <b-col cols="2">출석여부</b-col>
      <b-col cols="2">공부시간</b-col>
      <b-col cols="2">점수</b-col>
    </b-row>
    <hr>

    <b-row v-for="member in studyMembers" :key="member.id" class="my-2">
      <b-col>{{member.user_name}}</b-col>
      <b-col>{{member.user_email}}</b-col>
      <b-col>{{member.attendance}}</b-col>
      <b-col>{{member.studytime}}</b-col>
      <b-col>{{member.score}}</b-col>
      <!-- 스터디장이면 강퇴가능 -->
      <b-col><b-button variant="danger" @click="deleteMember(member.studymember_no)">강퇴</b-button></b-col>
    </b-row>
  </div>  
</template>

<script>
import axios from 'axios'

export default {
  name: 'StudyMember',
  data() {
    return {
      studyNo: this.$route.params.studyNo,
      studyMembers: [],
      newMemberId: null,
    }
  },
  methods: {
    getStudyMembers() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/studymember/search/${this.studyNo}`,
      })
      .then(res => {
        // console.log(res.data)
        this.studyMembers = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    // store로 보내는거 생각해보자(add,delete)
    addMember() {
      const memberInfo = {
        authority: true,
        leader: true,
        score: 0,
        study_no: this.$route.params.studyNo,
        user_id: this.newMemberId
      }
      axios({
        method: 'POST',
        url: 'http://i6e103.p.ssafy.io:8080/api/studymember/register',
        data: memberInfo
      })
      .then(res => {
        console.log(res)
        alert(`${memberInfo.user_id}님이 스터디 멤버로 추가되었습니다.`)
        this.newMemberId = ''
        this.getStudyMembers()
      })
      .catch(err => {
        console.log(err)
      })
    },
    deleteMember(studymember_no) {
      axios({
        method: 'DELETE',
        url: `http://i6e103.p.ssafy.io:8080/api/studymember/remove/${studymember_no}`
      })
      .then(res => {
        console.log(res)
        this.getStudyMembers()
      })
    }
  },
  created() {
    this.getStudyMembers()
  }
}
</script>

<style scoped>

</style>