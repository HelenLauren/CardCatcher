<template>
  <q-layout view="lHh lpr lFf">
    <q-page-container>
      <q-page class="flex flex-center bg-gradient">
        <Dashboard v-if="user" />
        <AuthCard v-else />
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { useAuth } from './composables/useAuth'
import AuthCard from './components/AuthCard.vue'
import Dashboard from './components/Dashboard.vue'

const { user } = useAuth()
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  margin: 0;
  padding: 0;
  font-family: 'Roboto', sans-serif;
}

.bg-gradient {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
}
</script>