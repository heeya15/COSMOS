<template>
  <tr>
    <td>{{ idx }}</td>
    <td><p v-if="boardItem.contentStatus === false" class="boardnum_tag">진행중</p>
    <p v-else class="boardnum_tag">완료</p></td>
    <td><span v-if="boardItem.header === false" class="boardnum_tag">[스터디원 구함]</span>
    <span v-else class="boardnum_tag">[스터디 구함]</span></td>
    <td><p class="boardnum_tag">{{ boardItem.contentTitle }}</p></td>
    <td><p class="boardnum_tag">{{ boardItem.studytypeName }}</p></td>
    <td><p class="boardnum_tag">{{ boardItem.user.userName }}</p></td>
    <td><p class="boardnum_tag">{{ makeDate(boardItem.createdAt) }}</p></td>
    </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  props: {
    boardItem: {
      Object,
    }
  },
  methods: {
    makeDate(datetime) {
      const old = ''+datetime
      return old.substring(0, 10)
    },
  }
}
</script>

<style>

</style>