/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ], theme: {
    screens: {
      'sm': '0px',
      'md': '780px',
      'lg': '1300px',
    },
    extend: {
      fontFamily: {
        'montserrat': ['"Montserrat"', 'cursive']
      },
      colors: {
        // Configure your color palette here
        'accent': 'rgb(217 70 239)',
      },
      height: {
        '1/4-screen': '25vh',
        '2/4-screen': '50vh',
        '3/4-screen': '75vh',
      },
      width: {
        '1/4-screen': '25vw',
        '2/4-screen': '50vw',
        '3/4-screen': '75vw',
        '80-screen': '80vw',
        '90-screen': '90vw',
      }
    },
  },
  plugins: [],
}
