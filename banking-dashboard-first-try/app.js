const app = Vue.createApp({
    data() {
        return {
            firstName: 'John',
            lastName: 'Doe',
            email: 'john@gmail.com',
            gender: 'male',
            picture: 'https://randomuser.me/api/portraits/men/10.jpg',
            ipAddress: 'Ip is loading...',
            date1: 'Date 1',
            date2: 'Date 2',
            date3: 'Date 3',
            date4: 'Date 4',
            date5: 'Date 5',
            height1: '0',
            height2: '0',
            height3: '0',
            height4: '0',
            height5: '0',
        }
    },
    methods: {
        async getUser() {
            const res = await fetch('https://randomuser.me/api')
            const { results } = await res.json()

            console.log(res);
            console.log(results);

            this.firstName = results[0].name.first
            this.lastName = results[0].name.last
            this.email = results[0].email
            this.gender = results[0].gender
            this.picture = results[0].picture.large
        },

        async getData() {
            const response = await fetch('http://192.168.1.199:8080/data');
            const results = await response.json();

            // setTimeout(function () {
            //     console.log('now');

                this.height1 = results[0].value
                this.height2 = results[1].value
                this.height3 = results[2].value
                this.height4 = results[3].value
                this.height5 = results[4].value
            // }, 2000);

            console.log(results);
            console.log(results[0]);

        },

        async getIpAddress() {
            const res = await fetch('https://api.ipify.org?format=json')
            const { results } = await res.json()

            console.log(results);

            this.ipAddress = results[0].ip
        }
    }
})

app.mount('#app')