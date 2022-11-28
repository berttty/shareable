<script setup lang="ts">
import { defineComponent } from 'vue'
import {getRandomInt} from '../utils/random'

export interface Props {
  question?: string
  counter?: number
}

const props = withDefaults(defineProps<Props>(), {
  question: () => {
    //TODO recovery from database
    let queries: string[] = [
      'query 1: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 2: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 3: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 4: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 5: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 6: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 7: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 8: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 9: Lorem ipsum dolor sit amet, consectetur adipiscing?',
      'query 0: Lorem ipsum dolor sit amet, consectetur adipiscing?'];
    return queries[getRandomInt(queries.length)];
  },
  counter: 1000
})
</script>

<template>
  <div class="row justify-content-center">
    <div class="col-md-6 align-self-center">
      <div class="p-2">
        <h1>{{question}}</h1>
        <form class="mt-2">
          <div class="form-check ps-0">
            <label for="exampleInputPassword1" class="form-label">Write your mind you have {{ counter }} characters</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="10"></textarea>
          </div>
          <div class="d-grid gap-2 pt-2">
            <button type="submit" class="btn btn-primary">Submit</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
