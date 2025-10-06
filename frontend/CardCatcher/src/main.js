import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

import { Quasar, Notify } from 'quasar'
import quasarLang from 'quasar/lang/pt-BR'
import quasarIconSet from 'quasar/icon-set/material-icons'
import '@quasar/extras/material-icons/material-icons.css'
import 'quasar/src/css/index.sass'

const app = createApp(App)

app.use(Quasar, {
  lang: quasarLang,
  iconSet: quasarIconSet,
  plugins: {
    Notify
  }
})

app.mount('#app')
