<template>
  <div>
    <CommentForm/>
    <div class="commentBg">
      <CommentItem v-for="(comment, idx) in comments" :key="idx" :comment="comment"/>
    </div>
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