<template>
  <!-- 공지사항 페이지 -->
  <div class="notice m-5" style="width:1000px;">
    <h3>공지사항</h3>
    <div v-if="notice.studyManageNo">
      <div v-show="!notice.modify">
        <p style="text-align:right;">변경일: {{notice.createdAt}}</p>
        <div style="width:1000px; height:100px; padding:10px; background-color:lightgray;">
          {{notice.studymanageNotice}}
        </div>
        <!-- 스터디장만 수정,삭제 보이게 -->
        <div v-show="power.leader" class="m-2 d-flex justify-content-end">
          <button @click="notice.modify=true" class="mr-3 modifyBtn" variant="success" size="lg">수정</button>
          <button @click="deleteNotice" size="lg" class="deleteBtn"><span style="color:white;">삭제</span></button>
        </div>
      </div>
        <div v-show="power.leader && notice.modify">
          <b-form-textarea id="textarea" v-model="notice.studymanageNotice" :placeholder=notice.studymanageNotice rows="3" max-rows="6"></b-form-textarea>
          <button @click="modifyNotice" class="modifyBtn m-2">수정</button>
        </div>
    </div>
    <div v-else-if="power.leader && noNotice">
      <b-form-textarea id="textarea" v-model="notice.studymanageNotice" placeholder="스터디 공지사항을 입력해주세요." rows="3" max-rows="6"></b-form-textarea>
      <button @click="registNotice" class="createBtn m-2">등록</button>
    </div>
    <div v-else-if="(!power.leader) && noNotice">등록된 공지사항이 없습니다.</div> 
  </div>
</template>

<script>
// import http from 'http'
import http from "@/util/http-common.js";
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
      noNotice:'',
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
      http({
        method: 'GET',
        url: `/studyManage/search/${this.studyNo}`,
      })
      .then(res => {
        console.log(res)
        if (!res.data) {
          // console.log('실행됨???')
          this.noNotice = true
        } else {
        this.notice.studymanageNotice = res.data.studymanageNotice
        this.notice.toggleNotice = true
        this.notice.createdAt = res.data.createdAt.slice(0,10)
        this.notice.studyManageNo = res.data.studymanageId.studyManageNo
        }
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
      http({
        method: 'PUT',
        url: '/studyManage/update',
        data: updateFormdata,
        // headers: this.getToken()
      })
      .then(() => {
        // console.log(res)
        this.notice.modify = false
        this.showStudyNotice()
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
      http({
        method: 'POST',
        url: '/studyManage/register',
        data: updateFormdata,
        // headers: this.getToken()
      })
      .then(() => {
        // console.log(res)
        this.notice.modify = false
        this.showStudyNotice()
      })
      .catch(err =>{
        console.log(err)
      })
    },
    // 공지사항 삭제
    deleteNotice() {
      http({
        method: 'DELETE',
        url: `/studyManage/remove/${this.studyNo}`
      })
      .then(() => {
        this.showStudyNotice()
        this.notice.studymanageNotice = ''
        this.notice.modify = false
        this.noNotice = true
        this.$router.go();
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
  },
  updated() {

  }
  
}
</script>

<style scoped>
.createBtn {
  border: none;
  border-radius: 8px;
  background-color: #e4c3f1;
  height: 40px;
  width: 100px;
  float: right;
}
.createBtn:hover {
  background-color: #ddaae6;
}
.modifyBtn {
  border: none;
  border-radius: 8px;
  background-color: #ffc107;
  height: 40px;
  width: 100px;
  float: right;
}
.modifyBtn:hover {
  background-color: #e2ab07;
}
.deleteBtn {
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