<template>
  <div>
    <Head></Head>
    <HeadNav></HeadNav>
    <div>
      <router-view></router-view>
    </div>
    <h1>前台首页</h1>
  </div>

</template>

<script>
  import Head from "../common/Head";
  import HeadNav from "../common/HeadNav";

  export default {
    name: "Home",
    components: {Head, HeadNav}
  }
</script>

<style scoped>

</style>
