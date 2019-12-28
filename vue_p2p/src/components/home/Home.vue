<template>
  <div>
    <Head></Head>
    <HeadNav></HeadNav>

    <div>
      <router-view></router-view>
    </div>
    <Footer></Footer>
  </div>

</template>

<script>
  import Head from "../common/Head";
  import HeadNav from "../common/HeadNav";
  import Footer from "../common/footer";

  export default {
    name: "Home",
    components: {Head, HeadNav,Footer}
  }
</script>

<style scoped>

</style>
