<template>
  <center>
    <h1>MY PAGE</h1>
    <div class="profile">
      <div>아이디: {{user_id}}</div>
      <div>이름: {{user_name}}</div>
      <div>이메일: {{user_email}}</div>
      <b-button variant="danger" class="me-3" @click="signOut">회원탈퇴</b-button>
      <b-button variant="warning" v-b-modal.modal-1>비밀번호 변경</b-button>

      <!-- 비밀번호 일치하면, 비밀번호 수정가능 -->
      <b-modal
      id="modal-1"
      ref="modal"
      title="비밀번호 확인이 필요합니다."
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          label="현재 비밀번호를 입력하세요."
          label-for="passwordCheck"
          invalid-feedback="비밀번호를 입력하세요."
          :state="passwordState"
        >
          <b-form-input
            id="passwordCheck"
            type="password"
            v-model="passwordCheck"
            :state="passwordState"
            required
          ></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
    </div>
  </center>
</template>

<script>
import axios from 'axios'

export default {
  name: 'MyPage',
  data() {
    return {
    user_id : null,
    user_name : null,
    user_email : null,
    user_password : null,
    passwordCheck : null,
    
    passwordState: null,
    submittedpassword: []
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
    getUserInfo(){
      axios({
        method: 'GET',
        url: 'http://localhost:8080/api/user/me',
        headers: this.getToken()
      })
      .then(res =>{
        // console.log(res)
        this.user_id=res.data['user_id']
        this.user_name=res.data['user_name']
        this.user_email=res.data['user_email']
        this.user_password=res.data['user_password']
      })
      .catch(err =>{
        console.log(err)
      })
    },
    signOut(){
      axios({
        method: 'DELETE',
        url: `http://localhost:8080/api/user/remove/${this.user_id}`,
        headers: this.getToken()
      })
      .then(res => {
        console.log(res)
        this.$store.dispatch('logOut')
        this.$router.push({ name:'SignUp' })
        alert('COSMOS에서 회원탈퇴완료')
      })
      .catch(err => {
        console.log(err)
      })
    },


    checkFormValidity() {
      const valid = this.$refs.form.checkValidity()
      this.passwordState = valid
      return valid
    },
    resetModal() {
      this.passwordCheck = null
      this.passwordState = null
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return
      }
      // Push the name to submitted names
      // if (this.passwordCheck === this.user_password) {
      //   console.log('비밀번호변경')
      // }
      this.submittedpassword.push(this.passwordCheck)
      // Hide the modal manually
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
    }


  },
  created(){
    this.getUserInfo()
  }
}
</script>

<style>
.profile {
  width: 1000px;
  border: 1px solid rgb(204, 143, 143);
}
b-modal {
  width:1000px;
}
</style>