<script lang="ts">
import axios from "axios";
import {defineComponent} from "vue";

export default defineComponent({
  data() {
    return {
      answer: String,
      counter: 240,
      question: {},
      questions: []
    }
  },
  methods: {
    save() {
      let params = {
        'question' : this.question,
        'answer' : this.answer,
        'time': Date.now(),
      }
      axios.post("/app/memory/", params).then(
          response => {
            console.log("response: " + JSON.stringify(response));
          }
      )
    }
  },
  mounted() {
    axios.get("/app/question/",).then(
        response => {
          console.log("data: " + JSON.stringify(response.data));
          this.question = response.data;
        }
    )
  }
})

</script>

<template>
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
            <button type="submit" class="btn btn-primary" @click="save()">Submit</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
