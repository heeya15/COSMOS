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

<style scope>

.list_clientName{
	/* display: inline-block; */
	/* width: 1001px; */
	font-size: 16px;
}

.list_clientName p{
	display: inline-block;
}

.publisher_id{
	margin-right: 5px;
}

</style>

<script>

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
