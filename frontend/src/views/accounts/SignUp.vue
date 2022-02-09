<template>
  <div align="center" id="signupPage">    
    <div class="my-3" id="signupBox">
      <b-form class="p-5" id="signupForm" @submit.stop.prevent>        
        <p id="signup">회원가입</p>
        <div class="input-box mt-4">
          <input id="username" type="text" name="username" v-model="credentials.userName" placeholder="이름" required/>
          <label for="username">이름</label>
        </div>
        <div class="input-box mt-4">
          <b-row>
            <b-col cols="8">
              <input id="userId" type="text" name="userId" v-model="credentials.userId" placeholder="아이디" @keydown="resetidDuplicate" @blur="idRuleCheck" required/>
              <label for="username" style="left: 15px;">아이디</label>
            </b-col>
            <b-col cols="4" class="pl-0"><b-button class="mt-3" @click="idDuplicateCheck" id="idCheckBtn">중복확인</b-button></b-col>
          </b-row>
          <div class="mt-1 message" :class="{ completeCheck: idDuplicate }">{{ idMsg }}</div>
        </div>
        <div class="input-box mt-4">
          <input id="password" type="text" ref="password" name="password" v-model="credentials.userPassword" placeholder="비밀번호" @keydown="resetpwdDouble" @blur="passwordRuleCheck" required/>
          <label for="password">비밀번호</label>
          <div class="mt-1 message">{{ pwdMsg1 }}</div>
        </div>
        <div class="input-box mt-4">
          <input id="passwordRule" type="password" name="passwordRule" v-model="passwordRule" placeholder="비밀번호 확인" @blur="doublePasswordCheck" required/>
          <label for="passwordRule">비밀번호 확인</label>
          <div class="mt-1 message">{{ pwdMsg2 }}</div>
        </div>
        <div class="input-box mt-4">
          <b-row>
            <b-col cols="8">
              <input id="email" type="text" name="email" :disabled="inputDisabled" v-model="credentials.userEmail" placeholder="이메일" required @blur="emailDuplicateCheck" />
              <label for="useremailname" style="left: 15px;">이메일</label>
              <div class="mt-1 message">{{ emailMsg }}</div>
            </b-col>
            <b-col cols="4" class="pl-0"><b-button class="mt-3" @click="sendEmail" id="emailBtn">인증</b-button></b-col>
          </b-row>
        </div>
        <div v-if="email">
          <div class="input-box">
            <b-row>
              <b-col cols="8">
                <input id="emailCode" type="text" name="emailCode" v-model="emailCode" placeholder="인증번호" required/>
                <label for="emailCode" style="left: 15px;">인증번호</label>
              </b-col>
              <b-col cols="4" class="pl-0"><b-button class="mt-3" @click="emailAuthenticate" id="emailBtn">확인</b-button></b-col>
            </b-row>
            <div class="mt-1 message" :class="{ completeCheck: emailAuth }">{{ emailAuthMsg }}</div>
          </div>
        </div>
        <div class="mt-1 message">{{ resultMsg }}</div>
        <b-button id="signupBtn" type="submit" @keydown.enter="signUp" @click="signUp">SIGNUP</b-button>
      </b-form> 
    </div>
  </div>
</template>

