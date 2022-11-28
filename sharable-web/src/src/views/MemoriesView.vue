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
          <h3>{{question.question.question}}</h3>
        </div>
        <div class="card-body">
          {{question.answer}}
        </div>
        <div class="card-footer ">
          <div class="d-flex justify-content-between">
            <button @click="like('i_like_it')" type="button" class="btn btn-outline-success" style="border-radius: 50%;">
              <i class="bi bi-balloon-heart-fill" style="font-size: 2.5em"> {{question.i_like_it}}</i>
            </button>
            <button @click="like('make_me_meditate')" type="button" class="btn btn-outline-info" style="border-radius: 50%;">
              <i class="bi bi-cup-hot-fill" style="font-size: 2.5em">{{question.make_me_meditate}}</i>
            </button>
            <button @click="like('so_sad')" type="button" class="btn btn-outline-danger" style="border-radius: 50%;">
              <i class="bi bi-emoji-frown" style="font-size: 2.5em">{{question.so_sad}}</i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center m-4">
      <div class="card">
        <div class="card-header">
          <label for="comment" class="form-label">Add your comment</label>
          <textarea class="form-control" id="comment" v-model="comment" rows="10" placeholder="write your comment" required></textarea>
          <div class="d-grid gap-2 pt-2">
            <a class="btn btn-primary" @click="save()">
              <i class="bi bi-chat-right-dots"></i> Comment
            </a>
          </div>

        </div>
        <div class="card-body">
          <h2>Previous comments</h2>
          <ul class="list-group">
            <li class="list-group-item" v-for="item in question.comments">
              {{item.comment}}
            </li>
          </ul>

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
      question: {
        id: '',
        question: {
          question: ''
        },
        answer: '',
        comments: [{
          comment: ''
        }],
        i_like_it: 0,
        make_me_meditate: 0,
        so_sad: 0,
      },
      comment: '',
    }
  },
  methods: {
    getMemory(id: string){
      axios.get("/app/memory/"+id,).then(
          response => {
            this.question = response.data;
          }
      );
      this.comment = '';
    },
    getOther(){
      this.getMemory('');
    },
    save() {
      let params = {
        'comment' : this.comment,
        'timestamp': Date.now(),
      }
      let id: string = this.question.id;
      axios.post("/app/memory/"+id+"/comment", params).then(
          response => {
            this.question.comments = response.data.comments;
          }
      )
    },
    like(button:string){
      let id: string = this.question.id;
      axios.post("/app/memory/"+id+"/counter/"+button, ).then(
          response => {
            this.question = response.data;
          }
      )
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
