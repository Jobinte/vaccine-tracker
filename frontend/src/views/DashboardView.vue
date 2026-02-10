<template>
  <div style="text-align:center;margin-top:40px">
    <h1>Vaccine Dashboard 💉</h1>

    <button @click="logout">Logout</button>

    <hr />

    <!-- ADD NEW VACCINE TYPE -->
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
        {{ v.name }} — {{ v.totalDoses }} doses
        <button @click="takeDose(v.name,1)">Take Dose 1</button>
        <button @click="takeDose(v.name,2)">Take Dose 2</button>
        <button @click="takeDose(v.name,3)">Take Dose 3</button>
      </li>
    </ul>

    <hr />

    <!-- MY VACCINES -->
    <h2>My Vaccination Records</h2>
    <ul>
      <li v-for="r in myVaccines" :key="r.id">
        💉 {{ r.vaccineName }} — Dose {{ r.doseNumber }}
        <br />
        Taken: {{ r.takenDate }}
        <br />
        Next Due: {{ r.nextDueDate || "Completed ✅" }}
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
      gapDays: "",
    };
  },

  mounted() {
    this.fetchVaccines();
    this.fetchMyVaccines();
  },

  methods: {
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    },

    async fetchVaccines() {
      const res = await api.get("/vaccines");
      this.vaccines = res.data;
    },

    async fetchMyVaccines() {
      const res = await api.get("/user-vaccines");
      this.myVaccines = res.data;
    },

    async addVaccine() {
      await api.post("/vaccines", {
        name: this.name,
        totalDoses: this.totalDoses,
        gapDays: this.gapDays,
      });

      this.fetchVaccines();
    },

    async takeDose(vaccineName, dose) {
      await api.post(`/user-vaccines/${vaccineName}/${dose}`);
      this.fetchMyVaccines();
    },
  },
};
</script>
