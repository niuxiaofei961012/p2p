<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import HeadNav from '@/components/common/HeadNav'
import Footer from  '@/components/common/footer'
import Head from  '@/components/common/Head'
export default {
  name: 'App',
  components:{
    HeadNav,
    Footer,
    Head
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
