import React from 'react'
import Button from './Button'
import Upload from './Upload'

function UploadPanel() {

  function onSubmit(e) {
    e.preventDefault();
    const files = document.getElementById('csvInput').files;
    const file = files[0]

    console.log(file);


    // nur CSV-Dateien
    if (!file.type.match('text/csv')) {
      alert('Failed: Please use a CSV file!')
      console.log('Failed: Please use a CSV file!');
      return;
    }


    const reader = new FileReader()
    reader.onload = handleFileLoad;
    reader.readAsText(file);
  }

  function handleFileLoad(event) {
    fetch('http://localhost:8080/upload', {
      method: "POST",
      body: event.target.result,
    }).then((response) => {

      if (response.status === 200) {
        this.uploadStatus = 'Success';
        this.uploadStyle = 'green';
      }
    });
  }

  return (
    <div className='fixed w-screen p-4 flex justify-center z-10 bg-[rgb(1,1,1,.25)]  shadow-[0_70px_35px_100px_rgba(1,1,1,.25)]'>
      <div className='sm:w-screen md:w-3/4-screen sm:h-screen md:h-3/4-screen p-4 
      rounded-3xl bg-white shadow-xl flex flex-col '>
        <h1 className='text-2xl font-bold'>File Upload</h1>
        <br />

        <form id="uploadform" method="post" enctype="multipart/form-data" onSubmit={onSubmit}>

          <Upload />

          <br />


          <Button type="submit" size="xll" text="Upload" addStyle="w-full" />


          {/* <input type="submit" value="Upload File" name="submit" /> */}
          {/* <p v-bind:class="`${uploadStyle}`">{{ uploadStatus }}</p> */}
        </form>


      </div>
    </div>
  )
}

export default UploadPanel