<script>
// import http from 'http'
import http from "@/util/http-common.js";

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
      idRule: false,
      idDuplicate: false,
      pwdRule: false,
      pwdDouble: false,
      toggleIdCheck: false,
      passwordRule: '',
      email: false,   // 이메일 전송 여부
      emailRule: false,
      emailDuplicate: false,    
      emailAuth: false,
      emailAuthCode: '',
      emailCode: '',
      inputDisabled: false,  // 이메일 입력 disabled 여부
      
      // 데이터 오입력 출력 메시지
      idMsg: '',
      pwdMsg1: '',
      pwdMsg2: '',
      emailMsg: '',
      emailAuthMsg: '',
      resultMsg: '',

    }
  },

  methods: {
    signUp() {
      console.log('아이디 유효성 검사여부 : ', this.idRule, ' && 아이디 중복 검사 : ', this.idDuplicate, 
      '&& 비밀번호 유효성 검사 여부 : ', this.pwdRule, ' && 비밀번호 확인 : ', this.pwdDouble,
      '&& 이메일 유효성 검사여부 : ', this.emailRule, '&& 이메일 중복 검사여부 : '
      , this.emailDuplicate, ' && 이메일 인증여부 : ', this.emailAuth);
      
      if(this.idRule && this.idDuplicate && this.pwdRule && this.pwdDouble && this.emailRule && this.emailDuplicate && this.emailAuth) {
        this.$store.dispatch('signUp',this.credentials)
      } else {
        if(!this.idDuplicate) {
          this.resultMsg = '아이디 중복 검사를 해주세요.';
        } else if(!this.emailAuth) {
          this.resultMsg = '이메일 인증을 해주세요.';
        }
      }
    },
    
    idDuplicateCheck() {
      http({
        method: 'GET',
        url: `/user/idcheck/${this.credentials.userId}`,
      })
      .then(res => {
        console.log(res);
        this.toggleIdCheck = true
        this.idDuplicate = true;
        this.idMsg = '사용가능한 아이디입니다.';
        if(this.resultMsg == '아이디 중복 검사를 해주세요.') this.resultMsg = '';
      })
      .catch(err => {
        this.idMsg = '사용할 수 없는 아이디입니다.';
        this.idDuplicate = false;
        console.log(err)
      })
    },

    idRuleCheck() {
      var rule = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,12}$/;

      if(!rule.test(this.credentials.userId)) {
        this.idMsg = '아이디는 4 ~ 12 자리수이며 문자, 숫자를 최소 1개씩 포함해야합니다.';
        this.idRule = false;
      } else {
        this.idMsg = '';
        this.idRule = true;
      }
    },

    resetidDuplicate() {
      this.idDuplicate = false;
    },

    resetpwdDouble() {
      if(this.pwdDouble == true) this.pwdMsg2 = '비밀번호가 일치하지 않습니다.';
      else this.pwdMsg2 = '';
      this.pwdDouble = false;
    },

    passwordRuleCheck() {
      var rule = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/;

      if(!rule.test(this.credentials.userPassword)) {
        this.pwdMsg1 = '비밀번호는 8 ~ 12 자리수이며 문자, 숫자, 특수기호를 최소 1개씩 포함해야합니다.';
        this.pwdRule = false;
      } else {
        this.pwdMsg1 = '';
        this.pwdRule = true;
      }
    },

    doublePasswordCheck() {
      if(this.credentials.userPassword == this.passwordRule) {
        this.pwdMsg2 = '';
        this.pwdDouble = true;
      } else {
        this.pwdMsg2 = '비밀번호가 일치하지 않습니다.';
        this.pwdDouble = false;
      }
    },

    // 이메일 중복 검사
    emailDuplicateCheck() {
      http({
        method: 'GET',
        url: `/email/emailcheck/${this.credentials.userEmail}`,
      })
      .then(res => {
        console.log(res.data)
        this.emailDuplicate = true; 
        this.emailMsg = ''
      })
      .catch(err => {
        this.emailMsg = '사용할 수 없는 이메일 입니다.'
        console.log(err)
      })

    },

    // 이메일 유효성 검사
    emailRuleCheck() {
      var rule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      
      if(!rule.test(this.credentials.userEmail)) {
        this.emailRule = false;
        this.emailMsg = '유효하지 않은 이메일 형식입니다.';
      } else {
        this.emailRule = true;
        this.emailMsg = '';
      }
    },

    // 이메일 전송
    sendEmail() {
      this.emailRuleCheck();
      this.emailDuplicateCheck();

      console.log(">>>>>>>>>>>>>>>> here/")

      if(this.emailDuplicate == true && this.emailRule == true) {
        this.email = true;
        this.inputDisabled = true;

        http({
          method: 'GET',
          url: `/email/send/${this.credentials.userEmail}`,
        })
        .then(res => {
          console.log(res);
          this.emailAuthCode = res.data;
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
    // emailAuthCode
    // 이메일 인증 코드 확인
    emailAuthenticate() {
      if(this.emailAuthCode == this.emailCode) {
        this.emailAuth = true;
        this.inputDisabled = true;
        this.emailAuthMsg = '인증 완료';
      } else {
        this.emailAuthMsg = '인증코드가 틀렸습니다.';
      }
    },
  },
}
</script>

<style scoped>
p {
  font-size: 40px;
  color: #fff;
}

#signupPage {
  /* padding: 100px 0; */
  height: 120%;
  position: relative;
  background-color: #DAC7F9;
}

#signupBox {
  height: 90%;
  width: 600px;
  background-color: rgb(255, 255, 255);
  box-shadow: 10px 10px 10px rgb(235, 235, 235);
  border-radius: 10px;
  position:absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}

#signupForm {
  width: 480px;
  height: auto;
  position:absolute;
  top: 45%;
  left: 50%;
  transform: translate(-50%,-50%);
}

#signup {
  color: #3d3d3d;
  margin-top: 70px;
}

#signupBtn {
  width: 100%;
  height: 50px;
  font-size: 20pt;
  background-color: #3d3d3d;
  margin-top: 30px;
}

#emailBtn, #idCheckBtn {
  font-size: 13pt;
  width: 100px;
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
  font-size:15pt;
  width:100%;
  color: #302f2f;
}

input::placeholder{ 
  color:transparent;
}

input:placeholder-shown + label{
  color:#ccc; 
  font-size:15pt;
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

/* 중복 확인 성공 안내 메시지 */
.completeCheck {
  color: #3C77C9;
}

</style>