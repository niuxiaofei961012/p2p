<template>
  <div>
    <Head></Head>
    <div>
      <router-view></router-view>
    </div>
  </div>

</template>

<script>
  import Head from "../common/Head";

  export default {
    name: "Home",
    components: {Head}
  }
</script>

<style scoped>

</style>
