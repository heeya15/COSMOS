<template>
  <div class="member m-5" style="width:1000px;">
    <h3>스터디원 정보</h3>
    <!-- 스터디장만 회원 추가 가능 -->
    <b-row v-if="power.leader" class="m-5">
      <b-col cols="2" offset="2"><label for="apply-member-form">회원 추가</label></b-col>
      <b-col cols="5"><b-form-input
        id="apply-member-form"
        class="mb-2 mr-sm-2 mb-sm-0"
        placeholder="추가할 회원의 아이디를 입력하세요." 
        v-model="newMemberId"
        @keydown.enter="addMember"
        autocomplete="off"
      ></b-form-input></b-col>
      <b-col cols="1"><button @click="addMember" class="memberBtn"><b-icon icon="person-plus-fill"></b-icon></button></b-col>
    </b-row>
    
    <table class="table table-bordered table-hover" v-show="power.leader">
      <thead style="background-color: #afa2dd;">
        <tr>
          <th>이름</th>
          <th>Email</th>
          <!-- <th>공부시간</th> -->
          <th>점수</th>
          <th>강퇴/권한</th>
        </tr>
      </thead>
      <tbody v-for="member in studyMembers" :key="member.id" class="info">
        <tr>
        <td v-if="member.leader">👑{{member.user_name}}({{member.user_id}})</td>
        <td v-else-if="!member.leader && member.authority">🌸{{member.user_name}}({{member.user_id}})</td>
        <td v-else>{{member.user_name}}({{member.user_id}})</td>
        <td>{{member.user_email}}</td>
        <!-- <td>{{member.studytime}}</td> -->
        <td>{{member.score}}</td>
        <td v-if="member.user_id!==myId">
          <b-button class="me-3 mr-1" variant="danger" @click="deleteMember(member.studymember_no)">강퇴</b-button>
          <!-- 권한이 true=>false, false=>true 바뀌게 설정 -->
          <b-button variant="info" @click="giveAuthority(member.studymember_no, member.authority)">권한</b-button>
        </td>
        </tr>
			</tbody>
    </table>

    <table class="table table-bordered table-hover align-middle" v-show="!power.leader">
      <thead style="background-color: #afa2dd;">
        <tr>
          <th>이름</th>
          <th>Email</th>
          <!-- <th>공부시간</th> -->
          <th>점수</th>          
        </tr>
      </thead>
      <tbody v-for="member in studyMembers" :key="member.id" class="info">
        <tr>
        <td v-if="member.leader">👑{{member.user_name}}({{member.user_id}})</td>
        <td v-else-if="!member.leader && member.authority">🌸{{member.user_name}}({{member.user_id}})</td>
        <td v-else>{{member.user_name}}({{member.user_id}})</td>
        <td>{{member.user_email}}</td>
        <!-- <td>{{member.studytime}}</td> -->
        <td>{{member.score}}</td>
        </tr>
			</tbody>
    </table>
  </div>  
</template>

<script>
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
      studyTotalMember: ''
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
    getStudyInfo() {
      http({
        method: 'GET',
        url: `/study/search/${this.studyNo}`
      })
      .then(res => {        
        this.studyTotalMember = res.data.totalMember
      })
      .catch(err => {
        console.log(err)
      })
    },

    addMember() {
      if (this.studyTotalMember === this.studyMembers.length) {
        alert('더이상 인원을 추가할 수 없습니다.')
        return
      }
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
      .then(() => {
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
        this.getStudyMembers()
      })
      .catch(err => {
        console.log(err)
      })
    },
    giveAuthority(studymember_no,authority) {
      if (authority === true){
        var memberAuthority = false
      } else {
        memberAuthority = true
      }
      http({
        method: 'PUT',
        url: '/studymember/updateAuthority',
        data: {studymember_no: studymember_no, authority: memberAuthority}
      })
      .then(() => {
        this.getStudyMembers()
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
    this.getStudyInfo()
  }
}
</script>

<style scoped>
  th, td {
  text-align: center;
  vertical-align : middle !important;
}
.memberBtn {
  border: none;
  border-radius: 8px;
  background-color: #afa2dd;
  height: 40px;
  width: 100%;
}
.memberBtn:hover {
  background-color: #c8c1e4;
  color: white;
}
</style>