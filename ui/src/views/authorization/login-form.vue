<template>
  <form class="login-form" @submit.prevent="onSubmit">
    <dx-form :form-data="formData" :disabled="loading">
      <dx-item
        data-field="email"
        editor-type="dxTextBox"
        :editor-options="{ mode: 'email',stylingMode: 'filled' }"
      >
        <dx-required-rule :message="$t('validation.login.require-email')" />
        <dx-email-rule :message="$t('validation.login.email-rule')" />
        <dx-label :visible="false" />
      </dx-item>
      <dx-item
        data-field='password'
        editor-type='dxTextBox'
        :editor-options="{  mode: 'password',stylingMode: 'filled' }"
      >
        <dx-required-rule :message="$t('validation.login.require-password')" />
        <dx-label :visible="false" />
      </dx-item>
      <dx-button-item>
        <dx-button-options
          width="100%"
          type="default"
          template="signInTemplate"
          :use-submit-behavior="true"
        >
        </dx-button-options>
      </dx-button-item>
      <template #signInTemplate>
        <div>
          <span class="dx-button-text">
            <dx-load-indicator v-if="loading" width="24px" height="24px" :visible="true" />
            <span v-if="!loading">{{ $t('app.login.sign-in') }}</span>
          </span>
        </div>
      </template>
    </dx-form>
  </form>
</template>

<script>
import DxLoadIndicator from "devextreme-vue/load-indicator";
import DxForm, {DxButtonItem, DxButtonOptions, DxEmailRule, DxItem, DxLabel, DxRequiredRule} from "devextreme-vue/form";

import auth from "../../app/common/auth";
import {reactive, ref} from 'vue';
import {useRoute, useRouter} from 'vue-router';

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();

    const formData = reactive({
      email:"",
      password:""
    });
    const loading = ref(false);

    async function onSubmit() {
      const { email, password } = formData;
      loading.value = true;
      const result = await auth.logIn(email, password);
      if (!result.isOk) {
        loading.value = false;
      } else {
        router.push(route.query.redirect || "/home");
      }
    }

    return {
      formData,
      loading,
      onSubmit
    };
  },
  components: {
    DxLoadIndicator,
    DxForm,
    DxRequiredRule,
    DxItem,
    DxLabel,
    DxButtonItem,
    DxButtonOptions,
    DxEmailRule
  }
};
</script>

<style lang="scss">
@import "../../app/common/themes/generated/variables.base.scss";

.login-form {
  .link {
    text-align: center;
    font-size: 16px;
    font-style: normal;

    a {
      text-decoration: none;
    }
  }

  .form-text {
    margin: 10px 0;
    color: rgba($base-text-color, alpha($base-text-color) * 0.7);
  }
}
</style>
