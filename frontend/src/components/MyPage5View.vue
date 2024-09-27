<template>

    <v-data-table
        :headers="headers"
        :items="myPage5"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MyPage5View',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "orderId", value: "orderId" },
                { text: "productId", value: "productId" },
                { text: "deliveryStatus", value: "deliveryStatus" },
                { text: "orderStatus", value: "orderStatus" },
            ],
            myPage5 : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/myPage5s'))

            temp.data._embedded.myPage5s.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.myPage5 = temp.data._embedded.myPage5s;
        },
        methods: {
        }
    }
</script>

