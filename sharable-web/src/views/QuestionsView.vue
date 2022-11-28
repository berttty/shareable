<template>
  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center">
      <div class="card">
        <div class="card-header">
          <h3>Create new QR</h3>
        </div>
        <form>
          <div class="card-body">
            <div class="mb-3">
              <label for="question" class="form-label">Intervention question</label>
              <input type="text" class="form-control" id="question" v-model="question" placeholder="Please add the intervention question" required />
            </div>

          </div>
          <div class="card-footer ">
            <div class="d-flex justify-content-center">
              <button type="button" class="btn btn-success" @click="save()">
                <i class="bi bi-qr-code"> Generate</i>
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>

  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center">
      <div class="card">
        <div class="card-header">
          <h3>QR Created in the pass</h3>
        </div>
        <div class="card-body">
          <table  class="table table-striped">
            <thead>
            <tr>
              <th scope="col">Question</th>
              <th scope="col">Delete option</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in questions">
              <td>{{item.question}}</td>
              <td>
                <button @click="deleteQuestion(item.id)" class="btn btn-danger"/>
              </td>
            </tr>
            </tbody>
          </table>

        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import axios from "axios";
import {defineComponent} from "vue";

export default defineComponent({

  data() {
    return {
      question: '',
      questions: []
    }
  },
  methods: {
    save() {
      let params = {
        'question' : this.question,
      }
      axios.post("/app/question/", params).then(
          response => {
            console.log("response: " + JSON.stringify(response));
            this.questions.push(response.data)
          }
      )
    },
    deleteQuestion(id:string){
      axios.delete("/app/question/"+id).then(
          response => {
            console.log("response: " + JSON.stringify(response));
            this.questions = this.questions.filter(
              question => question['id'] != id
            )
          }
      )
    }
  },
  mounted() {
    axios.get("/app/question/all",).then(
        response => {
          console.log("data: " + JSON.stringify(response.data));
          this.questions = response.data;
        }
    )
  }
})
</script>

<style scoped>

</style>