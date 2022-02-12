<template>
<div v-if="streamManager">
	<div class="list">
		<div class="list_clientName" v-if="this.streamManager.stream.connection.role=='PUBLISHER'">
			<p class="publisher_id">{{ clientData }}</p>
			<p>(나)</p>
		</div>
		<div class="list_clientName" v-else>
			<p>{{ clientData }}</p>
		</div>
	</div>
</div>
</template>
<script>
import "@/assets/style/PrivateStudyRoom/userList.css"

export default {
	name: 'UserList',

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			console.log("😀😀😀😀")
			console.log(this.streamManager.stream.connection.role);
			return JSON.parse(connection.data);
		},
	},
};
</script>
