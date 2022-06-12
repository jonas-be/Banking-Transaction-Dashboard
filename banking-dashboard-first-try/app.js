const app = Vue.createApp({
    data() {
        return {
            uploadStatus: '🟡 Pending',
            uploadStyle: 'yellow',
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
            const response = await fetch('http://localhost:8080/data');
            const results = await response.json();

            document.getElementById("graph-item-1").animate({'height': results[0].value + '%'}, 501);
            document.getElementById("graph-item-2").animate({'height': results[1].value + '%'}, 501);
            document.getElementById("graph-item-3").animate({'height': results[2].value + '%'}, 501);
            document.getElementById("graph-item-4").animate({'height': results[3].value + '%'}, 501);
            document.getElementById("graph-item-5").animate({'height': results[4].value + '%'}, 501);
            setTimeout(function() {
                document.getElementById("graph-item-1").style.setProperty('height', results[0].value + '%')
                document.getElementById("graph-item-2").style.setProperty('height', results[1].value + '%')
                document.getElementById("graph-item-3").style.setProperty('height', results[2].value + '%')
                document.getElementById("graph-item-4").style.setProperty('height', results[3].value + '%')
                document.getElementById("graph-item-5").style.setProperty('height', results[4].value + '%')
            }, 500);

            console.log(results);
            console.log(results[0]);
        },

    }
})


app.mount('#app')


const form = document.querySelector("#uploadform");

form.addEventListener("submit", function (evt) {
    evt.preventDefault();
    const files = document.querySelector('[type=file]').files;
    const file = files[0]

    // nur TXT-Dateien
    if (!file.type.match('text/csv')) {
        // Not working because out of vue.js!
        this.uploadStatus = 'Failed: Please use a CSV file!';
        this.uploadStyle = 'red';
        console.log('Failed: Please use a CSV file!');

        return;
    }

    console.log(file);


    const reader = new FileReader()
    reader.onload = handleFileLoad;
    reader.readAsText(file);
});

function handleFileLoad(event) {
    fetch('http://localhost:8080/upload', {
        method: "POST",
        body: event.target.result,
    }).then((response) => {
        console.log(response);

        if (response.status === 200) {
            this.uploadStatus = 'Success';
            this.uploadStyle = 'green';
        }
    });
}