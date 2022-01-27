<template>
  <div>    
    <center>
      <div style="width:600px; background-color: #DAC7F9;" class="p-5">
        <p>회원가입</p>
        <b-form style="width:400px;" @submit.stop.prevent>        
          <b-form-input class="mt-3" style="height:50px;" id="username" v-model="credentials.userName" required placeholder="이름"></b-form-input>
          <b-form-input class="mt-3" style="height:50px;" id="userId" v-model="credentials.userId" required placeholder="아이디"></b-form-input>
          <b-form-input class="mt-3" style="height:50px;" id="password" v-model="credentials.userPassword" type="password" aria-describedby="password-help-block" placeholder="비밀번호"></b-form-input>
          <b-form-input class="mt-3" style="height:50px;" id="passwordCheck" v-model="passwordCheck" type="password" aria-describedby="password-help-block" placeholder="비밀번호 확인"></b-form-input>
          <b-row>
            <b-col cols="9"><b-form-input class="mt-3" style="height:50px;" id="email" v-model="credentials.userEmail" required placeholder="이메일"></b-form-input></b-col>
            <b-col><b-button class="mt-3" style="height:50px;">인증</b-button></b-col>
          </b-row>
          <b-button class="mt-3" style="height:50px;" @click="signUp">회원가입</b-button>
        </b-form>
      </div>
    </center>

  </div>
</template>

<script>
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
      passwordCheck:''  
    }
  },
  methods: {
    signUp() {
      this.$store.dispatch('signUp',this.credentials)
      this.$router.push({name:'LogIn'})
    }
  }
}
</script>

<style>
p {
  font-size: 40px;
  color: white;
}
</style>