<template>
  <center>
    <!-- <div class="comment_position">
      <p>{{ comment.comment_no }}</p>
      <b-form-input v-if="editButton === true" type="text" v-model="comment.content" @keyup.enter="updateComment"></b-form-input>
      <p v-else style="text-align: left;">내용 : {{ comment.content }}</p>
      <p>작성자 : {{ comment.user_id }}</p>
      <p>작성 시간 : {{ makeDate(comment.created_at) }}</p>
      <p>인덱스 확인 >> {{ idx }} // 뜨나?</p> -->

      <!-- <div class="totalCard row mt-20 mb-20"> -->
      <b-collapse id="collapse-2">
      <div class="container mt-3">
        <div class="comment_body">
            <div class="card">
              <div class="comment-widgets">
                  <!-- Comment Row -->
                <div class="d-flex flex-row comment-row">
                  <div class="comment-text w-100">
                    <div style="display: flex; flex-wrap: wrap; justify-content: space-between;">
                      <b-form-input v-if="editButton === true" type="text" class=" d-block" style="text-align: left; font-size: 13px; height: 30px;" v-model="comment.content" @keyup.enter="updateComment"></b-form-input>
                      <span v-else class=" d-block" style="text-align: left; font-size: 13px;"> {{ comment.content }}</span>
                      <p class="font-medium" style="font-size: 10px; text-align: right;">작성자 : {{ comment.user_id }}</p>
                    </div>
                    <div class="comment-footer"> 
                    <span class="text-muted float-right" style="font-size: 10px"> {{ makeDate(comment.created_at) }}</span>
                      <div v-show="editButton === false" style="display: flex; justify-content: left;">
                        <b-button variant="warning" class="button_tag" size="sm" v-if="userId === loginUserId" @click="editButtonChange">수정</b-button>
                        <b-button variant="danger" class="button_tag" size="sm" v-if="userId === loginUserId" @click="deleteComment">삭제</b-button>
                      </div>
                      <div v-show="editButton === true" >
                        <b-button variant="warning" class="button_tag" size="sm" v-if="userId === loginUserId" @click="updateComment">수정</b-button>
                        <b-button variant="danger" class="button_tag" size="sm" v-if="userId === loginUserId" @click="deleteComment">삭제</b-button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
      </div>
      </b-collapse>

      <!-- <div v-show="editButton === false">
        <b-button v-if="userId === loginUserId" @click="editButtonChange" style="background-color: #DAC7F9">수정</b-button>
        <b-button v-if="userId === loginUserId" @click="deleteComment" style="background-color: #DAC7F9">삭제</b-button>
      </div>
      <div v-show="editButton === true">
        <b-button v-if="userId === loginUserId" @click="updateComment" style="background-color: #DAC7F9">수정</b-button>
        <b-button v-if="userId === loginUserId" @click="deleteComment" style="background-color: #DAC7F9">삭제</b-button>
      </div> -->

    <!-- </div> -->
  </center>
</template>

<script>
// import http from 'http'
import http from "@/util/http-common.js";
import {mapState} from 'vuex'

export default {
  name: 'CommentItem',
  props: {
    comment: {
      type: Object,
    }
  },
  data() {
    return {
      editButton: false,
      comment_no: this.comment.comment_no,
      userId: this.comment.user_id,
      loginUserId: null,
      created_at: null,
      idx: null,
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
    getComment() {
      this.$store.dispatch('getComment')
      // this.$router.go()
      console.log('댓글 가져오기')
    },
    deleteComment() {
      const commentIdx = this.$store.state.comments.indexOf(this.comment)
      this.$store.state.comments.splice(commentIdx, 1)
      http({
        method: 'delete',
        url: `/comment/remove/${this.comment_no}`,
        headers: this.getToken()
      })
      .then((res) => {
        console.log(res)
        this.comment_no = this.comment.comment_no
      })
      .catch(err => {
        console.log(err)
      })
    },
    editButtonChange() {
      this.editButton = true
    },
    updateComment() {
      const updateCommentItem = {
        board_no: this.comment.boardNo,
        comment_no: this.comment.comment_no,
        content: this.comment.content,
      }
      http({
        method: 'put',
        url: '/comment/update',
        data: updateCommentItem,
        headers: this.getToken(),
      })
      .then(res => {
        console.log('수정부분')
        console.log(res.data)
        this.editButton = false
        this.$router.go(this.$router.currentRoute)
      })
      .catch(err => {
        console.log(err)
      })
    },
    getUserInfo(){
      http({
        method: 'GET',
        url: '/user/me',
        headers: this.getToken()
      })
      .then(res =>{
        // console.log(res.data)
        this.loginUserId=res.data['user_id']
        // console.log('유저 확인')
        // console.log(this.loginUserId)
      })
      .catch(err =>{
        console.log(err)
      })
    },
    makeDate(datetime) {
      const old = ''+datetime
      return old.substring(0, 10)
    },
  },
  created() {
    this.getUserInfo()
    this.getComment()
  },
  computed: {
    ...mapState([
      'comments',
    ]),
  },
}

</script>

<style scoped>
/* .comment_position {
  display: flex;
  flex-wrap: wrap;
  justify-content: left;
  justify-content: space-evenly;
} */



.container {
  width: 700px;
}

/* .cardHead {
  position: relative;
  top: 100px;
  right: 260px;
  background-color: yellow;
  width: 70px;
  height: 100px;
} */

.card {
  display: flex;
  flex-wrap: wrap;
  /* background: linear-gradient(to left yellow 20%, #fcfc87 80%); */
  background: linear-gradient(to left, #fcfc87 25%, #fcfc87 25% 50%, #fcfc87 50% 75%, yellow 90% );
  /* background: linear-gradient(to left, #c8c1e4 25%, #c8c1e4 25% 50%, #c8c1e4 50% 75%, #afa2dd 90% ); */
  /* background-color: #fff; */
  border: none;
}

.totalCard {
  display: flex;
  /* justify-content: space-around; */
  flex-wrap: wrap;
}

/* 테스트 부분 */
.comment_body {
  /* border: 5px solid transparent; */
  background: linear-gradient(to left, #fcfc87 25%, #fcfc87 25% 50%, #fcfc87 50% 75%, yellow 90% );
  /* background: linear-gradient(to left, #c8c1e4 25%, #c8c1e4 25% 50%, #c8c1e4 50% 75%, #afa2dd 90% ); */
  /* background: repeating-linear-gradient(-45deg, #f45384, #f45384 5px, #f8bfd1 5px, #f8bfd1 10px); */
  /* background-color: #afa2dd; */
  box-shadow: 5px 6px 6px 2px #dcdcdd;
  width: 70%;
  height: 100px;
  border-radius: 4px;
  /* word-wrap: break-word; */
}

.comment-widgets {
  position: relative;
  margin-bottom: 10px
}

.comment-widgets .comment-row {
  border-bottom: 1px solid transparent;
  padding: 14px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  /* margin: 10px 0 */
}

.p-2 {
  padding: 0.5rem !important
}

.comment-text {
  padding-left: 15px
}

.w-100 {
  width: 100% !important
}

.m-b-15 {
  margin-bottom: 15px
}

.button_tag {
  font-size: 10px;
  border: none;
}


.comment-widgets .comment-row:hover {
  background: rgba(0, 0, 0, 0.05)
}

</style>