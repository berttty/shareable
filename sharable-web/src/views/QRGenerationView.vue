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
              <label for="name" class="form-label">Intervention Name</label>
              <input type="text" class="form-control" id="name" v-model="name" placeholder="Please add the intervention name" required />
            </div>
            <div class="mb-3">
              <label for="place" class="form-label">Intervention Place</label>
              <input type="text" class="form-control" id="place" v-model="place" placeholder="Please add the intervention place" required />
            </div>
            <div class="mb-3">
              <label for="date" class="form-label">Intervention Date and Time (Format: dd/mm/yyyy HH:MM) </label>
              <input type="datetime-local" class="form-control" id="date" v-model="date" placeholder="Please add the intervetion time with the Format: dd/mm/yyyy HH:MM"  required />
            </div>
            <qrcode-vue v-show="isGenerated" :value="value" :size="size" level="H" />

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
              <th scope="col">name</th>
              <th scope="col">place</th>
              <th scope="col">date</th>
              <th scope="col">QR</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in qrs">
                <td>{{item.name}}</td>
                <td>{{item.place}}</td>
                <td>{{item.moment_intervention}}</td>
                <td>
                  <qrcode-vue :value="item.id" :size="50" level="H" />
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
import {defineComponent, onMounted, ref} from "vue";
import QrcodeVue from 'qrcode.vue'

//
// let answer = ref();
// let query = ref();


export default defineComponent({
  // props: {
  //   name: { type: String, required: true },
  //   place: { type: String, required: true },
  //   date: {type:Date, required: true},
  // },
  data() {
    return {
      name: '',
      place: '',
      date: '',
      isGenerated: false,
      value: '',
      size: 300,
      qrs: []
    }
  },
  methods: {
    save() {
      let params = {
        'name' : this.name,
        'place' : this.place,
        'moment_intervention' : this.date
      }
      axios.post("/app/qr/", params).then(
        response => {
          console.log("response: " + JSON.stringify(response));
          this.isGenerated = true;
          this.value = response.data;
        }
      )


    }
  },
  mounted() {
    axios.get("/app/qr/",).then(
      response => {
        console.log("data: " + JSON.stringify(response.data));
        this.qrs = response.data;
      }
    )
  },
  components: {
    QrcodeVue,
  }
})
</script>

<style scoped>

</style>