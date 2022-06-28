import React from 'react'

function Upload() {
    return (
        <div className='w-full'>

            <label class="block w-full pt-1.5 px-1.5 bg-slate-100 rounded-xl">
                <span class="sr-only">Choose File</span>
                <input  id="csvInput" type="file" class="block w-full text-sm text-gray-600 
                
                file:text-sm file:font-semibold
                file:text-white 
                file:text-center
                file:border-none
                file:bg-gradient-to-r file:from-cyan-500 file:to-blue-500 file:hover:bg-gradient-to-bl file:rounded-lg 
                file:px-4 file:py-1.5 file:mr-2 file:mb-2
                " />
            </label>
        </div>


    )
}

export default Upload
