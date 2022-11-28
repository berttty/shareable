<script lang="ts">
import { defineComponent } from 'vue'
import { RouterLink, RouterView } from "vue-router";
import {getRandomInt} from './utils/random'


export default defineComponent({
  name: 'Home',
  mounted() {
    let colors: string[] = [
      // 'yellow',
      // 'green',
      // 'blue',
      // 'purple',
      // 'red',
      // 'orange'
        'white'
    ];
    document.body.classList.add(colors[getRandomInt(colors.length)]);
  },
});
</script>

<template>

  <nav class="navbar navbar-expand-lg sticky-top navbar-light bg-light">
    <div class="container-lg d-flex">
      <div class="flex-fill justify-content-center p-0">
        <ul class="nav nav-tabs justify-content-center">
          <li class="nav-item">
            <RouterLink class="nav-link" active-link="active" to="/answer">I want to Answer</RouterLink>
          </li>
          <li class="nav-item">
            <RouterLink class="nav-link" active-link="active" to="/memories">Memories</RouterLink>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="container-md" style="">

    <RouterView />

  </div>

</template>

<style scoped>
/*header {*/
/*  line-height: 1.5;*/
/*  max-height: 100vh;*/
/*}*/

/*.logo {*/
/*  display: block;*/
/*  margin: 0 auto 2rem;*/
/*}*/

/*nav {*/
/*  width: 100%;*/
/*  font-size: 12px;*/
/*  text-align: center;*/
/*  margin-top: 2rem;*/
/*}*/

/*nav a.router-link-exact-active {*/
/*  color: var(--color-text);*/
/*}*/

/*nav a.router-link-exact-active:hover {*/
/*  background-color: transparent;*/
/*}*/

/*nav a {*/
/*  display: inline-block;*/
/*  padding: 0 1rem;*/
/*  border-left: 1px solid var(--color-border);*/
/*}*/

/*nav a:first-of-type {*/
/*  border: 0;*/
/*}*/

/*@media (min-width: 1024px) {*/
/*  header {*/
/*    display: flex;*/
/*    place-items: center;*/
/*    padding-right: calc(var(--section-gap) / 2);*/
/*  }*/

/*  .logo {*/
/*    margin: 0 2rem 0 0;*/
/*  }*/

/*  header .wrapper {*/
/*    display: flex;*/
/*    place-items: flex-start;*/
/*    flex-wrap: wrap;*/
/*  }*/

/*  nav {*/
/*    text-align: left;*/
/*    margin-left: -1rem;*/
/*    font-size: 1rem;*/

/*    padding: 1rem 0;*/
/*    margin-top: 1rem;*/
/*  }*/
/*}*/
</style>
