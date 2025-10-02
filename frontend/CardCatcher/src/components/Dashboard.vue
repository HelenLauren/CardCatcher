<template>
  <q-card class="dashboard-card">
    <q-card-section class="bg-primary text-white">
      <div class="text-h5 text-weight-bold">Welcome!</div>
      <div class="text-subtitle2">{{ userEmail }}</div>
    </q-card-section>

    <q-separator />

    <q-card-section>
      <div class="q-pa-md">
        <div class="row items-center q-mb-md">
          <q-icon name="person" size="md" color="primary" class="q-mr-md" />
          <div>
            <div class="text-subtitle2 text-weight-medium">Full Name</div>
            <div class="text-body2 text-grey-7">{{ userName || 'Not provided' }}</div>
          </div>
        </div>

        <div class="row items-center q-mb-md">
          <q-icon name="email" size="md" color="primary" class="q-mr-md" />
          <div>
            <div class="text-subtitle2 text-weight-medium">Email</div>
            <div class="text-body2 text-grey-7">{{ userEmail }}</div>
          </div>
        </div>

        <div class="row items-center">
          <q-icon name="access_time" size="md" color="primary" class="q-mr-md" />
          <div>
            <div class="text-subtitle2 text-weight-medium">Member Since</div>
            <div class="text-body2 text-grey-7">{{ joinedDate }}</div>
          </div>
        </div>
      </div>
    </q-card-section>

    <q-separator />

    <q-card-actions class="q-pa-md">
      <q-btn
        label="Sign Out"
        color="negative"
        flat
        icon="logout"
        class="full-width"
        @click="handleSignOut"
        :loading="loading"
      />
    </q-card-actions>
  </q-card>
</template>

<script setup>
import { computed, ref } from 'vue'
import { useQuasar } from 'quasar'
import { useAuth } from '../composables/useAuth'

const $q = useQuasar()
const { user, signOut } = useAuth()
const loading = ref(false)

const userName = computed(() => user.value?.user_metadata?.full_name || '')
const userEmail = computed(() => user.value?.email || '')
const joinedDate = computed(() => {
  if (!user.value?.created_at) return ''
  return new Date(user.value.created_at).toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
})

const handleSignOut = async () => {
  loading.value = true

  const { error } = await signOut()

  if (error) {
    $q.notify({
      type: 'negative',
      message: error.message || 'Failed to sign out',
      position: 'top',
      timeout: 3000
    })
  } else {
    $q.notify({
      type: 'positive',
      message: 'Successfully signed out',
      position: 'top',
      timeout: 2000
    })
  }

  loading.value = false
}
</script>

<style scoped>
.dashboard-card {
  width: 100%;
  max-width: 500px;
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
}
</style>
