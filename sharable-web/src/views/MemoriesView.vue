<template>
  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center">
      <div class="card">
        <div class="card-header">
          <h3>{{query}}</h3>
        </div>
        <div class="card-body">
          {{answer}}
        </div>
        <div class="card-footer ">
          <div class="d-flex justify-content-between">
            <button type="button" class="btn btn-outline-success" style="border-radius: 50%;">
              <i class="bi bi-balloon-heart-fill" style="font-size: 2.5em"> 32</i>
            </button>
            <button type="button" class="btn btn-outline-info" style="border-radius: 50%;">
              <i class="bi bi-cup-hot-fill" style="font-size: 2.5em">21</i>
            </button>
            <button type="button" class="btn btn-outline-danger" style="border-radius: 50%;">
              <i class="bi bi-emoji-frown" style="font-size: 2.5em">23</i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script lang="ts" setup>
import axios from "axios";
import {onMounted, ref} from "vue";

let answer = ref();
let query = ref();

onMounted(async () => {
  const { data } = await axios.get("/app/memory",);
  console.log("data: " + JSON.stringify(data));
  answer.value = data.answer;
  query.value = data.questions;
});
</script>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
