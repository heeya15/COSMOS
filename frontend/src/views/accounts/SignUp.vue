<template>
  <div>    
    <center>
      <div style="width:600px; background-color: #DAC7F9;" class="p-5">
        <p>회원가입</p>
        <b-form style="width:400px;" @submit.stop.prevent>        
          <b-form-input class="mt-3" style="height:50px;" id="username" v-model="credentials.userName" placeholder="이름" required></b-form-input>
          <b-row>
            <b-col><b-form-input class="mt-3" style="height:50px;" id="userId" v-model="credentials.userId"  placeholder="아이디" required></b-form-input></b-col>
            <b-col><b-button @click="idConfirm" class="mt-3">중복확인</b-button></b-col>
          </b-row>
          <div v-show="toggleIdCheck">
            <span v-if="idCheck">사용할 수 있는 아이디입니다.</span>
            <span else>사용할수 없는 아이디입니다.</span>
          </div>
          <b-form-input class="mt-3" style="height:50px;" id="password" v-model="credentials.userPassword" type="password" aria-describedby="password-help-block" placeholder="비밀번호" :state=pwCheck required></b-form-input>
          <b-form-text id="password-help-block">비밀번호는 8~12자리여야 합니다.<br>문자,숫자,특수기호를 최소 1개씩 포함해야 합니다.</b-form-text>
          <b-form-input class="mt-3" style="height:50px;" id="passwordCheck" v-model="passwordCheck" type="password" placeholder="비밀번호 확인" required></b-form-input>
          <b-row>
            <b-col cols="9"><b-form-input class="mt-3" style="height:50px;" id="email" v-model="credentials.userEmail" required placeholder="이메일"></b-form-input></b-col>
            <b-col><b-button class="mt-3" style="height:50px;">인증</b-button></b-col>
          </b-row>
          <b-button class="mt-3" style="height:50px;" type="submit" @click="signUp">회원가입</b-button>
        </b-form>
      </div>
    </center>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name:'SignUp',
  data() {
    return {
      credentials: {
        userName:'',
        userId:'',
        userPassword:'',
        userEmail:'',
      },
      idCheck: null,
      pwCheck: null,
      toggleIdCheck: false,
      passwordCheck:''  
    }
  },
  methods: {
    signUp() {
      this.$store.dispatch('signUp',this.credentials)
    },
    idConfirm() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/user/idcheck/${this.credentials.userId}`,
      })
      .then(res => {
        console.log(res)
        this.toggleIdCheck = true
        // this.idConfirm = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    // passwordValidation() {
    //   var pw = this.credentials.userPassword
    //   var num = pw.search(/[0-9]/g)
    //   var eng = pw.search(/[a-z]/ig)
    //   var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi)

    //   if(pw.length < 8 || pw.length > 12){
    //     // alert("8자리 ~ 12자리 이내로 입력해주세요.")
    //     this.pwCheck = false
    //   }else if(pw.search(/\s/) != -1){
    //     // alert("비밀번호는 공백 없이 입력해주세요.")
    //     this.pwCheck = false
    //   }else if(num < 0 || eng < 0 || spe <0) {
    //     // alert("영문,숫자,특수문자를 혼합하여 입력해주세요.")
    //     this.pwCheck = false
    //   }else {
    //     console.log("통과")
    //     this.pwCheck = true 
    //   }
    // },
  },
  // computed: {
  //   pwState(){
  //     return this.passwordValidation()
  //   }
  // }
}
</script>

<style scoped>
p {
  font-size: 40px;
  color: white;
}
</style>