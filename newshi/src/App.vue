<template>
  <v-app
  >
    <nav-bar></nav-bar>
    <br /><br /><br />
    <router-view></router-view>
  </v-app>
</template>

<script>
import NavBar from './components/NavBar.vue';

export default {
  components: { NavBar },
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&family=Noto+Serif+KR:wght@700&display=swap');

html,
body {
  font-family: Spectral, 'Noto Serif KR', serif;
  font-weight: 900;
}

#app {
  font-family: Spectral, 'Noto Serif KR', serif;
}
</style>
