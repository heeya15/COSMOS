<template>
  <div align="center" id="loginPage">
      <div class="mt-3" id="loginBox">
        <b-form class="p-5" id="loginForm" @submit.stop.prevent>
          <p id="login">로그인</p>
          <div class="input-box my-5">
            <input id="id" type="text" name="id" v-model="credentials.id" @keydown="resetMsg" placeholder="아이디" required/>
            <label for="id">아이디</label>
          </div>
          <div class="input-box mb-3">
            <input id="password" type="password" ref="pwd" name="password" v-model="credentials.password" placeholder="비밀번호" required/>
            <label for="password">비밀번호</label>
            <b-icon icon="eye-slash-fill" id="pwdIcon" ref="pwdIcon" aria-hidden="true" @click="pwdPeek"></b-icon>
            <!-- <b-button id="passwordBtn" @click="pwdPeek">확인</b-button> -->
          </div>
          <div class="input-box">
            <div class="mt-4" id="message" :value="msg">{{ msg }}</div>
          </div>
          <!-- <b-form-input class="mt-3"  id="id" v-model="credentials.id" required placeholder="아이디"></b-form-input>
          <b-form-input class="mt-3" id="password" v-model="credentials.password" type="password" placeholder="비밀번호"></b-form-input> -->
          <b-button id="loginBtn" type="submit" @keydown.enter="logIn" @click="logIn">LOGIN</b-button>
        </b-form>
      </div>
  </div>
</template>

<script>

export default {
  name: 'LogIn',
  data() {
    return {
      credentials: {
        id:'',
        password:'',
      },
      msg: '',
      login: '',
    }
  },

  methods: {
    resetMsg() {
      if(this.msg != "") this.msg = "";
    },

    async logIn() {
      await this.$store.dispatch('logIn', this.credentials)

      // 입력값 초기화
      if(this.$store.state.isLogin === true) {
        this.msg = "";
        this.$router.push({name:'MainPage'})
      } else {
        this.credentials.id = '';
        this.credentials.password = '';
        this.msg = "잘못된 아이디 또는 비밀번호입니다.";
      }
    },

    pwdPeek() {
      if(this.$refs.pwd.type == "password") {
        this.$refs.pwd.type = "text";
        this.$refs.pwdIcon.icon = "eye-fill";
      } else {
        this.$refs.pwd.type = "password";
        this.$refs.pwdIcon.icon = "eye-fill";
      }
    },
  }, 

  beforeCreate() {
    this.login = this.$store.state.isLogin;   // false
  },
}
</script>

<style scoped>

#loginPage {
  height: 100%;
  position: relative;
  background-color: #DAC7F9;
}

#loginBox {
  height: 500px;
  width: 500px;
  background-color: rgb(255, 255, 255);
  box-shadow: 10px 10px 10px rgb(235, 235, 235);
  border-radius: 10px;
  position:absolute;
  left: 50%;
  top: 40%;
  transform: translate(-50%,-50%);
}

#loginForm {
  width: 400px;
  position:absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%,-50%);
}

#login {
  font-size: 40px;
  color: #3d3d3d;
  margin-top: 80px;
}

#id, #password{
  height: 50px;
}

#loginBtn {
  width: 100%;
  height: 50px;
  font-size: 20pt;
  background-color: #3d3d3d;
  margin-top: 100px;
}

.input-box{ 
  position: relative;
  margin: 10px 0;
}

.input-box > input{
  background: transparent;
  border: none;
  border-bottom: solid 1px #ccc;
  padding: 20px 0px 5px 0px;
  font-size: 15pt;
  width: 100%;
  color: #302f2f;
}

input::placeholder{ 
  color: transparent;
}

input:placeholder-shown + label{
  color: #ccc; 
  font-size: 15pt;
  top: 15px;
}

input:focus + label, label{ 
  color:#3d3d3d; 
  font-size:10pt; 
  pointer-events: none; 
  position: absolute; 
  left:0px; 
  top:0px; 
  transition: all 0.2s ease ; 
  -webkit-transition: all 0.2s ease; 
  -moz-transition: all 0.2s ease; 
  -o-transition: all 0.2s ease; 
}

input:focus, input:not(:placeholder-shown){
  border-bottom: solid 1px #3d3d3d;
  outline: none; 
}

/* 잘못된 정보 입력 안내 메시지 */
#message {
  font-size: 10pt;
  color: rgb(207, 1, 1);
}

#pwdIcon {
  position: absolute;
  cursor: pointer;
  right: 5%;
  top: 30%;
}

</style>