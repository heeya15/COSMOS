<template>
  <div>
    <CommentForm/>
    <CommentItem v-for="(comment, idx) in comments" :key="idx" :comment="comment"/>
    <!-- <button @click="getComment">확인</button> -->
    <test/>
  </div>
</template>

<script>
import CommentForm from '@/components/community/CommentForm.vue'
import CommentItem from '@/components/community/CommentItem.vue'
import { mapState } from 'vuex'

import test from '@/components/community/test.vue'

export default {
  name: 'Comment',
  components: {
    CommentForm,
    CommentItem,
    test,
  },
  data() {
    return {
      board_no: this.$store.state.boardNo,
    }
  },
  methods: {
    getComment() {
      this.$store.dispatch('getComment')
      // this.$router.go()
      console.log('댓글 가져오기')
    },
  },
  created() {
    this.getComment()
  },
  computed: {
    ...mapState([
      'comments',
    ]),
  },
  
}
</script>

<style>

</style>