<template>
  <!-- 공지사항 페이지 -->
  <center>
  <div class="notice m-5" style="width:1000px;">
    <h3>공지사항</h3>
    <!-- 등록된 공지사항이 있을 때 -->
    <div v-if="notice.studyManageNo">
      <div v-show="!notice.modify">
        <p style="text-align:right;">변경일: {{notice.createdAt}}</p>
        <div class="noticeContainer" style="background-color:#c8c1e4;">
          {{notice.studymanageNotice}}
        </div>
        <!-- 스터디장만 수정,삭제 보이게 -->
        <b-button-group v-show="power.leader" class="m-2" style="float:right;">
          <button @click="notice.modify=true" class="mr-3 modifyBtn">수정</button>
          <button @click="deleteNotice" class="deleteBtn">삭제</button>
        </b-button-group>
      </div>
        <div class="noticeContainer" v-show="power.leader && notice.modify">
          <b-form-textarea id="textarea" v-model="notice.studymanageNotice" :placeholder=notice.studymanageNotice rows="3" max-rows="6"></b-form-textarea>
          <button @click="modifyNotice" class="modifyBtn m-2">수정</button>
        </div>
    </div>

    <!-- 등록된 공지사항이 없을 때 -->
    <div class="noticeContainer" v-else-if="power.leader && noNotice">
      <b-form-textarea id="textarea" v-model="notice.studymanageNotice" placeholder="스터디 공지사항을 입력해주세요." rows="3" max-rows="6"></b-form-textarea>
      <button @click="registNotice" class="createBtn m-2">등록</button>
    </div>
    <div v-else-if="(!power.leader) && noNotice">등록된 공지사항이 없습니다.</div> 
    
  </div>
  </center>
</template>

<script>
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
    // 공지사항 조회
    showStudyNotice() {
      http({
        method: 'GET',
        url: `/studyManage/search/${this.studyNo}`,
      })
      .then(res => {
        // console.log(res)
        if (!res.data) {
          this.noNotice = true
        } else {
        this.notice.studymanageNotice = res.data.studymanageNotice
        this.notice.toggleNotice = true
        this.notice.createdAt = res.data.createdAt.slice(0,10)
        this.notice.studyManageNo = res.data.studymanageId.studyManageNo
        this.noNotice = false
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
      })
      .then(() => {
        // console.log(res)
        this.notice.modify = false
        this.noNotice = false
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
      })
      .then(() => {
        // console.log(res)
        this.notice.modify = false
        this.noNotice = false
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
        // this.notice.studymanageNotice = ''
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
  },
}
</script>

<style scoped>
.noticeContainer {
  width:1000px; 
  height:100px; 
  padding:10px;
}

/* 버튼 */
.createBtn {
  border: none;
  border-radius: 8px;
  background-color: #afa2dd;
  height: 40px;
  width: 100px;
  float: right;
}
.createBtn:hover {
  background-color: #c8c1e4;
  color: white;
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