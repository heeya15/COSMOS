<template>
  <div align="center" id="signupPage">    
    <div class="mt-3" id="signupBox">
      <b-form class="p-5" id="signupForm" @submit.stop.prevent>        
        <p id="signup">회원가입</p>
        <div class="input-box mt-4">
          <input id="username" type="text" name="username" v-model="credentials.userName" placeholder="이름" required/>
          <label for="username">이름</label>
        </div>
        <div class="input-box mt-4">
          <b-row>
            <b-col cols="10">
              <input id="userId" type="text" name="userId" v-model="credentials.userId" placeholder="아이디" @onchange="changeId" @blur="checkId" required/>
              <label for="username">아이디</label>
            </b-col>
            <b-col cols="2"><b-button class="mt-3" @click="idConfirm" id="idCheckBtn">중복확인</b-button></b-col>
          </b-row>
          <div class="mt-1 message" :value="msg1">{{ msg1 }}</div>
        </div>
        <div class="input-box mt-4">
          <input id="password" type="text" ref="password" name="password" v-model="credentials.userPassword" placeholder="비밀번호" @blur="checkPassword" required/>
          <label for="username">비밀번호</label>
          <div class="mt-1 message" :value="msg2">{{ msg2 }}</div>
        </div>
        <div class="input-box mt-4">
          <input id="passwordCheck" type="password" name="passwordCheck" v-model="passwordCheck" placeholder="비밀번호 확인" @blur="doubleCheckPassword" required/>
          <label for="username">비밀번호 확인</label>
          <div class="mt-1 message" :value="msg3">{{ msg3 }}</div>
        </div>
        <div class="input-box mt-4">
          <b-row>
            <b-col cols="10">
              <input id="email" type="text" name="email" v-model="credentials.userEmail" placeholder="이메일" required/>
              <label for="username">이메일</label>
            </b-col>
            <b-col cols="2"><b-button class="mt-3" @click="emailAuth" id="emailBtn">인증</b-button></b-col>
          </b-row>
        </div>
        <b-button id="signupBtn" type="submit" @click="signUp">SignUp</b-button>

        <!-- <b-form-input class="mt-3" style="height:50px;" id="username" v-model="credentials.userName" placeholder="이름" required></b-form-input>
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
        <b-button class="mt-3" style="height:50px;" type="submit" @click="signUp">회원가입</b-button> -->
      </b-form> 
    </div>
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
      idCheck: false,
      pwCheck: false,
      toggleIdCheck: false,
      passwordCheck:'', 
      msg1:'',
      msg2:'',
      msg3:'',
      response: [],
    }
  },
  methods: {
    signUp() {
      if(this.idCheck && this.pwCheck) this.$store.dispatch('signUp',this.credentials)
      else console.log('아이디 또는 비밀번호가 잘못되었습니다.');
    },
    
    idConfirm() {
      axios({
        method: 'GET',
        url: `http://i6e103.p.ssafy.io:8080/api/user/idcheck/${this.credentials.userId}`,
      })
      .then(res => {
        console.log(res)
        this.response = res;
        console.log(this.response.status);
        this.toggleIdCheck = true
        this.idCheck = true;
        console.log(">>>>>>>>>>>>>>>>>>> ", this.idCheck);
      })
      .catch(err => {
        this.msg1 = '사용할 수 없는 아이디입니다.'
        this.idCheck = false;
        console.log(err)
      })
    },

    async checkId() {
      var idRule = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,12}$/;

      if(!idRule.test(this.credentials.userId)) {
        this.msg1 = '아이디는 4 ~ 12 자리수이며 문자, 숫자를 최소 1개씩 포함해야합니다.';
        this.idCheck = false;
      } else {
        this.msg1 = ''
        this.idCheck = true;
      }
    },

    changeId() {
      this.idCheck = false;
    },

    async checkPassword() {
      console.log(">>>>>>>>>>>>>>> 비밀번호를 체크해보자!!");
      var pwdRule = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/;

      if(!pwdRule.test(this.credentials.userPassword)) {
        this.msg2 = '비밀번호는 8 ~ 12 자리수이며 문자, 숫자, 특수기호를 최소 1개씩 포함해야합니다.';
        this.pwCheck = false;
      } else {
        this.msg2 = ''
        this.pwCheck = true;
      }
    },

    async doubleCheckPassword() {
      console.log(">>>>>>>>>>>>>>> 비밀번호 중복 체크해보자!!");
      
      if(this.credentials.userPassword == this.passwordCheck) {
        this.msg3 = '';
        this.pwCheck = true;
      } else {
        this.msg3 = '비밀번호가 일치하지 않습니다.';
        this.pwCheck = false;
      }
    },

    emailAuth() {

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
  color: #fff;
}

#signupPage {
  height: 90%;
  position: relative;
  background-color: #DAC7F9;
}

#signupBox {
  height: 630px;
  width: 500px;
  background-color: rgb(255, 255, 255);
  box-shadow: 10px 10px 10px rgb(235, 235, 235);
  border-radius: 10px;
  position:absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}

#signupForm {
  width: 400px;
  position:absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%,-50%);
}

#signup {
  color: #3d3d3d;
  margin-top: 100px;
}

#signupBtn {
  width: 100%;
  height: 50px;
  font-size: 20px;
  background-color: #3d3d3d;
  margin-top: 30px;
}

#emailBtn, #idCheckBtn {
  width: 60px;
}

.input-box{ 
  position: relative;
  margin: 10px 0;
}

.input-box input{
  background:transparent;
  border:none;
  border-bottom: solid 1px #ccc;
  padding: 20px 0px 5px 0px;
  font-size:14pt;
  width:100%;
  color: #302f2f;
}

input::placeholder{ 
  color:transparent;
}

input:placeholder-shown + label{
  color:#ccc; 
  font-size:14pt;
  top:15px;
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
  outline:none; 
}

/* 잘못된 정보 입력 안내 메시지 */
.message {
  font-size: 8pt;
  color: rgb(207, 1, 1);
}

</style>