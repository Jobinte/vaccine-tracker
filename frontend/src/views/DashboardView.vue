<template>
  <div class="dashboard">


    <h1>Vaccine Dashboard 💉</h1>

   <button class="logout-btn" @click="logout">Logout</button>


    <hr />
    </div>

    <!-- ADD VACCINE -->
    <div class="section">
    <h2>Add Vaccine Type</h2>


    <input v-model="name" placeholder="Vaccine name" />
    <input v-model="totalDoses" placeholder="Total doses" type="number" />
    <input v-model="gapDays" placeholder="Gap days" type="number" />

    <br /><br />
    <button @click="addVaccine">Add Vaccine Type</button>

    <hr />

    <!-- AVAILABLE VACCINES -->
    <h2>Available Vaccines</h2>

    <ul>
      <li v-for="v in vaccines" :key="v.id">
        <b>{{ v.name }}</b> — {{ v.totalDoses }} doses

        <div style="margin:10px">
          <!-- Dynamic dose buttons -->
          <button
            v-for="dose in v.totalDoses"
            :key="dose"
            @click="takeDose(v.name, dose)"
            :disabled="hasTakenDose(v.name, dose)"
          >
            {{ hasTakenDose(v.name, dose) ? "Taken ✔" : "Take Dose " + dose }}
          </button>
        </div>

        <hr />
      </li>
    </ul>

    <!-- MY RECORDS -->
    <h2>My Vaccination Records</h2>

    <ul>
      <li v-for="r in myVaccines" :key="r.id">

        💉 <b>{{ r.vaccineName }}</b> — Dose {{ r.doseNumber }}
        <br />

        Taken: {{ formatDate(r.takenDate) }}
        <br />

        <span v-if="r.nextDueDate">
          Next Dose Due: <b style="color:green">{{ formatDate(r.nextDueDate) }}</b>
        </span>

        <span v-else>
          Completed ✅
        </span>

        <hr />
      </li>
    </ul>

  </div>
</template>

<script>
import api from "../services/api";

export default {
  data() {
    return {
      vaccines: [],
      myVaccines: [],
      name: "",
      totalDoses: "",
      gapDays: ""
    };
  },

  async mounted() {
    await this.loadDashboard();
  },

  methods: {

    // ⭐ LOAD EVERYTHING
    async loadDashboard() {
      await this.fetchVaccines();
      await this.fetchMyVaccines();
    },

    // ⭐ LOGOUT
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    },

    // ⭐ FETCH VACCINES
    async fetchVaccines() {
      const res = await api.get("/vaccines");
      this.vaccines = res.data;
    },

    // ⭐ FETCH USER RECORDS
    async fetchMyVaccines() {
      const res = await api.get("/user-vaccines");
      this.myVaccines = res.data;
    },

    // ⭐ ADD VACCINE TYPE
    async addVaccine() {
      if (!this.name || !this.totalDoses || !this.gapDays) {
        alert("Fill all fields");
        return;
      }

      await api.post("/vaccines", {
        name: this.name,
        totalDoses: this.totalDoses,
        gapDays: this.gapDays
      });

      // reset inputs
      this.name = "";
      this.totalDoses = "";
      this.gapDays = "";

      await this.fetchVaccines();
    },

    // ⭐ TAKE DOSE
    async takeDose(vaccineName, dose) {
      await api.post(`/user-vaccines/${vaccineName}/${dose}`);
      await this.fetchMyVaccines();
    },

    // ⭐ DISABLE BUTTON IF DOSE ALREADY TAKEN
    hasTakenDose(vaccineName, dose) {
      return this.myVaccines.some(
        v => v.vaccineName === vaccineName && v.doseNumber === dose
      );
    },

    // ⭐ FORMAT DATE NICE
    formatDate(date) {
      if (!date) return "";
      return new Date(date).toLocaleDateString();
    }
  }

};
</script>
