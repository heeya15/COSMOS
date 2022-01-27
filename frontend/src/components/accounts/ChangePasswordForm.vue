<template>
  <center>
    <h1>비밀번호변경페이지</h1>
    <div style="width: 600px;">
      <b-form-input class="mt-3" style="height:50px;" id="id" v-model="userpw" required placeholder="현재 비밀번호를 입력하세요."></b-form-input>
      <b-form-input class="mt-3" style="height:50px;" id="id" v-model="userpw1" required placeholder="변경할 비밀번호를 입력하세요."></b-form-input>
      <b-form-input class="mt-3" style="height:50px;" id="id" v-model="userpw2" required placeholder="변경할 비밀번호 확인"></b-form-input>
      <b-button variant="primary" class="mt-5">변경</b-button>
      <b-button class="ms-3 mt-5">취소</b-button>
    </div>
  </center>
</template>

<script>
  export default {
    name: 'ChangePasswordForm',
    data(){
      return{
        userpw : '',
        userpw1 : '',
        userpw2 : '',
        newpw : '',
        token : localStorage.getItem('jwt'),
      }
    },
    methods : {
      
    }
  }
</script>

<style scoped>

</style>