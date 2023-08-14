<template>
  <div id="progress-bar">
    <div class="shell">
      <div class="bar" :style="{ width: progress + '%' }">
        <span>{{ progress > 100 ? 100 : progress }}%</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'progress-bar',
  data() {
    return {
      progress: Math.floor((this.$store.state.userData.numberOfWorkouts / this.$store.state.user.days).toFixed(2)*100)
    };
  },
};
</script>

<style scoped>
#progress-bar {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.shell {
  height: 50px;
  width: 500px;
  border: 5px solid darkblue;
  border-radius: 15px;
  padding: 5px
}

.bar {
  background: linear-gradient(to left, #1926ef, #19c2ff);
  height: 50px;
  width: 15px;
  max-width: 480px;
  border-radius: 10px;
}

.bar > span {
    float: right;
    padding: 10px 10px;
    color: #fff;
    font-size: 30px;
  }

</style>