<template>
  <div>
    <div class="master-detail-caption">{{ detailInfo }}</div>
    <DxDataGrid
        :ref="rolesGridRefName"
        :data-source="userAuthorizationDataSource"
        :show-borders="true"
        :allow-column-resizing="true"
        id="grdUserRoles"
    >
      <DxEditing
          :allow-adding="true"
          :allow-deleting="true"
          mode="popup"
      />

      <DxColumn data-field="role" :caption="$t('app.general.account.role')" :calculate-display-value= "displayRoleDescription">
        <DxLookup
            :data-source="roleDataSource"
            value-expr="id"
            display-expr="description"
        />
      </DxColumn>
    </DxDataGrid>
  </div>
</template>
<script>

import {DxColumn, DxDataGrid, DxEditing, DxLookup} from 'devextreme-vue/data-grid';
import kazu from "@/app/common/utils/kazu";
import {CustomStoreCreator} from "@/app/common/utils/CustomStoreCreator";
import {UserAuthorizationDao} from "@/views/general/accountOperations/persistence/UserAuthorizationDao";

const rolesGridRefName = "rolesGridRefName"
export default {
  props: {
    templateData: {
      type: Object,
      default: () => {
      }
    }
  },
  components: {
    DxDataGrid,
    DxColumn,
    DxEditing,
    DxLookup
  },
  data() {
    let {id,name,surname} = this.templateData.data;
    return {
      rolesGridRefName,
      name,
      surname,
      userAuthorizationDataSource: UserAuthorizationDao().userAuthorizationDataSource(id),
      roleDataSource: CustomStoreCreator().createStore("/user/role/get/"),
      detailInfo: `${name } ${ surname }`
    };
  },
  methods: {
    displayRoleDescription(data) {
      return kazu.isObjectEmpty(data) ? "" : data.role.description;
    }
  }
};
</script>
<style>
#data-grid-demo {
  min-height: 700px;
}
</style>
