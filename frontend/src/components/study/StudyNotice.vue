<template>
  <!-- 공지사항 페이지 -->
  <div class="notice" style="width:1000px;">
    <h3>공지사항</h3>
    <div v-if="notice.studyManageNo">
      <div v-show="!notice.modify">
        <p style="text-align:right;">변경일: {{notice.createdAt}}</p>
        <div style="width:1000px; height:100px; padding:10px; background-color:lightgray;">
          {{notice.studymanageNotice}}
        </div>
        <!-- 스터디장만 수정,삭제 보이게 -->
        <div v-show="power.leader">
          <button @click="notice.modify=true">수정</button>
          <button @click="deleteNotice">삭제</button>
        </div>
      </div>
        <div v-show="power.leader && notice.modify">
          <b-form-textarea id="textarea" v-model="notice.studymanageNotice" :placeholder=notice.studymanageNotice rows="3" max-rows="6"></b-form-textarea>
          <button @click="modifyNotice">수정</button>
        </div>
    </div>
    <div v-else-if="power.leader">
      <b-form-textarea id="textarea" v-model="notice.studymanageNotice" placeholder="스터디 공지사항을 입력해주세요." rows="3" max-rows="6"></b-form-textarea>
      <button @click="registNotice">등록</button>
    </div>
    <div v-else>등록된 공지사항이 없습니다.</div> 
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  name: 'StudyNotice',
  data(){
    return {
      studyNo:this.$route.params.studyNo,
      notice: {
        toggleNotice: true,
        studymanageNotice: null,
        studyManageNo: null,
        createdAt: '',
        modify:false,
      },
    }
  },
  methods: {
    getToken(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`
      }
      return header
    },
    // 공지사항 조회
    showStudyNotice() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/studyManage/search/${this.studyNo}`,
      })
      .then(res => {
        // console.log(res)
        this.notice.studymanageNotice = res.data.studymanageNotice
        this.notice.toggleNotice = true
        this.notice.createdAt = res.data.createdAt.slice(0,10)
        this.notice.studyManageNo = res.data.studymanageId.studyManageNo
      })
      .catch(err => {
        console.log(err)
      })
    },
    // 공지사항 수정
    modifyNotice() {
      const updateFormdata = {
        studyNo: this.studyNo,
        studymanageNotice: this.notice.studymanageNotice
      }
      axios({
        method: 'PUT',
        url: 'http://i6e103.p.ssafy.io:8080/api/studyManage/update',
        data: updateFormdata,
        // headers: this.getToken()
      })
      .then(() => {
        // console.log(res)
        this.notice.modify = false
        this.$router.go()
      })
      .catch(err =>{
        console.log(err)
      })
    },
    // 공지사항 등록
    registNotice() {
      const updateFormdata = {
        studyNo: this.studyNo,
        studymanageNotice: this.notice.studymanageNotice
      }
      axios({
        method: 'POST',
        url: 'http://i6e103.p.ssafy.io:8080/api/studyManage/register',
        data: updateFormdata,
        // headers: this.getToken()
      })
      .then(() => {
        // console.log(res)
        this.notice.modify = false
        this.$router.go()
      })
      .catch(err =>{
        console.log(err)
      })
    },
    // 공지사항 삭제
    deleteNotice() {
      axios({
        method: 'DELETE',
        url: `http://i6e103.p.ssafy.io:8080/api/studyManage/remove/${this.studyNo}`
      })
      .then(() => {
        this.$router.go()
      })
      .catch(err =>{
        console.log(err)
      })
    }
  },
  computed:{
    ...mapState([
      'power',
    ])
  },
  created() {
    this.showStudyNotice()
    this.getToken()
  }
  
}
</script>

<style>

</style>