<template>
  <q-card class="auth-card">
    <q-card-section class="text-center q-pt-lg">
      <div class="text-h4 text-weight-bold text-grey-8 q-mb-sm">
        {{ isLogin ? 'Welcome Back' : 'Create Account' }}
      </div>
      <div class="text-subtitle2 text-grey-6">
        {{ isLogin ? 'Sign in to continue' : 'Sign up to get started' }}
      </div>
    </q-card-section>

    <q-card-section class="q-px-lg q-pb-lg">
      <q-form @submit="handleSubmit" class="q-gutter-md">
        <q-input
          v-if="!isLogin"
          v-model="form.name"
          outlined
          label="Full Name"
          :rules="[val => !!val || 'Name is required']"
          lazy-rules
        >
          <template v-slot:prepend>
            <q-icon name="person" />
          </template>
        </q-input>

        <q-input
          v-model="form.email"
          outlined
          type="email"
          label="Email"
          :rules="[
            val => !!val || 'Email is required',
            val => /.+@.+\..+/.test(val) || 'Please enter a valid email'
          ]"
          lazy-rules
        >
          <template v-slot:prepend>
            <q-icon name="email" />
          </template>
        </q-input>

        <q-input
          v-model="form.password"
          outlined
          :type="isPwd ? 'password' : 'text'"
          label="Password"
          :rules="[
            val => !!val || 'Password is required',
            val => val.length >= 6 || 'Password must be at least 6 characters'
          ]"
          lazy-rules
        >
          <template v-slot:prepend>
            <q-icon name="lock" />
          </template>
          <template v-slot:append>
            <q-icon
              :name="isPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isPwd = !isPwd"
            />
          </template>
        </q-input>

        <q-input
          v-if="!isLogin"
          v-model="form.confirmPassword"
          outlined
          :type="isConfirmPwd ? 'password' : 'text'"
          label="Confirm Password"
          :rules="[
            val => !!val || 'Please confirm your password',
            val => val === form.password || 'Passwords do not match'
          ]"
          lazy-rules
        >
          <template v-slot:prepend>
            <q-icon name="lock" />
          </template>
          <template v-slot:append>
            <q-icon
              :name="isConfirmPwd ? 'visibility_off' : 'visibility'"
              class="cursor-pointer"
              @click="isConfirmPwd = !isConfirmPwd"
            />
          </template>
        </q-input>

        <div v-if="isLogin" class="text-right">
          <a href="#" class="text-primary text-caption" @click.prevent="handleForgotPassword">
            Forgot password?
          </a>
        </div>

        <q-btn
          type="submit"
          :label="isLogin ? 'Sign In' : 'Sign Up'"
          color="primary"
          class="full-width q-mt-md"
          size="md"
          rounded
          unelevated
          :loading="authLoading"
        />
      </q-form>

      <div class="q-mt-lg text-center">
        <div class="text-grey-7">
          {{ isLogin ? "Don't have an account?" : 'Already have an account?' }}
          <a
            href="#"
            class="text-primary text-weight-medium"
            @click.prevent="toggleMode"
          >
            {{ isLogin ? 'Sign Up' : 'Sign In' }}
          </a>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useQuasar } from 'quasar'
import { useAuth } from '../composables/useAuth'

const $q = useQuasar()
const { signIn, signUp, resetPassword } = useAuth()

const isLogin = ref(true)
const isPwd = ref(true)
const isConfirmPwd = ref(true)
const authLoading = ref(false)

const form = reactive({
  name: '',
  email: '',
  password: '',
  confirmPassword: ''
})

const toggleMode = () => {
  isLogin.value = !isLogin.value
  form.name = ''
  form.email = ''
  form.password = ''
  form.confirmPassword = ''
}

const handleSubmit = async () => {
  authLoading.value = true

  try {
    if (isLogin.value) {
      const { data, error } = await signIn(form.email, form.password)

      if (error) {
        $q.notify({
          type: 'negative',
          message: error.message || 'Failed to sign in',
          position: 'top',
          timeout: 3000
        })
      } else {
        $q.notify({
          type: 'positive',
          message: 'Successfully signed in!',
          position: 'top',
          timeout: 2000
        })
      }
    } else {
      const { data, error } = await signUp(form.email, form.password, {
        full_name: form.name
      })

      if (error) {
        $q.notify({
          type: 'negative',
          message: error.message || 'Failed to sign up',
          position: 'top',
          timeout: 3000
        })
      } else {
        $q.notify({
          type: 'positive',
          message: 'Account created successfully!',
          position: 'top',
          timeout: 2000
        })

        form.name = ''
        form.email = ''
        form.password = ''
        form.confirmPassword = ''
      }
    }
  } catch (err) {
    $q.notify({
      type: 'negative',
      message: 'An unexpected error occurred',
      position: 'top',
      timeout: 3000
    })
  } finally {
    authLoading.value = false
  }
}

const handleForgotPassword = async () => {
  if (!form.email) {
    $q.notify({
      type: 'warning',
      message: 'Please enter your email address first',
      position: 'top',
      timeout: 2000
    })
    return
  }

  const { error } = await resetPassword(form.email)

  if (error) {
    $q.notify({
      type: 'negative',
      message: error.message || 'Failed to send reset email',
      position: 'top',
      timeout: 3000
    })
  } else {
    $q.notify({
      type: 'positive',
      message: 'Password reset email sent! Check your inbox.',
      position: 'top',
      timeout: 3000
    })
  }
}
</script>

<style scoped>
.auth-card {
  width: 100%;
  max-width: 450px;
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
}

a {
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
