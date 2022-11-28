<template>
  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center m-4">
      <div class="d-flex justify-content-center">
        <button type="button" class="btn btn-success" @click="getOther()">
          <i class="bi bi-arrow-clockwise"> Other Memory</i>
        </button>
      </div>
    </div>
  </div>
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

<script lang="ts" >
import axios from "axios";
import {defineComponent} from "vue";

export default defineComponent({
  data() {
    return {
      answer: '',
      query: {},
    }
  },
  methods: {
    getMemory(id: string){
      axios.get("/app/memory/"+id,).then(
          response => {
            this.answer = response.data.answer;
            this.query = response.data.question.question;
          }
      )
    },
    getOther(){
      this.getMemory('');
    },
    save() {
      // let params = {
      //   'question' : this.question,
      //   'answer' : this.answer,
      //   'time': Date.now(),
      // }
      // axios.post("/app/memory/", params).then(
      //     response => {
      //       console.log("response: " + JSON.stringify(response));
      //     }
      // )
    }
  },
  mounted() {
    let id: string = '';
    if(this.$route.params.id){
      id = this.$route.params.id.toString();
    }
    this.getMemory(id);
  }
})

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
