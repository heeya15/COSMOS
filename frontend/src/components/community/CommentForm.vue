<template>
  <div>
    <center>
      <b-row style="width: 800px;">
        <b-col cols="3" class="mt-3">
          <p>댓글</p>
        </b-col>
        <b-col cols="6" class="mt-3">
          <b-form-input v-model="commentInput.content" @keyup.enter="createComment" autocomplete="off"></b-form-input>
        </b-col>
        <b-col cols="3" class="mt-3">
          <b-button class="btnTag" @click="createComment">작성</b-button>
          <b-button v-if="visible === true" v-b-toggle.collapse-2 class="getCommetTag m-1" @click="visible = !visible">댓글 접기</b-button>
          <b-button v-else v-b-toggle.collapse-2 class="getCommetTag m-1" @click="visible = !visible">댓글 보기</b-button>
        </b-col>
      </b-row>
    </center>
  </div>
</template>

<script>
// import http from 'http'
import http from "@/util/http-common.js";

export default {
  name: 'CommentForm',
  data() {
    return {
      visible: true,
      commentInput: {
        content: null,
        user_id: null,
      },
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
    createComment() {
      const createCommentItem = {
        board_no: this.$store.state.boardNo,
        content: this.commentInput.content
      }
      http({
        method: 'post',
        url: '/comment/register',
        data: createCommentItem,
        headers: this.getToken()

      })
      .then(res => {
        console.log('댓글부분')
        console.log(res)
        console.log('댓글')
        // 작성하고 바로 뜨게 새로고침
        this.$router.go(this.$router.currentRoute)
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
}
</script>

<style scoped>
.btnTag {
  background-color: #afa2dd;
  border: none;
}

.btnTag:hover {
  background-color: #c8c1e4;
}

.getCommetTag {
  width: 100px;
  height: 36px;
  background-color: #f45384 !important;
  border: none;
}

.getCommetTag:hover {
  background-color: #f8bfd1 !important;
}


</style>