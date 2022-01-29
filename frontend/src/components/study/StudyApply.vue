<template>
  <div class="apply" style="width:1000px;">
    <h3>가입 요청 회원</h3>


  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'StudyApply',
  data(){
    return {

    }
  },
  methods: {
    showApplyMember() {
      axios({
        method: 'GET',
        // url: `http://i6e103.p.ssafy.io:8080/api/study/applyMemeber/register/${studyno}`
        url: `http://i6e103.p.ssafy.io:8080/api/study/applyMemeber/register/1`
      })
      .then(res =>{
        console.log(res.data)
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
}
</script>

<style>

</style>