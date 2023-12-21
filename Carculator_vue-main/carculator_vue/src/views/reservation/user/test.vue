<script setup>
import { onMounted, ref } from "vue";
// import { userRouter } from "vue-router";
import axios from "axios";

let membercode = null;
const car_id = ref(0);

const items = [
  { text: "이름", value: "", icon: "mdi-account-outline" },
  { text: "이메일", value: "" },
  { text: "연락처", value: "" },
  { text: "성별", value: "" },
  { text: "생년월일", value: "" },
];

const mycar = [
  { text: "차주명", value: "" },
  { text: "차종", value: "" },
  { text: "차량번호", value: "" },
  { text: "차량연식", value: "" },
];

onMounted(async () => {
  membercode = sessionStorage.getItem("memberCode");
  await fetchMyInfo();
  await fetchMyCar();
});

const formatPhoneNumber = (phoneNumber) => {
  const cleaned = ('' + phoneNumber).replace(/\D/g, '');
  const match = cleaned.match(/^(\d{3})(\d{3,4})(\d{4})$/);

  if (match) {
    return match[1] + '-' + match[2] + '-' + match[3];
  }

  return phoneNumber;
};

const formatBitrhData = (birthDate) => {
  const cleaned = ('' + birthDate).replace(/\D/g, '');
  const match = cleaned.match(/^(\d{4})(\d{2})(\d{2})$/);

  if (match) {
    return match[1] + '년 ' + match[2] + '월 ' + match[3] + '일';
  }

  return birthDate;
};

const fetchMyInfo = async () => {
  try {
    const res = await axios.get("http://localhost:8888/member/" + membercode);
    const userData = await res.data;

    items[0].value = userData.name;
    items[1].value = userData.email;
    const originalPhone = userData.phone;
    const formattedPhone = formatPhoneNumber(originalPhone);
    items[2].value = formattedPhone;
    items[3].value = userData.gender;
    const originalBirth = userData.birth;
    const formattedBirth = formatBitrhData(originalBirth);
    items[4].value = formattedBirth;

    document.querySelector(".userItem:nth-child(1)").click();
    document.querySelector(".userItem:nth-child(1)").click();
  } catch (error) {
    console.log("에러 내용 : " + error);
  }
};

const fetchMyCar = async () => {
  try {
    const response = await axios.get("http://localhost:8888/car/" + membercode);
    const data = await response.data;
    console.log(data);

    mycar[0].value = data.name;
    mycar[1].value = data.car_model;
    mycar[2].value = data.car_number;
    mycar[3].value = data.car_year;

    car_id.value = data.car_id;

    document.querySelector(".mycarItem:nth-child(1)").click();
    document.querySelector(".mycarItem:nth-child(1)").click();
  } catch (error) {
    console.error("\nError : " + error);
  }
};
</script>


<template>
<VDivider />
  <VList>
    <VListItem :prepend-avatar="avatar1" title="나의 정보" subtitle="">
      <template #append>
        <VBtn size="small" variant="text" icon="mdi-menu-down" />
      </template>
    </VListItem>
  </VList>

  <VDivider />

  <VList :lines="false" density="compact" nav>
    <VListItem
      v-for="(item, i) in items"
      :key="i"
      :value="item"
      color="primary"
      class="userItem"
    >
      <template #prepend>
        <VIcon :icon="item.icon" />
      </template>

      <VListItemTitle> {{ item.text }} : {{ item.value }} </VListItemTitle>
    </VListItem>
    <div class="buttonWrapper" style="text-align: center">
      <VBtn
        @click="this.$router.push('/')"
        color="success"
        variant="tonal"
        style="margin-top: 10px; margin-bottom: 5px; margin-right: 5px"
      >
        &nbsp;&nbsp;홈으로&nbsp;&nbsp;
      </VBtn>
      <VBtn
        @click="changePassword"
        color="error"
        variant="tonal"
        style="margin-top: 10px; margin-bottom: 5px; margin-left: 5px"
      >
        비밀번호 변경
      </VBtn>
    </div>
  </VList>

  <VDivider />

  <VList>
    <VListItem title="나의 차량정보">
      <template #append>
        <VBtn size="small" variant="text" icon="mdi-menu-down" />
      </template>
    </VListItem>
  </VList>

  <VDivider />

  <VList :lines="false" density="compact" nav>
    <VListItem
      v-for="(item, i) in mycar"
      :key="i"
      :value="item"
      color="primary"
      class="mycarItem"
    >
      <template #prepend>
        <VIcon :icon="item.icon" />
      </template>

      <VListItemTitle> {{ item.text }} : {{ item.value }} </VListItemTitle>
    </VListItem>
    <div class="buttonsContainer">
      <div class="buttons" style="text-align: center">
        <VBtn
          @click="$router.push('/mycarWrite')"
          color="success"
          variant="tonal"
          style="margin-bottom: 10px; margin-top: 10px; margin-right: 5px"
        >
          차량등록
        </VBtn>
        <VBtn
          @click="deleteMyCar"
          color="error"
          variant="tonal"
          style="margin-bottom: 10px; margin-top: 10px; margin-left: 5px"
        >
          차량삭제
        </VBtn>
      </div>
    </div>
  </VList>
</template>

<script>
export default {
  methods: {
    movePage() {
      this.$router.push("/main");
    },
    async deleteMyCar() {
      const membercode = sessionStorage.getItem("memberCode");
      if (
        confirm(
          "삭제한 차량정보는 되돌릴 수 없습니다.\n삭제를 진행하시겠습니까?"
        )
      ) {
        try {
          const response = await axios.delete(
            "http://localhost:8888/car/" + membercode
          );
          alert("차량 정보가 삭제되었습니다.");

          if (response.status === 200) {
            this.movePage();
          } else {
            alert(
              "등록된 차량이 없거나 삭제 과정에서 문제가 발생했습니다.\n계속 이런 현상이 발생한다면 고객센터에 문의해주세요."
            );
          }
        } catch (error) {
          alert(
            "등록된 차량이 없거나 삭제 과정에서 문제가 발생했습니다.\n계속 이런 현상이 발생한다면 고객센터에 문의해주세요."
          );
          this.$router.push("/mycarInfo");
        }
      }
    },
    changePassword() {
      this.$router.push("/changePassword");
    },
  },
};
</script>

<style>
</style>