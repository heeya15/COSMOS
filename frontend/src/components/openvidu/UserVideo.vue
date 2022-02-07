<template>
<div v-if="streamManager">
	<div class="userVideo col-md-3">
		<ov-video :stream-manager="streamManager"/>
		<div><p class="video_clientName">{{ clientData }}</p></div>
		<div><p class="video_stopwatch">00:00:00</p></div>
		<div class="selectDisplay d-flex justify-content-center">
			<p>사용자 캠 보기</p>
			<p>사용자 화면 보기</p>
		</div>
	</div>
</div>
</template>
<style scope>
.userVideo{
	position: relative;
	/* width: 30%; */
	/* height: 65%; */
	height: 180px;
	/* background-color: black; */
	padding: 0;
	margin: 0 10px 70px;
}

.video_stopwatch{
position: absolute;
top: 0px;
right: 0px;
display: inline-block;
background: #111;
padding-left: 5px;
padding-right: 5px;
color: #fff;
font-weight: bold;
border-bottom-right-radius: 4px;
}

.video_clientName{
position: absolute;
top: 0px;
left: 0px;
display: inline-block;
background: #f8f8f8;
padding-left: 5px;
padding-right: 5px;
color: #777777;
font-weight: bold;
border-bottom-right-radius: 4px;
}

.selectDisplay{
width: 100%;
margin-top: 5px;
/* position: absolute; */
/* bottom: 0px; */
}

.selectDisplay p{
	margin: 0 5px;
}

</style>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

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
