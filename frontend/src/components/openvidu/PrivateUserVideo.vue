<template>
<div v-if="streamManager">
	<div class="userVideo_info">
		<div class="userVideo">
			<ov-video :stream-manager="streamManager"/>
		</div>
		<div><p class="video_clientName">{{ clientData }}</p></div>
	</div>
</div>
</template>
<script>
import "@/assets/style/StudyRoom/video.css"
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	data() {
		return {
			count: 0,
			intervalId: null
		};
	},

	components: {
		OvVideo,
	},

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
			return JSON.parse(connection.data);
		},
		
	},

	
};
</script>
