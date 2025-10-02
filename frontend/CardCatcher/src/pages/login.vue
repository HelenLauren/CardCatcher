<template>
  <q-layout>
    <q-page-container>
      <q-page class="flex flex-center" style="font-family: Lato; background: #fff;">
        <q-card :style="$q.platform.is.desktop ? 'width:55%;' : ''" 
                class="row my-card items-center q-pa-none q-ma-none shadow-24"
                style="background: #fff;">
          
          <!-- LADO ESQUERDO -->
          <q-card-section v-if="$q.platform.is.desktop" 
                          class="col-md-4 col-lg-4 col-sm-12 sol-xs-12 items-center float-left text-white"
                          :style="{'height':(win_height-270)+'px','background':'linear-gradient(145deg, #aa00ff, #23a632)'}"
                          horizontal>
            <div class="text-center full-width">
              <div><img src="images/logo.png" style="width: 33%"/></div>
              <div class="text-weight-bolder text-h6">Card Catcher</div>
              <div class="text-caption">Manage your Pokémon TCG Collection</div>
            </div>
          </q-card-section>
          
          <!-- LADO DIREITO -->
          <q-card-section class="col-md-8 col-lg-8 col-sm-12 sol-xs-12 float-left">
            <q-card-section class="items-center">
              <div>
                <div v-if="!$q.platform.is.desktop" 
                     class="text-weight-bolder text-center q-mb-md text-h6"
                     style="color:#aa00ff">
                     Quasar Shopping
                </div>
                
                <q-form :style="$q.platform.is.desktop ? 'width:55%;margin: auto;' : 'margin: auto;'" class="q-gutter-md">
                  <span class="text-subtitle1 text-weight-bold" style="color:#23a632">Welcome Aboard</span>
                  
                  <q-input
                    dense
                    outlined
                    v-model="username"
                    label="Username"
                    lazy-rules
                    color="purple"
                  />

                  <q-input
                    dense
                    type="password"
                    outlined
                    v-model="password"
                    label="Password"
                    lazy-rules
                    color="green"
                  />

                  <q-checkbox class="text-grey-8" dense v-model="remember_me" label="Remember me" color="purple"/>

                  <div>
                    <q-btn class="text-capitalize" 
                           size="sm" 
                           style="width:75px" 
                           dense 
                           label="Login" 
                           to="/home" 
                           type="button"
                           :style="{'background':'#23a632','color':'#fff'}"/>
                    
                    <q-btn class="float-right text-capitalize" 
                           size="sm" 
                           style="width:75px; border: 1px solid #aa00ff; color:#aa00ff" 
                           dense 
                           label="Sign Up"
                           type="button"/>
                  </div>
                </q-form>

              </div>
            </q-card-section>
          </q-card-section>

        </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  data() {
    return {
      username: 'mayur',
      password: 'mayur@qu',
      remember_me: false
    }
  },
  computed: {
    win_width() {
      return this.$q.screen.width - 59;
    },
    win_height() {
      return this.$q.screen.height - 0;
    }
  }
}
</script>

<style>
  .my-card {
    height: 30%;
    background: #fff;
  }

  #particles-js {
    position: absolute;
    width: 100%;
    height: 100%;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 50% 50%;
  }

  .login-form {
    position: absolute;
  }
</style>
