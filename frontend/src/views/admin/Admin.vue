<template>
    <div class="container">
        <h1 class="text-center mb-5">관리자 페이지</h1>
        <div class="subMenu container" align="center">
            <span class="px-3 text-center" @click="toggleusermng" :class="{ selected: select }">회원 관리</span>
            <span class="px-3 text-center" @click="togglestudytypemng" :class="{ selected: !select }">스터디 분류 관리</span>
        </div>
        <hr>
            <UserManage v-show="toggleUserMng"/>
            <StudyTypeManage v-show="toggleStudyTypeMng"/>
    </div>  
</template>

<script>
import StudyTypeManage from '@/components/admin/StudyTypeManage.vue'
import UserManage from '@/components/admin/UserManage.vue'

export default {
    name: 'Admin',

    components: {
        StudyTypeManage,
        UserManage,
    },

    data() {
        return {
            toggleUserMng: true,
            toggleStudyTypeMng: false,
            select: true,
        }
    },
    methods: {
        toggleusermng() {
            this.toggleUserMng = true;
            this.toggleStudyTypeMng = false;
            this.select = !this.select
        }, 

        togglestudytypemng() {
            this.toggleUserMng = false;
            this.toggleStudyTypeMng = true;
            this.select = !this.select
        },
    }, 
}
</script>

<style scoped>
.subMenu {
    font-size: 180%;
    cursor: pointer;
}

#container {
    display: flex;
    justify-content: space-evenly;
}

.selected {
    color: #9255AD;
}

</style>