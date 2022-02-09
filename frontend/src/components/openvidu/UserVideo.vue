<template>
<div v-if="streamManager">
	<!-- <div class="userVideo_info col-md-3"> -->
	<div class="userVideo_info">
		<div class="userVideo">
			<ov-video :stream-manager="streamManager"/>
		</div>
		<div><p class="video_clientName">{{ clientData }}</p></div>
		<div><p class="video_stopwatch">00:00:00</p></div>
		
		<div class="selectDisplay d-flex justify-content-center">
			<p class="d-flex justify-content-center" style="width:50%">캠 보기</p>
			<p class="d-flex justify-content-center" style="width:50%">화면 보기</p>
		</div>
	</div>
</div>
</template>
<script>
import "@/assets/style/PrivateStudyRoom/video.css"
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
