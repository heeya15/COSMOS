<template>
  <div>
    <CommentForm/>
    <CommentItem v-for="comment in comments" :key="comment.idx" :comment="comment"/>
    <!-- <button @click="getComment">확인</button> -->
  </div>
</template>

<script>
import CommentForm from '@/components/community/CommentForm.vue'
import CommentItem from '@/components/community/CommentItem.vue'
import { mapState } from 'vuex'

export default {
  name: 'Comment',
  components: {
    CommentForm,
    CommentItem,
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