<template>
  <center>
      <b-collapse id="collapse-2" visible>
        <div class="container">
        <hr>
          <div class="reply-list-area">
            <div>
              <div class="comment-body">
                <div class="status float-base">
                  <div class="column user-info">
                    <span class="name">{{ comment.user_id }}</span>
                  </div>
                  <div class="column date">
                    <span>{{ comment.created_at }}</span>
                  </div>
                  <hr class="hrTag">
                </div>
                <div class="reply-content">
                  <b-form-textarea v-if="editButton === true" type="text" class=" d-block" style="text-align: left; font-size: 20px; height: 90px;" v-model="comment.content" @keyup.enter="updateComment"></b-form-textarea>
                    <span v-else class=" d-block" style="text-align: left;"> {{ comment.content }}</span>
                </div>
                <div class="button-tag" v-show="editButton === false">
                  <b-button variant="warning" class="button_tag" v-if="userId === loginUserId" @click="editButtonChange"><b-icon icon="pencil"></b-icon></b-button>
                  <b-button variant="danger" class="button_tag" v-if="userId === loginUserId" @click="deleteComment"><b-icon icon="trash"></b-icon></b-button>
                </div>
                <div v-show="editButton === true" class="button-tag">
                  <b-button variant="warning" class="button_tag" v-if="userId === loginUserId" @click="updateComment"><b-icon icon="pencil-square"></b-icon></b-button>
                  <b-button variant="danger" class="button_tag" v-if="userId === loginUserId" @click="deleteComment"><b-icon icon="trash-fill"></b-icon></b-button>
                </div>
              </div>
            </div>
          </div>
        <hr>
        </div>

      </b-collapse>
  </center>
</template>

<script>
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
    },
    deleteComment() {
      const commentIdx = this.$store.state.comments.indexOf(this.comment)
      this.$store.state.comments.splice(commentIdx, 1)
      http({
        method: 'delete',
        url: `/comment/remove/${this.comment_no}`,
        headers: this.getToken()
      })
      .then(() => {
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
      .then(() => {
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
        this.loginUserId=res.data['user_id']
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
/* .container {
  width: 50%;
} */

.card {
  display: flex;
  flex-wrap: wrap;
  /* background: linear-gradient(to left yellow 20%, #fcfc87 80%); */
  background: #fff;
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
  background: #fff;
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

.button-tag {
  display: flex;
  justify-content: right;
}
.button_tag {
  font-size: 10px;
  border: none;
  margin-left: 2px;
  margin-right: 2px;
}

.comment-widgets .comment-row:hover {
  background: rgba(0, 0, 0, 0.05)
}

.container {
  width: 100%;
  /* border: 1px dotted; */
  background-color: #fafafa;
}

.user-info {
  display: flex;
  text-align: left;
  margin-left: 3%;
  font-size: 13px;
}

.date {
  display: flex;
  justify-content: left;
  text-align: left;
  margin-left: 3%;
  font-size: 10px;
}

.reply-content {
  display: flex;
  justify-content: left;
  text-align: left;
  margin-left: 3%;
  margin-top: 3px;
  font-size: 20px;
}

.reply-content {
  height: 100px;
}

</style>