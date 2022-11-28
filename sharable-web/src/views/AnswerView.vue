<script lang="ts">
import axios from "axios";
import {defineComponent} from "vue";
import { useRoute } from 'vue-router';
import router from '@/router';



export default defineComponent({
  data() {
    return {
      answer: '',
      max: 240,
      counter: 240,
      question: {},
      questions: []
    }
  },
  methods: {
    getQuestion(){
      axios.get("/app/question/",).then(
          response => {
            console.log("data: " + JSON.stringify(response.data));
            this.question = response.data;
          }
      )
    },
    save() {
      let params = {
        'question' : this.question,
        'answer' : this.answer,
        'time': Date.now(),
      }
      axios.post("/app/memory/", params).then(
          response => {
            console.log("response: " + JSON.stringify(response));
            router.push("/memories/"+response.data.id);
          }
      )
    }
  },
  mounted() {
    const self = this
    window.addEventListener("keydown", function(e) {
      self.counter = self.max - self.answer.length;
    });

    this.getQuestion();
  }
})

</script>

<template>
  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center m-4">
      <div class="d-flex justify-content-center">
        <button type="button" class="btn btn-success" @click="getQuestion()">
          <i class="bi bi-arrow-clockwise"> Other Question</i>
        </button>
      </div>
    </div>
  </div>
  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center">
      <div class="p-2">
        <h1>{{question['question']}}</h1>
        <form class="mt-2">
          <div class="form-check ps-0">

            <label for="question" class="form-label">Write your mind you have {{ counter }} characters</label>
            <textarea class="form-control" id="question" v-model="answer" rows="10" placeholder="Please answer the question" required></textarea>

          </div>
          <div class="d-grid gap-2 pt-2">
            <a class="btn btn-primary" @click="save()">Submit</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
