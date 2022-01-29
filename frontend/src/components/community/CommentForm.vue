<template>
  <div>
    <h3>댓글작성(폼)</h3>
    <center>
      <b-row style="width: 600px;">
        <b-col cols="3" class="mt-3">
          <p>댓글</p>
        </b-col>
        <b-col cols="6" class="mt-3">
          <b-input v-model="commentInput.content"></b-input>
        </b-col>
        <b-col cols="3" class="mt-3">
          <b-button @click="createComment">작성</b-button>
        </b-col>
      </b-row>
    </center>
  </div>
</template>

<script>
export default {
  name: 'CommentForm',
  data() {
    return {
      commentInput: {
        content: null,
        user_id: null,
      },
    }
  },
  methods: {
    createComment() {
      this.$store.dispatch('createComment', this.commentInput)
    },
  },
}
</script>

<style>

</style>