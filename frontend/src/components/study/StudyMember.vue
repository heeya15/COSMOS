<template>
  <div class="notice" style="width:1000px;">
    <h3>스터디원 정보</h3>
    <!-- 스터디장만 회원 추가 가능 -->
    <b-row v-if="power.leader" class="m-3">
      <b-col cols="2" offset="2"><label for="apply-member-form">회원 추가</label></b-col>
      <b-col cols="5"><b-form-input
        id="apply-member-form"
        class="mb-2 mr-sm-2 mb-sm-0"
        placeholder="추가할 회원의 아이디를 입력하세요." v-model="newMemberId"
      ></b-form-input></b-col>
      <b-col cols="1"><button @click="addMember"><b-icon icon="person-plus"></b-icon></button></b-col>
    </b-row>
    
    <table class="table table-bordered table-hover align-middle">
      <thead class="table-danger">
        <tr>
          <th>이름</th>
          <th>Email</th>
          <th>출석여부</th>
          <th>공부시간</th>
          <th>점수</th>
          <th v-if="power.leader"></th>
        </tr>
      </thead>
      <tbody v-for="member in studyMembers" :key="member.id" class="info">
        <tr>
        <td>{{member.user_name}}({{member.user_id}})</td>
        <td>{{member.user_email}}</td>
        <td>{{member.attendance}}</td>
        <td>{{member.studytime}}</td>
        <td>{{member.score}}</td>
        <td v-if="power.leader&&member.user_id!==myId">
          <b-button class="me-3" variant="danger" @click="deleteMember(member.studymember_no)">강퇴</b-button>
          <!-- 권한이 true=>false, false=>true 바뀌게 설정 -->
          <b-button variant="success" @click="giveAuthority(member.studymember_no)">권한</b-button>
        </td>
        <td v-else></td>
        </tr>
			</tbody>
    </table>



    <b-row>
      <hr>
      <b-col cols="2">이름</b-col>
      <b-col cols="2">Email</b-col>
      <b-col cols="2">출석여부</b-col>
      <b-col cols="2">공부시간</b-col>
      <b-col cols="2">점수</b-col>
    </b-row>
    <hr>

    <b-row v-for="member in studyMembers" :key="member.id" class="my-2 info">
      
      <b-col cols="2">{{member.user_name}}</b-col>
      <b-col cols="2">{{member.user_email}}</b-col>
      <b-col cols="2">{{member.attendance}}</b-col>
      <b-col cols="2">{{member.studytime}}</b-col>
      <b-col cols="2">{{member.score}}</b-col>
      <!-- 스터디장이면 강퇴가능 -->
      <b-col v-if="power.leader&&member.user_id !== myId"><b-button variant="danger" @click="deleteMember(member.studymember_no)">강퇴</b-button></b-col>
    </b-row>
  </div>  
</template>

<script>
// import http from 'http'
import http from "@/util/http-common.js";
import JwtDecode from 'jwt-decode'
import { mapState } from 'vuex'

export default {
  name: 'StudyMember',
  data() {
    return {
      studyNo: this.$route.params.studyNo,
      newMemberId: null,
      myId: '',
    }
  },
  methods: {
    getMyId() {
      var token = localStorage.getItem('jwt')
      var decoded = JwtDecode(token);
      var myId = decoded.sub;

      this.myId = myId
    },
    getStudyMembers() {
      this.$store.dispatch('getStudyMembers', this.studyNo)
    },
    
    addMember() {
      const memberInfo = {
        authority: this.$store.state.power.leader,
        leader: this.$store.state.power.leader,
        study_no: this.$route.params.studyNo,
        user_id: this.newMemberId
      }
      http({
        method: 'POST',
        url: '/studymember/register',
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
      http({
        method: 'DELETE',
        url: `/studymember/remove/${studymember_no}`
      })
      .then(() => {
        // console.log(res)
        this.getStudyMembers()
      })
      .catch(err => {
        console.log(err)
      })
    },
    giveAuthority(studymember_no) {
      http({
        method: 'PUT',
        url: '/studymember/updateAuthority',
        data: {studymember_no: studymember_no, authority: true}
      })
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  computed: {
    ...mapState([
      'studyMembers',
      'power'
    ])
  },
  created() {
    this.getStudyMembers()
    this.getMyId()
  }
}
</script>

<style scoped>
  .info {
    height:50px;
  }
</style>